package game.dice_game;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.JPanel;
// import java.awt.BorderLayout;

enum DiceType{
  None,
  Ones,
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

  public JPanel PointList = new JPanel();
  
  public PointList(){
    
    showButton();
  }
  public void showButton(){
    JButton Ones = new JButton("點數一");
    Ones.setPreferredSize(new Dimension(100, 100));
    Ones.setSize(300, 300);
    PointList.add(Ones);
    Ones.setLocation(0,0);
    judge();
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
            Type[0] = DiceType.Ones;
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

  private int computeScore(int index,int[] DicePoint){
    int score = 0;
    switch (index) {
      case 1:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      case 2:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      case 3:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      case 4:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      case 5:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      case 6:
        for(int Point : DicePoint){
          if(Point==index){
            score += index;
          }
        }
        return score;    
      default:
        return score;
    }
  }

  // private JLabel createJLabel(int Point){

  //   return new JLabel(Point)
  // }
}