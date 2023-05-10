CREATE DATABASE IF NOT EXISTS inventory_db;

use inventory_db;

CREATE TABLE IF NOT EXISTS item(
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(50),
    item_price DOUBLE,
    item_qty DOUBLE
);