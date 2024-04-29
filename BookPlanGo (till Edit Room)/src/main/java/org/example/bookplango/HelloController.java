package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    @FXML
    private Button traveller;
    @FXML
    private Button manager;
    @FXML
    private Label message;

    @FXML
    public void setMessage(String s){
        message.setText(s);
    }
    @FXML
    protected void setManager() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Stage stage= (Stage)manager.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Login");
        stage.setScene(scene);
        Login login=fxmlLoader.getController();
        login.setRole("Manager");
        stage.show();
    }
    @FXML
    protected void setTraveller() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Stage stage= (Stage)traveller.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Login");
        stage.setScene(scene);
        Login login=fxmlLoader.getController();
        login.setRole("Traveller");
        stage.show();
    }
}