/**
 * Pirate class represent Pirate Object on our Board.Pirate class is described with only one 
 * private attribute of type Position, as the every other Object in our Project.
 * We ensure that every single Pirate created as instance of this class has valid Position on board and that
 * methods which are used to move Pirate around Board perform as expected.
 * 
 * Same explanation regarding simplyfing code by using Position class to represent exact position of Pirate on
 * game board is perfectly valid here as well as in Player and Barrier class.
 * @author kr3s0
 * @since 2018-12-23
 */

public class Pirate {
	private Position p;
	
	Pirate(Position p){
		p.setObject(Pirate.class);
		this.p = p;
	}
	
	public void setPosition(Position p){
		p.setObject(Pirate.class);
		this.p = p;
	}
	
	public Position getPosition() {
		return this.p;
	}
	
	/**
	 * Method moveToPlayer is used for deciding in which direction our Pirate should move.
	 * Firstly, we calculate distance to playerPosition(provided as parameter). When method 
	 * fetch distances for every eight possible directions, we choose one amongst them
	 * whit minimum overall distance.
	 * @param playerPosition
	 */
	public void moveToPlayer(Position playerPosition) {
		int[] arr = new int[8]; //up right down left
		arr[0]=getDistanceToPlayer(this.p.getX()-1,this.p.getY()-1,playerPosition);
		arr[1]=getDistanceToPlayer(this.p.getX()-1,this.p.getY(), playerPosition);
		arr[2]=getDistanceToPlayer(this.p.getX()-1, this.p.getY()+1, playerPosition);
		arr[3]=getDistanceToPlayer(this.p.getX(),this.p.getY()+1, playerPosition);
		arr[4]=getDistanceToPlayer(this.p.getX()+1,this.p.getY()+1, playerPosition);
		arr[5]=getDistanceToPlayer(this.p.getX()+1,this.p.getY(), playerPosition);
		arr[6]=getDistanceToPlayer(this.p.getX()+1,this.p.getY()-1, playerPosition);
		arr[7]=getDistanceToPlayer(this.p.getX(),this.p.getY()-1, playerPosition);
		int min=arr[0];
		int indeks = 0;
		for(int i=1;i<8;i++) {
			if(arr[i]<min) {
				min=arr[i];
				indeks = i;
			}
		}
		switch(indeks) {
			case 0:
				this.p.UP_LEFT();
				break;
			case 1:
				this.p.UP();
				break;
			case 2:
				this.p.UP_RIGHT();
				break;
			case 3:
				this.p.RIGHT();
				break;
			case 4:
				this.p.DOWN_RIGHT();
				break;
			case 5:
				this.p.DOWN();
				break;
			case 6:
				this.p.DOWN_LEFT();
				break;
			case 7:
				this.p.LEFT();
				break;
			default:
				break;
		}
	}
	
	/**
	 * Different version of method moveToPlayer. First version of moveToPlayer method calculate distances
	 * for all eight possible directions. On the other hand, this method is used to simulate easier game
	 * difficulty. We disable diagonal moves for Pirates, while Player can continue to move diagonal.
	 * @param playerPosition parameter that represent Player position on Board
	 * @param l Since Java enable only function overloading by providing different number of parameters
	 * 			we have one additional parameter to enable JVM to decide between two moveToPlayer methods.
	 * 			Rather hard-coded solution, but it works.
	 */
	public void moveToPlayer(Position playerPosition,int l) {
		int[] arr = new int[4]; //up right down left
		arr[0]=getDistanceToPlayer(this.p.getX()-1,this.p.getY(), playerPosition);
		arr[1]=getDistanceToPlayer(this.p.getX(),this.p.getY()+1, playerPosition);
		arr[2]=getDistanceToPlayer(this.p.getX()+1,this.p.getY(), playerPosition);
		arr[3]=getDistanceToPlayer(this.p.getX(),this.p.getY()-1, playerPosition);	
		int min=arr[0];
		int indeks = 0;
		for(int i=0;i<4;i++) {
			if(arr[i]<min) {
				min=arr[i];
				indeks = i;
			}
		}
		switch(indeks) {
			case 0:
				this.p.UP();
				break;
			case 1:
				this.p.RIGHT();
				break;
			case 2:
				this.p.DOWN();
				break;
			case 3:
				this.p.LEFT();
				break;
		}
	}
	
	/**
	 * Since in Pirate Hunt game, Pirates always perform the best possible move relative to one which
	 * was made by Player. Performing the best possible move is done by method moveToPlayer(), but actual
	 * value that is deciding factor to distinct best moves from others is this method calculating Manhattan
	 * distance, which is in 2D simple as it could possibly be.
	 * @param x
	 * @param y
	 * @param playerPosition
	 * @return int value that represent distance between Pirate and Player
	 */
	private int getDistanceToPlayer(int x,int y,Position playerPosition) {
		return Math.abs(playerPosition.getX()-x)+Math.abs(playerPosition.getY()-y);
	}
}
