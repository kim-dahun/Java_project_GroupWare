package com.sangsang.menu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.menu.controller.MenuDeptDaoImpl;
import com.sangsang.menu.model.Department;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class DeptSearchView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textdeptname;
	private JTextField textdeptref;
	private MenuDeptDaoImpl dao = MenuDeptDaoImpl.getInstance();
	private JButton cancelButton;
	private JLabel lbldeptno;
	private JTextField textdeptno;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void showmydeptsearch() {
		try {
			DeptSearchView dialog = new DeptSearchView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DeptSearchView() {
		setTitle("부서 수정");

		initialize();

	}

	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("부서이름");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(47, 91, 132, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("상위 부서 번호");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(47, 126, 132, 25);
			contentPanel.add(lblNewLabel);
		}

		textdeptname = new JTextField();
		textdeptname.setHorizontalAlignment(SwingConstants.CENTER);
		textdeptname.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textdeptname.setBounds(209, 91, 167, 25);
		contentPanel.add(textdeptname);
		textdeptname.setColumns(10);

		textdeptref = new JTextField();
		textdeptref.setHorizontalAlignment(SwingConstants.CENTER);
		textdeptref.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textdeptref.setColumns(10);
		textdeptref.setBounds(209, 126, 167, 25);
		contentPanel.add(textdeptref);
		{
			lbldeptno = new JLabel("검색할 부서번호");
			lbldeptno.setHorizontalAlignment(SwingConstants.CENTER);
			lbldeptno.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lbldeptno.setBounds(47, 56, 132, 25);
			contentPanel.add(lbldeptno);
		}
		{
			textdeptno = new JTextField();
			textdeptno.setHorizontalAlignment(SwingConstants.CENTER);
			textdeptno.setFont(new Font("D2Coding", Font.PLAIN, 15));
			textdeptno.setColumns(10);
			textdeptno.setBounds(209, 56, 86, 25);
			contentPanel.add(textdeptno);
		}
		{
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					handleclickEvent(e);
				}
			});
			btnSearch.setBounds(307, 57, 69, 23);
			contentPanel.add(btnSearch);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("닫기");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						handleclickEvent(e);

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();

		if (obj == cancelButton) {

			dispose();

		} else if (obj == btnSearch) {

			int deptno = Integer.parseInt(textdeptno.getText());
			Department redpart = dao.readOne(deptno);
			if (redpart == null) {
				JOptionPane.showMessageDialog(this, "해당 부서번호는 존재하지 않습니다.");
				return;
			} else {

				textdeptname.setText(redpart.getDeptName());
				textdeptref.setText("" + redpart.getDeptNodeNo());

			}

		}

	}
}
