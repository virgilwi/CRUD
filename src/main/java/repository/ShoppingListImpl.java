package repository;

import model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ShoppingListImpl implements ShoppingList {
    private final List<ShoppingItem> items = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingListImpl.class);

    @Override
    public ShoppingItem create(ShoppingItem item) {
        item.setId(idCounter.incrementAndGet());
        items.add(item);
        logger.info("Created item: {}", item);
        return item;
    }

    @Override
    public ShoppingItem read(Long id) {
        Optional<ShoppingItem> item = items.stream().filter(i -> i.getId().equals(id)).findFirst();
        item.ifPresent(i -> logger.info("Read item: {}", i));
        return item.orElse(null);
    }

    @Override
    public List<ShoppingItem> readAll() {
        logger.info("Reading all items");
        return new ArrayList<>(items);
    }

    @Override
    public ShoppingItem update(ShoppingItem item) {
        int index = items.indexOf(read(item.getId()));
        if (index >= 0){
            items.set(index, item);
            logger.info("Updated item: {}", item);
            return item;
        }
        logger.warn("Item not found for update: {}", item);
        return null;
    }

    @Override
    public boolean delete(Long id) {
        ShoppingItem item = read(id);
        if (item != null){
            items.remove(item);
            logger.info("Deleted item: {}", item);
            return true;
        }
        logger.warn("Item not found for delete: {}", id);
        return false;
    }
}
