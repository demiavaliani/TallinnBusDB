package controller;

import db.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class PassengersController extends DatabaseHandler  { // no need to extend // nobody is using entities but nobody asked about this
    DatabaseHandler databaseHandler = new DatabaseHandler();

    public void listAllPassengers() {
        Statement statement = databaseHandler.createStatement();
        try {
            ResultSet resultSetPassengers = statement.executeQuery("SELECT * FROM passengers");
            System.out.println("=========================================");
            System.out.println("Passengers table");

            while (resultSetPassengers.next()) {
                int myPassengerId = resultSetPassengers.getInt("id");
                String myPassengerFullName = resultSetPassengers.getString("fullName");
                String myPassengerEmail = resultSetPassengers.getString("email");
                String myPassengerPhoneNumber = resultSetPassengers.getString("phoneNumber");
                int myPassengerAge = resultSetPassengers.getInt("age");
                int myPassengerStartLocationId = resultSetPassengers.getInt("startLocationId");
                int myPassengerStopLocationId = resultSetPassengers.getInt("stopLocationId");
                int myPassengerBusID = resultSetPassengers.getInt("busId");
                Timestamp timestamp = resultSetPassengers.getTimestamp("createdOn");

                System.out.println(myPassengerId
                        + " " + myPassengerFullName
                        + " " + myPassengerEmail
                        + " " + myPassengerPhoneNumber
                        + " " + myPassengerAge
                        + " " + myPassengerStartLocationId
                        + " " + myPassengerStopLocationId
                        + " " + myPassengerBusID
                        + " " + timestamp);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void create(String name,         // There is no need to make this method regarding the exercise
                       String email,
                       String phoneNumber,
                       int age,
                       int startLocationId,
                       int stopLocationId,
                       int busId) {
        Statement statement = databaseHandler.createStatement();

        if (statement != null) {
            try {
                statement.executeUpdate("INSERT INTO passengers " +
                        "(fullName," +
                        " email," +
                        " phoneNumber," +
                        " age," +
                        " startLocationId," +
                        " stopLocationId," +
                        " busId)" +
                        " VALUES" +
                        "('" + name +
                        "', '" + email +
                        "', '" + phoneNumber +
                        "', '" + age +
                        "', '" + startLocationId +
                        "', '" + stopLocationId +
                        "', '" + busId + "')");
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    create method in all classes to find results from each table using available column name.
    (Example: using bus number; we can find a bus in mysql database by creating method
    findByBusNumber(String busNumber) that accepts a string
     which is the bus number 20A, 42B,
     etc our function will take that bus number
     and search mysql database using jdbc for the bus and show the results.)
     */
    public void findByPassengerID(int id) {
        Statement statement = databaseHandler.createStatement();
        try {
            ResultSet resultSetPassengers = statement.executeQuery("SELECT * FROM passengers WHERE id = " + id +";"); // it is better to LIMIT 1
            System.out.println("=========================================");
            System.out.println("Passenger by id # " + id + "information from 'passengers' table");

            while (resultSetPassengers.next()) {

                int myPassengerId = resultSetPassengers.getInt("id");
                String myPassengerFullName = resultSetPassengers.getString("fullName");
                String myPassengerEmail = resultSetPassengers.getString("email");
                String myPassengerPhoneNumber = resultSetPassengers.getString("phoneNumber");
                int myPassengerAge = resultSetPassengers.getInt("age");
                int myPassengerStartLocationId = resultSetPassengers.getInt("startLocationId");
                int myPassengerStopLocationId = resultSetPassengers.getInt("stopLocationId");
                int myPassengerBusID = resultSetPassengers.getInt("busId");
                Timestamp timestamp = resultSetPassengers.getTimestamp("createdOn");

                if (myPassengerId == id) { // no need to check with if statement
                    System.out.println(myPassengerId
                            + " " + myPassengerFullName
                            + " " + myPassengerEmail
                            + " " + myPassengerPhoneNumber
                            + " " + myPassengerAge
                            + " " + myPassengerStartLocationId
                            + " " + myPassengerStopLocationId
                            + " " + myPassengerBusID
                            + " " + timestamp);
                }
            }
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
