package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sangsang.menu.controller.MenuDeptDaoImpl;
import com.sangsang.menu.model.Department;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;

public class DeptMiniView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private String[] deptColName = {"부서No.","부서명","상위 부서"};
	private List<Department> list;

	/**
	 * Launch the application.
	 */
	public static void showMydeptlist() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeptMiniView frame = new DeptMiniView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeptMiniView() {
		
		initialize();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 73, 381, 507);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		table.setModel(getModel());
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("전체 부서 리스트");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 381, 53);
		contentPane.add(lblNewLabel);
	}
	
	public DefaultTableModel getModel() {
		
		list = MenuDeptDaoImpl.getInstance().read();
		DefaultTableModel model = new DefaultTableModel(null,deptColName);
		
		for(int i = 0; i<list.size(); i++) {
			
			Object[] obj = {list.get(i).getDeptno(),list.get(i).getDeptName(),list.get(i).getDeptNodeNo()};
			model.addRow(obj);
		}
		
		
		
		return model;
		
	}
	
}
