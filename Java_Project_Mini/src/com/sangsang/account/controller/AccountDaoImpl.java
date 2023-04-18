package com.sangsang.account.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static com.sangsang.account.model.Account.Entity.*;
import static com.sangsang.account.model.OrcaleDbLowData.*;
import com.sangsang.account.model.Account;
import com.sangsang.account.model.AccountLocalUser;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import oracle.jdbc.OracleDriver;

public class AccountDaoImpl implements AccountDao {

	private static AccountDaoImpl instance;

	private AccountDaoImpl() {

	}

	public static AccountDaoImpl getInstance() {
		if (instance == null) {

			instance = new AccountDaoImpl();

		}

		return instance;
	}

	public interface SqlMember {

		public static final String SqlRead = String.format("SELECT * FROM %s ORDER BY %s", TBL_NAME, COL_POSITIONNO);
		public static final String SqlReadEach = String.format("SELECT * FROM %s WHERE %s = ?" , TBL_NAME,
				COL_ID);
		public static final String SqlWrite = String.format(
				"INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
				TBL_NAME, COL_ID, COL_PW, COL_NAME, COL_PHONE, COL_EMAIL, COL_DEPTNO, COL_DEPTNAME, COL_ISADMIN);
		public static final String SqlDelete = String.format("DELETE FROM %s WHERE ID = ?", TBL_NAME);
		public static final String SqlModifyAdmin = String.format(
				"UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
				TBL_NAME, COL_PW, COL_NAME, COL_PHONE, COL_EMAIL, COL_DEPTNO, COL_POSITIONNO, COL_EMPNO, COL_ID);
		public static final String SqlModifyLocal = String.format(
				"UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?", 
				TBL_NAME, COL_PW, COL_NAME, COL_PHONE, COL_EMAIL, COL_ID);
	}

	// field

	private List<Account> acclist;

	// DB 연결에 관련된 메서드
	public Connection getConnection() throws Exception {
		Connection conn = null;

		DriverManager.registerDriver(new OracleDriver());

		conn = DriverManager.getConnection(URL, USER, Password);

		// TODO Auto-generated catch block

		return conn;

	}

	public void closeConnection(Connection conn, Statement stmt) throws Exception {
		stmt.close();
		conn.close();

	}

	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {

		rs.close();
		stmt.close();
		conn.close();

	}

	///////// DB 연결 관련 메서드 종료.

	@Override
	public Account read(String search) {
		// TODO Auto-generated method stub
		
		Account user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlReadEach);
			stmt.setString(1, search);
			rs = stmt.executeQuery();
			
			while (rs.next()) {

				int eid = rs.getInt(COL_EID);
				String id = rs.getString(COL_ID);
				String pw = rs.getString(COL_PW);
				String name = rs.getString(COL_NAME);
				String phone = rs.getString(COL_PHONE);
				String email = rs.getString(COL_EMAIL);
				int posNo = rs.getInt(COL_POSITIONNO);
				String posName = rs.getString(COL_POSTIONNAME);
				String empno = rs.getString(COL_EMPNO);
				int deptno = rs.getInt(COL_DEPTNO);
				String deptName = rs.getString(COL_DEPTNAME);
				int isAdmin = rs.getString(COL_ISADMIN).equals("true") ? 1 : 0;

				user = new AccountLocalUser(eid, id, pw, name, phone, email, deptno, deptName, empno,
						posNo, posName);

				

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

		return user;
	}

	@Override
	public List<Account> read() {
		acclist = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlRead);

			rs = stmt.executeQuery();

			while (rs.next()) {

				int eid = rs.getInt(COL_EID);
				String id = rs.getString(COL_ID);
				String pw = rs.getString(COL_PW);
				String name = rs.getString(COL_NAME);
				String phone = rs.getString(COL_PHONE);
				String email = rs.getString(COL_EMAIL);
				int posNo = rs.getInt(COL_POSITIONNO);
				String posName = rs.getString(COL_POSTIONNAME);
				String empno = rs.getString(COL_EMPNO);
				int deptno = rs.getInt(COL_DEPTNO);
				String deptName = rs.getString(COL_DEPTNAME);
				int isAdmin = rs.getString(COL_ISADMIN).equals("true".toLowerCase()) ? 1 : 0;

				AccountLocalUser user = new AccountLocalUser(eid, id, pw, name, phone, email, deptno, deptName, empno,
						posNo, posName);
				
				acclist.add(user);

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

		return acclist;
	}

	@Override
	public int writeAcc(Account account) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlWrite);

			stmt.setString(1, account.getId());
			stmt.setString(2, account.getPw());
			stmt.setString(3, account.getName());
			stmt.setString(4, account.getPhone());
			stmt.setString(5, account.getEmail());
			stmt.setInt(6, account.getDeptNo());
			stmt.setString(7, account.getDeptName());
			String isadmin = account.getIsAdmin() == 1 ? "true" : "false";
			stmt.setString(8,isadmin);

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
	public int deleteAcc(Account account) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlDelete);

			stmt.setString(1, account.getId());

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

	public int ModifiedAccAdmin(Account account) {
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
	public int ModifiedAcc(Account account) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();

			stmt = conn.prepareStatement(SqlMember.SqlModifyLocal);
			stmt.setString(1, account.getPw());
			stmt.setString(2, account.getName());
			stmt.setString(3, account.getPhone());
			stmt.setString(4, account.getEmail());
			stmt.setString(5, account.getId());
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
	public String findId(String name, String empno) {
		// TODO Auto-generated method stub
		acclist = read();

		for (Account acc : acclist) {

			if (acc.getName().equals(name) && acc.getEmpNo().equals(empno)) {

				return acc.getId();

			}

		}

		return null;
	}

	@Override
	public String findPw(String id, String email) {
		// TODO Auto-generated method stub
		acclist = read();

		for (Account acc : acclist) {

			if (acc.getId().equals(id) && acc.getEmail().equals(email)) {

				return acc.getPw();

			}

		}

		return null;
	}

	

	@Override
	public Account checkLogin(String id, String pw) {
		// TODO Auto-generated method stub
		acclist = read();
		
		for(Account acc : acclist) {
			
			if(id.equals(acc.getId())) {
				if(pw.equals(acc.getPw())) {
					
					return acc;
					
				}
			}
		}
		
		
		return null;
	}

}
