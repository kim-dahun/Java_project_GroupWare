package com.sangsang.menu2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sangsang.menu.controller.PaymentDaoImpl;
import com.sangsang.menu.model.PaymentContent;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PaymentPrintView extends JFrame {

	private JPanel contentPane;
	private PaymentDaoImpl dao = PaymentDaoImpl.getInstance();
	private String[] combolist = dao.readEmpno();
	private String[] combolist2 = dao.readPaymentMonth();
	private JTextField textField;
	private JTextField textbasesal;
	private JTextField textField_2;
	private JTextField textotsal;
	private JTextField textVmsal;
	private JTextField textMeal;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textSevsal;
	private JTextField textBonus;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textIncome;
	private JTextField textField_14;
	private JTextField textResi;
	private JTextField textNation;
	private JTextField textHeal;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textOtherDdc;
	private JTextField textEmpIns;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textDdcRs;
	private JTextField textField_28;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textFie;
	private JTextField textField_5;
	private JTextField textname;
	private JTextField textempno;
	private JTextField textposname;
	private JTextField textdeptname;
	private JTextField textField_4;
	private JTextField textField_8;
	private JTextField textField_15;
	private JTextField txtX_1;
	private JTextField textField_9;
	private JTextField txtX;
	private JButton btnSearch;
	private JButton btnPrint;
	private JButton btnPrintAll;
	private JButton btnClose;
	private JComboBox comboEmpno;
	private JComboBox comboPayment;
	private JLabel lblPaymonth;
	private JPanel panel;
	private PaymentContent pay;

	/**
	 * Launch the application.
	 */
	public static void showMyprintpreview() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentPrintView frame = new PaymentPrintView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentPrintView() {

		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("급여명세서 출력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 813);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setText("기본급");
		textField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(12, 276, 145, 33);
		panel.add(textField);
		textField.setColumns(10);

		textbasesal = new JTextField();
		textbasesal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textbasesal.setHorizontalAlignment(SwingConstants.CENTER);
		textbasesal.setColumns(10);
		textbasesal.setBounds(157, 276, 145, 33);
		panel.add(textbasesal);

		textField_2 = new JTextField();
		textField_2.setText("연장근로수당");
		textField_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(12, 309, 145, 33);
		panel.add(textField_2);

		textotsal = new JTextField();
		textotsal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textotsal.setHorizontalAlignment(SwingConstants.CENTER);
		textotsal.setColumns(10);
		textotsal.setBounds(157, 309, 145, 33);
		panel.add(textotsal);

		textVmsal = new JTextField();
		textVmsal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textVmsal.setHorizontalAlignment(SwingConstants.CENTER);
		textVmsal.setColumns(10);
		textVmsal.setBounds(157, 375, 145, 33);
		panel.add(textVmsal);

		textMeal = new JTextField();
		textMeal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textMeal.setHorizontalAlignment(SwingConstants.CENTER);
		textMeal.setColumns(10);
		textMeal.setBounds(157, 342, 145, 33);
		panel.add(textMeal);

		textField_6 = new JTextField();
		textField_6.setText("식대");
		textField_6.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setColumns(10);
		textField_6.setBounds(12, 342, 145, 33);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setText("차량유지비");
		textField_7.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setColumns(10);
		textField_7.setBounds(12, 375, 145, 33);
		panel.add(textField_7);

		textSevsal = new JTextField();
		textSevsal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textSevsal.setHorizontalAlignment(SwingConstants.CENTER);
		textSevsal.setColumns(10);
		textSevsal.setBounds(157, 441, 145, 33);
		panel.add(textSevsal);

		textBonus = new JTextField();
		textBonus.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textBonus.setHorizontalAlignment(SwingConstants.CENTER);
		textBonus.setColumns(10);
		textBonus.setBounds(157, 408, 145, 33);
		panel.add(textBonus);

		textField_10 = new JTextField();
		textField_10.setText("상여");
		textField_10.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setColumns(10);
		textField_10.setBounds(12, 408, 145, 33);
		panel.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setText("퇴직급여");
		textField_11.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setColumns(10);
		textField_11.setBounds(12, 441, 145, 33);
		panel.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setText("소득세");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_12.setColumns(10);
		textField_12.setBounds(395, 276, 145, 33);
		panel.add(textField_12);

		textIncome = new JTextField();
		textIncome.setHorizontalAlignment(SwingConstants.CENTER);
		textIncome.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textIncome.setColumns(10);
		textIncome.setBounds(540, 276, 145, 33);
		panel.add(textIncome);

		textField_14 = new JTextField();
		textField_14.setText("지방소득세");
		textField_14.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setColumns(10);
		textField_14.setBounds(395, 309, 145, 33);
		panel.add(textField_14);

		textResi = new JTextField();
		textResi.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textResi.setHorizontalAlignment(SwingConstants.CENTER);
		textResi.setColumns(10);
		textResi.setBounds(540, 309, 145, 33);
		panel.add(textResi);

		textNation = new JTextField();
		textNation.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textNation.setHorizontalAlignment(SwingConstants.CENTER);
		textNation.setColumns(10);
		textNation.setBounds(540, 375, 145, 33);
		panel.add(textNation);

		textHeal = new JTextField();
		textHeal.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textHeal.setHorizontalAlignment(SwingConstants.CENTER);
		textHeal.setColumns(10);
		textHeal.setBounds(540, 342, 145, 33);
		panel.add(textHeal);

		textField_18 = new JTextField();
		textField_18.setText("건강보험");
		textField_18.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setColumns(10);
		textField_18.setBounds(395, 342, 145, 33);
		panel.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setText("국민연금");
		textField_19.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setColumns(10);
		textField_19.setBounds(395, 375, 145, 33);
		panel.add(textField_19);

		textOtherDdc = new JTextField();
		textOtherDdc.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textOtherDdc.setHorizontalAlignment(SwingConstants.CENTER);
		textOtherDdc.setColumns(10);
		textOtherDdc.setBounds(540, 441, 145, 33);
		panel.add(textOtherDdc);

		textEmpIns = new JTextField();
		textEmpIns.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEmpIns.setHorizontalAlignment(SwingConstants.CENTER);
		textEmpIns.setColumns(10);
		textEmpIns.setBounds(540, 408, 145, 33);
		panel.add(textEmpIns);

		textField_22 = new JTextField();
		textField_22.setText("고용보험");
		textField_22.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setColumns(10);
		textField_22.setBounds(395, 408, 145, 33);
		panel.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setText("기타공제");
		textField_23.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setColumns(10);
		textField_23.setBounds(395, 441, 145, 33);
		panel.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setBackground(SystemColor.menu);
		textField_24.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_24.setText("수당항목");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setColumns(10);
		textField_24.setBounds(12, 243, 145, 33);
		panel.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setBackground(SystemColor.menu);
		textField_25.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_25.setText("금액");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setColumns(10);
		textField_25.setBounds(157, 243, 145, 33);
		panel.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setBackground(SystemColor.menu);
		textField_26.setText("수당항목");
		textField_26.setHorizontalAlignment(SwingConstants.CENTER);
		textField_26.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_26.setColumns(10);
		textField_26.setBounds(395, 243, 145, 33);
		panel.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setBackground(SystemColor.menu);
		textField_27.setText("금액");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_27.setColumns(10);
		textField_27.setBounds(540, 243, 145, 33);
		panel.add(textField_27);

		textDdcRs = new JTextField();
		textDdcRs.setHorizontalAlignment(SwingConstants.CENTER);
		textDdcRs.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textDdcRs.setColumns(10);
		textDdcRs.setBounds(157, 473, 528, 33);
		panel.add(textDdcRs);

		textField_28 = new JTextField();
		textField_28.setText("공제사유");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_28.setColumns(10);
		textField_28.setBounds(12, 473, 145, 33);
		panel.add(textField_28);

		textField_1 = new JTextField();
		textField_1.setBackground(SystemColor.menu);
		textField_1.setText("부서");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("D2Coding", Font.BOLD, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(12, 126, 145, 33);
		panel.add(textField_1);

		textField_3 = new JTextField();
		textField_3.setBackground(SystemColor.menu);
		textField_3.setText("직급");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("D2Coding", Font.BOLD, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(12, 159, 145, 33);
		panel.add(textField_3);

		textFie = new JTextField();
		textFie.setBackground(SystemColor.menu);
		textFie.setText("이름");
		textFie.setHorizontalAlignment(SwingConstants.CENTER);
		textFie.setFont(new Font("D2Coding", Font.BOLD, 15));
		textFie.setColumns(10);
		textFie.setBounds(12, 61, 145, 33);
		panel.add(textFie);

		textField_5 = new JTextField();
		textField_5.setBackground(SystemColor.menu);
		textField_5.setText("사번");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("D2Coding", Font.BOLD, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(12, 94, 145, 33);
		panel.add(textField_5);

		textname = new JTextField();
		textname.setHorizontalAlignment(SwingConstants.CENTER);
		textname.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textname.setColumns(10);
		textname.setBounds(157, 61, 145, 33);
		panel.add(textname);

		textempno = new JTextField();
		textempno.setHorizontalAlignment(SwingConstants.CENTER);
		textempno.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textempno.setColumns(10);
		textempno.setBounds(157, 94, 145, 33);
		panel.add(textempno);

		textposname = new JTextField();
		textposname.setHorizontalAlignment(SwingConstants.CENTER);
		textposname.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textposname.setColumns(10);
		textposname.setBounds(157, 159, 145, 33);
		panel.add(textposname);

		textdeptname = new JTextField();
		textdeptname.setHorizontalAlignment(SwingConstants.CENTER);
		textdeptname.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textdeptname.setColumns(10);
		textdeptname.setBounds(157, 126, 145, 33);
		panel.add(textdeptname);

		lblPaymonth = new JLabel("");
		lblPaymonth.setFont(new Font("D2Coding", Font.BOLD, 30));
		lblPaymonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymonth.setBounds(395, 61, 290, 46);
		panel.add(lblPaymonth);

		JLabel lblPaymonth_1 = new JLabel("급 여 명 세 서");
		lblPaymonth_1.setFont(new Font("D2Coding", Font.PLAIN, 28));
		lblPaymonth_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymonth_1.setBounds(395, 117, 290, 46);
		panel.add(lblPaymonth_1);

		JLabel lblNewLabel = new JLabel("주식회사 상상기업");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 654, 383, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("대표이사 김아무개");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(167, 697, 373, 15);
		panel.add(lblNewLabel_1);

		textField_4 = new JTextField();
		textField_4.setBackground(SystemColor.menu);
		textField_4.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setText("급여항목");
		textField_4.setBounds(12, 516, 145, 33);
		panel.add(textField_4);
		textField_4.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBackground(SystemColor.menu);
		textField_8.setText("산출식");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("D2Coding", Font.BOLD, 20));
		textField_8.setColumns(10);
		textField_8.setBounds(157, 516, 528, 33);
		panel.add(textField_8);

		textField_15 = new JTextField();
		textField_15.setText("연장수당");
		textField_15.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setColumns(10);
		textField_15.setBounds(12, 581, 145, 33);
		panel.add(textField_15);

		txtX_1 = new JTextField();
		txtX_1.setText("통상시급 X 1.5배 X 연장근로시수");
		txtX_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
		txtX_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtX_1.setColumns(10);
		txtX_1.setBounds(157, 581, 528, 33);
		panel.add(txtX_1);

		textField_9 = new JTextField();
		textField_9.setText("기본급");
		textField_9.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setColumns(10);
		textField_9.setBounds(12, 548, 145, 33);
		panel.add(textField_9);

		txtX = new JTextField();
		txtX.setText("통상시급 X 209시간 ( 기본근로 177시간 + 주휴근로 32시간)");
		txtX.setFont(new Font("D2Coding", Font.PLAIN, 15));
		txtX.setHorizontalAlignment(SwingConstants.CENTER);
		txtX.setColumns(10);
		txtX.setBounds(157, 548, 528, 33);
		panel.add(txtX);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		comboEmpno = new JComboBox();
		comboEmpno.setModel(new DefaultComboBoxModel(combolist));
		panel_1.add(comboEmpno);

		comboPayment = new JComboBox();
		comboPayment.setModel(new DefaultComboBoxModel(combolist2));
		panel_1.add(comboPayment);

		btnSearch = new JButton("불러오기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleClickEvent(e);

			}
		});
		panel_1.add(btnSearch);

		btnPrint = new JButton("출력");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleClickEvent(e);
			}
		});
		panel_1.add(btnPrint);

		btnPrintAll = new JButton("귀속연월 전체 출력");
		btnPrintAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			handleClickEvent(e);
			}
		});
		panel_1.add(btnPrintAll);

		btnClose = new JButton("나가기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClickEvent(e);

			}
		});
		panel_1.add(btnClose);
	}

	protected void handleClickEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnClose) {

			dispose();

		} else if (obj == btnSearch) {

			if (comboPayment.getSelectedIndex() == -1 || comboEmpno.getSelectedIndex() == -1) {

				JOptionPane.showMessageDialog(this, "출력할 귀속연월과 사번을 선택해주세요.");
				return;

			}
			String sel1 = comboEmpno.getSelectedItem().toString();
			String sel2 = comboPayment.getSelectedItem().toString();

			pay = dao.search(sel1, sel2);
			System.out.println(pay.getName() + "" + pay.getBaseSal());
			// 각 위치에 데이터 넣어줄 메서드 불러오기
			setpay();

		} else if (obj == btnPrint) {

			printOne();

		}

		else if (obj == btnPrintAll) {

			printAll();

		}

	}

	public void setpay() {
		int[] tax = dao.taxArray(pay);
		textbasesal.setText(pay.getBaseSal() + "");
		textBonus.setText(pay.getBonus() + "");
		textMeal.setText(pay.getMeals() + "");
		textDdcRs.setText(pay.getDeductionsReason() + "");
		textdeptname.setText(pay.getDeptName() + "");
		textEmpIns.setText(tax[4] + "");
		textIncome.setText(tax[0]+"");
		textempno.setText(pay.getEmpno() + "");
		textHeal.setText(tax[2] + "");
		textname.setText(pay.getName() + "");
		textNation.setText(tax[3] + "");
		textOtherDdc.setText(pay.getOtherDeductions() + "");
		textotsal.setText(pay.getOvertimePay() + "");
		textposname.setText(pay.getPosName() + "");
		textResi.setText(tax[1] + "");
		textSevsal.setText(pay.getSeverancePay() + "");
		textVmsal.setText(pay.getVehicleMaintenance() + "");
		lblPaymonth.setText(pay.getPaymentMonth());

	}

	public Paper getPaper() {

		double paperWidth = 8.27 * 72; // A4 용지 폭 (단위: 1/72 인치)
		double paperHeight = 11.69 * 72; // A4 용지 높이 (단위: 1/72 인치)
		double marginLeft = 0.5 * 72; // 좌측 여백 (단위: 1/72 인치)
		double marginTop = 0.5 * 72; // 상단 여백 (단위: 1/72 인치)
		double marginRight = 0.5 * 72; // 우측 여백 (단위: 1/72 인치)
		double marginBottom = 0.5 * 72; // 하단 여백 (단위: 1/72 인치)
		Paper paper = new Paper();
		paper.setSize(paperWidth, paperHeight);
		paper.setImageableArea(marginLeft, marginTop, paperWidth - marginLeft - marginRight,
				paperHeight - marginTop - marginBottom);

		return paper;
	}

	public void printAll() {

		PrinterJob job = null;
		List<Printable> printables = new ArrayList<>();
		for (int i = 0; i < combolist.length; i++) {
			
			pay = dao.search(combolist[i], comboPayment.getSelectedItem().toString());
			System.out.println(pay.getName());
			setpay();
			// Printable 객체 생성
			job = PrinterJob.getPrinterJob();
			JOptionPane.showMessageDialog(this, pay.getDeptName()+"의 "+pay.getName() + " " + pay.getPosName() +" 님의 명세서입니다.", "명세서 확인", JOptionPane.CLOSED_OPTION);
			PageFormat pageFormat = job.defaultPage();
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			pageFormat.setPaper(getPaper());
			Printable printable = new Printable() {
				@Override
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
					if (pageIndex > 0) {
						return NO_SUCH_PAGE;
					}

					Graphics2D g2d = (Graphics2D) graphics;
					g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

					// 패널 크기를 A4 용지 크기에 맞게 조정하여 그리기
					double scaleX = pageFormat.getImageableWidth() / panel.getWidth();
					double scaleY = pageFormat.getImageableHeight() / panel.getHeight();
					g2d.scale(scaleX, scaleY);
					panel.paint(g2d);

					return PAGE_EXISTS;
				}
			};
			printables.add(printable);
			job.setPrintable(printables.get(i));
			if (job.printDialog()) {
			    try {
			        job.print();
			    } catch (PrinterException ex) {
			        ex.printStackTrace();
			    }
			}
		}

		
		

	}

	public void printOne() {

		PrinterJob job = PrinterJob.getPrinterJob();
		if (job.printDialog()) {
			PageFormat pageFormat = job.defaultPage();
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			pageFormat.setPaper(getPaper());

			Printable printable = new Printable() {
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
					if (pageIndex > 0) {
						return NO_SUCH_PAGE;
					}

					Graphics2D g2d = (Graphics2D) graphics;
					g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

					// 패널 크기를 A4 용지 크기에 맞게 조정하여 그리기
					double scaleX = pageFormat.getImageableWidth() / panel.getWidth();
					double scaleY = pageFormat.getImageableHeight() / panel.getHeight();
					g2d.scale(scaleX, scaleY);
					panel.paint(g2d);

					return PAGE_EXISTS;
				}
			};

			job.setPrintable(printable, pageFormat);
			try {
				job.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		}

	}

}
