package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.menu.controller.GanttChartDaoImpl;
import com.sangsang.menu.model.GanttChart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class GanttChartInfoView extends JFrame {

	private JPanel contentPane;
	
	private final DateTimeFormatter DATEFMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JLabel lblName;
	private JLabel lblstart;
	private JLabel lblend;
	private JLabel lbltitle;
	private JTextArea textContent;
	private JLabel lblEmpno;
	private GanttChart gnt;

	/**
	 * Launch the application.
	 */
	public static void showMyGanttInfoView(GanttChart gnt) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GanttChartInfoView frame = new GanttChartInfoView(gnt);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initializeText() {
		
		
		
		GanttChart gantt = gnt;
		
		lblName.setText(gantt.getName());
		lblend.setText(gantt.getEndDay().format(DATEFMT));
		lblstart.setText(gantt.getStartDay().format(DATEFMT));
		lbltitle.setText(gantt.getTitle());
		lblEmpno.setText(gantt.getEmpno());
		textContent.setText(gantt.getContent());
		
		
		
		
	}

	public GanttChartInfoView(GanttChart gnt) {
		setTitle("세부내역");
		this.gnt = gnt;
		initialize();
		initializeText();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnClose = new JButton("나가기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnClose.setFont(new Font("D2Coding", Font.PLAIN, 20));
		panel.add(btnClose);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 10, 466, 471);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 67, 103, 25);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("시작일");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 102, 103, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("종료일");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(12, 137, 103, 25);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("제목");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(12, 172, 103, 25);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("내용");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(12, 207, 103, 25);
		panel_2.add(lblNewLabel_1_3);
		
		lblName = new JLabel("New label");
		lblName.setBackground(SystemColor.controlHighlight);
		lblName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(115, 67, 339, 25);
		panel_2.add(lblName);
		
		lblstart = new JLabel("New label");
		lblstart.setBackground(SystemColor.controlHighlight);
		lblstart.setHorizontalAlignment(SwingConstants.CENTER);
		lblstart.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblstart.setBounds(115, 102, 339, 25);
		panel_2.add(lblstart);
		
		lblend = new JLabel("New label");
		lblend.setBackground(SystemColor.controlHighlight);
		lblend.setHorizontalAlignment(SwingConstants.CENTER);
		lblend.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblend.setBounds(115, 137, 339, 25);
		panel_2.add(lblend);
		
		lbltitle = new JLabel("New label");
		lbltitle.setBackground(SystemColor.controlHighlight);
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lbltitle.setBounds(115, 172, 339, 25);
		panel_2.add(lbltitle);
		
		textContent = new JTextArea();
		textContent.setBackground(SystemColor.controlHighlight);
		textContent.setBounds(115, 207, 339, 254);
		panel_2.add(textContent);
		
		JLabel lblNewLabel_2 = new JLabel("사번");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 32, 103, 25);
		panel_2.add(lblNewLabel_2);
		
		lblEmpno = new JLabel("New label");
		lblEmpno.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpno.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblEmpno.setBackground(SystemColor.controlHighlight);
		lblEmpno.setBounds(115, 32, 339, 25);
		panel_2.add(lblEmpno);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(115, 32, 339, 429);
		panel_2.add(panel_3);
	}
}
