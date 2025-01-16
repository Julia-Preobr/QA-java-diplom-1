package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @InjectMocks
    private Burger burger;

    @Before
    public void setUp() throws Exception {
        doReturn("Тест-булка").when(bunMock).getName();
        doReturn("Тестлета").when(ingredientMock1).getName();
        doReturn(IngredientType.FILLING).when(ingredientMock1).getType();
        doReturn(411.0f).when(ingredientMock1).getPrice();
        doReturn("Тестоус").when(ingredientMock2).getName();
        doReturn(IngredientType.SAUCE).when(ingredientMock2).getType();
        doReturn(233.0f).when(ingredientMock2).getPrice();
    }

    @Test
    public void setBun() {
        burger.setBun(bunMock);
        Assert.assertEquals("Булочка не применилась в тестовом бургере", bunMock, burger.bun);
    }

    @Test
    public void ingredientListAddBurgerTest() {
        burger.addIngredient(ingredientMock1);
        Assert.assertTrue(burger.ingredients.contains(ingredientMock1));
    }

    @Test
    public void ingredientListRemoveBurgerTest() {
        burger.addIngredient(ingredientMock1);
        burger.removeIngredient(0);
        Assert.assertFalse(burger.ingredients.contains(ingredientMock1));
    }

    @Test
    public void moveIngredientBurgerTest() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceBurgerTest() {
        float price = 100;
        when(bunMock.getPrice()).thenReturn(price);
        when(ingredientMock1.getPrice()).thenReturn(price);

        burger.setBun(bunMock);
        burger.addIngredient(ingredientMock1);

        // Проверяем - цена соответствует ожиданиям (булочка * 2 + ингредиент)
        Assert.assertEquals(price * 2 + price, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptBurgerTest() {
        burger.setBun(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        // Проверяем, что строка рецепта содержит название булочки и ингредиентов
        Assert.assertEquals("(==== Тест-булка ====)\n" +
                        "= filling Тестлета =\n" +
                        "= sauce Тестоус =\n" +
                        "(==== Тест-булка ====)\n" +
                        "\n" +
                        "Price: 644,000000\n",
                receipt.replace("\r\n", "\n")
        );
    }
}
