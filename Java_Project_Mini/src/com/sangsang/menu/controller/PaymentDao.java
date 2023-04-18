package com.sangsang.menu.controller;

import java.util.List;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.model.PaymentContent;

public interface PaymentDao {

	AccountDaoImpl dao = AccountDaoImpl.getInstance();
	
	
	List<PaymentContent> read(String search, int index);
	
	
	
	List<PaymentContent> read(); // 모든 데이터를 가진 급여대장 리스트
	
	int writePay(PaymentContent payment); // 급여 대장에 급여기록 입력
	
	int deletePay(PaymentContent payment); // 선택한 급여기록 삭제
	
	int modifiedPay(PaymentContent payment); // 선택한 급여기록 수정
	
	int printPay(List<PaymentContent> list);
	
}
