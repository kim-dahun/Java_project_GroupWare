package com.sangsang.menu.model;

import java.time.LocalDateTime;

public class GanttChart {
	
	public interface EntityGantt{
		
		public static final String TBL_NAME_SCH = "SCHEDULE";
		public static final String COL_CONTENTNO = "CONTENTNO";
		public static final String COL_EMPNO_SCH = "EMPNO";
		public static final String COL_STARTDAY = "STARTDAY";
		public static final String COL_ENDDAY = "ENDDAY";
		public static final String COL_TITLE = "TITLE";
		public static final String COL_CONTENT = "CONTENT";
		
	}
	
	private String id;
	private String empno;
	private String name;
	// join 해서 가져올 필드값;
	
	private int contentno;
	private LocalDateTime startDay;
	private LocalDateTime endDay;
	private String title;
	private String content;
	
	public GanttChart() {
		
		
		
	}
	
	
	public GanttChart(String id, String empno, String name, LocalDateTime startDay, LocalDateTime endDay, String title,
			String content, int contentno) {
		super();
		this.id = id;
		this.empno = empno;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
		this.title = title;
		this.content = content;
		this.contentno = contentno;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmpno() {
		return empno;
	}


	public void setEmpno(String empno) {
		this.empno = empno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getStartDay() {
		return startDay;
	}


	public void setStartDay(LocalDateTime startDay) {
		this.startDay = startDay;
	}


	public LocalDateTime getEndDay() {
		return endDay;
	}


	public void setEndDay(LocalDateTime endDay) {
		this.endDay = endDay;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getContentno() {
		return contentno;
	}


	public void setContentno(int contentno) {
		this.contentno = contentno;
	}
	
	
	
	
	
	

}
