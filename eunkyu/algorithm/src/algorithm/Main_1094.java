package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1094 {
	
	static int x;
	static int sum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		sum = 64;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(64);
		while(sum != x) {
			int val = pq.poll();
			int a = val / 2;
			if(sum - a  >= x) {
				sum -= a;
				pq.add(a);
			} else {
				pq.add(a);
				pq.add(a);
			}
		}
		System.out.println(pq.size());
		
	}
}
