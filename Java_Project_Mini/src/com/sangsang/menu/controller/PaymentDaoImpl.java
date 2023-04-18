package com.sangsang.menu.controller;

import java.util.List;

import com.sangsang.account.model.Account;
import com.sangsang.menu.model.PaymentContent;
import static com.sangsang.account.model.Account.Entity.*;
import static com.sangsang.menu.model.PaymentContent.Entity.*;

public class PaymentDaoImpl implements PaymentDao {

	public static final String TBL_NAME = "PAYMENTCONTENT";
	
	public interface sqlPayment{
		
		// 조인한 테이블에서 PaymentList 관련 모든 데이터 불러오기
		public static final String SQLREADPAYMENT = String.format("SELECT * FROM %s , %s WHERE %s = %s",TBL_NAME,Account.Entity.TBL_NAME,Account.Entity.COL_EMPNO,PaymentContent.Entity.COL_EMPNO);
		
		
		
	}
	
	
	// 싱글톤
	private static PaymentDaoImpl instance;

	private PaymentDaoImpl() {

	}

	public static PaymentDaoImpl getInstance() {
		if (instance == null) {

			instance = new PaymentDaoImpl();

		}

		return instance;
	}
	
	
	private List<PaymentContent> paylist;
	
	
	@Override
	public PaymentContent read(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentContent> read(int[] Selectedempnos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentContent> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int writePay(PaymentContent payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePay(PaymentContent payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifiedPay(PaymentContent payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int printPay(List<PaymentContent> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
