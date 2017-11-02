import java.util.Random;
//ss
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author 
 * @version October 2017
 */
public class ChatBotXChen
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0, scissorCount = 0, rockCount = 0 , paperCount = 0, nameCount = 0 ; 
	String name = "";
	
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Call me king, call me demon - water forgets the names of the drowned.\n" + "Now tell me, what shall I call you?";
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
		statement.toLowerCase();
		int statementlen = statement.length();
		String response = "";
		
		if (nameCount == 0)
		{
			name = statement;
			nameCount++;
			return "Well " + name.substring(0,name.length()-1) + " is it? I see nothing splendiferous in your offerings!" ;
		}
		if (nameCount == 1)
		{
			nameCount++;
			return  "Oh? Perhaps I misheard. Can you tell me again?";
			
		}
		if(nameCount == 2)
		{
			nameCount++; 
			if(findKeyword (statement, name ) >=0)	
			{
				nameCount++; 
				return "Ah yes." ;
			}
		response = getRandomNotName();
		emotion-- ; 
		return response ;
		}
		else if (findKeyword(statement, "lol") >= 0)
		{
			response = "I have tastes that aren't easily... pacified."; 
		}
		
		else if (statement.length() == 0)
		{
			response = "Cat got your tongue?";
		}
		
		else if (findKeyword(statement, "?") >= 0 && statement.length() <= 1)
		{
			response = "It is my mouth into which all travels end.";
                	
		}
		else if (findKeyword(statement, "no") >= 0 && statement.length() <= 3)
		{
			response = "You're duller than a broken sandwich.";
                	emotion--;
		}
		
		else if (findKeyword(statement, "food") >= 0)
		{
			response = "I lost you at food";
			emotion++;
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
		else if (findKeyword(statement, "rock") >=0 && statementlen <= 5)
		{
			response = getRandomResponseRPS();
			paperCount = 0 ; scissorCount = 0; 
			rockCount++;
			if ( response.equals("Rock"))
			{
				response = response + "\nTie!" ;
			}
			if ( response.equals("Paper"))
			{
				response = response + "\nYou Lose" ;
			}
			if ( response.equals("Scissor"))
			{
				response = response + "\nYou Win" ;
			}
		}
		else if (findKeyword(statement, "paper") >=0 && statementlen <= 6)
		{
			response = getRandomResponseRPS();
			scissorCount = 0; rockCount = 0;
			paperCount++;
			if ( response.equals("Rock"))
			{
				response = response + "\nYou Win" ;
			}
			if ( response.equals("Paper"))
			{
				response = response + "\nTie!" ;
			}
			if ( response.equals("Scissor"))
			{
				response = response + "\nYou Lose" ;
			}
		}
		else if (findKeyword(statement, "scissor") >=0 && statementlen <= 8)
		{
			response = getRandomResponseRPS();
			rockCount = 0; paperCount = 0;
			scissorCount++;
			if ( response.equals("Rock"))
			{
				response = response + "\nYou Lose" ;
			}
			if ( response.equals("Paper"))
			{
				response = response + "\nYou Win" ;
			}
			if ( response.equals("Scissor"))
			{
				response = response + "\nTie!" ;
			}
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
		if (emotion >=0 )
		{
		return "Let us peruse this establishment's fare." + "I will allow you "+ restOfStatement ;
		}
		if (emotion < 0 )
		{
		return "You are a malodorous offense to my palate! You're asking me for a " + restOfStatement + "?";
		}
		return " "; 
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
	private String getRandomNotName()
	{
		Random r = new Random ();
		return randomNotName [r.nextInt(randomNotName.length)];
	}
	private String getRandomResponseRPS ()
	{
		if (scissorCount >= 3)
		{
			return "Rock" ; 
		}
		if (paperCount >= 3)
		{
			return "Scissor" ; 
		}
		if (rockCount >= 3)
		{
			return "Paper" ; 
		}
		Random r = new Random ();
		return randomRPS [r.nextInt(randomRPS.length)];
	}
	private String getRandomResponse() 
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	private String [] randomNotName = {"So which time were you lying?", "That is not what you said the first time"}; 
	private String [] randomNeutralResponses = {"How about a game? Just say Rock!", "Hunger!", "Do you understand my powers?"};
	private String [] randomAngryResponses = {"This buffet exceeds repugnance!", "This buffet exceeds repugnance!", "Your mind is as clear as mud." , "Child, you're a couple cows short of a steak!"};
	private String [] randomHappyResponses = {"Delicious." , "A feast awaits."};
	private String [] randomRPS = { "Rock" , "Paper" , "Scissor"};
}
