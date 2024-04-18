package org.example.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcome;
    @FXML
    private Label hotelName;
    @FXML
    private Button add;
    @FXML
    private Button modify;
    @FXML
    private Button back;
    @FXML
    protected void setAdd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addRoom.fxml"));
        Stage stage= (Stage)add.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 904, 600);
        stage.setTitle("Add room");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void setModify() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifyRoom.fxml"));
        Stage stage= (Stage)modify.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 904, 600);
        stage.setTitle("Add room");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void setBack(){

    }

}