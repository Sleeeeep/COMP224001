package callingplan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import phone.Phone;

public class CallingPlan_List {
	private ArrayList<CallingPlan> total_CallingPlan_list;
	private ArrayList<CallingPlan> output_CallingPlan_list;
	private CallingPlan thinkCallingPlan;
	private String Path = "./CallingPlan/";
	private String FILENAME = "CALLINGPLANINFO";

	public CallingPlan_List() {

	}

	public CallingPlan_List(int mode) {
		total_CallingPlan_list = new ArrayList<CallingPlan>();

		if (mode == 1) // 판매자에서 불러 왔을 때.
		{
			boolean check_dir;

			check_dir = CheckExistDir("CallingPlan");

			if (check_dir)
				ReadFile_CallingPlanList();
			else // CallingPlan이라는 폴더 없을 때 생성.
			{
				File dir_phone = new File("CallingPlan");
				if (!dir_phone.mkdirs()) {
					System.err.println("MKDIR Error");
				}
			}
		} else if (mode == 2) // 구매자에게서 불러 왔을 때. and 추천
		{
			boolean check_dir;

			check_dir = CheckExistDir("CallingPlan");

			if (check_dir) {
				ReadFile_CallingPlanList();
			} else {
				File dir_CallingPlan = new File("CallingPlan");
				if (!dir_CallingPlan.mkdirs()) {
					System.err.println("MKDIR Error");
				}
			}
		}
	}

	public int Get_Total_CallingPlan_List_Size() // 요금제 목록 사이즈 리턴
	{
		return total_CallingPlan_list.size();
	}

	public CallingPlan Get_SelectCallingPlan(int num) // 요금제 목록 항목중 하나 선택해서 리턴
	{
		CallingPlan tempC = total_CallingPlan_list.get(num);
		return tempC;
	}

	public boolean CheckExistFile(String filename) {
		File file = new File(filename);

		boolean isExists = file.exists();

		return isExists;
	}

	public boolean CheckExistDir(String dirname)

	{
		File dir = new File(dirname);

		boolean isExists = dir.isDirectory();

		return isExists;
	}

	public void modifyList(int index, CallingPlan newCallingPlan) {
		total_CallingPlan_list.set(index, newCallingPlan);
	}

	public void insert_CallingPlan(String PAY_NAME, String MESSAGE, String CALL, String DATA, String PRICE) {
		CallingPlan temp = new CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE);
		total_CallingPlan_list.add(temp);
	}

	public void WriteFile_CallingPlanList() throws IOException {
		FileOutputStream out;
		try {
			out = new FileOutputStream(Path + FILENAME);

			for (int i = 0; i < total_CallingPlan_list.size(); i++) {
				CallingPlan temp = total_CallingPlan_list.get(i);

				out.write(temp.getPAY_NAME().getBytes());
				out.flush();
				out.write(0x00);
				out.flush();

				out.write(temp.getMESSAGE().getBytes());
				out.flush();
				out.write(0x00);
				out.flush();

				out.write(temp.getCALL().getBytes());
				out.flush();
				out.write(0x00); // NULL. NULL을 통해 내용 구분을 목적으로 입력.
				out.flush();

				out.write(temp.getDATA().getBytes());
				out.flush();
				out.write(0x00);
				out.flush();

				out.write(temp.getPRICE().getBytes());
				out.flush();
				out.write(0x00);
				out.flush();

				char c = 10;
				out.write(c);
				out.flush();

			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReadFile_CallingPlanList() {
		String PAY_NAME = ""; // index 1

		String MESSAGE = ""; // index 2

		String CALL = ""; // index 3

		String DATA = ""; // index 4

		String PRICE = ""; // index 5

		if (!CheckExistFile(Path + FILENAME)) {
			File file = new File(Path + FILENAME);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileInputStream fin = new FileInputStream(Path + FILENAME);
			Reader reader = new InputStreamReader(fin, "euc-kr");
			BufferedReader in = new BufferedReader(reader);
			Scanner s = new Scanner(System.in);

			char b;
			String line = "";

			int BUFFER_SIZE = 1000;

			in.mark(BUFFER_SIZE);
			while ((line = in.readLine()) != null) {
				// System.out.print(line);
				in.reset();

				while ((b = (char) in.read()) != '\0') {
					// System.out.print(b);
					PAY_NAME += b;
				}

				while ((b = (char) in.read()) != '\0') {
					MESSAGE += b;
				}

				while ((b = (char) in.read()) != '\0') {
					CALL += b;
				}

				while ((b = (char) in.read()) != '\0') {
					DATA += b;
				}

				while ((b = (char) in.read()) != '\0') {
					PRICE += b;
				}

				if ((b = (char) in.read()) != 10) {
					System.out.println("Error");
				}
				in.mark(BUFFER_SIZE);
				CallingPlan temp = new CallingPlan(PAY_NAME, MESSAGE, CALL, DATA, PRICE);
				total_CallingPlan_list.add(temp);

				PAY_NAME = ""; // index 1

				MESSAGE = ""; // index 2

				CALL = ""; // index 3

				DATA = ""; // index 4

				PRICE = ""; // index 5

			}

			fin.close();
			in.close();

		} catch (IOException e) { // 존재여부 확인하고 와서 들어갈 일 없음.
			e.printStackTrace();
		}

	}

	public void Modifyanddelete() {
		for (int i = 0; i < total_CallingPlan_list.size(); i++) {
			System.out.println(i + ". " + total_CallingPlan_list.get(i).getPAY_NAME());
		}

		int value = 0;
		boolean flag = true;
		while (flag) {
			value = 0;

			System.out.print("Choose Delete or Modify CallingPlan Number : ");

			Scanner s = new Scanner(System.in);
			value = s.nextInt();

			for (int i = 0; i < total_CallingPlan_list.size(); i++) {
				if (value == i) {
					flag = false;
					break;
				}
			}
		}

		int tem;

		while (true) {
			tem = 0;

			System.out.print("Choose 1: Modify 2: Delete : ");

			Scanner s = new Scanner(System.in);
			tem = s.nextInt();

			if (tem == 1) {
				total_CallingPlan_list.remove(value);
				break;
			} else if (tem == 2) {
				total_CallingPlan_list.remove(value);
				break;
			} else {
				System.out.println("Wrong Number");
			}
		}

	}

	public ArrayList<CallingPlan> getTotal_CallingPlan_list() {
		return total_CallingPlan_list;
	}

	public void setTotal_CallingPlan_list(ArrayList<CallingPlan> total_CallingPlan_list) {
		this.total_CallingPlan_list = total_CallingPlan_list;
	}

	public ArrayList<CallingPlan> getOutput_CallingPlan_list() {
		return output_CallingPlan_list;
	}

	public void setOutput_CallingPlan_list(ArrayList<CallingPlan> output_CallingPlan_list) {
		this.output_CallingPlan_list = output_CallingPlan_list;
	}

	public CallingPlan getThinkCallingPlan() {
		return thinkCallingPlan;
	}

	public void setThinkCallingPlan(CallingPlan thinkCallingPlan) {
		this.thinkCallingPlan = thinkCallingPlan;
	}

}
