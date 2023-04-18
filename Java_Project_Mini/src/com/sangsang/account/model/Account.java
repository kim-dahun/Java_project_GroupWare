package com.sangsang.account.model;

public abstract class Account {
	
	public interface Entity{
		
		public static final String TBL_NAME = "account";
		public static final String COL_EID = "EID";
		public static final String COL_ID = "ID";
		public static final String COL_PW = "Password";
		public static final String COL_NAME = "name";
		public static final String COL_PHONE = "phone";
		public static final String COL_EMAIL = "email";
		public static final String COL_DEPTNO = "DEPT_NO";
		public static final String COL_DEPTNAME = "DEPT_NAME";
		public static final String COL_EMPNO = "EMP_NO";
		public static final String COL_POSITIONNO="POS_NO";
		public static final String COL_POSTIONNAME="POS_NAME";
		public static final String COL_ISADMIN = "ISADMIN";
	}
	

	private int eid;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private int deptNo;
	private String deptName;
	private String empNo;
	private int positionNo;
	private String positionName;
	protected int isAdmin;

	public Account() {

	}

	public Account(int eid, String id, String pw, String name, String phone, String email, int deptNo, String deptName,
			String empNo, int positionNo, String positionName) {
		super();
		this.eid = eid;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empNo = empNo;
		this.positionNo = positionNo;
		this.positionName = positionName;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public int getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	
	
	
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("유저-%s 사번-%s 부서-%s, 직급-%s", name, empNo , deptName, positionName);
		
	}

}
