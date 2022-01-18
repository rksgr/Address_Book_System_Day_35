package service;

import model.AddressBook;
import model.Contact;
import java.util.Scanner;

public class AddressBookService {

    /*
    Use Case 1: Create a Contact in address book containing contact details
     */
    public AddressBook createContact(AddressBook addressBook, Contact contact){
        // Adds the contact to the ArrayList
        addressBook.getAddressBookList().add(contact);
        return addressBook;
    }
    /*
    Use Case 2: Add a new contact to address book using console
     */
    public AddressBook addContactUsingConsole(AddressBook addressBook) {
        // Adds the contact to the ArrayList
        System.out.println("Enter the first name: ");
        Scanner sc1 = new Scanner(System.in);
        String first_name = sc1.next();

        System.out.println("Enter the last name: ");
        Scanner sc2 = new Scanner(System.in);
        String last_name = sc2.next();

        System.out.println("Enter the address: ");
        Scanner sc3 = new Scanner(System.in);
        String address = sc3.next();

        System.out.println("Enter the name of city: ");
        Scanner sc4 = new Scanner(System.in);
        String city = sc4.next();

        System.out.println("Enter the name of state: ");
        Scanner sc5 = new Scanner(System.in);
        String state = sc5.next();

        System.out.println("Enter the zip: ");
        Scanner sc6 = new Scanner(System.in);
        int zip = sc6.nextInt();

        System.out.println("Enter the phone number: ");
        Scanner sc7 = new Scanner(System.in);
        double phone_num = sc7.nextDouble();

        System.out.println("Enter the email: ");
        Scanner sc8 = new Scanner(System.in);
        String email = sc8.next();

        // Pass all the details to create an object of contact class
        Contact contact = new Contact(first_name, last_name, address, city, state, zip, phone_num, email);

        // Add the contact to the addressbook list
        addressBook.getAddressBookList().add(contact);
        return addressBook;
    }
}
