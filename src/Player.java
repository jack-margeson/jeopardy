// Player.java
// Player class written for Jack's Jeopardy.
// Jack Margeson, 05/25/2020.

public class Player {
    // Private data.
    private String name;
    private int score;

    // Constructors.
    // Default constructor.
    public Player() {
        this.name = "";
        this.score = 0;
    }
    // Name fill constructor.
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    // Fill constructor.
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Gets and sets.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    // Methods.
    // addScore()
    // Adds to the player's score.
    public void addScore(int score) {
        this.score = this.score + score;
    }

    // addScore()
    // Adds to the player's score.
    public void removeScore(int score) {
        this.score = this.score - score;
    }

    // toString()
    // Overrides the normal toString and will print name and score of player.
    @Override
    public String toString() {
        return(this.name + " — $" + this.score);
    }
}
