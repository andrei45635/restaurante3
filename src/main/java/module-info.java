module com.example.restaurante3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.restaurante3 to javafx.fxml;
    exports com.example.restaurante3;
}