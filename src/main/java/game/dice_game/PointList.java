package game.dice_game;

import java.awt.Font;
import java.util.Arrays;

// import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PointList extends JPanel{

  private static final long serialVersionUID = 1L;

  JButton[] AllButton = new JButton[Data.DiceType.values().length];
  
  Data.DiceType[] selectedType = new Data.DiceType[Data.DiceType.values().length];

  int index;

  static int round = 0;

  int[] typePoint = new int[14];

  int bounsPoint = 0;
  int yahtzeeBonus = 0;

  Boolean canGetYahtzeeBonus = false;
  
  public PointList(int getIndex){
    index = getIndex;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    createButton();
    setButton();
  }
  
  public void createButton(){
    JLabel name = new JLabel(DiceGameApplication.App.getName(index));
    name.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/40));
    this.add(name);
    for(int i = 1; i<Data.DiceType.values().length ;i++) {
      JButton button = new JButton();
      button.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/60));
      button.setAlignmentX(CENTER_ALIGNMENT);
      setAction(button,i);
      this.add(button);
      AllButton[i] = button;
    }
    setButton();

    setVisible(false);
  }


  public void setButton(){
    if(DiceArray.rolling == false && DiceGameApplication.roundStart == true){
      setVisible(true);
    }else{
      setVisible(false);
    }
    for(int i = 1; i<Data.DiceType.values().length ;i++){
      if(judge()[i]!=null){
        AllButton[i].setText("<html><p style='text-align: center'>"+ Data.DiceTypeToChinese(Data.DiceType.values()[i])+"<br>將可獲得"+computeScore(i)+"點</p></html>");
      }else{
        AllButton[i].setText("<html><p style='text-align: center; color: red'>"+ Data.DiceTypeToChinese(Data.DiceType.values()[i])+"<br>"+ Data.orginalText(i)+"</p></html>");
      }
      if(selectedType[i]!=null){ 
        AllButton[i].setEnabled(false);
        AllButton[i].setText("<html><p style='text-align: center'>"+ Data.DiceTypeToChinese(Data.DiceType.values()[i])+"<br>已使用</p></html>");
      }
      
    }   
    DiceGameApplication.App.validate();
    validate();
  }

  public Data.DiceType[] judge(){
    Data.DiceType[] Type = new Data.DiceType[Data.DiceType.values().length];
    try{
      int[] DicePoint = new int[5];
      for(int i = 0 ;i<DiceArray.DiceArray.length;i++){
        DicePoint[i] = DiceArray.DiceArray[i].DICE_NUMBER;
      }
      Arrays.sort(DicePoint);
      int[] combo = new int[6];
      int connect = 0;      

      Type[11] = Data.DiceType.Chance;
      for(int i = 0 ;i<DicePoint.length;i++){
        switch (DicePoint[i]) {
          case 1:
            Type[1] = Data.DiceType.Aces;
            combo[0]++;
            break;
          case 2:
            Type[2] = Data.DiceType.Twos;
            combo[1]++;
            break;
          case 3:
            Type[3] = Data.DiceType.Threes;
            combo[2]++;
            break;
          case 4:
            Type[4] = Data.DiceType.Fours;
            combo[3]++;
            break;
          case 5:
            Type[5] = Data.DiceType.Fives;
            combo[4]++;
            break;
          case 6:
            Type[6] = Data.DiceType.Sixes;
            combo[5]++;
            break;
          default:
            break;
        }
        if(i<DicePoint.length-1){
          int NextNumber = DicePoint[i+1];
          if(DicePoint[i]==NextNumber-1){
            connect++;
          }
        }
      }
      if(connect >2){
        Type[9] = Data.DiceType.SmallStraight;
        if(connect>3){
          Type[10] = Data.DiceType.LargeStraight;
        }
      }
      for(int comboCount : combo){
        switch (comboCount) {
          case 3:
            Type[7] = Data.DiceType.ThreeOfAKind;
            for(int fullHouseCombo : combo){
              if(fullHouseCombo == 2) {
                Type[12] = Data.DiceType.FullHouse;
              }
            }
            break;
          case 4:
            Type[7] = Data.DiceType.ThreeOfAKind;
            Type[8] = Data.DiceType.FourOfAKind;
            break;
          case 5:
            Type[7] = Data.DiceType.ThreeOfAKind;
            Type[8] = Data.DiceType.FourOfAKind;
            Type[12] = Data.DiceType.FullHouse;
            Type[13] = Data.DiceType.Yahtzee;
            if(canGetYahtzeeBonus){
              if(selectedType[DicePoint[0]]!=null){
                Type[9] = Data.DiceType.SmallStraight;
                Type[10] = Data.DiceType.LargeStraight;
              }
            }
            break;
          default:
            break;
        }
      }
      return Type;
    } catch(Exception e) { 
      return Type;
    }
  }

  private int computeScore(int index){
    int score = 0;
    if(judge()[index]==null){
      return score;
    }else{
      int[] DicePoint = new int[5];
      for(int i = 0 ;i<DiceArray.DiceArray.length;i++){
        DicePoint[i] = DiceArray.DiceArray[i].DICE_NUMBER;
      }
      switch (index) {
        case 1:
          for(int Point : DicePoint){
            if(Point==1){
              score += Point;
            }
          }
          return score;    
        case 2:
          for(int Point : DicePoint){
            if(Point==2){
              score += Point;
            }
          }
          return score;    
        case 3:
          for(int Point : DicePoint){
            if(Point==3){
              score += Point;
            }
          }
          return score;    
        case 4:
          for(int Point : DicePoint){
            if(Point==4){
              score += Point;
            }
          }
          return score;    
        case 5:
          for(int Point : DicePoint){
            if(Point==5){
              score += Point;
            }
          }
          return score;    
        case 6:
          for(int Point : DicePoint){
            if(Point==6){
              score += Point;
            }
          }
          return score;    
        case 7:
          if(judge()[index]==null){
            return score;
          }
          for(int Point : DicePoint){
            score += Point;
          }
          return score;
        case 8:
          for(int Point : DicePoint){
            score += Point;
          }
          return score; 
        case 9:
          score = 30;
          return score;
        case 10:
          score = 40;
          return score; 
        case 11:
          for(int Point : DicePoint){
            score += Point;
          }
          return score;
        case 12:
          score = 25;
          return score; 
        case 13:
          score = 50;
          return score; 
        default:
          return score;
      }
    }
  }

  

  private void setAction (JButton button,int index){
    button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        button.setFocusable(false);
        DiceGameApplication.App.requestFocus();

        typePoint[index] = computeScore(index);

        System.out.println(finalScore());
        if(topPoint()>=63){
          bounsPoint = 35;
        }

        if(judge()[13]==Data.DiceType.Yahtzee && canGetYahtzeeBonus){
          yahtzeeBonus+=100;
        }

        if(index == 13 && computeScore(index)!=0){
          canGetYahtzeeBonus = true;
        }

        selectedType[index] = Data.DiceType.values()[index];

        DiceArray.clearAllSelected();
        setButton();

        DiceGameApplication.App.repaint();
        DiceGameApplication.App.nextPlayer(index);;
      }
    });
  }

  public int topPoint(){
    int point = 0;
    for(int i = 0 ; i < 7 ; i++){
      point += typePoint[i];
    }
    return point;
  }
  public int bottonPoint(){
    int point = 0;
    for(int i = 7 ; i < 13 ; i++){
      point += typePoint[i];
    }
    return point;
  }

  public int finalScore(){
    return topPoint() + bottonPoint() + bounsPoint + yahtzeeBonus;
  }
}