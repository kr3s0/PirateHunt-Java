public class Board {
	String[][] matrix;
	private int rows;
	private int columns;
	static final String Player = "Y";
	static final String Pirate = "X";
	static final String Barrier = "M";
	static final String Empty = "O";
	
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
	
	private String onPosition(Position p) {
		if(!isValidPosition(p)) {
			return this.Empty;
		}
		return matrix[p.getX()][p.getY()];
	}
	
	private void generateBarrier() {
		matrix[(int)(Math.random()*this.rows)][(int)(Math.random()*this.columns)]=this.Barrier;
	}
	
	public void generateBarriers(int numberOf) {
		for(int i=0;i<numberOf;i++) {
			generateBarrier();
		}
	}
}
