public class Server2 {

	int[] arrayServer2 = new int[35000];

	// ---------------------------------------------------------
	// F(X): MAIN DRIVER
	// ---------------------------------------------------------
	public void mainDriver(int[] arrayServer2) throws InterruptedException {
		this.arrayServer2 = arrayServer2;
		long startTime = System.currentTimeMillis();
		int[] subArr1 = new int[arrayServer2.length / 2];
		int[] subArr2 = new int[arrayServer2.length - arrayServer2.length / 2];

		System.arraycopy(arrayServer2, 0, subArr1, 0, arrayServer2.length / 2);
		System.arraycopy(arrayServer2, arrayServer2.length / 2, subArr2, 0,
				arrayServer2.length - arrayServer2.length / 2);

		// ---------------------------------------------------------
		// START SORTING IN THREADS
		// ---------------------------------------------------------
		Server2Worker runner1 = new Server2Worker(subArr1);
		Server2Worker runner2 = new Server2Worker(subArr2);
		runner1.start();
		runner2.start();
		runner1.join();
		runner2.join();

		// ---------------------------------------------------------
		// MERGE THE SORTED THREADS
		// ---------------------------------------------------------
		finalMerge(runner1.getInternal(), runner2.getInternal());

		// ---------------------------------------------------------
		// RECORD TIME TAKEN
		// ---------------------------------------------------------
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		System.out.println(" > 2-threaded MergeSort in Server 2 takes: " + (float) elapsedTime / 1000 + " seconds");
		System.out.println("-----------------------------------------------------------");

	}// end Driver

	// ---------------------------------------------------------
	// F(X): FINAL SORTING
	// ---------------------------------------------------------
	public static void finalMerge(int[] a, int[] b) {
		int[] result = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int r = 0;

		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				result[r] = a[i];

				i++;
				r++;

			} else {
				result[r] = b[j];

				j++;
				r++;
			}
			if (i == a.length) {
				while (j < b.length) {
					result[r] = b[j];

					r++;
					j++;
				}
			}
			if (j == b.length) {
				while (i < a.length) {
					result[r] = a[i];

					r++;
					i++;
				}
			}

		}

		// ---------------------------------------------------------
		// COMPILE RESULTS TO LOAD BALANCE SERVER
		// ---------------------------------------------------------
		LoadBalancingServer lbServer = new LoadBalancingServer();
		lbServer.getResultServer2(result);

	}// end finalMerge

}// end Server1 class

class Server2Worker extends Thread {
	private int[] internal;

	public int[] getInternal() {
		return internal;
	}

	public void mergeSort(int[] array) {
		if (array.length > 1) {
			int[] left = leftHalf(array);
			int[] right = rightHalf(array);

			mergeSort(left);
			mergeSort(right);

			merge(array, left, right);

		}
	}// end mergeSort()

	public int[] leftHalf(int[] array) {
		int size1 = array.length / 2;
		int[] left = new int[size1];
		for (int i = 0; i < size1; i++) {
			left[i] = array[i];
		}
		return left;
	}

	public int[] rightHalf(int[] array) {
		int size1 = array.length / 2;
		int size2 = array.length - size1;
		int[] right = new int[size2];
		for (int i = 0; i < size2; i++) {
			right[i] = array[i + size1];
		}
		return right;
	}

	public void merge(int[] result, int[] left, int[] right) {
		int i1 = 0;
		int i2 = 0;

		for (int i = 0; i < result.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
				result[i] = left[i1];
				i1++;
			} else {
				result[i] = right[i2];
				i2++;
			}
		}
	}

	Server2Worker(int[] arr) {
		internal = arr;
	}

	public void run() {
		mergeSort(internal);
	}

}// end worker Class thread
