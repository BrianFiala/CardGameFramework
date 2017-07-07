/* name: Brian Fiala
 * date: 5/13/14
 * Professor Cecil
 * Assignment 5 Phase 3
 * 
 * Class GUICard: includes methods for: loading card icons into an array; 
 * accessors for these card icons given a card parameter, and an accessor for 
 * the back-of-card image; and private helper methods for converting Card data 
 * members, suit and value, to int values that are useful for accessing the 
 * image data in the card icons array
 */

import javax.swing.Icon;
import javax.swing.ImageIcon;

class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4]; // A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   // method to generate the iconCards[] from files
   static void loadCardIcons()
   {
      // if method has been called before, do nothing
      if (iconsLoaded) 
         return;
      
      else // load icons into iconCards[][]
      {
         // char arrays for suits and values for ease of loop access
         char[] values = 
            {'2', '3', '4', '5', '6', '7', '8', 
            '9', 'T', 'J', 'Q', 'K', 'A', 'X'};
         char[] suits = {'C', 'D', 'H', 'S'};
         
         // handle the unique card back icon separately
         iconBack = new ImageIcon("images/BK.gif");
         
         // loads the icons for a standard deck including 4 jokers
         for (int st = 0; st < 4; st++)
         {
            for (int val = 0; val < 14; val++)
            {
               iconCards[val][st] = new ImageIcon("images/" + values[val] + 
                  suits[st] + ".gif");
            }
         }
         
         // iconsLoaded = true to avoid unnecessary repetition in later calls
         iconsLoaded = true;
      }
   }
   
   // accessor for the icon for the card parameter
   public static Icon getIcon(Card card)
   {
      loadCardIcons(); // ensures iconCards[][] is filled with card icons
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }
   
   // accessor for card back image
   public static Icon getBackCardIcon()
   {
      loadCardIcons(); // ensures iconCards[][] is filled with card icons
      return iconBack;
   }
   
   // return int matches the value of Card card for access to proper card icon
   private static int valueAsInt(Card card)
   {
      return Card.getValueRank(card.getVal());
   }
   
   // return int matches the suit of Card card for access to proper card icon
   private static int suitAsInt(Card card)
   {
      return Card.getSuitRank(card.getSuit());
   }
} // end class GUICard
