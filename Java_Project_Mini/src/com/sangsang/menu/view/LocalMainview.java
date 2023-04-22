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
import com.sangsang.menu2.view.GanttChartView;
import com.sangsang.menu2.view.PaymentContentView;
import com.sangsang.menu2.view.PaymentPrintView;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LocalMainview {

	private JFrame frame;
	
	private AccountDaoImpl accdao = AccountDaoImpl.getInstance();
	private List<Account> acclist = accdao.read();
	private Account loginacc;
	private JLabel lblnowLogin;
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void showMainMenuFrame(Account acc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalMainview window = new LocalMainview(acc);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setTextDept() {
		
		String dept = loginacc.getDeptName();
		textArea.setText(loginacc.getDeptName());
		textArea.setText("\n");
		for(int i = 0 ; i<acclist.size() ; i++) {
			
			if(dept.equals(acclist.get(i).getDeptName())){
				
				textArea.setText(acclist.get(i).getEmpNo() + " / " + acclist.get(i).getName());
				textArea.setText("\n");
				
			}
			
			
		}
		
	}
	
	
	/**
	 * Create the application.
	 */
	public LocalMainview(Account acc) {
		this.loginacc = acc;
		initialize();
		setTextDept();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setTitle("일반유저 페이지");
		frame.setBounds(100, 100, 850, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 203, 389);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
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
		
		JLabel lblNewLabel = new JLabel("일반 유저 메뉴");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 24, 571, 29);
		panel.add(lblNewLabel);
		
		JButton btnpost = new JButton("로그아웃");
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AccountLoginUi.getFrame().setVisible(true);
				frame.dispose();
				//TODO
				
				
			}
		});
		btnpost.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnpost.setBounds(12, 115, 145, 39);
		panel.add(btnpost);
		
		JButton btnCal = new JButton("일정 관리");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GanttChartView.showMyGanttChartMain();
				
			}
		});
		btnCal.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnCal.setBounds(225, 115, 145, 39);
		panel.add(btnCal);
		
		JButton btnSal = new JButton("급여 확인");
		btnSal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PaymentPrintView.showMyprintpreview();
				
			}
		});
		btnSal.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnSal.setBounds(438, 115, 145, 39);
		panel.add(btnSal);
	}
}
