/**
 * Public class Board represent actual game field where everything occur.
 * Board is a matrix of type String[][], that is, 2D array of type String.
 * It also stores attributes rows and columns of type int. In order to easily 
 * perform rendering our Board to console, we represent and store Object on Boards
 * as static final String values.
 * @author Tarik Kreso - kr3s0
 * @since 2018-12-22;
 */
public class Board {
	String[][] matrix;
	private int rows;
	private int columns;
	public static final String Player = "Y";
	public static final String Pirate = "X";
	public static final String Barrier = "M";
	public static final String Empty = "O";
	
	Board(int r,int c){
		this.rows=r;
		this.columns=c;
		matrix = new String[this.rows][this.columns];
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.columns;j++) {
				matrix[i][j]=this.Empty;
			}
		}
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	/**
	 * Method for rendering Board class instance to console.
	 */
	public void toConsole() {
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.columns;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Simple method for performing validity test on given coordinates x and y.
	 * This method is used by Position class for checking if it is valid in terms of
	 * its x and y coordinates that represent.
	 * 
	 * We say that (x,y) 2D point is valid if x is in range (0,Board.rows)
	 * and if y is in range(0,Board.columns).
	 * @param x coordinate of 2D point
	 * @param y coordinate of 2D point 
	 * @return boolean value as result of test
	 */
	public boolean isValidPosition(int x,int y) {
		if((x<0) || (x>=this.rows) || (y<0) || (y>=this.columns)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method that is the same as method isValidPosition(int x,int y), but accept actual 
	 * Position.class object rather than x and y of type int.
	 *
	 * We say that Position p is valid if P.x is in range (0,P.Board.rows)
	 * and if P.y is in range(0,P.Board.columns).
	 * 
	 * @param p of Position.class type
	 * @return boolean value as result of test
	 */
	public boolean isValidPosition(Position p) {
		if(p.getX()<0 || p.getX()>=this.rows || p.getY()<0 || p.getY()>=this.columns) {
			return false;
		}
		return true;
	}
	
	/**
	 * Setter for Player Object.Firstly,we perform validity test on parameter Position
	 * then, if everything is fine, we assign String representing Player Object on 
	 * respected point.
	 * @param p Position.class
	 */
	public void setPlayer(Position p) {
		if(!isValidPosition(p)) {
			return;
		}
		matrix[p.getX()][p.getY()]=this.Player;
	}
	
	/**
	 * Setter for Pirate Object
	 * @param p Position.class
	 */
	public void setPirate(Position p) {
		if(!isValidPosition(p)) {
			return;
		}
		matrix[p.getX()][p.getY()]=this.Pirate;
	}
	
	/**
	 * Class method that remove Player.Object from this Board class.
	 * This method is used mostly in Position.class where we want to change Position of Object 
	 * that occupies Position p
	 * @param p Position.class
	 */
	public void removePlayer(Position p) {
		if(onPosition(p).equals(this.Player)) {
			matrix[p.getX()][p.getY()]=this.Empty;
		}
	}
	
	public void removePirate(Position p) {
		if(onPosition(p).equals(this.Pirate)){
			matrix[p.getX()][p.getY()]=this.Empty;
		}
	}
	
	/**
	 * Method onPosition() returns Object that is stated on Position p provided
	 * as parameter.We ensure that parameter p of type Position is valid. 
	 * @param p
	 * @return String that represents Object on Position p
	 */
	public String onPosition(Position p) {
		if(!isValidPosition(p)) {
			return this.Empty;
		}
		return matrix[p.getX()][p.getY()];
	}
	
	public String onPosition(int x,int y) {
		if(!isValidPosition(x, y)) {
			return this.Empty;
		}
		return matrix[x][y];
	}
	
	public void setBarrier(Position p) {
		this.matrix[p.getX()][p.getY()]=this.Barrier;
	}
	
	public void removeBarrier(Position p) {
		if(onPosition(p).equals(this.Barrier)) {
			this.matrix[p.getX()][p.getY()]=this.Empty;
		}
	}
}
