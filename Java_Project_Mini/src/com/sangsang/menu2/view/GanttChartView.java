package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.gantt.TaskSeries;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JButton;

public class GanttChartView extends JFrame {

	private JPanel contentPane;

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

	public GanttChartView() {
		
		initialize();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 81, 774, 528);
		panel.add(scrollPane);
		
		TaskSeries gantt = new TaskSeries("업무 일정 공유표");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAddNewWork = new JButton("새로운 일정 추가");
		panel_1.add(btnAddNewWork);
		
		JButton btnDeleteMyWork = new JButton("일정 삭제");
		panel_1.add(btnDeleteMyWork);
		
		JButton btnModifiedWork = new JButton("일정 수정");
		panel_1.add(btnModifiedWork);
	}
}
