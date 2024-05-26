import model.ShoppingItem;
import org.junit.*;
import repository.util.DatabaseUtil;
import service.ShoppingService;
import service.ShoppingServiceImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingServiceTest {
    private ShoppingService service;

    @BeforeClass
    public static void setUpClass() throws Exception {
    // Создаем таблицу один раз для всех тестов
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS shopping_items (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "quantity INT NOT NULL" +
                    ")");
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    // Удаляем таблицу после всех тестов
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE shopping_items");
        }
    }

    @Before
    public void setUp() throws Exception {
    // Инициализируем сервис и очищаем таблицу перед каждым тестом
        service = new ShoppingServiceImpl();
            try (Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()) {
                statement.execute("DELETE FROM shopping_items"); // очищаем таблицу перед каждым тестом
            }
        }

    @Test
    public void testAddItem() {
        ShoppingItem item = service.addItem("Eggs", 12);
        assertNotNull(item.getId());
        assertEquals("Eggs", item.getName());
        assertEquals(12, item.getQuantity());
    }

    @Test
    public void testGetItem() {
        ShoppingItem item = service.addItem("Butter", 1);
        ShoppingItem fetchedItem = service.getItem(item.getId());
        assertEquals(item.getId(), fetchedItem.getId());
        assertEquals(item.getName(), fetchedItem.getName());
        assertEquals(item.getQuantity(), fetchedItem.getQuantity());
    }

    @Test
    public void testGetAllItems() {
        service.addItem("Milk", 2);
        service.addItem("Bread", 1);
        List<ShoppingItem> items = service.getAllItems();
        assertEquals(2, items.size());
    }

    @Test
    public void testUpdateItem() {
        ShoppingItem item = service.addItem("Cheese", 1);
        ShoppingItem updatedItem = service.updateItem(item.getId(), "Cheese", 2);
        assertEquals(2, updatedItem.getQuantity());
    }

    @Test
    public void testRemoveItem() {
        ShoppingItem item = service.addItem("Juice", 1);
        boolean isRemoved = service.removeItem(item.getId());
        assertTrue(isRemoved);
   }
}
