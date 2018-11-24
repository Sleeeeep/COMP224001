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

import callingplan.CallingPlan;
import phone.*;
import gui.LoginFrame;
import gui.tab.AbstractTabPanel;
import seller.*;

public class sellerPhoneTab extends AbstractTabPanel {
	private DefaultListModel listModel;
	private JList list;

	final List<Phone> itemlist = new ArrayList<>();

	public sellerPhoneTab(Seller seller) {
		super();
		Phone_List phonelist = new Phone_List(1);

		for (int i = 0; i < phonelist.Get_Total_Phone_List_Size(); i++)
			itemlist.add(phonelist.getTotal_phone_list().get(i));

		// GUI part
		JLabel lblTitle = new JLabel("���� ���");
		lblTitle.setFont(new Font("���� ����", Font.BOLD, 30));
		lblTitle.setBounds(40, 33, 240, 40);
		add(lblTitle);

		// Scroll ������ List �����ִ� ��ü ���� (ScrollPane �ȿ� JList ����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 150, 530, 425);
		add(scrollPane);

		// Listmodel ���� (List�� element�� ���� ������ ����)
		listModel = new DefaultListModel();
		for (Phone element : itemlist) {
			listModel.addElement(element.getMODEL_NAME());
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
				String MODEL_NAME = "", CPU_INFO = "", DISPLAY = "", RAM = "", STORAGE = "", PRICE = "",
						PERFORMANCE = "", MANUFACTURE = "";

				MODEL_NAME = JOptionPane.showInputDialog("�� �̸�");
				if (MODEL_NAME != null) {
					CPU_INFO = JOptionPane.showInputDialog("CPU");
					if (CPU_INFO != null) {
						DISPLAY = JOptionPane.showInputDialog("ȭ�� ũ��");
						if (DISPLAY != null) {
							RAM = JOptionPane.showInputDialog("RAM");
							if (RAM != null) {
								STORAGE = JOptionPane.showInputDialog("�뷮");
								if (STORAGE != null) {
									PRICE = JOptionPane.showInputDialog("����");
									if (PRICE != null) {
										PERFORMANCE = JOptionPane.showInputDialog("�����̾� or ����");
										if (PERFORMANCE != null) {
											MANUFACTURE = JOptionPane.showInputDialog("1-���� 2-�Ｚ 3-LG");
											if (MANUFACTURE != null) {
												itemlist.add(new Phone(MODEL_NAME, CPU_INFO, DISPLAY, RAM, STORAGE,
														PRICE, PERFORMANCE, MANUFACTURE));
												updateList();
												phonelist.insert_Phone(MODEL_NAME, CPU_INFO, DISPLAY, RAM, STORAGE,
														PRICE, PERFORMANCE, MANUFACTURE);
												try {
													phonelist.WriteFile_PhoneList();
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
					Phone selected = itemlist.get(index);
					String MODEL_NAME = "", CPU_INFO = "", DISPLAY = "", RAM = "", STORAGE = "", PRICE = "",
							PERFORMANCE = "", MANUFACTURE = "";

					MODEL_NAME = JOptionPane.showInputDialog(sellerPhoneTab.this, "�� �̸�",
							selected.getMODEL_NAME());
					if (MODEL_NAME != null) {
						CPU_INFO = JOptionPane.showInputDialog(sellerPhoneTab.this, "CPU", selected.getCPU_INFO());
						if (CPU_INFO != null) {
							DISPLAY = JOptionPane.showInputDialog(sellerPhoneTab.this, "ȭ��ũ��",
									selected.getDISPLAY());
							if (DISPLAY != null) {
								RAM = JOptionPane.showInputDialog(sellerPhoneTab.this, "RAM", selected.getRAM());
								if (RAM != null) {
									STORAGE = JOptionPane.showInputDialog(sellerPhoneTab.this, "�뷮",
											selected.getSTORAGE());
									if (STORAGE != null) {
										PRICE = JOptionPane.showInputDialog(sellerPhoneTab.this, "����",
												selected.getPRICE());
										if (PRICE != null) {
											PERFORMANCE = JOptionPane.showInputDialog(sellerPhoneTab.this,
													"�����̾� or ����", selected.getPERFORMANCE());
											if (PERFORMANCE != null) {
												MANUFACTURE = JOptionPane.showInputDialog(sellerPhoneTab.this,
														"1-���� 2-�Ｚ 3-LG", selected.getMANUFACTURE());
												if (MANUFACTURE != null) {
													itemlist.set(index, new Phone(MODEL_NAME, CPU_INFO, DISPLAY, RAM,
															STORAGE, PRICE, PERFORMANCE, MANUFACTURE));
													updateList();
													phonelist.modifyList(index, new Phone(MODEL_NAME, CPU_INFO, DISPLAY,
															RAM, STORAGE, PRICE, PERFORMANCE, MANUFACTURE));
													try {
														phonelist.WriteFile_PhoneList();
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
				phonelist.getTotal_phone_list().remove(index);
				updateList();
				try {
					phonelist.WriteFile_PhoneList();
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
		for (Phone element : itemlist) {
			listModel.addElement(element.getMODEL_NAME());
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