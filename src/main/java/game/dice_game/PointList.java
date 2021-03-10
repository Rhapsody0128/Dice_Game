package game.dice_game;

import java.awt.Font;
import java.util.Arrays;

// import java.awt.BorderLayout;
import javax.swing.*;

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
  
  public PointList(){
    
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    createButton();
    
  }
  
  public void createButton(){
    this.removeAll();
    for (int i =1; i<DiceType.values().length ;i++) {
      String key = "";
      switch (DiceType.values()[i]) {
        case Aces:
          key = "點數一";
          break;
        case Twos:
          key = "點數二";
          break;
        case Threes:
          key = "點數三";
          break;
        case Fours:
          key = "點數四";
          break;
        case Fives:
          key = "點數五";
          break;
        case Sixes:
          key = "點數六";
          break;
        case ThreeOfAKind: 
          key = "三條";
          break;
        case FourOfAKind:
          key = "四條";
          break;
        case SmallStraight:
          key = "小順";
          break;
        case LargeStraight:
          key = "大順";
          break;
        case Chance:
          key = "機會";
          break;
        case FullHouse:
          key = "葫蘆";
          break;
        case Yahtzee:
          key = "壓死";
          break;
        default:
          break;

        }
        JLabel label = new JLabel("這次結果將獲得"+ computeScore(i) +"點");
        label.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        JButton button = new JButton(key);
        button.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        button.setEnabled(false);
        for(DiceType judgeType : judge()){
          if(judgeType!=null){
            if(judgeType == DiceType.values()[i]){
              button.setEnabled(true);
            }
          }
        }
        this.add(button);
        this.add(label);
      
    }
    revalidate();
  }


  private DiceType[] judge(){
    DiceType[] Type = new DiceType[14];

    try{
      int[] DicePoint = new int[5];
      for(int i = 0 ;i<DiceArray.DiceArray.length;i++){
        DicePoint[i] = DiceArray.DiceArray[i].DICE_NUMBER;
      }
      Arrays.sort(DicePoint);
      int[] combo = new int[6];
      int connect = 0;      
      for(int i = 0 ;i<DicePoint.length;i++){
        switch (DicePoint[i]) {
          case 1:
            Type[0] = DiceType.Aces;
            combo[0]++;
            break;
          case 2:
            Type[1] = DiceType.Twos;
            combo[1]++;
            break;
          case 3:
            Type[2] = DiceType.Threes;
            combo[2]++;
            break;
          case 4:
            Type[3] = DiceType.Fours;
            combo[3]++;
            break;
          case 5:
            Type[4] = DiceType.Fives;
            combo[4]++;
            break;
          case 6:
            Type[5] = DiceType.Sixes;
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
            Type[6] = DiceType.ThreeOfAKind;
            for(int fullHouseCombo : combo){
              if(fullHouseCombo == 2) {
                Type[8] = DiceType.FullHouse;
              }
            }
            break;
          case 4:
            Type[6] = DiceType.ThreeOfAKind;
            Type[7] = DiceType.FourOfAKind;
          case 5 :
            Type[6] = DiceType.ThreeOfAKind;
            Type[7] = DiceType.FourOfAKind;
            Type[11] = DiceType.Yahtzee;
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

  // private JLabel createJLabel(int Point){

  //   return new JLabel(Point)
  // }
}