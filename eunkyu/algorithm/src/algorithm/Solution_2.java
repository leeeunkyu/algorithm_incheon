package algorithm;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_2 {
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	static boolean visited [];
	public static void main(String[] args) {
		int A [] = {5, 4, 0, 3, 1, 6, 2};
		System.out.println(new Solution_2().solution(A));
	}
	public int solution(int[] A) {
		int size = A.length;
		
		 visited = new boolean [size];
		
		for (int i = 0; i < size; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, i, 0, A);
			}
		}
		return pq.poll();
	}
	private void dfs(int start, int now, int length, int[] A) {
		int next = A[now];
		length += 1;
		
		if (start == next) {
			pq.add(length);
			return;
		}
		if(!visited[next]) {
			visited[next] = true;
			dfs(start, next, length, A);
		}
		
	}
}
