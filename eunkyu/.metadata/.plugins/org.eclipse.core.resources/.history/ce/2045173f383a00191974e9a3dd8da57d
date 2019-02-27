package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_15661 {
	
	static int n;	//사람 수
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

	private static int selectMember() {
		int min = Integer.MAX_VALUE;
		for (int i = 2; i <= n-i; i++) {
			boolean[] isMember = new boolean[n];
	        int[] combArr = new int[n];		
/*	        System.out.println("선발 뽑는 수:"+i);
*/	        doCombination(combArr, n, i, 0, 0, isMember);
			
		}
		return min;
	}
	
	
	private static void subScore(boolean[] isMember) {		
        int teamA = 0;
		int teamB = 0;
		int res = 0;
		int min = Integer.MAX_VALUE;
/*		System.out.println();
*/		for (int j = 0; j < n; j++) {
			for (int j2 = 0; j2 < n; j2++) {
				if(isMember[j] && isMember[j2]) {
					teamA += (arr[j][j2]);
				}
			}
		}
/*		System.out.println("선발 합:"+teamA);
*/		
		for (int j = 0; j < n; j++) {
			for (int j2 = 0; j2 < n; j2++) {
				if(!isMember[j] && !isMember[j2]) {
/*					System.out.print("후발: "+j+" "+"("+j+" "+j2+")");
*/					teamB += (arr[j][j2]);
				}
			}
		}
	/*	System.out.println();
		System.out.println("후발 합:"+teamB);
		System.out.println();*/
		res = teamA - teamB;
		if(res < 0) {
			res = res * -1;
		}
		if(min >= res)
			min = res;
/*		System.out.println("min: "+min);
*/		pq.add(min);
	}

	private static void doCombination(int[] combArr, int n, int r, int index, int target, boolean[] isMember) {
		int a;
		int b;
		if(r == 0){
			isMember = new boolean[n];
			for(int i=0; i<index; i++) {
            	isMember[combArr[i]] = true;//01 02 03 12 13 23..
/*            	System.out.print(combArr[i]+" ");	
*/			}
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



