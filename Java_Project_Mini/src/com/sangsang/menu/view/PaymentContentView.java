package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class PaymentContentView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JButton btnAddPay;
	private JButton btnModifiedPay;
	private JButton btnDeletePay;
	private JButton btnDeletePay_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private String[] colNamePay = {}; //TODO

	/**
	 * Launch the application.
	 */
	public static void showMypaymentContentFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentContentView frame = new PaymentContentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentContentView() {
		
		
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("급여 관리 시스템");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 35, 803, 46);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 228, 803, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField.setBounds(12, 181, 139, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("급여 조회할 사원");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 157, 139, 21);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton("검색");
		btnNewButton.setBounds(163, 181, 57, 37);
		contentPane.add(btnNewButton);
		
		btnAddPay = new JButton("급여 등록");
		btnAddPay.setBounds(291, 181, 105, 37);
		contentPane.add(btnAddPay);
		
		btnModifiedPay = new JButton("급여 수정");
		btnModifiedPay.setBounds(408, 181, 105, 37);
		contentPane.add(btnModifiedPay);
		
		btnDeletePay = new JButton("급여 삭제");
		btnDeletePay.setBounds(525, 181, 105, 37);
		contentPane.add(btnDeletePay);
		
		btnDeletePay_1 = new JButton("급여 명세서 출력");
		btnDeletePay_1.setBounds(644, 181, 171, 37);
		contentPane.add(btnDeletePay_1);
	}

}
