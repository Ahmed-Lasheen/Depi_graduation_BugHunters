package features;

import features.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static features.BaseClass.driver;

public class Cart extends BaseClass {
    public static void viewCart(String user)
    {
        login(user);
        System.out.println("Testing cart for: " + user);
        driver.findElement(By.id("shopping_cart_container")).click();
    }
    public static void removeItem()
    {
        //Tests removing an item from the cart and checks for proper update of the cart content.
    }
    public static boolean verifyCartBadge()
    {
        int expected = CartState.get();

        int actual = 0;
        boolean exists = isElementPresent(driver, By.className("shopping_cart_badge"));

        if (exists) {
            try {
                actual = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText().trim());
            } catch (Exception e) {
                actual = 0;
            }
        }

        return actual == expected;
    }


    public static boolean isElementPresent(WebDriver driver, By by) {
        List<WebElement> elements = driver.findElements(by);
        return !elements.isEmpty();
    }
}
