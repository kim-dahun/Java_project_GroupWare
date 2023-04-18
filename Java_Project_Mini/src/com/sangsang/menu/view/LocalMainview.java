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

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LocalMainview {

	private JFrame frame;
	private JTextField textField;
	private Account loginacc;
	
	private AccountDaoImpl accdao = AccountDaoImpl.getInstance();
	private List<Account> acclist = accdao.read();
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

	
	
	/**
	 * Create the application.
	 */
	public LocalMainview(Account acc) {
		initialize();
		this.loginacc = acc;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("일반 사용자 페이지");
		frame.setBounds(100, 100, 850, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.WEST);
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				clickhandleEvent(e);
				
			}
		});
		
		
		
		
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("colors");
						node_2 = new DefaultMutableTreeNode("blue");
							node_2.add(new DefaultMutableTreeNode("violet"));
							node_2.add(new DefaultMutableTreeNode("red"));
							node_2.add(new DefaultMutableTreeNode("yellow"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("white");
							node_2.add(new DefaultMutableTreeNode("da"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("green");
							node_2.add(new DefaultMutableTreeNode("bl"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("sports");
						node_2 = new DefaultMutableTreeNode("basketball");
							node_2.add(new DefaultMutableTreeNode("soccer"));
							node_2.add(new DefaultMutableTreeNode("football"));
							node_2.add(new DefaultMutableTreeNode("hockey"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("food");
						node_2 = new DefaultMutableTreeNode("hot dogs");
							node_2.add(new DefaultMutableTreeNode("pizza"));
							node_2.add(new DefaultMutableTreeNode("ravioli"));
							node_2.add(new DefaultMutableTreeNode("bananas"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		textField = new JTextField();
		textField.setFont(new Font("D2Coding", Font.PLAIN, 12));
		textField.setText("기업 조직도");
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblnowLogin = new JLabel(loginacc.toString());
		lblnowLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnowLogin.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblnowLogin.setBounds(148, 10, 595, 22);
		panel.add(lblnowLogin);
	}



	protected void clickhandleEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
