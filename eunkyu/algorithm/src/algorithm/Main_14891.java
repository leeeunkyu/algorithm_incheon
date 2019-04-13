package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

//22:20
public class Main_14891 {
	
	static Deque[] deque;
	static int[][] order;
	static int k;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		deque = new Deque[4];
		for (int i = 0; i < 4; i++) {
			Deque<Integer> dq = new LinkedList<Integer>(); 
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				dq.addLast(str.charAt(j) - '0');
			}
			deque[i] = dq;
		}
		
		k = Integer.parseInt(br.readLine());
		order = new int[2][k];
		for (int i = 0; i < k; i++) {
			String[] info = br.readLine().split(" ");
			order[0][i] = Integer.parseInt(info[0]);
			order[1][i] = Integer.parseInt(info[1]);
		}
		int sum = 0;
		int[] cost = {1, 2, 4, 8};
		goGame();
		for (int i = 0; i < 4; i++) {
			Deque<Integer> dq = deque[i];
			int val = dq.pollFirst();
			if(val == 1)
				sum += cost[i];
		}
		System.out.println(sum);
	}
	
	private static void goGame() {
		//print();
		for (int i = 0; i < k; i++) {
			visited = new boolean[4];
			int num = order[0][i];
			int dir = order[1][i];
			visited[num - 1] = true;
			if(dir == 1)
				rotateR(deque[num - 1], num - 1, dir);
			else
				rotateR(deque[num - 1], num - 1, dir);
			//print();
		}
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < deque.length; i++) {
			System.out.println(deque[i]);
		}
	}

	public static void rotateR(Deque<Integer> dq, int curNum, int dir) {
		//�ð� ����
		if(curNum < 0 || curNum > 3)
			return;
		int r = 0;
		int l = 0;
		
		for (int i = 0; i < 8; i++) {
			int val = dq.pollFirst();
			if(i == 2)
				r = val;
			if(i == 6)
				l = val;
			dq.addLast(val);
		}
		if(curNum + 1 < 4 && !visited[curNum + 1]) {
			Deque<Integer> dq2 = deque[curNum + 1];
			int l2 = 0;
			for (int i = 0; i < 8; i++) {
				int val = dq2.pollFirst();
				if(i == 6)
					l2 = val;
				dq2.addLast(val);
			}
			if(l2 != r) {
				visited[curNum + 1] = true;
				rotateR(dq2, curNum + 1, dir * -1);				
			}
		}
		if(curNum - 1 >= 0 && !visited[curNum - 1]) {
			Deque<Integer> dq2 = deque[curNum - 1];
			int r2 = 0;
			for (int i = 0; i < 8; i++) {
				int val = dq2.pollFirst();
				if(i == 2)
					r2 = val;
				dq2.addLast(val);
			}
			if(l != r2) {
				visited[curNum - 1] = true;
				rotateR(dq2, curNum - 1, dir * -1);				
			}
		}
		
		if(dir == 1) {
			int val = dq.pollLast();
			dq.addFirst(val);	
		} else {
			int val = dq.pollFirst();
			dq.addLast(val);
		}
		
	}

}