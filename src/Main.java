import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Game g1 = new Game();
		g1.setGame(10,10);
		Scanner scan = new Scanner(System.in);
		while(!g1.isEndGame()) {
			g1.b.toConsole();
			g1.checkGame();
			int i = scan.nextInt();
			switch(i) {
			case 1:
				g1.p.moveDownLeft();
				break;
			case 2:
				g1.p.moveDown();
				break;
			case 3:
				g1.p.moveDownRight();
				break;
			case 4:
				g1.p.moveLeft();
				break;
			case 6:
				g1.p.moveRight();
				break;
			case 7:
				g1.p.moveUpLeft();
				break;
			case 8:
				g1.p.moveUp();
				break;
			case 9:
				g1.p.moveUpRight();
				break;
			}
			g1.list_Pirates.forEach((g)->g.moveToPlayer(g1.p.getPosition()));
			g1.checkGame();
		}		
	}
}
