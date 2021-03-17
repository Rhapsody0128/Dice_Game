package Yahtzee;
// import org.springframework.stereotype.Component;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class DiceGameApplication extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;

	public JPanel checkPanel = new JPanel(new GridLayout(11, 1));

	public DiceArray diceArray = new DiceArray();

	public JPanel PointPanel = new JPanel();
	
	public PointList[] pointList;

	Boolean Start = false;

	public static Boolean roundStart = false;

	public int playerCount;

	public int nowPlaying = 0;

	static DiceGameApplication App = new DiceGameApplication();

	public String[] name ;
	// PointList PointListz = new PointList();
	// ScoreList ScoreListz = new ScoreList();

	public DiceGameApplication() {
		setTitle("骰子遊戲");
		setSize(Setting.SCREEN_WIDTH, Setting.SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		// setUndecorated(true);
		
		setInit();

		add(checkPanel);

		addKeyListener(this);

	}
		public void paint(Graphics g) { 
			super.paint(g);
			if(Start){
				DiceArray.drawDiceBox(g);
				for(int i = 0 ; i<DiceArray.DiceArray.length ; i++){
					DiceArray.DiceArray[i].drawDice(g);
				}
			}

		}

		public static void main(String[] args) {
			App.setFocusable(true);
			App.setVisible(true);
		}

		private void setCheckPanel(){
			
			JComboBox<Number> jComboBox = new JComboBox<Number>();
        jComboBox.addItem(1);
        jComboBox.addItem(2);
        jComboBox.addItem(3);
        jComboBox.addItem(4);
        jComboBox.addItem(5);
        jComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choicPlayCount = (int) jComboBox.getSelectedItem();
						checkPanel.repaint();
						for(int i = 2 ; i< playerCount+3 ;i++){
							checkPanel.remove(2);
						}
            setNameTextField(choicPlayCount);
					}
				});
				jComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/40));
				JLabel TopLabel = new JLabel("請選擇遊玩人數");
				TopLabel.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/40));
				checkPanel.add(TopLabel);
				checkPanel.add(jComboBox);
				setNameTextField(1);
			}
		private void setNameTextField(int choicPlayCount){

			playerCount = choicPlayCount;
			name = new String[playerCount];
			JTextField[] textFieldArray = new JTextField[choicPlayCount];

			for(int j = 0 ;j<choicPlayCount;j++){
				JTextField TextField  = new JTextField("玩家" + j,20);
				TextField.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/40));
				textFieldArray[j] = TextField;
				checkPanel.add(TextField);
			}

			JButton jButton = new JButton("確認");
			jButton.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/40));
		
			jButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0 ;i<choicPlayCount;i++){
						name[i] = textFieldArray[i].getText();
					}
					checkPanel.removeAll();
					checkPanel.validate();
					checkPanel.repaint();
					App.repaint();
					Start = true;
					// setScoreList();
					setGame();
				}
			});
			checkPanel.add(jButton);

			checkPanel.validate();

		}

		public void setInit(){
			setCheckPanel();
		}

		private void setGame(){
			setScoreList();
			setPointList();
			App.validate();
		}
		
		private void setScoreList(){
			App.add(new ScoreList(),BorderLayout.LINE_END);
		};
		
		private void setPointList(){
			pointList = new PointList[playerCount];
			for(int i = 0 ; i < playerCount ;i++){
				pointList[i] = new PointList(i);
			}
			PointPanel.add(pointList[0]);
			App.add(PointPanel,BorderLayout.LINE_START);
		}
		
		public void nextPlayer(int now){
			
			roundStart = false;
			pointList[nowPlaying].setButton();
			PointPanel.removeAll();
			nowPlaying+=1;
			if(nowPlaying>=playerCount){
				nowPlaying = 0;
				PointList.round++;
				if(PointList.round==13){
					gameOver();
				}
			}
			PointPanel.add(pointList[nowPlaying]);
			ScoreList.select(nowPlaying+1);
			ScoreList.setTopPanel(getName(nowPlaying),DiceArray.chance,PointList.round);
			PointPanel.validate();
		}

		public String getName(int index){
			return name[index];
		}

		private void gameOver(){
			JFrame gameOverFrame = new JFrame();
			JPanel gameOverPanel = new JPanel(new GridLayout(playerCount+2, 1));
			gameOverFrame.setTitle("總分統計");
			gameOverFrame.setSize(Setting.SCREEN_WIDTH, Setting.SCREEN_HEIGHT);
			gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			int bestPlayerIndex = -1;
			int bestScore = 0;
			for(int i = 0 ;i<playerCount;i++){
				if(pointList[i].finalScore()>bestScore){
					bestScore = pointList[i].finalScore();
					bestPlayerIndex = i;
				}
			}
			for(int i = 0 ;i<playerCount;i++){
				JLabel JLabel = new JLabel();
				JLabel.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/20));
				JLabel.setText(getName(i) +  ": 獲得的總分為" + pointList[i].finalScore() + "分");
				gameOverPanel.add(JLabel);
			}
			JLabel jLabel = new JLabel("恭喜"+getName(bestPlayerIndex)+"獲得最優異成績"+bestScore+"分!!");
			jLabel.setFont(new Font("微軟正黑體", Font.PLAIN, Setting.SCREEN_HEIGHT/10));
			gameOverPanel.add(jLabel);

			gameOverFrame.add(gameOverPanel);
			gameOverFrame.setVisible(true);
			App.setVisible(false);
		}

	@Override
	public void keyPressed(KeyEvent e) {
		this.requestFocus();
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_W:
				DiceArray.selectedDice(0);
				break;
			case KeyEvent.VK_E:
				DiceArray.selectedDice(1);
				break;
			case KeyEvent.VK_A:
				DiceArray.selectedDice(2);
				break;
			case KeyEvent.VK_S:
				DiceArray.selectedDice(3);
				break;
			case KeyEvent.VK_D:
				DiceArray.selectedDice(4);
				break;
			case KeyEvent.VK_1:
				DiceArray.cancelSelectedDice(0);
				break;
			case KeyEvent.VK_2:
				DiceArray.cancelSelectedDice(1);
				break;
			case KeyEvent.VK_3:
				DiceArray.cancelSelectedDice(2);
				break;
			case KeyEvent.VK_4:
				DiceArray.cancelSelectedDice(3);
				break;
			case KeyEvent.VK_5:
				DiceArray.cancelSelectedDice(4);
				break;
			case KeyEvent.VK_SPACE:
				DiceArray.rollRemainDice();
				roundStart = true;
				pointList[nowPlaying].setButton();
				break;
			default:
				break;
		}
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			this.repaint();
	}
}
