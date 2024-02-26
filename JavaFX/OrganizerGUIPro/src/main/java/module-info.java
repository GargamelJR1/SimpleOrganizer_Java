module org.organizerguipro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.organizerguipro.controller to javafx.fxml;
    exports org.organizerguipro;
    exports org.organizerguipro.controller;
    exports org.organizerguipro.model;
}
