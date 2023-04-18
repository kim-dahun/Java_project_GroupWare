package com.sangsang.menu.model;

public class Position {

	public interface Entity {

		public static final String TBL_NAME = "position";
		public static final String COL_POSNAME = "posName";
		public static final String COL_POSNO = "posno";

	}
	
	private int PosNo;
	private String PosName;
	
	public Position() {
		
	}

	public Position(int posNo, String posName) {
		super();
		PosNo = posNo;
		PosName = posName;
	}

	public int getPosNo() {
		return PosNo;
	}

	public void setPosNo(int posNo) {
		PosNo = posNo;
	}

	public String getPosName() {
		return PosName;
	}

	public void setPosName(String posName) {
		PosName = posName;
	}
	
	
	
}
