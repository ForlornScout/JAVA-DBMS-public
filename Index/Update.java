import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import Methods.Item;

public class Update {

    // Select statement for selecting an item from the database
    public static Item searchItem(int itemId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM inventory_db.item WHERE item_id=" + itemId;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with id " + itemId + ", an error occurred: " + e);
            throw e;
        }
    }

    public static Item searchItemByName(String itemName) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_name=" + itemName;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with id " + itemName + ", an error occurred: " + e);
            throw e;
        }
    }

    public static Item searchItemByPrice(Double itemPrice) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_price=" + itemPrice;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with id " + itemPrice + ", an error occurred: " + e);
            throw e;
        }
    }

    public static Item searchItemByQty(Double itemQty) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_qty=" + itemQty;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with id " + itemQty + ", an error occurred: " + e);
            throw e;
        }
    }

    // Use ResultSet from DB as parameter and set Employee Object's attributes and
    // return employee object.
    private static Item getItemFromResultSet(ResultSet rs) throws SQLException {
        Item item = null;
        if (rs.next()) {
            item = new Item();
            item.setItemId(rs.getInt("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemPrice(rs.getDouble("item_price"));
            item.setItemQuantity(rs.getDouble("item_qty"));
        }
        return item;
    }

    // Observer : SELECT item
    public static ObservableList<Item> searchItems() throws SQLException, ClassNotFoundException {

        // Delcare the SELECT statement
        String select = "SELECT * FROM inventory_db.item";

        // Execute SELECT statement
        try {
            // ResultSet from dataExecuteQuery method
            ResultSet rsItem = DBMethods.dataExecuteQuery(select);

            // Send the ResultSet to the getItemList method and get the item object
            ObservableList<Item> itemList = getItemList(rsItem);
            return itemList;
        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }

    // Select * from items operation
    private static ObservableList<Item> getItemList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        while (rs.next()) {
            itemList.add(new Item(rs.getInt("item_id"), rs.getString("item_name"),
                    rs.getDouble("item_price"), rs.getDouble("item_qty")));
        }
        return itemList;
    }

    // Delete item with id
    public static void deleteItemWithId(String itemId) throws SQLException, ClassNotFoundException {
        // Delete statement
        String updateStatement = "   DELETE FROM inventory_db.item\n" +
                "         WHERE item_id =" + itemId + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error occured while trying to DELETE from item");
            throw e;
        }
    }

    // Insert an item into the item table
    public static void insertItem(String name, Double price, Double qty) throws SQLException, ClassNotFoundException {
        // Insert statement
        String updateStatement = "INSERT INTO inventory_db.item(item_name, item_price, item_qty)\n" +
                "VALUES('" + name + "','" + price + "','" + qty + "');\n";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("SAD, failed to insert item :( ");
            throw e;
        }
    }

    // Edit the name of an item
    public static void updateItemName(String itemId, String name) throws SQLException, ClassNotFoundException {
        String updateStatement = "  UPDATE inventory_db.item\n" + " SET item_name= '" +
                name + "'\n" + " WHERE item_id = " + itemId + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Failed to update item name");
            throw e;
        }
    }

    // Edit an item price
    public static void updateItemPrice(String itemId, String itemPrice) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE inventory_db.item\n" +
                "       SET item_price = '" + itemPrice + "'\n" +
                "   WHERE item_id = " + itemId + ";";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Edit an item quantity
    public static void updateItemQty(String itemId, String itemQty) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE inventory_db.item\n" +
                "       SET item_qty = '" + itemQty + "'\n" +
                "   WHERE item_id = " + itemId + ";";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Run passed in .sql scripts
    public static void runSqlScript(String script) throws Exception {

        // Connect to the DB
        Connection conn = DBMethods.getConnection();
        System.out.println("SQL Script method has been connected");

        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new FileReader("db/" + script + ".sql"));

        sr.runScript(reader);
    }
}
