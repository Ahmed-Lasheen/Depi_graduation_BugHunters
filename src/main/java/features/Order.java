package features;
import features.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static features.BaseClass.driver;

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

    public static void backHomeNavig()
    {
        //Ensures that clicking “Back Home” returns the user to the Products page after a completed order.
    }
    public static boolean isElementPresent(WebDriver driver, By by) {
        List<WebElement> elements = driver.findElements(by);
        return !elements.isEmpty();
    }
}
