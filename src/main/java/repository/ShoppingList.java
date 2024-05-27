package repository;

import model.ShoppingItem;

import java.util.List;

/**
 * List interface for shopping items.
 */
public interface ShoppingList {
    /**
     * Creates a new shopping item.
     *
     * @param item the shopping item to create
     * @return the created shopping item
     */
    ShoppingItem create(ShoppingItem item);

    /**
     * Reads a shopping item by its ID.
     *
     * @param id the ID of the shopping item
     * @return the shopping item, or null if not found
     */
    ShoppingItem read(Long id);

    /**
     * Reads all shopping items.
     *
     * @return a list of all shopping items
     */
    List<ShoppingItem> readAll();

    /**
     * Updates an existing shopping item.
     *
     * @param item the shopping item to update
     * @return the updated shopping item
     */
    ShoppingItem update(ShoppingItem item);

    /**
     * Deletes a shopping item by its ID.
     *
     * @param id the ID of the shopping item
     * @return true if the item was deleted, false otherwise
     */
    boolean delete(Long id);
}
