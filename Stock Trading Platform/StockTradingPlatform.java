import java.util.*;

public class StockTradingPlatform {

    static Scanner sc = new Scanner(System.in);

    static User user = new User("Anushka", 100000);

    static ArrayList<Stock> stocks = new ArrayList<>();

    static HashMap<String, Integer> portfolio = new HashMap<>();

    public static void main(String[] args) {

        stocks.add(new Stock("TCS", 3500));
        stocks.add(new Stock("INFY", 1500));
        stocks.add(new Stock("RELIANCE", 2800));

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewMarket();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    viewPortfolio();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewMarket() {

        System.out.println("\nMarket Data:");

        for (Stock stock : stocks) {
            System.out.println(stock.stockName +
                    " Price: ₹" + stock.price);
        }
    }

    static void buyStock() {

        System.out.print("Enter Stock Name: ");
        String name = sc.next();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        for (Stock stock : stocks) {

            if (stock.stockName.equalsIgnoreCase(name)) {

                double total = stock.price * qty;

                if (user.balance >= total) {

                    user.balance -= total;

                    portfolio.put(name,
                            portfolio.getOrDefault(name, 0) + qty);

                    System.out.println("Stock Purchased!");
                } else {
                    System.out.println("Insufficient Balance!");
                }

                return;
            }
        }

        System.out.println("Stock Not Found!");
    }

    static void sellStock() {

        System.out.print("Enter Stock Name: ");
        String name = sc.next();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        if (portfolio.containsKey(name)
                && portfolio.get(name) >= qty) {

            for (Stock stock : stocks) {

                if (stock.stockName.equalsIgnoreCase(name)) {

                    user.balance += stock.price * qty;

                    portfolio.put(name,
                            portfolio.get(name) - qty);

                    System.out.println("Stock Sold!");
                    return;
                }
            }
        }

        System.out.println("Not Enough Stocks!");
    }

    static void viewPortfolio() {

        System.out.println("\nPortfolio:");

        for (String stock : portfolio.keySet()) {

            System.out.println(stock +
                    " Shares: " +
                    portfolio.get(stock));
        }

        System.out.println("Available Balance: ₹" +
                user.balance);
    }
}
