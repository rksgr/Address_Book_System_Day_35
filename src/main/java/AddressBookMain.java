import model.AddressBook;
import service.AddressBookService;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Today we shall make Address Book System!");
        AddressBookService addressBookService = new AddressBookService();

        AddressBook addressBook = new AddressBook();

        // Get the size before and after addition of contact
        int size1 = addressBook.getAddressBookList().size();


        int size2 = addressBookService.addMultiplePerson(addressBook,3).getAddressBookList().size();
        System.out.println(" size before addition = "+size1);
        System.out.println(" size after addition = "+size2);
    }
}
