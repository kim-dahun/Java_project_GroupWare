package com.sangsang.menu.controller;

import java.util.List;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;

public interface EmployeeDao {

	// Payment와 Account DB를 조인해서 만드는 DB Table();
	AccountDaoImpl dao = AccountDaoImpl.getInstance();
	
	List<Account> acclist = dao.read();
	
	int modifiedAccount(Account account);
	
	int deleteAccount(Account account);
	
	List<Account> read();
	
	List<Account> read(String search);
	
	
}
