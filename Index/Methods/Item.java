package Methods;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    private IntegerProperty item_id;
    private StringProperty item_name;
    private DoubleProperty item_price;
    private DoubleProperty item_qty;

    public int id;
    public String name;
    public double price, qty;

    public Item() {
        this.item_id = new SimpleIntegerProperty();
        this.item_name = new SimpleStringProperty();
        this.item_price = new SimpleDoubleProperty();
        this.item_qty = new SimpleDoubleProperty();
    }

    public Item(int item_id, String item_name, double item_price, double item_qty) {
        this.id = item_id;
        this.name = item_name;
        this.price = item_price;
        this.qty = item_qty;
    }

    public int getItemId() {
        return item_id.get();
    }

    public int getId() {
        return id;
    }

    public void setItemId(int itemId) {
        this.item_id.set(itemId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public IntegerProperty itemIdProperty() {
        return item_id;
    }

    public String getItemName() {
        return item_name.get();
    }

    public String getName() {
        return name;
    }

    public void setItemName(String name) {
        this.item_name.set(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringProperty itemNameProperty() {
        return item_name;
    }

    public double getItemPrice() {
        return item_price.get();
    }

    public double getPrice() {
        return price;
    }

    public void setItemPrice(double price) {
        this.item_price.set(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DoubleProperty itemPriceProperty() {
        return item_price;
    }

    public double getItemQuantity() {
        return item_qty.get();
    }

    public double getQty() {
        return qty;
    }

    public void setItemQuantity(double quantity) {
        this.item_qty.set(quantity);
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public DoubleProperty itemQtyProperty() {
        return item_qty;
    }

}