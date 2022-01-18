import model.AddressBook;
import model.AddressBookSystem;
import service.AddressBookService;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Today we shall make Address Book System!");
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        AddressBookService addressBookService = new AddressBookService();

        AddressBook addressBook = new AddressBook();

        // Get the size of addressBookSystem before and after addition of address books
        int size1 = addressBookSystem.getAddressBookMap().size();

        addressBookService.addMultipleAddressBook(addressBookSystem,1);
        //int size2 = addressBookService.addMultipleAddressBook(addressBookSystem,3)
          //          .getAddressBookMap().size();
        int size2 = addressBookSystem.getAddressBookMap().size();
        System.out.println(" size before addition = "+size1);
        System.out.println(" size after addition = "+size2);
    }
}
