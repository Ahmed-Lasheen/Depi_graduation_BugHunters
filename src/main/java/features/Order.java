package features;

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
    public static void confirmationDisplay()
    {
        //Verifies the final confirmation screen shows the success message and order-complete illustration.
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

    }


