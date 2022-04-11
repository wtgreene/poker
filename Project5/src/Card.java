/** 
 * Assigns card value and suit.
 * This class was completed with Mrs. Glatz during the video.
 * 
 * @author Mrs. Glatz
 * @author Will Greene
 */
public class Card implements Comparable<Card> {
    
    /** Clubs suit */
    public static final char CLUBS = 'c';
    
    /** Diamonds suit */
    public static final char DIAMONDS = 'd';
    
    /** Hearts suit */
    public static final char HEARTS = 'h';
    
    /** Spades suit */
    public static final char SPADES = 's';
    
    /** Highest value */
    public static final int LOWEST_VALUE = 2;
    
    /** Lowest value */
    public static final int HIGHEST_VALUE = 14;
    
    /** Card's value (jack - 11, queen - 12, king - 13, ace - 14) */
    private int value;
    
    /** Card's suit */
    private char suit;
    
    /**
     * Card constructor.
     * 
     * @param value card value
     * @param suit card suit
     * @throws IllegalArgumentException if invalid value
     * @throws IllegalArgumentException if invalid suit
     */
    public Card(int value, char suit) {
        
        // Parameter error checking - invalid value
        if (value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException("Invalid value");
        }
        
        // Parameter error checking - invalid suit
        if (suit != CLUBS && suit != DIAMONDS && suit != SPADES && suit != HEARTS) {
            throw new IllegalArgumentException("Invalid suit");
        }
        
        this.value = value;
        this.suit = suit;
    }
    
    /**
     * Returns card value.
     * 
     * @return card value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns card suit.
     * 
     * @return card suit
     */
    public char getSuit() {
        return suit;
    }
    
    /**
     * Returns whether the current object and parameter object are equal.
     * 
     * @param o object to be compared to
     * @return true if equal and
               false if not
     */
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card c = (Card)o;
            return value == c.getValue() && suit == c.getSuit();
        }
        
        else {
            return false;
        }
    }
    
    /**
     * Returns card value and suit in the form of a String.
     * 
     * @return card value and suit
     */
    public String toString() {
        return "" + suit + value;
    }
    
    /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     * 
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }
}