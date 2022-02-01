package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.repository.AddressBookRepository;

public class AddressBookService {

	AddressBookRepository repository = new AddressBookRepositoryImpl();

	public void addContact() {
		repository.addContact();
	}

	public void editContact() {
		repository.editContact();
	}

	public void deleteContact() {
		repository.deleteContact();
	}

	public void displayAddressBook() {
		repository.displayAddressBook();
	}

	public void searchPerson() {
		repository.searchPerson();
	}

	public void countPerson() {
		repository.countPerson();
	}

}
