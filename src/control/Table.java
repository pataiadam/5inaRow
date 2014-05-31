package control;

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
		// testHorizontal();
		//testVertical();
	}

	public boolean isFinished() {
		setWinner(findWinner());
		return getWinner() != null ? true : false;
	}

	public String findWinner() {// X,O
		// For non-commercial use only!
		// opt: ha nem történt annyi lépés amiből meg lehet nyerni a játékot nem
		// kell vizsgálni
		// -What does the 'B' in Benoit B. Mandelbrot stand for?
		// -???
		// -Benoit B. Mandelbrot

		for (int x = 0; x < tableSize; x++) {
			for (int y = 0; y < tableSize; y++) {
				String s = table[x][y].getSign();
				if (!s.equals(Sign.EMPTY)) {
					for (int irany = 0; irany <= 3; irany++) {
						int hossz = checkWin(x, y, s, irany, 0);
						System.out.println(hossz);
						if (hossz >= lengthToWin) {

							return s;
						}
					}
				}
			}
		}
		return null;
	}

	private int checkWin(int x, int y, String s, int irany, int length) {
		boolean onTable = x >= 0 && x < tableSize && y >= 0 && y < tableSize;
		if (onTable && table[x][y].getSign().equals(s)) {
			switch (irany) {
			case 0:// jobb
				return checkWin(++x, y, s, irany, length + 1);
			case 1:// jobb le
				return checkWin(++x, ++y, s, irany, length + 1);
			case 2:// le
				return checkWin(x, ++y, s, irany, length + 1);
			case 3:// bal le
				return checkWin(--x, ++y, s, irany, length + 1);
			}
		}
		return length;
	}

	public boolean step(Point nextStep, boolean isFirstNext) {
		try {
			if (table[nextStep.getX()][nextStep.getY()].getSign().equals(
					Sign.EMPTY)) {
				table[nextStep.getX()][nextStep.getY()].setSign(isFirstNext);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*private void testHorizontal() {
		// feltölteni több kül mintaval
		table[3][3] = new Sign(Sign.X);
		table[4][4] = new Sign(Sign.X);
		table[5][5] = new Sign(Sign.X);
		table[6][6] = new Sign(Sign.X);
		table[7][7] = new Sign(Sign.X);
	}

	private void testVertical() {
		// feltölteni több kül mintaval
		table[9][0] = new Sign(Sign.O);
		table[8][1] = new Sign(Sign.O);
		table[7][2] = new Sign(Sign.O);
		table[6][3] = new Sign(Sign.O);
		table[5][4] = new Sign(Sign.X);
		table[4][5] = new Sign(Sign.X);
		table[3][6] = new Sign(Sign.O);
		table[2][7] = new Sign(Sign.O);
		table[1][8] = new Sign(Sign.O);
		table[0][9] = new Sign(Sign.O);
	}*/

	public String getWinner() {
		return winner;
	}

	private void setWinner(String winner) {
		this.winner = winner;
	}

}
