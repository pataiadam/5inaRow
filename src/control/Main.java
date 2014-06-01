package control;

import model.Table;
import players.FirstPlayer;
import players.SecondPlayer;
import view.MainWindow;
import control.rules.Game;

public class Main {

	private static MainWindow view;
	private static Game game;

	public static void main(String[] args) {
		IPlayer p1 = new FirstPlayer();
		IPlayer p2 = new SecondPlayer();
		Table t = new Table(20, 5);

		game = new Game(p1, p2, t);

		view = new MainWindow();
		view.setVisible(true);

		(new Thread(new Play())).start();
		(new Thread(new FrameRefresher())).start();

	}

	public static class FrameRefresher implements Runnable {
		public void run() {
			try {
				while (true) {
					view.redrawTable(game.getTable());
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Play implements Runnable {
		public void run() {
			try {
				game.start(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(game.result());
		}
	}

}
