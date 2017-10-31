 import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Cristina Chen
 * @version October 2017
 */
public class ChatBotCChen
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	String name = "";
	int emotion = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "I thought you'd never pick me.";
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
		
		if (statement.length() == 0)
		{
			response = "Hey, come back.";
		}

		else if (findKeyword(statement, "hello") >= 0)
		{
			response = "Yay, you talked to me! What is your name?";
                	emotion++;
		}
		
		else if (findKeyword(statement, "hey") >= 0)
		{
			response = "Yay, you talked to me! What is your name?";
                	emotion++;
		}
		
		else if (findKeyword(statement, "hi") >= 0)
		{
			response = "Yay, you talked to me! What is your name?";
                	emotion++;
		}
		
		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Aww.";
                	emotion--;
		}
		
		else if (findKeyword(statement, "friend") >= 0)
		{
			response = "Let me give you a hug.";
					emotion++;
		}

		else if (findKeyword(statement, "What are you?") >= 0)
		{
			response = "I used to be a Yordle, but I'm just a cursed mummy now.\r\n"
					+ "I don't remember anything from my past, \r\n"
					+ "all I hear are rumors and tales of me. \r\n";
					emotion++;
		}

		else if (findKeyword(statement, "Where are you from?") >= 0)
		{
			response = "I was born in Shurima, a place full of sun and sand.";
					emotion++;
		}

		else if (findKeyword(statement, "What are you scared of?") >= 0)
		{
			response = "I'm scared of no one becoming my friend.";
					emotion++;
		}

		else if (findKeyword(statement, "What are you afraid of?") >= 0)
		{
			response = "I'm afraid that no one will want to friends with me.";
					emotion++;
		}
		
		else if (findKeyword(statement, "Sing me your song") >= 0)
		{
			response = "Every child in Valoran has heard the tale before\r\n" + 
					"About the cursed mummy boy who felt his heart no more\r\n" + 
					"So sad and lorn, the helpless lad, Amumu was his name\r\n" + 
					"He ventured out to find a friend and learn about his bane\r\n" + 
					"\r\n" + 
					"For many years, young Amumu traveled through the lands\r\n" + 
					"Determined to make friends, if only they would understand\r\n" + 
					"But even when Amumu stood upon the ledge of home\r\n" + 
					"His hope would disappoint him, and he would remain alone\r\n" + 
					"\r\n" + 
					"But then the curse began to whisper in his ear\r\n" + 
					"And would confirm what was Amumu's biggest fear\r\n" + 
					"It pledged that never shall someone become his friend\r\n" + 
					"It pledged that he shall be alone until his end\r\n" + 
					"\r\n" + 
					"The sorrow and despair\r\n" + 
					"Became too much to bear\r\n" + 
					"The moment when Amumu realized what he had done\r\n" + 
					"Too late it was, for him, for them, the evil curse had won\r\n" + 
					"The anger and the anguish overwhelmed his fragile soul\r\n" + 
					"And caused a wicked tantrum that he never could control";
                	emotion++;
		}

		// Response transforming I want to statement
		//return nice to meet you is name
		else if (findKeyword(statement, "My name is", 0) >= 0)
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
		int psn = findKeyword (statement, "My name is ", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Nice to meet you " + restOfStatement + "!";
	
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
	private String getRandomResponse ()
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
	
	private String [] randomNeutralResponses = {"Lets be friends forever.",
			"Hmmm.",
			"Do you really think so?",
			"Okay."
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	
}
