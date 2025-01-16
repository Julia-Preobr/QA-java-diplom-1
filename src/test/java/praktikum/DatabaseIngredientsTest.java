package praktikum;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DatabaseIngredientsTest extends TestCase {
    private static final Database database = new Database();

    private final String name;    // Имя ингредиента
    private final float price;    // Цена ингредиента
    private final IngredientType type;  // Тип ингредиента (начинка или соус)

    // Конструктор для параметризированного теста
    public DatabaseIngredientsTest(String name, float price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Parameterized.Parameters(name = "Тестовые данные - ингредиент: {0}, Цена: {1}, Тип: {2}")
    public static Object[][] getIngredientsData() {
        return new Object[][]{
                // Начинки
                {"Мясо бессмертных моллюсков Protostomia", 1337.0f, IngredientType.FILLING},
                {"Говяжий метеорит (отбивная)", 3000.0f, IngredientType.FILLING},
                {"Биокотлета из марсианской Магнолии", 424.0f, IngredientType.FILLING},
                {"Филе Люминесцентного тетраодонтимформа", 988.0f, IngredientType.FILLING},
                {"Хрустящие минеральные кольца", 300.0f, IngredientType.FILLING},
                {"Плоды Фалленианского дерева", 874.0f, IngredientType.FILLING},
                {"Кристаллы марсианских альфа-сахаридов", 762.0f, IngredientType.FILLING},
                {"Мини-салат Экзо-Плантаго", 4400.0f, IngredientType.FILLING},
                {"Сыр с астероидной плесенью", 4142.0f, IngredientType.FILLING},

                // Соусы
                {"Соус Spicy-X", 90.0f, IngredientType.SAUCE},
                {"Соус фирменный Space Sauce", 80.0f, IngredientType.SAUCE},
                {"Соус традиционный галактический", 15.0f, IngredientType.SAUCE},
                {"Соус с шипами Антарианского плоскоходца", 88.0f, IngredientType.SAUCE}
        };
    }

    @Test
    public void testAvailableIngredients() {
        // Создаем объект ингредиента с параметрами
        Ingredient testIngredient = new Ingredient(type, name, price);

        Assert.assertTrue(
                database.availableIngredients().stream().anyMatch(
                        dbIngredient -> dbIngredient.getName().equals(testIngredient.getName()) &&
                                dbIngredient.getType() == testIngredient.getType() && (
                                dbIngredient.getPrice() - testIngredient.getPrice() < 0.001
                        )
                )
        );
    }

}