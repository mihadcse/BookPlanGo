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

public class ManagerEditProfile {
    @FXML
    private Label pa;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField nid;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Button done;
    @FXML
    private Button changePassword;
    @FXML
    private Button back;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;
    @FXML
    private Label message;
    String un,nd,nm,em,pw;
    String eun,enm,eem,end;
    int i=0;
    @FXML
    public void setData(String s) throws SQLException {
        pa.setVisible(false);
        un=s;
        password.setVisible(false);
        confirm.setVisible(false);
        cancel.setVisible(false);
        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
        Statement statement= connection.createStatement();
        ResultSet resultSet= statement.executeQuery("Select * from manager where Username='"+un+"'");
        while(resultSet.next()){
            nd=resultSet.getString("NID");
            em=resultSet.getString("Email");
            nm=resultSet.getString("Name");
            nid.setText(nd);
            email.setText(em);
            name.setText(nm);
            username.setText(un);
        }
    }
    @FXML
    protected void setDone() throws IOException, SQLException {

        eun=username.getText();
        enm=name.getText();
        eem=email.getText();
        end=nid.getText();
        if(eun.equals(un) && eem.equals(em) && enm.equals(nm) && end.equals(nd)){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
            Stage stage= (Stage)done.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 900,700);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            ManagerDashboard managerDashboard=fxmlLoader.getController();
            managerDashboard.setWelcome(un);
            if(i==1){
                managerDashboard.setMessage("Profile Updated");
            }stage.show();
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            Statement statement1= connection.createStatement();
            ResultSet resultSetU=statement.executeQuery("select Username from manager where Username='"+eun+"'");
            ResultSet resultSetE=statement1.executeQuery("select Email from manager where Email='"+eem+"'");
            if(resultSetU.next() && !eun.equals(un)){
                message.setText("User Exists with this username!!");
            }else if (resultSetE.next() && !eem.equals(em)) {
                message.setText("User Exists with this email!!");
            }else{
                message.setText("Enter password to confirm");
                pa.setVisible(true);
                password.setVisible(true);
                confirm.setVisible(true);
                cancel.setVisible(true);
                username.setDisable(true);
                nid.setDisable(true);
                email.setDisable(true);
                name.setDisable(true);
                back.setDisable(true);
                changePassword.setDisable(true);
                done.setDisable(true);
            }
        }

    } @FXML
    protected void setConfirm() throws IOException, SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
        Statement statement= connection.createStatement();
        Statement statement1= connection.createStatement();
        Statement statement2= connection.createStatement();
        Statement statement3= connection.createStatement();
        ResultSet resultSet=statement1.executeQuery("select Password from manager where Username='"+un+"'");
        while(resultSet.next()){
            pw=resultSet.getString("Password");
        }
        if(pw.equals(password.getText())){
            statement.executeUpdate("UPDATE manager SET UserName = '"+eun+"', Email = '"+eem+"', NID = '"+end+"', Name = '"+enm+"' WHERE Username = '"+un+"'");
            statement2.executeUpdate("UPDATE rooms SET manager = '"+eun+"' WHERE manager = '"+un+"'");
            statement3.executeUpdate("UPDATE hotels SET Manager = '"+eun+"' WHERE Manager = '"+un+"'");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
            Stage stage= (Stage)back.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 900,700);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            ManagerDashboard managerDashboard=fxmlLoader.getController();
            managerDashboard.setWelcome(eun);
            managerDashboard.setMessage("Profile Updated");
            stage.show();
        }else{
            message.setText("Wrong Password");
        }
    }
    @FXML
    protected void setCancel() throws IOException, SQLException {
        pa.setVisible(false);
        password.setText("");
        password.setVisible(false);
        cancel.setVisible(false);
        confirm.setVisible(false);
        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
        Statement statement= connection.createStatement();
        ResultSet resultSet= statement.executeQuery("Select * from manager where Username='"+un+"'");
        while(resultSet.next()){
            nd=resultSet.getString("NID");
            em=resultSet.getString("Email");
            nm=resultSet.getString("Name");
            nid.setText(nd);
            email.setText(em);
            name.setText(nm);
            username.setText(un);
        }username.setDisable(false);
        nid.setDisable(false);
        email.setDisable(false);
        name.setDisable(false);
        back.setDisable(false);
        changePassword.setDisable(false);
        done.setDisable(false);
    }@FXML
    public void setMessage(){
        message.setText("Password Changed");
        i=1;
    }
    @FXML
    protected void setBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        ManagerDashboard managerDashboard=fxmlLoader.getController();
        managerDashboard.setWelcome(un);
        if(i==1){
            managerDashboard.setMessage("Profile Updated");
        }
        stage.show();
    }@FXML
    protected void setChangePassword() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("changePassword.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Change Password");
        stage.setScene(scene);
        ChangePassword changePassword1=fxmlLoader.getController();
        changePassword1.setData(un);
        stage.show();
    }
}
