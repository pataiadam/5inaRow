

import model.Point;
import control.rules.Table;

public interface IPlayer {

	public String getName();
	public Point nextStep(Table table);
}
