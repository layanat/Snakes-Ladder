/**
 * @author layana muhdi al tounsi
 * Comp249 (PP)
 * ID 40125569
 * assignment 1 
 * Part 1
 * DUE DATE February 8th 2020
 * 
 */
import java.util.Random;
/**
 * the class LadderAndSnake contains all the necessary methods and variables that will help the driver class run smoothly
 */
public class LadderAndSnake {
	
	private int players;
	/**
	 * 
	 * @param ThePlayers 
	 * parametarized constructor for the number of  players
	 */
	public LadderAndSnake (int ThePlayers) {
	this.players= ThePlayers; 
	}
	/**
	 * building the board using arrays and displaying each block that has a ladder 
	 * showing where the player must end . 
	 */
	public void play() {
		int[] board = new int [101];
		board[1]=38; // ladder on block will end on block 38
		board[4]= 14;// ladder on block 4 will end on block 14
		board[9]=31;// ladder on block 9 will end on block 31
		board[21]=42;// ladder on block 21 will end on block 42
		board[28]=84;// ladder on block 28 will end on block 84
		board[36]=44;// ladder on block 36 will end on block 44
		board[51]=67;// ladder on block 51 will end on block 67
		board[71]=91;// ladder on block 71 will end on block 91
		board[80]=100;// ladder on block 80 will end on block 100 which is the last block
		//snakes :
		board[16]=6;
		board[48]=30;
		board[64]=60;
		board[79]=19;
		board[93]=68;
		board[95]=24;
		board[97]=76;
		board[98]=78;
		playerss[] myplayers = new playerss[players];

		for (int i = 0; i < players; i++) {
			myplayers[i] = new playerss(i + 1);
		}
		int[] dice_throws = new int[players];
		boolean already_found = false; // to make sure that no two players have the same first roll 
		for (int i = 0; i < players;) {
			
			int temp = flipDice();
			for (int j = i+1; j < players; j++) {
				if (temp == dice_throws[j])
					already_found = true;
			}

			if (already_found)
				continue;
			else {
				dice_throws[i] = temp;
				myplayers[i].setPosition(temp);
				/**
				 * the setPosition method will set a temporary position for the player each time the dice is thrown 
				 */
				i++;
			}
		}
		int rank = 1;

		for (int j = 6; j >= 1; j--) {
			for (int i = 0; i < players; i++) {
				if (dice_throws[i] == j) {
					System.out.println("Player "+(i+1)+" got a dice value of "+j);
					myplayers[i].setOrder(rank++);
				}
			}
		}
		
		System.out.println("Reached final decision on order");
		for (int i=4;i>=1;i--) {
		for (int j=0;j<players;j++) {
			if (myplayers[j].getOrder()==i) {
				System.out.print("Player "+(j+1));
			}
		}
		}
		System.out.println();
		for (int i=0;i<players;i++) {
			myplayers[i].setPosition(0);
		}

		boolean someone_won = false;

		while (!someone_won) {
			for (int i = 0; i < players; i++) {
				int dice_roll=flipDice();
				int current_location=myplayers[i].getPosition()+dice_roll;
				myplayers[i].setPosition(current_location);
				
				if (current_location<=100) {
					System.out.println("Player "+(i+1)+" rolled a "+dice_roll+", and is now in location "+myplayers[i].getPosition());
				int new_location=board[current_location];
				if (new_location!=current_location&&new_location!=0) {
					myplayers[i].setPosition(new_location);
				System.out.println("Now he/she had moved to the new position "+myplayers[i].getPosition());
				}
				}
				if (myplayers[i].getPosition()>=100||current_location>=100) {
					System.out.println("Game has ended\n Congratulations Player "+(i+1));
					/**
					 * program will end and a winner will be displayed
					 */
					System.exit(0);
				}
			}
			System.out.println();
		}

	}
	/**
	 * method flipDice() will act like a dice and will give the user a random number from 1-6
	 * @return a random number of type integer from 1-6 
	 */
	public int flipDice() {
		Random random = new Random();
		return (random.nextInt(6 - 1 + 1) + 1);
	} 
	
	
	
 class playerss {
	/**
	 *  the class players will set all the different characteristics for the player
	 *  taking into consideration their names if asked , position, and order
	 *  @param posotion will set the position of the player on the board
	 *  @param order will determine who goes first in order of players
	 */
         private int name;
		private int position;
		private int order;
		public playerss(int name) {
			this.name=name;
		}
		/**
		 * method setPosition will set for each player an initial position
		 * @param p the initial player position
		 */
		public void setPosition(int p) {
			this.position=p;
		}
		/**
		 * method getPosition give us what position each player is at
		 * @return returns the position we set in the setvalue method
		 */
		public int getPosition() {
			return this.position;
		}
		/**
		 * 
		 * method getOrder gives player order 
		 * @return returns the order in which each player will go
		 */
		public int getOrder() {
			return this.order;
		}
		/**
		 * SetOrder method will set this order for the players each time
		 * @param t is the order that the program will assign to the players
		 */
		public void setOrder(int t) {
			this.order=t;
		}
}
 }

		
	
			
			
					
