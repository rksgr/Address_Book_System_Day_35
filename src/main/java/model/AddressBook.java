package model;

import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> addressBookList = new ArrayList<>();
    private String addr_book_name;

    public AddressBook(){}

    // Single parameter constructor
    public AddressBook(String addr_book_name){
        this.addr_book_name = addr_book_name;
    }


    public String getAddr_book_name() {
        return addr_book_name;
    }

    public void setAddr_book_name(String addr_book_name) {
        this.addr_book_name = addr_book_name;
    }

    public ArrayList<Contact> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(ArrayList<Contact> addressBookList) {
        this.addressBookList = addressBookList;
    }
}
