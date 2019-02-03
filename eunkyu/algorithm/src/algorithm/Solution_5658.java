package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_5658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		Queue<Character> sideList[] = new Queue[4];
		for (int i = 1; i <= testCase; i++) {
			String str[] = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]); //num길이
			int k = Integer.parseInt(str[1]); //k번째로 큰 숫자
			String num = br.readLine(); //4의 배수 8~28 사이 각각 0이상 f이하
			
			int sideNum = num.length() / 4;//한변에 들어갈 수 있는 숫자길이
			
			int sideNumList[] = new int[n]; //sideNum이 나올수 있는 최대 경우의 수는 한 변이 가질수 있는 경우의수 * 4
			
			for (int j = 0; j < sideList.length; j++) {
				Queue<Character> side = new LinkedList<Character>();
				for (int l = 0; l < sideNum; l++) {
					side.add(num.charAt(l + (j*sideNum)));
				}
				sideList[j] = side;
			}

			selectSumVal(sideList, sideNumList, sideNum);
			
			Arrays.sort(sideNumList);

			for (int j = 0; j < sideNumList.length; j++) {
				if(j + 1 < sideNumList.length && sideNumList[j] == sideNumList[j + 1]) {
					k++;
				}
			}
			sb.append("#"+i+" "+sideNumList[sideNumList.length - k]+"\n");
		}
		
		System.out.println(sb);

	}

	private static void selectSumVal(Queue<Character>[] sideList, int[] sideNumList, int sideNum) {
		for (int i = 0; i < sideNumList.length; i++) {
			int sideVal = selectSideVal(sideList[i % 4]);
			sideNumList[i] = sideVal;
			if((i+1) % (4) == 0 && i != sideNumList.length - 1) {
				//시계방향으로 이동
				rotate(sideList);
			}
		}
	}

	private static void rotate(Queue<Character>[] sideList) {
		char rear[] = new char[sideList.length];
		Queue<Character> temp[] = new Queue[sideList.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new LinkedList<Character>();
		}
		for (int i = 0; i < sideList.length; i++) {
			Queue<Character> q = sideList[i];
			while (!q.isEmpty()) {
				if(q.size() == 1) {
					rear[i] =  q.poll();		
				}else {
					temp[i].add(q.poll());	
				}
			}
		}
		
		int length = rear.length - 1;
		for (int i = 0; i < sideList.length; i++) {
			Queue<Character> q = sideList[i];
			if(i != 0) {
				q.add(rear[i - 1]);
				while(!temp[i].isEmpty()) {
					q.add(temp[i].poll());
				}
			} else {
				q.add(rear[length]);
				while(!temp[i].isEmpty()) {
					q.add(temp[i].poll());
				}
			}
		}
		
	}

	private static int selectSideVal(Queue<Character> queue) {
		int sum = 0;
		int size = queue.size() - 1;
		int val = 0;
		for (int i = size; i >= 0; i-- ) {
			char temp = queue.peek();
			if(queue.peek() >= 65 && queue.peek() <= 90)
				val = queue.poll() - 'A' + 10; //문자일경우
			else
				val = queue.poll() - '0'; //숫자일경우
			if(i == 0)
				sum += val;
			else {
				for (int j = i; j > 0; j--) {
					val *= 16;
				}
				sum += val;
			}
			queue.add(temp);
		}
		return sum;
	}
}
