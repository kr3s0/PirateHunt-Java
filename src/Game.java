import java.util.ArrayList;

public class Game {
	Board b;
	Player p;
	ArrayList<Pirate> list;
	int level;
	
	Game() {
		this.b = null;
		this.p = null;
		this.level = 0;
		list = null;
	}
	
	public void setGame() {
		this.b = new Board(7,7);
		this.p = new Player(new Position(3,3,b));
		this.level = 1;
		list = new ArrayList<Pirate>();
		for(int i=0;i<4;i++) {
			list.add(new Pirate(new Position((int)(Math.random()*this.b.getRows()),(int)(Math.random()*this.b.getColumns()),b)));
		}
		this.b.generateBarriers(3);
	}
	
	public void checkGame() {
		checkPirates();
	}
	
	private void checkPirates() {
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(list.get(i).getPosition().isEqual(list.get(j).getPosition())) {
					list.remove(i);
					list.remove(j);
					this.b.generateBarriers(1);
				}
			}
		}
	}
}
