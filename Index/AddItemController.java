
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

public class AddItemController {

    @FXML
    private Button Add;
    @FXML
    private TextField nameText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField qtyText;

    @FXML
    private void addItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        double price = Double.parseDouble(priceText.getText());
        double qty = Double.parseDouble(qtyText.getText());

        try {
            Update.insertItem(nameText.getText(), price, qty);

        } catch (SQLException e) {
            throw e;
        }

        // Close the popup
        Stage stage = (Stage) Add.getScene().getWindow();
        stage.close();
    }
}