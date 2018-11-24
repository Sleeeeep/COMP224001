package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import common.gui.AbstractFrame;
import seller.*;
import buyer.*;
import gui.buyer.tab.*;
import gui.seller.tab.*;

public class MainFrame extends AbstractFrame {
	private Seller seller;
	private Buyer buyer;

	public MainFrame(Object logintype) {
		// Size 넘겨 줌 (Width, Height)
		super(900, 800);

		JLabel lblTitle = new JLabel("심청이");
		lblTitle.setBounds(20, 20, 340, 70);
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		contentPane.add(lblTitle);

		// 유저 정보 표시
		JLabel lblUserInfo = null;
		JLabel lblMarketInfo = null;
		if (logintype.getClass() == Buyer.class) {
			seller = new Seller();
			buyer = (Buyer) logintype;
			lblMarketInfo = new JLabel(seller.getMarketInfo() + " 지점입니다");
			lblMarketInfo.setBounds(620, 30, 276, 25);
			lblUserInfo = new JLabel(buyer.getName() + " 고객님 환영합니다");
			lblUserInfo.setBounds(620, 70, 276, 25);
			contentPane.add(lblMarketInfo);
			contentPane.add(lblUserInfo);
		}

		if (logintype.getClass() == Seller.class) // 판매자일때 ui
		{
			InfoTabPanel infoTab;
			sellerPhoneTab phoneTab;
			sellerCallingPlanTab callingplanTab;

			seller = (Seller) logintype;

			infoTab = new InfoTabPanel(seller);
			phoneTab = new sellerPhoneTab(seller);
			callingplanTab = new sellerCallingPlanTab(seller);

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(7, 125, 880, 640);
			tabbedPane.add("내 정보", infoTab);
			tabbedPane.add("기종", phoneTab);
			tabbedPane.add("요금제", callingplanTab);
			contentPane.add(tabbedPane);
		} else if (logintype.getClass() == Buyer.class) // 구매자일때 ui
		{
			buyerTab buyerTab = new buyerTab(buyer);; 
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(7, 125, 880, 640);
			tabbedPane.add("추천", buyerTab);
			contentPane.add(tabbedPane);
		}
		// 보이게 만듬
		setVisible(true);
	}
}
