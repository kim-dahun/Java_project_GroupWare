package com.sangsang.menu2.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import com.sangsang.menu.controller.GanttChartDaoImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class GanttChartView extends JFrame {

	private JPanel contentPane;
//	private TaskSeries gantt;
	private JButton btnAddNewWork;
	private JButton btnDeleteMyWork;
	private boolean runCheck = true;
	private JButton btnRefresh;
	private GanttChartDaoImpl dao = GanttChartDaoImpl.getInstance();
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JScrollPane scrollchartpane;
	private Font mainfont = new Font("D2Coding", Font.PLAIN, 25);
	private Font mainTitleFont = new Font("D2Coding",Font.PLAIN,35);
	private static int moveValue = 0;
	
	/**
	 * Launch the application.
	 */
	public static void showMyGanttChartMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
					GanttChartView frame = new GanttChartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshChart() {
		
		TaskSeriesCollection taskGroup = dao.readTask();
		
		System.out.println(taskGroup.getSeries(0).getItemCount());
		
		
		chart = ChartFactory.createGanttChart("업무 일정표", "수행자/수행일정", "소요기간", taskGroup, true, true, false);
		chart.setBorderVisible(true);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0,0,scrollchartpane.getWidth(),scrollchartpane.getHeight());
		chartPanel.setRangeZoomable(false);
		chartPanel.setAutoscrolls(true);
		chartPanel.setPreferredSize(scrollchartpane.getSize());
		chartPanel.setReshowDelay(250);
		
		chartPanel.setBackground(Color.WHITE);
		
		
		CategoryPlot caseSet = chart.getCategoryPlot();
		chart.getTitle().setFont(mainTitleFont);
		chart.getLegend().setItemFont(mainTitleFont);
		caseSet.getDomainAxis().setLabelFont(mainfont);
		caseSet.getDomainAxis().setTickLabelFont(mainfont);
		caseSet.getRangeAxis().setLabelFont(mainfont);
		caseSet.getRangeAxis().setTickLabelFont(mainfont);
		
		long timeScale = 7 * 1 * 24 * 60 * 60 * 1000;
		long nextWeekTime = new Date().getTime() + (7 * moveValue * 24 * 60 * 60 * 1000); // 오늘 날짜에 7일(밀리초)을 더한다.
		Date now = new Date(nextWeekTime);
		Date before = new Date(nextWeekTime);
		Date after = new Date(nextWeekTime+timeScale);
		
		System.out.println(now);
		System.out.println(after);
		
		DateAxis axis = (DateAxis) caseSet.getRangeAxis(); // DateAxis 타입으로 캐스팅, CategoryPlot 타입의 caseSet 변수의 메서드 getRangeAxis를 호출함.
		axis.setRange(before, after);
		DateTickUnit unit = new DateTickUnit(DateTickUnit.DAY,1); // DateTickUnit (기간의 간격 조건 = 시간 날짜 등, 간격범위  )
		axis.setTickUnit(unit); 

		axis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
		
		scrollchartpane.setPreferredSize(new Dimension(800,400));
		chartPanel.setMaximumDrawHeight(4000);
		chartPanel.setMaximumDrawWidth(6000);
		chartPanel.setMinimumDrawHeight(1000);
		chartPanel.setMinimumDrawWidth(1500);
		
		scrollchartpane.add(chartPanel);
		chartPanel.setVisible(true);
		
		
		
	}
	
	
	public GanttChartView() {
		setTitle("일정관리 시스템");
		
		initialize();
		refreshChart();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 824, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		scrollchartpane = new JScrollPane();
		scrollchartpane.setBounds(12, 81, 774, 528);
		panel.add(scrollchartpane);
		
//		gantt = new TaskSeries("업무 일정 공유표");
		
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnAddNewWork = new JButton("새로운 일정 추가");
		btnAddNewWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
				
			}
		});
		panel_1.add(btnAddNewWork);
		
		btnDeleteMyWork = new JButton("일정 삭제");
		btnDeleteMyWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
			}
		});
		panel_1.add(btnDeleteMyWork);
		
		btnRefresh = new JButton("새로고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleclickEvent(e);
				
			}
		});
		panel_1.add(btnRefresh);
	}

	
	
	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object obj = e.getSource();
		
		if(obj==btnAddNewWork) {
			
			GanttChartAddView.showMyGanttChartAdd();
			
		} else if (obj == btnDeleteMyWork) {
			
			JOptionPane.showInputDialog("삭제할 ");
			
		} else if ( obj == btnRefresh) {
			
			refreshChart();
			
		}
		
	}
}
