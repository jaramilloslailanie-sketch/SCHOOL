import java.util.Scanner;

public class PerezEateryOrderingSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] menuNames = {
            "Fried Chicken",
            "Pork Adobo",
            "Beef Sinigang",
            "Vegetable Lumpia",
            "Steamed Rice",
            "Mango Juice"
        };
        double[] menuPrices = {65.00, 55.00, 70.00, 35.00, 15.00, 25.00};

        int totalQuantity = 0;
        double totalBeforeDeduction = 0.0;
        double totalDeduction = 0.0;
        boolean keepOrdering = true;

        while (keepOrdering) {
            System.out.println("\n=====================================");
            System.out.println("          PEREZ EATERY MENU          ");
            System.out.println("=====================================");
            for (int i = 0; i < menuNames.length; i++) {
                System.out.printf("%d. %-18s - PHP %.2f%n",
                                  (i + 1), menuNames[i], menuPrices[i]);
            }
            System.out.println("=====================================");

            System.out.print("\nEnter item number: ");
            int itemNum = input.nextInt();

            System.out.print("Enter quantity (1-10): ");
            int qty = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            String studentStatus = input.next().trim().toUpperCase();

            boolean valid = true;
            if (itemNum < 1 || itemNum > menuNames.length) {
                System.out.println("Invalid item number! Choose from 1 to " + menuNames.length + ".");
                valid = false;
            }
            if (qty < 1 || qty > 10) {
                System.out.println("Invalid quantity! Must be 1–10.");
                valid = false;
            }
            if (!studentStatus.equals("Y") && !studentStatus.equals("N")) {
                System.out.println("Invalid input! Enter Y or N.");
                valid = false;
            }

            if (!valid) {
                System.out.println("Please try again.\n");
                continue;
            }

            int index = itemNum - 1;
            double orderAmount = menuPrices[index] * qty;
            boolean isStudent = studentStatus.equals("Y");

            double deductionRate = 0.0;
            if (isStudent && orderAmount >= 500) {
                deductionRate = 0.15;
            } else if (isStudent) {
                deductionRate = 0.10;
            } else if (orderAmount >= 500) {
                deductionRate = 0.05;
            }

            double orderDeduction = orderAmount * deductionRate;

            totalQuantity += qty;
            totalBeforeDeduction += orderAmount;
            totalDeduction += orderDeduction;

            System.out.printf("Order added: %d x %s%n", qty, menuNames[index]);
            System.out.printf("Amount: PHP %.2f | Deduction: PHP %.2f%n",
                              orderAmount, orderDeduction);

            System.out.print("\nDo you want to order again? (Y/N): ");
            String again = input.next().trim().toUpperCase();
            if (!again.equals("Y")) {
                keepOrdering = false;
            }
        }

        double finalAmount = totalBeforeDeduction - totalDeduction;

        System.out.println("\n==================================================");
        System.out.println("           PEREZ EATERY RECEIPT                 ");
        System.out.println("==================================================");
        System.out.printf("Total items purchased         : %d%n", totalQuantity);
        System.out.printf("Total amount before deductions : PHP %.2f%n", totalBeforeDeduction);
        System.out.printf("Total deductions applied      : PHP %.2f%n", totalDeduction);
        System.out.printf("FINAL AMOUNT TO PAY           : PHP %.2f%n", finalAmount);
        System.out.println("==================================================");
        System.out.println("    Thank you for eating at Perez Eatery!        ");
        System.out.println("==================================================");

        input.close();
    }
}