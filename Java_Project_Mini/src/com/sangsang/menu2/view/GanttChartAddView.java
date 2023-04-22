package com.sangsang.menu2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.controller.GanttChartDaoImpl;
import com.sangsang.menu.controller.PaymentDaoImpl;
import com.sangsang.menu.model.GanttChart;
import com.toedter.calendar.JCalendar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class GanttChartAddView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private boolean runningCal;
	private JTextField textStratDay;
	private JTextField textEndDay;
	private LocalDateTime startday;
	private LocalDateTime endday;
	private final DateTimeFormatter DATEFMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private GanttChart newWork = new GanttChart();
	private JTextField textId;
	private JTextField textName;
	private JTextField textTitle;
	private String[] combolist = PaymentDaoImpl.getInstance().readEmpno();
	private boolean runCheck = true;
	private JComboBox comboEmpno;
	private JTextArea textContent;
	private GanttChartDaoImpl dao = GanttChartDaoImpl.getInstance();
	private JButton btnStartDayRef;
	private JButton btnEnddayRef;

	/**
	 * Launch the application.
	 */
	public static void showMyGanttChartAdd() {
		try {
			GanttChartAddView dialog = new GanttChartAddView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GanttChartAddView() {
		setTitle("일정 등록");

		initialize();
		checkEmpno chk = new checkEmpno();
		chk.start();
	}

	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setBounds(100, 100, 540, 495);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("일정 등록");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setBounds(12, 10, 500, 29);
		contentPanel.add(lblNewLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				Calendar calendar = Calendar.getInstance();
//
//				JCalendar jcal = new JCalendar();
//				int result = JOptionPane.showConfirmDialog(lblNewLabel, jcal, "날짜를 선택하세요.",JOptionPane.OK_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE , null);
//				if ( result == 0) {
//					
//					Date selectedDate = jcal.getDate();
//					calendar.setTime(selectedDate);
//					
//					startday = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
//					newWork.setEndDay(startday);
//					textStratDay.setText(startday.format(DATEFMT));
//					textEndDay.setText("");
//					newWork.setEndDay(null);
//				}
			}
		});

		dateChooser.getCalendarButton().setText("선택");
		dateChooser.setBounds(121, 100, 122, 29);
		contentPanel.add(dateChooser);

		JLabel lblNewLabel_1 = new JLabel("일정 시작일");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 100, 97, 29);
		contentPanel.add(lblNewLabel_1);

		textStratDay = new JTextField();
		textStratDay.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textStratDay.setHorizontalAlignment(SwingConstants.CENTER);
		textStratDay.setBounds(255, 100, 201, 29);
		contentPanel.add(textStratDay);
		textStratDay.setColumns(10);

		textEndDay = new JTextField();
		textEndDay.setHorizontalAlignment(SwingConstants.CENTER);
		textEndDay.setFont(new Font("D2Coding", Font.PLAIN, 15));
		textEndDay.setColumns(10);
		textEndDay.setBounds(255, 147, 201, 29);
		contentPanel.add(textEndDay);

		JLabel lblNewLabel_1_1 = new JLabel("일정 종료일");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(12, 147, 97, 29);
		contentPanel.add(lblNewLabel_1_1);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				Calendar calendar = Calendar.getInstance();

