public class Item {
    String name;
    Double price;
    int quantity;

    public Item(String name1, Double price1) {
        name = name1;
        price = price1;
        quantity = 1;
    }

    public Item(String name1, Double price1, int quantity1) {
        name = name1;
        price = price1;
        quantity = quantity1;
    }

    public String toString() {
        return name + " : $" + price;
    }
}
