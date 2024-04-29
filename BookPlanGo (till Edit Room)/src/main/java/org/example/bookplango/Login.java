package org.example.bookplango;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Login {
    @FXML
    private Label role;
    @FXML
    private Label message;
    @FXML
    private Button login;
    @FXML
    private Button createAccount;
    @FXML
    private Button back;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    String r,un="",pw="";


    @FXML
    public void setRole(String s)  {
        r=s;
        role.setText("as "+r);
    }
    @FXML
    protected void setBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("BookPlanGo");
        stage.setScene(scene);
        stage.show();
    }@FXML
    protected void setLogin() throws IOException, SQLException {
        un=username.getText();
        pw=password.getText();
        if(un.equals("")||pw.equals("")){
            message.setText("Fillup all informations");
        }else{
            Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select Password from "+r.toLowerCase()+" where Username='"+un+"'");
            if(resultSet.next()){
                String p=resultSet.getString("Password");
                if(p.equals(pw)){
                    if(r.toLowerCase().equals("manager")){
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
                        Stage stage= (Stage)login.getScene().getWindow();
                        Scene scene = new Scene(fxmlLoader.load(), 900,700);
                        stage.setTitle("Dashboard");
                        stage.setScene(scene);
                        ManagerDashboard managerDashboard=fxmlLoader.getController();
                        managerDashboard.setWelcome(un);
                        stage.show();
                    }else if(r.toLowerCase().equals("traveller")){

                    }
                }else{
                    message.setText("Invalid Password");
                }
            }else{
                message.setText("No User Found");
            }
        }
    }@FXML
    protected void setCreateAccount() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createAccount.fxml"));
        Stage stage= (Stage)createAccount.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Create Account");
        stage.setScene(scene);
        CreateAccount createAccount1=fxmlLoader.getController();
        createAccount1.setRole(r);
        stage.show();
    }
}
