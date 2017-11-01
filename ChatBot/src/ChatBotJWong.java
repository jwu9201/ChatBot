import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Joyin Wong
 * @version October 2017
 */
public class ChatBotJWong
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	String name = "";
	int saidName = 0;
	boolean arcade = false;
	int health = 10;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Who says evil needs to come in a fearsome-looking package? \n Now tell me, Summoner, what is your name?";
		//figure out how to save name
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
		//doesnt switch out of arcade mode even though i made it set to false :L
		
		if (health == 0)
		{
			System.out.println("What? Impossible... No! No! No! This isn't over! ARGHHHHHHHHHHHH");
			arcade = false;
			
		}
	
		
		if (saidName == 0)
		{
			name = statement;
			response = name + ", your commands tire me.";
			saidName++;
		}
		
		else if (findKeyword(statement, "Engage") >= 0)
		{
			response = "Unimaginable is the power of Final Boss Veigar! \n Welcome to your doom! \n(trying typing punch, slash, kick, shoot, or magic!)";
			arcade = true;
		}
		else if (statement.length() == 0)
		{
			response = "Even death trembles in my presense! " + name + ", stop trembling and speak.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "You deny the darkness in your soul," + name + "! You deny your power!";
		}
		
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "You won't be Levin for long!";
			emotion++;
		}
		
		else if (findKeyword(statement, "human") >= 0)
		{
			response = "Filthy humans! Your soul will come to serve me.";
			emotion--;
		}
		
		else if (findKeyword(statement.toLowerCase(), "Noxus") >= 0)
		{
			response = "Damn Noxians, they did this to me!";
			emotion-=2;
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
		
		else if ((statement.length() == 0) && (arcade = true))
		{
			response = "I can't let you do that! Say your final words!";
		}

		else if ((findKeyword(statement, "no") >= 0) && (arcade = true))
		{
			response = "You dare defy me?!" + name + "! It's game over... for you!";
		}
	
		else if ((findKeyword(statement, "levin") >= 0) && (arcade = true))
		{
			response = "You will be Levin this level in pain!";
			emotion++;
		}
	
		else if ((findKeyword(statement, "I give up") >= 0) && (arcade = true))
		{
			response = "My strength is unparalleled! My every victory symbolizes the crumbling of justice!";
			emotion++;
		}
	
		else if (findKeyword(statement.toLowerCase(), "punch") >= 0)
		{
			response = "You! Curse you! \n Veigar has lost some HP. \nCurrent health: " + health;
			health--;
		}

		else if ((findKeyword(statement.toLowerCase(), "magic") >= 0) && (arcade = true))
		{
			response = "Magic?! How dare you use my powers! \n Veigar has lost some HP! \nCurrent health: " + health;
			health--;
		}

		else if ((findKeyword(statement.toLowerCase(), "shoot") >= 0) && (arcade = true))
		{
			response = "I am all-powerful! Don't point your lowly guns at me! \n Vegiar has lost some HP. \nCurrent health: " + health;
			health--;
		}

		else if ((findKeyword(statement.toLowerCase(), "slash") >= 0) && (arcade = true))
		{
			response = "I won't be defeated so easily! Your blades do nothing! \n Veigar has lost some HP. \nCurrent health: " + health;
			health--;
		}

		else if ((findKeyword(statement.toLowerCase(), "kick") >= 0) && (arcade = true))
		{
			response = "A battle you have no chance of winning! Don't kick me, " + name + "! \n Veigar has lost some HP. \nCurrent health: " + health;
			health--;
		}
		
		else if (arcade = true)
		{
			response = getRandomArcadeResponse();
		}
		else
		{
			response = getRandomResponse();
		}
		
		return response;
		
		
	}
	
/**	public String getArcadeResponse(String statement)
	{

			String response = "";
		
			else if ((statement.length() == 0) && (arcade = true))
			{
				response = "I can't let you do that! Say your final words!";
			}

			else if ((findKeyword(statement, "no") >= 0) && (arcade = true))
			{
				response = "You dare defy me?!" + name + "! It's game over... for you!";
			}
		
			else if ((findKeyword(statement, "levin") >= 0) && (arcade = true))
			{
				response = "You will be Levin this level in pain!";
				emotion++;
			}
		
			else if ((findKeyword(statement, "I give up") >= 0) && (arcade = true))
			{
				response = "My strength is unparalleled! My every victory symbolizes the crumbling of justice!";
				emotion++;
			}
		
			else if (findKeyword(statement.toLowerCase(), "punch") >= 0)
			{
				response = "You! Curse you! \n Veigar has lost some HP";
				health--;
			}

			else if ((findKeyword(statement.toLowerCase(), "magic") >= 0) && (arcade = true))
			{
				response = "Magic?! I rule all of magic you fool! \n No damage dealt!";
			}

			else if ((findKeyword(statement.toLowerCase(), "shoot") >= 0) && (arcade = true))
			{
				response = "I am all-powerful! Don't point your lowly guns at me! \n Vegiar has lost some HP";
				health--;
			}

			else if ((findKeyword(statement.toLowerCase(), "slash") >= 0) && (arcade = true))
			{
				response = "I won't be defeated so easily! Your blades do nothing! \n Veigar has lost some HP";
				health--;
			}

			else if ((findKeyword(statement.toLowerCase(), "kick") >= 0) && (arcade = true))
			{
				response = "A battle you have no chance of winning! Don't kick me, " + name + "! \n Veigar has lost some HP";
				health--;
			}
		
			else if (arcade = true)
			{
				response = getRandomArcadeResponse();
			}
		return response;
	}
*/	
	
	
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
		return "Why would a lowly being like you want " + restOfStatement + "?";
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
		return "I do not have time to grant something trivial such as give you " + restOfStatement + ".";
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
		return "How dare you " + restOfStatement + " me?";
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

	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < -1)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = 
		{"Stalking prey again?",
			"Hmmm.",
			"The magic it calls to me!",
			"You don't say...",
			"It's all boolean to me, you Fool-ean",
			"I can see the fear in your heart.",
			"Could you say that again? I wasn't caring enough.",
			"Ready to face the Final Boss? Type \"Engage\" to being the Boss Battle!"
		};
	
	private String [] randomAngryResponses = 
		{"What's black and blue and is about to show you the definition of pain?!",
			"I am evil! Stop laughing!",
			"The rage consumes me!",
			"Know that if the tables were turned, I would show you no mercy!"
		};
	
	private String [] randomHappyResponses = 
		{"D O O M, what's that spell?",
			"Today is a good day, to die!",
			"Suffering awaits."
		};
	

	private String getRandomArcadeResponse ()
	{
		Random r = new Random ();
		if ((emotion == 0) && (arcade = true))
		{	
			return randomNeutralArcadeResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if ((emotion < -1) && (arcade = true))
		{	
			return randomAngryArcadeResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyArcadeResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralArcadeResponses =
		{"Your journey ends here, " + name + "!",
			"Not even death can save you from me!",
			"Get lost. You can't compare with my powers!",
			"I'll make weapons from your bones!",
			"Your entire life has been a mathematical error! A bug in the coding!",
			"Could you say that again? My code doesn't interpret loser.",
			"Join me... or die!",
			"Do you realise who you're dealing with?"
		};
	private String [] randomAngryArcadeResponses = 
		{"What are you? A miserable pile of pixels.",
			"Don't get mad, get sadistic!",
			"I have fury!",
			"My patience is wearing thin!"
		};
	private String [] randomHappyArcadeResponses = 
		{"D E F E A T, what's that spell?",
			"I hear the locals call me some kind of baron.",
			"The feelings of terror only prove your inferiority!"
		};
	
}
