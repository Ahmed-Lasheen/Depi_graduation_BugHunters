package features;

import org.openqa.selenium.By;
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

    public void updateCartBadge(String user) {
        // TODO: Verify cart badge matches actual number of items
    }

    public void cartPresent(String user) {
        // TODO: Verify cart persists after navigating between pages
    }
}
