/* name: Brian Fiala
 * date: 5/13/14
 * Professor Cecil
 * Assignment 5 Phase 3
 * 
 * Class Foothill: instantiates a subclass of JFrame: CardTable, and an 
 * instance of the CardGameFramework class, then uses the CardGameFramework 
 * object to generate the hands. Then the client loads the card icon JLabels 
 * for the computer player, human player, and played cards, and displays them 
 * within the appropriate JPanels within the CardTable
 * 
 * Classes CardGameFramework, Card, Hand, and Deck are written by Professor 
 * Cecil and used with permission
 */

import javax.swing.*;

public class Foothill 
{
   static final int NUM_CARDS_PER_HAND = 7;
   static final int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   static int numPacksPerDeck = 1;
   static int numJokersPerPack = 0;
   static int numUnusedCarsPerPack = 0;
   static Card[] unusedCardsPerPack = null;
   
   static CardGameFramework toyGame = new CardGameFramework( 
      numPacksPerDeck, numJokersPerPack,  
      numUnusedCarsPerPack, unusedCardsPerPack, 
      NUM_PLAYERS, NUM_CARDS_PER_HAND);
   
   // main instantiates the CardTable and displays card icon JLabels for the 
   // dealer, player, and played cards area in the appropriate JPanels
   public static void main(String[] args)
   {
      // establish main frame in which program will run
      CardTable myCardTable = 
         new CardTable("Card Table", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // deal cards to the computer and player hands
      toyGame.deal();
      
      // generate computer (dealer) JLabels with back of card icons and add 
      // them to the computer JPanel
      for (int k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         // fill label array with card icons
         computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
         
         // add labels to dealer panel
         myCardTable.dealerPanel.add(computerLabels[k]);
      }
      
      // generate player JLabels with icons for the cards and add them 
      // to the player JPanel
      for (int k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         // fill label array with card icons. Hand 0 is computer, and hand 1 
         // is the player's hand
         humanLabels[k] = 
            new JLabel(GUICard.getIcon(toyGame.getHand(1).inspectCard(k)));
         
         // add labels to player panel
         myCardTable.playerPanel.add(humanLabels[k]);
      }
      
      // deal two new cards from the deck and display them as centered JLabels, 
      // and add them to the played cards JPanel
      for (int k = 0; k < NUM_PLAYERS; k++)
      {
         // fill label array with card icons 
         playedCardLabels[k] = 
            new JLabel(GUICard.getIcon(
               toyGame.getCardFromDeck()), JLabel.CENTER);
         
         // add labels to played card panel
         myCardTable.playingAreaPanel.add(playedCardLabels[k]);
      }
      
      // create labels "Computer" and "You" for display beneath played cards
      playLabelText[0] = new JLabel("Computer", JLabel.CENTER);
      playLabelText[1] = new JLabel("You", JLabel.CENTER);
      
      // add labels to played card panel
      myCardTable.playingAreaPanel.add(playLabelText[0]);
      myCardTable.playingAreaPanel.add(playLabelText[1]);
      
      // show everything to the user
      myCardTable.setVisible(true);
   } // end main
} // end class Foothill