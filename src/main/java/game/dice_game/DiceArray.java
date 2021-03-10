package game.dice_game;

import java.awt.Color;
import java.awt.Graphics;



public class DiceArray {

  public static Dice[] DiceArray = new Dice[5];
  
  public static Dice[] SelectedDice = new Dice[5];

  public DiceArray(){
    for(int i = 0 ; i<5 ; i++){
      DiceArray[i] = new Dice(i);
    }
  }
  
  public static void drawSelectedDice(Graphics g){
    g.setColor(new Color(0, 0, 0));
    for(int i = 0 ; i<5 ; i++){
      g.drawRect(Setting.SCREEN_WIDTH*2/5+(Dice.DICE_SIZE+Dice.DICE_DOT_SIZE)*i,Dice.DICE_SIZE,Dice.DICE_SIZE,Dice.DICE_SIZE);
    }
  }

  public static void selectedDice(int index){
    SelectedDice[index] = DiceArray[index] ;
    DiceArray[index].selected = true;
    DiceArray[index].selectDice();
  }

  public static void cancelSelectedDice(int index){
    SelectedDice[index] = null;
    DiceArray[index].selected = false;
    DiceArray[index].selectDice();
  }

  public static void rollRemainDice(){
    for (Dice dice : DiceArray) {
      if(dice.selected == false){
        dice.rollDice();
      }
    }
  }
}
