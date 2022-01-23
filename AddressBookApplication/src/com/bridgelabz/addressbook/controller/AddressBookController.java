package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.service.AddressBookService;
import com.bridgelabz.addressbook.service.AddressBookServiceImpl;

public class AddressBookController {

	AddressBookService service = new AddressBookServiceImpl();

	public void addContact() {
		service.addContact();
	}

	public void editContact() {
		service.editContact();
	}

	public void deleteContact() {
		service.deleteContact();
	}

	public void displayAddressBook() {
		service.displayAddressBook();
	}

	public void searchPerson() {
		service.searchPerson();
	}

	public void countPerson() {
		service.countPerson();
	}

}
