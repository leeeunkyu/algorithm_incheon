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
	/*
	 * 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
	나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 
	이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 
	그렇지 않다면 바로 인쇄를 한다.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			String[] str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]);
			int m = Integer.parseInt(str[1]);
			
			Queue<Integer> q = new LinkedList<Integer>();
 			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
			String info[] = br.readLine().split(" ");
 			for (int i = 0; i < n; i++) {
				int val = Integer.parseInt(info[i]);
				q.add(val);
				pq.add(val);
 			}
			
			int cnt = 1;
			while(!q.isEmpty()) {
				int pqVal = pq.poll();
				int qVal = q.poll();
				if(pqVal == qVal) {
					if(m == 0) {
						sb.append(cnt+"\n");
					}
					m--;
					cnt++;
				} else {
					q.add(qVal);
					pq.add(pqVal);
					if(m == 0) {
						m = q.size() - 1;
						continue;
					}
					m--;

				}
			}
		}
		
		System.out.println(sb+"\n");
	}
}