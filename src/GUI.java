import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

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
	private JLabel showLevel;
	private JLabel showDifficulty;
	private JLabel showScore;
	private JPanel panelField;
	private static final Color SEA = new Color(0,105,148);
	private static final Color SHIP = new Color(139,69,19);
	private static final Color PIRATES = Color.BLACK;
	private static final Color BARRIERS = new Color(255,228,181);
	
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
		this.showDifficulty.setPreferredSize(new Dimension(100,40));
		this.showLevel.setPreferredSize(new Dimension(100,40));
		this.showScore.setPreferredSize(new Dimension(100,40));
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
		this.buttonChangeDifficulty.setPreferredSize(new Dimension(150,40));
		this.buttonEndGame.setPreferredSize(new Dimension(150,40));
		this.buttonNewGame.setPreferredSize(new Dimension(150,40));
		this.panelMenuCenter.add(this.buttonNewGame);
		this.panelMenuCenter.add(this.buttonChangeDifficulty);
		this.panelMenuCenter.add(this.buttonEndGame);
		this.panelMenuCenter.setPreferredSize(new Dimension(200,400));
		this.panelMenuCenter.setBackground(this.SHIP);
		this.panelMenuNorth.setBackground(this.BARRIERS);
		this.panelMenuSouth.setBackground(this.BARRIERS);
		this.panelMenuSouth.setPreferredSize(new Dimension(200,150));
		this.panelMenu.add(this.panelMenuNorth,BorderLayout.NORTH);
		this.panelMenu.add(this.panelMenuCenter,BorderLayout.CENTER);
		this.panelMenu.add(this.panelMenuSouth,BorderLayout.SOUTH);
		this.g = new Game();
		this.g.setGame(r,c);
		this.showDifficulty.setText(this.g.getDifficulty());
		this.showLevel.setText(String.valueOf(this.g.getLevel()));
		this.showScore.setText(String.valueOf(this.g.getScore()));
		this.field = new JButton[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				this.field[i][j]=new JButton();
				this.field[i][j].setEnabled(false);
				this.field[i][j].setBackground(this.SEA);
				this.panelField.add(this.field[i][j]);
			}
		}
		this.buttonChangeDifficulty.addMouseListener(new ClickHandler());
		this.buttonEndGame.addMouseListener(new ClickHandler());
		this.buttonNewGame.addMouseListener(new ClickHandler());
		this.buttonChangeDifficulty.setFocusable(false);
		this.buttonEndGame.setFocusable(false);
		this.buttonNewGame.setFocusable(false);
		this.window.addKeyListener(new EventHandler());
		this.window.setSize(900,700);
		this.window.setResizable(false);
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI G1 = new GUI(15,15);
		while(true) {
			G1.g.checkGame();
			G1.renderBoard();
			if(G1.g.isEndGame()) {
				if(!G1.g.getWin()) {
					G1.showDifficulty.setText(G1.g.getDifficulty());
					G1.showLevel.setText(String.valueOf(G1.g.getLevel()));
					G1.showScore.setText(String.valueOf(G1.g.getScore()));
					G1.g.setGame(15,15);
				}
				else {
					G1.showLevel.setText(G1.g.updateLevel());
					G1.g.newGame(15, 15, G1.g.getLevel(), G1.g.getDifficulty(), G1.g.getScore());
				}
			}
		}
	}
	
	public void renderBoard() {
		for(int i=0;i<this.g.b.getRows();i++) {
			for(int j=0;j<this.g.b.getColumns();j++) {
				if(this.g.b.onPosition(i,j).equals(this.g.b.Player)) {
					this.field[i][j].setBackground(this.SHIP);
				}
				else if(this.g.b.onPosition(i,j).equals(this.g.b.Pirate)) {
					this.field[i][j].setBackground(this.PIRATES);
				}
				else if(this.g.b.onPosition(i,j).equals(this.g.b.Barrier)) {
					this.field[i][j].setBackground(this.BARRIERS);
				}
				else {
					this.field[i][j].setBackground(this.SEA);
				}
			}
		}
	}
	
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
	
	public class EventHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			//GUI.this.enableFocus(GUI.this.g.p.getPosition(), false);
		switch(e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1:
				GUI.this.g.p.moveDownLeft();
				break;
			case KeyEvent.VK_NUMPAD2:
				GUI.this.g.p.moveDown();
				break;
			case KeyEvent.VK_NUMPAD3:
				GUI.this.g.p.moveDownRight();
				break;
			case KeyEvent.VK_NUMPAD4:
				GUI.this.g.p.moveLeft();
				break;
			case KeyEvent.VK_NUMPAD6:
				GUI.this.g.p.moveRight();
				break;
			case KeyEvent.VK_NUMPAD7:
				GUI.this.g.p.moveUpLeft();
				break;
			case KeyEvent.VK_NUMPAD8:
				GUI.this.g.p.moveUp();
				break;
			case KeyEvent.VK_NUMPAD9:
				GUI.this.g.p.moveUpRight();
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
			}
			else if(e.getSource().equals(GUI.this.buttonEndGame)) {
				System.exit(0);
				GUI.this.window.setVisible(false);
				GUI.this.window.dispose();
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
