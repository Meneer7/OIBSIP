public class Transaction {
    public static void printTransactionHistory(User user) {
        System.out.println("Transaction History for user: " + user.getUserId());
        for (String transaction : user.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
