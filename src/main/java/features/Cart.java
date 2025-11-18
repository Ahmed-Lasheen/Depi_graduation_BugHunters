package features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static features.Products.addMultipleItems;

public class Cart extends BaseClass
{
    public void viewCart()
    {
        driver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart.html"),
                "Cart page did NOT open!"
        );
    }

    public void removeItem(String productID)
    {
        driver.findElement(By.id(productID)).click();
        boolean removed = driver.findElements(By.id(productID)).isEmpty();
        Assert.assertTrue(removed, "Item with ID '" + productID + "' was NOT removed!");
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
