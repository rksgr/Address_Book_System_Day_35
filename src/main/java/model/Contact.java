package model;

public class Contact implements Comparable<Contact>{
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private double phone_num;
    private String email;

    @Override
    public int compareTo(Contact contact){
        return this.getFirst_name().compareToIgnoreCase(contact.getFirst_name());
    }
    @Override
    public String toString(){
        String format = String.format("(%s,%s,%s,%s,%s,%d,%f,%s)", first_name, last_name, address, city, state, zip, phone_num, email);
        return format;
    }

    public Contact(String first_name, String last_name, String address, String city, String state, int zip,
                   double phone_num, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_num = phone_num;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public double getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(double phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if(!(obj instanceof Contact)){
            return false;
        }
        Contact c = (Contact) obj;

        return CharSequence.compare(first_name,c.getFirst_name())==0
                && CharSequence.compare(last_name,c.getLast_name())==0
                && CharSequence.compare(city,c.getCity())==0
                && CharSequence.compare(state,c.getState())==0
                && CharSequence.compare(address,c.getAddress())==0
                && CharSequence.compare(email,c.getEmail())==0
                && Integer.compare(zip,c.getZip())==0
                && Double.compare(phone_num,c.getPhone_num())==0;
    }
}
