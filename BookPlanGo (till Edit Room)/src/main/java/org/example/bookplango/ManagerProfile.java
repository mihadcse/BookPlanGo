package org.example.bookplango;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ManagerProfile {
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label role;
    @FXML
    private Label NID;
    @FXML
    private Label email;
    @FXML
    private Button back;
    @FXML
    private TableView<String> hotel;
    @FXML
    private TableView<String> vehicle;
    String un;
    @FXML
    public void setData(String s) throws SQLException {
        un=s;
        role.setText("Role: Manager");
        username.setText("Username: "+un);
        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
        Statement statement= connection.createStatement();
        ResultSet resultSet= statement.executeQuery("Select * from manager where Username='"+un+"'");
        while(resultSet.next()){
            NID.setText("NID: "+resultSet.getString("NID"));
            email.setText("Email: "+resultSet.getString("Email"));
            name.setText("Name: "+resultSet.getString("Name"));
        }
    }@FXML
    protected void setBack() throws IOException {
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
