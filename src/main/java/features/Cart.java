package features;

import features.BaseClass;
import org.openqa.selenium.By;

import static features.BaseClass.driver;

public class Cart extends BaseClass {
    public static void viewCart(String user
    )
    {
        login(user);
        System.out.println("Testing cart for: " + user);
        driver.findElement(By.id("shopping_cart_container")).click();
    }
    public static void removeItem()
    {
        //Tests removing an item from the cart and checks for proper update of the cart content.
    }
    public static void updateCartBadge()
    {
        //Verifies the cart badge reflects the real number of added items after adding or removing.
    }
    public static void cartPresent()
    {
        //Ensures cart contents remain saved when navigating between pages (e.g., Products → Cart → Products).
    }
}
