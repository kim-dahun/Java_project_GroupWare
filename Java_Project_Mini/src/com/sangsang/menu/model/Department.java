package com.sangsang.menu.model;

public class Department {

	public static interface Entity{
		
		public static final String TBL_NAME = "deptlist";
		public static final String COL_DEPTNO = "deptno";
		public static final String COL_DEPTNAME = "deptname";
		public static final String COL_DEPTREF = "deptref";
		
	}
	
	private int deptno;
	private String deptName;
	private int deptref;
	
	public Department() {
		
	}
	
	public Department(int deptno, String deptName, int deptref) {
		super();
		this.deptno = deptno;
		this.deptName = deptName;
		this.deptref = deptref;
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

	public int getDeptref() {
		return deptref;
	}

	public void setDeptref(int deptref) {
		this.deptref = deptref;
	}
	
	
	
}
