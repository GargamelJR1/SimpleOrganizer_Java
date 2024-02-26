package org.organizerguipro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.organizerguipro.controller.OrganizerWindowController;
import org.organizerguipro.model.ItemList;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final ItemList items = new ItemList();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/org/organizerguipro/view/OrganizerWindow.fxml"), 950, 600);
        stage.setScene(scene);
        stage.setTitle("OrganizerGUIPro");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        fxmlLoader.setControllerFactory( p -> new OrganizerWindowController(items));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}