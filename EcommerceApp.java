import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class ShoppingCart {
    List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product item : items) {
            total += item.price;
        }
        return total;
    }
}

class User {
    String username;
    String password;
    ShoppingCart cart = new ShoppingCart();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class EcommerceApp {
    static List<Product> productList = new ArrayList<>();
    static List<User> userList = new ArrayList<>();
    static User currentUser;

    public static void main(String[] args) {
        initializeProducts();
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    if (currentUser != null) {
                        addToCart(scanner);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        viewCart();
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        checkout();
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 5:
                    loginUser(scanner);
                    break;
                case 6:
                    createUser(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeProducts() {
        productList.add(new Product("Product 1", 19.99));
        productList.add(new Product("Product 2", 29.99));
        productList.add(new Product("Product 3", 39.99));
    }

    private static void initializeUsers() {
        userList.add(new User("Ajay", "A@123"));
        userList.add(new User("Vijay", "V@123"));
    }
    private static void displayMenu() {
        System.out.println("\n=== E-Commerce Menu ===");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Log In");
        System.out.println("6. Create Account");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayProducts() {
        System.out.println("\n=== Products ===");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println((i + 1) + ". " + productList.get(i).name + " - $" + productList.get(i).price);
        }
    }

    private static void addToCart(Scanner scanner) {
        displayProducts();
        System.out.print("Enter the product number to add to your cart: ");
        int productNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (productNumber >= 1 && productNumber <= productList.size()) {
            Product selectedProduct = productList.get(productNumber - 1);
            currentUser.cart.addItem(selectedProduct);
            System.out.println(selectedProduct.name + " added to your cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static void viewCart() {
        System.out.println("\n=== Shopping Cart ===");
        for (Product item : currentUser.cart.items) {
            System.out.println(item.name + " - $" + item.price);
        }
        System.out.println("Total: $" + currentUser.cart.calculateTotal());
    }

    private static void checkout() {
        System.out.println("\n=== Checkout ===");
        viewCart();
        System.out.println("Thank you for your purchase!");
        currentUser.cart.items.clear();
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : userList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Welcome, " + currentUser.username + "!");
                return;
            }
        }

        System.out.println("Invalid username or password,Please try again.");
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userList.add(new User(username, password));
        System.out.println("Account created successfully. You can now log in.");
    }
}
