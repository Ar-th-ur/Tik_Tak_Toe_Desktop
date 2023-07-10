module com.example.tik_tak_toe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.tik_tak_toe to javafx.fxml;
    exports com.example.tik_tak_toe;
}