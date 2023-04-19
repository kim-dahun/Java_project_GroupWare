package com.sangsang.menu.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.model.PaymentContent;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import static com.sangsang.account.model.Account.Entity.*;
import static com.sangsang.menu.model.PaymentContent.Entity.*;

public class PaymentDaoImpl implements PaymentDao {

	public static final String TBL_NAME = "PAYMENTCONTENT";

	public interface sqlPayment {

		// 조인한 테이블에서 PaymentList 관련 모든 데이터 불러오기
		public static final String SQLREADPAYMENT = String.format("SELECT * FROM %s JOIN %s ON %s = %s ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, Account.Entity.TBL_NAME+"."+ Account.Entity.COL_EMPNO, PaymentContent.Entity.TBL_NAME+"."+
				PaymentContent.Entity.COL_EMPNO,COL_PAYMONTH);
		
		public static final String SQLWRITEPAYMENT = String.format(
				"INSERT INTO %s (%s,%s,%s,%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				TBL_NAME, PaymentContent.Entity.COL_NAME, PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH, COL_BASESAL,
				COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY, COL_INCOMETAX,
				COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION, COL_EMPLOYMENTINSURANCE,
				COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON);
		
		public static final String SQLUPDATEPAYMENT = String.format(
				"UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=? AND %s=?", TBL_NAME,
				COL_BASESAL, COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY,
				COL_INCOMETAX, COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION,
				COL_EMPLOYMENTINSURANCE, COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON,
				com.sangsang.menu.model.PaymentContent.Entity.COL_EMPNO,COL_PAYMONTH);
		
		public static final String SQLDELETEPAYMENT = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", TBL_NAME,
				PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH);
		
