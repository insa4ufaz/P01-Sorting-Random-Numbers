import java.util.*;

public class Client {
	public static int min;
	public static int max;
	// public static int[] myArray;

	public static void main(String[] args) throws InterruptedException {
		Scanner clientInput = new Scanner(System.in);
		boolean repeat = false;
		do {
			System.out.println("\t  ------------------------------------------");
			System.out.println("\t    SORTING 100,000 RANDOM NUMBERS PROGRAM");
			System.out.println("\t  ------------------------------------------");

			// ---------------------------------------------------------
			// USER DEFINABLE RANGE
			// ---------------------------------------------------------
			// Includes error handling for input format and input range.

			int xValue = 0, yValue = 0;
			System.out.print("\nDEFINE YOUR NUMBER RANGE: [MIN <= x <= MAX]");

			boolean correctRange = false;
			while (!correctRange) {
				boolean success = false;
				while (!success) {
					try {
						System.out.print("\nMIN  >> ");
						xValue = clientInput.nextInt();
						min = xValue; // load min range into class static variable min
						success = true;
					} catch (InputMismatchException e) {
						clientInput.next();
						System.out.println("You have entered an invalid data");
					}
				}

				success = false;
				while (!success) {
					try {

						System.out.print("\nMAX >> ");
						yValue = clientInput.nextInt();
						max = yValue; // load max range into class static variable max

						success = true;
					} catch (InputMismatchException e) {
						clientInput.next();
						System.out.println("You have entered an invalid data.");
					}
				}

				if (xValue >= yValue) {
					System.out.println("MAX must be greater than MIN");
				} else {
					correctRange = true;
				}
			}

			// ---------------------------------------------------------
			// SEND REQUEST TO LOAD BALANCE SERVER
			// ---------------------------------------------------------
			LoadBalancingServer lbServer = new LoadBalancingServer();
			lbServer.generateRandomNum(xValue, yValue); // added suppress warning

			System.out.println("------------------------------------------");
			System.out.println("             END OF PROGRAM               ");
			System.out.println("------------------------------------------");

			// ---------------------------------------------------------
			// RESEND REQUEST
			// ---------------------------------------------------------
			boolean correctInput = false;
			int resendRequest = 0;
			while (!correctInput) {
				try {
					System.out.println("Do you want so send another request?");
					System.out.println("   [1]: Yes [2]: No");
					resendRequest = clientInput.nextInt();
					correctInput = true;

					switch (resendRequest) {
						case 1: {
							repeat = true;
							break;
						}
						case 2: {

							System.out.println("------------------------------------------");
							System.out.println("    THANK YOU FOR USING THIS PROGRAM!     ");
							System.out.println("------------------------------------------");
							repeat = false;
							break;
						}

						default: {
							System.out.println("Invalid input. Enter again.");
							correctInput = false;
							break;
						}
					}
				}

				catch (InputMismatchException e) {
					clientInput.next();
					System.out.println("You have entered an invalid data.");
				}
			}

		} while (repeat);
		
		clientInput.close();
	}// end main()

}// end Client
