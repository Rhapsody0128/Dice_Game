package game.dice_game;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;



public class DiceArray extends JFrame {

  private static final long serialVersionUID = 1L;


  public static Dice[] DiceArray = new Dice[6];
  
  public static Dice[] SelectedDice = new Dice[6];

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
}
