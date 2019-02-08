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
			int n = Integer.parseInt(str[0]); //num����
			int k = Integer.parseInt(str[1]); //k��°�� ū ����
			String num = br.readLine(); //4�� ��� 8~28 ���� ���� 0�̻� f����
			
			int sideNum = num.length() / 4;//�Ѻ��� �� �� �ִ� ���ڱ���
			
			int sideNumList[] = new int[n]; //sideNum�� ���ü� �ִ� �ִ� ����� ���� �� ���� ������ �ִ� ����Ǽ� 
			
			for (int j = 0; j < sideList.length; j++) {
				Queue<String> side = new LinkedList<String>();
				for (int l = 0; l < sideNum; l++) { //�� ������ ���� ����
					side.add(""+num.charAt(l + (j*sideNum)));
				}
				sideList[j] = side;
			}

			selectSumVal(sideList, sideNumList, sideNum);
			
			Arrays.sort(sideNumList);
			
			for (int j = 0; j < sideNumList.length; j++) {
				if(j + 1 < sideNumList.length && sideNumList[j] == sideNumList[j + 1]) {
					sideNumList[j] = -1; //�ߺ��� �� ó��
				}
			}
			Arrays.sort(sideNumList);

			sb.append("#"+i+" "+sideNumList[sideNumList.length - k]+"\n");
		}
		System.out.println(sb);
	}

	private static void selectSumVal(Queue<String>[] sideList, int[] sideNumList, int sideNum) {
		
		for (int i = 0; i < sideNumList.length; i++) { // 4���� * �� ���� ������ num�� �� = ���ü� �ִ� ũ���� �� ��ŭ ȸ��
			int sideVal = selectSideVal(sideList[i % 4]);  //�� ������ ���� �հ踦 ���� sideList�� 4���� ť(��)�� ����
			sideNumList[i] = sideVal; //1 2 / 3 4 / 5 6 / 7 8
			if((i+1) % (4) == 0 && i != sideNumList.length - 1) { //�ѹ��� Ž���� �� �ϸ� ȸ�� rotate�� ��� ��Ű�� ����!
				//�ð�������� �̵�
				rotate(sideList);
			}
		}
	}

	private static void rotate(Queue<String>[] sideList) {
		String rear[] = new String[sideList.length];
		Queue<String> temp[] = new Queue[sideList.length];
		
		//sideList ���縦 ���� temp queue ����
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new LinkedList<String>();
		}
		
		
		for (int i = 0; i < sideList.length; i++) {
			Queue<String> q = sideList[i];
			while (!q.isEmpty()) {
				if(q.size() == 1) {
					rear[i] =  q.poll();		//���� �� �κ��� ����
				}else {
					temp[i].add(q.poll());	//���� �� �κ��� ������ ���� ����
				}
			}
		}
		
		int length = rear.length - 1;
		for (int i = 0; i < sideList.length; i++) {
			Queue<String> q = sideList[i];
			if(i != 0) {
				q.add(""+rear[i - 1]);
				while(!temp[i].isEmpty()) {
					q.add(""+temp[i].poll()); //������ ���� �ٽ� �ִ´�.
				}
			} else {
				q.add(""+rear[length]); //�� �κ��� �� ó������ �ִ´�.
				while(!temp[i].isEmpty()) {
					q.add(""+temp[i].poll());
				}
			}
		}
		
	}
	
	//�� ���� ��(���ҵ��� ��)�� ���ϴ� �Լ�
	private static int selectSideVal(Queue<String> queue) {
		int sum = 0;
		int size = queue.size() - 1;
		int val = 0;
		for (int i = size; i >= 0; i-- ) {
			char temp = queue.peek().charAt(0);
			if(temp >= 65 && temp <= 90)
				val = queue.poll().charAt(0) - 'A' + 10; //�����ϰ��
			else
				val = queue.poll().charAt(0) - '0'; //�����ϰ��
			if(i == 0)
				sum += val;
			else {
				for (int j = i; j > 0; j--) {
					val *= 16; //16���� ���� ���ϱ� ���� 16�� ��� ���س�����.
				}
				sum += val;
			}
			queue.add(""+temp);
		}
		return sum;
	}
}