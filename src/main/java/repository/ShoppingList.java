package repository;

import model.ShoppingItem;

import java.util.List;

public interface ShoppingList {
    ShoppingItem create(ShoppingItem item);
    ShoppingItem read(Long id);
    List<ShoppingItem> readAll();
    ShoppingItem update(ShoppingItem item);
    boolean delete(Long id);
}
