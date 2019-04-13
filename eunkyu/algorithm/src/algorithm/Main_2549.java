package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_2549 {
	
	static int n = 4;
	static int[][] arr;
	static int res;
	static Info_2549[] infodq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		Deque<Info_2549> dq = new LinkedList<Info_2549>();
		infodq = new Info_2549[7];
		res = Integer.MAX_VALUE;
		go(0, dq);
		System.out.println(res);
		for (int j = res - 1; j >= 0; j--) {
			Info_2549 i = infodq[j];
			System.out.println(i.getType()+" "+i.getPivot()+" "+i.getIdx());			
		}

		
		
	}

	private static void go(int cnt, Deque<Info_2549> dq) {
		if(res > cnt && check()){
			res = cnt;
			for (int i = 0; i < cnt; i++) {
				Info_2549 info = dq.pollFirst();
				dq.addLast(info);
				infodq[i] = info;
			}
			return;
		}
		if(res -1 == cnt)
			return;
		if(cnt == 7)
			return;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				for (int j2 = 1; j2 <= 3; j2++) {
					if(res <= cnt)
						return;
					int[][] cpArr = new int[n][n];
					copy(cpArr, false);
					
					if(i == 0) {
						//��
						dq.add(new Info_2549(1, j, j2));
						moveOne(j, j2);
					} else {
						//��
						dq.add(new Info_2549(2, j, j2));
						moveTwo(j, j2);
					}
					//print(dq);		
					go(cnt + 1, dq);
					dq.pollLast();
					copy(cpArr, true);
					cpArr = null;
				}
			}
		}
	}

	private static void copy(int[][] cpArr, boolean type) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!type)
					cpArr[i][j] = arr[i][j];
				else
					arr[i][j] = cpArr[i][j];
			}
		}
	}

	private static void moveTwo(int pivot, int idx) {
		//pivot ���� ���η� idx��ŭ �̵�
		Deque<Integer> dq = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			int val = arr[pivot][i];
			dq.addLast(val);
		}
		for (int i = 0; i < idx; i++) {
			int val = dq.pollLast();
			dq.addFirst(val);
		}
		
		for (int i = 0; i < n; i++) {
			arr[pivot][i] = dq.pollFirst();
		}
		dq = null;
	}

	private static void moveOne(int pivot, int idx) {
		//pivot ����  �Ʒ��� idx��ŭ �̵�
		Deque<Integer> dq = new LinkedList<>();
		for (int i = n - 1; i >= 0; i--) {
			int val = arr[i][pivot];
			dq.addLast(val);
		}
		for (int i = 0; i < idx; i++) {
			int val = dq.pollFirst();
			dq.addLast(val);
		}
		
		for (int i = n - 1; i >= 0; i--) {
			arr[i][pivot] = dq.pollFirst();
		}
		dq = null;

	}

	private static boolean check() {
		
		int temp = 0;
		int[] temps = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] != temps[temp]) {
					return false;
				}
				++temp;
			}
		}
		return true;
	}

	private static void print(Deque<Info_2549> dq) {
		System.out.println();
		System.out.println(dq);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}

class Info_2549 {
	int type;
	int pivot;
	int idx;
	
	public Info_2549(int type, int pivot, int idx) {
		super();
		this.type = type;
		this.pivot = pivot;
		this.idx = idx;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPivot() {
		return pivot;
	}
	public void setPivot(int pivot) {
		this.pivot = pivot;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Info_2549 [type=");
		builder.append(type);
		builder.append(", pivot=");
		builder.append(pivot);
		builder.append(", idx=");
		builder.append(idx);
		builder.append("]");
		return builder.toString();
	}	
	
}