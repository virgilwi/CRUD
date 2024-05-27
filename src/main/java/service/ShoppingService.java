package service;

import model.ShoppingItem;

import java.util.List;

/**
 * Service interface for managing shopping items.
 */
public interface ShoppingService {
    /**
     * Adds a new shopping item.
     *
     * @param name     the name of the item
     * @param quantity the quantity of the item
     * @return the added shopping item
     */
    ShoppingItem addItem(String name, int quantity);

    /**
     * Gets a shopping item by its ID.
     *
     * @param id the ID of the item
     * @return the shopping item, or null if not found
     */
    ShoppingItem getItem(Long id);

    /**
     * Gets all shopping items.
     *
     * @return a list of all shopping items
     */
    List<ShoppingItem> getAllItems();

    /**
     * Updates an existing shopping item.
     *
     * @param id       the ID of the item
     * @param name     the new name of the item
     * @param quantity the new quantity of the item
     * @return the updated shopping item, or null if not found
     */
    ShoppingItem updateItem(Long id, String name, int quantity);

    /**
     * Removes a shopping item by its ID.
     *
     * @param id the ID of the item
     * @return true if the item was removed, false otherwise
     */
    boolean removeItem(Long id);
}
