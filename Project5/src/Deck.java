import java.util.Random;

/**
 * Represents a deck of cards.
 * 
 * @author Will Greene
 */
public class Deck {

    /** Number of cards in a deck */
    public static int CARDS_IN_DECK = 52;
    
    /** Array of card objects */
    private Card[] cards;
    
    /** Index of the next card to be dealt */
    private int next;
    
    /** Random seed provided for testing */
    private int seed;
    
    
    /**
     * Deck constructor.
     * Constructs a deck of cards in a specific order.
     * 
     * @param seed random seed provided for testing
     */
    public Deck(int seed) {
        this.seed = seed;
        
        cards = new Card[CARDS_IN_DECK];
        
        int value = Card.LOWEST_VALUE;
        char suit = Card.CLUBS;
        
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            cards[i] = new Card(value, suit);
            value++;
            
            if (value == Card.HIGHEST_VALUE + 1) {
                value = Card.LOWEST_VALUE;
                
                if (suit == Card.CLUBS) {
                    suit = Card.DIAMONDS;
                } else if (suit == Card.DIAMONDS) {
                    suit = Card.HEARTS;
                } else if (suit == Card.HEARTS) {
                    suit = Card.SPADES;
                }
            }
        }
    }
    
    
    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        if (seed != -1) {
            Random rand = new Random(seed);
            for (int i = CARDS_IN_DECK - 1; i >= 1; i--) {
                int randomCard = rand.nextInt(i + 1);
                
                Card temp = cards[i];
                cards[i] = cards[randomCard];
                cards[randomCard] = temp;
            }
        }
            
        else {
            Random rand = new Random();
            for (int i = CARDS_IN_DECK - 1; i >= 1; i--) {
                int randomCard = rand.nextInt(i + 1);
                
                Card temp = cards[i];
                cards[i] = cards[randomCard];
                cards[randomCard] = temp;
            }
        }
        
        next = 0;
    }
    
    
    /**
     * Returns the next card in the deck.
     * 
     * @return next card in the deck
     * @throws IllegalStateException if no more cards
     */
    public Card nextCard() {
        if (next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        
        return cards[next++];
    }
    
    
    /**
     * Returns whether the current object's deck is equal to the
     * parameter object's deck.
     * 
     * @param o object to be compared to
     * @return true if the cards, next index, and seed are all equal and
               false if not
     */
    public boolean equals(Object o) {
        if (o instanceof Deck) {
            Deck d = (Deck)o;

            for (int i = 0; i < CARDS_IN_DECK; i++) {
                if (cards[i].getValue() != d.cards[i].getValue() || 
                    cards[i].getSuit() != d.cards[i].getSuit()) {
                        return false;
                }
            }
            
            return next == d.next && seed == d.seed;
        }
        
        else {
            return false;
        }
    }
    
    
    /**
     * Returns a String representation of the Deck.
     * 
     * @return String representation of the Deck
     */
    public String toString() {
        String s = "";
        
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            s += "card " + i + ": " + cards[i].toString() + "\n";
        }
        
        return s;
    }
}