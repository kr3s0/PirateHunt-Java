/** This class represent one Position on our game Board.
* We want to ensure that every single Position that is created as a instance of this class
* is valid with respect to currently assign Board.
* What is more, we associated every Position also with Object that occupies it, although it is
* possible to create an instance of Position class without assigned Object.
* 
* As we mentioned in Player class, the main reason for developing this class is to make classes that 
* represent Objects cleaner. That is, we want to achieve simple statements as if we want to move Player up
* we want to say Player.moveUP. It's pretty clear that we don't want to perform validity checks every time
* for every "movement" method in every class that represent some movable Object.
* If user, while creating Position with Constructor, provides invalid Board position
* an IndexOutOfBoundsException will be thrown.
* If user, while changing Position variables, provides invalid x or y value with respect
* to Board positions, the Position will not be changed.
* 
* @author Tarik Kreso - kr3s0
* @since 2018-12-20
*/
public class Position{
	private int x;
	private int y;
	private Board b;
	private Object o;
	
	
	/** Constructor for class Position
	 *  Creates Position object and define four attributes
	 *  Position class.Also, this constructor 
	 *  set assigned Object o on Board b.
	 * 
	 * @param x One axis of 2D point
	 * @param y One axis of 2D point 
	 * @param b Position on Board b
	 * @param o Object o occupy this Position
	 * @throws IndexOutOfBoundsException if x,y 2D point is not valid
	 */
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
		else if(this.o.getClass()==Pirate.class){
			this.b.setPirate(this);
		}
		else {
			this.b.setBarrier(this);
		}
	}
	
	/** Constructor for Position class but
	 * with Object attribute set to null.
	 * 
	 * @param x One axis of 2D point
	 * @param y One axis of 2D point
	 * @param b Board
	 */
	Position(int x,int y,Board b){
		this.b=b;
		if(!this.b.isValidPosition(x,y)) {
			throw new IndexOutOfBoundsException("Koordinate "+"("+x+","+y+")"+"nisu validne!");
		}
		this.x=x;
		this.y=y;
		this.o=null;
	}
	
	/** Setter for x coordinate of 2D point
	 * 
	 * @param x
	 */
	public void setX(int x) {
		if(!this.b.isValidPosition(x,this.y)) {
			return;
		}
		this.x=x;
	}
	
	/** If we create Position class instance with constructor(x,y,b) 
	 *  without defining Object that occupies that position,
	 *  using this method we can set Object to Position
	 * 
	 * @param o Object
	 */
	public void setObject(Object o) {
		this.o=o;
		if(this.o.equals(Player.class)) {
			this.b.setPlayer(this);
		}
		else if(this.o.equals(Pirate.class)){
			this.b.setPirate(this);
		}
		else {
			this.b.setBarrier(this);
		}
	}
	
	/**
	 *  Getter for Object attribute
	 * @return Object Position.Object
	 */
	
	public Object getObject() {
		return this.o;
	}
	/**
	 * Getter for Board attribute
	 * @return instance of Board.class Position.Board
	 */
	public Board getBoard() {
		return this.b;
	}
	
	/**
	 * Setter for Board attribute
	 * @param b Board
	 */
	public void setBoard(Board b) {
		this.b=b;
	}
	
	/**
	 *  Setter for y coordinate of our Position
	 * @param y
	 */
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
	
	/**
	 *  Method UP_LEFT of Position.class moves Position to up-left position relative to 
	 *  current values of x and y coordinates.
	 *  Furthermore, this method moves object on Position.board to new location.
	 *  Same explanation is valid for all methods of this type.
	 */
	public void UP_LEFT() {
		if(this.o==null) {
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
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
			throw new NullPointerException("You can not move Position without object");
		}
		if(!this.b.isValidPosition(this.x, this.y-1)) {
			return;
		}
		removeClassObject(this);
		this.y--;
		setClassObject(this);	
	}
	
	/**
	 *	Method removeClassObject removes Position.Object from Board that is
	 *	assigned to Position.It uses Board.class methods removePlayer,removePirate and
	 *	removeBarrier as they are only type of objects that can exist on Board 
	 * @param p Position which Object occupies
	 */
	private void removeClassObject(Position p) {
		if(this.o.equals(Player.class)) {
			this.b.removePlayer(p);
		}
		else if(this.o.equals(Pirate.class)){
			this.b.removePirate(p);
		}
		else {
			this.b.removeBarrier(p);
		}
	}
	
	/**
	 * Setter for ClassObject. When we change Position of Object on Board
	 * we also need to literally move Object from previous Posiiton to a new one.
	 * @param p new Position for Object
	 */
	private void setClassObject(Position p) {
		if(this.o.equals(Player.class)) {
			this.b.setPlayer(p);
		}
		else if(this.o.equals(Pirate.class)){
			this.b.setPirate(p);
		}
		else {
			this.b.setBarrier(p);
		}
	}
	
	/**
	 * Method isEqual as parameter accepts one Position p
	 * and perform equality operation on it.
	 * We say that two Position are equal if and only if their respective
	 * x and y coordinates are same.
	 * @param p Position 
	 * @return boolean value true or false
	 */
	public boolean isEqual(Position p) {
		if(this.x==p.getX() && this.y==p.getY()) {
			return true;
		}
		return false;
	}
	
	/**
	 * As we know, every class in Java (even classes defined by ourselves) are 
	 * subclasses of java.Object class. As java.Object have generic method toString() 
	 * we can override it to provide our own implementation of it.
	 * @return String like (x,y)
	 */
	@Override
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
}
