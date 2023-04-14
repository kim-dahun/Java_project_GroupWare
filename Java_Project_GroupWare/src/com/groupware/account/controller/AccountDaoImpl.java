package com.groupware.account.controller;

import java.util.ArrayList;
import java.util.List;

import com.groupware.account.model.AccountModel;

public class AccountDaoImpl implements AccountDao {
	// 구현 클래스
	private List<AccountModel> accountList;
	private static AccountDaoImpl accDao = null;

	private AccountDaoImpl() {

	}

	public static AccountDaoImpl getInstance() {

		if (accDao == null) {
			accDao = new AccountDaoImpl();
		}

		return accDao;
	}

	@Override
	public int checkSignInId(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkSignInPw(String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkSignInPhone(String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkSignInEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int loginCheck() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAccountToDb(AccountModel account) {
		// TODO Auto-generated method stub
		// 계정 받아서 DB로 넘기는 메서드 ( 아직 안 배운 부분이라 틀만 잡음 )
		// 사용자 -> 데이터입력 -> UI가 받음 -> DaoImpl에서 데이터를 받음 -> 저장하는 메서드로 전달.
		// 반드시 나누어야 하는 것은 아님. MVC라는 패턴을 구현하기 위해 분리하는 것 뿐.
		// 분리하지 않고 데이터를 받아 DB나 LIST에 저장하는 것을 한 번에 구현하더라도 문제가 생기지는 X
		if (account == null) {
			return 0;
		}

		return 1;
	}

	@Override
	public void makeNewAccount(AccountModel account) {
		// TODO Auto-generated method stub
		// UI로부터 계정에 관련된 데이터를 전달받고, 그 전달받은 데이터를 실제 계정으로 등록하는 메서드
		// 받은 데이터가 DB로 전달하는 메서드와 프로그램 내에서 저장할 List로 전달
		
		accountList.add(account);
		getInstance().addAccountToDb(account);

	}

	public List<AccountModel> getList() {

		if (accountList == null) {
			accountList = new ArrayList<>();
		}

		return accountList;
	}

	

}
