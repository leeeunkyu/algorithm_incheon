package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_15661 {
	
	static int n;	//��� ��
	static int[][] arr;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] arrInfo = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(arrInfo[j]);
			}
		}
		
		selectMember();
		System.out.println(pq.poll());
	}

	private static void selectMember() {
		for (int i = 2; i <= n-i; i++) {
			boolean[] isMember = new boolean[n];
	        int[] combArr = new int[n];		
	        doCombination(combArr, n, i, 0, 0, isMember);
		}
	}
	
	
	private static void subScore(boolean[] isMember) {		
        int teamA = 0;
		int teamB = 0;
		int res = 0;
		for (int j = 0; j < n; j++) {
			for (int j2 = 0; j2 < n; j2++) {
				if(isMember[j] && isMember[j2]) {
					teamA += (arr[j][j2]);
				}
			}
		}
	
		for (int j = 0; j < n; j++) {
			for (int j2 = 0; j2 < n; j2++) {
				if(!isMember[j] && !isMember[j2]) {
				teamB += (arr[j][j2]);
				}
			}
		}
		res = teamA - teamB;
		pq.add(res);
	}

	private static void doCombination(int[] combArr, int n, int r, int index, int target, boolean[] isMember) {
		if(r == 0){
			isMember = new boolean[n];
			for(int i=0; i<index; i++) {
            	isMember[combArr[i]] = true;//01 02 03 12 13 23..
			}
        	subScore(isMember);
        }else if(target == n){ 
            return ;
        }else{
            combArr[index] = target;
            doCombination(combArr, n, r-1, index+1, target+1, isMember);
            doCombination(combArr, n, r, index, target+1, isMember); 
        }
	}
}
