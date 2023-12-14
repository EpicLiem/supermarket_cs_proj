import java.util.*;

public class Customer {
    String name;
    double money;
    List<Item> cart;
    double debt;

    double interest;

    public Customer(String name1, double money1) {
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

    public double getDebt() {
        return debt;
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

    public void addDebt(double amount) {
        debt += amount;
        debt = Math.round(debt *100)/100.0;
    }

    public void updateDebt() {
        debt += debt * interest;
        debt = Math.round(debt * 100) / 100.0;
    }

    public void setInterest(double interest1) {
        interest = interest1;
    }

    public double getTotal() {
        double price = 0;
        for (Item item : cart) {
            price += item.price * item.quantity;
        }
        return Math.round(price *100)/100.0;
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
