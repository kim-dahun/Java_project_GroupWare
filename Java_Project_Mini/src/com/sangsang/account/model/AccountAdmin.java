package com.sangsang.account.model;

public class AccountAdmin extends Account {

	public AccountAdmin() {
		
		super(0,"admin","admin","admin","010-1234-5678","admin@sangsang.com",10,"Management Support dept","1988-001",99,"관리자");
		isAdmin = isAdmin();
	}
	
	
	public int isAdmin() {
		
		return isAdmin = 1;
		
	}
	
	
}
