package game.dice_game;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

public class Dice extends JFrame {

  private static final long serialVersionUID = 1L;

  Setting setting = new Setting() ;

  final int DICE_SIZE = setting.SCREEN_HEIGHT / 8;
  final int DICE_DOT_SIZE = DICE_SIZE / 5;
  static int DICE_NUMBER = 0;
  static int DICE_POSITION_X = 0;
  static int DICE_POSITION_Y = 0;

  public void setPoint(int NUMBER){
    DICE_NUMBER = NUMBER;
  }
  public void setPosition(int X,int Y){
    DICE_POSITION_X = X;
    DICE_POSITION_Y = Y;
  }

  public void drawDice(Graphics g,int index) {
    switch (index) {
      case 1:
        setPosition(setting.SCREEN_WIDTH/2,setting.SCREEN_HEIGHT/2);
        g.setColor(Color.black);
        g.fillRect(DICE_POSITION_X, DICE_POSITION_X, DICE_SIZE, DICE_SIZE);
        break;
    
      default:
        break;
    }
    switch (DICE_NUMBER) {
      case 1:
        g.setColor(Color.white);
        g.fillOval(DICE_POSITION_X+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        
        break;
    
      default:
        break;
    }	
	}

}
