package com.sangsang.account.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.account.controller.AccountDao;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountFindUi extends JFrame {
	
	private int parentX;
	private int parentY;

	private JPanel contentPane;
	private JButton btnFindPw;
	private JButton btnFindId;

	/**
	 * Launch the application.
	 */
	public static void showAccountFindFrame(Component component) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountFindUi frame = new AccountFindUi(component);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AccountFindUi(Component component) {
		this.parentX = AccountDao.getFrameX(component);
		this.parentY = AccountDao.getFrameY(component);
		initialize();
		
	}
	

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(this.parentX, this.parentY, 450, 396);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(12, 10, 410, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnFindId = new JButton("Find ID");
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickhandleEvent(e);
				
			}
		});
		btnFindId.setForeground(Color.WHITE);
		btnFindId.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnFindId.setBackground(Color.DARK_GRAY);
		btnFindId.setBounds(106, 122, 204, 33);
		panel.add(btnFindId);
		
		JTextField textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textName.setBounds(106, 23, 204, 33);
		panel.add(textName);
		textName.setColumns(10);
		
		JTextField textempno = new JTextField();
		textempno.setHorizontalAlignment(SwingConstants.CENTER);
		textempno.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textempno.setColumns(10);
		textempno.setBounds(106, 66, 204, 33);
		panel.add(textempno);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 23, 82, 33);
		panel.add(lblNewLabel);
		
		JLabel lblEmpno = new JLabel("EmpNo");
		lblEmpno.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpno.setForeground(Color.WHITE);
		lblEmpno.setBounds(12, 66, 82, 33);
		panel.add(lblEmpno);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(12, 182, 410, 165);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnFindPw = new JButton("Find Password");
		btnFindPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clickhandleEvent(e);
				
			}
		});
		btnFindPw.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnFindPw.setForeground(Color.WHITE);
		btnFindPw.setBackground(Color.DARK_GRAY);
		btnFindPw.setBounds(107, 122, 204, 33);
		panel_1.add(btnFindPw);
		
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(106, 67, 204, 33);
		panel_1.add(textField);
		
		JTextField textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(106, 24, 204, 33);
		panel_1.add(textField_1);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(12, 24, 82, 33);
		panel_1.add(lblId);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(12, 67, 82, 33);
		panel_1.add(lblEmail);
	}
	protected void clickhandleEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj==btnFindId) {
			
		} else if (obj==btnFindPw) {
			
		}
		
	}

}
