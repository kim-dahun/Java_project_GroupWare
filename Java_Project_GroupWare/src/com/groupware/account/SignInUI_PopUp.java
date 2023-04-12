package com.groupware.account;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignInUI_PopUp extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textPw;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textAge;
	private JButton btnIdcheck;
	private JButton btnSignIn;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void showMySignInPopup() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInUI_PopUp frame = new SignInUI_PopUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private SignInUI_PopUp() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 292, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("회 원 가 입");
		lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 10, 252, 31);
		contentPane.add(lblTitle);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(12, 76, 70, 23);
		contentPane.add(lblID);
		
		textId = new JTextField();
		textId.setBounds(94, 76, 150, 23);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textPw = new JTextField();
		textPw.setColumns(10);
		textPw.setBounds(94, 120, 150, 23);
		contentPane.add(textPw);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(12, 120, 70, 23);
		contentPane.add(lblPassword);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(94, 163, 150, 23);
		contentPane.add(textName);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(12, 163, 70, 23);
		contentPane.add(lblName);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(94, 204, 150, 23);
		contentPane.add(textPhone);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(12, 204, 70, 23);
		contentPane.add(lblPhone);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(94, 245, 150, 23);
		contentPane.add(textEmail);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(12, 245, 70, 23);
		contentPane.add(lblEmail);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(94, 288, 150, 23);
		contentPane.add(textAge);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setBounds(12, 288, 70, 23);
		contentPane.add(lblAge);
		
		btnIdcheck = new JButton("ID 중복체크");
		btnIdcheck.setBounds(43, 338, 189, 31);
		contentPane.add(btnIdcheck);
		// UI
		btnSignIn = new JButton("회원가입");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭
				
				AccountDao account = AccountDaoImpl.getInstance();
				// DaoImpl 객체 호출
				String id = textId.getText();
				String pw = textPw.getText();
				String name = textName.getText();
				int age = Integer.parseInt(textAge.getText());
				String phone = textPhone.getText();
				String email = textEmail.getText();
				// 계정 관련 정보를 텍스트필드로부터 추출
				
				AccountModel acco = new AccountModel(id, pw, name, age, phone, email, 0, 0);
				// 추출한 정보를 토대로 새로운 객체 생성 ( 계정은 회원가입마다 새로 생기니까 )
				account.makeNewAccount(acco);
				// 객체를 DaoImpl에 있는 실제 계정 등록 메서드로 전달 ( 여기서 UI 역할은 종료 )
				// 만약 리턴값을 받아서 메시지 팝업 등을 표현할 거면, makeNewAccount 메서드에서
				// 리턴값을 주도록 설정해서 실패나 성공에 대한 팝업 표시
				

				
			}
		});
		btnSignIn.setBounds(43, 379, 189, 31);
		contentPane.add(btnSignIn);
		
		btnExit = new JButton("닫기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnExit.setBounds(43, 420, 189, 31);
		contentPane.add(btnExit);
	}
}
