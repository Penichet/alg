
public final class SelectionSort {
	
	/**
	 * Implementation of selection sort algorithm.
	 * 
	 * @param x The integer array to sort in place
	 */
	public static void sort(int[] x) {		
		for (int i = 0; i < x.length - 1; i++) {
			
			// Assume i is index of smallest value in array
			int min = i;
			
			// Check all other values in array to find smallest value
			//length was wrong, not going to end of array
			for (int j = i + 1; j < x.length; j++) {
				if (x[j] < x[min]) {
					min = j;
				}
			}
            swap(x, i, min);
		}
	}
	
	/**
	 * Swap two values in place using XOR algorithm.
	 * 
	 * @param x The array of values
	 * @param a Index of first value in x
	 * @param b Index of second value in x
	 */
	public static void swap(int[] x, int a, int b) {
		// 1 xor 1 = 0, make sure not same
		if(a!=b) {
		x[a] ^= x[b];
		x[b] ^= x[a];
		// a^a = 0
		x[a] ^= x[b];
		}
	}
}