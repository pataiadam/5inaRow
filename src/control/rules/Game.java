package control.rules;

import control.IPlayer;
import model.Point;
import model.Sign;

public class Game {

	private Table table;
	private IPlayer p1; /** Sign X */
	private IPlayer p2; /** Sign O */
	private String result; 
	private boolean isFirstNext = true;
	private Sign nextSign;
	
	public Game(IPlayer p1, IPlayer p2, Table table) {
		this.p1 = p1;
		this.p2 = p2;
		this.table = table;
	}

	public void start() {
		while (!table.isFinished()) {
			IPlayer next = nextPlayer();
			Point nextStep = next.nextStep(table);
			boolean stepIsSuccess=table.step(nextStep, nextSign);
			if(!stepIsSuccess){
				result="FAIL";
				return;
			}
			System.out.println(next.getName() + " "+nextStep);
		}
		
		if (table.getWinner().equals(Sign.X)) {
			result = p1.getName() + " is the winner!";
		} else {
			result = p2.getName() + " is the winner!";
		}
	}

	private IPlayer nextPlayer() {
		isFirstNext = !isFirstNext;
		if (isFirstNext) {
			nextSign=new Sign(Sign.X);
			return p1;
		} else{
			nextSign=new Sign(Sign.O);
			return p2;
		}
	}

	public String result() {
		return result;
	}

}
