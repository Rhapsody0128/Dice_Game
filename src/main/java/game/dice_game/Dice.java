package game.dice_game;
// import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;

// import java.awt.Rectangle;
// // import java.awt.Dimension;
// import java.awt.Point;

// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dice extends JButton {

  private static final long serialVersionUID = 1L;

  final static int DICE_SIZE = Setting.SCREEN_HEIGHT / 10;
  final static int DICE_DOT_SIZE = DICE_SIZE / 6;
  
  public int DICE_INDEX = 0;
  public int DICE_NUMBER = 0;
  
  static int DICE_POSITION_X = 0;
  static int DICE_POSITION_Y = 0;

  public JButton DiceButton;


  public Dice(int index){
    DICE_INDEX = index;
    // setVisible(false);
    addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        rollDice();
      }
  });

  }

  // public void mousePressed(MouseEvent e) {}
  // public void mouseReleased(MouseEvent e) {}
  // public void mouseEntered(MouseEvent e) {}
  // public void mouseExited(MouseEvent e) {}

  // /** Called whenever the mouse clicks.
  //   * Could be replaced with setting the value of a JLabel, etc. */
  // public void mouseClicked(MouseEvent e) {
  //     Point p = e.getPoint();
  //     if(DiceShape.contains(p)){
  //       rollDice();
  //       // drawDice();
  //     }
  //     else System.out.println("Triangle Doesn't contain point");
  // }

  private void setPoint(int NUMBER){
    DICE_NUMBER = NUMBER;
  }
  private void setPosition(int X,int Y){
    DICE_POSITION_X = X;
    DICE_POSITION_Y = Y;
  }

  public void rollDice(){
    int random = (int)(Math.random()*6)+1;
    setPoint(random);
  }

  private int gAndJFineTuningX(){
    return DICE_POSITION_X - DICE_DOT_SIZE*5/11;
  }
  private int gAndJFineTuningY(){
    return DICE_POSITION_Y - DICE_DOT_SIZE*7/4;
  }

  public void drawDice(Graphics g) {
    switch (DICE_INDEX) {
      case 0:
        setPosition(Setting.SCREEN_WIDTH/2-DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2);
        setBounds(gAndJFineTuningX(), gAndJFineTuningY(), DICE_SIZE, DICE_SIZE);
        g.setColor(new Color(0, 0, 0));
        g.fillRect(DICE_POSITION_X, DICE_POSITION_Y, DICE_SIZE, DICE_SIZE);
        drawDot(g);
        break;
      case 1:
        setPosition(Setting.SCREEN_WIDTH/2+DICE_SIZE/8*5,Setting.SCREEN_HEIGHT/2);
        setBounds(gAndJFineTuningX(),  gAndJFineTuningY(), DICE_SIZE, DICE_SIZE);
        g.setColor(new Color(50, 200, 200));
        g.fillRect(DICE_POSITION_X, DICE_POSITION_Y, DICE_SIZE, DICE_SIZE);
        drawDot(g);
        break;
      case 2:
        setPosition(Setting.SCREEN_WIDTH/2-DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+DICE_SIZE*5/4);
        setBounds(gAndJFineTuningX(), gAndJFineTuningY(), DICE_SIZE, DICE_SIZE);
        g.setColor(new Color(50, 50,200));
        g.fillRect(DICE_POSITION_X, DICE_POSITION_Y, DICE_SIZE, DICE_SIZE);
        drawDot(g);
        break;
      case 3:
        setPosition(Setting.SCREEN_WIDTH/2,Setting.SCREEN_HEIGHT/2+DICE_SIZE*5/4);
        setBounds(gAndJFineTuningX(), gAndJFineTuningY(), DICE_SIZE, DICE_SIZE);
        g.setColor(new Color(50, 180,50));
        g.fillRect(DICE_POSITION_X, DICE_POSITION_Y, DICE_SIZE, DICE_SIZE);
        drawDot(g);
        break;
      case 4:
        setPosition(Setting.SCREEN_WIDTH/2+DICE_SIZE*5/4,Setting.SCREEN_HEIGHT/2+DICE_SIZE*5/4);
        setBounds(gAndJFineTuningX(), gAndJFineTuningY(), DICE_SIZE, DICE_SIZE);
        g.setColor(new Color(200, 200, 20));
        g.fillRect(DICE_POSITION_X, DICE_POSITION_Y, DICE_SIZE, DICE_SIZE);
        drawDot(g);
        break;
    }
	}
  public void drawDot(Graphics g) {
    switch (DICE_NUMBER) {
      case 1:
        g.setColor(Color.red);
        g.fillOval(DICE_POSITION_X+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE*4/3,DICE_DOT_SIZE*4/3);
        break;
      case 2:
        g.setColor(Color.white);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        break;
      case 3:
        g.setColor(Color.white);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        break;
      case 4:
        g.setColor(Color.red);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        break;
      case 5:
        g.setColor(Color.white);
        g.fillOval(DICE_POSITION_X+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_DOT_SIZE,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        break;
      case 6:
        g.setColor(Color.white);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*3/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE-DICE_DOT_SIZE*3/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_DOT_SIZE,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        g.fillOval(DICE_POSITION_X+DICE_SIZE-DICE_DOT_SIZE*2,DICE_POSITION_Y+DICE_SIZE/2-DICE_DOT_SIZE/2,DICE_DOT_SIZE,DICE_DOT_SIZE);
        break;
    
      default:
        break;
    }	

  }

}
