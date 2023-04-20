package com.sangsang.menu.controller;

import static com.sangsang.account.model.Account.Entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.controller.AccountDaoImpl.SqlMember;
import com.sangsang.account.model.Account;
import com.sangsang.account.model.AccountLocalUser;
import com.sangsang.ojdbc.OracleDbConnectionTool;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl instance;

	private EmployeeDaoImpl() {

	}

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {

			instance = new EmployeeDaoImpl();

		}

		return instance;
	}

	private List<Account> acclist;

	public interface SqlMember {

		public static final String SqlRead = String.format("SELECT * FROM %s ORDER BY %s", TBL_NAME, COL_POSITIONNO);
		public static final String SqlReadEach = String.format(
				"SELECT * FROM %s WHERE LOWER(%s) LIKE LOWER(?) OR LOWER(%s) LIKE LOWER(?) OR LOWER(%s) LIKE LOWER(?)",
				TBL_NAME, COL_NAME, COL_DEPTNAME, COL_EMPNO);
//		public static final String SqlWrite = String.format( // 생성은 계정 생성으로 진행하기 때문에 생략
//				"INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
//				TBL_NAME, COL_ID, COL_PW, COL_NAME, COL_PHONE, COL_EMAIL, COL_DEPTNO, COL_DEPTNAME, COL_ISADMIN);
		public static final String SqlDelete = String.format("DELETE FROM %s WHERE ID = ?", TBL_NAME);
		public static final String SqlModifyAdmin = String.format(
				"UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s=? WHERE %s = ?",
				TBL_NAME, COL_PW, COL_NAME, COL_PHONE, COL_EMAIL, COL_DEPTNO, COL_POSITIONNO, COL_EMPNO, COL_DEPTNAME,
				COL_POSITIONNAME, COL_ID);
		public static final String SqlModifyLocal = String.format(
				"UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?", TBL_NAME, COL_PW, COL_NAME, COL_PHONE,
				COL_EMAIL, COL_ID);
	}

	@Override
	public int modifiedAccount(Account account) {
		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlModifyAdmin);
			stmt.setString(1, account.getPw());
			stmt.setString(2, account.getName());
			stmt.setString(3, account.getPhone());
			stmt.setString(4, account.getEmail());
			stmt.setInt(5, account.getDeptNo());
			stmt.setInt(6, account.getPositionNo());
			stmt.setString(7, account.getEmpNo());
			stmt.setString(8, account.getDeptName());
			stmt.setString(9, account.getPositionName());
			stmt.setString(10, account.getId());

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
	};

	@Override
	public int deleteAccount(Account account) {
		// TODO Auto-generated method stub
		int result = dao.deleteAcc(account);

		return result;
	}

	@Override
	public List<Account> read() {
		// TODO Auto-generated method stub
		acclist = dao.read();

		return acclist;
	}

	public Account SearchOne(String id) {

		acclist = dao.read();
		for (int i = 0; i < acclist.size(); i++) {
			String chId = acclist.get(i).getId();
			if (id.equals(chId) || !id.equals("admin")) {

				return acclist.get(i);

			}

		}

		return new AccountLocalUser();

	}

	@Override
	public List<Account> read(String search) {
		// TODO Auto-generated method stub
		acclist = dao.read();
		List<Account> acclistResult = new ArrayList<>();

		for (int i = 0; i < acclist.size(); i++) {
			String name = acclist.get(i).getName();

			String deptName = acclist.get(i).getDeptName();
			if (deptName == null) {
				deptName = "부서 없음";
			}
			String empno = acclist.get(i).getEmpNo();
			if(empno == null) {
				empno = "사원번호 없음";
			}
			if (name.contains(search) || deptName.contains(search) || empno.contains(search)) {
				acclistResult.add(acclist.get(i));
			}

		}

		return acclistResult;
	}

}
