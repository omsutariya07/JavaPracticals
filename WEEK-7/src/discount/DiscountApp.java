package discount;

import java.util.Scanner;

public class DiscountApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = {500, 1000, 1500, 2000};

        System.out.println("Choose discount:");
        System.out.println("1. 10% discount");
        System.out.println("2. 20% discount");
        System.out.println("3. 50% discount");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        if (choice == 1) {
            rule = price -> price - (price * 0.10);
        } 
        else if (choice == 2) {
            rule = price -> price - (price * 0.20);
        } 
        else if (choice == 3) {
            rule = price -> price - (price * 0.50);
        } 
        else {
            System.out.println("Invalid choice");
            sc.close();
            return;
        }

        System.out.println("\nPrices after discount:");

        for (double price : prices) {
            double final_price = rule.apply(price);
            System.out.println(price + " -> " + final_price);
        }

        sc.close();
    }
}