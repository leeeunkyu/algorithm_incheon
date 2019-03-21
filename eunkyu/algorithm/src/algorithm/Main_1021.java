package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//17:22~ 18:00
public class Main_1021 {
	
	static int n;
	static int m;
	static int[] idxs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		idxs = new int[m];
		
		String[] info = br.readLine().split(" ");
		
		for (int i = 0; i < m; i++) {
			idxs[i] = Integer.parseInt(info[i]);
		}
		
		Deque<Integer> dq = new LinkedList<Integer>();
		
		for (int i = 1; i <= n; i++) {
			dq.add(i);
		}
		
		goGame(dq);
	}

	private static void goGame(Deque<Integer> dq) {
		int a = 0; //맨 앞을 맨 뒤로
		int b = 0; //맨 뒤를 맨 앞으로
		Stack<Integer> tempStack = new Stack<>();
		
		for (int i = 0; i < m; i++) {
			int pivot = 0;
			int val = idxs[i];
			if(val == dq.peekFirst()) {
				dq.poll();
				continue;
			}
			if(val == dq.peekLast()) {
				dq.pollLast();
				b++;
				continue;
			}
			
			while(!dq.isEmpty()) {
				if(val == dq.peekFirst())
					break;
				++pivot;
				tempStack.add(dq.pollFirst());
			}
			
			while(!tempStack.isEmpty()) {
				dq.addFirst(tempStack.pop());
			}
			
			int dist1 = pivot;
			int dist2 = dq.size() - (pivot + 1);

			while(true) {
				if(dist1 <= dist2) {
				int temp = dq.pollFirst();
					if(temp == val) {
						break;
					} 
					dq.addLast(temp);
					a++;
				} else {
					int temp = dq.pollLast();
					b++;
					if(temp == val) {
						break;
					}
					dq.addFirst(temp);
				}	
			}			
		}
		System.out.println(a + b);
	}
}
