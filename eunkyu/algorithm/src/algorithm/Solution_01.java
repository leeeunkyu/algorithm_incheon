package algorithm;

import java.util.Arrays;

public class Solution_01 {
	public static void main(String[] args) {
		int [] ranks = {3, 4, 3, 0, 2, 2, 3, 0, 0};
		System.out.println(solution(ranks));
	}
	public static int solution(int[] ranks) {
		Arrays.sort(ranks);
		
		int cnt = 0;
		int duplication = 1;
		int temp = ranks[0];
		
		for (int i = 1; i < ranks.length; i++) {
			if (temp == ranks[i]) {
				duplication++;
			} else if (temp + 1 == ranks[i]) {
				cnt += duplication;
				temp = ranks[i];
				duplication = 1;
			}
			else if (temp + 1 < ranks[i]) {
	             temp = ranks[i];
	             duplication = 1;
	        }
		}
		
		return cnt;
    }
	
	
}
