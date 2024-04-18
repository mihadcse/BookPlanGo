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

public class ModifyRoom implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Label hotelName;
    @FXML
    private Button searchOrModify;


    @FXML
    private Button back;
    @FXML
    private ComboBox<String> FloorNum;
    @FXML
    private ComboBox<String> BedNum;
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
    String som="search";

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FloorNum.setItems(FXCollections.observableArrayList("1st",
                "2nd",
                "3rd",
                "4th",
                "5th"));
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
    protected void setSearchOrModify() throws FileNotFoundException {
        fn=FloorNum.getValue();
        rn=RoomNo.getText();
        if(fn==null || rn.equals("")){
            message.setText("Fill up all informations");
        }else{
            if(som.equals("search")) {
                try {
                    FileReader fileReader=new FileReader(fn+rn+".txt");
                    fileReader.close();
                    BedNum.setDisable(false);
                    AC.setDisable(false);
                    Balconi.setDisable(false);
                    TV.setDisable(false);
                    Price.setDisable(false);
                    searchOrModify.setText("MODIFY");
                    som="modify";
                    FloorNum.setDisable(true);
                    RoomNo.setDisable(true);
                    BedNum.setItems(FXCollections.observableArrayList("Single",
                            "Double",
                            "Triple"));
                } catch (FileNotFoundException e) {
                    message.setText("No room found");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (som.equals("modify")) {
                bn=BedNum.getValue();
                p=Price.getText();
                if(p.equals("")||rn.equals("")){
                    message.setText("Fillup All Information");
                }else {
                    try {
                        FileWriter fileWriter = new FileWriter(fn + rn + ".txt");
                        fileWriter.write(bn + "\n" + a + "\n" + b + "\n" + t + "\n" + p);
                        message.setText("Room Modified Successfully");
                        fileWriter.close();
                        som="search";
                        searchOrModify.setText("SEARCH");
                        BedNum.setDisable(true);
                        AC.setDisable(true);
                        Balconi.setDisable(true);
                        TV.setDisable(true);
                        Price.setDisable(true);
                        FloorNum.setDisable(false);
                        RoomNo.setDisable(false);
                        FloorNum.setItems(FXCollections.observableArrayList("1st",
                                "2nd",
                                "3rd",
                                "4th",
                                "5th"));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}