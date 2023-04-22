package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.controller.EmployeeDaoImpl;
import com.sangsang.menu.controller.MenuDeptDaoImpl;
import com.sangsang.menu.controller.MenuPosDaoImpl;
import com.sangsang.menu.model.Position;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class EmployeeModifiedView extends JFrame {

	private JPanel contentPane;
	private JTextField textPw;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textEmpno;
	private JButton btnModify;
	private JButton btnClose;
	private JComboBox comboDept;
	private JComboBox comboPos;
	private Account acc;
	private EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();
	private String[] comboDeptlist = MenuDeptDaoImpl.getInstance().readName();
	private String[] comboPoslist = MenuPosDaoImpl.getInstance().readName();

	/**
	 * Launch the application.
	 */
	public static void showMyEmpModifedFrame(Account acc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeModifiedView frame = new EmployeeModifiedView(acc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeModifiedView(Account acc) {
		setTitle("사원 수정");
		this.acc = acc;
		initialize();
		setModityFrame();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 315, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textPw = new JTextField();
		textPw.setHorizontalAlignment(SwingConstants.CENTER);
		textPw.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPw.setBounds(125, 98, 139, 26);
		panel.add(textPw);
		textPw.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("비밀번호");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 98, 101, 26);
		panel.add(lblNewLabel);
		
		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textName.setColumns(10);
		textName.setBounds(125, 134, 139, 26);
		panel.add(textName);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(12, 134, 101, 26);
		panel.add(lblNewLabel_1);
		
		textPhone = new JTextField();
		textPhone.setHorizontalAlignment(SwingConstants.CENTER);
		textPhone.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textPhone.setColumns(10);
		textPhone.setBounds(125, 170, 139, 26);
		panel.add(textPhone);
		
		JLabel lblNewLabel_1_1 = new JLabel("전화번호");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(12, 170, 101, 26);
		panel.add(lblNewLabel_1_1);
		
		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEmail.setColumns(10);
		textEmail.setBounds(125, 206, 139, 26);
		panel.add(textEmail);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("이메일");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(12, 206, 101, 26);
		panel.add(lblNewLabel_1_1_1);
		
		textEmpno = new JTextField();
		textEmpno.setHorizontalAlignment(SwingConstants.CENTER);
		textEmpno.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEmpno.setColumns(10);
		textEmpno.setBounds(125, 242, 139, 26);
		panel.add(textEmpno);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("사번");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(12, 242, 101, 26);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("직급");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1.setBounds(12, 278, 101, 26);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("부서");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1_1.setBounds(12, 314, 101, 26);
		panel.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("사원 정보 수정");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("D2Coding", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(12, 21, 252, 26);
		panel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		btnModify = new JButton("수정하기");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleClickEvent(e);
				
			}
		});
		btnModify.setBounds(16, 391, 97, 33);
		panel.add(btnModify);
		
		btnClose = new JButton("나가기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleClickEvent(e);
			}
		});
		btnClose.setBounds(167, 391, 97, 33);
		panel.add(btnClose);
		
		comboPos = new JComboBox();
		comboPos.setModel(new DefaultComboBoxModel(comboPoslist));
		comboPos.setBounds(125, 278, 139, 26);
		panel.add(comboPos);
		
		comboDept = new JComboBox();
		comboDept.setModel(new DefaultComboBoxModel(comboDeptlist));
		comboDept.setBounds(125, 316, 139, 26);
		panel.add(comboDept);
	}

	protected void handleClickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btnClose) {
			
		} else if (obj == btnModify) {
			int result = checkClean();
			
			
			if(result == 0) {
				return;
				
			}
			makeUpdate();
			result = dao.modifiedAccount(acc);

			

			JOptionPane.showMessageDialog(this, result + " 개의 사원이 수정되었습니다.");
			
		}
		
		dispose();
		
	}
	
	private void makeUpdate() {
		// TODO Auto-generated method stub
		
		List<Position> list = MenuPosDaoImpl.getInstance().read();
		int posNo = 0;
		for(int i = 0; i<list.size();i++) {
			
			if(list.get(i).getPosName().equals(comboPos.getSelectedItem().toString())) {
				
				posNo = list.get(i).getPosNo();
				
			}
			
		}
		
		acc.setDeptName(comboDept.getSelectedItem().toString());
		acc.setDeptNo((comboDept.getSelectedIndex()+1)*10);
		acc.setPositionName(comboPos.getSelectedItem().toString());
		acc.setPositionNo(posNo);
		acc.setEmail(textEmail.getText());
		acc.setPhone(textPhone.getText());
		acc.setName(textName.getText());
		acc.setPw(textPw.getText());
		acc.setEmpNo(textEmpno.getText());
	}

	public void setModityFrame () {
		
		textName.setText(acc.getName());
		
		if(acc.getEmpNo()==null) {
			textEmpno.setText("필수 입력");
			
		} else {
			textEmpno.setText(acc.getEmpNo());
			
		}
		textEmail.setText(acc.getEmail());
		textPhone.setText(acc.getPhone());
		if(acc.getDeptNo()==0) {
			comboDept.setSelectedIndex(0);
		} else {
			comboDept.setSelectedIndex((acc.getDeptNo()/10)-1);
		}
		
		if(acc.getPositionName()==null) {
			comboPos.setSelectedIndex(0);
		} else {
			comboPos.setSelectedItem(acc.getPositionName());
		}
		
		
		
		
	}
	
	public int checkClean() {
		int result = 1;
		if (textPw.getText().isBlank() || textPhone.getText().isBlank()
				|| textEmail.getText().isBlank() || textName.getText().isBlank()
				|| textEmpno.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "모든 칸은 입력 필수입니다. 모두 입력해주세요.", "누락 칸 발견", JOptionPane.CLOSED_OPTION);
			return 0;
		}

		if (textPw.getText().length() < 10 || textPw.getText().length() > 15) {
			JOptionPane.showMessageDialog(this, "비밀번호는 10자리 이상 15자리 이하여야 합니다.", "비밀번호 길이 오류",
					JOptionPane.CLOSED_OPTION);
			return 0;
		}

		if (!textPw.getText().matches(".*[a-z].*") || !textPw.getText().matches(".*[0-9].*")) {
			JOptionPane.showMessageDialog(this, "비밀번호는 반드시 영소문자,숫자를 포함해야 합니다..", "비밀번호 형식 오류",
					JOptionPane.CLOSED_OPTION);
			return 0;
		}

		if (!textEmail.getText().matches(".*[@].*")) {
			JOptionPane.showMessageDialog(this, "이메일 형식을 지켜주세요. a@a...", "이메일 입력 형식 오류", JOptionPane.CLOSED_OPTION);
			return 0;
		}

		if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", textPhone.getText())) {
			JOptionPane.showMessageDialog(this, "전화번호 형식을 지켜주세요. 010-0000-0000", "전화번호 입력 형식 오류",
					JOptionPane.CLOSED_OPTION);
			return 0;
		}
		String regex = "\\d{4}-\\d{3}";
		if (!textEmpno.getText().matches(regex)) {
			JOptionPane.showMessageDialog(this, "사원번호 형식을 지켜주세요. 0000-000", "사원번호 입력 형식 오류",
					JOptionPane.CLOSED_OPTION);
			return 0;
			
			
		}
		
		
		
		for(int i = 0; i < dao.read().size() ; i++) {
			
			if(textEmpno.getText().equals(dao.read().get(i).getEmpNo())) {
				JOptionPane.showMessageDialog(this, "중복된 사원번호가 존재합니다.", "사원번호 입력 형식 오류",
						JOptionPane.CLOSED_OPTION);
				return 0;
				
			}
			
		}
		
		
		
		return result;
		
	}
	
}
