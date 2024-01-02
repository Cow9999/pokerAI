import java.util.*;
import java.util.Collections;
public  class PokerHandEvaluator {

    public static int evaluateHand(List<Card> hand, List<Card> communityCards) {
  
      if (isRoyalFlush(hand)) return 9;
  
      else if (isStraightFlush(hand)) return 8;
  
      else if (isFourOfAKind(hand)) return 7; 
  
      else if (isFullHouse(hand)) return 6;
  
      else if (isFlush(hand)) return 5;
  
      else if (isStraight(hand)) return 4; 
  
      else if (isThreeOfAKind(hand)) return 3;
  
      else if (isTwoPair(hand)) return 2;
  
      else if (isOnePair(hand)) return 1;
      else return 0; // High card
  
    }
    public static String getHandName(double handRank) {
      if (handRank >= 9.0) return "Royal Flush";
      else if (handRank >= 8.0) return "Straight Flush";
      else if (handRank >= 7.0) return "Four of a Kind";
      else if (handRank >= 6.0) return "Full House";
      else if (handRank >= 5.0) return "Flush";
      else if (handRank >= 4.0) return "Straight";
      else if (handRank >= 3.0) return "Three of a Kind";
      else if (handRank >= 2.0) return "Two Pair";
      else if (handRank >= 1.0) return "One Pair";
      else return "High Card";
  }
    
  
    private static boolean isOnePair(List<Card> hand) {
  
      Set<Integer> ranks = new HashSet<>();
  
      for (Card c : hand) {
        if (!ranks.add(c.rank)) {
          return true;
        }
      }
  
      return false;
  
    }
  
    private static boolean isTwoPair(List<Card> hand) {
  
      Set<Integer> ranks = new HashSet<>();
  
      int pairs = 0;
  
      for (Card c : hand) {
        if (!ranks.add(c.rank)) {
          pairs++;
        }
      }
  
      return pairs == 2;
  
    }
  
    private static boolean isThreeOfAKind(List<Card> hand) {
      
      Set<Integer> ranks = new HashSet<>();
  
      for (Card c : hand) {
        if (!ranks.add(c.rank)) {
          return true;
        }
      }
  
      return false;
  
    }
  
    /**
     * @param hand
     * @return
     */
    private static boolean isStraight(List<Card> hand) {
      Collections.sort(hand, Comparator.comparingInt(Card::getRank));

    
        // Handling the special case for Ace low straight (A-2-3-4-5)
        if (hand.get(0).rank == 1 && hand.get(1).rank == 2 && hand.get(2).rank == 3 && hand.get(3).rank == 4 && hand.get(4).rank == 5) {
            return true;
        }
    
        int minRank = hand.get(0).rank;
        int maxRank = hand.get(hand.size() - 1).rank;
    
        if (maxRank - minRank != 4) {
            return false; 
        }
    
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).rank != hand.get(i - 1).rank + 1) {
                return false;
            }
        }
    
        return true;
    }
    
  
    /**
     * @param hand
     * @return
     */
    private static boolean isFlush(List<Card> hand) {
  
      String suit = hand.get(0).suit;
  
      for (Card c : hand) {
        if (c.suit != suit) {
          return false;
        }
      }
  
      return true;
  
    }
  
    private static boolean isFullHouse(List<Card> hand) {
  
      Set<Integer> ranks = new HashSet<>();
  
      boolean three = false;
      boolean pair = false;
  
      for (Card c : hand) {
        if (!ranks.add(c.rank)) {
          if (ranks.size() == 2) {
            three = true;
          } else {
            pair = true;
          }
        }
      }
  
      return three && pair;
  
    }
  
    private static boolean isFourOfAKind(List<Card> hand) {
  
      Set<Integer> ranks = new HashSet<>();
  
      for (Card c : hand) {
        if (!ranks.add(c.rank)) {
          return true;
        }
      }
  
      return false;
  
    }
  
    private static boolean isStraightFlush(List<Card> hand) {
  
      return isStraight(hand) && isFlush(hand);
  
    }
  
    private static boolean isRoyalFlush(List<Card> hand) {
   
      return false;
  
    }
  
  }