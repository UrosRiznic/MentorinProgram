/*
 *  It ensures a class to only have one instance, it provides point of access to it.
 *  
 *  When to use it? 
 *          - When we need to ensure only one instance to a well-known access point.
 *          - Usually for database connections, cashing, driver objects, thread pooling...
 * 
 * 
 *  There are 2 types of Singleton Initializations
 *          - Early init    ( class is initilized wheter it is used or not )
 *          - Lazy init   ( Initilized only when required. )
 * 
 *  
 *  Important thing is Static atribute (Static means that atribute is shared across the all instances. It is not unique to each of them)
 *  private static Singleton instance;
 * 
 * 
 *  Also important thing is private constructor. It ensures that class has control over its instantiation process.
 * class Singleton {
 *      private Singleton(){}
 * }
 * 
 *  A crucial aspect of the Singleton pattern is the presence of a static factory method. 
 *  This method acts as a gateway, providing a global point of access to the Singleton object. 
 *  When someone requests an instance, this method either creates a new instance (if none exists) or returns the existing instance to the caller.
 
    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    
 * 
 */


 // LAZY METHOD --- NOT GOOD FOR THREADING
 import java.io.*;

 class Singleton { 
    private static Singleton instance; 

    private Singleton(){
        System.out.println("Singleton is instantionated.");
    }

    public static Singleton getInstance(){
        // Checking if instance is alredy made somewhere. If not (null) than make a NEW one.
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    public static void doSoemthing(){
        System.out.println("Something is done.");
    }
 }

 class MainClass{
    public static void main(String[] args){
        Singleton.getInstance().doSoemthing();
    }
 }

/*
 *  THIS IS EXAMPLE OF SYNCHRONIZED GETINSTANCE. 
 *  THIS ENSURES THAT ONLY ONE THREAD CAN EXECUTE GETINSTANCE() AT THE TIME.
 *  IT IS QUITE EXPENSIVE COSTLY AND MAY SLOW DOWN THE PROGRAM. BUT FOR SMALL APPS IS GOOD.
 *
 * 
 *  // Only one thread can execute this at a time
    public static synchronized Singleton getInstance()
    {
        if (obj == null)
            obj = new Singleton();
        return obj;
    }
 * 
 */

//  SAFEST WAY FOR THREADING  -- DOUBLE CHECKED LOCKING
// 1. we check if the instance is alredy created (without locking it)
// 2. If instance is not created, we enter here and lock it
// 3. Second check, inside sync block we check again if instance is created (because some other thread could create it, while this one was locking it.)
// 4. Creating the instance .

// volatile is udes when threads have constant access to variable and they change it. 

class DoubleCheckedSingleton{
    private static volatile DoubleCheckedSingleton object = null;
    private DoubleCheckedSingleton(){}

    public static DoubleCheckedSingleton getInstance(){
        if (object == null) {
            synchronized (DoubleCheckedSingleton.class){  // this is the part that LOCKS 
                if (object == null){
                    object = new DoubleCheckedSingleton();
                }
            } 
        }
        return object;
    }
}

// --------------------------------------------------------------------

/*
 * 
 *  Advantages of Singleton Method Design Pattern:
-Solves Name Collisions: In scenarios where a single point of control is needed to avoid naming conflicts or collisions, the Singleton pattern ensures that there is only one instance with a unique name.
-Eager or Lazy Initialization: The Singleton pattern supports both eager initialization (creating the instance when the class is loaded) and lazy initialization (creating the instance when it is first requested), providing flexibility based on the use case.
-Thread Safety: Properly implemented Singleton patterns can provide thread safety, ensuring that the instance is created atomically and that multiple threads do not inadvertently create duplicate instances.
-Reduced Memory Footprint: In applications where resource consumption is critical, the Singleton pattern can contribute to a reduced memory footprint by ensuring that there is only one instance of the class.
    
    Disadvantages of Singleton Design Pattern
-Testing Difficulties: Because Singletons introduce global state, unit testing can become challenging. Testing one component in isolation may be more complicated if it relies on a Singleton, as the state of the Singleton may affect the outcome of tests.
-Concurrency Issues: In a multi-threaded environment, there can be issues related to the creation and initialization of the Singleton instance. If multiple threads attempt to create the Singleton simultaneously, it can result in race conditions.
-Limited Extensibility: The Singleton pattern can make code less extensible. If you later decide that you need multiple instances of the class or want to change the instantiation logic, it may require significant refactoring.
-Global Dependency: The Singleton pattern creates a global dependency, making it harder to replace the Singleton with an alternative implementation or to use dependency injection for providing instances.
-Hard to Subclass: Subclassing a Singleton can be challenging. Because the constructor is typically private, extending a Singleton requires additional care and may not follow standard inheritance patterns.
-Lifecycle Management: The Singleton pattern may not handle scenarios where the instance needs to be explicitly destroyed or reset. Managing the lifecycle of the Singleton can become a concern.
-Global Access Point Abuse: While a global access point is an advantage, it can also be abused. Developers might be tempted to use the Singleton for everything, leading to an overuse of global state and a less modular design.
 */
