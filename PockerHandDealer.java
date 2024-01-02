import java.util.*;

// Main class for the poker hand dealer
public class PockerHandDealer { 
  
      private static void playGame(Scanner scanner) {
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println("How Many players would you like to play with");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        while (numPlayers < 2 || numPlayers > 10) {
            System.out.println("Invalid number. Enter a number between 2 and 10:");
            numPlayers = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("");
        List<Player> players = new ArrayList<>();
        Player player1 = new Player(1000);
        player1.setHand(deck.dealHand(2));
        player1.bet(100);
        System.out.println("Player" + 1 + "'s Chips: " + player1.getChips());
        System.out.println("Player" + 1 + "'s Hand: " + player1.getHand());
        players.add(player1);
        for(int i = 2; i<= numPlayers; i++ ){
          Player player = new Player(1000);
          player.setHand(deck.dealHand(2));
          players.add(player);
          player.bet(100);
          System.out.println("Player" + i + "'s Chips: " + player.getChips());
          System.out.println("Player" + i + "'s Hand: " + player.getHand());
        }
    
        List<Card> burnCards = new ArrayList<>();
        burnCards.add(deck.dealHand(1).get(0));
        List<Card> flopCards = deck.dealHand(3);
        burnCards.add(deck.dealHand(1).get(0));
        List<Card> turnCards = deck.dealHand(1);
        burnCards.add(deck.dealHand(1).get(0));
        List<Card> riverCards = deck.dealHand(1);
    
        List<Card> deal = new ArrayList<>(flopCards);
        System.out.println("If you would like to bet in this hand type yes if not you lose your blind amount (bet = B then on the next line the amount)(check = C)(Fold = F)");



        System.out.println("What is your action");
        
        //First action betting bling
        player1.bettingRound(scanner, deal);
        
        //The Flop
        System.out.println("The deal is " + deal);

        System.out.println("What is your action");
       
        //The second action based on the flop
        player1.bettingRound(scanner, deal);

        //adds the turn card to the deal
        deal.addAll(turnCards);

        //see the turn
        System.out.println("The deal is " + deal);

        System.out.println("What is your action");

        deal.addAll(riverCards);

        player1.bettingRound(scanner, deal);

        System.out.println("The final deal is " + deal);

        System.out.println("What is your action");

        player1.bettingRound(scanner, deal);
        List<Double> handranks = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
          Player player = players.get(i);
          if (!player.folded) {
              double handRank = PokerHandEvaluator.evaluateHand(player.getHand(), deal);
              String handType = PokerHandEvaluator.getHandName(handRank);
              System.out.println("Player " + (i + 1) + "'s Hand: " + player.getHand() + " - " + handType);
              handranks.add(handRank);
          } else {
              System.out.println("Player " + (i + 1) + " folded.");
          }
      }
      Player.checkWin(players, deal);
      }
        //if (handType1.equals(handType2) && handRank1 == handRank2) {
          //System.out.println(SamHandEval.determineHigherHand(player1.getHand(), player2.getHand()));
      //}
        // Determine the winner based on hand ranks
      
        private static void replay(Scanner scanner) {
          while (true) {
              System.out.println("Do you want to play another round? (yes/no)");
              String response = scanner.nextLine();
  
              if (response.equalsIgnoreCase("yes")) {
                  playGame(scanner);  // Replay the game
              } else {
                  System.out.println("Thank you for playing!");
                  break;  // Exit the loop, thus ending the game
              }
          }
      }
  
      public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
          playGame(scanner);
          replay(scanner);   
      }
  }
}

