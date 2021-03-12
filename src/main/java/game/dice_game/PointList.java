package game.dice_game;

import java.awt.Font;
import java.util.Arrays;

// import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum DiceType{
  None,
  Aces,
  Twos,
  Threes,
  Fours,
  Fives,
  Sixes,
  ThreeOfAKind,
  FourOfAKind,
  SmallStraight,
  LargeStraight,
  Chance,
  FullHouse,
  Yahtzee
}

public class PointList extends JPanel{

  private static final long serialVersionUID = 1L;

  JButton[] AllButton = new JButton[DiceType.values().length];
  
  DiceType[] selectedType = new DiceType[DiceType.values().length];

  int round = 0;

  public int topPoint = 0;
  public int bottonPoint = 0;
  
  public PointList(){
    
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    createButton();
    setButton();
  }
  
  public void createButton(){
    for (int i =0; i<DiceType.values().length ;i++) {

        JButton button = new JButton();
        button.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/60));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setEnabled(false);
        setAction(button,i);

        this.add(button);
        AllButton[i] = button;
    }
    setButton();
  }
  private String DiceTypeToChinese(DiceType Type){
      switch (Type) {
        case None:
          return "放棄";
        case Aces:
          return "點數一";
        case Twos:
          return "點數二";
        case Threes:
          return "點數三";
        case Fours:
          return "點數四";
        case Fives:
          return "點數五";
        case Sixes:
          return "點數六";
        case ThreeOfAKind: 
          return "三條" ;
        case FourOfAKind:
          return "四條" ;
        case SmallStraight:
          return "小順" ;
        case LargeStraight:
          return "大順" ;
        case Chance:
          return "機會" ;
        case FullHouse:
          return "葫蘆" ;
        case Yahtzee:
          return "壓死" ;
        default:
          return "";
        }
  }

  public void setButton(){
      for(int i = 0; i<DiceType.values().length ;i++){
        if(i==0){
          AllButton[i].setEnabled(true);
          AllButton[i].setText("<html><p style='text-align: center'>"+DiceTypeToChinese(DiceType.values()[i])+"<br>跳過本次回合</html>");
        }else{
          if(judge()[i]!=null){
            AllButton[i].setEnabled(true);
            AllButton[i].setText("<html><p style='text-align: center'>"+DiceTypeToChinese(DiceType.values()[i])+"<br>將可獲得"+computeScore(i)+"點</p></html>");
          }else{
            AllButton[i].setEnabled(false);
            AllButton[i].setText("<html><p style='text-align: center'>"+DiceTypeToChinese(DiceType.values()[i])+"<br>"+orginalText(i)+"</p></html>");
          }
          if(selectedType[i]!=null){ 
            AllButton[i].setEnabled(false);
            AllButton[i].setText("<html><p style='text-align: center'>"+DiceTypeToChinese(DiceType.values()[i])+"<br>已使用</p></html>");
          }
        }
      }   
  }

  private DiceType[] judge(){
    DiceType[] Type = new DiceType[DiceType.values().length];

    try{
      int[] DicePoint = new int[5];
      for(int i = 0 ;i<DiceArray.DiceArray.length;i++){
        DicePoint[i] = DiceArray.DiceArray[i].DICE_NUMBER;
      }
      Arrays.sort(DicePoint);
      int[] combo = new int[6];
      int connect = 0;      

      Type[11] = DiceType.Chance;
      Type[0] = DiceType.None;
      for(int i = 0 ;i<DicePoint.length;i++){
        switch (DicePoint[i]) {
          case 1:
            Type[1] = DiceType.Aces;
            combo[0]++;
            break;
          case 2:
            Type[2] = DiceType.Twos;
            combo[1]++;
            break;
          case 3:
            Type[3] = DiceType.Threes;
            combo[2]++;
            break;
          case 4:
            Type[4] = DiceType.Fours;
            combo[3]++;
            break;
          case 5:
            Type[5] = DiceType.Fives;
            combo[4]++;
            break;
          case 6:
            Type[6] = DiceType.Sixes;
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
        Type[9] = DiceType.SmallStraight;
        if(connect>3){
          Type[10] = DiceType.LargeStraight;
        }
      }
      for(int comboCount : combo){
        switch (comboCount) {
          case 3:
            Type[7] = DiceType.ThreeOfAKind;
            for(int fullHouseCombo : combo){
              if(fullHouseCombo == 2) {
                Type[12] = DiceType.FullHouse;
              }
            }
            break;
          case 4:
            Type[7] = DiceType.ThreeOfAKind;
            Type[8] = DiceType.FourOfAKind;
            break;
          case 5:
            Type[7] = DiceType.ThreeOfAKind;
            Type[8] = DiceType.FourOfAKind;
            Type[12] = DiceType.FullHouse;
            Type[13] = DiceType.Yahtzee;
            break;
          default:
            break;
        }
      }
      return Type;
    } catch(Exception e) { 
      System.out.println(e);
      return Type;
    }
  }

  private int computeScore(int index){
    int[] DicePoint = new int[5];
    for(int i = 0 ;i<DiceArray.DiceArray.length;i++){
      DicePoint[i] = DiceArray.DiceArray[i].DICE_NUMBER;
    }
    Arrays.sort(DicePoint);
    int score = 0;
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
        for(int Point : DicePoint){
          score += Point;
        }
      case 8:
        for(int Point : DicePoint){
          score += Point;
        }
      case 9:
        score = 30;
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

  private String orginalText(int index){
    switch (index) {
      case 0:
        return "跳過本次回合";
      case 1:
        return "至少含有點數"+index;
      case 2:
        return "至少含有點數"+index;
      case 3:
        return "至少含有點數"+index;
      case 4:
        return "至少含有點數"+index;
      case 5:
        return "至少含有點數"+index;
      case 6:
        return "至少含有點數"+index;
      case 7:
        return "至少有三個同樣點數";
      case 8:
        return "至少有四個同樣點數";
      case 9:
        return "至少有四個點數連號";
      case 10:
        return "需五個點數連號";
      case 11:
        return "跳過本次回合";
      case 12:
        return "需二加三相同點數";
      case 13:
        return "需五個點數相同";
      default:
        return "無";
    }
  }

  private void setAction (JButton button,int index){
    button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        button.setFocusable(false);
        DiceGameApplication.App.requestFocus();
        selectedType[index] = DiceType.values()[index];
        DiceArray.clearAllSelected();
        setButton();
        DiceGameApplication.App.repaint();
        
        round++;
        if(round==13){
          gameOver();
        }
      }
    });
  }

  public void gameOver(){
    
  }
}