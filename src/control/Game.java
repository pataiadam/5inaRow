package control;

public class Game {

	private Table t;
	private boolean isFirstNext = true;
	private IPlayer p1;
	private IPlayer p2;
	private String result;

	public Game(IPlayer p1, IPlayer p2) {
		this.p1 = p1;//X
		this.p2 = p2;//O
		t = new Table(100, 5);

	}

	public void start() {
		while (!t.isFinished()) {
			boolean stepIsSuccess=t.step(nextPlayer().nextStep(), isFirstNext);
			if(!stepIsSuccess){
				result="FAIL";
				return;
			}
		}
		
		if (t.getWinner().equals(Sign.X)) {
			result = p1.getName() + " is the winner!";
		} else {
			result = p2.getName() + " is the winner!";
		}
	}

	private IPlayer nextPlayer() {
		isFirstNext = !isFirstNext;
		if (isFirstNext) {
			return p1;
		} else{
			return p2;
		}
	}

	public String result() {
		
		return result;
	}

}
