package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Employee> list = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while(line != null) {
				String[] filders = line.split(",");
				list.add(new Employee(filders[0], filders[1], Double.parseDouble(filders[2])));
				line = br.readLine();
			}
			
			List<Employee> listEmail = list.stream()
					.filter(e -> e.getSalary() > salary)
					.sorted((e1, e2) -> e1.getEmail().toUpperCase().compareTo(e2.getEmail().toUpperCase()))
					.collect(Collectors.toList());
			//OR
			List<String> listEmail2 = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			for (Employee e : listEmail) {
				System.out.println(e.getEmail());
			}
			
			
			Double sum = list.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + sum);
			
		} catch (Exception e) {
			System.out.println("Error in read file: " + e.getMessage());
		}
		
		sc.close();
	}
}
