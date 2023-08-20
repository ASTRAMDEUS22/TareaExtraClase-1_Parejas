module tareaextraclase1.tareaextraclase1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens tareaextraclase1.tareaextraclase1 to javafx.fxml;
    exports tareaextraclase1.tareaextraclase1;
}