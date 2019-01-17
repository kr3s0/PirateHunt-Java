import java.util.Scanner;

/**
 * Main class is used to provide the user abillity to play the game in console.We render our game Board by
 * calling Board.toConsole() method that simply output current game Board to console as it is.User interaction
 * with the game is maintained through console,also, since after rendering board to console, the user is
 * prompted to enter the move,which is then performed.
 * 
 * One more class that can be called self-explanatory.
 * @author kr3s0
 * @since 2019-01-05
 */
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
