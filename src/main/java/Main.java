package features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Main extends BaseClass {
//***********************************************view cart scenarios********************************
    @Test(dataProvider = "usersProvider", testName = "Cart Page Opens Successfully")
    public void TC_ViewCartOpens(String user)
    {
        login(user);

        Cart cart = new Cart();
        cart.viewCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"),
                "Cart page did NOT load correctly!");
    }

    @Test(dataProvider = "usersProvider", testName = "Cart Header Displayed Correctly")
    public void TC_ViewCartHeader(String user) {
        login(user);

        Cart cart = new Cart();
        cart.viewCart();

        String header = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(header, "Your Cart", "Incorrect cart header!");
    }

    @Test(dataProvider = "usersProvider", testName = "Empty Cart Behavior")
    public void TC_EmptyCart(String user) {
        login(user);

        Cart cart = new Cart();
        cart.viewCart();
        List<WebElement> items = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(items.size(), 0, "Cart should be EMPTY but items found!");
    }

    @Test(dataProvider = "usersProvider", testName = "View Cart With Multiple Items")
    public void TC_ViewCartWithItems(String user) {
        login(user);

        Products products = new Products();
        products.addMultipleItems();

        Cart cart = new Cart();
        cart.viewCart();

        List<WebElement> items = driver.findElements(By.className("cart_item"));
        Assert.assertTrue(items.size() > 0, "No items found in cart after adding them!");
    }

    /* ---------------- REMOVE ITEM SCENARIOS ---------------- */

    @Test(dataProvider = "usersProvider", testName = "Remove Single Item From Cart")
    public void TC_RemoveSingleItem(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addSingleItem();
        cart.viewCart();

        // Removes whichever item was added
        driver.findElement(By.cssSelector("button[id^='remove']")).click();

        boolean empty = driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(empty, "Single item was NOT removed!");
    }

    @Test(dataProvider = "usersProvider", testName = "Remove All Items From Cart")
    public void TC_RemoveAllItems(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addMultipleItems();
        cart.viewCart();

        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[id^='remove']"));

        for (WebElement button : removeButtons) {
            button.click();
        }

        boolean empty = driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(empty, "Cart is NOT empty after removing all items!");
    }

    @Test(dataProvider = "usersProvider", testName = "Cart badge updates correctly")
    public void TC_CartBadgeBehavior(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addMultipleItems();
        cart.viewCart();

        int before = driver.findElements(By.className("cart_item")).size();
        driver.findElement(By.cssSelector("button[id^='remove']")).click();
        int after = driver.findElements(By.className("cart_item")).size();
        Assert.assertEquals(after, before - 1);
        List<WebElement> badge = driver.findElements(By.className("shopping_cart_badge"));
        if (after == 0) {
            Assert.assertTrue(badge.isEmpty(), "Badge should disappear when cart becomes 0!");
        } else {
            Assert.assertFalse(badge.isEmpty(), "Badge missing even though cart is not empty!");
            int badgeNumber = Integer.parseInt(badge.get(0).getText());
            Assert.assertEquals(badgeNumber, after,
                    "Badge count does not match actual number of cart items!");
        }
    }


    @Test(dataProvider = "usersProvider", testName = "Cart Items Persist After Navigation")
    public void TC_CartPersistsAfterNavigation(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addSingleItem();
        cart.viewCart();

        driver.navigate().back();
        driver.navigate().forward();

        boolean present = !driver.findElements(By.className("cart_item")).isEmpty();
        Assert.assertTrue(present, "Cart items disappeared after navigation!");
    }



    @Test(dataProvider = "usersProvider", testName = "Continue Shopping Button Works")
    public void TC_ContinueShoppingWorks(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addSingleItem();
        cart.viewCart();

        driver.findElement(By.id("continue-shopping")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Continue shopping button did NOT return to inventory!");
    }

    @Test(dataProvider = "usersProvider", testName = "Checkout Button Navigates to Step One")
    public void TC_CheckoutButtonFromCart(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();

        products.addSingleItem();
        cart.viewCart();

        driver.findElement(By.id("checkout")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Checkout button did NOT navigate to Step One!");
    }
    @Test(dataProvider = "usersProvider", testName = "Finish Order Successfully")
    public void TC_FinishOrderSuccessfully(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();
        Checkout checkout = new Checkout();

        products.addSingleItem();
        cart.viewCart();
        checkout.stepOne();
        checkout.finishOrder();

        String confirmationMsg = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(
                confirmationMsg,
                "Thank you for your order!",
                "Order completion message is incorrect!"
        );
    }


    @Test(dataProvider = "usersProvider", testName = "Back Home Button Returns to Inventory")
    public void TC_BackHomeNavigation(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();
        Checkout checkout = new Checkout();

        products.addSingleItem();
        cart.viewCart();
        checkout.stepOne();
        checkout.finishOrder();

        driver.findElement(By.id("back-to-products")).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Back Home button did NOT return to inventory!"
        );
    }

//************************************* confirm order *************************************************
    @Test(dataProvider = "usersProvider", testName = "Order Confirmation Image Displays Correctly")
    public void TC_ConfirmationImage(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();
        Checkout checkout = new Checkout();

        products.addSingleItem();
        cart.viewCart();
        checkout.stepOne();
        checkout.finishOrder();

        boolean imageVisible = driver.findElement(By.className("pony_express")).isDisplayed();

        Assert.assertTrue(
                imageVisible,
                "Confirmation image is NOT displayed on the final order screen!"
        );
    }


    @Test(dataProvider = "usersProvider", testName = "Checkout Complete Page Header Visible")
    public void TC_ConfirmationHeaderVisible(String user) {
        login(user);

        Products products = new Products();
        Cart cart = new Cart();
        Checkout checkout = new Checkout();

        products.addSingleItem();
        cart.viewCart();
        checkout.stepOne();
        checkout.finishOrder();

        String header = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(header, "Checkout: Complete!", "Header text is incorrect!");
    }

}
