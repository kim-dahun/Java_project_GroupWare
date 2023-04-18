package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class EmployeeView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeView frame = new EmployeeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 435);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 117, 581, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("사원 명부");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 581, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("* 사원 명부에서는 아이디 변경 및 급여 관련 작업을 제외한 작업들을 진행할 수 있습니다");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 371, 581, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnDeleteAccount = new JButton("사원 삭제");
		btnDeleteAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnDeleteAccount.setBounds(451, 74, 142, 33);
		contentPane.add(btnDeleteAccount);
		
		JButton btnSearchAccount = new JButton("사원 검색");
		btnSearchAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnSearchAccount.setBounds(229, 74, 142, 33);
		contentPane.add(btnSearchAccount);
		
		JButton btnModifiedAccount = new JButton("사원 수정");
		btnModifiedAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnModifiedAccount.setBounds(12, 74, 142, 33);
		contentPane.add(btnModifiedAccount);
	}
}
