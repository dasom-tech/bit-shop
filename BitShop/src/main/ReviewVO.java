package main;

public class ReviewVO {
	private String ID;
	private Integer PNO;
	private Integer AMOUNT;
	
	public ReviewVO() {}

	public ReviewVO(String iD, Integer pNO, Integer amount) {
		super();
		ID = iD;
		PNO = pNO;
		AMOUNT = amount;
	}

	public Integer getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getPNO() {
		return PNO;
	}

	public void setPNO(Integer pNO) {
		PNO = pNO;
	}

	@Override
	public String toString() {
		return "ID : " + ID
		+ "\n" + "PNO :" + PNO;
	}
	
	

}
