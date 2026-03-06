import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ValidationRunner
	{
		static Scanner scanner = new Scanner(System.in);

		public static void main(String[] args) throws IOException
			{
				switch (giveChoices())
					{
					case 1:
						{
							String cardNumber = getCreditCardNumber();
							ArrayList<Integer> numbers = stringToArrayList(cardNumber);
							if (numberValid(numbers))
								{
									System.out.println(cardNumber + " is a potentially valid credit card number.");
								}
							else
								{
									System.out.println(cardNumber + " is NOT a potentially valid credit card number.");
								}
							break;
						}
					case 2:
						{

							printTXT(txtValidNumbers());
							break;
						}

					}
			}

		public static void printNumber(ArrayList<Integer> creditCardNumber)
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
				System.out.println("");
				boolean correctChoice = false;
				while (correctChoice == false)
					{
						// The try catch statement is here for if you accidentally input the card number
						// instead of the choice. I definitely didn't do this like 10 times during
						// testing. nuh uh
						try
							{
								System.out.println(
										"1) Input a credit card number and I'll tell you if it is potentially valid");
								System.out.println(
										"2) I will print you a list of credit card numbers, telling you which ones are valid and which ones are invalid.");
								System.out.println("This comes from a list of preexisting numbers");
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
							} catch (InputMismatchException e)
							{
								System.out.println("Type mismatch exception");
								System.out.println("Make sure you only type in a 1 or 2");
								System.out.println("");
								scanner.nextLine();
							}
					}

				return choice;
			}

		public static String getCreditCardNumber()
			{

				System.out.println("Give me a credit card number and I will tell you if it is potentially valid");
				// ArrayList<Integer> creditCardNumber = new ArrayList<Integer>();
				while (true)
					{

						System.out.println("Type the number with NO SPACES");
						System.out.print("Type the number: ");
						String input = scanner.nextLine();
						if (input.length() == 16)
							{

								return input;
							}

						System.out.println("The number you entered is not in the proper format, please try again");
						System.out.println("Check for any spaces or the length of the card number");
					}

			}

		public static ArrayList<Integer> stringToArrayList(String input)
			{
				ArrayList<Integer> creditCardNumber = new ArrayList<Integer>();

				for (int i = 0; i < input.length(); i++)
					{
						int number = Integer.parseInt(input.substring(i, i + 1));
						creditCardNumber.add(number);
					}

				return creditCardNumber;
			}

		public static boolean numberValid(ArrayList<Integer> creditCardNumber)
			{
				int sum = 0;
				// ArrayList<Integer> originalNumber = new ArrayList<Integer>(creditCardNumber);
				boolean numberValid = false;
				for (int i = 0; i < 16; i++)
					{
						if (i % 2 == 0)
							{
								int doubledNumber = creditCardNumber.get(i) * 2;
								if (doubledNumber < 10)
									{
										creditCardNumber.set(i, doubledNumber);
									}
								else
									{
										int stripSum = 0;
										while (doubledNumber > 0)
											{
												stripSum += doubledNumber % 10;
												doubledNumber /= 10;
											}
										creditCardNumber.set(i, stripSum);
									}
							}
						sum += creditCardNumber.get(i);
					}
				if (sum % 10 == 0)
					{
						numberValid = true;
					}

				return numberValid;
			}

		public static ArrayList<Cards> txtValidNumbers() throws IOException
			{
				System.out.println("");
				ArrayList<Cards> numbers = new ArrayList<Cards>();
				try
					{
						Scanner myFile = new Scanner(new File("CreditCards.csv"));
						while (myFile.hasNextLine())
							{
								String line = myFile.nextLine();

								ArrayList<Integer> singleCardCheck = stringToArrayList(line);
								numbers.add(new Cards(line, numberValid(singleCardCheck)));

							}
					} catch (IOException e)
					{
						System.out.println("Could not find .txt file");
					}
				return numbers;

			}

		public static void printTXT(ArrayList<Cards> numbers)
			{
				for (int i = 0; i < numbers.size(); i++)
					{
						if (numbers.get(i).isValid() == true)
							{
								System.out.println(
										i + 1 + ") " + numbers.get(i).getNumber() + " is a potentially valid number");
							}
						if (numbers.get(i).isValid() == false)
							{
								System.out.println(i + 1 + ") " + numbers.get(i).getNumber()
										+ " is NOT a potentially valid number");
							}
					}
			}
	}
