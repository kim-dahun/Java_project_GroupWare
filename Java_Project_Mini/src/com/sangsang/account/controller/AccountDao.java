package com.sangsang.account.controller;

import java.awt.Component;
import java.util.List;

import com.sangsang.account.model.Account;

public interface AccountDao {

	Account read(String search);
	
	List<Account> read(); // 관리자 ID 포지션에서 전체 사원 리스트 조회 기능 사용 시
	
	int writeAcc(Account account); // N 개 계정을 생성했다 등의 메시지 활용. DB의 GUI화
	
	int deleteAcc(Account account); // 관리자 계정이나 혹은 사용자 계정 탈퇴
	
	int ModifiedAcc(Account account); // 관리자가 사번이나 직급을 부여할 때 사용
	
	String findId(String name, String empno); // ID를 확인함
	
	String findPw(String id, String email); // 비밀번호를 확인함.
	
	
	
	Account checkLogin(String id, String pw); // id 비번 받아서 인수 전달.
	
	
	
	
	
	
	
	// 이전 프레임의 X,Y 값을 불러오는 static 메서드
	public static int getFrameX(Component component) {
		int x = component.getX();
		
		if(x==0) {
			x=100;
		}
		
		
		return x;
	};
	
	public static int getFrameY(Component component) {
		int y = component.getY();
		
		if(y==0) {
			y=100;
		}
		
		
		return y;
	};
	
}
