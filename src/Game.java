import java.util.ArrayList;

public class Game {
	Board b;
	Player p;
	ArrayList<Pirate> list_Pirates;
	ArrayList<Barrier> list_Barriers;
	int level;
	
	Game() {
		this.b = null;
		this.p = null;
		this.level = 1;
		list_Pirates = null;
		list_Barriers = null;
	}

	public void setGame() {
		this.b = new Board(10,10);
		this.p = new Player(new Position(3,3,b));
		this.level = 1;
		list_Pirates = new ArrayList<Pirate>();
		list_Barriers = new ArrayList<Barrier>();
		while(list_Pirates.size()!=4*this.level) {
			Position t = new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),this.b);
			if(!t.isEqual(this.p.getPosition())) {
				list_Pirates.add(new Pirate(t));
			}
		}
		while(list_Barriers.size()!=3*this.level) {
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
		for(int i=0;i<list_Pirates.size();i++) {
			for(int j=0;j<list_Pirates.size();j++) {
				if(i==j) {
					continue;
				}
				if(list_Pirates.get(i).getPosition().isEqual(list_Pirates.get(j).getPosition())) {
					this.b.removePirate(list_Pirates.get(i).getPosition());
					this.b.removePirate(list_Pirates.get(j).getPosition());
					list_Barriers.add(new Barrier(list_Pirates.get(i).getPosition()));
					list_Pirates.remove(i);
					if(j!=0) {
						list_Pirates.remove(j-1);
					}
					else {
						list_Pirates.remove(j);
					}
				}
			}
		}
	}
	
	private void checkBarriers() {
		for(int i=0;i<list_Pirates.size();i++) {
			for(int j=0;j<list_Barriers.size();j++) {
				if(list_Pirates.get(i).getPosition().isEqual(list_Barriers.get(j).getPosition())) {
					this.b.removePirate(list_Pirates.get(i).getPosition());
					this.b.setBarrier(list_Barriers.get(j).getPosition());
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
				return true;
			}
		}
		for(int i=0;i<list_Barriers.size();i++) {
			if(this.list_Barriers.get(i).getPosition().isEqual(this.p.getPosition())) {
				return true;
			}
		}
		return false;
	}
}
