package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JTree;
import javax.swing.JList;
import java.awt.Font;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.account.view.AccountLoginUi;
import com.sangsang.menu.model.PaymentContent;
import com.sangsang.menu2.view.EmployeeView;
import com.sangsang.menu2.view.PaymentContentView;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Mainview {

	private JFrame frame;
	
	private AccountDaoImpl accdao = AccountDaoImpl.getInstance();
	private List<Account> acclist = accdao.read();
	private Account loginacc;
	private JLabel lblnowLogin;
	
	/**
	 * Launch the application.
	 */
	public static void showMainMenuFrame(Account acc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainview window = new Mainview(acc);
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
	public Mainview(Account acc) {
		this.loginacc = acc;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setTitle("관리자 페이지");
		frame.setBounds(100, 100, 850, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 203, 389);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("조직도 자리\n");
		scrollPane.setViewportView(textArea);
		
		TreeNode ceo = new DefaultMutableTreeNode("a");
		DefaultTreeModel dtree = new DefaultTreeModel(ceo);
		TreeNode node_1 = new DefaultMutableTreeNode("b");
				TreeNode node_2 = new DefaultMutableTreeNode("c");
		
		lblnowLogin = new JLabel(loginacc.toString());
		lblnowLogin.setForeground(Color.WHITE);
		lblnowLogin.setBackground(Color.WHITE);
		lblnowLogin.setFont(new Font("D2Coding", Font.BOLD, 15));
		lblnowLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnowLogin.setBounds(227, 16, 595, 22);
		frame.getContentPane().add(lblnowLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(227, 135, 595, 264);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btndeptad = new JButton("부서 관리");
		btndeptad.setBounds(12, 63, 145, 39);
		panel.add(btndeptad);
		btndeptad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DeptView.showOrganizationFrame();
				
			}
		});
		btndeptad.setFont(new Font("D2Coding", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("관리자 메뉴");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 24, 571, 29);
		panel.add(lblNewLabel);
		
		JButton btnposad = new JButton("직급 관리");
		btnposad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PositionView.showmyPositonframe();
				
			}
		});
		btnposad.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnposad.setBounds(226, 63, 145, 39);
		panel.add(btnposad);
		
		JButton btnempad = new JButton("사원 관리");
		btnempad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EmployeeView.showMyemployee();
				
			}
		});
		btnempad.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnempad.setBounds(438, 63, 145, 39);
		panel.add(btnempad);
		
		JButton btnpost = new JButton("로그인 화면");
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AccountLoginUi.getFrame().setVisible(true);
				frame.dispose();
				//TODO
				
				
			}
		});
		btnpost.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnpost.setBounds(12, 187, 145, 39);
		panel.add(btnpost);
		
		JButton btnCal = new JButton("일정관리");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCal.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnCal.setBounds(226, 187, 145, 39);
		panel.add(btnCal);
		
		JButton btnSal = new JButton("급여 관리");
		btnSal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PaymentContentView.showMypaymentContentFrame();
				
			}
		});
		btnSal.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnSal.setBounds(438, 187, 145, 39);
		panel.add(btnSal);
	}
}
