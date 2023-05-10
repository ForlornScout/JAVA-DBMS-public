
/**
 * AddItem.java
 * 
 * This class adds the item to the database using the DBMethods.java, Updata.java, and the Item.java classes
 */

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdatePriceController {

    @FXML
    private Button updatePrice;
    @FXML
    private TextField idText;
    @FXML
    private TextField priceText;

    @FXML
    private void updateItemPrice(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Update.updateItemPrice(idText.getText(), priceText.getText());
        } catch (SQLException e) {
            throw e;
        }

        // Close the popup
        Stage stage = (Stage) updatePrice.getScene().getWindow();
        stage.close();
    }

}