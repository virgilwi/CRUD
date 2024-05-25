package service;

import model.ShoppingItem;

import java.util.List;

public interface ShoppingService {
    ShoppingItem addItem(String name, int quantity);
    ShoppingItem getItem(Long id);
    List<ShoppingItem> getAllItems();
    ShoppingItem updateItem(Long id, String name, int quantity);
    boolean removeItem(Long id);
}
