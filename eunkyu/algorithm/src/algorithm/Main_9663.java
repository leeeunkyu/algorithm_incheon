package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663 {
	
	static int ans, N;
	static int[] col;
	//n이 주어지면 nxn 크기의 채스판에 n개의 퀸이 놓여진다.
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int N = Integer.parseInt(br.readLine());
		col = new int[15];
	    
	    for (int i = 1; i <= N; i++) {
	        col[1] = i;
	        search(1);
	    }
	    System.out.println(ans);
	}
	public static void search(int x) {
	    if (x == N) { //x 가 N과 같으면 끝까지 Queen을 배치한것
	        ans++;
	    } else {
	        for (int i = 1; i <= N; i++) {
	            col[x + 1] = i;
	            if (check(x + 1)) {
	                search(x + 1);
	            } else {
	                col[x + 1] = 0;    
	            }
	        }
	    }
	    col[x] = 0;
	    
	}
	public static boolean check(int x) {
	    for (int i = 1; i < x; i++) {
	        if (col[i] == col[x])  //같은 세로 줄인지 확인
	            return false;
	        
	        if (Math.abs(i - x) == Math.abs(col[i] - col[x])) //대각선 확인. 값 음수 방지하기위해 절대값으로
	            return false;
	    }
	    return true;
	}
}
