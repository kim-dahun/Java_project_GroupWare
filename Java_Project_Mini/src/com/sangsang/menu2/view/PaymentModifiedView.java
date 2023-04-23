package com.sangsang.menu2.view;

import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.controller.PaymentDaoImpl;
import com.sangsang.menu.model.PaymentContent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentModifiedView extends JFrame {

	private JPanel contentPane;
	private PaymentDaoImpl dao = PaymentDaoImpl.getInstance();
	private String[] combolist = dao.readEmpno();
	private String[] combolist2 = { "기본급", "상여", "연장수당", "식대", "차량유지비", "퇴직금", "기타공제", "기타공제사유" };
	private JTextField textInput;
	private JComboBox comboBox;
	private JComboBox comboConInput;
	private JButton btntextinput;
	private JButton btnSave;
	private int[] taxlist;
	private PaymentContent payment = new PaymentContent();

	private JScrollPane scrollPane;
	private String name = "";
	private String deptname = "";
	private inputValue input = inputValue.getInstance();
	private JButton btnSearch;
	private JTextArea textArea;
	private JTextField textPaymonth;

	static class inputValue {

		private static inputValue inputInstance;

		private inputValue() {

		}

		public static inputValue getInstance() {
			if (inputInstance == null) {

				inputInstance = new inputValue();

			}

			return inputInstance;

		}

		String empno = "";
		String posname = "";
		String deptname = "";
		String name = "";
		String paymonth = "";
		int basesal = 0;
		int bouns = 0;
		int ot = 0;
		int ml = 0;
		int vm = 0;
		int sv = 0;
		int oz = 0;
		String ozrs = "";

		public String getEmpno() {
			return empno;
		}

		public void setEmpno(String empno) {
			this.empno = empno;
		}

		public String getPosname() {
			return posname;
		}

		public void setPosname(String posname) {
			this.posname = posname;
		}

		public String getDeptname() {
			return deptname;
		}

		public void setDeptname(String deptname) {
			this.deptname = deptname;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPaymonth() {
			return paymonth;
		}

		public void setPaymonth(String paymonth) {
			this.paymonth = paymonth;
		}

		public int getBasesal() {
			return basesal;
		}

		public void setBasesal(int basesal) {
			this.basesal = basesal;
		}

		public int getBouns() {
			return bouns;
		}

		public void setBouns(int bouns) {
			this.bouns = bouns;
		}

		public int getOt() {
			return ot;
		}

		public void setOt(int ot) {
			this.ot = ot;
		}

		public int getMl() {
			return ml;
		}

		public void setMl(int ml) {
			this.ml = ml;
		}

		public int getVm() {
			return vm;
		}

		public void setVm(int vm) {
			this.vm = vm;
		}

		public int getSv() {
			return sv;
		}

		public void setSv(int sv) {
			this.sv = sv;
		}

		public int getOz() {
			return oz;
		}

		public void setOz(int oz) {
			this.oz = oz;
		}

		public String getOzrs() {
			return ozrs;
		}

		public void setOzrs(String ozrs) {
			this.ozrs = ozrs;
		}

		public void setValueReset() {
			setBasesal(0);
			setBouns(0);
			setDeptname("");
			setPosname("");
			setOt(0);
			setSv(0);
			setMl(0);
			setVm(0);
			setOz(0);
			setOzrs("");
			setName("");
			setEmpno("");
			setPaymonth("");
		}
		
		public void setReadValue(PaymentContent pay) {

			if (pay == null) {
				setValueReset();
				return;
			}

			setBasesal(pay.getBaseSal());
			setBouns(pay.getBonus());
			setDeptname(pay.getDeptName());
			setPosname(pay.getPosName());
			setOt(pay.getOvertimePay());
			setSv(pay.getSeverancePay());
			setMl(pay.getMeals());
			setVm(pay.getVehicleMaintenance());
			setOz(pay.getOtherDeductions());
			setOzrs(pay.getDeductionsReason());
			setName(pay.getName());
			setEmpno(pay.getEmpno());
			setPaymonth(pay.getPaymentMonth());

		}

	}

	// 선택지에 따라 입력되는 값이 불러오는 메서드가 달라지는 메서드

	/**
	 * Launch the application.
	 */
	public static void showMyModifiedFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentModifiedView frame = new PaymentModifiedView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentModifiedView() {

		initialize();

	}

	public void refreshTax() {

		if (payment == null) {
			taxlist = new int[5];
			taxlist[0] = 0;
			taxlist[1] = 0;
			taxlist[2] = 0;
			taxlist[3] = 0;
			taxlist[4] = 0;

		} else {
			this.taxlist = dao.taxArray(payment);
		}

		textArea.setText("귀속연월 : " + input.getPaymonth() + "\r\n" + "이름 : " + input.getName() + "\r\n" + "사번 : "+ input.getEmpno()+" \r\n"
				+ "부서 : " + input.getDeptname() + "\r\n" + "직급 : " + input.getPosname() + "\r\n" + "기본급 : "
				+ input.getBasesal() + "\r\n" + "상여 : " + input.getBouns() + "\r\n" + "연장수당 : " + input.getOt() + "\r\n"
				+ "식대 : " + input.getMl() + "\r\n" + "차량유지비 : " + input.getVm() + "\r\n" + "퇴직금 : " + input.getSv()
				+ "\r\n" + "소득세 : " + taxlist[0] + "\r\n지방소득세 : " + taxlist[1] + "\r\n" + "건강보험 : " + taxlist[2]
				+ "\r\n" + "국민연금 : " + taxlist[3] + "\r\n" + "고용보험 : " + taxlist[4] + "\r\n" + "기타공제 : " + input.getOz()
				+ "\r\n" + "공제사유 : " + input.getOzrs() + "\r\n");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		inputValue inputvl = new inputValue();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 392, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(combolist));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(107, 18, 187, 23);
		contentPane.add(comboBox);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 345, 352, 270);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		refreshTax();
		textArea.setText("귀속연월 : " + input.getPaymonth() + "\r\n" + "이름 : " + input.getName() + "\r\n" + "사번 : "+ input.getEmpno() +"\r\n"
				+ "부서 : " + input.getDeptname() + "\r\n" + "직급 : " + input.getPosname() + "\r\n" + "기본급 : "
				+ input.getBasesal() + "\r\n" + "상여 : " + input.getBouns() + "\r\n" + "연장수당 : " + input.getOt() + "\r\n"
				+ "식대 : " + input.getMl() + "\r\n" + "차량유지비 : " + input.getVm() + "\r\n" + "퇴직금 : " + input.getSv()
				+ "\r\n" + "소득세 : " + taxlist[0] + "\r\n지방소득세 : " + taxlist[1] + "\r\n" + "건강보험 : " + taxlist[2]
				+ "\r\n" + "국민연금 : " + taxlist[3] + "\r\n" + "고용보험 : " + taxlist[4] + "\r\n" + "기타공제 : " + input.getOz()
				+ "\r\n" + "공제사유 : " + input.getOzrs() + "\r\n");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("사번 선택");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 18, 83, 23);
		contentPane.add(lblNewLabel);

		comboConInput = new JComboBox();
		comboConInput.setBounds(107, 165, 187, 23);
		comboConInput.setModel(new DefaultComboBoxModel(combolist2));
		comboConInput.setSelectedIndex(0);
		contentPane.add(comboConInput);

		JLabel lblNewLabel_1_1 = new JLabel("입력항목");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(12, 165, 83, 23);
		contentPane.add(lblNewLabel_1_1);

		textInput = new JTextField();
		textInput.setToolTipText("YYYY-MM 형태로 입력하세요");
		textInput.setColumns(10);
		textInput.setBounds(107, 195, 187, 26);
		contentPane.add(textInput);

		btntextinput = new JButton("반영하기");
		btntextinput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);
			}
		});
		btntextinput.setBounds(135, 231, 108, 23);
		contentPane.add(btntextinput);

		btnSave = new JButton("수정하기");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btnSave.setBounds(135, 312, 108, 23);
		contentPane.add(btnSave);

		btnSearch = new JButton("불러오기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);
			}
		});
		btnSearch.setBounds(135, 87, 108, 23);
		contentPane.add(btnSearch);

		textPaymonth = new JTextField();
		textPaymonth.setHorizontalAlignment(SwingConstants.CENTER);
		textPaymonth.setBounds(107, 51, 187, 21);
		contentPane.add(textPaymonth);
		textPaymonth.setColumns(10);
	}

	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();
		if (obj == btntextinput) {
			if (textInput.getText().isBlank()) {
				JOptionPane.showMessageDialog(this, "입력된 내용이 없습니다.");
				return;
			}
			inputText(comboConInput.getSelectedItem().toString(), textInput.getText());

			return;
		} else if (obj == btnSave) {
			String regex = "\\d{4}-\\d{2}";
			if (payment == null || payment.getPaymentMonth().isBlank() || !input.getPaymonth().matches(regex)) {
				JOptionPane.showMessageDialog(this, "입력된 내용이 없거나, 귀속연월이 입력되지 않은 상태입니다.");
				return;
			}

			int qus = JOptionPane.showConfirmDialog(this,
					payment.getName() + " " + payment.getPosName() + " 님의 급여를 정말 수정하시겠습니까?", "등록 전 최종확인",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (qus == 0) {
				payment.setIncomeTax(taxlist[0]);
				payment.setResidentTax(taxlist[1]);
				payment.setHealthInsurancePremium(taxlist[2]);
				payment.setNationalPension(taxlist[3]);
				payment.setEmploymentInsurance(taxlist[4]);
				
				
				int result = dao.modifiedPay(payment);
				JOptionPane.showMessageDialog(this, result + " 개의 급여내역이 수정되었습니다.");

			} else {

			}
			return;

		} else if (obj == btnSearch) {

			payment = new PaymentContent();
			input.setValueReset();
			refreshTax();
			if (textPaymonth.getText().isBlank()) {

				JOptionPane.showMessageDialog(this, "귀속연월을 반드시 입력해주세요. YYYY-MM");
				return;

			}

			
			String sel = comboBox.getSelectedItem().toString();
			String pmt = textPaymonth.getText();
			
			payment = dao.search(sel, pmt);
			
			
			input.setReadValue(payment);
			refreshTax();

		}

	}

	public void inputText(String select, String value) {

		try {
			switch (select) {

			case "기본급":
				payment.setBaseSal(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "상여":
				payment.setBonus(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "연장수당":
				payment.setOvertimePay(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "식대":
				payment.setMeals(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "차량유지비":
				payment.setVehicleMaintenance(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "퇴직금":
				payment.setSeverancePay(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "기타공제":
				payment.setOtherDeductions(Integer.parseInt(value));
				textInput.setText("");
				break;
			case "기타공제사유":
				payment.setDeductionsReason(value);
				textInput.setText("");
				break;
			

			default:

				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "잘못된 입력입니다.");
		}
		input.setReadValue(payment);
		refreshTax();
	}
}
