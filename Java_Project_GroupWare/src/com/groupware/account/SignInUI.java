package com.groupware.account;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInUI {

	private JFrame frmDh;
	private JTextField textId;
	private JTextField textPassword;
	private JButton btnSignIn;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInUI window = new SignInUI();
					window.frmDh.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignInUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDh = new JFrame();
		frmDh.setTitle("Co-work tool");
		frmDh.setBounds(100, 100, 272, 361);
		frmDh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDh.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmDh.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSignIn = new JButton("회원가입");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickbtn();
				
			}
		});
		panel.add(btnSignIn);
		
		btnLogIn = new JButton("로그인");
		panel.add(btnLogIn);
		
		JPanel panel_1 = new JPanel();
		frmDh.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textId = new JTextField();
		textId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textId.setText("");
				
			}
		});
		
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setText("ID를 입력하세요");
		textId.setBounds(97, 105, 128, 21);
		panel_1.add(textId);
		textId.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textPassword.setText("");
			}
		});
		
		textPassword.setText("PW를 입력하세요");
		textPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textPassword.setColumns(10);
		textPassword.setBounds(97, 154, 128, 21);
		panel_1.add(textPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(12, 157, 73, 15);
		panel_1.add(lblPassword);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(12, 108, 73, 15);
		panel_1.add(lblId);
		
		JLabel lblTitle = new JLabel("상상기업 그룹웨어");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 10, 232, 44);
		panel_1.add(lblTitle);
	}

	

	protected void handleclickbtn() {
		// TODO Auto-generated method stub
		
		SignInUI_PopUp.showMySignInPopup();
		
	}
}
