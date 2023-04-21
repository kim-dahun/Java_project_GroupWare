package com.sangsang.menu.controller;

import java.util.List;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeriesCollection;

import com.sangsang.menu.model.GanttChart;

public interface GanttChartDao {

	// 일정 등록하면 기록해줄 메서드
	
	// 일정 수정해서 변경해줄 메서드
	
	// 일정 불러와서 초기화해줄 메서드
	
	// 특정 사번에 부합하는 일정만 불러오는 메서드
	
	// 일정 삭제해서 변경해줄 메서드
	
	List<GanttChart> read(); // 전체 일정 리스트
	
	TaskSeriesCollection readTask(); // 사원의 전체 일정 리스트
	
	GanttChart read(String search); // 특정 사원의 일정 검색.
	
	int writeSch(GanttChart anything); // 새 일정 등록
	
	int modifiedSch(GanttChart anything); // 일정 수정 ( read Task 메서드와 혼용해서 사용 )
	
	int DeleteSch(GanttChart anything); // 일정 삭제
	
	
	
	
}
