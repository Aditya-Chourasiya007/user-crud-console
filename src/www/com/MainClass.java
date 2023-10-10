package www.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String args[]) throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		Scanner inputForNumber = new Scanner(System.in);
		Scanner inputForLine = new Scanner(System.in);

		int choice;
		do {
			System.out.println("1.Save Employee");
			System.out.println("2.Show Employee");
			System.out.println("3.Search Employee");
			System.out.println("4.Delete Employee");
			System.out.println("5.Update Employee");
			System.out.println("6.Shorting By Age Employee");
			System.out.print("Enter your choice : ");
			choice = inputForNumber.nextInt();
			switch (choice) {
			// Insert Operation
			case 1:
				insertData(employeeList, inputForNumber, inputForLine);
				break;
			// update Operation
			case 5:
				updateEmployee(employeeList, inputForNumber, inputForLine);
				break;
				// Display Operation
			case 2:
				System.out.println("---------------------");
				Iterator<Employee> displayiterator = employeeList.iterator();
				while (displayiterator.hasNext()) {
					Employee e = displayiterator.next();
					System.out.println(e);
				}
				System.out.println("---------------------");
				break;
			// Search Opeartion
			case 3:
				boolean founds = false;
				UUID searchEmployeeId = null;
				while (!founds) {
					System.out.print("Enter employeeid for Search (UUID format): ");
					String searchEmployeeIdStr = inputForLine.nextLine();
					try {
						searchEmployeeId = UUID.fromString(searchEmployeeIdStr);
						founds = true;
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid UUID format. Please enter a valid UUID.");
					}
				}

				System.out.println("---------------------");

				for (Employee e : employeeList) {
					if (e.getEmployeeid().equals(searchEmployeeId)) {
						System.out.println(e);
						founds = true;
					}
				}

				if (!founds) {
					System.out.println("Record Not Found");
				}

				System.out.println("---------------------");
				break;
			// Delete Operation
			case 4:
				boolean found1 = false;
				UUID deleteEmployeeId = null;

				while (!found1) {
					System.out.print("Enter employeeid for Delete (UUID format): ");
					String deleteEmployeeIdStr = inputForLine.nextLine();

					try {
						deleteEmployeeId = UUID.fromString(deleteEmployeeIdStr);
						found1 = true; // Set found to true if parsing succeeds
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid UUID format. Please enter a valid UUID.");
					}
				}

				System.out.println("---------------------");

				Iterator<Employee> deleteIterator = employeeList.iterator();
				while (deleteIterator.hasNext()) {
					Employee e = deleteIterator.next();
					if (e.getEmployeeid().equals(deleteEmployeeId)) {
						deleteIterator.remove();
						found1 = true;
					}
				}

				if (!found1) {
					System.out.println("Record Not Found");
				} else {
					System.out.println("Record is Deleted Successfully");
				}

				System.out.println("---------------------");
				break;
			// shorting Opeartion
			case 6:
				final Function<Employee, Integer> byAge = x -> x.getAge();
				final Function<Employee, String> byTheirName = x -> x.getName();
				List<Employee> sortByAgeList = employeeList.stream()
						.sorted(Comparator.comparing(byAge).thenComparing(byTheirName)).collect(Collectors.toList());
				sortByAgeList.forEach(System.out::println);
				break;
			}
		} while (choice != 0);
	}

	public void commanEmployee() {

	}

	private static boolean isValidDateFormat(String dateString) {
		String dateFormatPattern = "\\d{2}/\\d{2}/\\d{4}";
		return dateString.matches(dateFormatPattern);
	}

	public static void insertData(List<Employee> employeesList, Scanner inputForLine, Scanner inputForNumber)
			throws ParseException {
		Employee employee = enterEmployeeDetails(inputForLine, inputForNumber);
		employeesList.add(employee);
		System.out.println("Employee added successfully.");
	}

	public static void updateEmployee(List<Employee> employeesList, Scanner inputForLine, Scanner inputForNumber) {
		System.out.println("Enter the employee ID to update:");
		String updateEmployeeId = inputForLine.next();
		UUID updateUuid;
		try {
			updateUuid = UUID.fromString(updateEmployeeId);
		} catch (IllegalArgumentException e) {
			updateUuid = null;
		}

		boolean employeeFound = false;
		for (Employee employee : employeesList) {
			if (updateUuid != null && employee.getEmployeeid().equals(updateUuid)) {
				System.out.println("Employee found. Enter updated information:");
				Employee updatedEmployee = enterEmployeeDetails(inputForLine, inputForNumber);

				// Update the employee information
				employee.setName(updatedEmployee.getName());
				employee.setSkills(updatedEmployee.getSkills());
				employee.setAge(updatedEmployee.getAge());
				employee.setSalary(updatedEmployee.getSalary());
				employee.setJoiningDate(updatedEmployee.getJoiningDate());

				System.out.println("Employee information updated.");
				employeeFound = true;
				break;
			}
		}

		if (!employeeFound) {
			System.out.println("Employee not found with ID: " + updateEmployeeId);
		}
	}

	public static Employee enterEmployeeDetails(Scanner inputForLine, Scanner inputForNumber) {
		System.out.println("Enter employee name:");
		String name = inputForLine.nextLine();

		System.out.println("Enter employee skills:");
		String skills = inputForLine.nextLine();

		int age;
		while (true) {
			System.out.print("Enter age: ");
			if (inputForNumber.hasNextInt()) {
				age = inputForNumber.nextInt();
				break;
			} else {
				System.out.println("Please enter a valid age.");
				inputForNumber.next();
			}
		}

		int salary;
		while (true) {
			System.out.print("Enter salary: ");
			if (inputForNumber.hasNextInt()) {
				salary = inputForNumber.nextInt();
				break;
			} else {
				System.out.println("Please enter a valid salary.");
				inputForNumber.next();
			}
		}

		Date joiningDate;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		while (true) {
			System.out.print("Enter joining date (dd/MM/yyyy): ");
			String dateString = inputForLine.next();
			if (isValidDateFormat(dateString)) {
				try {
					joiningDate = simpleDateFormat.parse(dateString);
					break;
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please try again.");
				}
			} else {
				System.out.println("Please enter a date in dd/MM/yyyy format.");
			}
		}

		return new Employee(UUID.randomUUID(), name, skills, age, salary, joiningDate);
	}

}