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
	
	public void toConsole() {
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.columns;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean isValidPosition(int x,int y) {
		if((x<0) || (x>=this.rows) || (y<0) || (y>=this.columns)) {
			return false;
		}
		return true;
	}
	
	public boolean isValidPosition(Position p) {
		if(p.getX()<0 || p.getX()>=this.rows || p.getY()<0 || p.getY()>=this.columns) {
			return false;
		}
		return true;
	}
	
	public void setPlayer(Position p) {
		if(!isValidPosition(p)) {
			return;
		}
		matrix[p.getX()][p.getY()]=this.Player;
	}
	
	public void setPirate(Position p) {
		if(!isValidPosition(p)) {
			return;
		}
		matrix[p.getX()][p.getY()]=this.Pirate;
	}
	
	public void removePlayer(Position p) {
		if(onPosition(p).equals(this.Player)) {
			matrix[p.getX()][p.getY()]=this.Empty;
		}
		//To-Do: Maybe we should consider looping through our Board and removing Player ourselves;
	}
	
	public void removePirate(Position p) {
		if(onPosition(p).equals(this.Pirate)){
			matrix[p.getX()][p.getY()]=this.Empty;
		}
		//To-Do: Same as in removePlayer(Position p);
	}
	
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
