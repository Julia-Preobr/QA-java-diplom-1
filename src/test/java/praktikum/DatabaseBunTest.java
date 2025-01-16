package praktikum;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DatabaseBunTest extends TestCase {
    private static final Database database = new Database();

    private final String name;   // Имя булочки
    private final float price;   // Цена булочки

    // Конструктор с параметрами
    public DatabaseBunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные - булки: {0}, {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {"Флюоресцентная булка R2-D3", 988.0f},
                {"Краторная булка N-200i", 1255.0f}
        };
    }

    @Test
    public void testAvailableBuns() {
        // Создаем объект класса Bun
        Bun testBun = new Bun(name, price);

        Assert.assertTrue(
                database.availableBuns().stream().anyMatch(
                        dbBun -> dbBun.getName().equals(testBun.getName()) && (
                                dbBun.getPrice() - testBun.getPrice() < 0.001
                        )
                )
        );
    }

}