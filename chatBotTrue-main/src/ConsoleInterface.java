import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput() {
        System.out.print("Твой ответ: ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}