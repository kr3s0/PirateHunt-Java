import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class represent our graphical user interface(GUI).We build it on top of the all classes that are
 * also being used to play the game in console.The game itself is attribute of this class.Likewise playing 
 * in console,here the game instance g is really doing all the work, all we do in GUI class is render that 
 * board from Game instance g to our GUI.Furthermore, the rest of the work is creation and maintenance of 
 * GUI components. The GUI is build using javax.swing primarly, although we can find something from java.awt.
 * GUI class contains two nested public classes EventHandler and ClickHandler and their main purpose is to
 * reply to user-events.
 * 
 * One possible obstacle could be exception that is sometimes thrown while playing the game.Exception generally
 * references to problems with concurrent access to values of some variables. Although we do not really do here
 * any of parallel programming features, we should be aware that running any type of GUI applications in Java
 * is "something like" parallel programming since JVM creates new thread that takes care of created GUI parts.
 * Because of that, it is possible that two threads try to access same variable at the exact same time, causing
 * this particular ConcurrentException.Even though solving that kind of issue is not really a problem in Java
 * (using synchronized methods), we will not further complicate rather simple code.
 * @see https://docs.oracle.com/javase/7/docs/api/java/util/ConcurrentModificationException.html
 * 
 * In future version, the GUI class will be replaced by its duplicate built in javaFX.
 * @author Tarik Kreso - kr3s0
 * @since 2018-12-30
 */

public class GUI {
	public Game g;
	public JFrame window;
	private JButton[][] field;
	private JPanel panelwindow;
	private JPanel panelMenu;
	private JPanel panelMenuNorth;
	private JPanel panelMenuCenter;
	private JPanel panelMenuSouth;
	private JButton buttonNewGame;
	private JButton buttonEndGame;
	private JButton buttonChangeDifficulty;
	private JButton buttonSaveHighScore;
	private JLabel showLevel;
	private JLabel showDifficulty;
	private JLabel showScore;
	private JLabel showHighScore;
	private JPanel panelField;
	private JLabel imageLogo;
	private static final Color SEA = new Color(0,105,148);
	private static final Color SHIP = new Color(139,69,19);
	private static final Color PIRATES = Color.BLACK;
	private static final Color BARRIERS = new Color(255,228,181);
	private static final ImageIcon PIRATES2 = new ImageIcon("Resources/piracy.png");
	private static final ImageIcon SEA2 = new ImageIcon("Resources/sea3.png");
	private static final ImageIcon SHIP2 = new ImageIcon("Resources/ship3.png");
	private static final ImageIcon BARRIERS2 = new ImageIcon("Resources/island2.png");
	
