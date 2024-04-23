import java.util.Scanner;

public class BlackjackSession {
    private CardDeck deck;
    private PlayerHand playerHand;
    private PlayerHand dealerHand;
    private Scanner scanner;

    public BlackjackSession() {
        this.deck = new CardDeck();
        this.playerHand = new PlayerHand();
        this.dealerHand = new PlayerHand();
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println("Welcome to AAK's Blackjack table!");

        // Shuffle 
        deck.shuffle();

        // Deal start cards
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        // Display hands
        System.out.println("\nYour cards:");
        playerHand.displayCards();
        System.out.println("Your total points: " + playerHand.calculatePoints());
        System.out.println("\nDealer's cards:");
        dealerHand.displayCards();
        System.out.println("Dealer's total points: " + dealerHand.calculatePoints());

        // Player's turn
        playerTurn();

        // Dealer's turn
        dealerTurn();

        // Determine the winner
        determineWinner();
    }

    private void playerTurn() {
        while (playerHand.calculatePoints() < 21) {
            System.out.println("\nDo you want to hit or stand? (Enter 'h' or 's')");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("h")) {
                playerHand.addCard(deck.drawCard());
                System.out.println("Your cards:");
                playerHand.displayCards();
                System.out.println("Your total points: " + playerHand.calculatePoints());
            } else if (choice.equals("s")) {
                break;
            }
        }
    }

    private void dealerTurn() {
        while (dealerHand.calculatePoints() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
        System.out.println("\nDealer's final cards:");
        dealerHand.displayCards();
        System.out.println("Dealer's total points: " + dealerHand.calculatePoints());
    }

    private void determineWinner() {
        int playerPoints = playerHand.calculatePoints();
        int dealerPoints = dealerHand.calculatePoints();

        if (playerPoints > 21) {
            System.out.println("\nYou bust! Dealer wins.");
        } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
            System.out.println("\nCongratulations! You win!");
        } else if (playerPoints < dealerPoints) {
            System.out.println("\nDealer wins.");
        } else {
            System.out.println("\nIt's a tie!");
        }
    }

    public static void main(String[] args) {
        BlackjackSession game = new BlackjackSession();
        game.playGame();
    }
}
