package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Game 
{
	/**
	 * this class runs the game using the public instance veriables 
	 */
	public static int[][] roomLayout;
	public static String[] discription;
	public static String[] items;
	public static String roomNumber;
	public static int[] loadingNumber;
	public static int[] monster;
	public static int playerHP;
	public static int playerGold;
	public static int playerPosition = 0;
	public static boolean dagger = false;
	
	public static void endGame()
	{
		/**
		 * this method prints out some expilation to the screen for player enjoyments and ends the game
		 * @author Callum Drennan SID 14880673
		 * 
		 **/
		System.out.println("a bright light flashs in the room before you stands a blue floating guy");
		System.out.println("What the hell you think to your self as to soon reolize this is a gin ");
		System.out.println("gins are knowen for having fun with mortals as well the live forever and that gets really borring");
		System.out.println("your about to shout out to it but as you open your mouth the gin snaps his fingers and your silenced");
		System.out.println("he begins to talk with a boomy voice (very similar to that of Reg E. Cathey) ");
		System.out.println("Thank you for collecting my crowns i could not be botherd collecting it for my self so i made you do it");
		System.out.println("now that your job is done ill be taking my crowns an you can go back to that tavern i found you at");
		System.out.println("and just as he finished talking you black out");
		System.out.println("\n\n good Job player you won this game stay tuned for next episode ... \n same bat time... \n same bat chanel...");
		
		System.exit(0);
		return;
	}
	public static void death()
	{
		/**
		 * the method prints out that your dead and finishes the game
		 * @author Callum Drennan SID 14880673
		 */
		System.out.println("AAND you DEAD!");
		System.exit(0);
		return;
	}
	public static void dysplayPlayer()
	{
		/**
		 * this method dysplays the players health and crown total as a persentage(health)
		 * @author Callum Drennan SID 14880673
		 **/
		
		System.out.println("\n\n\n============================================");
		System.out.println(" Health         " +playerHP);
		System.out.println(" Crowns         " +playerGold);
		System.out.println("============================================\n");
		if(playerHP<0)
		{
			death();
		}
	}	
	public static void lookingForGold()
	{
		/**
		 * this method checks to see if the room has any Crowns in it and add to the players total if it does 
		 * @author Callum Drennan SID 14880673
		 **/
		int isGold = 0; 
		isGold = (int)(Math.random()*10)+1;
		System.out.println("you look around the room high and low");
		if(isGold > 7)
		{
			int addingGold = (int)(Math.random()*250)+1;
			playerGold = playerGold+addingGold;
			
			System.out.println("you manage to find " +addingGold +" Crowns");
			
		}
		else
		{
		System.out.println("you find nothing of interest");
		}
	}
	public static void printRooms()

	{
		/** this method prints out the doors located in the room 
		 * @pram = none
		 * @author Callum Drennan Sid 14880673
		 */
		for (int t =0  ;t < roomLayout[playerPosition].length ; ++t)
		{
			if(roomLayout[playerPosition][t] != 0)
			{
				System.out.print(" "+roomLayout[playerPosition][t]);
			}
		}
		System.out.print("\n");
	}
	public static void loading() throws IOException
	{
		/**
		 * this method takes in the items from the file 
		 * and loads them in to the corresponding arrays
		 * @param gamemap.txt
		 * @author Callum Drennan SID 14880673
		 */
		int counter = 0 ;
		String[] numbers ;
		String load;
		
		Scanner fileScan,loadingFile;
		fileScan = new Scanner(new File("input/gamemap.txt"));
		
		roomNumber = fileScan.nextLine();
		
		int row = Integer.valueOf(roomNumber);
		// setting up the array with elements read in from the file 
		roomLayout = new int[row][];
		discription = new String[row];
		numbers = new String[row];
		//loadingNumber = new int [3];
		items = new String[row];
		
		System.out.println("Loading Game");
		
		while (fileScan.hasNext())
		{
			load = fileScan.nextLine();
			if (load.toUpperCase().startsWith("@DESCRIPTION"))
			{
				//System.out.println("loading: room "+(counter+1) +load);
				load = fileScan.nextLine();
				discription[counter] = load;
			}
			if (load.toUpperCase().startsWith("@CONNECT"))
			{
				//System.out.println("loading: room " +(counter+1) +load);
				load = fileScan.nextLine();
				numbers = load.split(",");
				loadingNumber = new int [3];
				for(int i = 0 ; i<numbers.length; i++)
				{
					loadingNumber[i]= Integer.parseInt(numbers[i]);
				}
				
				
					roomLayout[counter]= loadingNumber;
				
				//roomLayout[counter]= loadingNumber;
			}
			if (load.toUpperCase().startsWith("@ITEMS"))
			{
				//System.out.println("Loading: room "+(counter+1) +load);
				load = fileScan.nextLine();
				items[counter]= load;
			}
			if(load.toUpperCase().startsWith("/"))
			{
			++counter;
			}
		}
		fileScan.close();

	}
	public static void monster()

	{
		/**
		 * in this class it rolls a random number between 1 and 4 to find 
		 * which monster will be set to the monster
		 * array different monsters have different values
		 **/
		monster = new int [2];
		int randomMonster = 0 ; 
		
		monster[0]= 0;
		monster[1]= 0;
		randomMonster = (int)(Math.random()*4)+1;
		
		if(randomMonster == 1)
		{
			monster[0] = 200;
			monster[1] = (int)(Math.random()*250);
		}
		if (randomMonster == 2)
		{
			monster[0] = 500;
			monster[1] = (int)(Math.random()*500);
		}
		if(randomMonster == 3)
		{
			monster[0] = 100;
			monster[1] = (int)(Math.random()*50);
		}
		if(randomMonster == 4)
		{
			monster[0]= 1;
			monster[1]= 0; 
		}	
		
	}
	public static void setdifficulty(int difficulty)
	{
		
		/**
		 * this method takes in the user entered number (representing the chosen difficulty
		 * and sets up the players stats accordingly (with some added player taunting for fun)
		 * @pram int difficulty
		 * @author Callum Drennen SID 14880673
		 */
		if(difficulty == 1)
		{
			playerHP = 1000;
			System.out.println("Well this should be eazy");
		}
		if(difficulty == 2)
		{
			playerHP = 750;
			System.out.println("ah good you should have some fun then");
		}
		if(difficulty == 3)
		{
			playerHP = 200;
			System.out.println("Ah I see your mad crazy in the head");
			System.out.println("and i bet you play DarkSouls blind folded with your face");
			System.out.println("useing the RockBand drumset too .... well good luck");
			
		}
	
	}
	public static void startGame()
	{
		/**
		 * this method sets up the game and gives a little backstory just for a little fun
		 **/
		System.out.print("\n\n");
		System.out.println("welcome to the Dungen!!\n\n\n");
		System.out.println("your eyes open, your a little fuzzy. Vision is blured its a few moments before your vision corrects but its no help ");
		System.out.println("the room is dark exept for the small flicker of light coming from a touch on the wall,");
		System.out.println("you slowly get to your feet you notice a cool liquid running down the back of you head");
		System.out.println("you move your hand to the back of your head lightly touching where you felt the liquid");
		System.out.println("it stings you look at your hand its red with blood your blood you dont remember how you got here ");
		System.out.println("all you know is that you have to get out you run to the touch on the wall taking it out of the brace");
		System.out.println("looking around the room you found yourself in you find a doorway with out thinking you run through and it closes");
		System.out.println("with a thud behind you... you a traped \n\n\n");
		
		while (playerHP > 0)
		{
		dungenRoom();
		}
	}
	public static void dungenRoom()
	{
		/**
		 * this method sets up the discryptions of the room and finds out if the room has a monster in it
		 * also it checks the win condition of 1000+ crowns 
		 * it takes in no praramiters 
		 * @author Callum Drennan Sid 14880673
		 **/
		
		if(playerGold >= 1000)
		{
			endGame();
		}
		Scanner input = new Scanner(System.in);
		String choice;
		int skipMonster = 0;
		int userInputRoom;
		int checkMonster = 0;
		int exit = 0;
		int leaveRoom = 0;
		dysplayPlayer();
		// printing out the room 
		System.out.println(discription[playerPosition]);
		System.out.println("you see some doors each labled");
		
		printRooms();
		
		checkMonster = (int)(Math.random()*10)+1;
		//=====================================================================================================
		//checking to see if a monster is in the room and if the player has already killed one
		
		if(skipMonster	==0	);
		{
			if(checkMonster >4)
			{
				System.out.println("You See a shadow in the room ");
				//choice = input.nextLine();
			
				while (exit == 0)			
				{
					System.out.println("COMMAND: ");
					choice = input.nextLine();
					switch(choice)
					{
					case "help":
					case "HELP":
					case "Help":
						System.out.println("well your could (fight) it or (run) like a little girl");
						choice = "0";
						break;
					case "run":
						System.out.println("i see we have a little girl playing");
						int runCheck = (int) (Math.random()*2)+1;
				
						if (runCheck == 1 );
						{
							System.out.println("you  try your luck at running away ...      you feel a hand grab you pulling you back in the room ");
							System.out.println("you slam down hard in the middle of the room ");
							playerHP = playerHP - 20;
							skipMonster = battleMode();
							exit = 1;
						}
						break;
					case"fight":
						exit = 1;
						skipMonster = battleMode();
						break;
					default:
						System.out.println("invalad input (words are case senitive please type in lower case and check spelling)");
						break;
					}
				}
			}
		}
		//=============================================================================================================
	    // exploreing or leaving the room 
		while(leaveRoom == 0)
		{
			System.out.println("COMMAND: ");
			choice = input.nextLine();
			switch(choice)
			{
			case "Help":
			case "HELP":
			case "help":
				System.out.println("You can (open) a door and go thought it or (look) in the room to goodies");
				//choice = "0";
				break;
			case "open":
				System.out.println("What door would you like to open");
				printRooms();

				userInputRoom = input.nextInt();
				
				if (userInputRoom == 0 )
				{
					System.out.println("dont be daft there is no room number 0 try again");
					break;					
				}
				for (int i =0 ; i< roomLayout[playerPosition].length;++i)
				{
					if (userInputRoom == roomLayout[playerPosition][i])
					{
						playerPosition = (userInputRoom - 1);
						leaveRoom++;
						break;
					}
				}
			
				break;
			case "look":
				System.out.println("your looking around the room ");
				
				if(playerPosition == 3  )
				{
					System.out.println("you find a Axe laying there on the ground lucky you becouse your not silly you pick it up to use");
					dagger = true;
				}
				lookingForGold();
				break;
			default:
				System.out.println("Input invalid try again (inputs are case sensitiveand check spelling");
				break;
			}
		}
		//dungenRoom();
	}	
	public static int battleMode()
	{
		/**
		 * in this mode it sets up the battle between the player and a monster 
		 * taking in inputs from the player to deturmen what happens also checks to see if the player is dead
		 * @author Callum Drennan SID 14880673
		 */
		System.out.println("\n\n******************************************");
		System.out.println("*               BATTLE MODE ON               *");
		System.out.println("**********************************************\n\n");
		
		
		System.out.println("before you stands a thing of some sort it looks like it will indeed kill you");
		monster();
			
		while(monster[0]>0)
		{
			System.out.println("Monster hp: " +monster[0]);
			
			int playerAttack =0;
			int monsterAttack = 0;
			int hitOrMiss = 0;
			int run = 0;
			int userInputRoom =0;
			String choice = "0";
			Scanner input = new Scanner(System.in);
			
			dysplayPlayer();
			
			if(playerHP < 0)
			{
				death();
			}
			System.out.println("COMMAND: ");
			choice = input.nextLine();
			
			//==================================================================================================================
			//alowing the player to run if low on heath or fight 
			switch(choice)
			{
			case "help":
			case "Help":
			case "HELP":
				System.out.println("you could attemet to (run) or you could (attack) like a badass");
				break;
			case "attack":
				if(dagger == true)
				{
					playerAttack = (int)(Math.random()*150)+100;
					System.out.println("You swing wildly at this thing infront of you hiting him you do: "+playerAttack +" damage");
					monster[0] = (monster[0] - playerAttack);
					break;
				}
				else
				{
					playerAttack = (int)(Math.random()*100)+1;
					System.out.println("You run foward and try to punch the monster (why would you try to punch it thats just dumb find a wepon)");
					System.out.println("you hit it in the face doing: " +playerAttack +" damage");	
					monster[0] = (monster[0] - playerAttack);
					break;
				}
				
			case "run":
				run = (int)(Math.random()*10)+1;
				if(run > 4)
				{
					System.out.println("well you managed to run thats no fun where would you like to go");
					printRooms();
					userInputRoom = input.nextInt();
					//======================================================================================
					//checking to see if the room is there to move into
					if (userInputRoom == 0 )
					{
						System.out.println("dont be daft there is no room number 0 try again");
						break;					
					}
					for (int i =0 ; i< roomLayout[playerPosition].length;++i)
					{
						if (userInputRoom == roomLayout[playerPosition][i])
						{
							playerPosition = (userInputRoom - 1);
							printRooms();
							return 1;
						}
					}
					break;
				}
				else
				{
					System.out.println("not this time buddy the monster grabs you and throws you back into the middle of the room ");
					playerHP = (playerHP - 20);
					break;
				}
			default:
				System.out.println("Invaled input try again (remember inputs are case sensitive)");
				break;
			}
			//================================================================================================================
			//monster attack checking to see if he misses or hits the player 
			if(monster[0] <0)
			{
				System.out.println("With the monster dead, you deside that your going to riffle through his stuff becouse reasons and such");
				System.out.println("and also your an adventure after all this is what you do... you manage to find " +monster[1] +"crowns");
				playerGold = (playerGold + monster[1]);
				
				return 1;
			}
			if(("help".equals(choice)) || ("HELP".equals(choice)) || ("Help".equals(choice)) || "run".equals(choice))
			{
				//skiping over the else statment if play enterd help		
			}
			else
			{
				hitOrMiss = (int)(Math.random()*3)+1;
				if(hitOrMiss == 3)
				{
					System.out.println("the monster lunges foward punching you right in the face it bloody hurt you should proberly try to not get hit");
					System.out.println("pro tip there dont get hit");
				
					monsterAttack = (int)(Math.random()*100)+20;
				
					System.out.println("it does : " +monsterAttack +" damage");
					playerHP = (playerHP - monsterAttack);
				}
				else
				{
					System.out.println("You see the attack coming from a mile away (you are a seasoned adventer after all) ");
					System.out.println("you roll under it arm as it swings over you head you feel the air rush over you");
				}
			}
			
			
		}
		
		
	
		dysplayPlayer();
		return 1;
		
	}	
	public static void main(String[] args) throws IOException
	{
		/**
		 * this method being the main method starts the loading of the game by calling the loading method
		 * then asked the player the diffucutly he/she would like and ends by calling the startGame method
		 * @author Callum Drennan SID 14880673
		 */
		loading();
		
		int difficulty= 0;
		String playerChoice;
		
		Scanner Scan = new Scanner(System.in);
		
		System.out.println("How Epic are you? ");
		System.out.println("there are 3 choises (its the difficutly setting)");
		System.out.println("1 = under epic");
		System.out.println("2 = epic enough");
		System.out.println("3 = super epic");
		System.out.println("COMMAND: ");
		difficulty = Scan.nextInt();
		
		setdifficulty(difficulty);
		startGame();
		
	}
}
