import java.util.ArrayList;

public class Game {
	public Board b;
	public Player p;
	public ArrayList<Pirate> list_Pirates;
	public ArrayList<Barrier> list_Barriers;
	private int level;
	private int score;
	private String difficulty;
	private boolean isWin;
	
	Game() {
		this.b = null;
		this.p = null;
		this.level = 1;
		list_Pirates = null;
		list_Barriers = null;
	}
	
	public void newGame(int r,int c,int level,String diff,int score) {
		this.b = new Board(r,c);
		this.p = new Player(new Position(3,3,b));
		this.level = level;
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

	public void setGame(int r,int c) {
		this.b = new Board(r,c);
		this.p = new Player(new Position(3,3,b));
		this.level = 1;
		this.difficulty = "Easy";
		this.score = 0;
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
	
	public void checkGame() {
		checkPirates();
		checkBarriers();
	}
	
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
		this.score+=i;
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
