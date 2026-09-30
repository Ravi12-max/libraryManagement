# 🎯 Number Guessing Game (Java)

A simple command-line number guessing game written in Java. The program picks a random number between 1 and 100, and you try to guess it. After every guess you get a hint telling you whether the secret number is higher or lower.

## Features

- Random secret number between **1 and 100**
- **5 attempts per round**
- Hints after each wrong guess ("greater than" / "less than")
- After each round you can choose to keep guessing or quit
- Total attempt count is shown when you win
- The correct number is revealed if you decide to give up

## How to Play

1. Run the program.
2. Enter a number between 1 and 100 when prompted.
3. Use the hint to adjust your next guess.
4. After 5 wrong guesses, choose `yes` to play another round of 5 attempts or `no` to end the game.
5. Guess correctly to win!

## Example Output

```
Choose the number between 1 and 100: 
You have 5 attempts per round to guess the correct number.
Enter your guess number.50
The number is greater than 50
Enter your guess number.75
The number is less than 75
Enter your guess number.62
Congratulations! You guessed the correct number in 3 attempts.
```

## Project Structure

```
.
└── Game
    └── NumberGuess.java
```

## Requirements

- **JDK 25 or later** (the class uses a non-public `static void main`, which is allowed in recent Java versions)
- JDK 21–24 also works if you run with preview features enabled (see below)

> **Tip:** For compatibility with any Java version, change the entry point to
> `public static void main(String[] args)`.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### 2. Compile

```bash
javac Game/NumberGuess.java
```

### 3. Run

```bash
java Game.NumberGuess
```

**On JDK 21–24** (preview features required):

```bash
javac --enable-preview --release 21 Game/NumberGuess.java
java --enable-preview Game.NumberGuess
```

(Replace `21` with your JDK version.)

## How It Works

- `Math.random()` generates the secret number (1–100).
- A `for` loop gives the player `k = 5` guesses per round.
- An outer `while` loop repeats rounds until the number is guessed or the player chooses to stop.
- `Scanner` reads the player's input from the console.

## Known Limitations / Ideas for Improvement

- Entering non-numeric input throws an `InputMismatchException`. Adding input validation would make the game more robust.
- The attempt counter keeps accumulating across rounds rather than resetting.
- Possible enhancements:
    - Difficulty levels (different ranges and attempt limits)
    - Score tracking / high scores
    - "Play again" option after a win
    - Unit tests and a build tool (Maven/Gradle)

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to fork the repo and open a pull request.

## License

This project is open source and available under the [MIT License](LICENSE).