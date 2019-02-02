package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_1966 {
	
	static int res = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		int n; // 문서의수
		int m; // 출력할 문서 위치
		int p = 0; // 출력할 문서 중요도
		int val = 0;
		for (int i = 0; i < testCase; i++) {
			String str2[] = br.readLine().split(" ");
			n = Integer.parseInt(str2[0]);
			m = Integer.parseInt(str2[1]);
			String str[] = br.readLine().split(" ");
			Queue<Integer> q = new LinkedList<Integer>();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			for (int j = 0; j < n; j++) {				
				val = Integer.parseInt(str[j]);
				if(j == m) {
					res = val;
				}
				q.add(val);
				pq.add(val);
			}
			if(q.size() != 1)
				print(q, pq, m, sb);
			else
				sb.append(1+"\n");
		}
		System.out.println(sb);
	}
	//1 2 3 4 5
	//5 4
	private static void print(Queue<Integer> q, PriorityQueue<Integer> pq, int p, StringBuilder sb) {
		int n = q.size();
		while(!q.isEmpty()) {
			int pivot = q.poll();
			if(pq.peek() == pivot) {
				q.add(pivot);
				if(pq.peek() == res) {
					sb.append((p + 1) +"\n");
					break;
				}
				pq.poll();
			} else {
				if(p != 0) {
					--p;
				}else {
					p = n - 1;
				}
				q.add(pivot);
			}
		}
		
		
	}
}
