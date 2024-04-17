package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField username_Text;
    @FXML
    private TextField phone_email_Text;
    @FXML
    private PasswordField password_text;
    @FXML
    private Label userlogin_label;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;

    public void switchtoUserScene(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onloginButtonclick(ActionEvent event) {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false) {
            //userlogin_label.setText("Login clicked");
            validateLogin();
        } else {
            userlogin_label.setText("Enter username/password");
        }
    }

    public void onsignupButtonclick(ActionEvent event) {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false && phone_email_Text.getText().isBlank() == false) {
            //userlogin_label.setText("Account Created");
            validateSignup();
        } else {
            userlogin_label.setText("Enter Informations");
        }
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifylogin = "Select count(1) from userinfo where Username = '" + username_Text.getText() + "' and Password = '" + password_text.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    userlogin_label.setText("Welcome!");
                } else {
                    userlogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateSignup(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifysignup = "INSERT INTO userinfo (Username, Password, Contact) VALUES ('"+username_Text.getText()+"','"+password_text.getText()+"','"+phone_email_Text.getText()+"')";

        try {
            Statement statement = connectDB.createStatement();
            int rowsAffected = statement.executeUpdate(verifysignup);
            if (rowsAffected > 0) {
                userlogin_label.setText("Welcome! Account Created");
            } else {
                userlogin_label.setText("Failed to create account. Please check your information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
