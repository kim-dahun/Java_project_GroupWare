package com.sangsang.menu2.view;

import java.awt.EventQueue;
import static com.sangsang.menu.model.PaymentContent.Entity.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.sangsang.menu.controller.PaymentDaoImpl;
import com.sangsang.menu.controller.PaymentDaoImpl.FileBackup;
import com.sangsang.menu.model.PaymentContent;
import com.sangsang.menu.model.Position;

import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PaymentContentView extends JFrame {

	private PaymentDaoImpl dao = PaymentDaoImpl.getInstance();
	private List<PaymentContent> paylist;
	private DefaultTableModel model;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JButton btnAddPay;
	private JButton btnModifiedPay;
	private JButton btnDeletePay;
	private JButton btnPrintFile;
	private JButton btnSearch;
	private String[] colNamePay = { "이름", "사번", "부서", "직급", "귀속연월", "기본급", "상여", "연장수당", "식대", "차량유지비", "퇴직금", "소득세",
			"주민세", "건강보험", "국민연금", "고용보험", "기타공제", "공제사유" }; // TODO
	private JButton btnPrintPay;
	private JButton btnRefresh;
	private JComboBox comboBoxCondition;

	/**
	 * Launch the application.
	 */
	public static void showMypaymentContentFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentContentView frame = new PaymentContentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshtableSearch() {

		table.setModel(new DefaultTableModel(null, colNamePay));
		model = new DefaultTableModel(null, colNamePay);
		for (PaymentContent x : this.paylist) {

			Object[] pos = { x.getName(), x.getEmpno(), x.getDeptName(), x.getPosName(),x.getPaymentMonth() ,x.getBaseSal(), x.getBonus(),x.getOvertimePay(),x.getMeals(),x.getVehicleMaintenance(),x.getSeverancePay(),x.getIncomeTax(),x.getResidentTax(),x.getHealthInsurancePremium(),x.getNationalPension(),x.getEmploymentInsurance(),x.getOtherDeductions(),x.getDeductionsReason() };

			model.addRow(pos);

		}
		table.getColumnModel().getColumn(2).setPreferredWidth(156);
		table.getColumnModel().getColumn(3).setPreferredWidth(61);
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
		table.getColumnModel().getColumn(7).setPreferredWidth(89);
		table.getColumnModel().getColumn(8).setPreferredWidth(84);
		table.getColumnModel().getColumn(9).setPreferredWidth(92);

		table.setModel(model);

	}

	public void refreshtable() {

		paylist = dao.read();
		refreshtableSearch();

	}

	public PaymentContentView() {
		setTitle("급여 관리 시스템");

		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1160, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("급여 관리 시스템");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 35, 1120, 46);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 228, 1120, 415);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\uC774\uB984", "\uC0AC\uBC88", "\uBD80\uC11C", "\uC9C1\uAE09",
						"\uADC0\uC18D\uC5F0\uC6D4", "\uAE30\uBCF8\uAE09", "\uC0C1\uC5EC", "\uC5F0\uC7A5\uC218\uB2F9",
						"\uC2DD\uB300", "\uCC28\uB7C9\uC720\uC9C0\uBE44", "\uD1F4\uC9C1\uAE08", "\uC18C\uB4DD\uC138",
						"\uC8FC\uBBFC\uC138", "\uAC74\uAC15\uBCF4\uD5D8", "\uAD6D\uBBFC\uC5F0\uAE08",
						"\uACE0\uC6A9\uBCF4\uD5D8", "\uAE30\uD0C0\uACF5\uC81C", "\uACF5\uC81C\uC0AC\uC720" }));
		refreshtable();
		table.getColumnModel().getColumn(2).setPreferredWidth(156);
		table.getColumnModel().getColumn(3).setPreferredWidth(61);
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
		table.getColumnModel().getColumn(7).setPreferredWidth(89);
		table.getColumnModel().getColumn(8).setPreferredWidth(84);
		table.getColumnModel().getColumn(9).setPreferredWidth(92);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField.setBounds(12, 181, 139, 37);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btnSearch.setBounds(163, 181, 66, 37);
		contentPane.add(btnSearch);

		btnAddPay = new JButton("급여 등록");
		btnAddPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btnAddPay.setBounds(291, 181, 105, 37);
		contentPane.add(btnAddPay);

		btnModifiedPay = new JButton("급여 수정");
		btnModifiedPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleclickEvent(e);
				
			}
		});
		btnModifiedPay.setBounds(408, 181, 105, 37);
		contentPane.add(btnModifiedPay);

		btnDeletePay = new JButton("급여 삭제");
		btnDeletePay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
			}
		});
		btnDeletePay.setBounds(525, 181, 105, 37);
		contentPane.add(btnDeletePay);

		btnPrintFile = new JButton("급여 대장 백업");
		btnPrintFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
			}
		});
		btnPrintFile.setBounds(784, 181, 171, 37);
		contentPane.add(btnPrintFile);

		btnPrintPay = new JButton("급여 명세서 출력");
		btnPrintPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);
			}
		});
		btnPrintPay.setBounds(967, 181, 165, 37);
		contentPane.add(btnPrintPay);

		comboBoxCondition = new JComboBox();
		comboBoxCondition.setModel(new DefaultComboBoxModel(new String[] { "사원명", "부서", "귀속연월" }));
		comboBoxCondition.setBounds(12, 147, 139, 23);
		contentPane.add(comboBoxCondition);

		btnRefresh = new JButton("급여 갱신");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refreshtable();

			}
		});
		btnRefresh.setBounds(642, 181, 105, 37);
		contentPane.add(btnRefresh);
	}

	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnSearch) {

			switch (comboBoxCondition.getSelectedItem().toString()) {

			case "사원명":
				paylist = dao.read(textField.getText(), 0);
				break;
			case "부서":
				paylist = dao.read(textField.getText(), 1);
				break;
			case "귀속연월":
				String regex = "\\d{4}-\\d{2}";
				if (textField.getText().matches(regex)) {
					paylist = dao.read(textField.getText(), 2);

				} else {
					JOptionPane.showMessageDialog(this, "형식은 반드시 YYYY-MM 형식을 지켜주세요.");
				}
				break;

			}
			refreshtableSearch();
			return;

		} else if (obj == btnAddPay) {

			PaymentAddView.showMyAddFrame();

		} else if (obj == btnDeletePay) {
			int select = table.getSelectedRow();
			if (select == -1) {

				return;
			}

			PaymentContent pay = paylist.get(select);

			int result = dao.deletePay(pay);

			JOptionPane.showMessageDialog(this, result + "개 행이 삭제되었습니다.");
			
			refreshtable();
		} else if (obj == btnModifiedPay) {

			PaymentModifiedView.showMyModifiedFrame();
			
		} else if (obj == btnPrintFile) {

			// 급여대장 파일변환
			int selecs = JOptionPane.showConfirmDialog(this, "정말로 백업을 진행하시겠습니까?", "백업 여부 확인", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if(selecs==0) {
				
				int result = FileBackup.FileBackUp();
				
				if(result == 1) {
					
					JOptionPane.showMessageDialog(this, "급여리스트 백업에 성공했습니다.");
					
				} else {
					
					JOptionPane.showMessageDialog(this, "급여리스트 백업에 실패했습니다.");
					
				}
			
			}
			
			return;
			

		} else if (obj == btnPrintPay) {

			PaymentPrintView.showMyprintpreview();
			
			// 명세서 출력

		}

	}
}
