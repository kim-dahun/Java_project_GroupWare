package com.sangsang.ojdbc;

import static com.sangsang.account.model.OrcaleDbLowData.Password;
import static com.sangsang.account.model.OrcaleDbLowData.URL;
import static com.sangsang.account.model.OrcaleDbLowData.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class OracleDbConnectionTool {

	public static Connection getConnection() throws Exception {
		Connection conn = null;

		DriverManager.registerDriver(new OracleDriver());

		conn = DriverManager.getConnection(URL, USER, Password);

		// TODO Auto-generated catch block

		return conn;

	}

	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		stmt.close();
		conn.close();

	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {

		rs.close();
		stmt.close();
		conn.close();

	}
	
}
