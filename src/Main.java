import players.FirstPlayer;
import players.SecondPlayer;
import control.IPlayer;
import control.rules.Game;
import control.rules.Table;


public class Main {

	public static void main(String[] args) {
		IPlayer p1 = new FirstPlayer();
		IPlayer p2 = new SecondPlayer();
		Table t = new Table(100, 5);
		
		Game g = new Game(p1, p2, t);
		g.start();
		System.out.println(g.result());
		
	}

}
