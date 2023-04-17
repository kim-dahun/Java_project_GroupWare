package com.sangsang.account.model;

public class AccountAdmin extends Account {

	public AccountAdmin() {
		
		super(0,"admin","admin01234","admin","010-1234-5678","admin@sangsang.com",10,"관리부","1988-001",99,"관리자");
		isAdmin = isAdmin();
	}
	
	
	public int isAdmin() {
		
		return isAdmin = 1;
		
	}
	
	
}
