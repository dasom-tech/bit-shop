package main;

public class IDVO {
	private String ID;
	private String PW;
	private String NA;
	private String EM;
	private String ADDR;
	private String PHONE;
	private Integer MONEY;
	private Integer POINT;
	
	public IDVO() {}
	
	public IDVO(String id, String pw, String na, String em, String phone, String addr) {
		super();
		this.ID = id;
		this.PW = pw;
		this.NA = na;
		this.EM = em;
		this.PHONE = phone;
		this.ADDR = addr;
	}

	public IDVO(String id, String pw, String na, String em, String phone, String addr,
			Integer point) {
		super();
		this.ID = id;
		this.PW = pw;
		this.NA = na;
		this.EM = em;
		this.PHONE = phone;
		this.ADDR = addr;
		this.POINT = point;
	}
	
	public IDVO(String id, Integer money) {
		super();
		this.ID = id;
		this.MONEY = money;
	}

	public String getADDR() {
		return ADDR;
	}

	public void setADDR(String addr) {
		ADDR = addr;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String phone) {
		PHONE = phone;
	}

	public Integer getMONEY() {
		return MONEY;
	}

	public void setMONEY(Integer money) {
		MONEY = money;
	}

	public Integer getPOINT() {
		return POINT;
	}

	public void setPOINT(Integer point) {
		POINT = point;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pw) {
		PW = pw;
	}

	public String getNA() {
		return NA;
	}

	public void setNA(String na) {
		NA = na;
	}

	public String getEM() {
		return EM;
	}

	public void setEM(String em) {
		EM = em;
	}
	
	@Override
	public String toString() {
		return "ID : " + ID 
		+ "\n" + "PW :" + PW 
		+ "\n" + "NA : " + NA 
		+ "\n" + "EM" + EM
		+ "\n" + "PHONE" + PHONE
		+ "\n" + "ADDR" + ADDR
		+ "\n" + "MONEY" + MONEY
		+ "\n" + "POINT" + POINT;
	}
}
