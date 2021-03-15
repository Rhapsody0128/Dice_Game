package game.dice_game;

import java.awt.Font;
// import java.util.Arrays;

import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ScoreList extends JPanel{

  private static final long serialVersionUID = 1L;

  ButtonGroup ButtonGroup = new ButtonGroup();

  JPanel radioPanel = new JPanel();

  JPanel textPanel = new JPanel();

  JLabel content = new JLabel();


  String[] players = new String[4];
  
  public ScoreList(){
    content.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/60));
    createRadioButton("說明");
    createRadioButton("vv");
    createRadioButton("vvzz");
    createRadioButton("vvaa");
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(radioPanel);
    add(textPanel);
    textPanel.setVisible(false);
  }
  public void createRadioButton(String name){
    JRadioButton JRadioButton = new JRadioButton(name);
    JRadioButton.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/50));
    JRadioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textPanel.setVisible(true);
      }
  });
    ButtonGroup.add(JRadioButton);
    radioPanel.add(JRadioButton,BorderLayout.PAGE_START);
  }
  // private void getManual(){
  //   content.setText("<html><br><h1 style='text-align: center;color: red'>規則說明</h1><p>遊戲使用投擲五個骰子判斷得分，<br>第一次投擲後可以兩次選擇重新投擲部分的骰子。<br>最後由骰子數字的組合來判斷得分。</p><h1 style='text-align: center;color: red'>操作說明</h1><p>SPACE : 擲骰子/停下骰子，會消耗一次重擲機會(Chance)。<br>W : 選擇黑色(編號1)骰子到待定保留區，將不會重擲。<br>E : 選擇青色(編號2)骰子到待定保留區，將不會重擲。<br>A : 選擇藍色(編號3)骰子到待定保留區，將不會重擲。<br>S : 選擇綠色(編號4)骰子到待定保留區，將不會重擲。<br>D : 選擇黃色(編號5)骰子到待定保留區，將不會重擲。<br>1 : 選擇黑色(編號1)骰子到重擲區，按下擲骰將被重擲。<br>2 : 選擇青色(編號2)骰子到重擲區，按下擲骰將被重擲。<br>3 : 選擇藍色(編號3)骰子到重擲區，按下擲骰將被重擲。<br>4 : 選擇綠色(編號4)骰子到重擲區，按下擲骰將被重擲。<br>5 : 選擇黃色(編號5)骰子到重擲區，按下擲骰將被重擲。<br>最後按下左邊按鈕來選擇這一輪要用來計分的類型。<br></p><h1 style='text-align: center;color: red'>計分方式</h1><p> 點數一 : 分數得點數為一的骰子的點數和。<br>點數二 : 分數得點數為二的骰子的點數和。<br>點數三 : 分數得點數為三的骰子的點數和。<br>點數四 : 分數得點數為四的骰子的點數和。<br>點數五 : 分數得點數為五的骰子的點數和。<br>點數六 : 分數得點數為六的骰子的點數和。<br>--------上半段分數和加總大於63分，則結算時額外獲得35分。<br>三條 : 三個相同點數的組合，全點數和即為分數。<br>四條 : 四個相同點數的組合，全點數和即為分數。<br>小順 : 點數排列四連貫者者，分數得30分。<br>大順 : 點數排列全連貫者者，分數得30分。<br>機會 : 將全點數和作為所得分數。<br>葫蘆 : 三個點數相同和另外兩個點數相同的組合，分數得25分。<br>壓死 : 為五個點數相同骰子的組合，分數得50分。<br></p></html>");
  // }
  // private void getScroeList(String name){
  //   // for(String player : pla)
  // }
}
