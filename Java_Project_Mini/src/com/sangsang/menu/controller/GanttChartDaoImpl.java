package com.sangsang.menu.controller;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import com.sangsang.account.model.Account;
import com.sangsang.menu.model.GanttChart;
import com.sangsang.menu.model.GanttChart.EntityGantt;
import com.sangsang.ojdbc.OracleDbConnectionTool;

import static com.sangsang.menu.model.GanttChart.EntityGantt.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.sangsang.account.model.Account.Entity.*;

public class GanttChartDaoImpl implements GanttChartDao {

	private static GanttChartDaoImpl instance;

	private GanttChartDaoImpl() {

	}

	public static GanttChartDaoImpl getInstance() {
		if (instance == null) {

			instance = new GanttChartDaoImpl();

		}

		return instance;
	}

	private List<GanttChart> chartlist;

	public interface SqlListGantt {

		public static final String SQLREAD = String.format("SELECT * FROM %s JOIN %s ON %s = %s",
				GanttChart.EntityGantt.TBL_NAME_SCH, Account.Entity.TBL_NAME,

				GanttChart.EntityGantt.TBL_NAME_SCH + "." + EntityGantt.COL_EMPNO_SCH,
				Account.Entity.TBL_NAME + "." + Account.Entity.COL_EMPNO);

		public static final String SQLWRITE = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)",
				TBL_NAME_SCH, COL_EMPNO_SCH, COL_STARTDAY, COL_ENDDAY, COL_TITLE, COL_CONTENT);
		public static final String SQLDELETE = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", TBL_NAME_SCH,
				COL_EMPNO_SCH, COL_CONTENTNO);
		public static final String SQLUPDATE = String.format(
				"UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ? AND %s = ?", TBL_NAME_SCH, COL_TITLE,
				COL_CONTENT, COL_STARTDAY, COL_ENDDAY, COL_EMPNO_SCH, COL_CONTENTNO);

	}

	@Override
	public List<GanttChart> read() {
		chartlist = new ArrayList<>();
		TaskSeriesCollection taskGroup = new TaskSeriesCollection();
		TaskSeries tasks = new TaskSeries("업무 일정");

		Task task = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListGantt.SQLREAD);
			rs = stmt.executeQuery();
			while (rs.next()) {
				GanttChart gantt = new GanttChart();
				gantt.setEmpno(rs.getString(COL_EMPNO_SCH));
				Timestamp stime = rs.getTimestamp(COL_STARTDAY);

				LocalDateTime stimeCasting = stime.toLocalDateTime();
				Timestamp etime = rs.getTimestamp(COL_ENDDAY);
				LocalDateTime etimeCasting = etime.toLocalDateTime();

				gantt.setStartDay(stimeCasting);
				gantt.setEndDay(etimeCasting);
				gantt.setTitle(rs.getString(COL_TITLE));
				gantt.setContent(rs.getString(COL_CONTENT));
				gantt.setContentno(rs.getInt(COL_CONTENTNO));
				gantt.setName(rs.getString(COL_NAME));
				gantt.setId(rs.getString(COL_ID));
				gantt.setEmpno(rs.getString(COL_EMPNO_SCH));

				chartlist.add(gantt);
			}

			taskGroup.add(tasks);

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

		return chartlist;
	}

	@Override
	public TaskSeriesCollection readTask() {
		Task task = null;
		chartlist = read();
		TaskSeries tasks = new TaskSeries("일정");
		TaskSeriesCollection taskGroup = new TaskSeriesCollection();
		for (GanttChart x : chartlist) {

			Timestamp start = Timestamp.valueOf(x.getStartDay());
			Timestamp endtime = Timestamp.valueOf(x.getEndDay());
			task = new Task(x.getEmpno() + "/" + x.getName() + "/" + x.getTitle(), start, endtime);
			tasks.add(task);
		}

		taskGroup.add(tasks);

		return taskGroup;

	}

	@Override
	public GanttChart read(String search) {
		// TODO Auto-generated method stub
		chartlist = read();

		for (GanttChart x : chartlist) {

			if (search.equals(x.getEmpno())) {

				GanttChart chart = x;
				return chart;

			}

		}

		return new GanttChart();
	}

	@Override
	public int writeSch(GanttChart anything) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListGantt.SQLWRITE);

			Timestamp start = Timestamp.valueOf(anything.getStartDay());
			Timestamp endtime = Timestamp.valueOf(anything.getEndDay());

			stmt.setString(1, anything.getEmpno());
			stmt.setTimestamp(2, start);
			stmt.setTimestamp(3, endtime);
			stmt.setString(4, anything.getTitle());
			stmt.setString(5, anything.getContent());

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
	public int modifiedSch(GanttChart anything) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListGantt.SQLUPDATE);

			Timestamp start = Timestamp.valueOf(anything.getStartDay());
			Timestamp endtime = Timestamp.valueOf(anything.getEndDay());

			stmt.setString(1, anything.getTitle());
			stmt.setString(2, anything.getContent());

			stmt.setTimestamp(3, start);
			stmt.setTimestamp(4, endtime);
			stmt.setString(5, anything.getEmpno());
			stmt.setInt(6, anything.getContentno());

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
	public int DeleteSch(GanttChart anything) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = OracleDbConnectionTool.getConnection();
			stmt = conn.prepareStatement(SqlListGantt.SQLDELETE);

			stmt.setString(1, anything.getEmpno());
			stmt.setInt(2, anything.getContentno());

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

}
