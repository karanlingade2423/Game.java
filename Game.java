import java.util.Random;
import java.util.Scanner;

class Game {
    protected int secretNumber;
    protected int attempts;
    protected int maxAttempts;

    public Game(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    protected int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    protected int getGuessFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    protected void displayResult() {
        if (attempts >= maxAttempts) {
            System.out.println("Sorry, you have reached the maximum number of attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
        } else {
            System.out.println("Congratulations! You guessed the number " + secretNumber + " correctly.");
            System.out.println("You took " + attempts + " attempts.");
        }
    }
}

class NumberGuessingGame extends Game {
    public NumberGuessingGame(int maxAttempts) {
        super(maxAttempts);
    }

    public void playGame() {
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            System.out.println("I'm thinking of a number between 1 and 100.");
            secretNumber = generateRandomNumber(1, 100);
            attempts = 0;

            while (attempts < maxAttempts) {
                int guess = getGuessFromUser();
                attempts++;

                if (guess == secretNumber) {
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try guessing a higher number.");
                } else {
                    System.out.println("Too high! Try guessing a lower number.");
                }
            }

            displayResult();

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");

            System.out.println();
        }

        System.out.println("Thank you for playing!");
    }
}

public class NumberGame {
    public static void main(String[] args) {
        int maxAttempts = 5;
        NumberGuessingGame game = new NumberGuessingGame(maxAttempts);
        game.playGame();
    }
}