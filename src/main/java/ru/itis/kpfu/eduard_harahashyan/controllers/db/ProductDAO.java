package ru.itis.kpfu.eduard_harahashyan.controllers.db;

import ru.itis.kpfu.eduard_harahashyan.controllers.db.interfaces.DAO;
import ru.itis.kpfu.eduard_harahashyan.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO implements DAO<Product> {

    private DBController controller;
    private Connection con;

    public ProductDAO() {
        controller = new DBController();
        con = controller.getCon();
    }

    public boolean addNew(Product element) {
        String query = "INSERT INTO products (name, description, cost, quantity, attack, defense, health, damage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return execute(element, query);
    }

    public boolean edit(Product element) {
        String query = "UPDATE products SET name = ?, description = ?, cost = ?, quantity = ?, attack = ?, defense = ?, health = ?, damage = ? WHERE id = " + element.getId();
        return execute(element, query);
    }

    public boolean execute(Product element, String query) {
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, element.getName());
                ps.setString(2, element.getDescription());
                ps.setInt(3, element.getCost());
                ps.setInt(4, element.getIntQuantity());
                ps.setInt(5, element.getAttack());
                ps.setInt(6, element.getDefense());
                ps.setInt(7, element.getHealth());
                ps.setInt(8, element.getDamage());
                ps.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean remove(int id) {
        if (con != null) {
            try {
                String query = "DELETE FROM products WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Product> getAll() {
        String query = "SELECT id, name, description, cost, quantity, attack, defense, health, damage FROM products";
        ResultSet rs = controller.executeQuery(query);
        return getListFromResultSet(rs);
    }

    public List<Product> getList(String param, String value) {
        try {
            String query = "SELECT * FROM products WHERE " + param + " = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            if (isDigit(value)) {
                ps.setInt(1, Integer.parseInt(value));
            } else {
                ps.setString(1, value);
            }
            ResultSet rs = ps.executeQuery();
            return getListFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Product getFirst(String type, String value) {
        List<Product> temp = getList(type, value);
        return temp == null ? null : temp.get(0);
    }

    private List<Product> getListFromResultSet(ResultSet rs) {
        List<Product> result = new LinkedList<Product>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int attack = rs.getInt("attack");
                    int defense = rs.getInt("defense");
                    int damage = rs.getInt("damage");
                    int health = rs.getInt("health");
                    int id = rs.getInt("id");
                    int cost = rs.getInt("cost");
                    int quantity = rs.getInt("quantity");
                    Product temp = new Product(name, attack, defense, damage, health, description, cost, quantity);
                    temp.setId(id);
                    result.add(0, temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result.isEmpty() ? null : result;
    }

    public int getLastId() {
        String query = "SELECT max(id) FROM products";
        ResultSet rs = controller.executeQuery(query);
        try {
            rs.next();
            return rs.getInt("max");
        } catch (SQLException e) {
            return 0;
        }
    }

    public void close() {
        controller.closeCon();
    }

}
