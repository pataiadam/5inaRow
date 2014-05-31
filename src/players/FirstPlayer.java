package players;
import java.util.Random;

import model.Point;
import model.Table;
import control.IPlayer;


public class FirstPlayer implements IPlayer {

	@Override
	public String getName() {
		return "First player";
	}

	@Override
	public Point nextStep(Table table) {
		Random g = new Random();
		Point p = new Point(g.nextInt(table.getTableSize()), g.nextInt(table
				.getTableSize()));
		while (!table.isValidStep(p)) {
			p = new Point(g.nextInt(table.getTableSize()), g.nextInt(table
					.getTableSize()));
		}
		return p;
	}


}
