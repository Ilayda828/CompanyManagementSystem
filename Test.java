// This class tests the company system by reading input.txt, creating objects, running the scenario, and writing to output.txt.
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		ArrayList<Department> departments = new ArrayList<>();
		ArrayList<Project> projects = new ArrayList<>();
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Person> persons = new ArrayList<>();
		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Manager> managers = new ArrayList<>();
		ArrayList<RegularEmployee> regularEmployees = new ArrayList<>();
		ArrayList<SalesEmployee> salesEmployees = new ArrayList<>();
		ArrayList<Developer> developers = new ArrayList<>();
		ArrayList<Customer> customers = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");
				switch (tokens[0]) {
				case "Department": {
					int id = Integer.parseInt(tokens[1]);
					String name = tokens[2];
					departments.add(new Department(id, name));
					break;
				}
				case "Project": {
					String name = tokens[1];
					Calendar date = parseDate(tokens[2]);
					String state = tokens[3];
					projects.add(new Project(name, date, state));
					break;
				}
				case "Product": {
					String name = tokens[1];
					Calendar date = parseDate(tokens[2]);
					double price = Double.parseDouble(tokens[3]);
					products.add(new Product(name, date, price));
					break;
				}
				case "Person": {
					String firstName = tokens[1];
					String lastName = tokens[2];
					int id = Integer.parseInt(tokens[3]);
					String gender = tokens[4];
					Calendar birthDate = parseDate(tokens[5]);
					String maritalStatus = tokens[6];
					String hasDriverLicence = tokens[7];
					persons.add(new Person(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence));
					break;
				}
				case "Employee": {
					int id = Integer.parseInt(tokens[1]);
					double salary = Double.parseDouble(tokens[2]);
					Calendar hireDate = parseDate(tokens[3]);
					String depName = tokens[4];
					Person p = findPersonById(persons, id);
					Department dep = findDepartmentByName(departments, depName);
					Employee e = new Employee(p, salary, hireDate, dep);
					employees.add(e);
					break;
				}
				case "Manager": {
					int id = Integer.parseInt(tokens[1]);
					double bonusBudget = Double.parseDouble(tokens[2]);
					Employee e = findEmployeeById(employees, id);
					Manager m = new Manager(e, bonusBudget);
					managers.add(m);
					employees.remove(e);
					employees.add(m);
					break;
				}
				case "RegularEmployee": {
					int id = Integer.parseInt(tokens[1]);
					double perf = Double.parseDouble(tokens[2]);
					Employee e = findEmployeeById(employees, id);
					RegularEmployee re = new RegularEmployee(e, perf);
					regularEmployees.add(re);
					employees.remove(e);
					employees.add(re);
					break;
				}
				case "Developer": {
					int id = Integer.parseInt(tokens[1]);
					RegularEmployee re = findRegularEmployeeById(regularEmployees, id);
					ArrayList<Project> devProjects = new ArrayList<>();
					for (int i = 2; i < tokens.length; i++) {
						Project pr = findProjectByName(projects, tokens[i]);
						if (pr != null) devProjects.add(pr);
					}
					Developer d = new Developer(re, devProjects);
					developers.add(d);
					regularEmployees.remove(re);
					regularEmployees.add(d);
					break;
				}
				case "SalesEmployee": {
					int id = Integer.parseInt(tokens[1]);
					RegularEmployee re = findRegularEmployeeById(regularEmployees, id);
					ArrayList<Product> sales = new ArrayList<>();
					for (int i = 2; i < tokens.length; i++) {
						Product pr = findProductByName(products, tokens[i]);
						if (pr != null) sales.add(pr);
					}
					SalesEmployee se = new SalesEmployee(re, sales);
					salesEmployees.add(se);
					regularEmployees.remove(re);
					regularEmployees.add(se);
					break;
				}
				case "Customer": {
					int id = Integer.parseInt(tokens[1]);
					Person p = findPersonById(persons, id);
					ArrayList<Product> custProducts = new ArrayList<>();
					for (int i = 2; i < tokens.length; i++) {
						Product pr = findProductByName(products, tokens[i]);
						if (pr != null) custProducts.add(pr);
					}
					customers.add(new Customer(p, custProducts));
					break;
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Manager'lara çalışan ekle (aynı departmandaki regularEmployee'lar)
		for (Manager m : managers) {
			for (RegularEmployee re : regularEmployees) {
				if (re.getDepartment().getDepartmentName().equals(m.getDepartment().getDepartmentName())) {
					m.addEmployee(re);
				}
			}
		}

		// Senaryo işlemleri
		for (Manager m : managers) {
			m.distributeBonusBudget();
			m.raiseSalary(0.2);
		}
		for (RegularEmployee re : regularEmployees) {
			re.raiseSalary(0.3);
		}
		for (Developer d : developers) {
			d.raiseSalary(0.23);
		}
		for (SalesEmployee se : salesEmployees) {
			se.raiseSalary(0.18);
		}
		// En çok satış yapan SalesEmployee'a 10000 zam
		SalesEmployee maxSales = null;
		double maxTotal = 0;
		for (SalesEmployee se : salesEmployees) {
			double total = 0;
			for (Product p : se.getSales()) total += p.getPrice();
			if (total > maxTotal) {
				maxTotal = total;
				maxSales = se;
			}
		}
		if (maxSales != null) maxSales.raiseSalary(10000);

		// Çıktı dosyasına yaz
		try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
			for (Department dep : departments) {
				pw.println("************************************************");
				pw.println(dep);
				for (Manager m : managers) {
					if (m.getDepartment().getDepartmentName().equals(dep.getDepartmentName())) {
						pw.println("\t" + m);
						int empNo = 1;
						for (RegularEmployee re : m.getRegularEmployees()) {
							pw.print("\t\t" + empNo + ". ");
							pw.println(re);
							empNo++;
						}
					}
				}
			}
			pw.println();
			pw.println("**********************CUSTOMERS************************");
			for (Customer customer : customers) {
				pw.println(customer);
			}
			pw.println();
			pw.println("**********************PEOPLE************************");
			for (Person person : persons) {
				if (person.getId() == 145 || person.getId() == 189 || person.getId() == 236) {
					pw.println(person);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Yardımcı metotlar
	private static Calendar parseDate(String s) throws Exception {
		String[] parts = s.split("/");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]) - 1;
		int year = Integer.parseInt(parts[2]);
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		return c;
	}
	private static Person findPersonById(ArrayList<Person> list, int id) {
		for (Person p : list) if (p.getId() == id) return p;
		return null;
	}
	private static Employee findEmployeeById(ArrayList<Employee> list, int id) {
		for (Employee e : list) if (e.getId() == id) return e;
		return null;
	}
	private static RegularEmployee findRegularEmployeeById(ArrayList<RegularEmployee> list, int id) {
		for (RegularEmployee re : list) if (re.getId() == id) return re;
		return null;
	}
	private static Department findDepartmentByName(ArrayList<Department> list, String name) {
		for (Department d : list) if (d.getDepartmentName().equals(name)) return d;
		return null;
	}
	private static Project findProjectByName(ArrayList<Project> list, String name) {
		for (Project p : list) if (p.getProjectName() != null && p.getProjectName().equals(name)) return p;
		return null;
	}
	private static Product findProductByName(ArrayList<Product> list, String name) {
		for (Product p : list) if (p.getProductName().equals(name)) return p;
		return null;
	}
}