package game.dice_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;



public class DiceArray {

  public static Dice[] DiceArray = new Dice[5];
  
  public static Dice[] SelectedDice = new Dice[5];

  private static Boolean rolling = false;

  private static Timer Timer = new Timer();

  public DiceArray(){
    for(int i = 0 ; i<5 ; i++){
      DiceArray[i] = new Dice(i);
    }
  }
  
  public static void drawDiceBox(Graphics g){
    g.setColor(new Color(0, 0, 0));
    for(int i = 0 ; i<5 ; i++){
      g.drawRect(Setting.SCREEN_WIDTH*3/8+(Dice.DICE_SIZE+Dice.DICE_DOT_SIZE)*i,Dice.DICE_SIZE,Dice.DICE_SIZE,Dice.DICE_SIZE);
    }
    g.drawRect(Setting.SCREEN_WIDTH/2-Dice.DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH/2+Dice.DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH/2-Dice.DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH/2,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH/2+Dice.DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
  };

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
    rolling = !rolling;
    
    if(rolling){
      Timer = new Timer();
      Timer.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run(){
          for (Dice dice : DiceArray) {
            if(dice.selected == false){
              dice.rollDice();
              DiceGameApplication.App.repaint();
            }
          }
        }
      },0,50);
    } else{
      Timer.cancel();
    }
  }
}
