/* name: Brian Fiala
 * date: 5/13/14
 * Professor Cecil
 * Assignment 5 Phase 3
 * 
 * Class CardTable: instantiates JPanels for the computer player, human player,
 * and played cards, and initializes them with titled borders and a grid layout
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

class CardTable extends JFrame
{
   static final int MAX_CARDS_PER_HAND = 56;
   
   // allows at most one human and one computer player
   static final int MAX_PLAYERS = 2; 
   
   private int numCardsPerHand;
   private int numPlayers;
   
   // instantiate the JPanels for the three playing fields
   // this code will need to be updated to allow more than one human player
   public JPanel dealerPanel, playingAreaPanel, playerPanel;
   
   //constructor
   // this code will need to be updated to allow more than one human player
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      super(title); // pass title to constructor for JFrame
      
      // if bad parameters are passed set defaults to MAX...
      if (numCardsPerHand > MAX_CARDS_PER_HAND || numCardsPerHand <= 0)
         this.numCardsPerHand = MAX_CARDS_PER_HAND;
      else if (numPlayers > MAX_PLAYERS || numPlayers <= 0)
         this.numPlayers = MAX_PLAYERS;
      else
      {
         this.numCardsPerHand = numCardsPerHand;
         this.numPlayers = numPlayers;
      }
      setLayout(new BorderLayout(5, 10));
      
      // construct the JPanels
      dealerPanel = 
         new JPanel(new GridLayout(1, numCardsPerHand, 10, 10));
      playingAreaPanel = 
         new JPanel(new GridLayout(2, numPlayers, 0, 10));
      playerPanel = 
         new JPanel(new GridLayout(1, numCardsPerHand, 10, 10));
      
      // set the borders
      dealerPanel.setBorder(new TitledBorder("Computer Hand"));
      playingAreaPanel.setBorder(new TitledBorder("Playing Area"));
      playerPanel.setBorder(new TitledBorder("Your Hand"));
      
      // add the panels to the CardTable
      add(playingAreaPanel, BorderLayout.CENTER);
      add(playerPanel, BorderLayout.SOUTH);
      add(dealerPanel, BorderLayout.NORTH);
   } // end constructor
   
   // accessor for the number of cards in each hand
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }
   
   // accessor for the number of players
   public int getNumPlayers()
   {
      return numPlayers;
   }
} // end class CardTable
