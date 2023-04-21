package com.sangsang.menu.controller;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeriesCollection;

import com.sangsang.menu.model.GanttChart;

public interface GanttChartController {

	Task makeTask(GanttChart gantt); // 다오에서 받아야함. DB의 한줄
	// 간트차트에 필요한 데이터를 입력받아서 차트를 생성함
	
	TaskSeriesCollection tasklist = new TaskSeriesCollection(); // 다오에서 받아야함. DB에서 받아온 데이터리스트
	// 데이터를 받아둘 컬렉션 객체
	
//	GanttRenderer renderer = new GanttRenderer(); // 메인
//
//	CategoryPlot plot = new CategoryPlot();
//	plot.setDataset(dataset);
//	plot.setRenderer(renderer);
//
//	CategoryAxis categoryAxis = new CategoryAxis("Task");
//	DateAxis dateAxis = new DateAxis("Date");
//
//	JFreeChart chart = ChartFactory.createGanttChart(
//	    "Project Schedule", "Task", "Date", dataset, true, true, false);
//	chart.getCategoryPlot().setRenderer(renderer);
//
//	ChartPanel chartPanel = new ChartPanel(chart);
	
}
