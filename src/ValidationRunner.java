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
					case 3:
						{
							int totalTries = 0;
							int totalCorrect = 0;
							int cardTarget = askForNumberOfRandomCards();
							long startTime = System.currentTimeMillis();
							while (totalCorrect < cardTarget)
								{
									ArrayList<Integer> currentCard = randomCardNumber();
									// Checks it against a copy of current card. Not an issue below b/c it prints the string in the arraylist, not the ArrayList<Integer at a specific point
									if (numberValid(new ArrayList<Integer>(currentCard)))
										{
											totalTries ++;
											totalCorrect ++;
											printNumber(currentCard);
											
										}
									else
										{
											totalTries++;
										}
								}
							printTelemetry(totalTries, startTime, totalCorrect);
							break;
						}

					}
			}

		// Prints integer
		public static void printNumber(ArrayList<Integer> creditCardNumber)
			{
				for (int i = 0; i < 16; i++)
					{
						System.out.print(creditCardNumber.get(i));
					}
				System.out.println("");
				
			}

		public static int giveChoices()
			{
				int choice = 0;

				System.out.println("This program will give you one of three options");
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
								System.out.println(
										"3) I will randomly generate some brand new card numbers. Telemetry will be included");
								System.out.print("Type your answer: ");
								choice = scanner.nextInt();
								scanner.nextLine();

								if (choice == 1 || choice == 2 || choice == 3)
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
								System.out.println("Make sure you only type in a 1, 2, or 3");
								System.out.println("");
								scanner.nextLine();
							}
					}

				return choice;
			}

		// Get user input for credit card number
		public static String getCreditCardNumber()
			{

				System.out.println("Give me a credit card number and I will tell you if it is potentially valid");
				
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

		// Changes the string input into the ArrayList<Integer>
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

		// Inputs an ArrayList<Integer> and says if it's valid or not
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

		// Adds cards and their validity to an arraylist to be used later
		public static ArrayList<Cards> txtValidNumbers() throws IOException
			{
				System.out.println("");
				ArrayList<Cards> numbers = new ArrayList<Cards>();
				try
					{
						Scanner myFile = new Scanner(new File("CreditCards.txt"));
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

		// Used for saying if numbers from the .txt file are valid or not
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

		public static ArrayList<Integer> randomCardNumber()
			{

				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < 16; i++)
					{
						list.add((int) (Math.random() * 10));
					}

				return list;
			}

		public static int askForNumberOfRandomCards()
			{
				System.out.println("How many random card numbers do you want?");
				int input = scanner.nextInt();
				return input;
			}

		// Creates telemetry for creation
		public static void printTelemetry(int totalTries, long startTime, int cardsFound)
			{
				long endTime = System.currentTimeMillis();
				double seconds = (endTime-startTime) / 1000.0;
				
				System.out.println("");
				System.out.println("Here's the data from the generation...");
				System.out.println("Valid Cards Generated: " + cardsFound);
				System.out.println("Total Card Generation Attempts: " + totalTries);
				System.out.println("Elapsed Generation Time: " + seconds + " seconds");
				double percent = (cardsFound / (double)totalTries)*100;
				System.out.printf("%.2f%% of the trials generated a valid card number.\n", percent);
			}
	}
