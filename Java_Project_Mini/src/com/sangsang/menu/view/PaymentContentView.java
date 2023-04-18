package com.sangsang.menu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaymentContentView extends JFrame {

	private JPanel contentPane;

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

	public PaymentContentView() {
		
		
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
