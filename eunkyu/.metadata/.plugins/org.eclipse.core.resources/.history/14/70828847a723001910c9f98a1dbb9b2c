package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2661 {
	//숫자는 1,2,3 세개로만 이루어져있음
	//나쁜 수열은 수열이 반복될경우 ex) 123123213 
	static int arr[];
	static int n;
	static ArrayList<Integer> numList = new ArrayList<>();
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dfs(0);
		System.out.println(res);
	}

	private static void dfs(int idx) {
		int sample[] = {1, 2, 3};
		int length = n - 1;
		if(idx >= n) {
			return;
		}
		for (int i = 0; i < sample.length; i++) {
			arr[idx] = sample[i];

			if(arr.length-1 == length) {
				res = check();
			}
			int temp = ++idx;
			if(temp >= n) {
				return ;
			}else {
				dfs(temp);						
			}
		}
	}
	//3 2 1 2 3
	//
	private static int check() {
		Queue<Integer> q1 = new LinkedList<>();
		int temp[] = new int[3];
		int cnt = 0;
		//3 2 1 3
		//3 2 1
		//3
		String str = "";
		for (int j = 0; j < arr.length; j++) {
			q1.add(arr[j]);
			str += arr[j];
		}
		
			
		for (int i = 0; i < temp.length && !q1.isEmpty(); i++) {
			temp[i] = q1.poll();
			if(temp[i] == q1.peek())
				return -1;
		}
		
		for (int i = 0; i < temp.length && !q1.isEmpty(); i++) {
			if(temp[i] == q1.peek()) {
				q1.poll();
				cnt++;
				if(cnt > 1) {
					return -1;
				}
			}
			else {
				cnt = 0;
				q1.poll();
			}
		}
		
		return Integer.parseInt(str);
	}
}
