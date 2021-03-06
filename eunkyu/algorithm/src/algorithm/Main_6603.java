package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6603 {
	
	static int k;
	static int cnt;
	static int arr[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str[] = br.readLine().split(" ");
			if("0".equals(str[0])) {
				break;
			}
			k = Integer.parseInt(str[0]);
			arr = new int[13];
			for (int i = 1; i < str.length; i++) {
				arr[i-1] = Integer.parseInt(str[i]);
			}
			for (int i = 0; i < (k - 5); i++) {
				cnt = 1;
				dfs(i, arr[i] + " ");			
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int idx, String str) {
		if(cnt == 6) {
			sb.append(str+"\n");
		} else {
			for (int i = (idx + 1); i < k; i++) {
				++cnt;
				dfs(i, str + arr[i] + " ");
			}
		}
		--cnt;
	}	
}


