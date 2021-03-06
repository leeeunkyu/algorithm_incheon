package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//17:50
public class Main_1182 {
	
	static int n;
	static int s;
	static int[] arr;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(Integer.MAX_VALUE);
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		s = Integer.parseInt(str[1]);
		arr = new int[n];
		String[] info = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(info[i]);
		}
		res = 0;
		dfs(0, 0, 0, "");
		System.out.println(res);
	}

	private static void dfs(int val, int cnt, int a, String str) {
		if(cnt == n) {
			if(val == s && a > 0) {
/*				System.out.println(val+" "+s+"  "+str);
*/				++res;
			}
			return;
		}
		dfs(val + arr[cnt], cnt + 1, a + 1," "+str+" "+(+arr[cnt]));
/*		dfs(val - arr[cnt], cnt + 1, a + 1," "+str+" "+(-arr[cnt]));
*/		dfs(val, cnt + 1, a," "+str);
	}
}
