import model.AddressBook;
import model.AddressBookSystem;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.AddressBookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
public class AddressBookTest {

    /*
    Use Case 1: Create a contacts in address book
     */
    @Test
    public void whenContactCreated_sizeOfAddressBook_IncreasesByOne(){
        Contact contact = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",111,89898989,"arun.kumar@gmail.com");
        AddressBook addressBook = new AddressBook();
        AddressBookService addressBookService = new AddressBookService();

        // size of list before contact creation
        int size1 =  addressBook.getAddressBookList().size();

        // size of list after contact creation
        int size2 = addressBookService.createContact(addressBook,contact).getAddressBookList().size();
        Assertions.assertEquals(size2,size1+1);
    }
    /*
    Use Case 3: Edit existing contact in address book using name of person
     */
    @Test
    public void whenContactEdited_addressBookEntry_IsEdited(){
        Contact contact = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",111,89898989,"arun.kumar@gmail.com");
        Contact contact_edited = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",5555,77777777,"arun.kumar1234@gmail.com");
        AddressBook addressBook = new AddressBook();
        AddressBookService addressBookService = new AddressBookService();

        // Add a contact to the address book
        addressBookService.createContact(addressBook,contact);

        // Edit contact
        addressBookService.editExistingContact(addressBook,"Arun","kumar",contact_edited);

        // fetch zip of the edited contact
        int zip_edited = addressBook.getAddressBookList().get(0).getZip();

        Assertions.assertEquals(zip_edited,5555);
    }
    /*
    Use Case 4: Delete a person in address book using name of person
     */
    @Test
    public void whenContactDeleted_addressBookSize_ReducesByOne(){
        Contact contact1 = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",111,89898989,"arun.kumar@gmail.com");
        Contact contact2 = new Contact("Manish","kumar","Mackenzie Street","Kent",
                "England",5555,77777777,"manish.kumar1234@gmail.com");
        AddressBook addressBook = new AddressBook();
        AddressBookService addressBookService = new AddressBookService();

        // Add contacts to the address book
        addressBookService.createContact(addressBook,contact1);
        addressBookService.createContact(addressBook,contact2);

        // compare size before and after deletion
        int size_bef_del = addressBook.getAddressBookList().size();

        // Delete the contact
        addressBookService.deleteExistingContact(addressBook,"Arun","kumar");

        int size_aft_del = addressBook.getAddressBookList().size();

        Assertions.assertEquals(size_aft_del,size_bef_del-1);
    }
    /*
    Use Case 7:Prevent duplicate entry of the same person in a particular address book
     */
    @Test
    public void whenAContactAlreadyPresentInAddressBook_ifAdded_itShouldNotGetAdded(){
        Contact contact1 = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",111,89898989,"arun.kumar@gmail.com");
        AddressBook addressBook = new AddressBook();
        AddressBookService addressBookService = new AddressBookService();

        addressBookService.createContact(addressBook,contact1);
        int size_bef_contct_add = addressBook.getAddressBookList().size();

        //Add new contact
        Contact contact2 = new Contact("Manish","kumar","Mackenzie Street","Kent",
                "England",5555,77777777,"manish.kumar1234@gmail.com");
        addressBookService.preventDuplicateEntry(addressBook,contact2);
        int size_aft_contct_add = addressBook.getAddressBookList().size();

        // Add duplicate contact
        Contact contact3 = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",111,89898989,"arun.kumar@gmail.com");
        addressBookService.preventDuplicateEntry(addressBook,contact3);
        int size_aft_duplicate_contct_add = addressBook.getAddressBookList().size();

        // size of address book should increase by one after addition of contact
        Assertions.assertEquals(size_bef_contct_add + 1,size_aft_contct_add);

        // size of address book should not increase after addition of duplicate contact
        Assertions.assertEquals(size_aft_contct_add,size_aft_duplicate_contct_add);
    }
    /*
    Use Case 8: Search person in a city or state across multiple address book
     */
    @Test
    public void whenPerson_searchedInACity_AcrossMultipleAddressBooks_shouldReturnListOfPersonNames(){
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        Contact contact1 = new Contact("Arun","kumar","Leadenhall Street","Gangtok",
                "Sikkim",1011,808989,"arun.kumaras@gmail.com");
        AddressBook addressBook = new AddressBook("book");
        // Add the contact to the address book
        addressBook.getAddressBookList().add(contact1);
        // Add the address book to the address book system
        addressBookSystem.getAddressBookMap().put(addressBook.getAddr_book_name(),addressBook);

        Contact contact2 = new Contact("Varun","kumar","Leader Street","Allahabad",
                "Uttar Pradesh",1151,89898989,"varun.kumar@gmail.com");
        AddressBook addressBook2 = new AddressBook("book2");
        addressBook2.getAddressBookList().add(contact2);
        addressBookSystem.getAddressBookMap().put(addressBook2.getAddr_book_name(),addressBook2);

        Contact contact3 = new Contact("Arunesh","kumar","Ironwall Street","Sagar",
                "Madhya Pradesh",111,89898989,"arun.kumar@gmail.com");
        AddressBook addressBook3 = new AddressBook("book3");
        addressBook3.getAddressBookList().add(contact3);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);

