import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * !!! WARNING !!!
 * ALL OF THE USERNAME AND PASSWORD FUNCTIONALITY WILL BE CHANGED
 * BEFORE PRODUCTION VERSION OF PROGRAM.
 * 
 * IN ITS CURRENT STATE, THIS IS NOT A "SAFE" PROGRAM
 */

public class LoginController {

    private static double width = 590;
    private static double height = 646;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    public void initialize() throws Exception {
        Update.runSqlScript("schema");
        Update.runSqlScript("seed");
        System.out.println("All good!");
    }

    @FXML
    private void switchToMainMenu() throws Exception, IOException {
        Gui gui = new Gui();

        if (usernameInput.getText().toString().equals(gui.username)
                && passwordInput.getText().toString().equals(gui.password)) {

            Gui.setRoot("main-menu");
            Gui.setSize(width, height);
            Gui.isResizeable(false);
            Gui.changeStageTitle("Main Menu");

        } else {
            failedLogin();
        }
    }

    // Terminate the program when requested
    @FXML
    private void closeOnClick() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    // Clicked the about menu
    @FXML
    void aboutClicked() throws IOException {
        Stage about = new Stage();
        about.initModality(Modality.APPLICATION_MODAL);
        about.setTitle("About");
        Scene scene1 = new Scene(Gui.loadFXML("about"));

        about.setScene(scene1);
        about.showAndWait();
    }

    // Failed Login popup
    public static void failedLogin() {
        Stage failedpopup = new Stage();

        failedpopup.initModality(Modality.APPLICATION_MODAL);
        failedpopup.setTitle("Incorrect Credentials");

        Label label1 = new Label("Incorrect username or password");
        Button button1 = new Button("Close");

        button1.setOnAction(e -> failedpopup.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        failedpopup.setScene(scene1);
        failedpopup.showAndWait();
    }
}
