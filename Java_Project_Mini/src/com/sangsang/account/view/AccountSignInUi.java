package com.sangsang.account.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.account.controller.AccountDao;
import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.account.model.AccountAdmin;
import com.sangsang.account.model.AccountLocalUser;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;


public class AccountSignInUi extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textPw;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private int parentX;
	private int parentY;
	private JButton btnSignIn;
	private JButton btnCheckIdDuple;
	private JComboBox comboBox;
	private boolean checkId = false;
	private AccountDaoImpl dao = AccountDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void showSignInFrame(Component component) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountSignInUi frame = new AccountSignInUi(component);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private AccountSignInUi(Component component) {
		this.parentX = AccountDao.getFrameX(component);
		this.parentY = AccountDao.getFrameY(component);
		this.checkId = false;
		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		// 프로그램 최초 실행 시 admin 계정 생성 여부체크 후 생성안됐으면 생성하고 진행.
		
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(parentX, parentY, 393, 552);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		textID = new JTextField();
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textID.setBounds(171, 52, 182, 49);
		panel.add(textID);
		textID.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(27, 52, 132, 49);
		panel.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblPassword.setBackground(Color.BLACK);
		lblPassword.setBounds(27, 111, 132, 49);
		panel.add(lblPassword);

		textPw = new JTextField();
		textPw.setHorizontalAlignment(SwingConstants.CENTER);
		textPw.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textPw.setColumns(10);
		textPw.setBounds(171, 111, 182, 49);
		panel.add(textPw);

		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1.setBounds(27, 170, 132, 49);
		panel.add(lblNewLabel_1_1);

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textName.setColumns(10);
		textName.setBounds(171, 170, 182, 49);
		panel.add(textName);

		JLabel lblNewLabel_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel_1_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1_1.setBounds(27, 229, 132, 49);
		panel.add(lblNewLabel_1_1_1);

		textPhone = new JTextField();
		textPhone.setHorizontalAlignment(SwingConstants.CENTER);
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textPhone.setColumns(10);
		textPhone.setBounds(171, 229, 182, 49);
		panel.add(textPhone);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel_1_1_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1_1_1.setBounds(27, 288, 132, 49);
		panel.add(lblNewLabel_1_1_1_1);

		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textEmail.setColumns(10);
		textEmail.setBounds(171, 288, 182, 49);
		panel.add(textEmail);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("DepartMent");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel_1_1_1_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1_1_1_1.setBounds(27, 347, 132, 49);
		panel.add(lblNewLabel_1_1_1_1_1);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 20));
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"10 Management Support dept", "20 F&A team", "30 HR team", "40 CS team", "50 GA team", "60 Sales Management dept", "70 Sales team", "80 S&Support team", "90 S&Planning team", "100 S&Global team", "110 Production Management dept", "120 QA team", "130 QC team", "140 P&C team", "150 P&T team", "160 CEO"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(171, 347, 182, 49);
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnSignIn = new JButton("SignIn");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickhandleEvent(e);

			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setBackground(Color.DARK_GRAY);
		btnSignIn.setFont(new Font("D2Coding", Font.PLAIN, 20));
		panel_1.add(btnSignIn);

		btnCheckIdDuple = new JButton("Check My ID");
		btnCheckIdDuple.setForeground(Color.WHITE);
		btnCheckIdDuple.setBackground(Color.DARK_GRAY);
		btnCheckIdDuple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickhandleEvent(e);

			}
		});
		btnCheckIdDuple.setFont(new Font("D2Coding", Font.PLAIN, 20));
		panel_1.add(btnCheckIdDuple);
	}

	protected void clickhandleEvent(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();

		if (obj == btnSignIn) {

			if (checkId == false) {

				JOptionPane.showMessageDialog(this, "아이디 중복 검사부터 시행해주세요", "중복검사 미실행", JOptionPane.CLOSED_OPTION);
				return;
			}

			if (textID.getText().isBlank() || textPw.getText().isBlank() || textPhone.getText().isBlank()
					|| textEmail.getText().isBlank() || textName.getText().isBlank()
					|| comboBox.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "모든 칸은 입력 필수입니다. 모두 입력해주세요.", "누락 칸 발견", JOptionPane.CLOSED_OPTION);
				return;
			}

			if (textPw.getText().length() < 10 || textPw.getText().length() > 15) {
				JOptionPane.showMessageDialog(this, "비밀번호는 10자리 이상 15자리 이하여야 합니다.", "비밀번호 길이 오류",
						JOptionPane.CLOSED_OPTION);
				return;
			}

			if (!textPw.getText().matches(".*[a-z].*") || !textPw.getText().matches(".*[0-9].*")) {
				JOptionPane.showMessageDialog(this, "비밀번호는 반드시 영소문자,숫자를 포함해야 합니다..", "비밀번호 형식 오류",
						JOptionPane.CLOSED_OPTION);
				return;
			}

			if (!textEmail.getText().matches(".*[@].*")) {
				JOptionPane.showMessageDialog(this, "이메일 형식을 지켜주세요. a@a...", "이메일 입력 형식 오류", JOptionPane.CLOSED_OPTION);
				return;
			}

			if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", textPhone.getText())) {
				JOptionPane.showMessageDialog(this, "전화번호 형식을 지켜주세요. 010-0000-0000", "전화번호 입력 형식 오류",
						JOptionPane.CLOSED_OPTION);
				return;
			}

			AccountLocalUser account = new AccountLocalUser();
			account.setName(textName.getText());
			account.setId(textID.getText());
			account.setPw(textPw.getText());
			account.setPhone(textPhone.getText());
			account.setEmail(textEmail.getText());
			account.setDeptNo((comboBox.getSelectedIndex() + 1) * 10);
			account.setDeptName(comboBox.getSelectedItem().toString());

			int result = dao.writeAcc(account);
			JOptionPane.showMessageDialog(this, result + "명의 회원가입을 정상적으로 마쳤습니다.", "정상 체크 완료",
					JOptionPane.CLOSED_OPTION);
			dispose();

		} else if (obj == btnCheckIdDuple) {

			if (textID.getText().isBlank()) {

				JOptionPane.showMessageDialog(this, "아이디 칸에 공백없이 입력해주세요.", "아이디 입력 오류", JOptionPane.CLOSED_OPTION);
				return;
			}

			if (dao.read(textID.getText()) == null) {

				JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다.", "정상 체크 완료", JOptionPane.CLOSED_OPTION);
				checkId = true;
				return;
			}

			JOptionPane.showMessageDialog(this, "이미 중복된 아이디가 있습니다.", "아이디 중복 발생", JOptionPane.CLOSED_OPTION);
			return;
		}

	}
}
