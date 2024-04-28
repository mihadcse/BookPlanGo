package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traveler_Dashboard_Controller {
    private Stage stage;
    private Scene scene;
    UserWelcomeDashboard user_dash;
    @FXML
    private Label username_label_dashboard;

    @FXML
    private TableView<Traveler_Dashboard> travelerDashboardTableView;
    @FXML
    private TableColumn<Traveler_Dashboard,Integer> travelerIDTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard,String>travelerDestinationTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard,Integer>travelerExpensesTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard,String>travelerStartTableColumn;
    @FXML
    private TableColumn<Traveler_Dashboard,String>travelerEndTableColumn;

    public void switchtouserSigninScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void setWelcome(String name)
    {
        username_label_dashboard.setText(name);
        System.out.println(name);
    }

    ObservableList<Traveler_Dashboard> traveler_dashboardObservableList = FXCollections.observableArrayList();

    public void initialize () {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String travelerTableViewquery = "Select id,Destination,StartDate,EndDate,Total_Expenses from tourdetails where traveler_username = '" + username_label_dashboard.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery (travelerTableViewquery);
            while (queryOutput.next()) {
                Integer queryID = queryOutput.getInt("iD");
                String queryDestination = queryOutput.getString( "Destination");
                String queryStartdate = queryOutput.getString("Startdate");
                String queryEnddate = queryOutput.getString("Enddate");
                Integer queryExpenses = queryOutput.getInt("Total_Expenses");
                traveler_dashboardObservableList.add(new Traveler_Dashboard (queryID, queryExpenses, queryDestination, queryStartdate, queryEnddate));
                System.out.println(queryID);
                System.out.println(queryDestination);
                System.out.println(queryStartdate);
                System.out.println(queryEnddate);
                System.out.println(queryExpenses);
            }

            travelerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("tour_ID"));
            travelerDestinationTableColumn.setCellValueFactory (new PropertyValueFactory<>("Destination"));
            travelerStartTableColumn.setCellValueFactory (new PropertyValueFactory<>("StartDate"));
            travelerEndTableColumn.setCellValueFactory (new PropertyValueFactory<>("EndDate"));
            travelerExpensesTableColumn.setCellValueFactory(new PropertyValueFactory<>("Expenses"));

            travelerDashboardTableView.setItems (traveler_dashboardObservableList);

        }
        catch(SQLException e) {
            Logger.getLogger (Traveler_Dashboard_Controller.class.getName()).log (Level. SEVERE,null, e);
            e.printStackTrace();
        }
    }

}
