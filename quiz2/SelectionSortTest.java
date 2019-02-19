import static org.junit.Assert.*;

import org.junit.Test;

public class SelectionSortTest {

	@Test
	public void testSelectionSortPartial(){
		int[] x = new int[]{5, 4, 2 ,6 ,3, 8, 9};
		int[] expected = new int[]{2, 3, 4, 5, 6, 8, 9};
		SelectionSort.sort(x);
		assertArrayEquals(expected, x);
	}
	@Test
	public void testSwap() {
		int[] x = new int[]{5, 4, 2 ,6 ,3, 8, 9};
		int[] expected = new int[]{5, 4, 6 ,2 ,3, 8, 9};
		SelectionSort.swap(x, 2, 3);
		assertArrayEquals(expected, x);
	}
	@Test
	public void testSwapOne() {
		int[] x = new int[]{5, 4, 2 ,6 ,3, 8, 9};
		int[] expected = new int[]{2, 4, 5 ,6 ,3, 8, 9};
		SelectionSort.swap(x, 0, 2);
		assertArrayEquals(expected, x);
	}
	@Test
	public void testSelectionSortExtra(){
		int[] x = new int[]{7, 8, 4, 1,5, 2, 9, 3};
		int[] expected = new int[]{1, 2, 3, 4, 5, 7, 8, 9};
		SelectionSort.sort(x);
		assertArrayEquals(expected, x);
	}
	

}
