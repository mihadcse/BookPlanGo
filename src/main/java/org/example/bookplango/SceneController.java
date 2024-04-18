package org.example.bookplango;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField username_Text,NID_Text,phone_email_Text,service_phone_email_Text,service_ID_Text;
    @FXML
    private Label username_label,userlogin_label,servicelogin_label;
    @FXML
    private PasswordField password_text,service_pass_Text;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
    int switch_to_welcome = 0;
    String name = "";

    public void switchtoUserScene(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoservicefirstpageScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_firstpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtouserSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtouserwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("welcomeuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //username_label.setText(username_Text.getText());
    }

    public void useronloginButtonclick(ActionEvent event) throws IOException {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false) {
            //userlogin_label.setText("Login clicked");
            uservalidateLogin();
            name = username_Text.getText();
            //username_label.setText(name);
            if(switch_to_welcome == 1) {
                switchtouserwelcomeScene(event);
                username_label.setText(name);
            }
        } else {
            userlogin_label.setText("Enter username/password");
        }
    }

    public void useronsignupButtonclick(ActionEvent event) {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false && phone_email_Text.getText().isBlank() == false) {
            //userlogin_label.setText("Account Created");
            uservalidateSignup();
        } else {
            userlogin_label.setText("Enter Informations");
        }
    }

    public void uservalidateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(1) FROM userinfo WHERE Username = '" + username + "' AND Password = '" + password + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switch_to_welcome = 1;
                } else {
                    userlogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }

    public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "Insert into userinfo (NID,Username,Password,Contact) values ('"+NID+"','"+username+"','"+password+"','"+phone_email_Text.getText()+"')";
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

    public void serviceonloginButtonclick(ActionEvent event) throws IOException {
        if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false) {
            servicevalidateLogin();
            //servicelogin_label.setText("welcome");
        } else {
            servicelogin_label.setText("Enter ID/password");
        }
    }

    /*public void servicevalidateLogin() {
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();

        String ID = service_ID_Text.getText();
        String password = hashPassword(service_pass_Text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(1) FROM serviceprovider_info WHERE service_id = '" + ID + "' AND service_password = '" + password + "'";

        try {
            System.out.println("SQL Query: " + verifylogin);
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //switch_to_welcome = 1;
                    servicelogin_label.setText("welcome");
                } else {
                    servicelogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }*/
    public void servicevalidateLogin() {
        DatabaseConnection connectNew = new DatabaseConnection();
        Connection connectDB = connectNew.getConnection();

        String ID = service_ID_Text.getText();
        String password = hashPassword(service_pass_Text.getText()); // Hash the provided password

        String verifylogin = "SELECT count(1) FROM serviceprovider_info WHERE service_id = '" + ID + "' AND service_password = '" + password + "'";

        try {
            System.out.println("SQL Query: " + verifylogin); // Print SQL query for debugging
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    servicelogin_label.setText("welcome");
                } else {
                    servicelogin_label.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }


    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

