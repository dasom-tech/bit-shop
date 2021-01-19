package main;

public class SignoutVO {
	private String NAME;
	private String PHONE;
	
	public SignoutVO() {}

	public SignoutVO(String nAME, String pHONE) {
		super();
		NAME = nAME;
		PHONE = pHONE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	@Override
		public String toString() {
    		return "NAME : " + NAME
    		+ "\n" + "PHONE :" + PHONE;
	}

}