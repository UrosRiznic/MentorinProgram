package DBConnection_Example;

// Example of testing Singleton class
public class Main {
    public static void main(String[] args) {
        // Getting instance of Singleton class
        DB connection1 = DB.getInstance();
        connection1.executeQuery("SELECT * FROM users");

        // Trying to get one more instance of this class
        DB connection2 = DB.getInstance();
        connection2.executeQuery("SELECT * FROM products");

        // Checking if both variables point to same instance
        if (connection1 == connection2) {
            System.out.println("Both connections are the same instance.");
        } else {
            System.out.println("Connections are different instances.");
        }
    }
}
