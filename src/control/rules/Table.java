package control.rules;

import model.Point;
import model.Sign;

public class Table {

	private Sign[][] table;
	private int tableSize;
	private int lengthToWin;
	private String winner = null;

	public Table(int tableSize, int lengthToWin) {
		this.lengthToWin = lengthToWin;
		this.tableSize = tableSize;
		table = new Sign[this.tableSize][this.tableSize];
		for (int x = 0; x < tableSize; x++) {
			for (int y = 0; y < tableSize; y++) {
				table[x][y] = new Sign(Sign.EMPTY);
			}
		}
	}

	boolean isFinished() {
		setWinner(findWinner());
		return getWinner() != null ? true : false;
	}

	private String findWinner() {
		for (int x = 0; x < tableSize; x++) {
			for (int y = 0; y < tableSize; y++) {
				String s = table[x][y].getSign();
				if (!s.equals(Sign.EMPTY)) {
					for (int direction = 0; direction <= 3; direction++) {
						int length = checkWin(x, y, s, direction, 0);
						if (length >= lengthToWin) {
							return s;
						}
					}
				}
			}
		}
		return null;
	}

	private int checkWin(int x, int y, String sign, int irany, int length) {
		boolean onTable = x >= 0 && x < tableSize && y >= 0 && y < tableSize;
		if (onTable && table[x][y].getSign().equals(sign)) {
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
			if (isValidStep(nextStep)) {
				table[nextStep.getX()][nextStep.getY()].setSign(sign);
			}
		} catch (Exception e) {
			// TODO: fix pokemon handling
			return false;
		}
		return true;
	}

	String getWinner() {
		return winner;
	}

	private void setWinner(String winner) {
		this.winner = winner;
	}

	public Sign[][] getTable() {
		return table;
	}

	public Sign getSign(int x, int y) {
		return table[x][y];
	}

	public boolean isValidStep(int x, int y) {
		return table[x][y].getSign().equals(Sign.EMPTY);
	}

	public boolean isValidStep(Point nextStep) {
		return isValidStep(nextStep.getX(), nextStep.getY());
	}
	
	public int getTableSize(){
		return tableSize;
	}

	public int getLengthToWin(){
		return lengthToWin;
	}
}
