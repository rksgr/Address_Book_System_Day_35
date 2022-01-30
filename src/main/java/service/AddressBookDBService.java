package service;

import model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDBService {

    // Establish connection with the database
    public Connection getConnection() throws SQLException{
        String jdbcURL ="jdbc:mysql://localhost:3306/address_book_service?useSSL=true";
        String userName = "root";
        String password = "1234";
        Connection connection;
        System.out.println("Connecting to database");
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection is successful.");
        return connection;
    }

    public List<Contact>readData(){
        String sql = "SELECT * FROM person_address";
        List<Contact> contactList = new ArrayList<>();

        // Connection automatically closes after the try block is executed
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = getContactData(resultSet);
        }catch(SQLException e){e.printStackTrace();}
        return contactList;
    }

    private List<Contact> getContactData(ResultSet resultSet) {
        List<Contact> contactList = new ArrayList<>();
        try{
            while(resultSet.next()){
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                double phone_num = resultSet.getDouble("phone_num");
                String email_id = resultSet.getString("email_id");

                // Add the contact fetched to the array list
                contactList.add(new Contact(first_name,last_name,address,city,state,zip,phone_num,email_id));
            }
        }catch(SQLException e){e.printStackTrace();}
        return contactList;
    }

    public static void main(String[] args) {
        Contact contact_old_det = new Contact("sushila","kumari","Ambedkar Nagar","chhindwara"
                ,"Madhya Pradesh",456238,0.0,null);

        Contact contact_new_det = new Contact("sushila","kumari","Ambedkar Nagar","chhindwara"
                ,"Madhya Pradesh",456238,0.0,"sushila.kumari@gmail.com");
        String sql1 = "UPDATE person_address SET email_id= "+ String.format(contact_new_det.getEmail())
                        +"WHERE first_name="+String.format(contact_old_det.getFirst_name());
        String sql = new StringBuilder().append("UPDATE person_address SET email_id=\" ").append(contact_new_det.getEmail())
                .append("\" WHERE first_name=\"").append(contact_old_det.getFirst_name()).append("\"").toString();
        System.out.println("sql with string builder = "+ sql1);
        System.out.println("sql without builder" +sql);
    }
    public int updateData(Contact contact_old_det, Contact contact_new_det) {
        // update email_id of the given contact
        String sql = new StringBuilder().append("UPDATE person_address SET email_id= \"").append(contact_new_det.getEmail())
                .append("\" WHERE first_name=\"").append(contact_old_det.getFirst_name()).append("\"").toString();
        int rows = 0;
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            //System.out.println("resulteset+ "+ resultSet);
            //rows = Integer.parseInt(String.valueOf(resultSet));
        }catch(SQLException e){e.printStackTrace();}
        return rows;
    }

    // Overloaded method, used to read rows of table where first name and last name match
    public List<Contact> readData(String first_name, String last_name) {

        // Read contact details based on the first name and last name
        String sql = "SELECT * FROM person_address WHERE first_name=\""+ first_name +"\" AND last_name=\"" + last_name +"\"";
        List<Contact> contactList = new ArrayList<>();

        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = getContactData(resultSet);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return contactList;
    }
}



