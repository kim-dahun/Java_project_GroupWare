package com.sangsang.menu2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.sangsang.account.controller.AccountDaoImpl;
import com.sangsang.account.model.Account;
import com.sangsang.menu.controller.GanttChartDaoImpl;
import com.sangsang.menu.model.GanttChart;
import com.sangsang.menu.model.Position;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GanttChartDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String[] modellist = new String[] {"사번","이름","시작일","종료일","제목","내용"};
	private GanttChartDaoImpl dao = GanttChartDaoImpl.getInstance();
	private List<GanttChart> chartlist;
	private List<GanttChart> charRelist;
	private DefaultTableModel model;
	private JButton btnRefresh;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void showMyDeleteChart() {
		try {
			GanttChartDelete dialog = new GanttChartDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GanttChart searchForDelete(int index) {
		refreshtable();
		
		
		
		return chartlist.get(index);
		
	}
	
	public void refreshtable() {
		String empno = AccountDaoImpl.getInstance().getNowlogin().getEmpNo();
		if(!AccountDaoImpl.getInstance().getNowlogin().getId().equals("admin")) {
			chartlist = new ArrayList<>();
			for(GanttChart x : dao.read()) {
				
				if(empno.equals(x.getEmpno())) {
					
					chartlist.add(x);
					
				}
				
			}
			
		} else {
			chartlist = dao.read();
		}
		
		
		
		
		
		table.setModel(new DefaultTableModel(null, modellist));
		model = new DefaultTableModel(null, modellist);
		for (GanttChart x : this.chartlist) {

			Object[] pos = { x.getEmpno(),x.getName(),x.getStartDay(),x.getEndDay(),x.getTitle(),x.getContent() };

			model.addRow(pos);

		}

		table.setModel(model);
		
		
	}
	
	public GanttChartDelete() {
		setTitle("일정 삭제");
		
		initialize();
		refreshtable();
	}
	
	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setBounds(100, 100, 881, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 91, 841, 300);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(new Object[][] {
		},
		modellist
	);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"\uC0AC\uBC88", "\uC774\uB984", "\uC2DC\uC791\uC77C", "\uC885\uB8CC\uC77C", "\uC81C\uBAA9", "\uB0B4\uC6A9"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(119);
		table.getColumnModel().getColumn(3).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(189);
		table.getColumnModel().getColumn(5).setPreferredWidth(278);
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRefresh = new JButton("갱신하기");
				btnRefresh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleclickEvent(e);
						
					}
				});
				{
					btnNewButton = new JButton("상세보기");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							handleclickEvent(e);
							
						}
					});
					buttonPane.add(btnNewButton);
				}
				buttonPane.add(btnRefresh);
			}
			{
				okButton = new JButton("삭제하기");
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
		
		if(obj==okButton) {
			
			if(table.getSelectedRow()==-1) {
				
				return;
			}
			GanttChart gantt = searchForDelete(table.getSelectedRow());
			
			int result = dao.DeleteSch(gantt);
			
			JOptionPane.showMessageDialog(this, result + "개 데이터 삭제 완료");
			
			refreshtable();
			
		} else if (obj==cancelButton) {
			dispose();
		} else if (obj == btnRefresh) {
			
			refreshtable();
			
		} else if (obj == btnNewButton) {
			
			if(table.getSelectedRow()==-1) {
				
				JOptionPane.showMessageDialog(this, "세부적으로 보고 싶은 일정을 선택해주세요.");
				return;
			}
			
			GanttChartInfoView.showMyGanttInfoView(searchForDelete(table.getSelectedRow()));
			
		}
		
	}
}
