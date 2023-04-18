package com.sangsang.account.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.account.model.AccountAdmin;
import com.sangsang.menu.view.LocalMainview;
import com.sangsang.menu.view.Mainview;

import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;
import java.awt.Container;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountLoginUi {

	private JFrame frame;
	private JTextField textID;
	private JTextField textPW;
	private JButton btnNewSign;
	private JButton btnLogin;
	private JButton btnFindAccount;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private AccountDaoImpl dao = AccountDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountLoginUi window = new AccountLoginUi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountLoginUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		AccountAdmin admin = new AccountAdmin();
		Account adminchecker = dao.read(admin.getId());
		if (adminchecker == null) {

			dao.writeAcc(admin);

		}

		frame = new JFrame();
		frame.setTitle("로그인 화면");
		frame.setBounds(100, 100, 748, 639);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("고객센터 222-2222 언제든 편하게 전화주면 안되세요");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(menuBar);

		JMenu mnNewMenu_1 = new JMenu("파일");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("이미지 불러오기");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(frame, "이 곳은 이미지를 첨부할 곳이 없습니다.");

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("종료하기");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer answer = JOptionPane.showConfirmDialog(frame, "정말 종료하시겠습니까?", "종료 확인",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println(answer);
				checkWannaClose(answer);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_2 = new JMenu("유틸리티");
		mnNewMenu_2.setBackground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("로그 확인");
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JMenu mnNewMenu = new JMenu("정보");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("도움말");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("버전 정보 확인");
		mnNewMenu.add(mntmNewMenuItem_1);

		JPanel panel_loginMain = new JPanel();
		panel_loginMain.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel_loginMain);
		panel_loginMain.setLayout(null);

		textID = new JTextField();
		textID.setText("아이디를 입력하세요");
		textID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textID.setText("");

			}
		});
		textID.setHorizontalAlignment(SwingConstants.CENTER);
		textID.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textID.setBounds(274, 144, 417, 60);
		panel_loginMain.add(textID);
		textID.setColumns(10);

		textPW = new JTextField();
		textPW.setToolTipText("영문소문자,숫자를 포함한 10자리 이상");
		textPW.setText("비밀번호를 입력하세요.");
		textPW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textPW.setText("");

			}
		});
		textPW.setHorizontalAlignment(SwingConstants.CENTER);
		textPW.setFont(new Font("D2Coding", Font.PLAIN, 22));
		textPW.setColumns(10);
		textPW.setBounds(274, 248, 417, 60);
		panel_loginMain.add(textPW);

		lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(34, 144, 204, 60);
		panel_loginMain.add(lblNewLabel_1);

		lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("D2Coding", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(34, 248, 204, 60);
		panel_loginMain.add(lblNewLabel_1_1);

		btnNewSign = new JButton("Sign In");
		btnNewSign.setBackground(Color.WHITE);
		btnNewSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickbtnAnything(e);

			}
		});
		btnNewSign.setFont(new Font("D2Coding", Font.PLAIN, 22));
		btnNewSign.setBounds(32, 419, 206, 68);
		panel_loginMain.add(btnNewSign);

		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickbtnAnything(e);

			}
		});
		btnLogin.setFont(new Font("D2Coding", Font.PLAIN, 22));
		btnLogin.setBounds(259, 419, 198, 68);
		panel_loginMain.add(btnLogin);

		btnFindAccount = new JButton("Find Account");
		btnFindAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickbtnAnything(e);

			}
		});
		btnFindAccount.setBackground(Color.WHITE);
		btnFindAccount.setFont(new Font("D2Coding", Font.PLAIN, 22));
		btnFindAccount.setBounds(480, 419, 211, 68);
		panel_loginMain.add(btnFindAccount);
	}

	protected void checkWannaClose(int check) {
		// TODO Auto-generated method stub

		if (check == 0) {

			frame.dispose();

		}

		return;

	}

	protected void handleclickbtnAnything(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnNewSign) {

			AccountSignInUi.showSignInFrame(this.frame);

		} else if (obj == btnLogin) {
			// 로그인 관련 id 비밀번호 검증 & 조건문을 통해 성공 시 팝업, 아니면 실패 메시지
			Account loginacc = dao.checkLogin(textID.getText(), textPW.getText());
			if (loginacc == null) {

				JOptionPane.showMessageDialog(frame, "알맞은 ID 혹은 비밀번호를 입력해주세요.");
				return;

			} else {

				// 로그인 성공 시 팝업창 띄우고 로그인 이후 메인화면 표현 ( admin 일 경우 관리자 메인 / local 은 일반 메인 )
				JOptionPane.showMessageDialog(frame, "로그인에 성공했습니다.");

				if (!loginacc.getId().equals("admin")) {

					// 일반 유저 페이지 - 새 창 켜지면서 현재창은 종료하고 현재 창의 account 정보 넘겨줌.
					LocalMainview.showMainMenuFrame(loginacc);

				} else if (loginacc.getId().equals("admin")) {

					// 관리자 페이지
					Mainview.showMainMenuFrame(loginacc);
				}

				return;

			}

		} else if (obj == btnFindAccount) {
			AccountFindUi.showAccountFindFrame(frame);
			return;
		}

	}
}
