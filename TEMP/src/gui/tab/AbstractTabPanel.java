package gui.tab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import seller.*;
import buyer.*;

public abstract class AbstractTabPanel extends JPanel {

	protected static int panelWidth = 887;
	protected static int panelHeight = 608;

	public AbstractTabPanel() {
		// ������ ����
		setSize(new Dimension(panelWidth, panelHeight));

		// Panel�� ���� ������� ����
		setBackground(new Color(0xEBF7FF));

		// Panel�� Absolute layout���� ���� (��ǥ�� ��ü ��ġ ����)
		setLayout(null);
	}
}