		public static final String SQLREADNAMEPAYMENT = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME +"."+ PaymentContent.Entity.COL_EMPNO, Account.Entity.TBL_NAME+"."+
				Account.Entity.COL_EMPNO, TBL_NAME + "."+PaymentContent.Entity.COL_NAME,COL_PAYMONTH);
		
		public static final String SQLREADDEPTPAYMENT = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME +"."+ PaymentContent.Entity.COL_EMPNO, Account.Entity.TBL_NAME+"."+
				Account.Entity.COL_EMPNO, Account.Entity.COL_DEPTNAME,COL_PAYMONTH);
		
		public static final String SQLREADPAYMONTH = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME +"."+ PaymentContent.Entity.COL_EMPNO, Account.Entity.TBL_NAME+"."+
				Account.Entity.COL_EMPNO, PaymentContent.Entity.COL_PAYMONTH,COL_PAYMONTH);

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
	private List<Account> acclist;

	public String[] readEmpno() {
		acclist = AccountDaoImpl.getInstance().read();
		String[] payemplist = new String[acclist.size()];

		for (int i = 0; i < payemplist.length; i++) {
			String check = acclist.get(i).getEmpNo();
			if(check == null || check.isBlank() || check.isEmpty()) {
			payemplist[i]="사원번호 없음";
			} else {
			payemplist[i] = acclist.get(i).getEmpNo();
			}
		}

		return payemplist;
	}

	@Override
	public List<PaymentContent> read(String search, int index) {
		// TODO Auto-generated method stub
		paylist = new ArrayList<>();
		switch (index) {

		case 0: {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = OracleDbConnectionTool.getConnection();
				stmt = conn.prepareStatement(sqlPayment.SQLREADNAMEPAYMENT);

				String src = "%" + search + "%";
				stmt.setString(1, src);
				rs = stmt.executeQuery();

				while (rs.next()) {
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
					String paymonth = rs.getString(COL_PAYMONTH);
					
					
					PaymentContent pmc = new PaymentContent(name, empno, posname, deptname, basesal, bonus, overtimepay,
							meals, vehiclemt, severancepay, incometax, residenttax, healthinsurance, nationalInsurance,
							empInsurance, otherDeductions, deductionReason, paymonth);

					paylist.add(pmc);

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

		case 1: {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = OracleDbConnectionTool.getConnection();
				stmt = conn.prepareStatement(sqlPayment.SQLREADDEPTPAYMENT);

				String src = "%" + search + "%";
				stmt.setString(1, src);
				rs = stmt.executeQuery();

				while (rs.next()) {
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
					String paymonth = rs.getString(COL_PAYMONTH);
					
					
					PaymentContent pmc = new PaymentContent(name, empno, posname, deptname, basesal, bonus, overtimepay,
							meals, vehiclemt, severancepay, incometax, residenttax, healthinsurance, nationalInsurance,
							empInsurance, otherDeductions, deductionReason, paymonth);

					paylist.add(pmc);

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

		case 2: {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = OracleDbConnectionTool.getConnection();
				stmt = conn.prepareStatement(sqlPayment.SQLREADPAYMONTH);

				String src = "%" + search + "%";
				stmt.setString(1, src);
				rs = stmt.executeQuery();

				while (rs.next()) {
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
					String paymonth = rs.getString(COL_PAYMONTH);
					
					
					PaymentContent pmc = new PaymentContent(name, empno, posname, deptname, basesal, bonus, overtimepay,
							meals, vehiclemt, severancepay, incometax, residenttax, healthinsurance, nationalInsurance,
							empInsurance, otherDeductions, deductionReason, paymonth);

					paylist.add(pmc);

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
			while (rs.next()) {
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
				String paymonth = rs.getString(COL_PAYMONTH);
				
				
				PaymentContent pmc = new PaymentContent(name, empno, posname, deptname, basesal, bonus, overtimepay,
						meals, vehiclemt, severancepay, incometax, residenttax, healthinsurance, nationalInsurance,
						empInsurance, otherDeductions, deductionReason, paymonth);

				paylist.add(pmc);

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
			

			stmt.setString(1, payment.getName());
			stmt.setString(2, payment.getEmpno());
			stmt.setString(3, payment.getPaymentMonth());
			stmt.setInt(4, payment.getBaseSal());
			stmt.setInt(5, payment.getBonus());
			stmt.setInt(6, payment.getOvertimePay());
			stmt.setInt(7, payment.getMeals());
			stmt.setInt(8, payment.getVehicleMaintenance());
			stmt.setInt(9, payment.getSeverancePay());
			stmt.setInt(10, payment.getIncomeTax());
			stmt.setInt(11, payment.getResidentTax());
			stmt.setInt(12, payment.getHealthInsurancePremium());
			stmt.setInt(13, payment.getNationalPension());
			stmt.setInt(14, payment.getEmploymentInsurance());
			stmt.setInt(15, payment.getOtherDeductions());
			stmt.setString(16, payment.getDeductionsReason());

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
			stmt.setString(1, payment.getEmpno());
			stmt.setString(2, payment.getPaymentMonth());

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
			stmt.setInt(1, payment.getBaseSal());
			stmt.setInt(2, payment.getBonus());
			stmt.setInt(3, payment.getOvertimePay());
			stmt.setInt(4, payment.getMeals());
			stmt.setInt(5, payment.getVehicleMaintenance());
			stmt.setInt(6, payment.getSeverancePay());
			stmt.setInt(7, payment.getIncomeTax());
			stmt.setInt(8, payment.getResidentTax());
			stmt.setInt(9, payment.getHealthInsurancePremium());
			stmt.setInt(10, payment.getNationalPension());
			stmt.setInt(11, payment.getEmploymentInsurance());
			stmt.setInt(12, payment.getOtherDeductions());
			stmt.setString(13, payment.getDeductionsReason());
			stmt.setString(14, payment.getEmpno());
			stmt.setString(15, payment.getPaymentMonth());

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
		// PRINT 메소드
		return 0;
	}

	
	
	public PaymentContent getPaymentContent(String empno) {

		paylist = read();

		for (int i = 0; i < paylist.size(); i++) {

			if (empno.equals(paylist.get(i).getEmpno())) {

				return paylist.get(i);
			}

		}

		return null;

	}

	public int[] taxArray(PaymentContent pay) {
		int[] taxArray = { CalTax(pay), CalResiTax(pay), CalHealIns(pay), CalNationIns(pay), CalempIns(pay) };

		return taxArray;
	}

	public int CalTax(PaymentContent pay) {
		int result = 0;

		result = pay.getBaseSal() + pay.getBonus() + pay.getOvertimePay();

		if(result<1800000) {
			return 0;
		}
		
		result = (int) ((result-1800000) * (18 / 100.0));

		return result;
	}

	public int CalResiTax(PaymentContent pay) {
		int result = 0;

		result = CalTax(pay)/10;

		return result;
	}

	public int CalHealIns(PaymentContent pay) {
		int result = 0;

		result = pay.getBaseSal() + pay.getBonus() + pay.getOvertimePay();

		result = (int) (result * (35 / 1000.0));

		return result;
	}

	public int CalNationIns(PaymentContent pay) {
		int result = 0;
		result = pay.getBaseSal() + pay.getBonus() + pay.getOvertimePay();

		result = (int) (result * (45 / 1000.0));
		return result;
	}

	public int CalempIns(PaymentContent pay) {
		int result = 0;
		result = pay.getBaseSal() + pay.getBonus() + pay.getOvertimePay();

		result = (int) (result * (8 / 1000.0));
		return result;
	}

}
