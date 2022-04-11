/**
 * Handles the logic behind a game of Video Poker.
 * 
 * @author Will Greene
 */
public class VideoPoker {

    /** integer used for the 'seed' variable, indicating a random game */
    public static final int RANDOM_GAME = -1;
    
    /** number of cards in a poker hand (5 card draw style) */
    public static final int CARDS_IN_HAND = 5;
    
    /** points that a player starts with when the program begins */
    public static final int STARTING_POINTS = 100;
    
    /** points needed to start a new game */
    public static final int POINTS_FOR_NEW_GAME = 10;
    
    /** points earned for a royal flush */
    public static final int ROYAL_FLUSH = 100;
    
    /** points earned for a straight flush */
    public static final int STRAIGHT_FLUSH = 60;
    
    /** points earned for a four of a kind */
    public static final int FOUR_OF_A_KIND = 50;
    
    /** points earned for a full house */
    public static final int FULL_HOUSE = 40;
    
    /** points earned for a flush */
    public static final int FLUSH = 30;
    
    /** points earned for a straight */
    public static final int STRAIGHT = 25;
    
    /** points earned for a three of a kind */
    public static final int THREE_OF_A_KIND = 15;
    
    /** points earned for a two pair */
    public static final int TWO_PAIRS = 10;
    
    /** points earned for a one pair */
    public static final int ONE_PAIR = 7;
        
    /** reference to a deck of 52 cards */
    private Deck deck;
    
    /** reference to an array of cards */
    private Hand hand;
    
    /** points a player earns or spends */
    private int points;
    
    
    /**
     * Video poker constructor.
     * Creates a deck using the seed.
     * Sets points to STARTING_POINTS.
     * 
     * @param seed random seed provided for testing
     */
    public VideoPoker(int seed) {
        deck = new Deck(seed);
        points = STARTING_POINTS;
    }
    
    
    /**
     * Returns the number of points.
     * 
     * @return number of points
     */
    public int getPoints() {
        return points;
    }
    
    
    /**
     * Returns the name of the image file for the Card at the given index in the hand.
     * Includes the cards directory followed by the card followed by the .gif extension.
     * 
     * @param index index of the card in the hand
     * @return image filename
     */
    public String getCardFileName(int index) {
        return "cards/" + hand.getCard(index).toString() + ".gif";
    }
    
    
    /**
     * Returns the card at the given index in the hand.
     * 
     * @return card at given index in the hand
     */
    public Card getCard(int index) {
        return hand.getCard(index);
    }
    
    
    /**
     * Starts a new game.
     * Subtracts POINTS_FOR_NEW_GAME from points and shuffles the deck.
     * A new array of CARDS_IN_HAND Cards is created and filled.
     */
    public void newGame() {
        points = points - POINTS_FOR_NEW_GAME;
        deck.shuffle();
        
        Card[] newHand = new Card[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            newHand[i] = deck.nextCard();
        }
        
        hand = new Hand(newHand);
    }
    
    
    /**
     * Gets the next Card from the deck and requests the hand to 
     * replace the card at the given index with the new Card.
     * 
     * @param index index of the card in the hand
     */
    public void replaceCard(int index) {
        hand.replace(index, deck.nextCard());
    }
    
    
    /**
     * Returns a String corresponding to the scored hand and 
     * adds the correct number of points to the total points based on the type of hand.
     */
    public String scoreHand() {
        
        if (hand.isRoyalFlush()) {
            points += ROYAL_FLUSH;
            return "Royal Flush";
        } else if (hand.isStraightFlush()) {
            points += STRAIGHT_FLUSH;
            return "Straight Flush";
        } else if (hand.hasFourOfAKind()) {
            points += FOUR_OF_A_KIND;
            return "Four of a Kind";
        } else if (hand.isFullHouse()) {
            points += FULL_HOUSE;
            return "Full House";
        } else if (hand.isFlush()) {
            points += FLUSH;
            return "Flush";
        } else if (hand.isStraight()) {
            points += STRAIGHT;
            return "Straight";
        } else if (hand.hasThreeOfAKind()) {
            points += THREE_OF_A_KIND;
            return "Three of a Kind";
        } else if (hand.hasTwoPairs()) {
            points += TWO_PAIRS;
            return "Two Pairs";
        } else if (hand.hasOnePair()) {
            points += ONE_PAIR;
            return "One Pair";
        } else {
            return "No Pair";
        }
    }
}