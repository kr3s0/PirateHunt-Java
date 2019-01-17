import java.util.ArrayList;
/**
 * Class Game describe everything that one instance of our Game should have.It defines Board which represent
 * game field,instance of Player class that represent single Player, we also store Pirates and Barriers as 
 * ArrayList of Pirate/Barrier type. Besides that, we also store, as attribute of Game, variables that
 * represent level,score,difficulty and others. Also, there is static variable highScore which is 
 * self-explanatory.
 * @author kr3s0
 * @since 2019-01-03
 */
public class Game {
	public Board b;
	public Player p;
	public ArrayList<Pirate> list_Pirates;
	public ArrayList<Barrier> list_Barriers;
	private int level;
	private int score;
	private int numberOfMoves;
	private String difficulty;
	private boolean isWin;
	public static int highScore;
	
	Game() {
		this.b = null;
		this.p = null;
		this.level = 1;
		list_Pirates = null;
		list_Barriers = null;
	}
	
	public static int getHighScore() {
		return highScore;
	}
	
	/**
	 * Since we calculate score as numberOfMoves divided by current level, we represent highScore as
	 * lowest overall score throughout the game.Player should try to defeat Pirates in as least numberOfMoves
	 * as possible, at highest possible level.
	 * @param newScore of type int that store current Score
	 */
	public static void setHighScore(int newScore) {
		if(highScore==0) {
			highScore=newScore;
			return;
		}
		if(newScore<highScore) {
			highScore = newScore;
		}
	}
	
	public int getNumberOfMoves() {
		return this.numberOfMoves;
	}
	
	public void updateNumberOfMoves() {
		this.numberOfMoves++;
	}
	
