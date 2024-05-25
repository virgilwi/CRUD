package repository;

import model.ShoppingItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ShoppingListImpl implements ShoppingList {
    private final List<ShoppingItem> items = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public ShoppingItem create(ShoppingItem item) {
        item.setId(idCounter.incrementAndGet());
        items.add(item);
        return item;
    }

    @Override
    public ShoppingItem read(Long id) {
        Optional<ShoppingItem> item = items.stream().filter(i -> i.getId().equals(id)).findFirst();
        return item.orElse(null);
    }

    @Override
    public List<ShoppingItem> readAll() {
        return new ArrayList<>(items);
    }

    @Override
    public ShoppingItem update(ShoppingItem item) {
        int index = items.indexOf(read(item.getId()));
        if (index >= 0){
            items.set(index, item);
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        ShoppingItem item = read(id);
        if (item != null){
            items.remove(item);
        }
        return false;
    }
}
