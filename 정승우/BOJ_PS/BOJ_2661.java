import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2661 {
	private static int n;
	private static boolean stopFlag = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dfs(1, "1");
		br.close();
	}
	
	private static boolean isSafe(String str) {
		int len = str.length();
		int repeatCnt = len / 2;
		int start = len - 1;
		int end = len;
		for (int i = 1; i <= repeatCnt; i++) {
			String first = str.substring(start - i, end - i);
			String second = str.substring(start, end);
			if (first.equals(second)) {
				return false;
			}
			start--;
		}
		return true;
	}

	private static void dfs(int len, String str) {
		if (stopFlag || !isSafe(str)) {
			return;
		}
		if (len == n) {
			stopFlag = true;
			System.out.println(str);
			return;
		}
		for (int i = 1; i <= 3; i++) {
			dfs(len + 1, str + i);
		}
	}
}

