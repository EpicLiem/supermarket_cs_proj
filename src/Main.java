public class Main {
    public static void main(String[] args) {
        // Used Chatgpt to generate the stores (I can put them in manually if you want)
        // Expanding the range of items in each store

// Home Depot store with additional items
        Store homeDepot = new Store("Home Depot", new Item[]{
                new Item("Paint (1 gallon)", 24.99),
                new Item("Cordless Drill", 129.99),
                new Item("LED Light Bulbs (4 pack)", 14.99),
                new Item("Hammer", 15.99),
                new Item("Garden Hose", 22.99),
                new Item("Wood Planks", 5.99)
        });

// Best Buy store with additional items
        Store bestBuy = new Store("Best Buy", new Item[]{
                new Item("Laptop", 799.99),
                new Item("Smartphone", 999.99),
                new Item("Headphones", 59.99),
                new Item("Smart TV (55 inch)", 549.99),
                new Item("Bluetooth Speaker", 39.99),
                new Item("Digital Camera", 299.99)
        });

// Target store with additional items
        Store target = new Store("Target", new Item[]{
                new Item("T-shirt", 9.99),
                new Item("Coffee Maker", 29.99),
                new Item("Board Game", 19.99),
                new Item("Bath Towel", 12.99),
                new Item("Table Lamp", 35.99),
                new Item("Kitchen Utensils", 24.99)
        });

        Customer customer = new Customer("User", 100000.0);

        Display display = new Display(new Store[]{homeDepot, bestBuy, target}, customer);
        display.Update();
    }
}