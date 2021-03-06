package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class BitTest {
	public static void main(String[] args) {
		String[] arr = {"a", "b", "c", "d"};
		int n = arr.length;
		Queue<String> q = new LinkedList<String>();
/*		for (int i = 0; i < (1 << n); i++) {
			if(Integer.bitCount(i) == 2) {
				String str = Integer.toBinaryString(i);
				System.out.println(str);
				q.add(str);
			}
		}
		System.out.println();
		while (!q.isEmpty()) {
			String str = q.poll();
			System.out.println(str);
			char[] indexs = str.toCharArray();
			for (int i = 0; i < indexs.length; i++) {
				int index = indexs[i] - '0';
				if(index != 0)
					System.out.print(arr[i]+" ");
			}
			System.out.println();
		}*/
		
		System.out.println();
		
		for(int i=0; i < (1<<n); i++) {
		    if(Integer.bitCount(i) == 3) {
		        for(int j=0; j < n; j++) {
		            if( ( (1<<j) & i) > 0) {
		                System.out.print(arr[j]+"  ");
		            }
		        }
		        System.out.println();
		    }
		}
		
		
		int m = 3;
		for (int i = 0; i < (1 << n); i++) {
			if(Integer.bitCount(i) == 3) {
				for (int j = 0; j < n; j++) {
					if( ((1 << j) & i) > 0) {
						System.out.print(arr[j]+ " ");
					}
				}
				System.out.println();
			}
		}
		
		
	}
}
