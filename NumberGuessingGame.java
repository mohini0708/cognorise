import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int targetNumber = random.nextInt(10);
        int userGuess = 0;
        int numberOfTries = 0;
        int maxAttempts = 5;  // Set maximum number of attempts

        System.out.println("Welcome to the Enhanced Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 10. You have " + maxAttempts + " attempts to guess it!");

        while (userGuess != targetNumber && numberOfTries < maxAttempts) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();
            numberOfTries++;

            if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the correct number.");
                System.out.println("It took you " + numberOfTries + " tries.");
                break;
            }

            if (numberOfTries == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + targetNumber + ".");
            }
        }

        scanner.close();
    }
}