        Contact contact4 = new Contact("Arun","kumar","Prince Street","Gangtok",
                "Sikkim",211,89898979,"arun.kumar@gmail.com");
        addressBook3.getAddressBookList().add(contact4);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);


        Contact contact5 = new Contact("Arun","kumar","Wales Street","Gangtok",
                "Sikkim",2151,80000979,"arun.kumar486@gmail.com");
        AddressBook addressBook4 = new AddressBook("book4");
        addressBook4.getAddressBookList().add(contact5);
        addressBookSystem.getAddressBookMap().put(addressBook4.getAddr_book_name(),addressBook4);

        AddressBookService addressBookService = new AddressBookService();
        ArrayList<Contact> contactArrayList = addressBookService.searchPersonAcrossCityOrState(addressBookSystem,
                "Arun","kumar","Gangtok","Sikkim");

        // Get the size of arraylist containing contacts having same first and last name and living in same city and state
        int size = contactArrayList.size();
        Assertions.assertEquals(3,size);
    }
    /*
    Use Case 9: View persons by city or state
     */
    @Test
    public void givenCityOrStateName_viewAllPersonsInTheCityOrState(){
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        Contact contact1 = new Contact("Arun","kumar","Leadenhall Street","Gangtok",
                "Sikkim",1011,808989,"arun.kumaras@gmail.com");
        AddressBook addressBook = new AddressBook("book");
        // Add the contact to the address book
        addressBook.getAddressBookList().add(contact1);
        // Add the address book to the address book system
        addressBookSystem.getAddressBookMap().put(addressBook.getAddr_book_name(),addressBook);

        Contact contact2 = new Contact("Varun","kumar","Leader Street","Allahabad",
                "Uttar Pradesh",1151,89898989,"varun.kumar@gmail.com");
        AddressBook addressBook2 = new AddressBook("book2");
        addressBook2.getAddressBookList().add(contact2);
        addressBookSystem.getAddressBookMap().put(addressBook2.getAddr_book_name(),addressBook2);

        Contact contact3 = new Contact("Arunesh","kumar","Ironwall Street","Sagar",
                "Madhya Pradesh",111,89898989,"arun.kumar@gmail.com");
        AddressBook addressBook3 = new AddressBook("book3");
        addressBook3.getAddressBookList().add(contact3);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);

        Contact contact4 = new Contact("Arun","kumar","Prince Street","Gangtok",
                "Sikkim",211,89898979,"arun.kumar@gmail.com");
        addressBook3.getAddressBookList().add(contact4);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);


        Contact contact5 = new Contact("Arun","kumar","Wales Street","Gangtok",
                "Sikkim",2151,80000979,"arun.kumar486@gmail.com");
        AddressBook addressBook4 = new AddressBook("book4");
        addressBook4.getAddressBookList().add(contact5);
        addressBookSystem.getAddressBookMap().put(addressBook4.getAddr_book_name(),addressBook4);

        AddressBookService addressBookService = new AddressBookService();
        ArrayList<Contact> contactArrayList1 = addressBookService.viewPersonsByCity(addressBookSystem,"Gangtok");
        ArrayList<Contact> contactArrayList2 = addressBookService.viewPersonsByState(addressBookSystem,"Madhya Pradesh");

        // Get the size of arraylist containing contacts having same first and last name and living in same city and state
        int size1 = contactArrayList1.size();
        int size2 = contactArrayList2.size();
        Assertions.assertEquals(3,size1);
        Assertions.assertEquals(1,size2);
    }

    /*
    Use Case 10: Get number of contact persons by city or state
     */
    @Test
    public void givenCityOrStateChoice_getCountOfAllPersonsInTheCityOrState(){
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        Contact contact1 = new Contact("Arun","kumar","Leadenhall Street","London",
                "England",1011,808989,"arun.kumaras@gmail.com");
        AddressBook addressBook = new AddressBook("book");
        // Add the contact to the address book
        addressBook.getAddressBookList().add(contact1);
        // Add the address book to the address book system
        addressBookSystem.getAddressBookMap().put(addressBook.getAddr_book_name(),addressBook);

        Contact contact2 = new Contact("Varun","kumar","Leader Street","Allahabad",
                "Uttar Pradesh",1151,89898989,"varun.kumar@gmail.com");
        AddressBook addressBook2 = new AddressBook("book2");
        addressBook2.getAddressBookList().add(contact2);
        addressBookSystem.getAddressBookMap().put(addressBook2.getAddr_book_name(),addressBook2);

        Contact contact3 = new Contact("Arunesh","kumar","Ironwall Street","Sagar",
                "Madhya Pradesh",111,89898989,"arun.kumar@gmail.com");
        AddressBook addressBook3 = new AddressBook("book3");
        addressBook3.getAddressBookList().add(contact3);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);

        Contact contact4 = new Contact("Valmiki","kumar","Prince Street","Gangtok",
                "Sikkim",211,89898979,"valmiki.kumar@gmail.com");
        addressBook3.getAddressBookList().add(contact4);
        addressBookSystem.getAddressBookMap().put(addressBook3.getAddr_book_name(),addressBook3);


        Contact contact5 = new Contact("Aamir","khan","Wales Street","Nagpur",
                "Maharashtra",2151,80000979,"aamir.kumar486@gmail.com");
        AddressBook addressBook4 = new AddressBook("book4");
        addressBook4.getAddressBookList().add(contact5);
        addressBookSystem.getAddressBookMap().put(addressBook4.getAddr_book_name(),addressBook4);

        AddressBookService addressBookService = new AddressBookService();

        // To get person count by city set countByCity to true and to get count by state set countByCity to false,
        Map<String,Integer> personCountByCity = addressBookService.getPersonCountByCityOrState(addressBookSystem,true);
        Map<String,Integer> personCountByState = addressBookService.getPersonCountByCityOrState(addressBookSystem,false);

        // Get the count of persons living in London city and Uttar Pradesh state
        int size1 = personCountByCity.get("London");
        int size2 = personCountByState.get("Uttar Pradesh");

        Assertions.assertEquals(1,size1);
        Assertions.assertEquals(1,size2);
    }
    /*
    Use Case 11: Sort address book entries alphabetically
     */
    @Test
    public void givenAddressBook_WhenSortedAlphabeticallyAsPerFirstName_ShouldReturnFirstAlphabeticContactAsPerFirstNameAsFirstContact(){
        Contact contact1 = new Contact("Altaf", "kumar", "Leadenhall Street", "London",
                "England", 1011, 808989, "altaf.kumaras@gmail.com");
        AddressBook addressBook = new AddressBook("book");

        Contact contact2 = new Contact("Varun", "kumar", "Leader Street", "Allahabad",
                "Uttar Pradesh", 1151, 89898009, "varun.kumar@gmail.com");

        Contact contact3 = new Contact("Arunesh", "kumar", "Ironwall Street", "Sagar",
                "Madhya Pradesh", 111, 877778989, "arun.kumar@gmail.com");

        Contact contact4 = new Contact("Valmiki", "kumar", "Prince Street", "Gangtok",
                "Sikkim", 211, 892222979, "valmiki.kumar@gmail.com");

        Contact contact5 = new Contact("Krishna", "kumar", "Alexander Street", "Jodhpur",
                "Rajasthan", 211, 89638979, "krishna.kumar@gmail.com");
        addressBook.getAddressBookList().add(contact1);
        addressBook.getAddressBookList().add(contact2);
        addressBook.getAddressBookList().add(contact3);
        addressBook.getAddressBookList().add(contact4);
        addressBook.getAddressBookList().add(contact5);

        AddressBookService addressBookService = new AddressBookService();

        ArrayList<Contact> contactArrayList  = addressBookService.sortAddressBookByPersonName(addressBook);

        String third_name_in_list = contactArrayList.get(2).getFirst_name();
        Assertions.assertEquals("Krishna",third_name_in_list);
    }
}