//				JCalendar jcal = new JCalendar();
//				int result = JOptionPane.showConfirmDialog(lblNewLabel, jcal, "날짜를 선택하세요.",JOptionPane.OK_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE , null);
//				if ( result == 0) {
//					
//					Date selectedDate = jcal.getDate();
//					calendar.setTime(selectedDate);
//					
//					endday = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
//					
//					if(endday.compareTo(startday)<0) {
//						
//						JOptionPane.showMessageDialog(lblNewLabel, "종료일자는 시작일자와 같거나 그 이후여야 합니다.");
//						return;
//					} else {
//						newWork.setEndDay(endday);
//						textEndDay.setText(endday.format(DATEFMT));
//						
//					}
//				}

			}
		});
		dateChooser_1.getCalendarButton().setText("선택");
		dateChooser_1.setBounds(121, 147, 122, 29);
		contentPanel.add(dateChooser_1);

		comboEmpno = new JComboBox();
		if(!AccountDaoImpl.getInstance().getNowlogin().getId().equals("admin")) {
			String nowEmpno=AccountDaoImpl.getInstance().getNowlogin().getEmpNo();
			if(nowEmpno.equals(null)) {
				JOptionPane.showMessageDialog(this, "아직 사번을 부여받지 못해서 일정관리 사용이 불가능합니다.");
				return;
			} 
			comboEmpno.setModel(new DefaultComboBoxModel(new String[] {nowEmpno}));
		} else {
			comboEmpno.setModel(new DefaultComboBoxModel(combolist));
		}
		
		comboEmpno.setSelectedIndex(0);
		comboEmpno.setBounds(121, 198, 122, 29);
		contentPanel.add(comboEmpno);

		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(255, 198, 109, 29);
		contentPanel.add(textId);
		textId.setColumns(10);

		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBounds(376, 198, 124, 29);
		contentPanel.add(textName);

		JLabel lblNewLabel_1_1_1 = new JLabel("일정 관리자");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(12, 198, 97, 29);
		contentPanel.add(lblNewLabel_1_1_1);

		textTitle = new JTextField();
		textTitle.setBounds(121, 250, 391, 29);
		contentPanel.add(textTitle);
		textTitle.setColumns(10);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("일정 제목");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(12, 250, 97, 29);
		contentPanel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("일정 내용");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setBounds(12, 299, 97, 29);
		contentPanel.add(lblNewLabel_1_1_1_1_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(121, 299, 391, 114);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		textContent = new JTextArea();
		panel.add(textContent, BorderLayout.CENTER);

		btnStartDayRef = new JButton("F5");
		btnStartDayRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dateChooser.getDate().equals(null)) {
					return;
				} else {
					textEndDay.setText("");
					newWork.setEndDay(null);
					
					LocalDateTime start = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();

					int year = start.getYear();
					int month = start.getMonthValue();
					int day = start.getDayOfMonth();
					int hour = 0;
					int minute = 1;
					int second = start.getSecond();
					startday = LocalDateTime.of(year, month, day, hour, minute, second);
					textStratDay.setText(startday.format(DATEFMT));
					newWork.setStartDay(startday);
				}

			}
		});
		btnStartDayRef.setBounds(463, 100, 49, 29);
		contentPanel.add(btnStartDayRef);

		btnEnddayRef = new JButton("F5");
		btnEnddayRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dateChooser_1.getDate().equals(null) || textStratDay.getText().isBlank()) {

					
					
				} else {
					
					
					LocalDateTime start = dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();

					int year = start.getYear();
					int month = start.getMonthValue();
					int day = start.getDayOfMonth();
					int hour = 0;
					int minute = 1;
					int second = start.getSecond();
					endday = LocalDateTime.of(year, month, day, hour, minute, second);
					
					if(endday.compareTo(startday)<0) {
						
						JOptionPane.showMessageDialog(null, "시작일은 종료일보다 늦을 수 없습니다.");
						
					}
					
					textEndDay.setText(endday.format(DATEFMT));
					newWork.setEndDay(endday);
				}

			}
		});
		btnEnddayRef.setBounds(463, 147, 49, 29);
		contentPanel.add(btnEnddayRef);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("등록하기");
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
				cancelButton = new JButton("나가기");
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

			if (textContent.getText().isBlank() || textId.getText().isBlank() || textName.getText().isBlank()

					|| textStratDay.getText().isBlank() || textEndDay.getText().isBlank()) {

				JOptionPane.showMessageDialog(this, "입력되지 않은 값이 남아 있습니다. 모든 값을 반드시 입력해주세요.");
				return;

			}

			newWork.setContent(textContent.getText());
			newWork.setTitle(textTitle.getText());
			newWork.setName(textName.getText());
			newWork.setId(textId.getText());
			newWork.setEmpno(comboEmpno.getSelectedItem().toString());
			
			int result = dao.writeSch(newWork);

			JOptionPane.showMessageDialog(this, result + "개의 일정을 추가했습니다.");
			
			newWork = new GanttChart();
			textContent.setText("");
			textTitle.setText("");
			textEndDay.setText("");
			textStratDay.setText("");
			

		} else if (obj == cancelButton) {
			dispose();
			runCheck = false;

		}

	}

	class checkEmpno extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			while (runCheck) {
				if (comboEmpno.getSelectedIndex() == -1) {

				} else {
					String empno = comboEmpno.getSelectedItem().toString();
					java.util.List<Account> list = AccountDaoImpl.getInstance().read();

					for (Account x : list) {

						if (x.getEmpNo().equals(empno)) {

							textId.setText(x.getId());
							textName.setText(x.getName());
							

						}

					}
					long sleepTime = 250;
					try {
						sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

		}

	}

}
