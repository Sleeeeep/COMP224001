package gui.seller.tab;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import seller.*;
import callingplan.*;
import gui.LoginFrame;
import gui.tab.AbstractTabPanel;
import phone.Phone;

public class sellerCallingPlanTab extends AbstractTabPanel {
	private DefaultListModel listModel;
	private JList list;

	final List<CallingPlan> itemlist = new ArrayList<>();

	public sellerCallingPlanTab(Seller seller) {
		super();
		CallingPlan_List callingplanlist = new CallingPlan_List(1);
		for (int i = 0; i < callingplanlist.Get_Total_CallingPlan_List_Size(); i++)
			itemlist.add(callingplanlist.getTotal_CallingPlan_list().get(i));

		// GUI part
		JLabel lblTitle = new JLabel("요금제 목록");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblTitle.setBounds(40, 33, 240, 40);
		add(lblTitle);

		// Scroll 가능한 List 보여주는 객체 생성 (ScrollPane 안에 JList 넣음
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 150, 530, 425);
		add(scrollPane);

		// Listmodel 생성 (List의 element에 대한 정보를 지님)
		listModel = new DefaultListModel();
		for (CallingPlan element : itemlist) {
			listModel.addElement(element.getPAY_NAME());
		}

		// Listmodel을 연결한 JList 생성하고 Scroll 가능한 Panel에 추가
		list = new JList(listModel);
		scrollPane.setViewportView(list);

		// 새로 데이터를 추가하는 버튼
		JButton btnNewData = new JButton("신규 등록");
		btnNewData.setBounds(637, 200, 181, 45);
		add(btnNewData);
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String PAY_NAME = "", MESSAGE = "", CALL = "", DATA = "", PRICE = "";

				PAY_NAME = JOptionPane.showInputDialog("요금제 이름");
				if (PAY_NAME != null) {
					MESSAGE = JOptionPane.showInputDialog("문자량");
					if (MESSAGE != null) {
						CALL = JOptionPane.showInputDialog("통화량");
						if (CALL != null) {
							DATA = JOptionPane.showInputDialog("데이터");
							if (DATA != null) {
								PRICE = JOptionPane.showInputDialog("가격");
								if (PRICE != null) {
									itemlist.add(new CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE));
									updateList();
									callingplanlist.insert_CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE);
									try {
										callingplanlist.WriteFile_CallingPlanList();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}

				}

			}
		});

		// 리스트에서 더블 클릭 시 정보 수정
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				if (evt.getClickCount() != 0) {
				}
				// Double-click일 경우
				if (evt.getClickCount() == 2) {
					// 클릭된 Index 가져옴
					int index = list.locationToIndex(evt.getPoint());
					if (index < 0)
						return;

					if (index >= itemlist.size())
						return;
					CallingPlan selected = itemlist.get(index);
					String PAY_NAME = "", MESSAGE = "", CALL = "", DATA = "", PRICE = "";

					PAY_NAME = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "요금제 이름",
							selected.getPAY_NAME());
					if (PAY_NAME != null) {
						MESSAGE = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "문자량",
								selected.getMESSAGE());
						if (MESSAGE != null) {
							CALL = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "통화량", selected.getCALL());
							if (CALL != null) {
								DATA = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "데이터",
										selected.getDATA());
								if (DATA != null) {
									PRICE = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "가격",
											selected.getPRICE());
									if (PRICE != null) {
										itemlist.set(index, new CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE));
										updateList();
										callingplanlist.modifyList(index,
												new CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE));
										try {
											callingplanlist.WriteFile_CallingPlanList();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		});

		// 기존 데이터를 삭제하는 버튼
		JButton btnDelData = new JButton("삭제");
		btnDelData.setBounds(637, 300, 181, 45);
		add(btnDelData);
		btnDelData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();

				itemlist.remove(index);
				callingplanlist.getTotal_CallingPlan_list().remove(index);
				updateList();
				try {
					callingplanlist.WriteFile_CallingPlanList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// class 형식으로 이벤트를 표현하는 방법
	class RadioButtonSelectedEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			updateList();
		}
	}

	private void updateList() {

		listModel.clear();
		for (CallingPlan element : itemlist) {
			listModel.addElement(element.getPAY_NAME());
		}
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}
}