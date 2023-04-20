package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.account.model.AccountLocalUser;
import com.sangsang.menu.controller.EmployeeDaoImpl;
import com.sangsang.menu.model.PaymentContent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSearchAccount;
	private JButton btnModifiedAccount;
	private JButton btnDeleteAccount;
	private final EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();

	private List<Account> acclist;
	private String[] colNameEmp = { "아이디", "사원번호", "이름", "부서명", "직급", "휴대전화", "이메일", "관리자 여부" };
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private DefaultTableModel model;
	private JButton btnRefresh;

	/**
	 * Launch the application.
	 */
	public static void showMyemployee() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeView frame = new EmployeeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshtableSearch() {

		table.setModel(new DefaultTableModel(null, colNameEmp));
		model = new DefaultTableModel(null, colNameEmp);
		for (Account x : this.acclist) {

			Object[] acc = { x.getId(), x.getEmpNo(), x.getName(), x.getDeptName(), x.getPositionName(), x.getPhone(),
					x.getEmail(), x.getIsAdmin() };

			model.addRow(acc);

		}

		table.setModel(model);

	}

	public void refreshtable() {

		acclist = dao.read();
		refreshtableSearch();

	}

	public EmployeeView() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				refreshtable();
				
			}
		});

		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 435);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 117, 770, 250);
		contentPane.add(scrollPane);

		table = new JTable();

		refreshtable();

		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("사원 명부");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 770, 33);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("* 사원 명부에서는 아이디 변경 및 급여 관련 작업을 제외한 작업들을 진행할 수 있습니다");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 371, 581, 15);
		contentPane.add(lblNewLabel_1);

		btnDeleteAccount = new JButton("사원 삭제");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleClickEvent(e);

			}
		});
		btnDeleteAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnDeleteAccount.setBounds(443, 74, 142, 33);
		contentPane.add(btnDeleteAccount);

		btnSearchAccount = new JButton("사원 검색");
		btnSearchAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleClickEvent(e);

			}
		});
		btnSearchAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnSearchAccount.setBounds(234, 74, 142, 33);
		contentPane.add(btnSearchAccount);

		btnModifiedAccount = new JButton("사원 수정");
		btnModifiedAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleClickEvent(e);
			}
		});
		btnModifiedAccount.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnModifiedAccount.setBounds(12, 74, 142, 33);
		contentPane.add(btnModifiedAccount);

		btnRefresh = new JButton("새로 고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClickEvent(e);

			}
		});
		btnRefresh.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnRefresh.setBounds(640, 74, 142, 33);
		contentPane.add(btnRefresh);
	}

	protected void handleClickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnSearchAccount) {

			String search = JOptionPane.showInputDialog(this, "찾고 싶은 사원의 이름, 부서, 사번 중 하나를 입력해주세요.");

			acclist = dao.read(search);

			refreshtableSearch();

			return;

		} else if (obj == btnModifiedAccount) {

			String search = JOptionPane.showInputDialog(this, "수정하고 싶은 사원의 아이디를 입력해주세요.");

			Account acc = dao.SearchOne(search);
			
			
				if(acc.getId()==null) {
					JOptionPane.showMessageDialog(this, "검색한 ID를 확인하거나 admin 계정이 아닌지 확인해주세요. admin 계정은 수정할 수 없습니다.");
					System.out.println("메서드 정상 작동");
					return;
				} else {
				EmployeeModifiedView.showMyEmpModifedFrame(acc);
				}
			
			
			

		} else if (obj == btnDeleteAccount) {

			String search = JOptionPane.showInputDialog(this, "삭제하고 싶은 사원의 아이디를 입력해주세요.");

			Account acc = dao.SearchOne(search);

			int result = dao.deleteAccount(acc);

			if (result == 0) {

				JOptionPane.showMessageDialog(this, "검색한 ID를 확인하거나 admin 계정이 아닌지 확인해주세요. admin 계정은 수정할 수 없습니다.");
				return;
			}

			JOptionPane.showMessageDialog(this, result + " 개의 사원이 삭제되었습니다.");

		} else if (obj == btnRefresh) {

		}
		refreshtable();
	}
}
