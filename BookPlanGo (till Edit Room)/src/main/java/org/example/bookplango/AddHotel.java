package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddHotel implements Initializable {
    @FXML
    private Label message;
    @FXML
    private TextField hotelName;
    @FXML
    private TextField floorNum;
    @FXML
    private ComboBox <String> location;
    @FXML
    private Button back;
    @FXML
    private Button addHotel;
    String un,hn="",nf="",l;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> locations=FXCollections.observableArrayList("Dhaka",
                "Chattogram",
                "Sylhet",
                "Rajshahi",
                "Barishal",
                "Rangpur",
                "Khulna",
                "Sylhet");

        location.setItems(locations);
    }
   /* @FXML
    public void setData(String s){
        un=s;
        /*ObservableList<String> locations=FXCollections.observableArrayList("Dhaka",
                "Chattogram",
                "Sylhet",
                "Rajshahi",
                "Barishal",
                "Rangpur",
                "Khulna",
                "Sylhet");

        location.setItems(locations);
    }*/
    @FXML
    protected void setAddHotel() throws SQLException, IOException {
        hn=hotelName.getText();
        nf=floorNum.getText();
        l=location.getValue();
        if(l==null||hn==null||nf==null){
            message.setText("Fill Up all information");
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from hotels where Name='"+hn+"' and Manager='"+un+"' and Location='"+l+"';");
            if(resultSet.next()){
                message.setText("Hotel Exists");
            }else {
                statement.executeUpdate("INSERT INTO hotels (`Name`, `Location`, `Manager`, `FloorNum`) VALUES ('" + hn + "', '" + l + "', '" + un + "', '" + nf + "')");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
                Stage stage = (Stage) addHotel.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 900, 700);
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                ManagerDashboard managerDashboard = fxmlLoader.getController();
                managerDashboard.setWelcome(un);
                managerDashboard.setMessage("Hotel Added");
                stage.show();
            }
        }
    }@FXML
    protected void setBack() throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
            Stage stage= (Stage)back.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 900,700);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            ManagerDashboard managerDashboard=fxmlLoader.getController();
            managerDashboard.setWelcome(un);
            stage.show();

    }


}
