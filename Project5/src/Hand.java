import java.util.*;

/** 
 * Represents hand of cards
 * @author Dan Longo
 * @author Suzanne Balik
 * @author Will Greene
 */
public class Hand {

    /** Number of cards in a poker hand */
    public static final int CARDS_IN_HAND = 5;
    
    /** Contains cards in hand */
    private Card[] hand;
    
    
    /**
     * Hand constructor.
     * 
     * @param hand array of cards
     * @throws IllegalArgumentException if null array
     * @throws IllegalArgumentException if invalid array length
     * @throws IllegalArgumentException if null element
     */
    public Hand(Card[] hand) {
    
        // Parameter error checking - null array
        if (hand == null) {
            throw new IllegalArgumentException("Null array");
        }
        
        // Parameter error checking - invalid array length
        if (hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid array length");
        }
        
        // Parameter error checking - null element
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i] == null) {
                throw new IllegalArgumentException("Null element");
            }
        }
        
        this.hand = hand;
    }
    
    
    /**
     * Returns the card from the hand array at the index specified.
     * 
     * @param index index of the card in the hand
     * @return card from the hand array at index
     * @throws IllegalArgumentException if invalid index
     */
    public Card getCard(int index) {
    
        // Parameter error checking - invalid index
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        
        return hand[index];
    }
    
    
    /**
     * Replaces the card at the given index in the hand array 
     * with the card passed to the method.
     * 
     * @param index index of the card in the hand
     * @param card card to replace
     * @throws IllegalArgumentException if invalid index
     * @throws IllegalArgumentException if null card
     */
    public void replace(int index, Card card) {
    
        // Parameter error checking - invalid index
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        }
        
        // Parameter error checking - null card
        if (card == null) {
            throw new IllegalArgumentException("Null card");
        }
        
        hand[index] = card;
    }
    
    
    /**
     * Creates a String representation of the hand.
     * 
     * @return String representation of hand
     */
    public String toString() {
        return Arrays.toString(hand);
    }
    
    
    /**
     * Returns whether this Hand and "o" are equal.
     * 
     * @param o object to be compared to
     * @return true if equal
               false if not
     */
    public boolean equals(Object o) {
        if (o instanceof Hand) {
            Hand h = (Hand)o;
            
            getSortedHand();
            h.getSortedHand();
            
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (hand[i].getValue() != h.hand[i].getValue() || 
                    hand[i].getSuit() != h.hand[i].getSuit()) {
                    return false;
                }
            }
        }
        
        else {
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Counts the number of cards with each value in the hand.
     * 
     * @return tally array containing number of cards of each value from 2 to 14.
     */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }
    
    
    /**
     * Creates a copy of the hand sorted first by value, then by suit.
     * 
     * @return copy of the hand sorted first by value, then by suit
     */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }
    
    
    /**
     * Returns whether the hand qualifies as a flush.
     * 
     * @return true if hand qualifies as a flush
               false if not
     */
    public boolean isFlush() {
        for (int i = 1; i < CARDS_IN_HAND; i++) {
            if (hand[i].getSuit() != hand[i - 1].getSuit()) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Returns whether the hand qualifies as a straight.
     * 
     * @return true if hand qualifies as a straight
               false if not
     */
    public boolean isStraight() {
        hand = getSortedHand();
        for (int i = 1; i < CARDS_IN_HAND; i++) {
            if (hand[i].getValue() != hand[i - 1].getValue() + 1) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Returns whether the hand qualifies as a straight flush.
     * 
     * @return true if hand qualifies as a straight flush
               false if not
     */
    public boolean isStraightFlush() {
        if (isStraight() && isFlush()) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    
    /**
     * Returns whether the hand qualifies as a royal flush.
     * 
     * @return true if hand qualifies as a royal flush
               false if not
     */
    public boolean isRoyalFlush() {
        int[] handCounts = getCounts();
        
        if (isStraightFlush() && handCounts[Card.HIGHEST_VALUE] == 1) {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * Returns whether the hand qualifies as a four of a kind.
     * 
     * @return true if hand qualifies as a four of a kind
               false if not
     */
    public boolean hasFourOfAKind() {
        int[] handCounts = getCounts();
        for (int i = Card.LOWEST_VALUE; i <= Card.HIGHEST_VALUE; i++) {
            if (handCounts[i] == CARDS_IN_HAND - 1) {
                return true;
            }
        }
        
        return false;
    }
    
    
    /**
     * Returns whether the hand qualifies as a three of a kind.
     * 
     * @return true if hand qualifies as a three of a kind
               false if not
     */
    public boolean hasThreeOfAKind() {
        int[] handCounts = getCounts();
        for (int i = Card.LOWEST_VALUE; i <= Card.HIGHEST_VALUE; i++) {
            if (handCounts[i] == CARDS_IN_HAND - 2) {
                return true;
            }
        }
        
        return false;
    }
    
    
    /**
     * Returns whether the hand qualifies as a two pair.
     * 
     * @return true if hand qualifies as a two pair
               false if not
     */
    public boolean hasTwoPairs() {
        int[] handCounts = getCounts();
        int numPairs = 0;
        
        for (int i = Card.LOWEST_VALUE; i <= Card.HIGHEST_VALUE; i++) {
            if (handCounts[i] == 2) {
                numPairs++;
            }
        }
        
        if (numPairs == 2) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    
    /**
     * Returns whether the hand qualifies as a one pair.
     * 
     * @return true if hand qualifies as a one pair
               false if not
     */
    public boolean hasOnePair() {
        int[] handCounts = getCounts();
        int numPairs = 0;
        
        for (int i = Card.LOWEST_VALUE; i <= Card.HIGHEST_VALUE; i++) {
            if (handCounts[i] == 2) {
                numPairs++;
            }
        }
        
        if (numPairs == 1) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    
    /**
     * Returns whether the hand qualifies as a full house.
     * 
     * @return true if hand qualifies as a full house
               false if not
     */
    public boolean isFullHouse() {
        if (hasThreeOfAKind() && hasOnePair()) {
            return true;
        }
        
        else {
            return false;
        }
    }
}
