package com.groupware.account.view;

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
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JCheckBox;

public class SignInUI {

	private JFrame frmDh;
	private JTextField textId;
	private JTextField textpw;

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
		frmDh.setBounds(100, 100, 808, 735);
		frmDh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDh.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		frmDh.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textId = new JTextField();
		textId.setForeground(SystemColor.desktop);
		textId.setBackground(SystemColor.control);
		textId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textId.setText("");
				
			}
		});
		
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setText("ID를 입력하세요");
		textId.setBounds(80, 191, 643, 98);
		panel_1.add(textId);
		textId.setColumns(10);
		
		JLabel lblTitle = new JLabel("상상기업 그룹웨어");
		lblTitle.setFont(new Font("D2Coding", Font.BOLD, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 10, 786, 64);
		panel_1.add(lblTitle);
		
		textpw = new JTextField();
		
		textpw.setForeground(SystemColor.desktop);
		textpw.setBackground(SystemColor.control);
		textpw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textpw.setText("");
				
			}
		});
		
		textpw.setText("비밀번호를 입력하세요");
		textpw.setHorizontalAlignment(SwingConstants.CENTER);
		textpw.setForeground(Color.BLACK);
		textpw.setColumns(10);
		textpw.setBackground(SystemColor.menu);
		textpw.setBounds(80, 314, 643, 98);
		panel_1.add(textpw);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("     ID 저장");
		chckbxNewCheckBox.setFont(new Font("D2Coding", Font.PLAIN, 22));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(80, 442, 247, 50);
		panel_1.add(chckbxNewCheckBox);
		
		JLabel lblLogin = new JLabel("New label");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SignInUI_PopUp.showMySignInPopup();
				
			}
		});
		lblLogin.setBounds(80, 525, 643, 98);
		panel_1.add(lblLogin);
		
		JLabel lblbackground = new JLabel("New label");
		lblbackground.setIcon(new ImageIcon("C:\\Users\\user1\\Documents\\GitHub\\Java_project_GroupWare\\Java_Project_GroupWare\\img\\login.png"));
		lblbackground.setBounds(0, -11, 822, 725);
		panel_1.add(lblbackground);
	}
}
