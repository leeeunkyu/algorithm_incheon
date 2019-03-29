package algorithm;

public class TestClass {
	public static void main(String[] args) {
		char[] A = {'a', 'b', 'c', 'd', 'e'};
		int size = A.length;
		for (int i = 0; i < (1 << size); i++) {
			if(Integer.bitCount(i) == 3) {
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						System.out.print(A[j]+" ");
					}
				}
				System.out.println();
			}
		}
	}
}