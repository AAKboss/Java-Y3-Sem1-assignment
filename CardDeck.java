import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<PlayingCard> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                PlayingCard card = new PlayingCard(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public PlayingCard drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck.");
        }
        return cards.remove(0);
    }
}
