package com.sangsang.menu.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.sangsang.menu.model.Department;

import oracle.jdbc.OracleDriver;

import static com.sangsang.ojdbc.OrcaleDbLowData.*;
import static com.sangsang.account.model.OrcaleDbLowData.Password;
import static com.sangsang.account.model.OrcaleDbLowData.URL;
import static com.sangsang.account.model.OrcaleDbLowData.USER;
import static com.sangsang.menu.model.Department.Entity.*;

public class MenuDeptDaoImpl implements MenuDeptDao {
	// 싱글톤 관련 메서드 ( 부서 리스트를 갖고 다니는 객체는 1개만 사용 )
	private static MenuDeptDaoImpl instance;
	private List<Department> deptlist;
	
	private MenuDeptDaoImpl() {
		
	}
	
	public static MenuDeptDaoImpl getInstance() {
		if(instance==null) {
			
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
		
		
		
		return deptlist;
	}

	@Override
	public int writeAcc(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAcc(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ModifiedAcc(Department dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkDupledept(Department dept) {
		// TODO Auto-generated method stub
		return false;
	}

}
