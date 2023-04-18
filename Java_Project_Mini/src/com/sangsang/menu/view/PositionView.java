package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.sangsang.menu.controller.MenuPosDaoImpl;
import com.sangsang.menu.model.Position;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PositionView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private final String[] colname = { "직급순번", "직급이름" };

	private MenuPosDaoImpl dao = MenuPosDaoImpl.getInstance();
	private List<Position> poslist = dao.read();
	private JButton btnAddPosi;
	private JButton btnDeletePosi;
	private JScrollPane scrollPane;
	DefaultTableModel model;
	private JButton btnExit;
	private JButton btnRefresh;

	/**
	 * Launch the application.
	 */
	public static void showmyPositonframe() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PositionView frame = new PositionView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshtable() {

		poslist = dao.read();
		table.setModel(new DefaultTableModel(null, colname));
		model = new DefaultTableModel(null, colname);
		for (Position x : this.poslist) {

			Object[] pos = { x.getPosNo(), x.getPosName() };

			model.addRow(pos);

		}

		table.setModel(model);

	}

	public PositionView() {
		setTitle("직급 관리");

		initialize();

	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 351);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				refreshtable();
				
			}
		});
		scrollPane.setBounds(12, 66, 489, 239);
		contentPane.add(scrollPane);

		table = new JTable();
		refreshtable();
		table.setFont(new Font("D2Coding", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		btnAddPosi = new JButton("새로운 직급 생성");
		btnAddPosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);
			}
		});
		btnAddPosi.setBounds(12, 10, 125, 46);
		contentPane.add(btnAddPosi);

		btnDeletePosi = new JButton("직급 삭제");
		btnDeletePosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				handleclickEvent(e);

			}
		});
		btnDeletePosi.setBounds(157, 10, 105, 46);
		contentPane.add(btnDeletePosi);
		
		btnExit = new JButton("뒤로 가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
				
			}
		});
		btnExit.setBounds(283, 10, 105, 46);
		contentPane.add(btnExit);
		
		btnRefresh = new JButton("새로 고침");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleclickEvent(e);
				
			}
		});
		btnRefresh.setBounds(407, 10, 94, 46);
		contentPane.add(btnRefresh);
	}

	protected void handleclickEvent(ActionEvent e) {
		// TODO Auto-generated method stub

		Object obj = e.getSource();

		if (obj == btnAddPosi) {
			
			PosAddview.showMyAddPosition();
			
		} else if (obj == btnDeletePosi) {
			
			if(table.getSelectedRows().length!=1) {
				
				JOptionPane.showMessageDialog(this, "삭제할 하나의 행만 선택해주세요");
				return;
				
			}
			
			Integer select = JOptionPane.showOptionDialog(this, "정말 삭제하시겠습니까?", "삭제 전 최종확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			System.out.println(select);
			if(select == 0) {
				List<Position> list = dao.read();
				
				
				int result = dao.deletePos(list.get(table.getSelectedRow()));
				JOptionPane.showMessageDialog(this, result + " 개의 직급이 삭제되었습니다.");
				refreshtable();
				return;
			} else {
				
				
			}
			
			return;
			
		} else if (obj == btnExit) {
			
			dispose();
			
		} else if (obj == btnRefresh) {
			
			refreshtable();
			
		}

	}

}
