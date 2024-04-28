package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelWelcomeDashboard {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Label service_label_dashboard;
    @FXML
    private Label total_rooms,available_rooms,booked_rooms;

    String s = "";


    public void setWelcome(String name)
    {
        service_label_dashboard.setText(name);
        System.out.println(name);
        s = name;
    }

    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hotel_dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Hotel_Dashboard_Controller hotel_name = fxmlLoader.getController();
        hotel_name.setWelcome(s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Hotel_Dashboard_Controller hotel_control = fxmlLoader.getController();
        hotel_control.initialize();
    }
    public void Showing_Numbers() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int totalRooms = 0;
        int totalAvailable = 0;
        int totalBooked = 0;

        try {
            Statement statement = connectDB.createStatement();

            // Get total users
            ResultSet totalRoomResult = statement.executeQuery("Select count(*) from h_roomdetails where Hotel_ID = '"+s+"'");
            if (totalRoomResult.next()) {
                totalRooms = totalRoomResult.getInt(1);
            }

            // Get total service providers
            ResultSet availableRoomResult = statement.executeQuery("Select count(*) from h_roomdetails where Hotel_ID = '"+s+"' And room_status = 'Available'");
            if (availableRoomResult.next()) {
                totalAvailable = availableRoomResult.getInt(1);
            }

            ResultSet bookedRoomResult = statement.executeQuery("Select count(*) from h_roomdetails where Hotel_ID = '"+service_label_dashboard.getText()+"' And room_status = 'Booked'");
            if (bookedRoomResult.next()) {
                totalBooked = bookedRoomResult.getInt(1);
            }

            // Update UI elements with the count (assuming you have labels like total_user and total_service_provider)
            total_rooms.setText(String.valueOf(totalRooms));
            //total_service_provider.setText(String.valueOf(totalServiceProviders));
            available_rooms.setText(String.valueOf(totalAvailable));

            booked_rooms.setText(String.valueOf(totalBooked));

            // Debugging statements
            System.out.println("Total Rooms: " + totalRooms);
            System.out.println("Available Rooms: " + totalAvailable);
            System.out.println("Booked Rooms: " + totalBooked);

        } catch (SQLException e) {
            Logger.getLogger(HotelWelcomeDashboard.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}
