package gui;

import java.io.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import common.gui.AbstractFrame;

public class SelectFrame extends AbstractFrame {
	public SelectFrame() {
		super(210, 63);

		// 전체 Client UI의 폰트를 Times 일반, 크기는 20으로 설정
		setUIFont(new FontUIResource(new Font("맑은 고딕", Font.PLAIN, 20)));

		JButton btnSeller = new JButton("판매자");
		btnSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame newframe = new LoginFrame("S");
				dispose();
			}
		});
		btnSeller.setBounds(0, 0, 105, 35);
		contentPane.add(btnSeller);

		JButton btnBuyer = new JButton("구매자");
		btnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame newframe = new LoginFrame("B");
				dispose();
			}
		});
		btnBuyer.setBounds(105, 0, 105, 35);
		contentPane.add(btnBuyer);

		setVisible(true);
	}
}
