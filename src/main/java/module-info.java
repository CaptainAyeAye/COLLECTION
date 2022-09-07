module com.example.collection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.collection to javafx.fxml;
    exports com.example.collection;
}