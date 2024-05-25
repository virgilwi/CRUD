import model.ShoppingItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ShoppingService;
import service.ShoppingServiceImpl;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        ShoppingService service = new ShoppingServiceImpl();

        ShoppingItem item1 = service.addItem("Milk", 2);
        ShoppingItem item2 = service.addItem("Bread", 1);

        ShoppingItem readItem = service.getItem(item1.getId());
        logger.info("Read item: {}", readItem);

        List<ShoppingItem> items = service.getAllItems();
        logger.info("All items: {}", items);

        ShoppingItem updatedItem = service.updateItem(item1.getId(), "Milk", 3);
        logger.info("Updated item: {}", updatedItem);

        boolean deleted = service.removeItem(item2.getId());
        logger.info("Deleted item: {}", deleted);

        List<ShoppingItem> finalItems = service.getAllItems();
        logger.info("All items: {}", finalItems);
    }
}
