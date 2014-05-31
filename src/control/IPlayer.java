package control;

import model.Point;
import model.Table;


public interface IPlayer {

	public String getName();
	public Point nextStep(Table table);
}
