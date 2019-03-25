package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//3:50
public class Main_2629 {
	
	static int n;
	static int m;
	static int[] stone;
	static int[] ball;
	static int a;
	static int b;
	static boolean isOk;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		stone = new int[n];
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			int val = Integer.parseInt(str[i]);
			stone[i] = val;
			sum += val;
		}
		
		m = Integer.parseInt(br.readLine());
		ball = new int[m];
		String[] info = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			ball[i] = Integer.parseInt(info[i]);
		}
		
		for (int i = 0; i < m; i++) {
			if(ball[i] == sum) {
				sb.append("Y"+" ");
				continue;
			}
			if(ball[i] > sum) {
				sb.append("N"+" ");
				continue;
			}
			a = 0;
			b = ball[i];
			isOk = false;
			dfs(0);
			if(isOk) {
				sb.append("Y"+" ");
			}else {
				sb.append("N"+" ");
			}
		}
		System.out.println(sb);
	
	}

	private static boolean dfs(int idx) {
		if(idx == n) {
			if(a == b) {
				isOk = true;
				return true;
			}
		}
		boolean res;
		for (int i = idx; i < n; i++) {
			//ball + ��... == ��...
			a += stone[idx];
			res = dfs(idx + 1);
			if(res)
				return true;
			a -= stone[idx];
			b += stone[idx];
			res = dfs(idx + 1);
			if(res)
				return true;
			b -= stone[idx];
		}
		return false;
	}
}