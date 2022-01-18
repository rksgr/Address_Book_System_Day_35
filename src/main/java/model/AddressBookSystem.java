package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class AddressBookSystem {
    private Map<String, AddressBook> addressBookMap = new HashMap<>();

    public Map<String, AddressBook> getAddressBookMap() {
        return addressBookMap;
    }

    public void setAddressBookMap(Map<String, AddressBook> addressBookMap) {
        this.addressBookMap = addressBookMap;
    }
}
