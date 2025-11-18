package features;
import features.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static features.BaseClass.driver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static features.BaseClass.driver;
import static features.BaseClass.login;
import static features.Checkout.finishOrder;
import static features.Checkout.stepOne;
import static features.Products.addSingleItem;
import static features.Cart.viewCart;

public class Order {
    public static boolean confirmationDisplay()
    {
        // 1. Check Title
        boolean titleCorrect = isElementPresent(driver,
                By.xpath("//span[@class='title' and text()='Checkout: Complete!']"));

        // 2. Check Success Message
        boolean messageCorrect = isElementPresent(driver,
                By.xpath("//h2[text()='Thank you for your order!']"));

        // 3. Check Illustration Image
        boolean imagePresent = isElementPresent(driver,
                By.cssSelector("img.pony_express"));

        // 4. All must be present
        return titleCorrect && messageCorrect && imagePresent;
    }
    public void backHomeNavig()
    {
        addSingleItem();
        viewCart();
        stepOne();
        finishOrder();
        driver.findElement(By.id("back-to-products")).click();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "inventory did not open!"
        );
    }
    public static boolean isElementPresent(WebDriver driver, By by) {
        List<WebElement> elements = driver.findElements(by);
        return !elements.isEmpty();
    }
}
