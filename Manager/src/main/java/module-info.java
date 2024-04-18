module org.example.manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.manager to javafx.fxml;
    exports org.example.manager;
}