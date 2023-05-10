import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateNameController {
    @FXML
    private Button UpdateName;

    @FXML
    private TextField idText;
    @FXML
    private TextField priceText;

    @FXML
    void updateItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Update.updateItemName(idText.getText(), priceText.getText());
        } catch (SQLException e) {
            throw e;
        }

        Stage stage = (Stage) UpdateName.getScene().getWindow();
        stage.close();
    }
}