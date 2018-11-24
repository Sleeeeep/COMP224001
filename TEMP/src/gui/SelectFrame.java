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

		// ��ü Client UI�� ��Ʈ�� Times �Ϲ�, ũ��� 20���� ����
		setUIFont(new FontUIResource(new Font("���� ����", Font.PLAIN, 20)));

		JButton btnSeller = new JButton("�Ǹ���");
		btnSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame newframe = new LoginFrame("S");
				dispose();
			}
		});
		btnSeller.setBounds(0, 0, 105, 35);
		contentPane.add(btnSeller);

		JButton btnBuyer = new JButton("������");
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