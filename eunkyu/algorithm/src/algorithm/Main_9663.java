package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663 {
	
	static int ans, n;
	static int[] col;
	//n이 주어지면 nxn 크기의 채스판에 n개의 퀸이 놓여진다.
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
		//채스판의 최대 크기는 15
	    for (int i = 1; i <= n; i++) {
			col = new int[n + 1];
			col[1] = i;
			dfs(1);
	    }
	    System.out.println(ans);

	}
	public static void dfs(int row) {
		if(row == n) {
			++ans;
		}else {
			for (int i = 1; i <= n; i++) {
				col[row + 1] = i;
				if(isPossible(row + 1)) {
					dfs(row + 1);
				}
			}
		}
	}
	 
	public static boolean isPossible(int c) {
	    // col[1]의 의미는 1행 *열이다.
	    // col[1] = 1 => 1행 1열, col[2] = 3 => 2행 3열
	 
	    // 이전 열들을 탐색하면서 배치 가능 여부 확인
	    for (int i = 1; i < c; i++) {
	        // 같은 행, 열
	        if (col[i] == col[c]) {
	            return false;
	        }
	        // 대각선
	        if (Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
	            return false;
	        }
	    }
	    return true;
	}

}
