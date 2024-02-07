import java.util.Scanner;
import java.util.Random;

public class SimpleNumberGuessingGame {
    public static void main(String[] args) {
        playGame();
    }

    static void playGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            int secretNumber = random.nextInt(100) + 1; // Random number between 1 and 100
            int maxAttempts = 10;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Try to guess the number between 1 and 100.");

            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                System.out.print("Attempt " + attempts + ": Enter your guess: ");

                // Validate user input
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Consume the invalid input
                }

                int userGuess = scanner.nextInt();

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    return;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + secretNumber + ".");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
