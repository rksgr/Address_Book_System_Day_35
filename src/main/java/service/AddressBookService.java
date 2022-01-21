package service;

import model.AddressBook;
import model.AddressBookSystem;
import model.Contact;

import java.util.*;

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

    /*
    Use Case 6: Add multiple address book to system
     */
    public AddressBookSystem addMultipleAddressBook(AddressBookSystem addressBookSystem, int no_of_addrbooks){

        // The loop runs for as many times as the number of address books
        for(int i=0;i<no_of_addrbooks;i++) {
            AddressBook addressBook = new AddressBook();
            System.out.println("Enter the name of the given address book.");
            Scanner sc1 = new Scanner(System.in);
            String addr_book_name = sc1.next();

            System.out.println("Enter the number of persons to be added to the given address book.");
            Scanner sc2 = new Scanner(System.in);
            int no_of_persons = sc2.nextInt();

            //Add each address book to the dictionary
            addressBookSystem.getAddressBookMap().put(addr_book_name, addMultiplePerson(addressBook,no_of_persons));
        }
        return addressBookSystem;
    }
    /*
    Use Case 7: Prevent duplicate entry of the same person in a particular address book
     */
    public AddressBook preventDuplicateEntry(AddressBook addressBook,Contact contact){
        boolean is_duplicate_entry = checkDuplicateEntry(addressBook,contact);
        if (!is_duplicate_entry){
            createContact(addressBook,contact);
        }
        return addressBook;
    }
    // check if the contact already exists in address book
    public boolean checkDuplicateEntry(AddressBook addressBook,Contact contact){
        boolean isDuplicateContact = true;
        int count = 0;
        // search for the person in the address book
        int no_of_contacts = addressBook.getAddressBookList().size();
        for (int i=0;i<no_of_contacts;i++){
            if (addressBook.getAddressBookList().get(i).equals(contact)){
                break;
            }
            count++;
        }
        if (no_of_contacts == count){
            isDuplicateContact = false;
        }
        return isDuplicateContact;
    }
    /*
    Use Case 8: Search person by city or state across multiple address books
     */
    public ArrayList<Contact> searchPersonAcrossCityOrState(AddressBookSystem addressBookSystem,String person_first_name,
                                                                  String person_last_name,String city_ser,String state_ser){
        ArrayList<Contact> contactArrayList = new ArrayList<>();

        Collection<AddressBook> addressBookCollection = addressBookSystem.getAddressBookMap().values();
        addressBookCollection.forEach(addressBook -> {
            Collection<Contact> contactCollection = addressBook.getAddressBookList();
            contactCollection.forEach(contact -> {
                if(contact.getState().equals(state_ser) && contact.getCity().equals(city_ser)) {
                    if (contact.getFirst_name().equals(person_first_name) && contact.getLast_name().equals(person_last_name)) {
                        contactArrayList.add(contact);
                    }
                }
            });
        });
        return contactArrayList;
    }
    /*
    Use Case 9: View persons by city or state
     */
    // To view persons by city
    public ArrayList<Contact> viewPersonsByCity(AddressBookSystem addressBookSystem,String city_ser){
        Map<String, ArrayList<Contact>> stringArrayListMap = maintainDictPersonsByCityOrState(addressBookSystem,true);
        return stringArrayListMap.get(city_ser);
    }
    // To view persons by state
    public ArrayList<Contact> viewPersonsByState(AddressBookSystem addressBookSystem,String state_ser){
        Map<String, ArrayList<Contact>> stringArrayListMap = maintainDictPersonsByCityOrState(addressBookSystem,false);
        return stringArrayListMap.get(state_ser);
    }

    // Maintain Dictionary of persons by city or state
    public Map<String,ArrayList<Contact>> maintainDictPersonsByCityOrState(AddressBookSystem addressBookSystem, boolean record_by_city){
        Map<String, ArrayList<Contact>> stringArrayListMap = new HashMap<>();
        Collection<AddressBook> addressBookCollection = addressBookSystem.getAddressBookMap().values();

        addressBookCollection.forEach(addressBook -> {              // for each address book
            Collection<Contact> contactCollection = addressBook.getAddressBookList();

            contactCollection.forEach(contact->{        // for each contact
                String city_or_state = "";

                // If city set to true, search by city else search by state
                if (record_by_city){
                    city_or_state = contact.getCity();
                }else if (!record_by_city){
                    city_or_state = contact.getState();
                }

                // If given city or state already present in dictionary
                if (stringArrayListMap.containsKey(city_or_state)){
                    ArrayList<Contact> contactArrayList1 = stringArrayListMap.get(city_or_state);
                    contactArrayList1.add(contact);
                    stringArrayListMap.replace(city_or_state,contactArrayList1);
                }

                // if given city or state not present in dictionary
                else if(!stringArrayListMap.containsKey(city_or_state)){
                    ArrayList<Contact> contactArrayList2 = new ArrayList<>();
                    contactArrayList2.add(contact);
                    stringArrayListMap.put(city_or_state,contactArrayList2);
                }
            });
        });
        return stringArrayListMap;
    }
    /*
    Use Case 10: Get number of contact persons by city or state
     */
    // Returns Dictionary of count of persons by city or state
    public Map<String,Integer> getPersonCountByCityOrState(AddressBookSystem addressBookSystem, boolean countByCity){
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        Collection<AddressBook> addressBookCollection = addressBookSystem.getAddressBookMap().values();

        addressBookCollection.forEach(addressBook -> {              // for each address book
            Collection<Contact> contactCollection = addressBook.getAddressBookList();
            contactCollection.forEach(contact->{        // for each contact
                String city_or_state = "";

                // If city set to true, search by city else search by state
                if (countByCity)    {city_or_state = contact.getCity();
                }else if (!countByCity)     {city_or_state = contact.getState();}

                // If given city or state already present in dictionary
                if (stringIntegerMap.containsKey(city_or_state)){
                    int size1 = stringIntegerMap.get(city_or_state);
                    stringIntegerMap.replace(city_or_state,size1+1);
                }

                // if given city or state not present in dictionary
                else if(!stringIntegerMap.containsKey(city_or_state)){
                    stringIntegerMap.put(city_or_state,1);
                }
            });
        });
        return stringIntegerMap;
    }
    /*
    Use Case 11: Sort entries in address book alphabetically by person's name
     */
    public ArrayList<Contact> sortAddressBookByPersonName(AddressBook addressBook){
        ArrayList<Contact> contactArrayList = addressBook.getAddressBookList();
        Collections.sort(contactArrayList);
        contactArrayList.forEach(contact -> {
            System.out.println(contact.toString());
        });
        return contactArrayList;
    }
}
