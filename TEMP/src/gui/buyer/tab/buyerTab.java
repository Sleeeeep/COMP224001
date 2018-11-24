package gui.buyer.tab;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.seller.tab.sellerCallingPlanTab;
import gui.tab.*;
import buyer.*;
import phone.*;
import callingplan.*;

public class buyerTab extends AbstractTabPanel {
	private DefaultListModel listModel;
	private JList list;

	private int step = 0;

	private Phone phonetemp;
	private CallingPlan callingplantemp;
	private Phone SelectedPhone;
	private CallingPlan SelectedCallingPlan;

	private PhoneRec phonerec;
	private CallingPlanRec callingplanrec;

	private int offset = 1;
	
	final List<Phone> itemlistP = new ArrayList<>();
	final List<CallingPlan> itemlistC = new ArrayList<>();

	public buyerTab(Buyer buyer) {
		super();

		Phone_List phonelist = new Phone_List(2);
		CallingPlan_List callingplanlist = new CallingPlan_List(2);
		
		phonetemp = new Phone("없음", "", "", "", "", "", "", "");
		itemlistP.add(phonetemp);
		phonetemp = new Phone("변경안함", "", "", "", "", "", "", "");
		itemlistP.add(phonetemp);
		for (int i = 0; i < phonelist.Get_Total_Phone_List_Size(); i++)
			itemlistP.add(phonelist.getTotal_phone_list().get(i));

		// GUI part
		JLabel lblTitle = new JLabel("기종 목록");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblTitle.setBounds(40, 33, 240, 40);
		add(lblTitle);

		// Scroll 가능한 List 보여주는 객체 생성 (ScrollPane 안에 JList 넣음
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 150, 530, 425);
		add(scrollPane);

		// Listmodel 생성 (List의 element에 대한 정보를 지님)
		listModel = new DefaultListModel();
		for (Phone element : itemlistP) {
			listModel.addElement(element.getMODEL_NAME());
		}
		// Listmodel을 연결한 JList 생성하고 Scroll 가능한 Panel에 추가
		list = new JList(listModel);
		scrollPane.setViewportView(list);

		JButton btnSelect = new JButton("선택");
		btnSelect.setBounds(637, 300, 181, 45);
		add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index < 0)
					return;
				if (step == 1 && index >= itemlistP.size())
					return;
				if (step == 3 && index >= itemlistC.size())
					return;
				// step = 0 : 폰 선택, step = 1 : 폰 추천, step = 2 : 요금 선택, step = 3 : 요금 추천
				if (index != 0) 
				{
					if (step == 0 || step == 1) {
						step = 2;
						lblTitle.setText("요금제 목록");
						SelectedPhone = new Phone(itemlistP.get(index));

						callingplantemp = new CallingPlan("없음", "", "", "", "");
						itemlistC.add(callingplantemp);
						callingplantemp = new CallingPlan("변경안함", "", "", "", "");
						itemlistC.add(callingplantemp);
						for (int i = 0; i < callingplanlist.Get_Total_CallingPlan_List_Size(); i++)
							itemlistC.add(callingplanlist.getTotal_CallingPlan_list().get(i));
						updateListC();
						offset = 1;
					} else if(step == 2 || step == 3)
					{
						SelectedCallingPlan = new CallingPlan(itemlistC.get(index));
						additionalFrame additionalP = new additionalFrame(SelectedPhone);
						additionalFrame additionalC = new additionalFrame(SelectedCallingPlan);
					}
						
				} else // 추천으로 들어감
				{
					if (step == 0) // 기종 추천
					{
						step++;
						String DISPLAY = "", STORAGE = "", PRICE = "", PERFORMANCE = "", MANUFACTURE = "";

						DISPLAY = JOptionPane.showInputDialog(buyerTab.this, "화면크기(O.O인치)");
						if (DISPLAY != null) {
							STORAGE = JOptionPane.showInputDialog(buyerTab.this, "용량(0GB)");
							if (STORAGE != null) {
								PRICE = JOptionPane.showInputDialog(buyerTab.this, "가격");
								if (PRICE != null) {
									PERFORMANCE = JOptionPane.showInputDialog(buyerTab.this, "프리미엄 or 보급");
									if (PERFORMANCE != null) {
										MANUFACTURE = JOptionPane.showInputDialog(buyerTab.this, "1-애플 2-삼성 3-LG");
										if (MANUFACTURE != null) {
											itemlistP.clear();
											phonerec = new PhoneRec(DISPLAY, STORAGE, PRICE, PERFORMANCE, MANUFACTURE);
											phonerec.checkInterval(offset);
											phonetemp = new Phone("없음", "", "", "", "", "", "", "");
											itemlistP.add(phonetemp);
											for (int i = 0; i < phonerec.getRecommandlist().size(); i++)
												itemlistP.add(phonerec.getRecommandlist().get(i));
											updateListP();
											offset++;
										}
									}
								}
							}
						}
					}
					else if(step == 1)
					{
						itemlistP.clear();
						phonerec.checkInterval(offset);
						phonetemp = new Phone("없음", "", "", "", "", "", "", "");
						itemlistP.add(phonetemp);
						for (int i = 0; i < phonerec.getRecommandlist().size(); i++)
							itemlistP.add(phonerec.getRecommandlist().get(i));
						updateListP();
						offset++;
					}
					else if(step == 2)
					{
						step++;
						String MESSAGE = "", CALL = "", DATA = "", PRICE = "";

						MESSAGE = JOptionPane.showInputDialog(buyerTab.this, "문자량(통)");
						if (MESSAGE != null) {
							CALL = JOptionPane.showInputDialog(buyerTab.this, "통화량(분)");
							if (CALL != null) {
								DATA = JOptionPane.showInputDialog(buyerTab.this, "데이터(0.0GB)");
								if (DATA != null) {
									PRICE = JOptionPane.showInputDialog(buyerTab.this, "가격");
									if (PRICE != null) {
										itemlistC.clear();
										callingplanrec = new CallingPlanRec(MESSAGE, CALL, DATA, PRICE);
										callingplanrec.checkInterval(offset);
										callingplantemp = new CallingPlan("없음", "", "", "", "");
										itemlistC.add(callingplantemp);
										for (int i = 0; i < callingplanrec.getRecommandlist().size(); i++)
											itemlistC.add(callingplanrec.getRecommandlist().get(i));
										updateListC();
										offset++;
									}
								}
							}
						}
					}
					else
					{
						itemlistC.clear();
						callingplanrec.checkInterval(offset);
						callingplantemp = new CallingPlan("없음", "", "", "", "");
						itemlistC.add(callingplantemp);
						for (int i = 0; i < callingplanrec.getRecommandlist().size(); i++)
							itemlistC.add(callingplanrec.getRecommandlist().get(i));
						updateListC();
						offset++;
					}
				}
			}
		});
		// 상세정보보기 
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				// Double-click일 경우
				if (evt.getClickCount() == 2) {
					// 클릭된 Index 가져옴
					int index = list.locationToIndex(evt.getPoint());
					if (index <= 0)
						return;
					additionalFrame additional;
					
					if(step == 0 || step == 1)
						additional = new additionalFrame(itemlistP.get(index));
					else if(step == 2 || step == 3)
						additional = new additionalFrame(itemlistC.get(index));
				}
			}
		});
	}

	private void updateListP() {
		listModel.clear();
		for (Phone element : itemlistP) {
			listModel.addElement(element.getMODEL_NAME());
		}
	}

	private void updateListC() {
		listModel.clear();
		for (CallingPlan element : itemlistC) {
			listModel.addElement(element.getPAY_NAME());
		}
	}
}