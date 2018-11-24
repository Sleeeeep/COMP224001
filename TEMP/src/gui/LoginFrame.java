package gui;

import java.io.*;
import java.awt.Dimension;
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
import seller.Seller;
import buyer.*;

public class LoginFrame extends AbstractFrame {
	private Seller seller = new Seller();
	private Buyer buyer = new Buyer();
	private JTextField tfID;
	private JPasswordField tfPW;
	private JTextField tfNAME;

	public LoginFrame(String mode) {
		super(450, 270);

		// ��ü Client UI�� ��Ʈ�� Times �Ϲ�, ũ��� 20���� ����
		setUIFont(new FontUIResource(new Font("���� ����", Font.PLAIN, 20)));

		if (mode.equals("S")) {
			// �ܼ� Text ǥ���ϴ� ��ü(JLabel) ����
			setTitle("�Ǹ���");
			JLabel lblID = new JLabel("ID");
			lblID.setBounds(116, 55, 62, 18);
			contentPane.add(lblID);

			JLabel lblPW = new JLabel("PW");
			lblPW.setBounds(116, 95, 62, 18);
			contentPane.add(lblPW);

			// Text �Է¹��� �� �ִ� ��ü(JTextField) ����
			tfID = new JTextField();
			tfID.setBounds(165, 49, 136, 30);
			contentPane.add(tfID);
			tfID.setColumns(10);

			tfPW = new JPasswordField();
			tfPW.setBounds(165, 89, 136, 30);
			contentPane.add(tfPW);
			tfPW.setColumns(10);

			// �α���
			JButton btnSignin = new JButton("�α���");
			btnSignin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// �α��� ��ư�� ������ �� ����
					// TextField���� �Էµ� ID�� PW�� �����ͼ� ��ü ����
					String inputID = tfID.getText();
					String inputPW = tfPW.getText();
					Scanner s = new Scanner(System.in);

					if (!seller.CheckExistFile("SellerInfo"))
						JOptionPane.showMessageDialog(LoginFrame.this, "No Seller info", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {
						seller.getFile();
						if (inputID.equals(seller.getID()) == true && inputPW.equals(seller.getPW()) == true) {
							new MainFrame(seller);
							dispose();
						} else
							JOptionPane.showMessageDialog(LoginFrame.this, "Invalid ID or PW", "Error",
									JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnSignin.setBounds(82, 157, 105, 35);
			contentPane.add(btnSignin);

			// ó�� ���� ����
			JButton btnRegister = new JButton("���");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (seller.CheckExistFile("SellerInfo"))
						JOptionPane.showMessageDialog(LoginFrame.this, "Already Seller info exist", "Error",
								JOptionPane.ERROR_MESSAGE);
					// ȸ������ ��ư�� ������ �� ����
					// ID�� PW�� �Է¹��� Dialog�� ���
					else {
						String newID, newPW, newMK, newBN, newNAME;
						newID = JOptionPane.showInputDialog("ID�� �Է����ּ���");
						if (newID != null) {
							newPW = JOptionPane.showInputDialog("PW�� �Է����ּ���");
							if (newPW != null) {
								newMK = JOptionPane.showInputDialog("���������� �Է����ּ���");
								if (newMK != null) {
									newNAME = JOptionPane.showInputDialog("�̸��� �Է����ּ���");
									if (newNAME != null) {
										newBN = JOptionPane.showInputDialog("����ڹ�ȣ�� �Է����ּ���");
										seller = new Seller(newNAME, newID, newPW, newMK, newBN);
										seller.setFile(newID, newPW, newMK, newNAME, newBN);
										JOptionPane.showMessageDialog(LoginFrame.this, "��� ����",
												"SellerInfo", JOptionPane.PLAIN_MESSAGE);
									}
								}
							}
						}
					}
				}
			});
			btnRegister.setBounds(212, 157, 130, 35);
			contentPane.add(btnRegister);

			setVisible(true);
		} else if (mode.equals("B")) {
			if (!buyer.CheckExistFile("SellerInfo"))
				JOptionPane.showMessageDialog(LoginFrame.this, "No Seller info", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				String NAME, AGE, PHONE;
				NAME = JOptionPane.showInputDialog("�̸��� �Է����ּ���");
				if (NAME != null) {
					AGE = JOptionPane.showInputDialog("���̸� �Է����ּ���");
					if (AGE != null) {
						PHONE = JOptionPane.showInputDialog("���� ����ϰ� �ִ� ������ �Է����ּ���");
						if (PHONE != null) {
							buyer = new Buyer(NAME, AGE, PHONE);
							new MainFrame(buyer);
						}
					}
				}
			}
		}
	}
}