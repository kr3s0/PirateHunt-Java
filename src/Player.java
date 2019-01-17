/**
 *  Class Player handles all responsibilites regarding Player on the game filed(instance of Board class).
 *  Class keeps two attributes that represent Player position of type Position and number of lives that player
 *  currentyl has.Besides that, Player class contains methods for performing moves of Player on game field.
 *  This rather simple class, with all methods that are implemented here, are really self-explanatory.
 *  
 *  The main reason I decided to perform this "two-step moves"(move Player(Up) -> Player.moveUp 
 *  -> Player.position.UP -> Position.class -> UP) is that this Player class should not be bothered with
 *  performing validity checks on every new position. Actually, no Object should be bothered with it, that is
 *  the main reason we develop class Position, which handles all dirty work for us.
 *  
 *  For more information regarding how to actual "move" is handled:
 *  @see Position.class.UP/DOWN/etc.
 * @author kr3s0
 * @since 2018-12-21
 */
public class Player {
	private Position position;
	private int lives;
	
	Player(Position p){
		p.setObject(Player.class);
		this.position = p;
		this.lives=1;
	}
	
	Player(Position p,int lives){
		p.setObject(Player.class);
		this.position = p;
		this.lives=lives;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position p) {
		this.position.setX(p.getX());
		this.position.setY(p.getY());
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void moveUpLeft() {
		this.position.UP_LEFT();
	}
	
	public void moveUp() {
		this.position.UP();
	}
	
	public void moveUpRight() {
		this.position.UP_RIGHT();
	}
	
	public void moveRight() {
		this.position.RIGHT();
	}
	
	public void moveDownRight() {
		this.position.DOWN_RIGHT();
	}
	
	public void moveDown() {
		this.position.DOWN();
	}
	
	public void moveDownLeft() {
		this.position.DOWN_LEFT();
	}
	
	public void moveLeft() {
		this.position.LEFT();
	}
}
