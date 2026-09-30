package NumberGusessing;

import java.util.Scanner;

public class NumberGuess {
    public void numberGuess(){
        Scanner sc = new Scanner(System.in);
        int number = 1 + (int)(Math.random() * 100);
        int attempt = 0;
        boolean isGuessCorrectly = false;
        int k = 5;
        System.out.println("Choose the number between 1 and 100: ");
        System.out.println(
                "You have " + k
                        + " attempts per round to guess the correct number.");
        while (!isGuessCorrectly){
            for (int i = 1; i <= k; i++){
                System.out.print("Enter your guess number.");
                int guess = sc.nextInt();
                attempt++;
                if (guess == number){
                    System.out.println(
                            "Congratulations! You guessed the correct number in "
                                    + attempt + " attempts.");
                    isGuessCorrectly = true;
                    break;
                } else if (guess < number) {
                    System.out.println(
                            "The number is greater than "
                                    + guess);

                }else{
                    System.out.println(
                            "The number is less than " + guess);
                }
            }
            if (!isGuessCorrectly){
                System.out.println("You have used all " + k
                        + " attempts.");
                System.out.print(
                        "Do you want to continue guessing? (yes/no): ");
                String response = sc.next();

                if (!response.equalsIgnoreCase("yes")) {
                    System.out.println(
                            "Game Over! The correct number was: "
                                    + number);
                    break;
                }
            }
        }
        sc.close();
    }

    static void main(String[] args) {
        NumberGuess a = new NumberGuess();
        a.numberGuess();

    }
}
