import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
    private List<PlayingCard> cards;

    public PlayerHand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(PlayingCard card) {
        cards.add(card);
    }

    public int calculatePoints() {
        int points = 0;
        int aceCount = 0;

        for (PlayingCard card : cards) {
            String rank = card.getRank();
            if (rank.equals("Ace")) {
                aceCount++;
                points += 11; // Ace is 11 initially
            } else if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                points += 10;
            } else {
                points += Integer.parseInt(rank);
            }
        }

        // Adjust Ace Points
        while (points > 21 && aceCount > 0) {
            points -= 10; // Change Ace from 11 to 1
            aceCount--;
        }

        return points;
    }

    public void displayCards() {
        for (PlayingCard card : cards) {
            System.out.println(card);
        }
    }
}
