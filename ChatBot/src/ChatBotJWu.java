import java.util.Random;

public class ChatBotJWu {
	
	int emotion = 0;
	int saidName = 0;
	String name = "";
	int abilities = 0;
	
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
		String response = "";
		if (saidName == 0) 
		{
			name = statement;
			response = "Ah yes, " + name + ", a fitting name for the summoner that commands a great being such as myself.";
			saidName++;
		}
		else if (abilities == 1)
		{		
			if (statement.equals("1"))
			{
				response = "You have chosen: Passive - Soul Eater\n" + "Nasus permanently has 10 / 15 / 20% bonus life steal at levels 1,7,13 respectively.\n";
			}
			else if (statement.equals("2"))
			{
				response = "You have chosen: Q - Siphoning Strike\n" + "COST: 20 MANA  COOLDOWN: 8/7/6/5/4\n" + "\n" + "Nasus's next basic attack within 10 seconds has 150 range and deals bonus physical damage.\n" + "\n" + "BONUS PHYSICAL DAMAGE:\n" + 
						"30 / 50 / 70 / 90 / 110 (+ Siphoning Strike stacks)\n" + "\n" + "If Siphoning Strike kills its target, Nasus permanently gains 3 stacks (doubled to 6 if the victim is a champion, large minion, or large monster).\n";
			}
			else if (statement.equals("3"))
			{
				response = "";
			}
			else if (statement.equals("4"))
			{
				response = "";
			}
		}
		else if (statement.length() == 0)
		{
			response = "Such hollow, empty minds.";
		}
		else if (findKeyword(statement, "abilities") >= 0)
		{
			abilities++;
			response = "Choose the ability you would like to know more about, " + name + ".\n" + "\t1. Passive - Soul Eater\n" + "\t2. Q - Siphoning Strike\n" + "\t3. W - Wither\n" + "\t4. E - Spirit Fire\n" + "\t5. R - Fury of the Sands\n";
		}
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "There is such potential in one mortal life; you have wasted yours.";
			emotion--;
		}  
		else if (name.equals("renekton") || name.equals("Renekton"))
		{
			response = Renekton();
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

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
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
		return "Why do you want to " + restOfStatement + "?";
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
		return "Would you really be happy if you had " + restOfStatement + "?";
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
	
	private String [] randomNeutralResponses = {
			"Eons... pass like days.",
			"Perhaps Shurima was meant to fall.",
			"For centuries, I have watched.",
			"The wheel never stops turning.",
			"We begin a new cycle!",
			"I walk behind none.",
			"I am the final arbiter.",
			"The past is a tapestry of what lies ahead.",
			"Burdens sleep best in their tombs.", 
			"Fate is a manifestation of my will."
	};
	private String [] randomAngryResponses = {"Do not try my patience.",
											  "Ambition is a mirage.",
											  "Filth of the earth.",
											  "So eager to find meaning in nothingness.",
											  "I will bury you alive.",
											  "The sky was naught but dying stars.",
											  "Your spirit is hollow.",
											  "Eternity is beyond your reach.",
											  "This too must end.",
											  "Life is part of a cycle. Yours is over.",
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