	/**
	 * GUI class constructor that creates all components and refactor them to appear the way we want.
	 * We have two main panels that handles menu and game field.There is one more JPanel that serves as
	 * contentpane for our JFrame.As we represent game field by building matrix of JButton, that work is also
	 * done by this constructor.
	 * @param r represent the number of rows on our game field
	 * @param c represent the nubmer of columns on our game field
	 * @throws IOException if File stream that is used to upload our image to GUI fails.
	 */
	GUI(int r,int c){
		this.window = new JFrame("Pirate Hunt");
		this.panelwindow = new JPanel(new BorderLayout());
		this.panelMenu = new JPanel(new BorderLayout());
		this.panelMenuNorth = new JPanel();
		this.panelMenuCenter = new JPanel();
		this.panelMenuSouth = new JPanel();
		this.panelField = new JPanel(new GridLayout(r,c));
		this.window.setContentPane(this.panelwindow);
		this.panelMenu.setPreferredSize(new Dimension(200,700));
		this.panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.panelwindow.add(this.panelMenu,BorderLayout.LINE_START);
		this.panelwindow.add(this.panelField,BorderLayout.CENTER);
		this.showDifficulty = new JLabel();
		this.showLevel = new JLabel();
		this.showScore = new JLabel();
		this.showHighScore = new JLabel();
		this.showHighScore.setPreferredSize(new Dimension(100,40));
		this.showDifficulty.setPreferredSize(new Dimension(100,40));
		this.showLevel.setPreferredSize(new Dimension(100,40));
		this.showScore.setPreferredSize(new Dimension(100,40));
		this.showHighScore.setBorder(BorderFactory.createTitledBorder("High Score"));
		this.showDifficulty.setBorder(BorderFactory.createTitledBorder("Difficulty"));
		this.showLevel.setBorder(BorderFactory.createTitledBorder("Level"));
		this.showScore.setBorder(BorderFactory.createTitledBorder("Score"));
		this.panelMenuNorth.add(this.showScore);
		this.panelMenuNorth.add(this.showLevel);
		this.panelMenuNorth.add(this.showDifficulty);
		this.panelMenuNorth.setPreferredSize(new Dimension(200,150));
		this.buttonChangeDifficulty = new JButton("Change Difficulty");
		this.buttonEndGame = new JButton("End Game");
		this.buttonNewGame = new JButton("New Game");
		this.buttonSaveHighScore = new JButton("Save Score");
		this.buttonSaveHighScore.setPreferredSize(new Dimension(150,40));
		this.buttonChangeDifficulty.setPreferredSize(new Dimension(150,40));
		this.buttonEndGame.setPreferredSize(new Dimension(150,40));
		this.buttonNewGame.setPreferredSize(new Dimension(150,40));
		this.panelMenuCenter.add(this.buttonNewGame);
		this.panelMenuCenter.add(this.buttonChangeDifficulty);
		this.panelMenuCenter.add(this.buttonSaveHighScore);
		this.panelMenuCenter.add(this.buttonEndGame);
		this.panelMenuCenter.setPreferredSize(new Dimension(200,400));
		this.panelMenuCenter.setBackground(this.SHIP);
		this.panelMenuNorth.setBackground(this.BARRIERS);
		this.panelMenuSouth.add(this.showHighScore);
		this.panelMenuSouth.setBackground(this.BARRIERS);
		this.panelMenuSouth.setPreferredSize(new Dimension(200,150));
		BufferedImage logo;
		try {
			logo = ImageIO.read(new File("Resources/skulllogo2.jpg"));
			this.imageLogo = new JLabel(new ImageIcon(logo));
			this.imageLogo.setPreferredSize(new Dimension(200,200));
			this.panelMenuCenter.add(this.imageLogo);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		this.panelMenu.add(this.panelMenuNorth,BorderLayout.NORTH);
		this.panelMenu.add(this.panelMenuCenter,BorderLayout.CENTER);
		this.panelMenu.add(this.panelMenuSouth,BorderLayout.SOUTH);
		this.g = new Game();
		this.g.setGame(r,c);
		this.showDifficulty.setText(this.g.getDifficulty());
		this.showLevel.setText(String.valueOf(this.g.getLevel()));
		this.showScore.setText(String.valueOf(this.g.getScore()));
		this.showHighScore.setText(String.valueOf(Game.getHighScore()));
		this.field = new JButton[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				this.field[i][j]=new JButton();
				this.field[i][j].setEnabled(false);
				//this.field[i][j].setBackground(Color.BLACK);
				this.panelField.add(this.field[i][j]);
			}
		}
		this.buttonChangeDifficulty.addMouseListener(new ClickHandler());
		this.buttonEndGame.addMouseListener(new ClickHandler());
		this.buttonNewGame.addMouseListener(new ClickHandler());
		this.buttonSaveHighScore.addMouseListener(new ClickHandler());
		this.buttonChangeDifficulty.setFocusable(false);
		this.buttonEndGame.setFocusable(false);
		this.buttonNewGame.setFocusable(false);
		this.buttonSaveHighScore.setFocusable(false);
		this.buttonSaveHighScore.setEnabled(false);
		this.window.addKeyListener(new EventHandler());
		this.window.setSize(900,700);
		this.window.setResizable(false);
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
	}
	/**
	 * Main function represent out actual Game. While our GUI is active, we are rendering the board 
	 * and checking game rules.If the current game that is being played (i.e current level) is finished
	 * (if g.isEndGame return true) that is if the player is being destroyed,or all the pirates, we update
	 * our game Components that display some new updated information,and perform creation of new game instance.
	 * @param args
	 */
	public static void main(String[] args) {
		GUI G1 = new GUI(15,15);
		while(true) {
			G1.g.checkGame();
			G1.renderBoard();
			if(G1.g.isEndGame()) {
				G1.g.setScore((int)(G1.g.getNumberOfMoves()/G1.g.getLevel()));
				G1.buttonSaveHighScore.setEnabled(true);
				if(!G1.g.getWin()) {
					G1.msgBox("Sorry! You lost");
					G1.g.setGame(15,15);
					G1.showDifficulty.setText(G1.g.getDifficulty());
					G1.showLevel.setText(String.valueOf(G1.g.getLevel()));
					G1.showScore.setText(String.valueOf(G1.g.getScore()));
					G1.showHighScore.setText(String.valueOf(Game.highScore));
				}
				else {
					G1.msgBox("Congratulation! See you on next level.");
					G1.showHighScore.setText(String.valueOf(Game.highScore));
					G1.showLevel.setText(G1.g.updateLevel());
					G1.showScore.setText(String.valueOf(G1.g.getScore()));
					G1.g.newGame(15, 15, G1.g.getLevel(), G1.g.getDifficulty(), G1.g.getScore());
				}
			}
		}
	}
	
