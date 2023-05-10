import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateQtyController {
    @FXML
    private Button updateQty;
    @FXML
    private TextField idText;
    @FXML
    private TextField amountText;

    @FXML
    private void updateItemQty(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Update.updateItemQty(idText.getText(), amountText.getText());
        } catch (SQLException e) {
            throw e;
        }

        // Close the popup
        Stage stage = (Stage) updateQty.getScene().getWindow();
        stage.close();
    }
}