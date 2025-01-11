package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final String name;    // Имя ингредиента
    private final float price;    // Цена ингредиента
    private final IngredientType type;  // Тип ингредиента (начинка или соус)

    // Конструктор для параметризированного теста
    public IngredientParameterizedTest(String name, float price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    // Параметризированные данные
    @Parameterized.Parameters(name = "Ингредиент: {0}, Цена: {1}, Тип: {2}")
    public static Object[][] getData() {
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

    // Тест для проверки создания ингредиента
    @Test
    public void testIngredientCreate() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertNotNull("Ингредиент не должен быть null", ingredient);
    }

    // Тест для проверки правильности заполнения имени ингредиента
    @Test
    public void testIngredientName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Имя ингредиента не совпадает", name, ingredient.getName());
    }

    // Тест для проверки правильности заполнения типа ингредиента
    @Test
    public void testIngredientType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Тип ингредиента не совпадает", type, ingredient.getType());
    }

    // Тест для проверки правильности заполнения цены ингредиента
    @Test
    public void testIngredientPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals("Цена ингредиента не совпадает", price, ingredient.getPrice(), 0.001f);
    }
}
