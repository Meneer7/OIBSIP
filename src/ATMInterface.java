import java.util.Scanner;

public class ATMInterface {
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private Account account;

    public ATMInterface(User user) {
        this.currentUser = user;
        this.account = new Account(user);
    }

    public boolean start() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Transaction.printTransactionHistory(currentUser);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient user ID: ");
                    String recipientId = scanner.next();
                    User recipientUser = ATM.getUserById(recipientId);  // Find recipient by ID
                    if (recipientUser != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(recipientUser, transferAmount);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    loggedOut = true; // Set loggedOut to true to return to the login screen
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        return loggedOut;  // Return true to indicate that logout happened (to trigger login again)
    }
}
