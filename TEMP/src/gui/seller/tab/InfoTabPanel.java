package gui.seller.tab;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import gui.tab.AbstractTabPanel;
import seller.Seller;

public class InfoTabPanel extends AbstractTabPanel {

	private DefaultListModel listModel;
	private JList list;
	private Seller seller = new Seller();

	public InfoTabPanel(Seller seller) {
		super();
		// 사이즈 설정
		setSize(new Dimension(panelWidth, panelHeight));

		JLabel lblTitle = new JLabel("정보 관리");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblTitle.setBounds(40, 33, 240, 40);
		add(lblTitle);

		JLabel lblId = new JLabel("수정할 정보를 더블클릭하세요");
		lblId.setBounds(40, 102, 350, 35);
		add(lblId);

		// Scroll 가능한 List 보여주는 객체 생성 (ScrollPane 안에 JList 넣음
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 150, 276, 194);
		add(scrollPane);

		listModel = new DefaultListModel();
		listModel.addElement("ID");
		listModel.addElement("PW");
		listModel.addElement("매장 정보");
		listModel.addElement("이름");
		listModel.addElement("사업자 번호");

		JList list = new JList(listModel);
		scrollPane.setViewportView(list);

		// 더블 클릭 시 정보 보여주도록 함
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

				// Double-click일 경우
				if (evt.getClickCount() == 2) {
					// 클릭된 Index 가져옴
					int index = list.locationToIndex(evt.getPoint());

					String selected = (String) listModel.getElementAt(index);
					String temp = "";
					switch (index) {
					case 0:
						temp = JOptionPane.showInputDialog(InfoTabPanel.this, selected, seller.getID());
						if (temp != null)
							seller.setID(temp);
						break;
					case 1:
						temp = JOptionPane.showInputDialog(InfoTabPanel.this, selected, seller.getPW());
						if (temp != null)
							seller.setPW(temp);
						break;
					case 2:
						temp = JOptionPane.showInputDialog(InfoTabPanel.this, selected, seller.getMarketInfo());
						if (temp != null)
							seller.setMarketInfo(temp);
						break;
					case 3:
						temp = JOptionPane.showInputDialog(InfoTabPanel.this, selected, seller.getName());
						if (temp != null)
							seller.setName(temp);
						break;
					case 4:
						temp = JOptionPane.showInputDialog(InfoTabPanel.this, selected, seller.getBusinessNumber());
						if (temp != null)
							seller.setBusinessNumber(temp);
					default:
						break;
					}

				}
			}
		});
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
