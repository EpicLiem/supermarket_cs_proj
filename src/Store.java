public class Store {
    String name;
    Item[] items;

    public Store(String name1, Item[] items1) {
        name = name1;
        items = items1;
    }

    public Item[] getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
