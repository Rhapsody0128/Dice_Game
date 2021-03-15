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

  public static int chance = 3;

  public DiceArray(){
    for(int i = 0 ; i<5 ; i++){
      DiceArray[i] = new Dice(i);
    }
  }
  
  public static void drawDiceBox(Graphics g){
    g.setColor(new Color(0, 0, 0));
    for(int i = 0 ; i<5 ; i++){
      g.drawRect(Setting.SCREEN_WIDTH*4/13+(Dice.DICE_SIZE+Dice.DICE_DOT_SIZE)*i,Dice.DICE_SIZE,Dice.DICE_SIZE,Dice.DICE_SIZE);
    }
    g.drawRect(Setting.SCREEN_WIDTH*4/9-Dice.DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH*4/9+Dice.DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH*4/9-Dice.DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH*4/9,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
    g.drawRect(Setting.SCREEN_WIDTH*4/9+Dice.DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+Dice.DICE_SIZE*5/4,Dice.DICE_SIZE,Dice.DICE_SIZE);
  };

  public static void selectedDice(int index){
    if(rolling==false){
      SelectedDice[index] = DiceArray[index] ;
      DiceArray[index].selected = true;
      DiceArray[index].putSelectDice();
    }
  }

  public static void cancelSelectedDice(int index){
    if(rolling==false){
      SelectedDice[index] = null;
      DiceArray[index].selected = false;
      DiceArray[index].putSelectDice();
    }
  }

  public static void clearAllSelected(){
    for (int i=0;i<5;i++){
      SelectedDice[i] = null;
      DiceArray[i].selected = false;
      DiceArray[i].putSelectDice();
    }
    chanceReset();
    rollRemainDice();
  }
  public static void chanceReset(){
    chance = 3;
  }
  public static void rollRemainDice(){
    if(chance>0){
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
        chance--;
        Timer.cancel();
      }
    }
  }
}
