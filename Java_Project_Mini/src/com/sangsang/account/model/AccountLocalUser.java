package com.sangsang.account.model;

public class AccountLocalUser extends Account {

	public AccountLocalUser() {
		super();
		isAdmin = isAdmin();
		
	}
	
	
	
	public AccountLocalUser(int eid, String id, String pw, String name, String phone, String email, int deptNo,
			String deptName, String empNo, int positionNo, String positionName) {
		super(eid, id, pw, name, phone, email, deptNo, deptName, empNo, positionNo, positionName);
		// TODO Auto-generated constructor stub
	}



	public int isAdmin() {
		
		return isAdmin = 0;
		
	}
	
}
