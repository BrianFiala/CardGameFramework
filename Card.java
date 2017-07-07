//class Card  ----------------------------------------------------------------
class Card
{   
   // type and constants
   public enum State {deleted, active} // not bool because later we may expand
   public enum Suit { clubs, diamonds, hearts, spades }

   // for sort.  
   public static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', 
      '9', 'T', 'J', 'Q', 'K', 'A', 'X'};
   static Suit[] suitRanks = {Suit.clubs, Suit.diamonds, Suit.hearts,
      Suit.spades};
   static int numValsInOrderingArray = 13;  // maybe 14 if 'X' = Joker, or < 13

   // private data
   private char value;
   private Suit suit;
   State state;
   boolean errorFlag;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.spades);
   }
   public Card()
   {
      this('A', Suit.spades);
   }
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }

   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char

      // can't really have an error here
      this.suit = suit;  

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      // check for validity
      if (
            upVal == 'A' || upVal == 'K'
            || upVal == 'Q' || upVal == 'J'
            || upVal == 'T' || upVal == 'X'
            || (upVal >= '2' && upVal <= '9')
            )
      {
         errorFlag = false;
         state = State.active;
         this.value = upVal;
      }
      else
      {
         errorFlag = true;
         return false;
      }

      return !errorFlag;
   }

   public void setState( State state)
   {
      this.state = state;
   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public State getState()
   {
      return state;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   // stringizer
   public String toString()
   {
      String retVal;

      if (errorFlag)
         return "** illegal **";
      if (state == State.deleted)
         return "( deleted )";

      // else implied

      if (value != 'X')
      {
         // not a joker
         retVal =  String.valueOf(value);
         retVal += " of ";
         retVal += String.valueOf(suit);
      }
      else
      {
         // joker
         retVal = "joker";

         if (suit == Suit.clubs)
            retVal += " 1";
         else if (suit == Suit.diamonds)
            retVal += " 2";
         else if (suit == Suit.hearts)
            retVal += " 3";
         else if (suit == Suit.spades)
            retVal += " 4";
      }

      return retVal;
   }

   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      if (this.state != card.state)
         return false;
      return true;
   }

   // sort member methods
   public int compareTo(Card other)
   {
      if (this.value == other.value)
         return ( getSuitRank(this.suit) - getSuitRank(other.suit) );

      return (
            getValueRank(this.value)
            - getValueRank(other.value)
            );
   }

   public static void setRankingOrder(
         char[] valueOrderArr, Suit[] suitOrdeArr,
         int numValsInOrderingArray )
   {
      int k;

      // expects valueOrderArr[] to contain only cards used per pack,
      // including jokers, needed to define order for the game environment

      if (numValsInOrderingArray < 0 || numValsInOrderingArray > 13)
         return;

      Card.numValsInOrderingArray = numValsInOrderingArray;

      for (k = 0; k < numValsInOrderingArray; k++)
         Card.valueRanks[k] = valueOrderArr[k];

      for (k = 0; k < 4; k++)
         Card.suitRanks[k] = suitOrdeArr[k];
   }

   public static int getSuitRank(Suit st)
   {
      int k;

      for (k = 0; k < 4; k++)
         if (suitRanks[k] == st)
            return k;

      // should not happen
      return 0;
   }

   public static int getValueRank(char val)
   {
      int k;

      for (k = 0; k < numValsInOrderingArray; k++)
         if (valueRanks[k] == val)
            return k;

      // should not happen
      return 0;
   }

   public static void arraySort(Card[] array, int arraySize)
   {
      for (int k = 0; k < arraySize; k++)
         if (!floatLargestToTop(array, arraySize - 1 - k))
            return;
   }

   private static boolean floatLargestToTop(Card[] array, int top)
   {
      boolean changed = false;
      Card temp;

      for (int k = 0; k < top; k++)
         if (array[k].compareTo(array[k+1]) > 0)
         {
            temp = array[k];
            array[k] = array[k+1];
            array[k+1] = temp;
            changed = true;
         };
         return changed;
   }
}




