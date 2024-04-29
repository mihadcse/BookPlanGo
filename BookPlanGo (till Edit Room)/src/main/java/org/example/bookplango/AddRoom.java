package org.example.bookplango;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddRoom {
    @FXML
    private ComboBox<String> Location;
    @FXML
    private ComboBox<String> hotelName;
    @FXML
    private ComboBox<String> FloorNum;
    @FXML
    private ComboBox<String> Side;
    @FXML
    private ComboBox<String> BedNum;
    @FXML
    private Label message;
    @FXML
    private Button back;
    @FXML
    private Button addRoom;
    @FXML
    private TextField RoomNum;
    @FXML
    private TextField Price;
    @FXML
    private CheckBox ac;
    @FXML
    private CheckBox tv;
    @FXML
    private CheckBox belconi;
    String un,l;
    String a="NON AC",t="NO",b="NO";
    @FXML
    public void setData(String s) throws SQLException {
        un=s;
        Side.setItems(FXCollections.observableArrayList("South","North","Ceenter","East","West"));
        BedNum.setItems(FXCollections.observableArrayList("Single","Double","triple"));
        Location.setItems(FXCollections.observableArrayList("Dhaka",
                "Chattogram",
                "Sylhet",
                "Rajshahi",
                "Barishal",
                "Rangpur",
                "Khulna",
                "Sylhet"));

    }@FXML
    private void setHotelName() throws SQLException {
        if(Location.getValue()!=null) {

            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("Select * from hotels where Location='"+Location.getValue()+"' and Manager='"+un+"'");
            ObservableList <String> hotels=FXCollections.observableArrayList();
            while(resultSet.next()){
                hotels.add(resultSet.getString("Name"));
            }
            hotelName.setItems(hotels);
            FloorNum.setValue("");
        }
    }
    @FXML
    private void setFloorNum() throws SQLException {
        if(hotelName.getValue()!=null) {
            int j;
            String x="";
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("Select * from hotels where Name='"+hotelName.getValue()+"'");
            while(resultSet.next()){
                x=resultSet.getString("FloorNum");
            }ObservableList <String> floor=FXCollections.observableArrayList();
            for(j=1;j<=Integer.parseInt(x);j++){
                floor.add(String.valueOf(j));
            }FloorNum.setItems(floor);
        }
    }
    @FXML
    protected void setAC(){
        if(ac.isSelected()){
            a="AC";
        }else{
            a="NON AC";
        }
    }
    @FXML
    protected void setTV(){
        if(tv.isSelected()){
            t="YES";
        }else{
            t="NO";
        }

    }
    @FXML
    protected void setBelconi(){
        if(belconi.isSelected()){
            b="YES";
        }else{
            b="NO";
        }
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
        stage.show();
    }
    @FXML
    protected void setAddRoom() throws IOException, SQLException {
        String h=hotelName.getValue(),fn=FloorNum.getValue(),p=Price.getText(),rn=RoomNum.getText(),s=Side.getValue(),bn=BedNum.getValue();
        if(h==null || fn==null || p==null || rn==null || s==null || bn==null){
            message.setText("Fill up all informations");
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("Select * from rooms where HotelName='"+h+"' and RoomNo='"+rn+"' and FloorNum='"+fn+"'and manager='"+un+"' and Location='"+l+"'");
            if(resultSet.next()){
                message.setText("Room Exists");
            }else{
                Statement statement1=connection.createStatement();
                statement1.executeUpdate("INSERT INTO rooms (`HotelName`,`RoomNo`,`FloorNum`,`BedNum`,`manager`,`price`,`location`,`side`,`ac`,`tv`,`belconi`) " +
                        "VALUES ('"+h+"','"+rn+"','"+fn+"','"+bn+"','"+un+"','"+p+"','"+Location.getValue()+"','"+s+"','"+a+"','"+t+"','"+b+"')");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
                Stage stage= (Stage)addRoom.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 900,700);
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                ManagerDashboard managerDashboard=fxmlLoader.getController();
                managerDashboard.setWelcome(un);
                managerDashboard.setMessage("Room Added");
                stage.show();
            }

        }
    }



}
