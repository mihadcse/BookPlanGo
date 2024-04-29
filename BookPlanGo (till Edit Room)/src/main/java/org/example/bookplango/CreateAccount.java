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

public class CreateAccount {
    String r,nm="",n="",un="",pw="",e="";
    @FXML
    private Label role;
    @FXML
    private Label message;
    @FXML
    private Button back;
    @FXML
    private Button createAccount;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField nid;
    @FXML
    private TextField email;
    @FXML
    public void setRole(String s){
        r=s;
        role.setText("as "+r);
    }@FXML
    protected void setBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("BookPlanGo");
        stage.setScene(scene);
        stage.show();
    }@FXML
    protected void setCreateAccount() throws IOException, SQLException {
        un=username.getText();
        pw=password.getText();
        n=nid.getText();
        e=email.getText();
        nm=name.getText();
        if(nm.equals("")||un.equals("")||pw.equals("")||n.equals("")||e.equals("")){
            message.setText("Fill up all informations");
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            Statement statement1= connection.createStatement();
            Statement statement2= connection.createStatement();
            ResultSet resultSetU=statement.executeQuery("select Username from "+r.toLowerCase()+" where Username='"+un+"'");
            ResultSet resultSetE=statement1.executeQuery("select Email from "+r.toLowerCase()+" where Email='"+e+"'");
            if(resultSetU.next()){
                message.setText("User Exists with this username!!");
            }else if (resultSetE.next()) {
                message.setText("User Exists with this email!!");
            }else{
                statement2.executeUpdate("INSERT INTO "+r.toLowerCase()+" (`Username`, `Password`, `NID`, `Email`,`Name`) VALUES ('"+un+"', '"+pw+"','"+n+"', '"+e+"', '"+nm+"')");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Stage stage= (Stage)createAccount.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 900,700);
                stage.setTitle("BookPlanGo");
                stage.setScene(scene);
                HelloController helloController=fxmlLoader.getController();
                helloController.setMessage("account created with "+un+" username as "+r);
                stage.show();
            }
        }
    }

}
