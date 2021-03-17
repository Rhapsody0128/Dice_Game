package Yahtzee;

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

  static JPanel topPanel = new JPanel();

  static JRadioButton[] radioButton = new JRadioButton[DiceGameApplication.App.playerCount+1];

  JLabel content = new JLabel();

  
  public ScoreList(){
    
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    initTopPanel();
    initRadioButton();
    initTextPanel();
    setTopPanel(DiceGameApplication.App.getName(0),DiceArray.chance,PointList.round);
  }
  private void initRadioButton(){
    createRadioButton("說明",-1);
    for(int i = 0 ;i < DiceGameApplication.App.playerCount;i++){
      createRadioButton(DiceGameApplication.App.getName(i),i);
      add(radioPanel);
    }
  }
  private void initTopPanel(){
    add(topPanel);
  }

  public static void setTopPanel(String player, int chance,int round) {
    try{
      topPanel.removeAll();
      JLabel content = new JLabel();
      content.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/50));
      if(DiceArray.rolling){
        content.setText("<html><h1 style='text-align: center;color: red'>"+player+"的回合</h1><p style='text-align: center'>請按下 SPACE 觀看擲骰結果<br>目前回數 : "+ round +"回 </p></html>");

      }else{
        if(DiceGameApplication.roundStart){
          content.setText("<html><h1 style='text-align: center;color: red'>"+player+"的回合</h1><p style='text-align: center'>擲骰機會 : "+chance + "次<br>目前回數 : "+ round +"回 </p></html>");
        }else{
          content.setText("<html><h1 style='text-align: center;color: red'>"+player+"的回合</h1><p style='text-align: center'>請按下 SPACE 開始擲骰<br>目前回數 : "+ round +"回 </p></html>");
        }
      }
      topPanel.add(content);
      topPanel.validate();
    }catch(Exception e){
    }
  }

  private void initTextPanel(){
    
    textPanel.add(content);
    add(textPanel);
    setContent(-1);
  }

  private void createRadioButton(String name,int index){
    JRadioButton JRadioButton = new JRadioButton(name);
    radioButton[index+1] = JRadioButton;
    JRadioButton.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/50));
    JRadioButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setContent(index);
        DiceGameApplication.App.requestFocus();
      }
  });
    ButtonGroup.add(JRadioButton);
    radioPanel.add(JRadioButton,BorderLayout.PAGE_START);
  }

  private void setContent(int query){

    DiceGameApplication.App.repaint();
    if(query == -1){
      content.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/80));
      content.setText("<html><h1 style='text-align: center;color: red'>規則說明</h1><p style='text-align: center'>遊戲使用投擲五個骰子判斷得分，<br>第一次投擲後可以兩次選擇重新投擲部分的骰子。<br>最後由骰子數字的組合來判斷得分。</p><h1 style='text-align: center;color: red'>操作說明</h1><p>SPACE : 擲骰子/停下骰子，會消耗一次重擲機會(Chance)<br>W : 選擇黑色(編號1)骰子到待定保留區，將不會重擲<br>E : 選擇青色(編號2)骰子到待定保留區，將不會重擲<br>A : 選擇藍色(編號3)骰子到待定保留區，將不會重擲<br>S : 選擇綠色(編號4)骰子到待定保留區，將不會重擲<br>D : 選擇黃色(編號5)骰子到待定保留區，將不會重擲<br>1 : 選擇黑色(編號1)骰子重擲區，按下擲骰將被重擲<br>2 : 選擇青色(編號2)骰子重擲區，按下擲骰將被重擲<br>3 : 選擇藍色(編號3)骰子重擲區，按下擲骰將被重擲<br>4 : 選擇綠色(編號4)骰子重擲區，按下擲骰將被重擲<br>5 : 選擇黃色(編號5)骰子重擲區，按下擲骰將被重擲<br>按下左邊按鈕來選擇這一輪要用來計分的類型<br>*如果按鍵失效嘗試切換輸入法<br></p><h1 style='text-align: center;color: red'>計分方式</h1><p> 點數一 : 分數為點數為一的骰子的點數和，最多5分<br>點數二 : 分數為點數為二的骰子的點數和，最多10分<br>點數三 : 分數為點數為三的骰子的點數和，最多15分<br>點數四 : 分數為點數為四的骰子的點數和，最多20分<br>點數五 : 分數為點數為五的骰子的點數和，最多25分<br>點數六 : 分數為點數為六的骰子的點數和，最多30分<br>點數紅利 : 若上半段前6格分數達63分以上，<br>可獲得紅利分數35分。<br>-------------------------------------------------------<br>三條 : 三個相同點數的組合，全點數和即為分數。<br>四條 : 四個相同點數的組合，全點數和即為分數。<br>小順 : 點數排列四連貫者者，分數得30分。<br>大順 : 點數排列全連貫者者，分數得40分。<br>機會 : 任意組合，將全點數和作為所得分數。<br>葫蘆 : 三、兩個點數各一組相同的組合，分數得25分。<br>壓死 : 五個點數相同骰子的組合，分數得50分。<br>壓死紅利 : 當壓死沒有被填上0，若第二次擲出壓死，<br>會獲得額外獎勵100點，若壓死點數對應的上半段也<br>已經被填上，則可填入任意下半段並獲得判定分數。</p></html>");
    }else{
      String contentText = "";
      for(int i=1 ;i<14;i++){
        contentText += getTypePoint(query, i);
        if(i==6){
          contentText += "上半部獲得紅利分數 : "+DiceGameApplication.App.pointList[query].topPoint()+"/63";
          contentText += "<br>-------------------------------------<br>";
        }
      }
      contentText+="<br>上半部得分 : " + DiceGameApplication.App.pointList[query].topPoint()+"分<br>";
      contentText+="下半部得分 : " + DiceGameApplication.App.pointList[query].bottonPoint()+"分<br>";
      contentText+="紅利得分 : " + DiceGameApplication.App.pointList[query].bounsPoint+"分<br>";
      contentText+="總分 : " + DiceGameApplication.App.pointList[query].finalScore()+"分<br>";
      contentText+="<br><br><br><br><br>";
      content.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/50));
      content.setText("<html><h1 style='text-align: center;color: red'>"+DiceGameApplication.App.getName(query)+"的計分</h1>"+"<p style='text-align: center'>"+contentText+"</p></html>");
    }
  }

  private String getTypePoint(int query,int index){
    try{
      int[] pointList = DiceGameApplication.App.pointList[query].typePoint;
      Data.DiceType[] selectedType = DiceGameApplication.App.pointList[query].selectedType;
        if(selectedType[index] != null){
          return Data.DiceTypeToChinese(Data.DiceType.values()[index]) + " : " + pointList[index] + " 分<br>";
        }else{
          return Data.DiceTypeToChinese(Data.DiceType.values()[index]) + " : 還沒填入<br>";
        }
    }catch(Exception e) { 
      return Data.DiceTypeToChinese(Data.DiceType.values()[index]) + " 還沒填入";
    }
  }

  public static void select(int query){
    radioButton[query].doClick();
    
  }
}
