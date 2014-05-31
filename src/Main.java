import control.Game;
import control.IPlayer;


public class Main {

	public static void main(String[] args) {
		IPlayer p1 = new ElsoPlayer();
		IPlayer p2 = new MasodikPlayer();
		
		Game g = new Game(p1, p2);
		g.start();
		System.out.println(g.result());
		
	}

}