	/**
	 * As described above, this method does all the actual work, it renders board to our game field 
	 * which is represented as matrix of JButtons.
	 */
	public void renderBoard() {
		for(int i=0;i<this.g.b.getRows();i++) {
			for(int j=0;j<this.g.b.getColumns();j++) {
				if(this.g.b.onPosition(i,j).equals(this.g.b.Player)) {
					this.field[i][j].setBackground(this.SHIP);
					this.field[i][j].setIcon(this.SHIP2);
				}
				else if(this.g.b.onPosition(i,j).equals(this.g.b.Pirate)) {
					this.field[i][j].setBackground(this.PIRATES);
					this.field[i][j].setIcon(this.PIRATES2);
				}
				else if(this.g.b.onPosition(i,j).equals(this.g.b.Barrier)) {
					this.field[i][j].setBackground(Color.ORANGE);
					this.field[i][j].setIcon(this.BARRIERS2);
				}
				else {
					this.field[i][j].setBackground(this.SEA);
					this.field[i][j].setIcon(this.SEA2);
				}
			}
		}
	}
	/**
	 * This method is not being used in this project, since we move Player around by pressing the keys.
	 * If we want to enable the feature of playing by mouse clicks, we need to ensure that JButton on 
	 * our game field that represent valid position on our board be enabled for clicking. That part of the 
	 * work is done by this method, providing it with actual position of player and boolean valued variable
	 * to set JButton to enable or disable.
	 * @param p represent current Player Position around which we want to enable focus
	 * @param onf represent boolean valued variable to set enable to onf (true/false)
	 */
	private void enableFocus(Position p,boolean onf) {
		if(!this.g.b.isValidPosition(p)) {
			return;
		}
		int x=p.getX();
		int y=p.getY();
		for(int i=x-1;i<(x-1)+3;i++) {
			for(int j=y-1;j<(y-1)+3;j++) {
				if(this.g.b.isValidPosition(i, j)) {
					this.field[i][j].setEnabled(onf);
				}
			}
		}
	}
	
	private void msgBox(String s) {
		JOptionPane.showMessageDialog(this.window,s);
	}
	
	/**
	 * This is one of two nested classes in our GUI class.Nested class EventHandler is used to handle 
	 * every meaningful key pressed by user.The Player is being moved by numpads on your keyboard.
	 * Arrow keys are not supported since there is no the right way to implement Players diagonal moves.
	 * Because of that, numpads must be used for playing. Numpads 2,4,6,8 represent simple directions 
	 * as (by row) Down,Left,Right,Up. Diagonal directions are represented by 1,3,7,9 numpads, with their
	 * respective directions as Down-Left,Down-Right,Up-left,Up-Right. Although numpad 5 is not supported
	 * it still can be used for playing, since it represent that the user choose to stay at same place, but
	 * Pirate still perform their type of move.
	 * @author kr3s0
	 *
	 */
	public class EventHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			//GUI.this.enableFocus(GUI.this.g.p.getPosition(), false);
		switch(e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1:
				GUI.this.g.p.moveDownLeft();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD2:
				GUI.this.g.p.moveDown();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD3:
				GUI.this.g.p.moveDownRight();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD4:
				GUI.this.g.p.moveLeft();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD5:
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD6:
				GUI.this.g.p.moveRight();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD7:
				GUI.this.g.p.moveUpLeft();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD8:
				GUI.this.g.p.moveUp();
				GUI.this.g.updateNumberOfMoves();
				break;
			case KeyEvent.VK_NUMPAD9:
				GUI.this.g.p.moveUpRight();
				GUI.this.g.updateNumberOfMoves();
				break;
			}
			//GUI.this.enableFocus(GUI.this.g.p.getPosition(), true);
			if(GUI.this.g.getDifficulty().equals("Easy")) {
				GUI.this.g.list_Pirates.forEach((g)->g.moveToPlayer(GUI.this.g.p.getPosition(),1));
			}
			else {
				GUI.this.g.list_Pirates.forEach((g)->g.moveToPlayer(GUI.this.g.p.getPosition()));
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
	
	/**
	 * Second nested class in our GUI class is ClickHandler. This nested class is used to handle user
	 * interacting with button on screen. It can choose between multiple buttons and this handler provide 
	 * him with necessary interfaces.
	 * @author kr3s0
	 *
	 */
	public class ClickHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(GUI.this.buttonChangeDifficulty)) {
				if(GUI.this.g.getDifficulty().equals("Easy")) {
					GUI.this.g.setEasyDifficulty(false);
				}
				else {
					GUI.this.g.setEasyDifficulty(true);
				}
				GUI.this.showDifficulty.setText(GUI.this.g.getDifficulty());
			}
			else if(e.getSource().equals(GUI.this.buttonNewGame)) {
				GUI.this.g.newGame(GUI.this.g.b.getRows(), GUI.this.g.b.getColumns(), 1, "Easy", 0);
				GUI.this.showDifficulty.setText(GUI.this.g.getDifficulty());
				GUI.this.showLevel.setText(String.valueOf(GUI.this.g.getLevel()));
				GUI.this.showScore.setText(String.valueOf(GUI.this.g.getScore()));
			}
			else if(e.getSource().equals(GUI.this.buttonEndGame)) {
				System.exit(0);
				GUI.this.window.setVisible(false);
				GUI.this.window.dispose();
			}
			else if(e.getSource().equals(GUI.this.buttonSaveHighScore)) {
				Game.setHighScore(GUI.this.g.getScore());
				GUI.this.buttonSaveHighScore.setEnabled(false);
				GUI.this.msgBox("Your score is saved!");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {	
		}
	}
}
