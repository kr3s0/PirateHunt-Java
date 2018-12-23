public class Position{
	/* This class represent one Position on our game Board.
	 * We want to ensure that every single Position that is created as a instance of this class
	 * is valid with respect to currently assign Board.
	 * If user, while creating Position with Constructor, provides invalid Board position
	 * an IndexOutOfBoundsException will be thrown.
	 * If user, while changing Position variables, provides invalid x or y value with respect
	 * to Board positions, the Position will not be changed.
	 */
	private int x;
	private int y;
	private Board b;
	private Object o;
	
	Position(int x,int y,Board b,Object o){
		this.b=b;
		if(!this.b.isValidPosition(x,y)) {
			throw new IndexOutOfBoundsException("Koordinate "+"("+x+","+y+")"+"nisu validne!");
		}
		this.x=x;
		this.y=y;
		this.o=o;
		if(this.o.getClass()==Player.class) {
			this.b.setPlayer(this);
		}
		else {
			this.b.setPirate(this);
		}
	}
	
	
	Position(int x,int y,Board b){
		this.b=b;
		if(!this.b.isValidPosition(x,y)) {
			throw new IndexOutOfBoundsException("Koordinate "+"("+x+","+y+")"+"nisu validne!");
		}
		this.x=x;
		this.y=y;
		this.o=null;
	}
	
	public void setX(int x) {
		if(!this.b.isValidPosition(x,this.y)) {
			return;
		}
		this.x=x;
	}
	
	public void setObject(Object o) {
		this.o=o;
		if(this.o.equals(Player.class)) {
			this.b.setPlayer(this);
		}
		else {
			this.b.setPirate(this);
		}
	}
	
	public Object getObject() {
		return this.o;
	}
	
	public Board getBoard() {
		return this.b;
	}
	
	public void setBoard(Board b) {
		this.b=b;
	}
	
	public void setY(int y) {
		if(!this.b.isValidPosition(this.x,y)) {
			return;
		}
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void UP_LEFT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x-1,this.y-1)) {
			return;
		}
		removeClassObject(this);
		this.x--;
		this.y--;
		setClassObject(this);
	}
	
	public void UP() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x-1,this.y)) {
			return;
		}
		removeClassObject(this);
		this.x--;
		setClassObject(this);
	}
	
	public void UP_RIGHT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x-1,this.y+1)) {
			return;
		}
		removeClassObject(this);
		this.x--;
		this.y++;
		setClassObject(this);
	}
	
	public void RIGHT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x, this.y+1)) {
			return;
		}
		removeClassObject(this);
		this.y++;
		setClassObject(this);
	}
	
	public void DOWN_RIGHT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x+1,this.y+1)) {
			return;
		}
		removeClassObject(this);
		this.x++;
		this.y++;
		setClassObject(this);
	}
	
	public void DOWN() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x+1, this.y)) {
			return;
		}
		removeClassObject(this);
		this.x++;
		setClassObject(this);
	}
	
	public void DOWN_LEFT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x+1,this.y-1)) {
			return;
		}
		removeClassObject(this);
		this.x++;
		this.y--;
		setClassObject(this);
	}
	
	public void LEFT() {
		if(this.o==null) {
			throw new NullPointerException("Ne mozete pomjeriti poziciju koja nema objekta");
		}
		if(!this.b.isValidPosition(this.x, this.y-1)) {
			return;
		}
		removeClassObject(this);
		this.y--;
		setClassObject(this);	
	}
	
	private void removeClassObject(Position p) {
		if(this.o.equals(Player.class)) {
			this.b.removePlayer(p);
		}
		else {
			this.b.removePirate(p);
		}
	}
	
	private void setClassObject(Position p) {
		if(this.o.equals(Player.class)) {
			this.b.setPlayer(p);
		}
		else {
			this.b.setPirate(p);
		}
	}
	
	public boolean isEqual(Position p) {
		if(this.x==p.getX() && this.y==p.getY()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
}
