public class LoadBalancingServer {

	// Generate 100,000 random integers in an array, based on user defined range:
	public void generateRandomNum(int min, int max) throws InterruptedException // accept parameters from client
	{
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("           GENERATING 100,000 RANDOM INTEGERS....    ");
		System.out.println("-----------------------------------------------------------");

		// An array to store all 100,000 integers
		int[] numberArray = new int[100000];

		// Generate 100,000 integers and load into numberArray[]
		for (int i = 0; i < 100000; i++) {
			int randomNumber = (int) ((Math.random() * ((max - min) + 1)) + min);
			numberArray[i] = randomNumber;
		}

		// Pass generated integers into getSortedResults()
		getSortedResults(numberArray);
	}

	// Distribute tasks to Server1,2,3 for sorted results of generated integers.
	public void getSortedResults(int[] numberArray) throws InterruptedException {
		// ------------------------------------------------------------------------------------------
		// CREATE SEPARATE ARRAYS OF INTEGERS TO BE DISTRIBUTED AMONG SERVERS
		// ------------------------------------------------------------------------------------------
		int[] firstUnsortedArray = new int[35000]; // Create first group of numbers for Server 1
		int[] secondUnsortedArray = new int[35000]; // Create second group of numbers for Server 2
		int[] thirdUnsortedArray = new int[30000]; // Create third group of numbers for Server 3

		// Load integers from the complete array into the first number array (SERVER1)
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("      SENDING FIRST GROUP (35,000 INTEGERS) TO SERVER 1..    ");
		System.out.println("-----------------------------------------------------------");
		int counter1 = 0;
		for (int i = 0; i < 35000; i++) {
			firstUnsortedArray[i] = numberArray[i];
			// System.out.print("Array[" +i+ "] : " +firstUnsortedArray[i]+ "\t");
			counter1++;
			if (counter1 == 500) {
				// System.out.println("");
				counter1 = 0;
			}
		}

		// Load integers from the complete array into the second number array (SERVER2)
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("      SENDING SECOND GROUP (35,000 INTEGERS) TO SERVER 2..   ");
		System.out.println("-----------------------------------------------------------");
		int j = 35000; // increase counter to 35000th integer
		int counter2 = 0;
		for (int i = 0; i < 35000; i++) {
			secondUnsortedArray[i] = numberArray[j];
			// System.out.print(" Array[" +i+ "] : " +secondUnsortedArray[i]);
			j++;

			counter2++;
			if (counter2 == 500) {
				// System.out.println("");
				counter2 = 0;
			}
		}

		// Load integers from the complete array into the third number array (SERVER3)
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("      SENDING THIRD GROUP (30,000 INTEGERS) TO SERVER 3..    ");
		System.out.println("-----------------------------------------------------------");
		j = 70000;// increase counter to 70000th integer
		int counter3 = 0;
		for (int i = 0; i < 30000; i++) {
			thirdUnsortedArray[i] = numberArray[j];
			// System.out.print(" Array[" +i+ "] : " +thirdUnsortedArray[i]);
			j++;

			counter3++;
			if (counter3 == 500) {
				// System.out.println("");
				counter3 = 0;
			}
		}

		System.out.println("\n-----------------------------------------------------------");
		System.out.println("\n             DISTRIBUTING TASKS TO SERVERS.....              ");
		System.out.println("\n-----------------------------------------------------------");
		// ------------------------------------------------------------------------------------------
		// CREATE SERVERS
		// ------------------------------------------------------------------------------------------
		Server1 server1 = new Server1();
		Server2 server2 = new Server2();
		Server3 server3 = new Server3();

		// ------------------------------------------------------------------------------------------
		// DISTRIBUTE THE INTEGER ARRAYS AMONG SERVERS
		// ------------------------------------------------------------------------------------------

		server1.mainDriver(firstUnsortedArray);

		server2.mainDriver(secondUnsortedArray);

		server3.mainDriver(thirdUnsortedArray);

	}// end getSortedResults()

	// ------------------------------------------------------------------------------------------
	// RECIEVE COMPILED RESULTS FROM ALL SERVERS
	// ------------------------------------------------------------------------------------------
	public void getResultServer1(int[] resultServer1) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("                  RESULTS FROM SERVER 1                    ");
		System.out.println("-----------------------------------------------------------");

		int counter = 0;
		int n = resultServer1.length;
		for (int i = 0; i < n; ++i) {
			System.out.print("[" + i + "]:" + resultServer1[i] + "\t");
			counter++;
			if (counter == 100) {
				System.out.println("");
				counter = 0;
			}
		}
		System.out.println("-----------------------------------------------------------");
	}

	public void getResultServer2(int[] resultServer2) {
		System.out.println("\n\n\n-----------------------------------------------------------");
		System.out.println("                  RESULTS FROM SERVER 2                    ");
		System.out.println("-----------------------------------------------------------");

		int counter = 0;
		int n = resultServer2.length;
		for (int i = 0; i < n; ++i) {
			System.out.print("[" + i + "]:" + resultServer2[i] + "\t");
			counter++;
			if (counter == 100) {
				System.out.println("");
				counter = 0;
			}
		}
		System.out.println("-----------------------------------------------------------");

	}

	public void getResultServer3(int[] resultServer3) {
		System.out.println("\n\n\n-----------------------------------------------------------");
		System.out.println("                  RESULTS FROM SERVER 3                    ");
		System.out.println("-----------------------------------------------------------");

		int counter = 0;
		int n = resultServer3.length;
		for (int i = 0; i < n; ++i) {
			System.out.print("[" + i + "]:" + resultServer3[i] + "\t");
			counter++;
			if (counter == 100) {
				System.out.println("");
				counter = 0;
			}
		}

		System.out.println("-----------------------------------------------------------");
	}

}// end LoadBalanceServer
