import model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.util.DatabaseUtil;
import service.ShoppingService;
import service.ShoppingServiceImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ShoppingService service = new ShoppingServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        createTableIfNotExists();
        while (true) {
            System.out.println("Shopping List Menu:");
            System.out.println("1. Add item");
            System.out.println("2. View item");
            System.out.println("3. View all items");
            System.out.println("4. Update item");
            System.out.println("5. Remove item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

        switch (choice) {
            case 1:
                addItem();
                break;
            case 2:
                viewItem();
                break;
            case 3:
                viewAllItems();
                break;
            case 4:
                updateItem();
                break;
            case 5:
                removeItem();
                break;
            case 6:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createTableIfNotExists() {
        try (Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS shopping_items (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "quantity INT NOT NULL)";
            statement.execute(createTableSQL);
            logger.info("Table 'shopping_items' created or already exists.");
        } catch (Exception e) {
                logger.error("Error creating table 'shopping_items'", e);
        }
    }
    private static void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        ShoppingItem item = service.addItem(name, quantity);
        logger.info("Added item: {}", item);
        System.out.println("Item added: " + item);
    }

    private static void viewItem() {
        System.out.print("Enter item ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        ShoppingItem item = service.getItem(id);
            if (item != null) {
                logger.info("Viewed item: {}", item);
            System.out.println("Item: " + item);
            } else {
            logger.warn("Item with id={} not found", id);
            System.out.println("Item not found.");
        }
    }

    private static void viewAllItems() {
        List<ShoppingItem> items = service.getAllItems();
        logger.info("Viewed all items");
        System.out.println("All items:");
            for (ShoppingItem item : items) {
                System.out.println(item);
        }
    }

    private static void updateItem() {
        System.out.print("Enter item ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter new item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        ShoppingItem item = service.updateItem(id, name, quantity);
            if (item != null) {
                logger.info("Updated item: {}", item);
                System.out.println("Item updated: " + item);
            } else {
                logger.warn("Failed to update item with id={}", id);
                System.out.println("Item not found.");
        }
    }

    private static void removeItem() {
        System.out.print("Enter item ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        boolean removed = service.removeItem(id);
            if (removed) {
                logger.info("Removed item with id={}", id);
                System.out.println("Item removed.");
            } else {
                logger.warn("Failed to remove item with id={}", id);
                System.out.println("Item not found.");
            }
        }
    }
