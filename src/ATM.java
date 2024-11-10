import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private static HashMap<String, User> users = new HashMap<>();

    static {
        // Predefined users for testing
        users.put("Faheem", new User("Faheem", "7764", 20000.0));
        users.put("Moola", new User("Moola", "7765", 25000.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User loggedInUser = null;

        // Login logic loop
        while (true) {
            if (!loggedIn) {
                System.out.print("Enter user ID: ");
                String userId = scanner.next();
                System.out.print("Enter PIN: ");
                String pin = scanner.next();

                User user = authenticate(userId, pin);
                if (user != null) {
                    loggedInUser = user;
                    ATMInterface atmInterface = new ATMInterface(user);
                    boolean loggedOut = atmInterface.start();  // Get the result of the start method
                    if (loggedOut) {
                        System.out.println("Returning to login screen...");
                        loggedIn = false;  // Log out and return to login
                    }
                } else {
                    System.out.println("Invalid user ID or PIN. Access denied.");
                }
            }
        }
    }

    private static User authenticate(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public static User getUserById(String userId) {
        return users.get(userId);
    }
}