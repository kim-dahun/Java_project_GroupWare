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

public class DeptAddView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textdeptname;
	private JTextField textdeptref;
	private MenuDeptDaoImpl dao = MenuDeptDaoImpl.getInstance();
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void showmydeptAdd() {
		try {
			DeptAddView dialog = new DeptAddView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DeptAddView() {
		setTitle("부서 등록");

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
			JLabel lblNewLabel = new JLabel("등록할 부서이름");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(47, 64, 132, 25);
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
		textdeptname.setBounds(209, 64, 167, 25);
		contentPanel.add(textdeptname);
		textdeptname.setColumns(10);

		textdeptref = new JTextField();
		textdeptref.setHorizontalAlignment(SwingConstants.CENTER);
		textdeptref.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textdeptref.setColumns(10);
		textdeptref.setBounds(209, 126, 167, 25);
		contentPanel.add(textdeptref);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("등록");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						handleclickEvent(e);

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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

		if (obj == okButton) {

			try {
				if (textdeptname.getText().isBlank() || textdeptref.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "정확한 부서명과 상위부서를 설정해주세요. 원하는 상위부서가 없으면 0을 입력해주세요");
					return;

				}
				
				for(int i = 0 ; i<dao.read().size() ; i++) {
					int check = Integer.parseInt(textdeptref.getText());
					if(dao.read().get(i).getDeptno()==check || check==0) {
						int deptno = (dao.readName().length+1)*10;
						Department dpart = new Department(deptno,textdeptname.getText(),Integer.parseInt(textdeptref.getText()));
						dao.writedept(dpart);
						JOptionPane.showMessageDialog(this, "부서 등록이 완료되었습니다.");
						textdeptname.setText("");
						textdeptref.setText("");
						return;
					}
					
				}
				
				JOptionPane.showMessageDialog(this, "상위 부서에는 정확한 부서 번호를 입력해야 합니다.");
				return;
				
			} catch (NumberFormatException e2) {
				// TODO: handle exception

				JOptionPane.showMessageDialog(this, "상위 부서에는 숫자를 입력해야 합니다.");
				return;
			}

		} else if (obj == cancelButton) {

			dispose();

		}

	}
}
