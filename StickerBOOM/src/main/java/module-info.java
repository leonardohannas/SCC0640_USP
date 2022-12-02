module br.com.stickerboom {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires java.sql.rowset;

    opens br.com.stickerboom to javafx.fxml;
    exports br.com.stickerboom;
    opens br.com.stickerboom.view to javafx.fxml;
    exports br.com.stickerboom.view;
}