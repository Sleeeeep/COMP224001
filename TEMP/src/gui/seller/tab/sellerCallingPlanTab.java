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
		JLabel lblTitle = new JLabel("����� ���");
		lblTitle.setFont(new Font("���� ����", Font.BOLD, 30));
		lblTitle.setBounds(40, 33, 240, 40);
		add(lblTitle);

		// Scroll ������ List �����ִ� ��ü ���� (ScrollPane �ȿ� JList ����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 150, 530, 425);
		add(scrollPane);

		// Listmodel ���� (List�� element�� ���� ������ ����)
		listModel = new DefaultListModel();
		for (CallingPlan element : itemlist) {
			listModel.addElement(element.getPAY_NAME());
		}

		// Listmodel�� ������ JList �����ϰ� Scroll ������ Panel�� �߰�
		list = new JList(listModel);
		scrollPane.setViewportView(list);

		// ���� �����͸� �߰��ϴ� ��ư
		JButton btnNewData = new JButton("�ű� ���");
		btnNewData.setBounds(637, 200, 181, 45);
		add(btnNewData);
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String PAY_NAME = "", MESSAGE = "", CALL = "", DATA = "", PRICE = "";

				PAY_NAME = JOptionPane.showInputDialog("����� �̸�");
				if (PAY_NAME != null) {
					MESSAGE = JOptionPane.showInputDialog("���ڷ�");
					if (MESSAGE != null) {
						CALL = JOptionPane.showInputDialog("��ȭ��");
						if (CALL != null) {
							DATA = JOptionPane.showInputDialog("������");
							if (DATA != null) {
								PRICE = JOptionPane.showInputDialog("����");
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

		// ����Ʈ���� ���� Ŭ�� �� ���� ����
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				if (evt.getClickCount() != 0) {
				}
				// Double-click�� ���
				if (evt.getClickCount() == 2) {
					// Ŭ���� Index ������
					int index = list.locationToIndex(evt.getPoint());
					if (index < 0)
						return;

					if (index >= itemlist.size())
						return;
					CallingPlan selected = itemlist.get(index);
					String PAY_NAME = "", MESSAGE = "", CALL = "", DATA = "", PRICE = "";

					PAY_NAME = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "����� �̸�",
							selected.getPAY_NAME());
					if (PAY_NAME != null) {
						MESSAGE = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "���ڷ�",
								selected.getMESSAGE());
						if (MESSAGE != null) {
							CALL = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "��ȭ��", selected.getCALL());
							if (CALL != null) {
								DATA = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "������",
										selected.getDATA());
								if (DATA != null) {
									PRICE = JOptionPane.showInputDialog(sellerCallingPlanTab.this, "����",
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

		// ���� �����͸� �����ϴ� ��ư
		JButton btnDelData = new JButton("����");
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

	// class �������� �̺�Ʈ�� ǥ���ϴ� ���
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