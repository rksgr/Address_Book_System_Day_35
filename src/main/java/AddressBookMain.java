import model.AddressBook;
import service.AddressBookService;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Today we shall make Address Book System!");
        AddressBookService addressBookService = new AddressBookService();
        AddressBook addressBook = new AddressBook();
        int size1 = addressBook.getAddressBookList().size();

        addressBookService.addContactUsingConsole(addressBook);
        int size2 = addressBook.getAddressBookList().size();
        System.out.println(" size before addition = "+size1);
        System.out.println(" size after addition = "+size2);
    }
}
