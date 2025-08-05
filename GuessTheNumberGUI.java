import javax.swing.*;
import java.util.Random;

public class GuessTheNumberGUI {
    static int totalScore = 0;

    public static void main(String[] args) {
        int rounds = getNumber("🎮 Welcome to Guess the Number!\nHow many rounds do you want to play?");

        for (int i = 1; i <= rounds; i++) {
            playRound(i);
        }

        JOptionPane.showMessageDialog(null, "🏁 Game Over!\nYour total score is: " + totalScore);
    }

    public static void playRound(int round) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 10;
        boolean guessedCorrectly = false;

        while (attempts < maxAttempts) {
            int guess = getNumber("🔢 Round " + round + "\nGuess a number (1-100):\nAttempts left: " + (maxAttempts - attempts));
            attempts++;

            if (guess == numberToGuess) {
                int roundScore = (11 - attempts) * 10;
                totalScore += roundScore;
                JOptionPane.showMessageDialog(null, "🎉 Correct! You guessed it in " + attempts + " attempts.\n🏆 Score: " + roundScore);
                guessedCorrectly = true;
                break;
            } else if (guess < numberToGuess) {
                JOptionPane.showMessageDialog(null, "📉 Too low!");
            } else {
                JOptionPane.showMessageDialog(null, "📈 Too high!");
            }
        }

        if (!guessedCorrectly) {
            JOptionPane.showMessageDialog(null, "❌ Out of attempts!\nThe correct number was: " + numberToGuess + "\n💔 Score: 0");
        }
    }

    public static int getNumber(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) {
                System.exit(0); // Exit if user cancels
            }
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 100 || message.contains("round")) {
                    return number;
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ Please enter a number between 1 and 100.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "⚠️ Invalid input. Please enter a valid number.");
            }
        }
    }
}
