package service;

import model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ShoppingList;
import repository.ShoppingListImpl;

import java.util.List;

public class ShoppingServiceImpl implements ShoppingService {
    private final ShoppingList list = new ShoppingListImpl();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingServiceImpl.class);

    @Override
    public ShoppingItem addItem(String name, int quantity) {
        logger.info("Adding item: name={}, quantity={}", name, quantity);
        ShoppingItem item = new ShoppingItem(null, name, quantity);
        ShoppingItem createdItem = list.create(item);
        logger.info("Adding item: {}", createdItem);
        return createdItem;
    }

    @Override
    public ShoppingItem getItem(Long id) {
        logger.info("Getting item with id={}", id);
        ShoppingItem item = list.read(id);
        if (item != null) {
            logger.info("Retrieved item: {}", item);
        } else {
            logger.warn("Item with id={} not found", id);
        }
        return item;
    }

    @Override
    public List<ShoppingItem> getAllItems() {
        logger.info("Getting all items");
        List<ShoppingItem> items = list.readAll();
        logger.info("Retrieved {} items", items.size());
        return items;
    }

    @Override
    public ShoppingItem updateItem(Long id, String name, int quantity) {
        logger.info("Updating item: id={}, name={}, quantity={}", id, name, quantity);
        ShoppingItem item = new ShoppingItem(id, name, quantity);
        ShoppingItem updatedItem = list.update(item);
        if (updatedItem != null) {
            logger.info("Updated item: {}", updatedItem);
        } else {
            logger.warn("Failed to update item with id={}", id);
        }
        return updatedItem;
    }

    @Override
    public boolean removeItem(Long id) {
        logger.info("Removing item with id={}", id);
        boolean removed = list.delete(id);
        if (removed) {
            logger.info("Removed item with id={}", id);
        } else {
            logger.warn("Failed to remove item with id={}", id);
        }
        return removed;
    }
}
