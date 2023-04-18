package com.sangsang.menu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.menu.controller.MenuPosDaoImpl;
import com.sangsang.menu.model.Position;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PosAddview extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JTextField textPosNo;
	private JTextField textPosName;
	private JButton okButton;
	private MenuPosDaoImpl dao = MenuPosDaoImpl.getInstance();

	/**
	 * Launch the application.
	 */
	public static void showMyAddPosition() {
		try {
			PosAddview dialog = new PosAddview();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PosAddview() {

		initialize();

	}

	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setTitle("직급 추가");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textPosNo = new JTextField();
			textPosNo.setHorizontalAlignment(SwingConstants.CENTER);
			textPosNo.setFont(new Font("D2Coding", Font.PLAIN, 15));
			textPosNo.setBounds(134, 51, 198, 30);
			contentPanel.add(textPosNo);
			textPosNo.setColumns(10);
		}
		{
			textPosName = new JTextField();
			textPosName.setHorizontalAlignment(SwingConstants.CENTER);
			textPosName.setFont(new Font("D2Coding", Font.PLAIN, 15));
			textPosName.setColumns(10);
			textPosName.setBounds(134, 101, 198, 30);
			contentPanel.add(textPosName);
		}
		{
			JLabel lblNewLabel = new JLabel("직급순번");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(42, 51, 80, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("직급명");
			lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(42, 101, 80, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("*주의 직급순번 오름차순으로 정렬되기 때문에 높은 직급일수록");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(12, 155, 410, 20);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("낮은 숫자로 배정해주세요");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(12, 173, 410, 20);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
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
				cancelButton = new JButton("취소");
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

			if(textPosName.getText().isBlank() || textPosNo.getText().isBlank()) {
				JOptionPane.showMessageDialog(this, "공백만으로는 등록이 불가합니다.");
				return;
				
			}
			
			try {

			Position pos = new Position(Integer.parseInt(textPosNo.getText()),textPosName.getText());
			int result = dao.writePos(pos);
			JOptionPane.showMessageDialog(this, result + " 개 직급이 등록되었습니다.");
			textPosName.setText("");
			textPosNo.setText("");
			return;
			
				
			} catch (Exception e2) {
				// TODO: handle exception
				
				JOptionPane.showMessageDialog(this, "직급순번에는 숫자만 입력되어야 합니다.");
				return;
			}

		} else if (obj == cancelButton) {

			dispose();

		}

	}

}
