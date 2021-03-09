package game.dice_game;

import javax.swing.*;
import javax.swing.JPanel;
// import java.awt.BorderLayout;

public class PointList extends JPanel{

  private static final long serialVersionUID = 1L;

  public JPanel PointList = new JPanel();
  
  public PointList(){
    
    addButton();
  }
  private void addButton(){
    JButton Ones = new JButton("點數一");
    PointList.add(Ones);
    
  }
}
