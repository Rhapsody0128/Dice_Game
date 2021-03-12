package game.dice_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.awt.BorderLayout;

@SpringBootApplication
public class DiceGameApplication extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;

	public static JPanel checkPlanel = new JPanel();

	public static DiceArray diceArray = new DiceArray();
	
	public static PointList PointList = new PointList();
	
	public static JTextField[] TextField = new JTextField[4];
	
	public static String[] PlayersName = new String[4];

	static DiceGameApplication App = new DiceGameApplication();

	PointList PointListz = new PointList();
	ScoreList ScoreListz = new ScoreList();

	public DiceGameApplication() {
		setTitle("骰子遊戲");
		setSize(Setting.SCREEN_WIDTH, Setting.SCREEN_HEIGHT);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLocationRelativeTo(null);
		// setUndecorated(true);
		
		setPlayerList();
		setCheckPanel(); 
		checkPlanel.add(new JButton("asd"));
		add(checkPlanel,BorderLayout.LINE_START);

		add(PointListz,BorderLayout.LINE_START);
		add(ScoreListz,BorderLayout.LINE_END);
		
		addKeyListener(this);

	}
		public void paint(Graphics g) { 
			super.paint(g);
			DiceArray.drawDiceBox(g);
			for(int i = 0 ; i<DiceArray.DiceArray.length ; i++){
				DiceArray.DiceArray[i].drawDice(g);
			}
			PointListz.setButton();
		}

		public static void main(String[] args) {
			SpringApplication.run(DiceGameApplication.class, args);
			App.setFocusable(true);
			App.setVisible(true);
		}

		private void setCheckPanel(){
			JComboBox<Number> jComboBox = new JComboBox<Number>();
        jComboBox.addItem(1);
        jComboBox.addItem(2);
        jComboBox.addItem(3);
        jComboBox.addItem(4);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playersCount = (int) jComboBox.getSelectedItem();
                switch (playersCount) {
                    case 1:
                        setNameTextField(1);
                        break;
                    case 2:
											setNameTextField(2);
                        break;
                    case 3:
											setNameTextField(3);
                        break;
                    case 4:
											setNameTextField(4);
                        break;
                }
            }
        });

        checkPlanel.add(jComboBox);
		}

		private void setNameTextField(int playerCount){
			JButton jButton = new JButton("確認");
			for(int i = 0 ;i<playerCount;i++){
				TextField[i].setVisible(true);
			}
			jButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0 ;i<playerCount;i++){
						PlayersName[i] = TextField[i].getText();
					}
				}
			});
			checkPlanel.add(jButton);
		}


		public void setPlayerList(){
			for(int i = 0 ;i<4;i++){
				TextField[i] = new JTextField();
				TextField[i].setVisible(false);
				checkPlanel.add(TextField[i]);
			}
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
