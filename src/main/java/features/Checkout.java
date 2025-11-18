package features;

public class Checkout {
    public static void stepOne()
    {
        //Tests proceeding through checkout step one using valid customer information.
    }
    public static void missingInfo()
    {
        //Ensures proper validation messages appear when required fields (name/zip) are empty.
    }
    public static void OverviewValid()
    {
        //Validates that item list, prices, subtotal, tax, and final total are shown correctly before finishing checkout.
    }
    public static void finishOrder()
    {
        //Confirms that clicking “Finish” completes the order and redirects to the confirmation page.
    }
}
