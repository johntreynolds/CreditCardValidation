import java.util.Scanner;

public class ValidationRunner
	{

		public static void main(String[] args)
			{
				System.out.println(giveChoices());
				System.out.println(getCreditCardNumber());
			}

		public static int giveChoices()
			{
				int choice = 0;
				Scanner choiceSelection = new Scanner(System.in);
				System.out.println("This program will give you one of two options");
				while (choice == 0)
					{

						System.out
								.println("1) Input a credit card number and I'll tell you if it is potentially valid");
						System.out
								.println("2) I will print you a list of credit card number that are potentially valid");
						System.out.print("Type your answer: ");
						choice = choiceSelection.nextInt();

						if (choice == 1 || choice == 2)
							{
								// No issue
							}
						else
							{
								System.out.println("Try again, must input the number as 1 or 2");
								choice = 0;
							}
					}
				return choice;
			}

		public static int getCreditCardNumber()
			{
				Scanner inputCard = new Scanner(System.in);
				int creditCardNumber = 0;
				System.out.println("Give me a credit card number and I will tell you if it is potentially valid");
				System.out.print("Type the number: ");
				
				creditCardNumber = inputCard.nextInt();
				return creditCardNumber;
			}

		public static boolean numberValid(int creditCardNumber)
			{
				boolean numberValid = false;
				return numberValid;
			}

	}
