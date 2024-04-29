package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerDashboard {
    @FXML
    private Label welcome;
    @FXML
    private Label message;
    @FXML
    private Button seeProfile;
    @FXML
    private Button editProfile;
    @FXML
    private Button addHotel;
    @FXML
    private Button addRoom;
    @FXML
    private Button editHotel;
    @FXML
    private Button editRoom;
    @FXML
    private Button addVehicle;
    @FXML
    private Button editVehicle;
    @FXML
    private Button logout;
    String un;
    @FXML
    public void setWelcome(String s){
        un=s;
        welcome.setText("WELCOME "+s);
    }
    @FXML
    public void setMessage(String s){

        message.setText(s);
    }
    @FXML
    protected void setAddHotel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addHotel.fxml"));
        Stage stage= (Stage)addHotel.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Add Hotel");
        stage.setScene(scene);
        AddHotel addHotel1=fxmlLoader.getController();
        addHotel1.un=un;
        stage.show();
    }@FXML
    protected void setAddRoom() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addRoom.fxml"));
        Stage stage= (Stage)addRoom.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Add Room");
        stage.setScene(scene);
        AddRoom addRoom1=fxmlLoader.getController();
        addRoom1.setData(un);
        stage.show();

    }@FXML
    protected void setAddVehicle(){

    }@FXML
    protected void setEditHotel(){

    }@FXML
    protected void setEditRoom() throws IOException, SQLException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editRoom.fxml"));
        Stage stage= (Stage)editRoom.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Edit Profile");
        stage.setScene(scene);
        EditRoom editRoom1=fxmlLoader.getController();
        editRoom1.un=un;
        stage.show();

    }@FXML
    protected void setEditVehicle(){

    }@FXML
    protected void setEditProfile() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerEditProfile.fxml"));
        Stage stage= (Stage)editProfile.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Edit Profile");
        stage.setScene(scene);
        ManagerEditProfile managerEditProfile=fxmlLoader.getController();
        managerEditProfile.setData(un);
        stage.show();

    }@FXML
    protected void setLogout () throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage= (Stage)logout.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("BookPlanGo");
        stage.setScene(scene);
        stage.show();
    }@FXML
    protected void setSeeProfile() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerProfile.fxml"));
        Stage stage= (Stage)seeProfile.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Manager Profile");
        stage.setScene(scene);
        ManagerProfile managerProfile=fxmlLoader.getController();
        managerProfile.setData(un);
        stage.show();
    }

}
