package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663 {
	
	static int ans, N;
	static int[] col;
	//n�� �־����� nxn ũ���� ä���ǿ� n���� ���� ��������.
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
	    if (x == N) { //x �� N�� ������ ������ Queen�� ��ġ�Ѱ�
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
	        if (col[i] == col[x])  //���� ���� ������ Ȯ��
	            return false;
	        
	        if (Math.abs(i - x) == Math.abs(col[i] - col[x])) //�밢�� Ȯ��. �� ���� �����ϱ����� ���밪����
	            return false;
	    }
	    return true;
	}
}
