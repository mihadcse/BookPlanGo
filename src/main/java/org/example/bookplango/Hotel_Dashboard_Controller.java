package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hotel_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    public Label hotel_label_dashboard;

    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void setWelcome(String name)
    {
        hotel_label_dashboard.setText(name);
        System.out.println(name);
    }

    @FXML
    private TableView<Hotel_Dashboard> hotelDashboardTableView;
    @FXML
    private TableColumn<Hotel_Dashboard,Integer> hotelroomPriceTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomTypeTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomACTableColumn;
    @FXML
    private TableColumn<Hotel_Dashboard,String>hotelroomStatusTableColumn;

    ObservableList<Hotel_Dashboard> hotel_dashboardObservableList = FXCollections.observableArrayList();

    public void initialize () {
        hotel_dashboardObservableList.clear();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //String hotelTableViewquery = "Select room_type,room_ac,room_status,room_price from h_roomdetails where Hotel_ID = '1001'";

        String hotelTableViewquery = "Select room_type,room_ac,room_status,room_price from h_roomdetails where Hotel_ID ='" + hotel_label_dashboard.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery (hotelTableViewquery);
            while (queryOutput.next()) {
                String querytype = queryOutput.getString( "room_type");
                String queryAC = queryOutput.getString("room_ac");
                String queryStatus = queryOutput.getString("room_status");
                Integer queryPrice = queryOutput.getInt("room_price");
                hotel_dashboardObservableList.add(new Hotel_Dashboard (queryPrice,querytype,queryAC,queryStatus));
            }

            hotelroomTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Room_type"));
            hotelroomACTableColumn.setCellValueFactory (new PropertyValueFactory<>("ac_non_ac"));
            hotelroomStatusTableColumn.setCellValueFactory (new PropertyValueFactory<>("Status"));
            hotelroomPriceTableColumn.setCellValueFactory (new PropertyValueFactory<>("Price"));

            hotelDashboardTableView.setItems (hotel_dashboardObservableList);

        }
        catch(SQLException e) {
            Logger.getLogger (Hotel_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

}
