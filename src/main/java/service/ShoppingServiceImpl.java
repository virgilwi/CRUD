package service;

import model.ShoppingItem;
import repository.ShoppingList;
import repository.ShoppingListImpl;

import java.util.List;

public class ShoppingServiceImpl implements ShoppingService {
    private final ShoppingList list = new ShoppingListImpl();


    @Override
    public ShoppingItem addItem(String name, int quantity) {
        ShoppingItem item = new ShoppingItem(null, name, quantity);
        return list.create(item);
    }

    @Override
    public ShoppingItem getItem(Long id) {
        return list.read(id);
    }

    @Override
    public List<ShoppingItem> getAllItems() {
        return list.readAll();
    }

    @Override
    public ShoppingItem updateItem(Long id, String name, int quantity) {
        ShoppingItem item = new ShoppingItem(id, name, quantity);
        return list.update(item);
    }

    @Override
    public boolean removeItem(Long id) {
        return list.delete(id);
    }
}
