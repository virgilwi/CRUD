package model;

/**
 * Represents an item in the shopping list.
 */
public class ShoppingItem {
    private Long id;
    private String name;
    private int quantity;

    /**
     * Constructs a new ShoppingItem.
     *
     * @param id       the ID of the item
     * @param name     the name of the item
     * @param quantity the quantity of the item
     */
    public ShoppingItem(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the item.
     *
     * @return the ID of the item
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the new ID of the item
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity the new quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" + "id=" + id + ", name=" + name + '\'' + ", quantity=" + quantity + '}';
    }
}
