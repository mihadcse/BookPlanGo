module com.example.zarif_manager_part {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zarif_manager_part to javafx.fxml;
    exports com.example.zarif_manager_part;
}