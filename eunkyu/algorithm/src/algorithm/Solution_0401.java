package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_0401 {
	
	static int n;
	static int x;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			x = Integer.parseInt(str[1]);
			arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(info[j]);
				}
			}
			int b = 0;
			int a = 0;
			a = go();
			//b = go2();
			System.out.println("가로"+a);
			System.out.println("세로"+b);
			sb.append("#"+tc+" "+(a + b)+"\n");
		}
		System.out.println(sb);
	}

	private static int go2() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			Deque<Integer> q = new LinkedList<>();
			q.add(arr[0][i]);
			boolean c = true;
			loop:
			for (int j = 1; j < n; j++) {
				int peek = q.peekLast();
/*				System.out.println(q);
*/				int val = arr[j][i];
				if(Math.abs(peek - val) > 1) {
					c = false;
					continue;
				}
				if(peek < 0) {
					c = false;
					continue;
				}
				if(peek == val)
					q.add(arr[j][i]);
				else if(peek < val){
					if(q.size() < x) {
						c = false;
						continue;
					}
					Queue<Integer> temp = new LinkedList<>();
					for (int k = 0; k <= x; k++) {
						if(!q.isEmpty()) {
							int val2 = q.poll();
							if(val2 < 0) {
								c = false;
								continue loop;
							}
							temp.add(val2);
						}						
					}
					
					while(!temp.isEmpty()) {
						q.add(temp.poll());
					}
					q.add(-x);
					q.add(val);
					j += 1;
				} else if(peek > val) {
					int val2 = arr[j][i];
					q.add(-x);
					for (int k = j; k < j + x; k++) {
						if(k >= n || arr[k][i] != val2) {
							c = false;
							continue loop;	
						}
						q.add(arr[k][i]);
					}
					j += x - 1;
				}
 			}
			if(c) {
				System.out.println(i);
				++cnt;
			}
		}
		System.out.println("세로"+cnt);
		return cnt;
	}

	private static int go() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			Deque<Integer> q = new LinkedList<>();
			q.add(arr[i][0]);
			boolean c = true;
			loop:
			for (int j = 1; j < n; j++) {
				int peek = q.peekLast();
/*				System.out.println(q);
*/				int val = arr[i][j];
				if(Math.abs(peek - val) > 1) {
					c = false;
					continue;
				}
				if(peek < 0) {
					c = false;
					continue;
				}
				if(peek == val)
					q.add(arr[i][j]);
				else if(peek < val){
					if(q.size() < x) {
						c = false;
						continue;
					}
					Queue<Integer> temp = new LinkedList<>();
					for (int k = 0; k <= x; k++) {
						if(!q.isEmpty()) {
							int val2 = q.poll();
							if(val2 < 0) {
								c = false;
								continue loop;
							}
							temp.add(val2);
						}						
					}
					
					while(!temp.isEmpty()) {
						q.add(temp.poll());
					}
					q.add(-x);
					q.add(val);
					j += 1;
				} else if(peek > val) {
					int val2 = arr[i][j];
					q.add(-x);
					for (int k = j; k < j + x; k++) {
						if(k >= n || arr[i][k] != val2) {
							if(j + x > n) {
								c = false;
								continue loop;	
							}else {
								continue;
							}
								
						}
						q.add(arr[i][k]);
					}
					j += x - 1;
				}
 			}
			if(c) {
				System.out.println(i);
				++cnt;
			}
		}
		System.out.println("가로"+cnt);
		return cnt;
	}
}
