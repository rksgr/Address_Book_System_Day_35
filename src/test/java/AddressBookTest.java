import model.AddressBook;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.AddressBookService;

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
}
