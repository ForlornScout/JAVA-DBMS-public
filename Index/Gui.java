import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Gui extends Application {

    public static Scene scene;
    public static Stage stage;

    public String username = "root";
    public String password = "password";

    @Override
    public void start(Stage stage) throws IOException {
        Gui.stage = stage;
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.getIcons().add(new Image("/Resources/cow.png"));
    }

    // Changes the root scene
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Set the default size of a window when opening
    public static void setSize(double width, double height) throws IOException {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    // Used to set if the window is resizeable
    public static void isResizeable(boolean isResizeable) {
        stage.setResizable(isResizeable);
    }

    // Change the stage name
    public static void changeStageTitle(String title) {
        stage.setTitle(title);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/Resources/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}