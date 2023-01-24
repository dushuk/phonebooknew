module com.example.phonebooknew {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.phonebooknew to javafx.fxml;
    exports com.example.phonebooknew;
}