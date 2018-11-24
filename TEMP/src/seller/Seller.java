package seller;

import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;

import callingplan.CallingPlan_List;
import gui.LoginFrame;
import phone.Phone_List;

import java.io.*;

public class Seller {
	private String ID, PW;
	private String marketInfo;
	private String name;
	private String businessNumber;
	private Phone_List phonelist;
	private CallingPlan_List callingplanlist;

	public Seller() {
		if (CheckExistFile("SellerInfo"))
			getFile();
	}

	public Seller(String NAME, String ID, String PW, String Market, String Business) {
		if (CheckExistFile("SellerInfo"))
			getFile();
		else {
			this.name = NAME;
			this.ID = ID;
			this.PW = PW;
			this.marketInfo = Market;
			this.businessNumber = Business;
			setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
		}
	}

	public boolean CheckExistFile(String filename) {
		File file = new File(filename);

		boolean isExists = file.exists();

		return isExists;
	}

	public void getFile() {
		try {
			FileInputStream fin = new FileInputStream("SellerInfo");
			Reader reader = new InputStreamReader(fin, "euc-kr");
			BufferedReader in = new BufferedReader(reader);

			char b;

			ID = "";
			PW = "";
			businessNumber = "";
			marketInfo = "";
			name = "";

			while ((b = (char) in.read()) != '\0')
				ID += b;
			while ((b = (char) in.read()) != '\0')
				PW += b;
			while ((b = (char) in.read()) != '\0')
				businessNumber += b;
			while ((b = (char) in.read()) != '\0')
				marketInfo += b;
			while ((b = (char) in.read()) != '\0')
				name += b;
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFile(String ID, String PW, String MK, String NAME, String BN) {
		try {
			// �� �������� NULL�� ���� ���н�Ŵ.

			FileOutputStream out = new FileOutputStream("SellerInfo");

			out.write(ID.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();

			out.write(PW.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();

			out.write(BN.getBytes());
			out.flush();
			out.write(0x00); // NULL. NULL�� ���� ���� ������ �������� �Է�.
			out.flush();

			out.write(MK.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();

			out.write(NAME.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMarketInfo() {
		return marketInfo;
	}

	public void setMarketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
		setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
		setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		this.PW = pW;
		setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
		setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setFile(this.ID, this.PW, this.marketInfo, this.name, this.businessNumber);
	}
}