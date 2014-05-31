package model;

public class Table {
	
	private Sign[][] table;
	private int tableSize;
	private int lengthToWin;
	
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
	
	private Table(Sign[][] table, int tableSize, int lengthToWin){
		this.table = table;
		this.tableSize=tableSize;
		this.lengthToWin=lengthToWin;
	}

	public Sign[][] getTable() {
		return table;
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getLengthToWin() {
		return lengthToWin;
	}

	public void setSign(int x, int y, Sign s){
		table[x][y]=s;
	}
	
	public Sign getSign(int x, int y) {
		return table[x][y];
	}

	public Sign getSign(Point p) {
		return getSign(p.getX(), p.getY());
	}
	
	public boolean isValidStep(int x, int y) {
		return table[x][y].getSign().equals(Sign.EMPTY);
	}

	public boolean isValidStep(Point nextStep) {
		return isValidStep(nextStep.getX(), nextStep.getY());
	}

	public Table getCopy() {
		return new Table(this.table, this.tableSize, this.lengthToWin);
	}
	

}
