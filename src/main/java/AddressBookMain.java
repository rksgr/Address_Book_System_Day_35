import com.opencsv.CSVWriter;
import model.AddressBook;
import model.AddressBookSystem;
import model.Contact;
import service.AddressBookService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class AddressBookMain {

    public static void main(String[] args) {
        System.out.println("Today we shall make Address Book System!");
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        //AddressBookService addressBookService = new AddressBookService();

        //AddressBook addressBook = new AddressBook();

        // Get the size of addressBookSystem before and after addition of address books
        //int size1 = addressBookSystem.getAddressBookMap().size();

        //addressBookService.addMultipleAddressBook(addressBookSystem,1);
        //int size2 = addressBookService.addMultipleAddressBook(addressBookSystem,3)
        //          .getAddressBookMap().size();
        //int size2 = addressBookSystem.getAddressBookMap().size();
        //System.out.println(" size before addition = "+size1);
        //System.out.println(" size after addition = "+size2);
        Contact contact1 = new Contact("Altaf", "kumar", "Leadenhall Street", "London",
                "England", 103711, 808989, "altaf.kumaras@gmail.com");
        AddressBook addressBook = new AddressBook("book");

        Contact contact2 = new Contact("Varun", "kumar", "Leader Street", "Allahabad",
                "Uttar Pradesh", 115751, 89898009, "varun.kumar@gmail.com");

        Contact contact3 = new Contact("Arunesh", "kumar", "Ironwall Street", "Sagar",
                "Madhya Pradesh", 110921, 877778989, "arun.kumar@gmail.com");

        Contact contact4 = new Contact("Valmiki", "kumar", "Prince Street", "Gangtok",
                "Sikkim", 215251, 892222979, "valmiki.kumar@gmail.com");

        Contact contact5 = new Contact("Krishna", "kumar", "Alexander Street", "Jodhpur",
                "Rajasthan", 232541, 89638979, "krishna.kumar@gmail.com");
        addressBook.getAddressBookList().add(contact1);
        addressBook.getAddressBookList().add(contact2);
        addressBook.getAddressBookList().add(contact3);
        addressBook.getAddressBookList().add(contact4);
        addressBook.getAddressBookList().add(contact5);

        AddressBookService addressBookService = new AddressBookService();

        // Write address book entries into file
        File file = new File("C:\\Users\\Renu\\Desktop\\DAddressBookFile.txt");
        addressBookService.writeAddressBookIntoCSVFile(addressBook,file);
    }
}

