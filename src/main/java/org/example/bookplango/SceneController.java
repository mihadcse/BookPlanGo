package org.example.bookplango;

import javafx.animation.FadeTransition;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.annotation.Inherited;
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
    private TextField NID_Text,phone_email_Text,service_phone_Text,service_ID_Text,service_name_Text;
    @FXML
    private TextField username_Text;
    @FXML
    private TextField admin_server,admin_user;
    @FXML
    private PasswordField admin_pass;
    @FXML
    private Label userlogin_label,servicelogin_label,username_label,admin_login_label;
    @FXML
    private PasswordField password_text,service_pass_Text;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
    int switch_to_welcome = 0,switch_to_welcome_service = 0;
    int hotel_clicked = 0, car_clicked = 0;
    public String name = "";

    @FXML
    private TextField signup_service;
    @FXML
    private TextField signup_text;
    int hotel_button_click_signup = 0,car_button_click_signup = 0;

    public void hotel_click_signup(ActionEvent event)
    {
        hotel_button_click_signup = 1;
    }
    public void car_click_signup(ActionEvent event)
    {
        car_button_click_signup = 1;
    }
    /*@FXML
    private StackPane rootpane;*/

    /*private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }*/
    public void switchtoAdminSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoUserScene(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(BookPlanGo_Main.class.getResource("selectuser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("selectuser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //Initialize();
    }

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtohotelwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hotel_welcomepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchtoserviceSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtouserSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoserviceSignupScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("service_signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchtoHoteldashboardScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Hotel_dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }
    public void switchtoadminwelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin_welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //username_label.setText(username_Text.getText());
    }

    public void select_hotel(ActionEvent event)
    {
        hotel_clicked = 1;
    }
    public void select_car(ActionEvent event)
    {
        car_clicked = 1;
    }

    public void adminloginbuttonclick(ActionEvent event) throws IOException {
        System.out.println(admin_server.getText()+'\n'+admin_user.getText()+'\n'+admin_pass.getText());
        if(admin_server.getText().equals("sys") && admin_user.getText().equals("root") && admin_pass.getText().equals("dBase@BookPlanGo24"))
        {
             switchtoadminwelcomeScene(event);
        }
        else
        {
            admin_login_label.setText("Invalid");
        }
    }
    public void useronloginButtonclick(ActionEvent event) throws IOException {
        if (username_Text.getText().isBlank() == false && password_text.getText().isBlank() == false) {
            uservalidateLogin();
            if(switch_to_welcome == 1) {
                //makeFadeOut();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcomeuser.fxml"));
                Parent root1 = fxmlLoader.load();
                UserWelcomeDashboard user_name_dashboard = fxmlLoader.getController();
                user_name_dashboard.setWelcome(username_Text.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        } else {
            userlogin_label.setText("Enter username/password");
        }
    }

    public void setWelcome(String name)
    {
        username_label.setText(name);
        System.out.println(name);
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

       // String verifyname = "SELECT Username FROM userinfo WHERE Username = '" + username + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            //Statement st = connectDB.createStatement() ;
            //ResultSet query = st.executeQuery(verifyname);
            //name = query.getString("Username");
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    switch_to_welcome = 1;
                    /*if (query.next()) {
                        name = query.getString("Username");
                    }*/
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

    /*public void uservalidateSignup() {
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
    }*/

    public void uservalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String NID = NID_Text.getText();
        String username = username_Text.getText();
        String password = hashPassword(password_text.getText()); // Hash the password

        String verifysignup = "INSERT INTO userinfo (NID, Username, Password, Contact) " +
                "VALUES ('" + NID + "', '" + username + "', '" + password + "', '" + phone_email_Text.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            try {
                int rowsAffected = statement.executeUpdate(verifysignup);
                if (rowsAffected > 0) {
                    userlogin_label.setText("Welcome! Account Created");
                } else {
                    userlogin_label.setText("Failed to create account. Please check your information.");
                }
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                userlogin_label.setText("Username or NID already exists. Please choose a different one.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void serviceonloginButtonclick(ActionEvent event) throws IOException {
        if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false) {
            servicevalidateLogin();
            if(hotel_clicked == 1 && switch_to_welcome_service == 1)
            {
                //switchtohotelwelcomeScene(event);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotel_welcomepage.fxml"));
                Parent root1 = fxmlLoader.load();
                HotelWelcomeDashboard hotel_name_dashboard = fxmlLoader.getController();
                hotel_name_dashboard.setWelcome(service_ID_Text.getText());
                hotel_name_dashboard.Showing_Numbers();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root1);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
            else if(car_clicked == 1 && switch_to_welcome_service == 1)
            {
                switchtohotelwelcomeScene(event);
            }
        } else {
            servicelogin_label.setText("Enter ID/password");
        }
    }

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
                    switch_to_welcome_service = 1;

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

    public void serviceonsignupButtonclick(ActionEvent event) {
        if (service_ID_Text.getText().isBlank() == false && service_pass_Text.getText().isBlank() == false && service_phone_Text.getText().isBlank() == false && service_name_Text.getText().isBlank() == false) {
            //userlogin_label.setText("Account Created");
            servicevalidateSignup();
        } else {
            servicelogin_label.setText("Enter Informations");
        }
    }
    public void servicevalidateSignup() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String S_ID = service_ID_Text.getText();
        String servicename = service_name_Text.getText();
        String password = hashPassword(service_pass_Text.getText()); // Hash the password

        String checkDuplicateID = "SELECT * FROM serviceprovider_info WHERE service_id = '" + S_ID + "'";

        String verifysignup = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type) values ('"+S_ID+"','"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Hotel"+"')";

        String verifysignup2 = "Insert into serviceprovider_info (service_id,service_name,service_password,service_phone_no,service_type) values ('"+S_ID+"','"+servicename+"','"+password+"','"+service_phone_Text.getText()+"','"+"Car"+"')";

        // Create a dynamic SQL query string to create a table for the service provider
        /*String createHotelTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Hotel_ID INT PRIMARY KEY NOT NULL, " +
                "Hotel_available_rooms INT NOT NULL, " +
                "Hotel_booked_rooms INT NOT NULL, " +
                "Hotel_address VARCHAR(65) NOT NULL, " +
                "Hotel_city VARCHAR(30) NOT NULL)";
        String createCarTable = "CREATE TABLE " + servicename + "_" + S_ID + " (" +
                "Car_ID INT PRIMARY KEY NOT NULL, " +
                "Car_available INT NOT NULL, " +
                "Car_booked INT NOT NULL, " +
                "Car_address VARCHAR(65) NOT NULL, " +
                "Car_city VARCHAR(30) NOT NULL)";*/

        try {
            Statement statement = connectDB.createStatement();
            ResultSet checkResult = statement.executeQuery(checkDuplicateID);
            if(!checkResult.next()) {
                if (hotel_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup);
                    //int createTableRowsAffected = statement.executeUpdate(createHotelTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
                if (car_button_click_signup == 1) {
                    int rowsAffected = statement.executeUpdate(verifysignup2);
                    //int createTableRowsAffected = statement.executeUpdate(createCarTable);
                    if (rowsAffected > 0) {
                        servicelogin_label.setText("Welcome! Account Created");
                    } else {
                        servicelogin_label.setText("Failed to create account.");
                    }
                }
            }
            else{
                servicelogin_label.setText("Service ID already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

