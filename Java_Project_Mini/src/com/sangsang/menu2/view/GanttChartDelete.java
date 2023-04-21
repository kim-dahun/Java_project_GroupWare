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
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
	private JButton btnNewButton;
	private JButton okButton;
	private JButton cancelButton;

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
		
		chartlist = dao.read();
		
		
		return chartlist.get(index);
		
	}
	
	public void refreshtable() {
		chartlist = dao.read();
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
		setBounds(100, 100, 630, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 91, 590, 127);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(new Object[][] {
		},
		modellist
	);
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(79);
		table.getColumnModel().getColumn(3).setPreferredWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(143);
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnNewButton = new JButton("갱신하기");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleclickEvent(e);
						
					}
				});
				buttonPane.add(btnNewButton);
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
			
		} else if (obj==cancelButton) {
			dispose();
		} else if (obj == btnNewButton) {
			
			refreshtable();
			
		}
		
	}
}
