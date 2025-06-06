module com.tama.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.commons.csv;
    requires org.controlsfx.controls;

    opens com.tama.javafxtest to javafx.fxml;
    opens com.tama.javafxtest.controller to javafx.fxml;
    opens com.tama.javafxtest.model to javafx.base;
    
    exports com.tama.javafxtest;
    exports com.tama.javafxtest.controller;
    exports com.tama.javafxtest.model;

}