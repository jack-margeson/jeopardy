// Jeopardy.java
// Jeopardy class written for Jack's Jeopardy.
// Jack Margeson, 05/25/2020.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeopardy {
    // Arrays and variables.

    // Array of players.
    public static ArrayList<Player> players = new ArrayList<>();
    // Scanner, for grabbing user input (System.in)
    public static Scanner scanner = new Scanner(System.in);
    // Status of game.
    // 0 - no game is currently active.
    // 1 - game is currently active.
    // 2 - double jeopardy is currently active.
    public static int game_status = 0;

    public static void main(String[] args) {
        // Display title message.
        System.out.println("This... is... Jeopardy!");

        // Get number of players.
        System.out.print("How many players?: ");
        int num_players = Integer.parseInt(scanner.nextLine());
        System.out.println("Great! " + num_players + " player(s).\n");

        // Get and set player names.
        for (int i = 0; i < num_players; i++) {
            System.out.print("Please enter Player #" + (i + 1) + "'s name: ");
            players.add(new Player(scanner.nextLine()));
        }

        // Print the game rules.
        printRules();

        // Main game loop.
        while (game_status == 1 || game_status == 2) {
            clear();
            printScores();
            printMenu();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> increment();
                case 2 -> decrement();
                case 3 -> dailyDouble();
                case 4 -> game_status = 2;
                case 5 -> finalJeopardy();
                case 6 -> endGame();
            }
        }
    }

    // clear()
    // Clears the screen.
    public static void clear() {
        for(int i=0;i<50; i++){System.out.println("\n");}
    }

    // printMenu();
    // Prints the main game's option menu.
    public static void printMenu() {
        System.out.println("1) Increment (Add points to a player's score.)");
        System.out.println("2) Decrement (Remove points from a player's score.)");
        System.out.println("3) Daily Double (Starts Jeopardy's daily double mode.)");
        System.out.println("4) Double Jeopardy (Enables Double Jeopardy mode.)");
        System.out.println("5) Final Jeopardy (Jeopardy's final round.)");
        System.out.println("6) End Game (Ends the game early, no Final Jeopardy.)\n");
        System.out.print("Choose an option (1-6): ");
    }

    // printValues();
    // Print the point values for Jeopardy.
    public static void printValues() {
        if (game_status == 1) { // normal jeopardy values
            System.out.println("1) $200");
            System.out.println("2) $400");
            System.out.println("3) $600");
            System.out.println("4) $800");
            System.out.println("5) $1000");
        } else if (game_status == 2) { // double jeopardy values
            System.out.println("1) $400");
            System.out.println("2) $800");
            System.out.println("3) $1200");
            System.out.println("4) $1600");
            System.out.println("5) $2000");
        }
    }

    // printRules();
    // Prints the rules for Jeoparty.
    public static void printRules() {
        System.out.println("\nDuring the course of the game, use the main menu to select from the following options: ");
        System.out.println("Increment — Add points to a player's score.");
        System.out.println("Decrement — Remove points from a player's score.");
        System.out.println("Daily Double — Starts Jeopardy's daily double round.");
        System.out.println("Double Jeopardy — Enables Double Jeopardy mode (double point values).");
        System.out.println("Final Jeopardy — Jeopardy's final round (everyone wagers, last question)!");
        System.out.println("End Game — Ends the game early (no Final Jeopardy).\n");

        System.out.println("Press enter to start the game.");
        scanner.nextLine();
        game_status = 1;
    }

    // printScores()
    // Prints the current scores.
    public static void printScores() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player #" + (i+1) + " — " + players.get(i));
        }
    }

    // increment()
    // Handles incrementation of the player scores.
    public static void increment() {
        clear();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1) + ") Player #" + (i+1) + " — " + players.get(i).getName());
        }
        System.out.print("Which player's score would you like to increment? (1-" + (players.size()) + "): ");
        int p = Integer.parseInt(scanner.nextLine()) - 1;
        printValues();
        System.out.print("Add which denomination? (1-5): ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> players.get(p).addScore(200 * game_status);
            case 2 -> players.get(p).addScore(400 * game_status);
            case 3 -> players.get(p).addScore(600 * game_status);
            case 4 -> players.get(p).addScore(800 * game_status);
            case 5 -> players.get(p).addScore(1000 * game_status);
        }
    }

    // decrement()
    // Handles incrementation of the player scores.
    public static void decrement() {
        clear();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1) + ") Player #" + (i+1) + " — " + players.get(i).getName());
        }
        System.out.print("Which player's score would you like to decrement? (1-" + (players.size()) + "): ");
        int p = Integer.parseInt(scanner.nextLine()) - 1;
        printValues();
        System.out.print("Remove which denomination? (1-5): ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> players.get(p).removeScore(200 * game_status);
            case 2 -> players.get(p).removeScore(400 * game_status);
            case 3 -> players.get(p).removeScore(600 * game_status);
            case 4 -> players.get(p).removeScore(800 * game_status);
            case 5 -> players.get(p).removeScore(1000 * game_status);
        }
    }

    public static void dailyDouble() {
        clear();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1) + ") Player #" + (i+1) + " — " + players.get(i).getName());
        }
        System.out.print("Which player is attempting the daily double? (1-" + (players.size()) + "): ");
        int p = Integer.parseInt(scanner.nextLine()) - 1;
        int wager = 0;
        if (players.get(p).getScore() < 1000) {
            System.out.println(players.get(p).getName() + "'s score is less than $1000! Wagering $1000.");
            wager = 1000;
        } else {
            System.out.print("How much would you like to wager? (1-" + (players.get(p).getScore()) + "): ");
            wager = Integer.parseInt(scanner.nextLine());
        }
        System.out.print("Did the player get the daily double correct? (y/n): ");
        switch (scanner.nextLine()) {
            case "y" -> players.get(p).addScore(wager);
            case "n" -> players.get(p).removeScore(wager);
        }
    }

    public static void finalJeopardy() {
        clear();
        // Array of wagers.
        ArrayList<Integer> wagers = new ArrayList<>();
        System.out.println("Final Jeopardy! Everyone has a chance to wager.");
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() <= 0) {
                System.out.println("Player #" + (i + 1) + " (" + players.get(i).getName() + "), you don't have any money to wager! You cannot participate in the final jeopardy.");
                wagers.add(-1);
            } else {
                System.out.print("Player #" + (i + 1) + " (" + players.get(i).getName() + "), what is your wager? (0-" + (players.get(i).getScore()) + "): ");
                wagers.add(Integer.parseInt(scanner.nextLine()));
            }
        }
        System.out.println("Great! All wagers are in. Good luck!\n");
        for (int i = 0; i < players.size(); i++) {
            if (wagers.get(i) != -1) {
                System.out.print("Player #" + (i + 1) + " (" + players.get(i).getName() + "), did you get the final jeopardy correct? (y/n): ");
                switch (scanner.nextLine()) {
                    case "y" -> players.get(i).addScore(wagers.get(i));
                    case "n" -> players.get(i).removeScore(wagers.get(i));
                }
            } else {
                System.out.print("Player #" + (i + 1) + " (" + players.get(i).getName() + "), you did not participate in the final jeopardy.");
            }
        }
        endGame();
    }

    public static void endGame() {
        clear();
        printScores();
        game_status = 0;
        System.out.println("Thanks for playing!");
    }
}
