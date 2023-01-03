module com.example.proiect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proiect to javafx.fxml;
    exports com.example.proiect;
}