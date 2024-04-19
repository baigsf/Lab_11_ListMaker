import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = getRegExString(scanner, "[AaDdPpQq]");
            switch (choice.toUpperCase()) {
                case "A":
                    addItem(scanner);
                    break;
                case "D":
                    deleteItem(scanner);
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (confirmQuit(scanner)) {
                        System.out.println("Exiting program...");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit the program");
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        list.add(item);
        System.out.println("Item added to the list.");
    }

    private static void deleteItem(Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Current list:");
        printNumberedList();
        int index = getRangedInt(scanner, "Enter the item number to delete: ", 1, list.size()) - 1;
        String removedItem = list.remove(index);
        System.out.println("Item '" + removedItem + "' deleted from the list.");
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Current list:");
        for (String item : list) {
            System.out.println("- " + item);
        }
    }

    private static boolean confirmQuit(Scanner scanner) {
        System.out.print("Are you sure you want to quit? (Y/N): ");
        return getYNConfirm(scanner, "");
    }

    private static void printNumberedList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static String getRegExString(Scanner scanner, String pattern) {
        String input;
        do {
            System.out.print("Enter your choice: ");
            input = scanner.nextLine();
        } while (!input.matches(pattern));
        return input;
    }

    private static boolean getYNConfirm(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().toUpperCase();
        } while (!input.equals("Y") && !input.equals("N"));
        return input.equals("Y");
    }

    private static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // discard non-integer input
                System.out.print(prompt);
            }
            input = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            if (input < low || input > high) {
                System.out.println("Input out of range! Please enter a number between " + low + " and " + high + ".");
            }
        } while (input < low || input > high);
        return input;
    }
}

