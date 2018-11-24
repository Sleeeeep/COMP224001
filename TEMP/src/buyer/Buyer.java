package buyer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Buyer {
	private String name;
	private int age;
	private String Phone;

	public Buyer() {

	}

	public Buyer(String name, String age, String Phone) {
		setName(name);
		setAge(Integer.parseInt(age));
		setPhone(Phone);
	}

	public boolean CheckExistFile(String filename) {
		File file = new File(filename);

		boolean isExists = file.exists();

		return isExists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

}
