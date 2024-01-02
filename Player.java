import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Player {
        public static final int List = 0;
        public static final int Player = 0;
        Scanner scanner = new Scanner(System.in);
        List<Card> hand = new ArrayList<>();
        public int chips;
        public static int pot;
        public boolean folded;

        // Player constructor
        public Player(int initialChips) {
            this.chips = initialChips;
            this.hand = new ArrayList<>();
        }

        // Sets the player's hand
        public void setHand(List<Card> hand) {
            this.hand = hand;
        }

        // Gets the player's hand
        public List<Card> getHand() {
            return hand;
        }

        // Gets the player's chip count
        public int getChips() {
            return chips;
        }

        // Player bets a certain amount of chips
        public void bet(int amount) {
            if (amount > chips) {
                amount = chips;
            }
            chips -= amount;
            pot += amount;
        }

        // Player wins a certain amount of chips
        public void win() {

              chips += pot;
        }
        public void tie(){
            chips= chips + (pot/2);
        }
        public int getBetAmount(Scanner scanner) {
        int amount;

            while (true) {
                System.out.println("Enter your bet amount (You have " + this.getChips() + " chips):");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Consume the invalid input
                }

                amount = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                if (amount <= this.getChips() && amount > 0) {
                    break; // Valid amount entered
                } else {
                    System.out.println("Invalid amount. You can bet up to " + this.getChips() + " chips.");
                }
            }

            return amount;
        }
        public void bettingRound(Scanner scanner, List<Card> deal) {
            // Display player's hand and chips
            
            System.out.println("Your Hand: " + this.getHand());
            System.out.println("Your Chips: " + this.getChips());
            System.out.println("The Pot is: "+pot);
          
            // Ask if player wants to bet
              System.out.println("Enter your action (B for bet, C for check, F for fold):");  
              String action = scanner.nextLine();
            if (folded == false){
              switch(action.toUpperCase()) {
          
                case "B":  
                  // Bet logic
                  int betAmount = getBetAmount(scanner); 
                  bet(betAmount);
                  break;
                  
                case "C":
                  // Check logic
                  System.out.println("You checked.");
                  break;
                  
                case "F":
                  // Fold logic
                  System.out.println("You folded."); 
                  folded = true;
                  break;
                  
                default:
                  System.out.println("Invalid action. Checking.");
                  break;  
            }
          }
          else if(folded == true){
            System.out.println("Action ignored due to previous fold.");
          }
    }
    public static List<Player> checkWin(List<Player> players, List<Card> communityCards) {
            double highestRank = 0;
            int pots = pot;
            List<Player> winners = new ArrayList<>();
    
            // Evaluate hands and find the highest hand rank
            for (Player player : players) {
                if (!player.folded) {
                    double handRank = PokerHandEvaluator.evaluateHand(player.getHand(), communityCards);
                    if (handRank > highestRank) {
                        highestRank = handRank;
                        winners.clear();
                        winners.add(player);
                    } else if (handRank == highestRank) {
                        winners.add(player);
                    }
                }
            }
    
            // Calculate winnings and update each winner's chips
            int winnings = pots / winners.size();
            for (Player winner : winners) {
                winner.chips += winnings;
                System.out.println("Player wins " + winnings + " chips!");
            }
    
            // Display each player's chip count
            for (int i = 0; i < players.size(); i++) {
                System.out.println("Player " + (i + 1) + " has " + players.get(i).chips + " chips.");
            }
    
            return winners;
        }
    }