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

public class DeptDeleteView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textdeptname;
	private MenuDeptDaoImpl dao = MenuDeptDaoImpl.getInstance();
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void showmydeptDelete() {
		try {
			DeptDeleteView dialog = new DeptDeleteView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DeptDeleteView() {
		setTitle("부서 삭제");

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
			JLabel lblNewLabel = new JLabel("삭제할 부서번호");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(47, 64, 132, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("* 주의! 해당 부서로 재직 중인 사원이 있을 경우 삭제되지 않습니다");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 11));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(12, 126, 410, 25);
			contentPanel.add(lblNewLabel);
		}

		textdeptname = new JTextField();
		textdeptname.setHorizontalAlignment(SwingConstants.CENTER);
		textdeptname.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textdeptname.setBounds(209, 64, 167, 25);
		contentPanel.add(textdeptname);
		textdeptname.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("삭제");
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
				if (textdeptname.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "부서번호는 숫자로 입력해주세요. 부서 번호는 부서 전체 리스트에서 확인이 가능합니다.");
					return;

				}
				int deptno = Integer.parseInt(textdeptname.getText());
				Department dpart = new Department();
				dpart.setDeptno(deptno);
				
				int result = dao.deletedept(dpart);
				
				JOptionPane.showMessageDialog(this, result + "개 부서 삭제가 완료되었습니다.");
				textdeptname.setText("");
				return;
				
			} catch (NumberFormatException e2) {
				// TODO: handle exception

				JOptionPane.showMessageDialog(this, "부서번호에는 반드시 숫자만 입력되어야 합니다");
				return;
			}

		} else if (obj == cancelButton) {

			dispose();

		}

	}
}
