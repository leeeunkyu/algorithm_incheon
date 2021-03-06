package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution_5 {
	static int k;
	static Deque[] dqs;
	static boolean[] visited;
	//12:50
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			k = Integer.parseInt(br.readLine());
			dqs = new Deque[4];
			String[] str;
			Deque<Integer> dq;
			for (int i = 0; i < 4; i++) {
				dq = new LinkedList<Integer>();
				str = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					dq.addLast(Integer.parseInt(str[j]));
				}	
				dqs[i] = dq;
			}
			int[][] order = new int[2][k];
			for (int i = 0; i < k; i++) {
				String[] info = br.readLine().split(" ");
				order[0][i] = Integer.parseInt(info[0]) - 1;
				order[1][i] = Integer.parseInt(info[1]);
			}
			print();
			goGame(order);
			int res = getScore();
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
		
	}
	
	static void print() {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			System.out.println(dqs[i]);
		}
	}
	private static int getScore() {
		int sum = 0;
		int[] scoreMap = {1, 2, 4, 8};
		for (int i = 0; i < 4; i++) {
			Deque<Integer> dq = dqs[i];
			int val = dq.pollFirst();
			if(val == 1)
				sum += scoreMap[i];
		}
		return sum;
	}
	private static void goGame(int[][] order) {
		System.out.println(k);
		for (int i = 0; i < k; i++) {
			int num = order[0][i];
			int dir = order[1][i];
			visited = new boolean[4];
			Deque<Integer> dq = dqs[num];
			visited[num] = true;
			System.out.println("dir: "+dir);
			if(dir == 1) {
				cycle(num, dir, dq);
				dq.addFirst(dq.pollLast());
			} else{
				cycle(num, dir, dq);
				dq.addLast(dq.pollFirst());
			}
			print();
		}
	}
	private static void cycle(int num, int dir, Deque<Integer> dq) {
		if(num < 0) {
			return;
		}
		if(num > 3){
			return;
		}
		Val val = getVal(dq);
		System.out.println(val.toString());
		if(num + 1< 3) {
			Val rNumVal = getVal(dqs[num + 1]);
			if(!visited[num + 1] && rNumVal.getlVal() != val.getrVal()) {
				visited[num + 1] = true;
				cycle(num + 1, dir * -1, dqs[num + 1]);
				dqs[num + 1].addLast(dqs[num + 1].pollFirst());
			}			
		}
		if(num - 1 >= 0) {
			Val lNumVal = getVal(dqs[num - 1]);
			if(!visited[num - 1] && lNumVal.getrVal() != val.getlVal()) {
				visited[num - 1] = true;
				cycle(num - 1, dir * -1, dqs[num - 1]);
				dqs[num - 1].addLast(dqs[num - 1].pollFirst());

			}	
		}
		
	}
	private static Val getVal(Deque<Integer> dq) {
		int rVal = 0;
		int lVal = 0;
		Stack<Integer> stack = new Stack<>();
		for (int j = 0; j < 3; j++) {
			rVal = dq.pollFirst();
			stack.add(rVal);
		}
		while(!stack.isEmpty()) {
			dq.addFirst(stack.pop());
		}
		for (int j = 0; j < 2; j++) {
			lVal = dq.pollLast();
			stack.add(lVal);
		}
		while(!stack.isEmpty()) {
			dq.addLast(stack.pop());
		}
		return new Val(rVal, lVal);
	}

}

class Val {
	int rVal;
	int lVal;
	
	public Val(int rVal, int lVal) {
		super();
		this.rVal = rVal;
		this.lVal = lVal;
	}
	
	public int getrVal() {
		return rVal;
	}
	public void setrVal(int rVal) {
		this.rVal = rVal;
	}
	public int getlVal() {
		return lVal;
	}
	public void setlVal(int lVal) {
		this.lVal = lVal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Val [rVal=");
		builder.append(rVal);
		builder.append(", lVal=");
		builder.append(lVal);
		builder.append("]");
		return builder.toString();
	}
	
}
