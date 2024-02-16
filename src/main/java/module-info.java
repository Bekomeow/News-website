module com.example.website {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.website to javafx.fxml;
    exports com.example.website;
}