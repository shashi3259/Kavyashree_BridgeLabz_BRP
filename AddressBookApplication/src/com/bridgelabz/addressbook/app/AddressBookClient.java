package com.bridgelabz.addressbook.app;

import java.util.Scanner;

import com.bridgelabz.addressbook.controller.AddressBookController;

public class AddressBookClient {

	public static void main(String[] args) {
		AddressBookController controller = new AddressBookController();
		while (true) {
			System.out.println("--------------------------------------------------------");
			System.out.println("Choose an Operation from the following list.");
			System.out.println("1 -Add a contact");
			System.out.println("2 -Edit a contact");
			System.out.println("3 -Delete a contact");
			System.out.println("4 -Display addressBook");
			System.out.println("5 -Search a Contact");
			System.out.println("6 -Count of Contacts");
			System.out.println("E -Exit");
			System.out.println("--------------------------------------------------------");

			String opertion = readInput("Operation");
			switch (opertion) {
			case "1":
				controller.addContact();
				break;
			case "2":
				controller.editContact();
				break;
			case "3":
				controller.deleteContact();
				break;
			case "4":
				controller.displayAddressBook();
				break;
			case "5":
				controller.searchPerson();
				break;
			case "6":
				controller.countPerson();
				break;
			case "E":
				System.exit(0);
			}
		}
	}

	static String readInput(String term) {
		System.out.print("Enter " + term + ":");
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
}
