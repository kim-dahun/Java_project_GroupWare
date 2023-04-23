package com.sangsang.menu.controller;

import java.awt.Component;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.controller.PaymentDaoImpl.FileBackup;
import com.sangsang.menu.model.PaymentContent;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import static com.sangsang.account.model.Account.Entity.*;
import static com.sangsang.menu.model.PaymentContent.Entity.*;

public class PaymentDaoImpl implements PaymentDao {

	public static final String TBL_NAME = "PAYMENTCONTENT";

	public interface FileBackup {

		public static final String FILEROOT = String.format("BackUpD/%s.csv", getNow());
		public static final String FOLDER = "./BackUpD";
		
		public static int FolderMaker() {
			int result = 0;
			
			File file = new File(FOLDER);
			
			if(!file.exists()) {
				System.out.println("이미");
				file.mkdir();
			} else {
				
				System.out.println("성공");
				
			}
			
			return result;
		}
		
		
		public static int FileBackUp() {
			
			int result = 0;

			File file = new File(FILEROOT);

			FileOutputStream fos = null;
			
			OutputStreamWriter wr = null;

			try {
				fos = new FileOutputStream(file);
				wr = new OutputStreamWriter(fos, "UTF-8");

				List<PaymentContent> pl = PaymentDaoImpl.getInstance().read();
				String[] str = new String[pl.size()];
				String fileResult = "";

				for (int i = 0; i < str.length; i++) {

					if (i == 0) {
						fileResult = "사번,이름,직급,부서,귀속연월,기본급,상여,연장수당,식대,차량유지비,퇴직금,소득세,지방소득세,건강보험,국민연금,고용보험,기타공제,기타공제사유\n";
						
					}

					str[i] = pl.get(i).getEmpno() + "," + pl.get(i).getName() + "," + pl.get(i).getPosName() + ","
							+ pl.get(i).getDeptName() + "," + pl.get(i).getPaymentMonth() + "," + pl.get(i).getBaseSal()
							+ "," + pl.get(i).getBonus() + "," + pl.get(i).getOvertimePay() + "," + pl.get(i).getMeals()
							+ "," + pl.get(i).getVehicleMaintenance() + "," + pl.get(i).getSeverancePay() + ","
							+ pl.get(i).getIncomeTax() + "," + pl.get(i).getResidentTax() + ","
							+ pl.get(i).getHealthInsurancePremium() + "," + pl.get(i).getNationalPension() + ","
							+ pl.get(i).getEmploymentInsurance() + "," + pl.get(i).getOtherDeductions() + ","
							+ pl.get(i).getDeductionsReason() + "\n";

					fileResult = fileResult+str[i];

				}
				
				wr.write(fileResult);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return result;
			} finally {

				try {
					wr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			result = 1;
			return result;

		}

		private static String getNow() {

			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
			String text = date.format(formatter);

			return text;

		}

	}

	public interface sqlPayment {

		// 조인한 테이블에서 PaymentList 관련 모든 데이터 불러오기
		public static final String SQLREADPAYMENT = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s ORDER BY %s DESC, %s DESC", TBL_NAME, Account.Entity.TBL_NAME,
				Account.Entity.TBL_NAME + "." + Account.Entity.COL_EMPNO,
				PaymentContent.Entity.TBL_NAME + "." + PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH, COL_POSITIONNO);

		public static final String SQLWRITEPAYMENT = String.format(
				"INSERT INTO %s (%s,%s,%s,%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				TBL_NAME, PaymentContent.Entity.COL_NAME, PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH, COL_BASESAL,
				COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY, COL_INCOMETAX,
				COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION, COL_EMPLOYMENTINSURANCE,
				COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON);

		public static final String SQLUPDATEPAYMENT = String.format(
				"UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=? AND %s=?",
				TBL_NAME, COL_BASESAL, COL_BONUS, COL_OVERTIMEPAY, COL_MEALS, COL_VEHICLEMAINTENANCE, COL_SEVERANCEPAY,
				COL_INCOMETAX, COL_RESIDENTTAX, COL_HEALTHINSURANCEPREMIUM, COL_NATIONALPENSION,
				COL_EMPLOYMENTINSURANCE, COL_OTHERDEDUCTIONS, COL_DEDUCTIONSREASON,
				com.sangsang.menu.model.PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH);

		public static final String SQLDELETEPAYMENT = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", TBL_NAME,
				PaymentContent.Entity.COL_EMPNO, COL_PAYMONTH);

		public static final String SQLREADNAMEPAYMENT = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME + "." + PaymentContent.Entity.COL_EMPNO,
				Account.Entity.TBL_NAME + "." + Account.Entity.COL_EMPNO,
				TBL_NAME + "." + PaymentContent.Entity.COL_NAME, COL_PAYMONTH);

		public static final String SQLREADDEPTPAYMENT = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME + "." + PaymentContent.Entity.COL_EMPNO,
				Account.Entity.TBL_NAME + "." + Account.Entity.COL_EMPNO, Account.Entity.COL_DEPTNAME, COL_PAYMONTH);

		public static final String SQLREADPAYMONTH = String.format(
				"SELECT * FROM %s JOIN %s ON %s = %s AND LOWER(%s) LIKE LOWER(?) ORDER BY %s DESC", TBL_NAME,
				Account.Entity.TBL_NAME, TBL_NAME + "." + PaymentContent.Entity.COL_EMPNO,
				Account.Entity.TBL_NAME + "." + Account.Entity.COL_EMPNO, PaymentContent.Entity.COL_PAYMONTH,
				COL_PAYMONTH);

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
			if (check == null || check.isBlank() || check.isEmpty()) {
				payemplist[i] = "사원번호 없음";
			} else {
				payemplist[i] = acclist.get(i).getEmpNo();
			}
		}

		return payemplist;
	}
	
	public String[] readPaymentMonth() {
		paylist = read();
		Set<String> set = new HashSet<>(); 
		for(int i = 0 ; i<paylist.size();i++) {
			
			String check = paylist.get(i).getPaymentMonth();
			if (check == null || check.isBlank() || check.isEmpty()) {
				set.add("오류");
			} else {
				set.add(paylist.get(i).getPaymentMonth());
			}
			
		}
		
		
		String[] payMonthlist = new String[set.size()];
		int i = 0;
		
		for(String x : set) {
			
			payMonthlist[i]=x;
			i++;
			
		}

		return payMonthlist;
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
					String posname = rs.getString(COL_POSITIONNAME);
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
					String posname = rs.getString(COL_POSITIONNAME);
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
					String posname = rs.getString(COL_POSITIONNAME);
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
				String posname = rs.getString(COL_POSITIONNAME);
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

	//@Override
	//public int printPay(List<PaymentContent> list, Component printcom) {
		// PRINT 메소드
		
		
		
	//	return 0;
	//}
	
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

		if (result < 1800000) {
			return 0;
		}

		result = (int) ((result - 1800000) * (18 / 100.0));

		return result;
	}

	public int CalResiTax(PaymentContent pay) {
		int result = 0;

		result = CalTax(pay) / 10;

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

	public PaymentContent search(String s, String a) {
		paylist = read();
		for (int i = 0; i < paylist.size(); i++) {

			if (s.equals(paylist.get(i).getEmpno())
					&& a.equals(paylist.get(i).getPaymentMonth())) {

				
				return paylist.get(i);
				

			}

		}
		
		return new PaymentContent();
		
	}

	
	
	
}
