package com.groupware.account.model;

public class AdminAccountModel extends AccountModel {

	// 관리자 계정은 계정정보 변경 불가 고정값으로 지정하기 위해 상수 사용.
	// static 지정은 프로그램 시작 시 인스턴스 생성 없이 바로 객체 생성하기 위해 사용.
	private static final boolean ADMIN = true;
	private static final String ID = "admin";
	private static final String PASSWORD = "admin2023!";
	private static final int AGE = 20;
	private static final String PHONE = "010-1234-5678";
	private static final String EMAIL = "admin@sangsang.com";
	private static final int DEPARTMENTCODE = 10;
	private static final int EMPLOYMENTNUMBER = 2000-01;
	
	public static void callAdmin() {
		
		// 계정 DB 맨 밑에 추가하는 메서드 호출
		
	}
	
}
