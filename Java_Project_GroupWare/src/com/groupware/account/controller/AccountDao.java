package com.groupware.account.controller;

import com.groupware.account.model.AccountModel;

public interface AccountDao {
	// InterFace
	public int checkSignInId(String id); // 회원 가입 시 중복 ID 체크
	
	public int checkSignInPw(String pw); // 회원 가입 시 비밀번호 무결성 체크
	
	public int checkSignInPhone(String pw); // 휴대폰 번호 형식 확인 (XXX-XXXX-XXXX);
	
	public int checkSignInEmail(String email); // 이메일 형식 확인
	
	public int loginCheck(); // 아이디, 비밀번호 일치 여부 확인
	
	public int addAccountToDb(AccountModel account); // 회원가입에 입력한 데이터를 DB로 전달
	
	public void makeNewAccount(AccountModel account); // 아이디 생성하기
	
	
}
