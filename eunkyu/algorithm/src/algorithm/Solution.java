package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int answer = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		
		int numbers[] = new int[5];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		int target = Integer.parseInt(br.readLine());
		System.out.println(solution(numbers, target));
	}
	
	public static int solution(int[] numbers, int target) {
		dfs(numbers, target, 0, 0);
		return answer;
	}
	
	public static void dfs(int[] numbers, int target, int idx, int res) {
		if(idx == numbers.length) {
			if(target == res)
				answer++;
			return;
		}
		
		int type[] = {1 ,-1};
		//���ϱ� ���⸸ ����
		for(int i = 0; i < 2; i++) {
			if(type[i] == 1)
				dfs(numbers, target, idx + 1, res + (numbers[i]));
			else
				dfs(numbers, target, idx + 1, res - (numbers[i]));
		}
	}
	
	
}