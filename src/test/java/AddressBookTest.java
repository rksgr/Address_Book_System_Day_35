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
}
