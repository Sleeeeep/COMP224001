package common.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * 
 * @author Wooyong Choi
 * 프레임들의 공통 사항 제공
 * 
 */
public abstract class AbstractFrame extends JFrame {

	protected Container contentPane;
	
	
	public AbstractFrame(int width, int height) {

		// 기본 설정 (제목, 사이즈, 종료버튼)
		setTitle("GUI Framework");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		// 초기 위치 설정 (해상도 기준 가운데에 위치하게)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (int)(screen.getWidth() / 2 - width / 2);
        int yPos = (int)(screen.getHeight() / 2 - height / 2);
        this.setLocation(xPos, yPos);
		
		// Frame의 기본 ContnetPanel을 가져옴
		contentPane = getContentPane();
		
		// Panel의 색상 흰색으로 설정
		contentPane.setBackground(Color.WHITE);
		
		// Panel을 Absolute layout으로 설정 (좌표로 객체 위치 설정)
		contentPane.setLayout(null);
	}
	
	// 폰트 설정
	protected static void setUIFont(FontUIResource f) {
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}
}
