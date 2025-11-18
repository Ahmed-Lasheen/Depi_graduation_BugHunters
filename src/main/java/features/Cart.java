package features;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static features.Products.addMultipleItems;

public class Cart extends BaseClass {
    public void viewCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart.html"),
                "Cart page did NOT open!"
        );
    }
//scenario 1
    @Test(dataProvider = "usersProvider", testName = "View cart test")
    public void TestViewCart(String user) {
        login(user);
        System.out.println("Testing cart for: " + user);
        viewCart();
    }


    private void removeItem() {

        String[] removeButtons = {
                "remove-sauce-labs-backpack",
                "remove-sauce-labs-bike-light",
                "remove-sauce-labs-bolt-t-shirt",
                "remove-sauce-labs-fleece-jacket",
                "remove-test.allthethings()-t-shirt-(red)",
                "remove-sauce-labs-onesie"
        };

        String[] itemIds = {
                "item_4_title_link",
                "item_0_title_link",
                "item_1_title_link",
                "item_5_title_link",
                "item_3_title_link",
                "item_2_title_link"
        };

        for (int i = 0; i < removeButtons.length; i++) {

            viewCart(); // always open cart before removing

            driver.findElement(By.id(removeButtons[i])).click();

            boolean removed = driver.findElements(By.id(itemIds[i])).isEmpty();

            Assert.assertTrue(
                    removed,
                    "Item with ID '" + itemIds[i] + "' was NOT removed after clicking remove!"
            );
        }
    }

//scenario 2
    @Test(dataProvider = "usersProvider", testName = "Remove from cart test")
    public void TestRemoveItem(String user) {
        login(user);
        System.out.println("Testing Remove from cart for: " + user);

        addMultipleItems();  // add test items first
        removeItem();

        System.out.println("All items removed successfully for: " + user);
    }

    @Test(dataProvider = "usersProvider", testName = "View cart test")
    public void updateCartBadge(String user) {
        // TODO: Verify cart badge matches actual number of items
    }

    @Test(dataProvider = "usersProvider", testName = "View cart test")
    public void cartPresent(String user) {
        // TODO: Verify cart persists after navigating between pages
    }
}
