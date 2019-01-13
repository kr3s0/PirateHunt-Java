import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {
	public Game g;
	public JFrame window;
	private JButton[][] field;
	private JPanel panelwindow;
	private JPanel panelMenu;
	private JPanel panelField;
	private static final Color SEA = new Color(0,105,148);
	private static final Color SHIP = new Color(139,69,19);
	private static final Color PIRATES = Color.BLACK;
	private static final Color BARRIERS = new Color(255,228,181);
	
	public class EventHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			GUI.this.enableFocus(GUI.this.g.p.getPosition(), false);
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
			GUI.this.enableFocus(GUI.this.g.p.getPosition(), true);
			if(GUI.this.g.level==1) {
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
	
	GUI(int r,int c){
		this.window = new JFrame("Pirate Hunt");
		this.panelwindow = new JPanel(new BorderLayout());
		this.panelMenu = new JPanel();
		this.panelField = new JPanel(new GridLayout(r,c));
		this.window.setContentPane(this.panelwindow);
		this.panelMenu.setPreferredSize(new Dimension(200,700));
		this.panelMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.panelwindow.add(this.panelMenu,BorderLayout.LINE_START);
		this.panelwindow.add(this.panelField,BorderLayout.CENTER);
		this.g = new Game();
		this.g.setGame(r,c);
//		this.g.setLevel(2);
		this.field = new JButton[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				this.field[i][j]=new JButton();
				this.field[i][j].setEnabled(false);
				this.field[i][j].setBackground(this.SEA);
				this.panelField.add(this.field[i][j]);
			}
		}
		this.window.addKeyListener(new EventHandler());
		this.window.setSize(900,700);
		this.window.setResizable(false);
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI G1 = new GUI(10,10);
		while(!G1.g.isEndGame()) {
			G1.g.checkGame();
			G1.renderBoard();
		}
		G1.window.setVisible(false);
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
}
