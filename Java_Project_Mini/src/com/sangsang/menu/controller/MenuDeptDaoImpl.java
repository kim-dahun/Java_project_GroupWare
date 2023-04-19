package com.sangsang.menu.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sangsang.menu.model.Department;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import oracle.jdbc.OracleDriver;

import static com.sangsang.ojdbc.OrcaleDbLowData.*;

import static com.sangsang.account.model.OrcaleDbLowData.Password;
import static com.sangsang.account.model.OrcaleDbLowData.URL;
import static com.sangsang.account.model.OrcaleDbLowData.USER;
import static com.sangsang.menu.model.Department.Entity.*;

public class MenuDeptDaoImpl implements MenuDeptDao {

	public static interface SqlList {

		public static final String SQLREADDEPT = String.format("SELECT * FROM %s ORDER BY %s", TBL_NAME, COL_DEPTNO);
		public static final String SQLWRITEDEPT = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
				TBL_NAME, COL_DEPTNO, COL_DEPTNAME, COL_DEPTNODENO);
		public static final String SQLDELETEDEPT = String.format("DELETE FROM %s WHERE %s = ?", TBL_NAME, COL_DEPTNO);
		public static final String SQLMODIFIEDDEPT = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TBL_NAME,
				COL_DEPTNAME, COL_DEPTNODENO, COL_DEPTNO);
	}

	// 싱글톤 관련 메서드 ( 부서 리스트를 갖고 다니는 객체는 1개만 사용 )
	private static MenuDeptDaoImpl instance;
	private List<Department> deptlist;

	private MenuDeptDaoImpl() {

	}

	public static MenuDeptDaoImpl getInstance() {
		if (instance == null) {

			instance = new MenuDeptDaoImpl();

		}

		return instance;
	}

	// DB 연결과 관련된 메서드
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

	@Override
	public Department read(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> read() {
		// TODO Auto-generated method stub
		deptlist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SqlList.SQLREADDEPT);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Department dpart = new Department(rs.getInt(COL_DEPTNO), rs.getString(COL_DEPTNAME), rs.getInt(COL_DEPTNODENO));
				
				deptlist.add(dpart);
				
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

		return deptlist;
	}

	// 읽어온 부서 목록을 콤보박스의 목록으로 만드는 메서드
	public String[] readName() {
		// TODO Auto-generated method stub
		deptlist = read();
		String[] deptcombo = new String[deptlist.size()];

		for(int i = 0 ; i < deptcombo.length; i++) {
			
			deptcombo[i]=deptlist.get(i).getDeptName();
			
		}
		
		return deptcombo;
	}

	@Override
	public int writedept(Department dept) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlList.SQLWRITEDEPT);
			stmt.setInt(1, dept.getDeptno());
			stmt.setString(2, dept.getDeptName());
			stmt.setInt(3, dept.getDeptNodeNo());
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				closeConnection(conn, stmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
				try {
					closeConnection(conn, stmt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
		
		
		
		return result;
	}

	@Override
	public int deletedept(Department dept) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlList.SQLDELETEDEPT);
			stmt.setInt(1, dept.getDeptno());
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
	public int modifiedDept(Department dept) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlList.SQLMODIFIEDDEPT);
			stmt.setInt(1, dept.getDeptno());
			stmt.setString(2, dept.getDeptName());
			stmt.setInt(3, dept.getDeptNodeNo());
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

	public Department readOne(int deptno) {
		deptlist = read();
		
		for(int i = 0 ; i<deptlist.size(); i++) {
			
			if(deptno == deptlist.get(i).getDeptno()) {
				
				return deptlist.get(i);
				
			}
			
		}
		
		return null;
	}
	
	@Override
	public boolean checkDupledept(Department dept) {
		// TODO Auto-generated method stub
		for(int i = 0; i<deptlist.size(); i++) {
			
			if(deptlist.get(i).getDeptName().equals(dept.getDeptName())) {
				
				return true;
				
			}
			
		}
		
		
		return false;
	}

}
