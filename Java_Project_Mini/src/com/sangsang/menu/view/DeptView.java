package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeptView extends JFrame {

	private JPanel contentPane;
	private JButton btndeptlinkdept;
	private JButton btnadddept;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private JButton btndeletedept;
	private JButton btndeptlist;
	private JButton btnsearch;

	/**
	 * Launch the application.
	 */
	public static void showOrganizationFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeptView frame = new DeptView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeptView() {

		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("D2Coding", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "CEO", "1번 라인", "2번 라인", "3번 라인" }));
		comboBox.setBounds(35, 152, 248, 43);
		panel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("D2Coding", Font.PLAIN, 20));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "10 Management Support dept", "20 F&A team",
				"30 HR team", "40 CS team", "50 GA team", "60 Sales Management dept", "70 Sales team",
				"80 S&Support team", "90 S&Planning team", "100 S&Global team", "110 Production Management dept",
				"120 QA team", "130 QC team", "140 P&C team", "150 P&T team", "160 CEO" }));
		comboBox_1.setBounds(35, 277, 248, 43);
		panel.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("조직도 등록");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setBounds(35, 26, 544, 43);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(315, 95, 4, 492);
		panel.add(panel_1);

		btndeptlinkdept = new JButton("조직도 연결");
		btndeptlinkdept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btndeptlinkdept.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btndeptlinkdept.setBounds(35, 400, 248, 43);
		panel.add(btndeptlinkdept);

		btnadddept = new JButton("부서 등록");
		btnadddept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btnadddept.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnadddept.setBounds(353, 152, 226, 43);
		panel.add(btnadddept);

		btndeletedept = new JButton("부서 삭제");
		btndeletedept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
			}
		});
		btndeletedept.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btndeletedept.setBounds(353, 488, 226, 43);
		panel.add(btndeletedept);

		btndeptlist = new JButton("전체 부서리스트 확인");
		btndeptlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);
				
			}
		});
		btndeptlist.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btndeptlist.setBounds(35, 488, 248, 43);
		panel.add(btndeptlist);

		btnsearch = new JButton("부서 검색");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleclickEvent(e);
				
			}
		});
		btnsearch.setFont(new Font("D2Coding", Font.PLAIN, 20));
		btnsearch.setBounds(353, 318, 226, 43);
		panel.add(btnsearch);
	}

	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnadddept) {
			
			DeptAddView.showmydeptAdd();
			
		} else if (obj == btndeptlinkdept) {
			
			
			
		} else if (obj == btndeletedept) {

			DeptDeleteView.showmydeptDelete();
			
		} else if (obj == btnsearch) {

			DeptSearchView.showmydeptsearch();
			
		} else if (obj == btndeptlist) {
			
			DeptMiniView.showMydeptlist();
			
		}

	}

}
