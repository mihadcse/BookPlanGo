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

public class ChangePassword {
    @FXML
    private Label message;
    @FXML
    private Button back;
    @FXML
    private Button changePassword;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField reNewPassword;
    String un,pw;
    public void setData(String s){
        un=s;
    }
    @FXML
    protected void setBack() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerEditProfile.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Edit Profile");
        stage.setScene(scene);
        ManagerEditProfile managerEditProfile=fxmlLoader.getController();
        managerEditProfile.setData(un);
        stage.show();
    }
    @FXML
    protected void setChangePassword() throws SQLException, IOException {
        if(oldPassword.getText().isEmpty() || newPassword.getText().isEmpty() || reNewPassword.getText().isEmpty()){
            message.setText("Fillup All information");
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            Statement statement1= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("Select Password from manager where Username='"+un+"'");
            while(resultSet.next()){
                pw=resultSet.getString("Password");
            }if(!pw.equals(oldPassword.getText())){
                message.setText("Invalid Old Password");
            }else if(!newPassword.getText().equals(reNewPassword.getText())){
                message.setText("New password didn't match");
            }else{
                statement1.executeUpdate("UPDATE manager SET Password = '"+newPassword.getText()+"' WHERE Username = '"+un+"'");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerEditProfile.fxml"));
                Stage stage= (Stage)changePassword.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 900,700);
                stage.setTitle("Edit Profile");
                stage.setScene(scene);
                ManagerEditProfile managerEditProfile=fxmlLoader.getController();
                managerEditProfile.setData(un);
                managerEditProfile.setMessage();
                stage.show();
            }
        }
    }
}
