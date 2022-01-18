package service;

import model.AddressBook;
import model.Contact;

public class AddressBookService {

    /*
    Use Case 1: Create a Contact in address book containing contact details
     */
    public AddressBook createContact(AddressBook addressBook, Contact contact){
        // Adds the contact to the ArrayList
        addressBook.getAddressBookList().add(contact);
        return addressBook;
    }
}
