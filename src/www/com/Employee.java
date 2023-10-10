package www.com;

import java.util.Date;
import java.util.UUID;

public class Employee {

	private UUID employeeid;
	private String name;
	private String skills;
	private int age;
	private int salary;
	private Date joiningDate;

	public Employee(UUID employeeid, String name, String skills, int age, int salary, Date joiningDate) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}

	public UUID getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(UUID employeeid) {
		this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", name=" + name + ", skills=" + skills + ", age=" + age
				+ ", salary=" + salary + ", joiningDate=" + joiningDate + "]";
	}

}
