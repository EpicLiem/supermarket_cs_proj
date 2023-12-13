import java.util.*;

public class Customer {
    String name;
    Double money;
    List<Item> cart;

    public Customer(String name1, Double money1) {
        money = money1;
        name = name1;
        cart = new ArrayList<Item>();
    }

    public double getMoney() {
        return money;
    }
    public List<Item> getCart() {
        return cart;
    }

    public void addMoney(double amount) {
        money += amount;
    }
    public void addItem(Item item) {
        if (cart.contains(item)) {
            cart.get(cart.indexOf(item)).quantity++;
        } else {
            cart.add(item);
        }
    }

    public double getTotal() {
        double price = 0;
        for (Item item : cart) {
            price += item.price * item.quantity;
        }
        return Math.round(price *100)/100.0 ;
    }
    public boolean checkout() {
        double total = this.getTotal();

        if (total > money) {
            return false;
        } else {
            money -= total;
            cart.clear();
            return true;
        }
    }
}
