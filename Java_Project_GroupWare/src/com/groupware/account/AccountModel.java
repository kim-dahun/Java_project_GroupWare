package com.groupware.account;

public class AccountModel {
	// MODEL
	
	// 속성
	private String id;
	private String password;
	private String name;
	private int age;
	private String phone;
	private String email;
	private int departmentCode;
	private int employeeNumber;
	private static final boolean ADMIN = false;
	private int confirmLogin = 0;
	// 생성자
	public AccountModel() {

	}

	public AccountModel(String id, String password, String name, int age, String phone, String email,
			int departmentCode, int employeeNumber) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.departmentCode = departmentCode;
		this.employeeNumber = employeeNumber;
	}
	// 모델을 위한 Get,Set 메서드
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	// 습관적으로 한 오버라이딩
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("id : %s, pw : %s, name : %s, age : %d, phone : %s, E-mail : %s", id, password, name, age,
				phone, email);
	}

}
