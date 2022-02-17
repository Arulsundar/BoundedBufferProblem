package com.extendable;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		employee obj = new EmployeeImp();
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1.ADD EMPLOYEE");
			System.out.println("2.DELETE EMPLOYEE");
			System.out.println("3.UPDATE EMPLOYEE");
			System.out.println("4.SEARCH EMPLOYEE");
			System.out.println("5.DISPLAY EMPLOYEE");
			System.out.println("6.EXIT");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				obj.add();
				break;
			case 2:
				obj.delete();
				break;
			case 3:
				obj.update();
				break;
			case 4:
				obj.search();
				break;
			case 5:
				obj.display();
				break;
			case 6: {
				choice = 0;
				System.out.println("__________Program Exited__________");
			}
			}

		} while (choice != 0);
		sc.close();
	}

}
