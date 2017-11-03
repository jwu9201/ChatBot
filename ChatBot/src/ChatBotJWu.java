import java.util.Random;
import java.util.Scanner;

public class ChatBotJWu {
	
	int emotion = 0;
	int saidName = 0;
	String name = "";
	int abilities = 0, stats = 0;
	int Q = 0, W = 0, E = 0, R = 0;
	int count = 0, level = 0, stacks = 0, armor = 0, AD = 0, AP = 0, calculate = 0, number = 0, duration = 0, magicresist = 0, health = 0;
	double damage = 0, effect = 0;
	String response = "";
	
	//basic greeting upon selecting bot//
	public String getGreeting()
	{
		return "The cycle of life and death continues. We will live, they will die.\n"+ "Now tell me summoner, what is your name?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		//scans for only integers in user input//
		findInteger(statement);
		//takes only first user input as name and saves it//
		if (saidName == 0) 
		{
			name = statement;
			if (name.equals("renekton") || name.equals("Renekton"))
			{
				response = Renekton();
				emotion--;
			}
			else 
			{
				response = "Ah yes, " + name + ", most fitting for the summoner that commands a great being such as myself.";
			}
			//sets saidName to yes so it doesn't change//
			saidName++;
		}
		//locks user in stats program//
		else if (stats == 1)
		{
			Stats(statement);
		}
		//locks user in abilities program//
		else if (abilities == 1)
		{		
			Abilities(statement);
		}
		//response if user doesn't say anything//
		else if (statement.length() == 0)
		{
			response = "Such hollow, empty minds.";
		}
		//starts stats program if inputted by user//
		else if (findKeyword(statement, "stats") >= 0)
		{
			stats++;
			response = "What level would you like to check stats for?";
		}
		//starts abilities program if inputted by user//
		else if ((findKeyword(statement, "abilities") >= 0) || (findKeyword(statement, "ability") >= 0))
		{
			abilities++;
			response = "Choose the ability you would like to know more about, " + name + ".\n" + "\t1. Passive - Soul Eater\n" + "\t2. Q - Siphoning Strike\n" + "\t3. W - Wither\n" + "\t4. E - Spirit Fire\n" + "\t5. R - Fury of the Sands\n" +"\t6. Skill Order\n" + "Or say 'done' to end this request.";
		}
		//response to 'no' - lowers emotion of bot//
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "There is such potential in one mortal life; you have wasted yours.";
			emotion--;
		}  
		else if (findKeyword(statement, "renekton") >= 0)
		{
			response = Renekton();
			emotion--;
		}
		else if (findKeyword(statement, "dog") >= 0)
		{
			response = Dog();
		}
		else if (findKeyword(statement, "stacks") >= 0)
		{
			response = Stacks();
		}
		//transform statement for 'I want to'//
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		//transform statement for 'I want to'//
		else if (findKeyword(statement,"I want", 0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		//random responses for any other input by user, affected by emotion//
		else
		{
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "None will dare to oppose the ascended's will to " + restOfStatement;
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "If you want " + restOfStatement + " then seize it for yourself.";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String Renekton ()
	{
		Random r = new Random();
		return randomRenektonResponses [r.nextInt(randomRenektonResponses.length)];
	}
	private String Dog()
	{
		Random r = new Random();
		return randomDogResponses [r.nextInt(randomDogResponses.length)];
	}
	private String Stacks()
	{
		Random r = new Random();
		return randomStacksResponses [r.nextInt(randomStacksResponses.length)];
	}
		
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}
		return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
	}
	//scanner for integer in user input//
	private void findInteger (String statement)
	{
		Scanner in = new Scanner (statement);
		if (in.hasNextInt())
		{
			number = in.nextInt();
		}
	}
	private void Abilities (String statement)
	{
		//ends abilities program//
		if (statement.equals("done"))
		{
			abilities = 0;
			count = 0;
			calculate = 0;
			Q = 0;
			W = 0;
			E = 0;
			R = 0;
			response = "Understood, is there anything else you would like to talk about?";
		}
		//resets program//
		else if (statement.equals("retry"))
		{
			level = 0;
			stacks = 0;
			armor = 0;
			AD = 0;
			AP = 0;
			number = 0;
			damage = 0;
			duration = 0;
			count = 0;
			response = "How much AD do you currently have?";
			count++;
		}
		//starts calculate program//
		else if (statement.equals("calculate"))
		{
			//global start response for every ability//
			if (calculate == 0)
			{
				response = "How much AD do you currently have?";
				count++;
				calculate++;
			}
			else
			{
				response = "You are already in calculation mode. Say done to exit, or retry to redo the calculations.";
			}	
		}
		else if ((count == 1) && (number >= 0)) 
		{
			response = "How much AP do you currently have?";
			AD = number;
			count++;
		}
		else if ((Q > 0) && (calculate == 1)) 
		{
			if (count == 2)
			{
				//sets answer to previous question//
				response = "What level is Q - Siphoning Strike currently at?";
				AP = number;
				count++;
			}
			//level can only be between 1 and 5//
			else if (((count == 3) && (number >= 1)) && (number <= 5))
			{
				//sets answer to previous question//
				response = "How many stacks do you currrently have?";
				level = number;
				count++;
			}
			else if ((count == 4) && (number >= 0))
			{
				//sets answer to previous question//
				response = "How much armor does your target currently have? Say 0 to skip.";
				stacks = number;
				count++;
			}
			else if ((count == 5) && (number >= 0))
			{
				//sets answer to previous question//
				armor = number;
				damage = ((AD + 30 + (level-1)*20 + stacks)*(100D/(100+armor)));
				response = "Q - Siphoning Strike will deal " + damage + " physical damage.\n" +
						   "\tSay 'done' to exit, or 'retry' to redo the calculations.";
			}
			else
			{
					response = "Try again.";
			}
		}
		else if ((W > 0) && (calculate == 1))
		{
			if (count == 2)
			{
				response = "What level is W - Wither currently at?";
				//sets answer to previous question//
				AP = number;
				count++;
			}
			else if (((count == 3) && (number >= 1)) && (number <= 5))
			{
				response = "How many seconds has W - Wither lasted for?";
				//sets answer to previous question//
				level = number;
				count++;
			}
			else if (count == 4)
			{
				//sets answer to previous question//
				duration = number;
				effect = (35 + level*3*duration);
				response = "W - Wither will slow them for " + effect + "% and will slow their attack speed by " + (double)(effect/2) + "%.\n" +
						   "\t Say 'done' to exit, or 'retry' to redo the calculations.";
			}
			else
			{
				response = "Try again.";
			}
		}
		else if ((E > 0) && (calculate == 1))
		{
			if (count == 2)
			{
				response = "What level is E - Spirit Fire currently at?";
				AP = number;
				count++;
			}
			//level can only be between 1 and 5//
			else if (((count == 3) && (number >= 1)) && (number <= 5))
			{
				response = "How long have they stood in E - Spirit Fire for?";
				//sets answer to previous question//
				level = number;
				count++;
			}
			//duration can only be between 1 and 5 seconds//
			else if (((count == 4) && (number >= 1)) && (number <= 5))
			{
				response = "How much magic resistance does your target have?";
				//sets answer to previous question//
				duration = number;
				count++;
			}
			else if (count == 5)
			{
				response = "How much armor does your target have?";
				//sets answer to previous question//
				magicresist = number;
				count++;
			}
			else if (count == 6)
			{
				//sets answer to previous question//
				armor = number;
				effect = ((armor)/(double)(10+5*(level)));
				damage = (((55 + 40*level + AP*60/100)+(3+8*level+AP*12/100)*duration)/(double)(100/(100+magicresist)));
				response = "E - Spirit Fire will reduce their armor to " + effect + " and deal " + damage + " magic damage.\n" +
						   "\t Say 'done' to exit, or 'retry' to redo the calculations.";
			}
			else
			{
				response = "Try again.";
			}
		}
		else if ((R > 0) && (calculate == 1))
		{
			if (count == 2)
			{
				response = "What level is R - Fury of the Sands currently at?";
				//sets answer to previous question//
				AP = number;
				count++;
			}
			else if (((count == 3) && (number >= 1)) && (number <= 3))
			{
				response = "How long has R - Fury of the Sands been active?";
				//sets answer to previous question//
				level = number;
				count++;
			}
			else if (((count == 4) && (number >= 0)) && (number <= 15))
			{
				response = "How much health does your target have?";
				//sets answer to previous question//
				duration = number;
				count++;
			}
			else if (count == 5)
			{
				response = "How much magic resistance does your target have?";
				//sets answer to previous question//
				health = number;
				count++;
			}
			else if (count == 6)
			{
				//sets answer to previous question//
				magicresist = number;
				//skill formula//
				damage = ((health*((2+level+(AP/(double)100))/100))*duration);
				if (damage > (240*duration))
				{
					damage = (240*duration);
				}
				response = "R - Fury of the Sands will deal " + damage + " magic damage and will give " + (150 * (level+1)) + " bonus Health + " + (-5 + (20*level) + (1*level*duration)) + " armor and magic resistance.\n" +
						   "\t Say 'done' to exit, or 'retry' to redo the calculations.";
			}
		}
		//response choices//
		//sets all other variables to 0 to avoid overlaps and errors//
		else if (statement.equals("1"))
		{
			response = "You have chosen: Passive - Soul Eater\n" + "Nasus permanently has 10 / 15 / 20% bonus life steal at levels 1,7,13 respectively.\n";
		}
		else if (statement.equals("2"))
		{
			response = "You have chosen: Q - Siphoning Strike\n" + "COST: 20 MANA  COOLDOWN: 8/7/6/5/4\n" + 
				   "\n" + "Nasus's next basic attack within 10 seconds has 150 range and deals bonus physical damage.\n" + 
				   "\n" + "BONUS PHYSICAL DAMAGE:\n" + 
				   "30 / 50 / 70 / 90 / 110 (+ Siphoning Strike stacks)\n" + 
				   "\n" + "If Siphoning Strike kills its target, Nasus permanently gains 3 stacks (doubled to 6 if the victim is a champion, large minion, or large monster).\n\n" +
				   "Say 'calculate' to determine skill effectiveness";
			Q++;
			W = 0;
			E = 0;
			R = 0;
		}
		else if (statement.equals("3"))
		{
			response = "You have chosen: W - Wither\n " + 
				   "COST: 80 MANA  COOLDOWN: 15/14/13/12/11\n\n" +
				   "Nasus ages the target enemy champion, slowing them by 35%, increasing over 5 seconds, and slowering their attack speed by half the amount for the duration.\n\n" +
				   "ADDITIONAL SLOW PER SECOND:\n" +
				   "3 / 6 / 9 / 12 / 15%\n\n" +
				   "Say 'calculate' to determine skill effectiveness";
			W++;
			Q = 0;
			E = 0;
			R = 0;
		}
		else if (statement.equals("4"))
		{
			response = "You have chosen: E - Spirit Fire\n" +
				   "COST: 70/85/100/115/130 MANA  COOLDOWN: 12\n\n" + 
				   "Nasus unleashes a spirit flame at the target location, dealing magic damage to enemies in the area.\n" +
				   "The flame then remains for 5 seconds, dealing magic damage each second to all enemies within and reducing their armor while they remain.\n\n" +
				   "INITIAL MAGIC DAMAGE:        MAGIC DAMAGE PER SECOND:   ARMOR REDUCTION:\n" +
				   "55/95/135/175 /215(+60% AP)  11/19/27/35/43(+12% AP)    15/20/25/30/35% of target's armor\n\n" + 
				   "Say 'calculate' to determine skill effectiveness";
			E++;
			Q = 0;
			W = 0;
			R = 0;
		}
		else if (statement.equals("5"))
		{
			response = "You have chosen: R - Fury of the Sands\n" +
				   "COST: 100 MANA  COOLDOWN: 120\n\n" +
				   "Nasus empowers himself for 15 seconds, gaining bonus health, armor and magic resistance, increased size, and 175 range on his basic attacks for the duration.\n" +
				   "While Nasus is empowered, he deals magic damage to all enemies around him each second, capped at 240, gains bonus armor and magic resistance each second,\n" + 
				   "and reduces Siphoning Strike Siphoning Strike's cooldown by 50%.\n\n" +
				   "BONUS HEALTH:  BONUS RESISTS:                DAMAGE PER SECOND:\n" +                     
				   "300/400/600    15/35/55(+1/2/3 PER SECOND)   3/4/5(+1% per 100 AP) of target's max health\n\n" +
				   "Say 'calculate' to determine skill effectiveness";
			R++;
			Q = 0;
			W = 0;
			E = 0;
		}
		else if (statement.equals("6"))
		{
			response = 
				   "STANDARD AD:\n" +
				   "LVL: 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14 - 15 - 16 - 17 - 18\n" +
				   "     Q   W   E   Q   Q   R   Q   W   Q   W    R    W    W    E    E    R    E    E\n\n" +
				   "STANDARD AP:\n" +
				   "LVL: 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12 - 13 - 14 - 15 - 16 - 17 - 18\n" +
				   "     E   Q   E   W   E   R   E   Q   E   Q    R    Q    Q    W    W    R    W    W";
		}
		else 
		{
			response = "\tSorry I didn't understand that request.\n\n" + "Choose the ability you would like to know more about, " + name + ".\n" + "\t1. Passive - Soul Eater\n" + "\t2. Q - Siphoning Strike\n" + "\t3. W - Wither\n" + "\t4. E - Spirit Fire\n" + "\t5. R - Fury of the Sands\n" +"\t6. Skill Order\n" +"Or say 'done' to end this request.";
		}
	}
	private void Stats (String statement)
	{
		//sets stats to 0 to end stats program and goes back to normal responses//
		if (statement.equals("done"))
		{
			stats = 0;
			response = "Understood, is there anything else you would like to talk about?";
		}
		else if ((stats == 1) && (number >= 1) && (number <= 18))
		{
			//-1 for base stats at lvl 1//
			level = number-1;
			//formulas for stats//
			double Health = (561.2 + 90*level);
			double attdmg = (59.18 + 3.5*level);
			double as = (0.638 + 0.0348*(level)*0.638);
			double hr = (9.01 + 0.9*level);
			double arm = (24.88 + 3.5*level);
			double magr = (32.1 + 1.25*level);
			response =  "Health:\n" + 
						Health + " \n" + 
						"Attack Damage:\n" + 
						attdmg + " \n" + 
						"Attack Speed:\n" + 
						as + " \n" + 
						"Movement Speed:\n" + 
						"350\n" + 
						"Health Regen:\n" + 
						hr + " \n" + 
						"Armor:\n" + 
						arm + " \n" + 
						"Magic Resist:\n" + 
						magr +" \n" +
						"Type another level to check or 'done' to finish.";
		}

		else
		{
			response = "Level can only be between 1-18. Or say 'done' to finish.";
		}
	}
	
	//List of all random responses//
	
	private String [] randomNeutralResponses = {
			"Eons... pass like days.",
			"For centuries, I have watched.",
			"The wheel never stops turning.",
			"The past is a tapestry of what lies ahead.",
			"Burdens sleep best in their tombs.",
			"Note: (try typing 'stats' or 'abilities'"
	};
	private String [] randomAngryResponses = {"Do not try my patience.",
											  "Ambition is a mirage.",
											  "So eager to find meaning in nothingness.",
											  "The sky was naught but dying stars.",
											  "Your spirit is hollow.",
											  "Eternity is beyond your reach.",
											  "This too must end.",
											  "They toil to live in a fool's paradise.",
											  "Hope is the opiate of the frail.",
											  "Mankind's corruption spreads no further.",
											  "Try not the temper of the Ascended."};
	
	
	private String [] randomRenektonResponses = {"Rage burned away all that was good in you, my brother.", 
												 "Renekton was lost, long ago. You are but his pale shadow.",
												 "The butcher, carved to pieces.",
												 "The butcher, drowned in his own blood.",
												 "I will not hesitate to stop you, Renekton.",
												 "I will show you the meaning of butchery, brother.",
												 "You have wasted your power, Renekton. Now bleed for it."};
	
	private String [] randomDogResponses = {"Who let the dogs out? Woof. Woof. Woof.",
											"No, I will not fetch the ball.",
											"My bite is worse than my bark.",
											"Who's a good boy? I'm a good boy."};
	
	private String [] randomStacksResponses = {"More souls for the pyre.",
											   "Power... forged in soulfire!",
											   "Fodder for my wrath.",
											   "Every death feeds my fury."};
	}

