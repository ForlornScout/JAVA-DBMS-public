
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Methods.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {
    private static double width = 400;
    private static double height = 170;

    LoginController login = new LoginController();

    @FXML
    private Label userLogin;
    @FXML
    private TextField itemIdLookup;
    @FXML
    private TableView<Item> ItemTable;
    @FXML
    private TableColumn<Item, Integer> itemIdColumn;
    @FXML
    private TableColumn<Item, String> itemNameColumn;
    @FXML
    private TableColumn<Item, Double> itemPriceColumn;
    @FXML
    private TableColumn<Item, Double> itemQtyColumn;
    @FXML
    public TextArea commandResultArea;

    // Search for a specific item
    @FXML
    void searchItem() throws ClassNotFoundException, SQLException {
        try {
            int itemId = Integer.parseInt(itemIdLookup.getText());
            Item item = Update.searchItem(itemId);
            popAndShowItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
            commandResultArea.setText("An error occurred while fetching item information from database!\n");
            throw e;
        }
    }

    // Search all items in the database
    @FXML
    void searchItems() throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Item> itemData = Update.searchItems();
            popItems(itemData);
        } catch (SQLException e) {
            e.printStackTrace();
            commandResultArea.setText("An error occured while fetching all items from the database!");
            throw e;
        }
    }

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
        Gui gui = new Gui();

        userLogin.setText(gui.username);

        // Use this for updating the table
        ObservableList<Item> tableList = FXCollections.observableArrayList();

        try {
            Connection conn = DBMethods.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM inventory_db.item");

            while (rs.next()) {
                tableList.add(new Item(rs.getInt("item_id"), rs.getString("item_name"),
                        rs.getDouble("item_price"), rs.getDouble("item_qty")));
            }

        } catch (SQLException e) {
            System.out.println("DB Connection failed at table population!");
            throw e;
        }

        // Populate the table with all of this spaghetti code
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        ItemTable.setItems(tableList);
    }

    // Populate Item Table :)
    @FXML
    void popItem(Item item) throws ClassNotFoundException {
        ObservableList<Item> itemData = FXCollections.observableArrayList();
        itemData.add(new Item(item.getItemId(), item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
        ItemTable.setItems(itemData);
    }

    // Set Item information to the commandResultArea
    @FXML
    void setItemInfoToTextArea(Item item) {
        commandResultArea.setText("Item ID: " + item.getItemId() + "\n" +
                "Item Name: " + item.getItemName() + "\n" +
                "Item Price: " + item.getItemPrice() + "\n" +
                "Item Quantity: " + item.getItemQuantity());
    }

    // Pop Item for the TableView and display Item on commandResultArea
    @FXML
    void popAndShowItem(Item item) throws ClassNotFoundException {
        if (item != null) {
            popItem(item);
            setItemInfoToTextArea(item);
        } else {
            commandResultArea.setText("This item does not exist!");
        }
    }

    // Pop Items for TableView
    @FXML
    void popItems(ObservableList<Item> itemData) throws ClassNotFoundException {
        ItemTable.setItems(itemData);
    }

    // Bring up the add item window using the AddItem controller
    @FXML
    private void addItem() throws IOException, ClassNotFoundException, SQLException {
        Stage about = new Stage();
        about.initModality(Modality.NONE);
        about.setTitle("Add Item");
        Scene scene1 = new Scene(Gui.loadFXML("add-item"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
        searchItems();
    }

    // Bring up the delete item window using the DelItem controller
    @FXML
    private void delItem() throws IOException, ClassNotFoundException, SQLException {
        Stage about = new Stage();
        about.initModality(Modality.NONE);
        about.setTitle("Delete Item");
        Scene scene1 = new Scene(Gui.loadFXML("del-item"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
        searchItems();
    }

    // Update the item name
    @FXML
    private void updateItemName() throws IOException, ClassNotFoundException, SQLException {
        Stage about = new Stage();
        about.initModality(Modality.NONE);
        about.setTitle("Update Item Name");
        Scene scene1 = new Scene(Gui.loadFXML("update-name"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
        searchItems();
    }

    // Update the item price
    @FXML
    private void updateItemPrice() throws IOException, ClassNotFoundException, SQLException {
        Stage about = new Stage();
        about.initModality(Modality.NONE);
        about.setTitle("Update Item Price");
        Scene scene1 = new Scene(Gui.loadFXML("update-price"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
        searchItems();
    }

    // Update the item quantity
    @FXML
    private void updateItemQty() throws IOException, ClassNotFoundException, SQLException {
        Stage about = new Stage();
        about.initModality(Modality.NONE);
        about.setTitle("Update Item Quantity");
        Scene scene1 = new Scene(Gui.loadFXML("update-qty"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
        searchItems();
    }

    // When the user "Logs out"
    @FXML
    private void switchToLogin() throws IOException {
        Gui.setRoot("login");
        Gui.setSize(width, height);
        Gui.isResizeable(false);
        Gui.changeStageTitle("Login");
    }

    // About
    @FXML
    void aboutClicked() throws IOException {
        Stage about = new Stage();
        about.initModality(Modality.APPLICATION_MODAL);
        about.setTitle("About");
        Scene scene1 = new Scene(Gui.loadFXML("about"));

        about.setScene(scene1);
        about.setResizable(false);
        about.showAndWait();
    }

    // Terminate the program when requested
    @FXML
    private void closeOnClick() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
