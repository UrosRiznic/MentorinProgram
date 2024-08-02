package DBConnection_Example;

// Singleton class for Database Connection
public class DB {
    // Private atribute that saves 1 instance
    private static DB instance;
    
    // Private constructor
    private DB() {
        // HERE WOULD BE A CODE TO CONNECT TO DATABASE
        System.out.println("Connection to database established.");
    }

    // Public static method for getting instance of this class
    public static synchronized DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    // Simulation of some Query execution
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}