	/**
	 * Method newGame is used to set up newGame to be played, with new Positions assigned to all Objects on
	 * Board. Apart from setGame() method, using newGame() we can "transfer" score,level and difficulty if 
	 * it is necessary to overall new game created. Besides that, we use newGame() to perform what is 
	 * called "level-change", that is, creating whole new level with keeping score,level and difficulty.
	 * @param r of type int represent Board.rows
	 * @param c of type int represent Board.Cols
	 * @param level of type int represent current Game level
	 * @param diff of type String represent current set difficulty
	 * @param score of type int represent current achieved game score
	 */
	public void newGame(int r,int c,int level,String diff,int score) {
		this.b = new Board(r,c);
		this.p = new Player(new Position(3,3,b));
		this.level = level;
		this.numberOfMoves = 0;
		this.difficulty = diff;
		this.score = score;
		this.isWin = true;
		list_Pirates = new ArrayList<Pirate>();
		list_Barriers = new ArrayList<Barrier>();
		while(list_Pirates.size()!=4+this.level) {
			Position t = new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),this.b);
			if(!t.isEqual(this.p.getPosition())) {
				list_Pirates.add(new Pirate(t));
			}
		}
		while(list_Barriers.size()!=3+this.level) {
			Position t = new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),this.b);
			if(!t.isEqual(this.p.getPosition())) {
				list_Barriers.add(new Barrier(t));
			}
		}
	}

	/**
	 * Method setGame() is used to set up entire Game engine that we use for controlling Objects and 
	 * others responsibilites. Only difference between setGame and newGame is that, by using setGame, we 
	 * create entire Game as from beggining, without providing anything rather than number of rows and columns
	 * for our game field.
	 * 
	 * Our Player Position is always the same, and we randomly choose other Positions for Barriers and Pirates.
	 * Since we are randomly choosing Positions for Pirates and Barriers, we should ensure ourselves that 
	 * Pirate or Barrier Object gets created on Position reserved for Player.
	 * On the other hand, it is possible to, at the beginning of the game, we create Pirate and Barrier at
	 * same Position, which means that Pirate is not created at all. 
	 * @param r of type int represent Board.rows
	 * @param c of type int represent Board.columns
	 */
	public void setGame(int r,int c) {
		this.b = new Board(r,c);
		this.p = new Player(new Position(3,3,b));
		this.level = 1;
		this.difficulty = "Easy";
		this.score = 0;
		this.numberOfMoves = 0;
		this.isWin = true;
		list_Pirates = new ArrayList<Pirate>();
		list_Barriers = new ArrayList<Barrier>();
		while(list_Pirates.size()!=3+this.level) {
			Position t = new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),this.b);
			if(!t.isEqual(this.p.getPosition())) {
				list_Pirates.add(new Pirate(t));
			}
		}
		while(list_Barriers.size()!=3+this.level) {
			Position t = new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),this.b);
			if(!t.isEqual(this.p.getPosition())) {
				list_Barriers.add(new Barrier(t));
			}
		}
	}
	
	/**
	 * Public method checkGame() is used,as its name describes, to check current state of the game.
	 * It performs checkPirates() and checkBarriers().
	 *
	 */
	public void checkGame() {
		checkPirates();
		checkBarriers();
	}
	/**
	 * We need to checkPirates() in order to know if two or more Pirates occupies the same Position on the 
	 * Board.If that is the case, then, by the rules of game, that Pirates are destroyed, and new Barrier
	 * is created at their Position.
	 * Since for storing Pirates we use ArrayLists, we need to ensure that we are destroying the right ones.
	 * Problem with ArrayLists is that they are something that is called "Dynamic" data structures, and by 
	 * performing removal of one element, others are updated to their new positions in ArrayList (i.e.
	 * they are moved one position to the left).
	 * So, to be sure that we are destroying the right ones, we firstly make entire copy of ArrayList of Pirates
	 * and instead of destroying the Pirate, at givend index,we assign null value. At the end of the method
	 * we simply assing every non-null value to our list_Pirates.
	 */
	private void checkPirates() {
		Pirate[] arr = new Pirate[this.list_Pirates.size()];
		for(int i=0;i<this.list_Pirates.size();i++) {
			arr[i]=this.list_Pirates.get(i);
		}
		for(int i=0;i<list_Pirates.size();i++) {
			for(int j=0;j<list_Pirates.size();j++) {
				if(i==j) {
					continue;
				}
				if(list_Pirates.get(i).getPosition().isEqual(list_Pirates.get(j).getPosition())) {
					this.b.removePirate(list_Pirates.get(i).getPosition());
					this.b.removePirate(list_Pirates.get(j).getPosition());
					list_Barriers.add(new Barrier(list_Pirates.get(i).getPosition()));
					arr[i]=null;
					arr[j]=null;
				}
			}
		}
		ArrayList<Pirate> temp = new ArrayList<Pirate>();
		for(Pirate p : arr) {
			if(p!=null) {
				temp.add(p);
			}
		}
		this.list_Pirates = temp;
	}
	
	/**
	 * Second method that is called by checkGame() is checkBarriers(). By the rules of the game, if some other
	 * Object on Board (Pirate or Player) hit the Barrier, it is destroyed. To provide that kind of check, we
	 * simply check if there is some Object that is not Barrier, but has the same Position as the one of
	 * Barirers. If that is the case, that other Object is destroyed.
	 */
	private void checkBarriers() {
		for(int i=0;i<list_Pirates.size();i++) {
			for(int j=0;j<list_Barriers.size();j++) {
				if(list_Pirates.get(i).getPosition().isEqual(list_Barriers.get(j).getPosition())) {
					this.b.removePirate(list_Pirates.get(i).getPosition());
					this.list_Pirates.remove(i);
					this.b.setBarrier(list_Barriers.get(j).getPosition());
					break;
				}
			}
		}
	}
	
	/**
	 * Boolean valued method that return true or false depending on the current state of the game.
	 * If there is no left Pirates on the gamefield (i.e list_Pirates is empty) the game should end and
	 * Player should be declared as a winner.
	 * On the other hand, if one of Pirates catches Player, that is, they are on same Positions, the game 
	 * should end, and player has lost.Also, Player can lose by hitting one of the Barriers, so we check
	 * that one.
	 * 
	 * @return boolean value indicating is game ended or not.
	 */
	public boolean isEndGame() {
		if(this.list_Pirates.isEmpty()) {
			return true;
		}
		for(int i=0;i<list_Pirates.size();i++) {
			if(this.list_Pirates.get(i).getPosition().isEqual(this.p.getPosition())) {
				this.isWin = false;
				return true;
			}
		}
		for(int i=0;i<list_Barriers.size();i++) {
			if(this.list_Barriers.get(i).getPosition().isEqual(this.p.getPosition())) {
				this.isWin = false;
				return true;
			}
		}
		return false;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int i) {
		this.score=i;
	}
	
	public String updateLevel() {
		return String.valueOf(++this.level);
	}

	public String getDifficulty() {
		return this.difficulty;
	}
	
	public void setEasyDifficulty(boolean b) {
		if(b) {
			this.difficulty="Easy";
			return;
		}
		this.difficulty="Hard";
	}
	
	public boolean getWin() {
		return this.isWin;
	}
	
	public void setWin(boolean b) {
		this.isWin = b;
	}
	
}
