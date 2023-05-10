
/**
 * DelItem.java
 * 
 * This class deletes the item to the database using the DBMethods.java, Updata.java, and the Item.java classes
 */

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DelItemController {

    @FXML
    private Button Delete;

    @FXML
    private TextField idText;

    @FXML
    void delItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Update.deleteItemWithId(idText.getText());
        } catch (SQLException e) {
            throw e;
        }

        // Close the popup
        Stage stage = (Stage) Delete.getScene().getWindow();
        stage.close();
    }
}