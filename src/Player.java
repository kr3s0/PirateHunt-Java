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
	
	//This should be banned,we don't want user to have ability of free-choice Position changing
	//But, during the course of implementing project, this will be used.
	//To prevent having two or more Players, we should first call Board.removePlayer(Position p);
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
