package pm;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static String url = "jdbc:mysql://localhost:3306/passwordmanager";
	static String user = "root";
	static String pass = "root";

	public static void main(String[] args) throws Exception {
		System.out.print("Enter master password: ");
		String masterPass = hash.toHexString(hash.getSHA(sc.next().trim()));
		boolean isCorrect = masterPass.equals(masterPasswordHandler.getMasterPassword());
		while (!isCorrect) {
			System.out.println("Incorrect master password");
			System.out.println("Forgot Password? y/n");
			String entry = sc.next().trim().toLowerCase();
			if (entry.equals("y")) {
				System.out.println("Enter the 256-bit key provided to you: ");
				String userKey = sc.next().trim();
				if (userKey.equals(masterPasswordHandler.getKey())) {
					isCorrect = true;
					System.out.println("Enter new master password: ");
					// set master password in data base
					String newMasterPass = hash.toHexString(hash.getSHA(sc.next().trim()));
					masterPasswordHandler.setMasterPassword(newMasterPass, userKey);
					// generate new key
					String newKey = masterPasswordHandler.generateKey();
					masterPasswordHandler.setKey(newMasterPass, newKey);
					System.out.println("This is your new key. Store it somewhere safe.");
					System.out.println(newKey);
				} else {
					System.out.println("Incorrect key. Terminating program");
					break;
				}
			} else {
				System.out.println("Re-enter master password: ");
				masterPass = hash.toHexString(hash.getSHA(sc.next().trim()));
				isCorrect = masterPass.equals(masterPasswordHandler.getMasterPassword());
			}
		}
		if (isCorrect) {
			messages();
			int choice = sc.nextInt();
			do {
				if (choice == 1) {
					interactions.add();
				} else if (choice == 2) {
					interactions.get();
				} else if (choice == 3) {
					interactions.remove();
				} else {
					System.out.println("Number invalid. Enter Another number");
				}
				messages();
				choice = sc.nextInt();
			} while (choice != 4);
		}
	}

	public static void messages() {
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
		System.out.println("What would you like to do next?");
		System.out.println("1. Add Password\n" + "2. Retrieve Password\n" + "3. Remove Password\n" + "4. Exit");
		System.out.println("---------------------\nxxxxxxxxxxxxxxxxxxxx\n---------------------");
	}

}
