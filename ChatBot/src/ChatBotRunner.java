import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Mr. Levin
 * @version September 2017
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	static boolean chosen = false;
	public static void main(String[] args)
	{
/**		String name = "";
		System.out.println("What is your name?");
		name = response;
*/		while (!chosen)
		{
			System.out.println("Welcome, Summoner, to Runeterra. Please select the champion you wish to bring forth onto Summoners Rift.\n" + "\t 1. Veigar, The Tiny Master of Evil\n" + "\t 2. Amumu, The Sad Mummy\n" + "\t 3. Tahm Kench, The River King\n" + "\t 4. Nasus, The Creator of The Sands\n" + "\t Type " + "Lets talk again some time! " + "to leave\n");
			Scanner Summoner = new Scanner(System.in);
			String response = Summoner.nextLine();
	//Names are case sensitive
			
			if (response.equals("1") || response.equals("Veigar"))
			{
				ChatBotJWong chatbot1 = new ChatBotJWong();
				System.out.println (chatbot1.getGreeting());
				String statement = Summoner.nextLine();
				while(!statement.toLowerCase().equals("bye"))
				{
					System.out.println(chatbot1.getResponse(statement));
					statement = Summoner.nextLine();
				}
			}
			 if (response.equals("2") || response.equals("Amumu"))
			{
				ChatBotCChen chatbot2 = new ChatBotCChen();
				System.out.println (chatbot2.getGreeting());
				String statement = Summoner.nextLine();
				while(!statement.toLowerCase().equals("bye"))
				{
					System.out.println(chatbot2.getResponse(statement));
					statement = Summoner.nextLine();
				}
			 
			}
			 if (response.equals("3") || response.equals("Tahm Kench"))
				{
					ChatBotXChen chatbot3 = new ChatBotXChen();
					System.out.println (chatbot3.getGreeting());
					String statement = Summoner.nextLine();
					while(!statement.toLowerCase().equals("bye"))
					{
						System.out.println(chatbot3.getResponse(statement));
						statement = Summoner.nextLine();
					}
				}
			 if (response.equals("4") || response.equals("Nasus"))
				{
					ChatBotJWu chatbot4 = new ChatBotJWu();
					System.out.println (chatbot4.getGreeting());
					String statement = Summoner.nextLine();
					while(!statement.toLowerCase().equals("bye"))
					{
						System.out.println(chatbot4.getResponse(statement));
						statement = Summoner.nextLine();
					}
				}
			 if (response.toLowerCase().equals("Lets talk again some time!") || response.toLowerCase().equals("bye"))
			 {
				 chosen = true;
			 }
	/**
	*	ChatBotJWu chatbot3 = new ChatBotJWu();
	*	ChatBotXChen chatbot4 = new ChatBotXChen();
	*
	*	System.out.println (chatbot1.getGreeting());
	*	Scanner in = new Scanner (System.in);
	*	String statement = in.nextLine();
	*
	*
	*	}
	*	while (!statement.equals("Bye"))
	*	{
	*		System.out.println (chatbot1.getResponse(statement));
	*		statement = in.nextLine();
	*/
		}
	}

}
