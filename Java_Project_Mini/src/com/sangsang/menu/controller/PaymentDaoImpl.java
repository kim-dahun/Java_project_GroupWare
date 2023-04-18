package com.sangsang.menu.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sangsang.account.model.Account;
import com.sangsang.menu.model.PaymentContent;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import static com.sangsang.account.model.Account.Entity.*;
import static com.sangsang.menu.model.PaymentContent.Entity.*;

public class PaymentDaoImpl implements PaymentDao {

	public static final String TBL_NAME = "PAYMENTCONTENT";

	public interface sqlPayment {

		// 조인한 테이블에서 PaymentList 관련 모든 데이터 불러오기
		public static final String SQLREADPAYMENT = String.format("SELECT * FROM %s , %s WHERE %s.%s = %s.%s", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME, Account.Entity.COL_EMPNO, Account.Entity.TBL_NAME,
				PaymentContent.Entity.COL_EMPNO);
		public static final String SQLWRITEPAYMENT = String.format(
				"INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				TBL_NAME, COL_BASESAL, COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY,
				COL_INCOMETAX, COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION,
				COL_EMPLOYMENTINSURANCE, COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON);
		public static final String SQLUPDATEPAYMENT = String.format(
				"UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?", TBL_NAME,
				COL_BASESAL, COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY,
				COL_INCOMETAX, COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION,
				COL_EMPLOYMENTINSURANCE, COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON,
				com.sangsang.menu.model.PaymentContent.Entity.COL_EMPNO);
		public static final String SQLDELETEPAYMENT = String.format("DELETE FROM %s WHERE %s = ?", TBL_NAME,
				PaymentContent.Entity.COL_EMPNO);
		public static final String SQLREADNAMEPAYMENT = String.format(
				"SELECT * FROM %s , %s WHERE %s.%s = %s.%s AND LOWER(%s) LIKE LOWER(?)", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME, Account.Entity.COL_EMPNO, Account.Entity.TBL_NAME,
				PaymentContent.Entity.COL_EMPNO, PaymentContent.Entity.COL_NAME);
		public static final String SQLREADDEPTPAYMENT = String.format(
				"SELECT * FROM %s , %s WHERE %s.%s = %s.%s AND LOWER(%s) LIKE LOWER(?)", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME, Account.Entity.COL_EMPNO, Account.Entity.TBL_NAME,
				PaymentContent.Entity.COL_EMPNO, COL_DEPTNAME);
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
	public List<PaymentContent> read(String search, int index) {
		// TODO Auto-generated method stub
		paylist = new ArrayList<>();
		switch (index) {

		case 0:
		{Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLREADNAMEPAYMENT);

			String name = "%" + search + "%";
			stmt.setString(1, name);
			rs = stmt.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return paylist;
	}
			
		case 1:
		{Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLREADDEPTPAYMENT);

			String name = "%" + search + "%";
			stmt.setString(1, name);
			rs = stmt.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return paylist;
	}
			
			

		}

		return null;
	}

	@Override
	public List<PaymentContent> read() {
		// TODO Auto-generated method stub
		paylist = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLREADPAYMENT);

			rs = stmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String empno = rs.getString("empno");
				String posname = rs.getString(COL_POSTIONNAME);
				String deptname = rs.getString(COL_DEPTNAME);
				int basesal = rs.getInt(COL_BASESAL);
				int bonus = rs.getInt(COL_BONUS);
				int overtimepay = rs.getInt(COL_OVERTIMEPAY);
				int meals = rs.getInt(COL_MEALS);
				int vehiclemt = rs.getInt(COL_VEHICLEMAINTENANCE);
				int severancepay = rs.getInt(COL_SEVERANCEPAY);
				int incometax = rs.getInt(COL_INCOMETAX);
				int residenttax = rs.getInt(COL_RESIDENTTAX);
				int healthinsurance = rs.getInt(COL_HEALTHINSURANCEPREMIUM);
				int nationalInsurance = rs.getInt(COL_NATIONALPENSION);
				int empInsurance = rs.getInt(COL_EMPLOYMENTINSURANCE);
				int otherDeductions = rs.getInt(COL_OTHERDEDUCTIONS);
				String deductionReason = rs.getString(COL_DEDUCTIONSREASON);
				Date paymonth = rs.getDate("paymonth");
				PaymentContent pmc = new PaymentContent(name, empno, posname, deptname, basesal, bonus, overtimepay, meals, vehiclemt, severancepay, incometax, residenttax, healthinsurance, nationalInsurance, empInsurance, otherDeductions, deductionReason, paymonth);
				
						
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return paylist;
	}

	@Override
	public int writePay(PaymentContent payment) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLWRITEPAYMENT);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public int deletePay(PaymentContent payment) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLDELETEPAYMENT);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public int modifiedPay(PaymentContent payment) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(sqlPayment.SQLUPDATEPAYMENT);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				OracleDbConnectionTool.closeConnection(conn, stmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public int printPay(List<PaymentContent> list) {
		// PRINT METHOD
		return 0;
	}

}
