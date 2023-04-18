package com.sangsang.menu.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.menu.model.Department;
import com.sangsang.menu.model.Position;
import com.sangsang.ojdbc.OracleDbConnectionTool;
import static com.sangsang.menu.model.Position.Entity.*;

public class MenuPosDaoImpl implements MenuPosDao {

	public interface SqlListPos{
		
		public static final String SQLREADPOS = String.format("SELECT * FROM %s ORDER BY %s", TBL_NAME, COL_POSNO );
		public static final String SQLWRITEPOS = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", TBL_NAME,COL_POSNO,COL_POSNAME);
		public static final String SQLDELETE = String.format("DELETE FROM %s WHERE %s = ?", TBL_NAME,COL_POSNO);
	}


	// 싱글톤
	private static MenuPosDaoImpl instance;

	private MenuPosDaoImpl() {

	}

	public static MenuPosDaoImpl getInstance() {
		if (instance == null) {

			instance = new MenuPosDaoImpl();

		}

		return instance;
	}

	private List<Position> poslist;

	@Override
	public Position read(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position> read() {
		poslist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListPos.SQLREADPOS);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Position pos = new Position ( rs.getInt(1), rs.getString(2));
				poslist.add(pos);
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				OracleDbConnectionTool.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		// TODO Auto-generated method stub
		return poslist;
	}

	@Override
	public int writePos(Position pos) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListPos.SQLWRITEPOS);
			
			stmt.setInt(1, pos.getPosNo());
			stmt.setString(2, pos.getPosName());
			
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
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
	public int deletePos(Position pos) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListPos.SQLDELETE);
			stmt.setInt(1, pos.getPosNo());
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
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

}
