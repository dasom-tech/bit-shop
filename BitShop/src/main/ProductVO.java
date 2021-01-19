package main;

public class ProductVO {
	private Integer PNO;
	private String PNAME;
	private Integer PRICE;
	private String PINFO;
	private Integer PSTOCK;
	
	public ProductVO() {}

	public ProductVO(Integer pNO, String pNAME, Integer pRICE, String pINFO, Integer pstock) {
		super();
		PNO = pNO;
		PNAME = pNAME;
		PRICE = pRICE;
		PINFO = pINFO;
		PSTOCK = pstock;
	}

	public Integer getPSTOCK() {
		return PSTOCK;
	}

	public void setPSTOCK(Integer pSTOCK) {
		PSTOCK = pSTOCK;
	}

	public Integer getPNO() {
		return PNO;
	}

	public void setPNO(Integer pNO) {
		PNO = pNO;
	}

	public String getPNAME() {
		return PNAME;
	}

	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}

	public Integer getPRICE() {
		return PRICE;
	}

	public void setPRICE(Integer pRICE) {
		PRICE = pRICE;
	}

	public String getPINFO() {
		return PINFO;
	}

	public void setPINFO(String pINFO) {
		PINFO = pINFO;
	}

	@Override
	public String toString() {
		return "PNO=" + PNO 
		+ "\n" + "PNAME :" + PNAME
		+ "\n" + "PRICE :" + PRICE
		+ "\n" + "PINFO :" + PINFO;
	}
	
	

}
