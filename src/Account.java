public class Account {
    private User user;

    public Account(User user) {
        this.user = user;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            user.addTransaction("Withdraw: -" + String.format("R%.2f", amount));
            System.out.println("Withdraw successful. New balance: " + user.getFormattedBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            user.addTransaction("Deposit: +" + String.format("R%.2f", amount));
            System.out.println("Deposit successful. New balance: " + user.getFormattedBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            // Add transaction for both sender and recipient
            user.addTransaction("Transfer: -" + String.format("R%.2f", amount) + " to " + recipient.getUserId());
            recipient.addTransaction("Transfer: +" + String.format("R%.2f", amount) + " from " + user.getUserId());

            System.out.println("Transfer successful. New balance: " + user.getFormattedBalance());
            // Do not show recipient's balance in sender's account
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
