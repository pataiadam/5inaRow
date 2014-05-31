package model;

public class Sign {

	public static final String X = "X";
	public static final String O = "O";
	public static final String EMPTY = "EMPTY";
	
	private String sign;
	
	public Sign(){
		this.sign=EMPTY;
	}
	
	public Sign(String s){
		if(s.equals(X) || s.equals(O))
			this.sign=s;
		else
			this.sign=EMPTY;
	}
	
	public String getSign(){
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign=sign.getSign();
	}
	
	public void setSign(String sign) {
		this.sign=sign;
	}
}
