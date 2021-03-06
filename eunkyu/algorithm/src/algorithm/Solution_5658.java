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
		Queue<String> sideList[] = new Queue[4];
		for (int i = 1; i <= testCase; i++) {
			String str[] = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]); //num길이
			int k = Integer.parseInt(str[1]); //k번째로 큰 숫자
			String num = br.readLine(); //4의 배수 8~28 사이 각각 0이상 f이하
			
			int sideNum = num.length() / 4;//한변에 들어갈 수 있는 숫자길이
			
			int sideNumList[] = new int[n]; //sideNum이 나올수 있는 최대 경우의 수는 한 변이 가질수 있는 경우의수 
			
			for (int j = 0; j < sideList.length; j++) {
				Queue<String> side = new LinkedList<String>();
				for (int l = 0; l < sideNum; l++) { //한 변마다 값을 넣음
					side.add(""+num.charAt(l + (j*sideNum)));
				}
				sideList[j] = side;
			}

			selectSumVal(sideList, sideNumList, sideNum);
			
			Arrays.sort(sideNumList);
			
			for (int j = 0; j < sideNumList.length; j++) {
				if(j + 1 < sideNumList.length && sideNumList[j] == sideNumList[j + 1]) {
					sideNumList[j] = -1; //중복된 값 처리
				}
			}
			Arrays.sort(sideNumList);

			sb.append("#"+i+" "+sideNumList[sideNumList.length - k]+"\n");
		}
		System.out.println(sb);
	}

	private static void selectSumVal(Queue<String>[] sideList, int[] sideNumList, int sideNum) {
		
		for (int i = 0; i < sideNumList.length; i++) { // 4바퀴 * 각 변이 가지는 num의 수 = 나올수 있는 크기의 수 만큼 회전
			int sideVal = selectSideVal(sideList[i % 4]);  //각 변마다 값의 합계를 저장 sideList는 4개의 큐(변)를 담음
			sideNumList[i] = sideVal; //1 2 / 3 4 / 5 6 / 7 8
			if((i+1) % (4) == 0 && i != sideNumList.length - 1) { //한바퀴 탐색을 다 하면 회전 rotate를 몇번 시키냐 결정!
				//시계방향으로 이동
				rotate(sideList);
			}
		}
	}

	private static void rotate(Queue<String>[] sideList) {
		String rear[] = new String[sideList.length];
		Queue<String> temp[] = new Queue[sideList.length];
		
		//sideList 복사를 위한 temp queue 생성
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new LinkedList<String>();
		}
		
		
		for (int i = 0; i < sideList.length; i++) {
			Queue<String> q = sideList[i];
			while (!q.isEmpty()) {
				if(q.size() == 1) {
					rear[i] =  q.poll();		//가장 끝 부분을 추출
				}else {
					temp[i].add(q.poll());	//가장 끝 부분을 제외한 값을 복사
				}
			}
		}
		
		int length = rear.length - 1;
		for (int i = 0; i < sideList.length; i++) {
			Queue<String> q = sideList[i];
			if(i != 0) {
				q.add(""+rear[i - 1]);
				while(!temp[i].isEmpty()) {
					q.add(""+temp[i].poll()); //복사한 값을 다시 넣는다.
				}
			} else {
				q.add(""+rear[length]); //끝 부분을 맨 처음으로 넣는다.
				while(!temp[i].isEmpty()) {
					q.add(""+temp[i].poll());
				}
			}
		}
		
	}
	
	//각 변의 값(원소들의 합)을 구하는 함수
	private static int selectSideVal(Queue<String> queue) {
		int sum = 0;
		int size = queue.size() - 1;
		int val = 0;
		for (int i = size; i >= 0; i-- ) {
			char temp = queue.peek().charAt(0);
			if(temp >= 65 && temp <= 90)
				val = queue.poll().charAt(0) - 'A' + 10; //문자일경우
			else
				val = queue.poll().charAt(0) - '0'; //숫자일경우
			if(i == 0)
				sum += val;
			else {
				for (int j = i; j > 0; j--) {
					val *= 16; //16진수 값을 구하기 위해 16씩 계속 곱해나간다.
				}
				sum += val;
			}
			queue.add(""+temp);
		}
		return sum;
	}
}
