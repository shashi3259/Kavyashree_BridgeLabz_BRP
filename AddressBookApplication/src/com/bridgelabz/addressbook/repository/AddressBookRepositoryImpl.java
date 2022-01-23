package com.bridgelabz.addressbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.addressbook.model.Person;

public class AddressBookRepositoryImpl implements AddressBookRepository {

	String jdbcUrl = "jdbc:mysql://localhost:3306/addressbook?useSSL=false";
	String username = "root";
	String password = "root";
	Connection con;

	static final String SELECT_ALL_QUERY = "SELECT * FROM person ";
	static final String SELECT_BY_ID_QUERY = "SELECT * FROM person WHERE id = ?";
	static final String SELECT_BY_NAME_QUERY = "SELECT * FROM person WHERE name = ?";
	static final String COUNT_QUERY = "SELECT COUNT(1) AS CONTACT_TOTAL_COUNT FROM person";
	static final String INSERT_QUERY = "INSERT INTO PERSON( `name`, `phone_number`, `address`) VALUES(?,?,?)";
	static final String DELETE_QUERY = "DELETE FROM PERSON WHERE name=?";
	static final String UPDATE_QUERY = "UPDATE PERSON SET phone_number=?,address=? WHERE name=?";

	/**
	 * 
	 */
	public AddressBookRepositoryImpl() {
		try {
			con = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void countPerson() {
		String count = "";
		try (PreparedStatement statement = con.prepareStatement(COUNT_QUERY);) {
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				count = rs.getString("CONTACT_TOTAL_COUNT");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Total Number of Contacts in AddressBook: " + count);
	}

	public void searchPerson() {
		String inputName = readInput("Name");

		Person person = new Person();
		try (PreparedStatement statement = con.prepareStatement(SELECT_BY_NAME_QUERY);) {
			statement.setString(1, inputName);
			ResultSet rs = statement.executeQuery();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name

				int id = Integer.parseInt(rs.getString("id"));
				String name = rs.getString("name");
				String phone_number = rs.getString("phone_number");
				String address = rs.getString("address");

				person.setId(id);
				person.setName(name);
				person.setAddress(address);
				person.setPhoneNumber(phone_number);

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(person);

	}

	public void displayAddressBook() {
		List<Person> personList = new ArrayList<>();
		try (Statement stmt = con.createStatement();) {

			ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String phone_number = rs.getString("phone_number");
				String address = rs.getString("address");
				personList.add(new Person(id, name, phone_number, address));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Person p : personList)
			System.out.println(p);

	}

	public void deleteContact() {
		String name = readInput("Name");

		try (PreparedStatement statement = con.prepareStatement(DELETE_QUERY);) {
			statement.setString(1, name);
			statement.executeUpdate();

			System.out.println(name + " deleted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editContact() {
		String name = readInput("Name");
		String phone = readInput("phone");
		String address = readInput("address");

		try (PreparedStatement statement = con.prepareStatement(UPDATE_QUERY);) {

			statement.setString(1, phone);
			statement.setString(2, address);
			statement.setString(3, name);
			statement.executeUpdate();

			System.out.println(name + " updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addContact() {
		String name = readInput("Name");
		String phone = readInput("phone");
		String address = readInput("address");

		try (PreparedStatement statement = con.prepareStatement(INSERT_QUERY);) {
			statement.setString(1, name);
			statement.setString(2, phone);
			statement.setString(3, address);
			statement.executeUpdate();

			System.out.println(name + " added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	String readInput(String term) {
		System.out.print("Enter " + term + ":");
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}

}
