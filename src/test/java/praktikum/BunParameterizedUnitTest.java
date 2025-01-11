package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunParameterizedUnitTest {

    private final String name;   // Имя булочки
    private final float price;   // Цена булочки

    public BunParameterizedUnitTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Параметризированные данные
    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Флюоресцентная булка R2-D3", 988.0f},
                {"Краторная булка N-200i", 1255.0f},
                {"Флюоресцентная булка R2-D3", -988.0f},
                {"Краторная булка N-200i", 12 / 55.0f},
                {"Краторная булка N-200i", 0.0f}
        };
    }

    @Test
    public void testBunName() {
        // Создаем объект класса Bun
        Bun bun = new Bun(name, price);

        // Проверяем имя булочки
        Assert.assertEquals("Неверное имя булочки", name, bun.getName());
    }

    @Test
    public void testBunPrice() {
        // Создаем объект класса Bun
        Bun bun = new Bun(name, price);
        // Проверяем цену булочки
        Assert.assertEquals("Неверная цена булочки", price, bun.getPrice(), 0.001);
    }
}


