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

public class EditRoom implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Button deleteRoom;
    @FXML
    private Button done;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> location;
    @FXML
    private ComboBox<String> hotel;
    @FXML
    private ComboBox<String> bed;
    @FXML
    private ComboBox<String> floor;
    @FXML
    private ComboBox<String> room;
    @FXML
    private TextField Price;
    @FXML
    private CheckBox ac;
    @FXML
    private CheckBox tv;
    @FXML
    private CheckBox balcony;
    String un,l,h,r,a,t,b,ba,p,f;
    String ea,et,eb,eba,ep;
    int i=0;

    @FXML
    protected void setData(String s)  {
        un=s;

    }
    @FXML
    protected void setLocation() throws SQLException{
        if(location.getValue()!=null){
            l=location.getValue();
            hotel.setDisable(false);
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from hotels where Manager='"+un+"' and Location='"+l+"'");
            ObservableList<String> observableList=FXCollections.observableArrayList();
            while(resultSet.next()){
                observableList.add(resultSet.getString("Name"));
            }hotel.setValue("");
            floor.setValue("");
            hotel.setItems(observableList);
            bed.setDisable(true);
            bed.setValue("");
            Price.setDisable(true);
            Price.setText("");
            floor.setDisable(true);
            floor.setValue("");
            ac.setDisable(true);
            tv.setDisable(true);
            balcony.setDisable(true);
            room.setDisable(true);
        }
    }@FXML
    protected void setHotel() throws SQLException{
       if(hotel.getValue()!=null){
           String x="";
            h=hotel.getValue();
            floor.setDisable(false);
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from hotels where Name='"+h+"' and Manager='"+un+"' and Location='"+l+"'");
            while(resultSet.next()){
                x=resultSet.getString("FloorNum");
            }ObservableList<String> observableList1=FXCollections.observableArrayList();
            if(!x.equals("")) {
                int y = Integer.parseInt(x);
                for (int j = 1; j <= y; j++) {
                    observableList1.add(String.valueOf(j));
                }
            }floor.setValue("");
            floor.setItems(observableList1);
            room.setDisable(true);
           bed.setDisable(true);
           bed.setValue("");
           Price.setDisable(true);
           Price.setText("");
           ac.setDisable(true);
           tv.setDisable(true);
           balcony.setDisable(true);
        }
    }@FXML
    protected void setFloor() throws SQLException{
        if(floor.getValue()!=null){
            f=floor.getValue();
            room.setDisable(false);
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from rooms where HotelName='"+h+"' and manager='"+un+"' and location='"+l+"' and FloorNum='"+f+"';");
            ObservableList<String> observableList=FXCollections.observableArrayList();
            while(resultSet.next()){
                //System.out.println(resultSet.getString("RoomNo"));
                observableList.add(resultSet.getString("RoomNo"));
            }room.setItems(observableList);
            bed.setDisable(true);
            bed.setValue("");
            Price.setDisable(true);
            Price.setText("");
            ac.setDisable(true);
            tv.setDisable(true);
            balcony.setDisable(true);
        }
    }@FXML
    protected void setRoom() throws SQLException{
        //if(room.getValue()!=null) {

       // }
    }
    @FXML
    protected void setInfo() throws SQLException{
        if(room.getValue()!=null){
            r = room.getValue();
            bed.setItems(FXCollections.observableArrayList("Single","Double","triple"));
            bed.setDisable(false);
            Price.setDisable(false);
            ac.setDisable(false);
            balcony.setDisable(false);
            tv.setDisable(false);
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo","root","project");
            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from rooms where HotelName='"+h+"' and RoomNo='"+r+"' and manager='"+un+"' and location='"+l+"' and FloorNum="+f);
            ObservableList<String> observableList=FXCollections.observableArrayList();
            while(resultSet.next()){
                p=resultSet.getString("price");
                b=resultSet.getString("BedNum");
                a=resultSet.getString("AC");
                t=resultSet.getString("TV");
                ba=resultSet.getString("Belconi");
            }if(a.toLowerCase().equals("ac")){
                ac.setSelected(true);
            }else{
                ac.setSelected(false);
            }if(t.toLowerCase().equals("no")){
                tv.setSelected(false);
            }else{
                tv.setSelected(true);
            }if(ba.toLowerCase().equals("no")){
                balcony.setSelected(false);
            }else{
                balcony.setSelected(true);
            }Price.setText(p);
            bed.setValue(b);
        }
    }
    @FXML
    protected void setAC(){
        if(ac.isSelected()){
            ea="AC";
        }else{
            ea="NON AC";
        }
    }
    @FXML
    protected void setTV(){
        if(tv.isSelected()){
            et="YES";
        }else{
            et="NO";
        }

    }
    @FXML
    protected void setBelconi(){
        if(balcony.isSelected()){
            ba="YES";
        }else{
            ba="NO";
        }
    }
    @FXML
    protected void setBed(){
        if(bed.getValue()!=null){
            eb=bed.getValue();
        }
    }
    @FXML
    protected void  setBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900,700);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        ManagerDashboard managerDashboard=fxmlLoader.getController();
        managerDashboard.setWelcome(un);
        stage.show();
    }@FXML
    protected void setDeleteRoom() throws IOException,SQLException{
        if(Price.getText().equals("") ){
            message.setText("Fill up all information");
        }else {
            i = 1;
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo", "root", "project");
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("DELETE FROM rooms " +
                    "WHERE HotelName='" + h + "' and RoomNo='" + r + "' and manager='" + un + "' and location='" + l + "' and FloorNum='" + f + "';");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
            Stage stage = (Stage) deleteRoom.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 900, 700);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            ManagerDashboard managerDashboard = fxmlLoader.getController();
            managerDashboard.setWelcome(un);
            managerDashboard.setMessage("Room Deleted");
            stage.show();
        }
    }@FXML
    protected void setDone () throws IOException,SQLException{
            if (Price.getText() == null ) {
                message.setText("Fill up all information");
            } else {
                ep = Price.getText();
                if (p.equals(ep) && a.equals(ea) && t.equals(et) && ba.equals(eba) && b.equals(eb)) {
                    i = 0;
                } else {
                    i = 1;
                }
                if (i == 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
                    Stage stage = (Stage) done.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 900, 700);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    ManagerDashboard managerDashboard = fxmlLoader.getController();
                    managerDashboard.setWelcome(un);
                    stage.show();
                } else {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BookPlanGo", "root", "project");
                    Statement statement1 = connection.createStatement();
                    statement1.executeUpdate("UPDATE rooms\n" +
                            "SET\n" +
                            "BedNum = '" + eb + "',\n" + "price = '" + ep + "',\n" +
                            "ac = '" + ea + "',\n" +
                            "tv = '" + et + "',\n" +
                            "belconi = '" + eba + "'\n" +
                            "WHERE HotelName='" + h + "' and RoomNo='" + r + "' and manager='" + un + "' and location='" + l + "' and FloorNum='" + f + "';");

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("managerdashboard.fxml"));
                    Stage stage = (Stage) done.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 900, 700);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    ManagerDashboard managerDashboard = fxmlLoader.getController();
                    managerDashboard.setWelcome(un);
                    managerDashboard.setMessage("Hotel Added");
                    if (i == 1) {
                        managerDashboard.setMessage("Room Edited");
                    }
                    stage.show();
                }
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        location.setItems(FXCollections.observableArrayList("Dhaka",
                "Chattogram",
                "Sylhet",
                "Rajshahi",
                "Barishal",
                "Rangpur",
                "Khulna",
                "Sylhet"));
        bed.setDisable(true);
        Price.setDisable(true);
        hotel.setDisable(true);
        floor.setDisable(true);
        ac.setDisable(true);
        tv.setDisable(true);
        balcony.setDisable(true);
        room.setDisable(true);
    }
}
