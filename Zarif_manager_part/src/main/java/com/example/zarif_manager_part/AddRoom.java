package com.example.zarif_manager_part;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRoom implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Label hotelName;
    @FXML
    private Button addRoom;


    @FXML
    private Button back;
    @FXML
    private ComboBox<String> FloorNum;
    @FXML
    private ComboBox<String> BedNum;
    @FXML
    private ComboBox<String> Side;
    @FXML
    private TextField Price;
    @FXML
    private TextField RoomNo;
    @FXML
    private CheckBox AC;
    @FXML
    private CheckBox TV;
    @FXML
    private CheckBox Balconi;
    String fn,p,rn,bn,s,a="Non AC",b="NO",t="NO";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FloorNum.setItems(FXCollections.observableArrayList("1st",
                "2nd",
                "3rd",
                "4th",
                "5th"));
        BedNum.setItems(FXCollections.observableArrayList("Single",
                "Double",
                "Triple"));
        Side.setItems(FXCollections.observableArrayList("East",
                "West",
                "North",
                "South",
                "Center"));

    }


    @FXML
    protected void setAC(){
        if(AC.isSelected()){
            a="AC";
        }else{
            a="Non AC";
        }
    }
    @FXML
    protected void setTV(){
        if(AC.isSelected()){
            t="YES";
        }else{
            t="NO";
        }

    }
    @FXML
    protected void setBalconi(){
        if(AC.isSelected()){
            b="YES";
        }else{
            b="NO";
        }
    }
    @FXML
    protected void setBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage= (Stage)back.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 645, 465);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void setAddRoom() throws IOException {
        fn=FloorNum.getValue();
        bn=BedNum.getValue();
        s=Side.getValue();
        rn=RoomNo.getText();
        p=Price.getText();
        if(p.equals("")||rn.equals("")||fn==null||bn==null||s==null){
            message.setText("Fillup All Information");
        }else{
            message.setText("Room Added Successfully");
            System.out.println("Floor: "+fn+"\nBed: "+bn+"\nSide: "+s+"\nRoom no: "+rn+"\nAC: "+a+"\nTV: "+t+"\nBalconi: "+b+"\nPrice: "+p);
            try {
                FileReader fileReader=new FileReader(fn+rn+".txt");
                message.setText("Room Already Exists");
                fileReader.close();
            } catch (FileNotFoundException e) {
                FileWriter fileWriter=new FileWriter(fn+rn+".txt");
                fileWriter.write(bn+"\n"+a+"\n"+b+"\n"+t+"\n"+p);
                fileWriter.close();
            }
        }
    }
}