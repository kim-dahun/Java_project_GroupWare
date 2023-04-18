package com.sangsang.menu.model;

public class Department {

	public static interface Entity{
		
		public static final String TBL_NAME = "deptlist";
		public static final String COL_DEPTNO = "deptno";
		public static final String COL_DEPTNAME = "deptname";
		public static final String COL_DEPTNODENO = "deptnodeno";
		
	}
	
	private int deptno;
	private String deptName;
	private int deptNodeNo;
	
	public Department() {
		
	}
	
	public Department(int deptno, String deptName, int deptNodeno) {
		super();
		this.deptno = deptno;
		this.deptName = deptName;
		this.deptNodeNo = deptNodeno;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptNodeNo() {
		return deptNodeNo;
	}

	public void setDeptNodeNo(int deptNodeNo) {
		this.deptNodeNo = deptNodeNo;
	}
	
	
	
}
