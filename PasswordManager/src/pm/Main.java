package pm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static HashMap<String, ArrayList<String>> details = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) {

		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		System.out.println("What would you like to do today?");
		System.out.println("1. Add Password\n" + "2. Retrieve Password\n" + "3. Remove Password\n" + "4. Exit");
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		int choice = sc.nextInt();
		do {
			if (choice == 1) {
				choice1();
			} else if (choice == 2) {
				choice2();
			} else if (choice == 3) {
				choice3();
			} else {
				System.out.println("Number invalid. Enter Another number");
			}

			System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
			System.out.println("What would you like to do next?");
			System.out.println("1. Add Password\n" + "2. Retrieve Password\n" + "3. Remove Password\n" + "4. Exit");
			System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
			choice = sc.nextInt();

		} while (choice != 4);

	}

	public static void choice1() {
		System.out.println("Enter software name");
		String software = sc.next().toLowerCase();
		System.out.println("Enter username");
		String username = sc.next().toLowerCase();
		System.out.println("Enter password");
		String password = sc.next().toLowerCase();
		if (details.containsKey(software)) {
			ArrayList<String> currArray = details.get(software);
			currArray.add(username);
			currArray.add(password);
			details.put(software, currArray);
		} else {
			details.put(software, new ArrayList(List.of(username, password)));
		}
		System.out.println("Data for " + software + " added");
	}

	public static void choice2() {
		System.out.println("Enter software name");
		String software = sc.next().toLowerCase();
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		System.out.println("Usernames\t\tPasswords");
		ArrayList<String> userPass = details.get(software);
		for (int i = 0; i < userPass.size(); i++) {
			if (i % 2 == 0)
				System.out.print(i + 1 + ". " + userPass.get(i));
			else
				System.out.println("\t\t" + userPass.get(i));
		}
	}

	public static void choice3() {
		System.out.println("Enter software name");
		String software = sc.next().toLowerCase();
		ArrayList<String> userPass = details.get(software);
		if (userPass.size() > 2) {
			System.out.println("Which passwords do you want to delete");
			System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
			System.out.println("Usernames\t\tPasswords");
			for (int i = 0; i < userPass.size(); i++) {
				if (i % 2 == 0)
					System.out.print(i + 1 + ". " + userPass.get(i));
				else
					System.out.println("\t\t" + userPass.get(i));
			}
			System.out.println("Enter the username you want to delete");
			String usr = sc.next();
		} else {
			details.remove(software);
		}

	}

}
