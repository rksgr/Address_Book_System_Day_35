package model;

import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> addressBookList = new ArrayList<>();

    public ArrayList<Contact> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(ArrayList<Contact> addressBookList) {
        this.addressBookList = addressBookList;
    }
}
