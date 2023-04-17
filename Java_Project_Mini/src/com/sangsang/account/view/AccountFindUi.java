package com.sangsang.account.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.account.controller.AccountDao;
import com.sangsang.account.controller.AccountDaoImpl;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountFindUi extends JFrame {

	private int parentX;
	private int parentY;
	private AccountDaoImpl dao = AccountDaoImpl.getInstance();
	private JPanel contentPane;
	private JButton btnFindPw;
	private JButton btnFindId;
	private JTextField textempno;
	private JTextField textName;
	private JTextField textID;
	private JTextField textEmail;

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
		setTitle("아이디/비밀번호 찾기");
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

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textName.setBounds(106, 23, 204, 33);
		panel.add(textName);
		textName.setColumns(10);

		textempno = new JTextField();
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

		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textEmail.setColumns(10);
		textEmail.setBounds(106, 67, 204, 33);
		panel_1.add(textEmail);

		textID = new JTextField();
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setFont(new Font("D2Coding", Font.PLAIN, 20));
		textID.setColumns(10);
		textID.setBounds(106, 24, 204, 33);
		panel_1.add(textID);

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
		if (obj == btnFindId) {
			// 입력 칸이 공백이거나 스페이스키만 입력된 게 아닌지 체크
			if (textName.getText().isBlank() || textempno.getText().isBlank()) {

				JOptionPane.showMessageDialog(this, "성함과 사번을 정확히 기재해주세요");
				return;

			}
			// 조건문 패스하면 아이디찾기 메서드 호출
			String answer = dao.findId(textName.getText(), textempno.getText());

			// 아이디가 없으면 팝업 표출
			if (answer == null) {

				JOptionPane.showMessageDialog(this, "일치하는 정보가 없습니다.");
				return;
			}
			
			JOptionPane.showMessageDialog(this, "찾으시는 ID는 [ " + answer + " ] 입니다.", "아이디 찾기", JOptionPane.INFORMATION_MESSAGE, null);
			
		} else if (obj == btnFindPw) {
			// 입력 칸이 공백이거나 스페이스키만 입력된 게 아닌지 체크
			if (textID.getText().isBlank() || textEmail.getText().isBlank()) {
				JOptionPane.showMessageDialog(this, "아이디와 이메일을 정확히 기재해주세요");
				return;
			}
			// 조건문 패스하면 아이디찾기 메서드 호출
			String answer = dao.findPw(textID.getText(), textEmail.getText());
			
			// 해당 하는 비밀번호 없으면 팝업 표시
			if (answer == null) {

				JOptionPane.showMessageDialog(this, "일치하는 정보가 없습니다.");
				return;
			}
			
			JOptionPane.showMessageDialog(this, "찾으시는 Password는 [ " + answer + " ] 입니다.", "패스워드 찾기", JOptionPane.INFORMATION_MESSAGE, null);

		}

	}

}
