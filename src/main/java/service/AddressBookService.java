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

    /*
    Use Case 3: Edit existing contact person using their name
     */
    public AddressBook editExistingContact(AddressBook addressBook, String person_first_name,String person_last_name,
                                           Contact contact){
        int index = searchExistingContact(addressBook, person_first_name,person_last_name);
        addressBook.getAddressBookList().set(index,contact);
        return addressBook;
    }

    // Search for a given person in the address book
    public int searchExistingContact(AddressBook addressBook, String person_first_name,String person_last_name){
        int index = -1, size = addressBook.getAddressBookList().size();
        for (int i=0;i<size;i++){

            // Get first name and last name of various contacts
            String first_name = addressBook.getAddressBookList().get(i).getFirst_name();
            String last_name = addressBook.getAddressBookList().get(i).getLast_name();

            // If name matches get the index of the contact
            if ((person_first_name.equals(first_name)) && (person_last_name.equals(last_name))){
                index = i;
                break;
            }
        }
        return index;
    }

    /*
    Use Case 4: Delete a person using person's name
     */
    public AddressBook deleteExistingContact(AddressBook addressBook, String person_first_name,String person_last_name){
        // Fetch the index of the contact to be deleted
        int index = searchExistingContact(addressBook, person_first_name,person_last_name);
        addressBook.getAddressBookList().remove(index);
        return addressBook;
    }

    /*
    Use Case 5: Add multiple persons to address book
     */
    public AddressBook addMultiplePerson(AddressBook addressBook, int no_of_persons){
        // The loop runs for as many times as the number of persons
        for(int i=0;i<no_of_persons;i++){
            addContactUsingConsole(addressBook);
        }
        return addressBook;
    }
}
