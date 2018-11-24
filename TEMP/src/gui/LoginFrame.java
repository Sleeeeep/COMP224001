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

		// 전체 Client UI의 폰트를 Times 일반, 크기는 20으로 설정
		setUIFont(new FontUIResource(new Font("맑은 고딕", Font.PLAIN, 20)));

		if (mode.equals("S")) {
			// 단순 Text 표시하는 객체(JLabel) 생성
			setTitle("판매자");
			JLabel lblID = new JLabel("ID");
			lblID.setBounds(116, 55, 62, 18);
			contentPane.add(lblID);

			JLabel lblPW = new JLabel("PW");
			lblPW.setBounds(116, 95, 62, 18);
			contentPane.add(lblPW);

			// Text 입력받을 수 있는 객체(JTextField) 생성
			tfID = new JTextField();
			tfID.setBounds(165, 49, 136, 30);
			contentPane.add(tfID);
			tfID.setColumns(10);

			tfPW = new JPasswordField();
			tfPW.setBounds(165, 89, 136, 30);
			contentPane.add(tfPW);
			tfPW.setColumns(10);

			// 로그인
			JButton btnSignin = new JButton("로그인");
			btnSignin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// 로그인 버튼이 눌렸을 때 수행
					// TextField에서 입력된 ID와 PW를 가져와서 객체 생성
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

			// 처음 정보 생성
			JButton btnRegister = new JButton("등록");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (seller.CheckExistFile("SellerInfo"))
						JOptionPane.showMessageDialog(LoginFrame.this, "Already Seller info exist", "Error",
								JOptionPane.ERROR_MESSAGE);
					// 회원가입 버튼이 눌렸을 때 수행
					// ID와 PW를 입력받을 Dialog를 띄움
					else {
						String newID, newPW, newMK, newBN, newNAME;
						newID = JOptionPane.showInputDialog("ID를 입력해주세요");
						if (newID != null) {
							newPW = JOptionPane.showInputDialog("PW를 입력해주세요");
							if (newPW != null) {
								newMK = JOptionPane.showInputDialog("매장정보를 입력해주세요");
								if (newMK != null) {
									newNAME = JOptionPane.showInputDialog("이름을 입력해주세요");
									if (newNAME != null) {
										newBN = JOptionPane.showInputDialog("사업자번호를 입력해주세요");
										seller = new Seller(newNAME, newID, newPW, newMK, newBN);
										seller.setFile(newID, newPW, newMK, newNAME, newBN);
										JOptionPane.showMessageDialog(LoginFrame.this, "등록 성공",
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
				NAME = JOptionPane.showInputDialog("이름을 입력해주세요");
				if (NAME != null) {
					AGE = JOptionPane.showInputDialog("나이를 입력해주세요");
					if (AGE != null) {
						PHONE = JOptionPane.showInputDialog("현재 사용하고 있는 기종을 입력해주세요");
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