public class Main {
	public static void main(String[] args) {
		Game g1 = new Game();
		g1.setGame();
		for(int i=0;i<4;i++) {
			g1.b.toConsole();
			g1.p.moveDownRight();
			g1.list_Pirates.forEach((g) -> g.moveToPlayer(g1.p.getPosition()));
			g1.checkGame();
			if(g1.isEndGame()) {
				g1.b.toConsole();
				break;
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
}
