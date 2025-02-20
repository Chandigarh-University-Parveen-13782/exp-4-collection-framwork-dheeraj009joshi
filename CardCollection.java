import java.util.*;

class Card {
    String symbol;
    String name;

    Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Symbol: " + symbol + ", Name: " + name;
    }
}

public class CardCollection {
    static Map<String, List<Card>> cardMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Card Symbol: ");
        String symbol = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Card Name: ");
        String name = scanner.nextLine();
        
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(new Card(symbol, name));
        System.out.println("Card added successfully!");
    }

    public static void findCardsBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.next();
        
        List<Card> cards = cardMap.get(symbol);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for the given symbol.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public static void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (List<Card> cards : cardMap.values()) {
                for (Card card : cards) {
                    System.out.println(card);
                }
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addCard();
                case 2 -> findCardsBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
