package com.extendable;

import java.util.*;

import com.model.Employee;

interface employee {
	void add();

	void delete();

	void update();

	void search();

	void display();
}

public class EmployeeImp implements employee {
	List<Employee> list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	@Override
	public void add() {
		System.out.println("Enter the employee's Id :");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the employee's name:");
		String name = sc.nextLine();
		System.out.println("Enter the employee's address:");
		String address = sc.nextLine();
		System.out.println("Enter the employee's Phone_no :");
		String phone = sc.nextLine();
		list.add(new Employee(id, name, address, phone));

	}

	@Override
	public void delete() {
		boolean flag = false;
		System.out.println("Enter the employee id to delete:");
		int id = sc.nextInt();
		Iterator<Employee> itr = list.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			if (emp.getId() == id) {
				itr.remove();
				flag = true;
			}
		}
		if (flag)
			System.out.println("Emp_id:" + id + " deleted");
		else
			System.out.println("Emp_id:" + id + " not Found");
	}

	@Override
	public void update() {
		boolean flag = false;
		System.out.println("Enter the employee id to update:");
		int id = sc.nextInt();
		sc.nextLine();
		ListIterator<Employee> itr = list.listIterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			if (emp.getId() == id) {
				System.out.println("Enter the name you want to update:");
				String name = sc.nextLine();
				if (name.equals(""))
					name = emp.getName();
				System.out.println("Enter the address you want to update:");
				String address = sc.nextLine();
				if (address.equals(""))
					address = emp.getAddress();
				System.out.println("Enter the phone_no you want to update:");
				String phone = sc.nextLine();
				if (phone.equals(""))
					phone = emp.getPhone();
				itr.set(new Employee(id, name, address, phone));
				flag = true;
			}
		}
		if (flag)
			System.out.println("Emp_id:" + id + " updated");
		else
			System.out.println("Emp_id:" + id + " not Found!!");
	}

	@Override
	public void display() {
		Iterator<Employee> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	@Override
	public void search() {
		boolean flag = false;
		System.out.println("Enter the id to search:");
		int id = sc.nextInt();
		Iterator<Employee> itr = list.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			if (emp.getId() == id)
				flag = true;
		}
		if (flag)
			System.out.println("Emp_id:" + id + " found");
		System.out.println("Emp_id:" + id + " not found");
	}

}
