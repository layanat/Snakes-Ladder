import java.util.Scanner;
/**
 * 
 * @author layana muhdi al tounsi
 * Comp249 (PP)
 * ID 40125569
 * assignment 1
 * Part 2
 * DUE DATE February 8th 2020
 * 
 *
 *//**
 * the class DriverSnakeLadder  is the class where
 * the game will come to life and he user will be prompted to input the number of players
 * 
 */
public class DriverSnakeLadder {
/**
 * constructor DriverSnakeLadder is a default contrsuctor with no parameters
 */
	public DriverSnakeLadder() {
	}
	/**
	 * 
	 * @param args
	 * prompting the user to enter the number of players
	 */
		public static void main(String[] args) 
		{
			Scanner kb=new Scanner(System.in);
			System.out.println("--------------------------------------------------");
			System.out.println("------------Snake and Laddeers, the game.---------");
			System.out.println("--------------------------------------------------");
			System.out.println("Hello and welcome to the game of Snakes and Ladders!");
			System.out.println("How many players are playing?( Allowed number of player is 2-4 inclusively )");
			int attempts = 0;
			int players=kb.nextInt();
			while ((players > 4 || players <= 1) && attempts < 5)
			{
				if(attempts == 3)
				{
					System.out.println("Tries exceeded, goodbye!");
					/**
					 * the number of players cannot exceed 4 or be less  than 2 , if this happens the 
					 * players are asked three more times to adjust their numbers
					 * when the attempts are all used and the users have not adjusted their number
					 * the program will terminate completely using the exit method
					 */
					System.exit(0);
					
					
			}
				else
				{
					System.out.println(" INVALID - PLEASE TRY AGAIN - How many players are playing ? ");
					System.out.println("Remaining attempts:"+(3-attempts));
					players = kb.nextInt();
					attempts++;
				}
				}
			int [] order = new int [players];
			int [] dice = new int [players];
			for (int i = 0; i < players; i++)
			{
				dice[i] = flipDice();
				order[i] = i+1;
				System.out.println((i+1)+" player has rolled: "+dice[i]);
			}
			/**
			 * initiative method to compare the order in which the players went originally
			 * @param players is the number of players playing the game
			 * @param dice refers to the value of the dice 1 to 6 first time
			 * @param order is the order in which they played
			 */
		
			initiative (players, dice, order);
			reRoll (players,dice,order);
			LadderAndSnake X = new LadderAndSnake ( players );
			X.play();
			kb.close();
		}
		
			
		/**
		 * method flipDice() will act like a dice and will give the user a random number from 1-6
		 * @return a random number which is diceValue of type integer from 1-6 
		 * diceValue which is initialized o zero is the value that the generator will later assign 
		 * to the player's dice
		 */
			public static int flipDice()
			{
				int diceValue = 0;
				diceValue = (int)(Math.random()*6 )+1;
				return diceValue;
			}
/**
 * method swap ; it will swap the players at specified positions
 * @param A1 specific array that is used to swap
 * @param p1 p1 will be swapped with p2
 * @param p2 p2 will be swapped with p1
 */
			public static void swap (int[] A1, int p1, int p2) 
			{
		        int temp = A1[p1];     
		        A1[p1] = A1[p2];    
		        A1[p2] = temp;       
			}  
			/**
			 * initiative method to compare the order in which the players went originally
			 * @param players is the number of players playing the game
			 * @param dice refers to the value of the dice 1 to 6 first time
			 * @param order is the order in which they played
			 */
			public static void initiative (int players, int[] dice, int [] order)
				{
					for (int i = 0; i < players; i++)
					{
						int largest = i;
						for (int x = i+1; x < players; x++)
						{
							if(dice[largest] < dice[x])
							{
								largest = x;
							}
						}
						
						swap(dice, i, largest);
						swap(order, i, largest);	
					}
				}
		/**
		 * 
		 * @param players # of players
		 * @param dice array for the repeated dice value
		 * @param order array for the repeated  player order 
		 * method reRoll will take action when we have a repeated dice values and will help solve this tie
		 */
		public static void reRoll(int players, int[] dice, int [] order)
		{
			int [] aRepeat;
			int [] aRoll;
			for (int i = 0; i < players-1; i++)
			{
				int repeated = 0;
				for (int x = i+1; x < players ; x++)
				{
					if (dice [i] == dice [x])
					{
						repeated++;
					}
					else
					{
						break;
					}
				}
				if (repeated > 0)
				{
					aRepeat = new int [repeated+1];
					aRoll = new int [repeated+1];
					System.out.print(" WARNING : WE HAVE A TIE -Player  ");
					for (int j = 0; j < aRepeat.length; j++)
					{
						if(j == repeated)
						{ 
							System.out.print("is tied with "+ order[i+j] + "!");
						}
						
						if(j < repeated)
						{ 
							System.out.print("is tied with " + order[i+j] + " ");
						}
						aRoll[j] = flipDice();
						
						aRepeat[j] = order[j+i];
						
					}
					System.out.println(" Breaking the tie :) ");
					for (int j = 0; j < aRepeat.length; j++)
					{
						System.out.println("player " +aRepeat[j]+ " rolled: " + aRoll[j]);					
					}
					initiative (repeated+1, aRoll, aRepeat);
					reRoll(repeated+1, aRoll, aRepeat);
					for(int j = 0; j < aRepeat.length; j++)
					{
						order[i+j] = aRepeat[j];
					}
					
					
		}
				
}}
	}

	