package control.rules;

import model.Point;
import model.Sign;
import model.Table;
import control.IPlayer;

public class Game {

	private Table table;
	private IPlayer p1;
	/** Sign X */
	private IPlayer p2;
	/** Sign O */
	private String result;
	private boolean isFirstNext = true;
	private Sign nextSign;

	private String winnerSign;

	public Game(IPlayer p1, IPlayer p2, Table table) {
		this.p1 = p1;
		this.p2 = p2;
		this.table = table;
	}

	public void start(long time) throws InterruptedException {
		while (!isFinished()) {
			IPlayer next = nextPlayer();
			Point nextStep = next.nextStep(table.getCopy());
			System.out.println(next.getName() + " " + nextStep);

			boolean stepIsSuccess = step(nextStep, nextSign);
			if (!stepIsSuccess) {
				return;
			}
			Thread.sleep(time);
		}
	}

	private IPlayer nextPlayer() {
		isFirstNext = !isFirstNext;
		if (isFirstNext) {
			nextSign = new Sign(Sign.X);
			return p1;
		} else {
			nextSign = new Sign(Sign.O);
			return p2;
		}
	}

	boolean isFinished() {
		setWinnerSign(findWinner());
		return getWinnerSign() != null ? true : false;
	}

	private String findWinner() {
		for (int x = 0; x < table.getTableSize(); x++) {
			for (int y = 0; y < table.getTableSize(); y++) {
				String s = table.getSign(x, y).getSign();
				if (!s.equals(Sign.EMPTY)) {
					for (int direction = 0; direction <= 3; direction++) {
						int length = checkWin(x, y, s, direction, 0);
						if (length >= table.getLengthToWin()) {
							return s;
						}
					}
				}
			}
		}
		return null;
	}

	private int checkWin(int x, int y, String sign, int irany, int length) {
		boolean onTable = x >= 0 && x < table.getTableSize() && y >= 0
				&& y < table.getTableSize();
		if (onTable && table.getSign(x, y).getSign().equals(sign)) {
			switch (irany) {
			case 0:
				/** Direction right */
				return checkWin(++x, y, sign, irany, length + 1);
			case 1:
				/** Direction right and down */
				return checkWin(++x, ++y, sign, irany, length + 1);
			case 2:
				/** Direction down */
				return checkWin(x, ++y, sign, irany, length + 1);
			case 3:
				/** Direction down and left */
				return checkWin(--x, ++y, sign, irany, length + 1);
			}
		}
		return length;
	}

	boolean step(Point nextStep, Sign sign) {
		try {
			if (table.isValidStep(nextStep)) {
				table.setSign(nextStep.getX(), nextStep.getY(), sign);
			}
		} catch (Exception e) {
			// TODO: fix pokemon handling
			return false;
		}
		return true;
	}

	String getWinnerSign() {
		return winnerSign;
	}

	private void setWinnerSign(String winnerSign) {
		this.winnerSign = winnerSign;
	}

	public String result() {
		if (getWinnerSign().equals(Sign.X)) {
			result = p1.getName() + " is the winner!";
		} else if (getWinnerSign().equals(Sign.O)) {
			result = p2.getName() + " is the winner!";
		} else {
			result = "FAIL";
		}
		return result;
	}

	public Table getTable() {
		return this.table;
	}

}
