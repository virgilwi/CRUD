package repository;

import model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListImpl implements ShoppingList {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingListImpl.class);

    @Override
    public ShoppingItem create(ShoppingItem item) {
        String sql = "INSERT INTO shopping_items (name, quantity) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                }
            }
            logger.info("Created item: {}", item);
        } catch (SQLException e) {
            logger.error("Error creating item", e);
        }
        return item;
    }

    @Override
    public ShoppingItem read(Long id) {
        String sql = "SELECT * FROM shopping_items WHERE id = ?";
        ShoppingItem item = null;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new ShoppingItem(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("quantity")
                    );
                }
            }
            logger.info("Read item: {}", item);
        } catch (SQLException e) {
            logger.error("Error reading item", e);
        }
        return item;
    }

    @Override
    public List<ShoppingItem> readAll() {
        String sql = "SELECT * FROM shopping_items";
        List<ShoppingItem> items = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ShoppingItem item = new ShoppingItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("quantity")
                );
                items.add(item);
            }
            logger.info("Read all items");
        } catch (SQLException e) {
            logger.error("Error reading all items", e);
        }
        return items;
    }

    @Override
    public ShoppingItem update(ShoppingItem item) {
        String sql = "UPDATE shopping_items SET name = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setLong(3, item.getId());
            pstmt.executeUpdate();
            logger.info("Updated item: {}", item);
        } catch (SQLException e) {
            logger.error("Error updating item", e);
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM shopping_items WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("Deleted item with id: {}", id);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error deleting item", e);
        }
        return false;
    }
}