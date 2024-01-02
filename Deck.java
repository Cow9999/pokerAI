import java.util.*;
public class Deck {
        private List<Card> cards;

        // Deck constructor to initialize and populate the deck
        public Deck() {
            this.cards = new ArrayList<>();
            // Array of all card suits and ranks
            String[] suits = {"H2","H3","H4","H5","H6","H7","H8","H9","H10","HJ","HQ","HK","HA",  
                              "D2","D3","D4","D5","D6","D7","D8","D9","D10","DJ","DQ","DK","DA", 
                              "S2","S3","S4","S5","S6","S7","S8","S9","S10","SJ","SQ","SK","SA", 
                              "C2","C3","C4","C5","C6","C7","C8","C9","C10","CJ","CQ","CK","CA"};

            // Populating the deck with cards
            for (String suit : suits) {
                this.cards.add(new Card(suit));
            }
        }

        // Shuffles the deck
        public void shuffle() {
            Collections.shuffle(this.cards);
        }

        // Deals a hand of specified number of cards
        public List<Card> dealHand(int numberOfCards) {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < numberOfCards; i++) {
                hand.add(cards.remove(0));
            }
            return hand;
        }
    }
