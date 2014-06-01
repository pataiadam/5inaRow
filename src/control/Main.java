package control;
import java.awt.EventQueue;

import model.Table;
import players.FirstPlayer;
import players.SecondPlayer;
import view.MainWindow;
import control.rules.Game;
import control.rules.Game.FrameRefresher;
import control.rules.Game.Play;

public class Main {

	public static MainWindow frame;
	
	public static void main(String[] args) {

		frame=new MainWindow();
		frame.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					IPlayer p1 = new FirstPlayer();
					IPlayer p2 = new SecondPlayer();
					Table t = new Table(20, 5);
					Game g = new Game(p1, p2, t);
					//g.start();
					(new Thread(g.new Play())).start();
					(new Thread(g.new FrameRefresher())).start();
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}

}
