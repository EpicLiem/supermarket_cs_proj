// wanted to do a full terminal gui, but didn't feel like learning the lower-level system.out methods, or figure out external libraries

import java.awt.*;
import java.util.*;

public class Display {
    Store[] stores;
    Customer customer;
    int state; // 0: Choose Store 1: Viewing Store 2: View Cart 3: Make Money
    Store currentStore;

    public Display(Store[] stores1, Customer customer1) {
        stores = stores1;
        customer = customer1;
        state = 0;
    }

    public void Update() {
        Scanner input = new Scanner(System.in);
        String choice = null;
        customer.updateDebt();
        customer.addMoney(customer.getDebt() * -0.05);
        if (customer.getMoney() < 0) {
            System.out.println("You are bankrupt! Game Over!");
            System.exit(0);
        }
        customer.addDebt(customer.getDebt()* -0.05);
        System.out.println("I. Change Stores, O. View Cart, P. Make money, ;. Loans | Money: " + customer.getMoney() + " | Debt: " + customer.getDebt());
        switch (state) {
            case 0:
                System.out.println("Select a Store");
                for (int i = 0; i < stores.length; i++) {
                    System.out.println((i + 1) + ". " + stores[i].getName());
                }
                System.out.print("Choice: ");
                choice = input.nextLine();
                try {
                    int itemChoice = Integer.parseInt(choice);
                    if (itemChoice > stores.length || itemChoice < 1) {
                        System.out.println("Error 404: Please select a valid item");
                        System.out.print("Hit Enter to Reload: ");
                        input.nextLine();
                        this.Update();
                    } else {
                        currentStore = stores[itemChoice - 1];
                        state = 1;
                    }
                    this.Update();
                } catch (Exception e) {
                    Menu(choice);
                }
                break;

            case 1:
                System.out.println("Current Store's Items");
                for (int i = 0; i < currentStore.getItems().length; i++) {
                    System.out.println((i + 1) + ". " + currentStore.getItems()[i].toString());
                }
                System.out.print("Choice: ");
                choice = input.nextLine();
                try {
                    int itemChoice = Integer.parseInt(choice);
                    if (itemChoice > currentStore.getItems().length || itemChoice < 1) {
                        System.out.println("Error 404: Please select a valid item");
                        System.out.print("Hit Enter to Reload: ");
                        input.nextLine();
                        this.Update();
                    } else {
                        customer.addItem(currentStore.getItems()[itemChoice - 1]);
                    }
                    this.Update();
                } catch (Exception e) {
                    Menu(choice);
                }
                break;
            case 2:
                System.out.println("Cart");
                if (customer.getCart().isEmpty()) {
                    System.out.println("Cart Empty!");
                } else{
                    for (int i = 0; i < customer.getCart().size(); i++) {
                        System.out.println((i + 1) + ". " + customer.getCart().get(i).toString() + " x" + customer.getCart().get(i).quantity);
                    }
                }
                System.out.println("Total: $" + customer.getTotal());
                System.out.print("Choice (C to checkout/X to clear): ");
                choice = input.nextLine();
                if (choice.equalsIgnoreCase("C")) {
                    if (customer.checkout()) {
                        System.out.println("New balance: " + customer.money);
                        if ((int)(Math.random() * 10) == 1) {
                            System.out.println("\uD83D\uDCA5 You got robbed while leaving the store \uD83D\uDCA5");
                            System.out.println("You lost $" + customer.getMoney() * 0.1 + " and your items!");
                            customer.addMoney(-customer.getMoney() * 0.1);
                        }
                        customer.getCart().clear();
                    } else {
                        System.out.println("Card Error 51: Insufficient funds");
                    }
                } else if (choice.equalsIgnoreCase("X")) {
                    customer.getCart().clear();
                } else {
                    Menu(choice);
                }
                this.Update();
            case 3:
                System.out.println("\uD83C\uDFB0 Slot Machine Extravaganza \uD83C\uDFB0");
                System.out.println("\uD83C\uDF52 Match 3 symbols to win! Special 333 jackpot available! \uD83C\uDF52");
                System.out.println("\uD83D\uDCB0 How much do you want to insert?");
                System.out.print("\uD83D\uDCB0 Amount/Choice: ");
                choice = input.nextLine();
                try {
                    double itemChoice = Double.parseDouble(choice);
                    itemChoice = Math.round(itemChoice * 100) / 100.0;
                    customer.addMoney(-itemChoice);
                    System.out.print("How much do you want to bet?: ");
                    choice = input.nextLine();
                    double bet = 1.00;
                    try {
                        bet = Double.parseDouble(choice);
                        bet = Math.round(bet * 100) / 100.0;
                        if (bet > itemChoice) {
                            System.out.println("Error 51: Insufficient funds");
                            break;
                        }
                        itemChoice -= bet;
                    } catch (Exception e) {
                        Menu(choice);
                    }

                    if (itemChoice <= customer.getMoney() || !(itemChoice < 1)) {
                        boolean playing = true;
                        double jackpot = 10.00;
                        while (playing) {
                            jackpot = Math.round(jackpot * 100) / 100.0;
                            itemChoice = Math.round(itemChoice * 100) / 100.0;
                            itemChoice--;
                            int rand1 = (int) (Math.random() * 3) + 1;
                            int rand2 = (int) (Math.random() * 3) + 1;
                            int rand3 = (int) (Math.random() * 3) + 1;
                            int specialbet = (int) (Math.random() * 50);
                            double winamount = bet * 5.00;
                            System.out.println("Current Jackpot: $" + jackpot * bet);


                            animateNumbers(rand1, rand2, rand3);
                            if (rand1 == rand2 && rand2 == rand3) {
                                if (rand1 == 3) {
                                    if (winamount > 5.00) {
                                        jackpot *= bet;
                                        jackpot = Math.round(jackpot * 100) / 100.0;
                                        System.out.println("🎉🎉🎉 MEGA JACKPOT! You won $" + jackpot + "! 🎉🎉🎉");
                                    } else {
                                        System.out.println("🎉🎉🎉 JACKPOT! You won $" + jackpot + "! 🎉🎉🎉");
                                        itemChoice += jackpot;
                                        jackpot = 10.00;
                                    }
                                } else {
                                    if (winamount > 5.00) {
                                        System.out.println("🎉🎉🎉 SPECIAL WIN! You won $" + winamount + "! 🎉🎉🎉");
                                    } else {
                                        System.out.println("🌟 You won $" + winamount + "! 🌟");
                                    }
                                    itemChoice += winamount;
                                }
                            } else {
                                System.out.println("😔 Better luck next time!");
                                jackpot += 0.10;
                            }
                            System.out.println("Current Balance: " + itemChoice);
                            System.out.print("Play again($" + bet + ")? (Any Input/N): ");
                            choice = input.nextLine();
                            if (choice.equalsIgnoreCase("N")) {
                                playing = false;
                            }
                            if (itemChoice < bet) {
                                playing = false;
                                System.out.println("You don't have enough money to play again!");
                                System.out.print("Hit the amount to Reload: ");
                                choice = input.nextLine();
                                try {
                                    itemChoice = Double.parseDouble(choice);
                                    itemChoice = Math.round(itemChoice * 100) / 100.0;
                                    if (itemChoice < 0) {
                                        System.out.println("Error 404: Please select a valid item");
                                        System.out.print("Hit Enter to Reload: ");
                                        break;
                                    } else if (itemChoice > customer.getMoney()) {
                                        System.out.println("Error 51: Insufficient funds");
                                        break;
                                    }
                                    customer.addMoney(-itemChoice);
                                    playing = true;
                                } catch (Exception e) {
                                    customer.addMoney(itemChoice);
                                    itemChoice = 0;
                                    Menu(choice);
                                }
                            }
                        }
                        customer.addMoney(itemChoice);
                        itemChoice = 0;
                        this.Update();
                    } else {
                        System.out.println("Invalid Input");
                    }
                    this.Update();
                } catch (Exception e) {
                    Menu(choice);
                }
            break;
            case 4:
                System.out.println("\uD83C\uDFE6 IMF Loans \uD83C\uDFE6");
                System.out.print("How much do you want to borrow?(Amount or L to pay):");
                choice = input.nextLine();
                try {
                    double itemChoice = Double.parseDouble(choice);
                    itemChoice = Math.round(itemChoice * 100) / 100.0;
                    customer.addDebt(itemChoice);
                    customer.addMoney(itemChoice);
                    customer.setInterest(0.1);
                    System.out.println("\uD83E\uDD11 You now owe $" + customer.getDebt() + " to the IMF at a 10% interest rate. 5% of the loan is paid off automatically every period. \uD83E\uDD11");
                    this.Update();
                } catch (Exception e) {git
                    if (choice.equalsIgnoreCase("L")) {
                        System.out.println("How much do you want to pay?");
                        System.out.print("Amount: ");
                        choice = input.nextLine();
                        try {
                            double itemChoice = Double.parseDouble(choice);
                            itemChoice = Math.round(itemChoice * 100) / 100.0;
                            if (itemChoice > customer.getMoney()) {
                                System.out.println("Error 51: Insufficient funds");
                                break;
                            }

                            customer.addMoney(-itemChoice);
                            customer.addDebt(-itemChoice);
                            if (customer.getDebt() <= 0) {
                                customer.setInterest(0);
                                customer.addDebt(-customer.getDebt());
                            }
                            System.out.println("You now owe $" + customer.getDebt() + " to the IMF at a 10% interest rate. 5% of the loan is paid off automatically every period.");
                            this.Update();
                        } catch (Exception e1) {
                            Menu(choice);
                        }
                    } else {
                        Menu(choice);
                    }
                }



        }
    }

    private static void animateNumbers(int number1, int number2, int number3) throws InterruptedException {
        int[] arr = {number1, number2, number3};
        System.out.print("\uD83C\uDFB0 ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 3 - i; k++) {
                    System.out.print((int)(Math.random() * 3) + 1);
                    System.out.print(" ");
                }
                Thread.sleep((long) ((double)(Math.random() * 2)));
                for (int k = 0; k < 3 - i; k++) {
                    System.out.print("\b\b");
                }
                System.out.flush();
            }
            System.out.print(arr[i] + " ");
            System.out.flush();
        }
        System.out.println("\uD83C\uDFB0");
        System.out.flush();
        Thread.sleep(500);
    }

    private void Menu(String choice) {
        Scanner input = new Scanner(System.in);
        switch (choice.toUpperCase()) {
            case "I":
                state = 0;
                break;
            case "O":
                state = 2;
                break;
            case "P":
                state = 3;
                break;
            case ";":
                    state = 4;
                break;
            default:
                System.out.println("Error 404: Unknown option");
                System.out.print("Hit Enter to Reload: ");
                input.nextLine();
                this.Update();
        }
        this.Update();
    }
}
