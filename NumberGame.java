import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private int lowerBound;
    private int upperBound;
    private int numberToGuess;
    private int maxTries;
    private int numberOfTries;
    private int roundsWon;
    private int totalAttempts;

    public NumberGame(int lowerBound, int upperBound, int maxTries) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.maxTries = maxTries;
        this.numberOfTries = 0;
        this.roundsWon = 0;
        this.totalAttempts = 0;
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Game!");
        System.out.println("I've selected a random number between " + lowerBound + " and " + upperBound + ". Try to guess it.");
        System.out.println("You have a maximum of " + maxTries + " attempts.");

        int guess;
        boolean playAgain = true;

        while (playAgain) {
            numberOfTries = 0;

            while (numberOfTries < maxTries) {
                System.out.print("Attempt " + (numberOfTries + 1) + ": Enter your guess: ");
                guess = scanner.nextInt();
                numberOfTries++;
                totalAttempts++;

                if (guess < numberToGuess) {
                    System.out.println("Try a higher number.");
                } else if (guess > numberToGuess) {
                    System.out.println("Try a lower number.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + numberToGuess + " in " + numberOfTries + " tries.");
                    roundsWon++;
                    break;
                }
            }

            if (numberOfTries == maxTries) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Rounds won: " + roundsWon);
            System.out.println("Total attempts: " + totalAttempts);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");

            if (playAgain) {
                generateRandomNumber();
                System.out.println("Let's play another round!");
            } else {
                System.out.println("Thanks for playing!");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        NumberGame game = new NumberGame(1, 100, 5); // Set the lower and upper bounds and the maximum number of tries as desired
        game.play();
    }
}
