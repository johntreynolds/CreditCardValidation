import java.util.Scanner;
import java.util.ArrayList;
public class ValidationRunner
	{
		static Scanner scanner = new Scanner(System.in);

		public static void main(String[] args)
			{
				switch (giveChoices())
					{
					case 1:
						{
							System.out.println(numberValid(getCreditCardNumber()));
							break;
						}
					case 2:
						{
							
							break;
						}

					}
			}
		
		public static void printNumber(ArrayList <Integer> creditCardNumber)
			{
				for (int i = 0; i < 16; i++)
					{
						System.out.print(creditCardNumber.get(i));
					}
			}

		public static int giveChoices()
			{
				int choice = 0;

				System.out.println("This program will give you one of two options");
				boolean correctChoice = false;
				while (correctChoice == false)
					{

						System.out
								.println("1) Input a credit card number and I'll tell you if it is potentially valid");
						System.out
								.println("2) I will print you a list of credit card number that are potentially valid");
						System.out.print("Type your answer: ");
						choice = scanner.nextInt();
						scanner.nextLine();

						if (choice == 1 || choice == 2)
							{
								// No issue
								correctChoice = true;
							}
						else
							{
								System.out.println("Try again, must input the number as 1 or 2");

							}
					}
				return choice;
			}

		public static ArrayList<Integer> getCreditCardNumber()
			{
				
				System.out
				.println("Give me a credit card number and I will tell you if it is potentially valid");
				ArrayList <Integer> creditCardNumber = new ArrayList <Integer>();
				while (true)
					{
						
						System.out.println("Type the number with NO SPACES");
						System.out.print("Type the number: ");
						String input = scanner.nextLine();
						if (input.length() == 16)
							{
								
								for (int i = 0; i < input.length(); i++)
									{
										int number = Integer.parseInt(input.substring(i, i + 1));  
										creditCardNumber.add(0, number);
									}
								return creditCardNumber;
							}
						
						
						System.out.println("The number you entered is not in the proper format, please try again");
						System.out.println("Check for any spaces or the length of the card number");
					}
				

			}

		public static boolean numberValid(ArrayList<Integer> creditCardNumber)
			{
				boolean numberValid = false;
				for (int i = 0; i < 16; i++)
					{
						if (i % 2 == 1)
							{
								creditCardNumber.set(i, creditCardNumber.get(i) * 2);
							}
						else
							{
								
							}
						System.out.print(creditCardNumber.get(i));
					}
				
				
				
				
				return numberValid;
			}

	}
