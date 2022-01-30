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
        String password = "priya@1987";
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
}
