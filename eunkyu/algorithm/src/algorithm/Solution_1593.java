package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//22:00
public class Solution_1593 {
	
	static int n; //���� ����
	static int m; //���� ����
	static int r; //��Ȧ ����
	static int c; //��Ȧ ����
	static int l; //�ҿ�ð�
	static int[][] arr;
	static int[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			
			init(info);
			
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < m; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}
			visited = new int[n][m];
			int y = r;
			int x = c;
			goGame(y, x, arr[y][x], 1);
			int res = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(visited[i][j] != 0)
						++res;
				}
			}
			//print(0);
			sb.append("#"+tc+" "+res+"\n");
			//System.out.println(res);
		}
		System.out.println(sb);
		
	}

	private static void goGame(int y, int x, int val, int cnt) {
		boolean[] res = mapping(val);
		if(cnt > l) {
			//print(cnt);
			return;
		}
		visited[y][x] = cnt;
		for (int i = 0; i < 4; i++) {
			if(res[i]) {
				if(i == 0 && y - 1 >= 0 && mapping(arr[y - 1][x])[1] && (visited[y - 1][x] == 0 || visited[y - 1][x] > cnt + 1)) {
					//���� ���� ���� �־�� �ϴ� �ֵ��� 1, 2, 5, 6
					goGame(y - 1, x, arr[y - 1][x], cnt + 1);
				}
				if(i == 1 && y + 1 < n && mapping(arr[y + 1][x])[0] && (visited[y + 1][x] == 0 || visited[y + 1][x] > cnt + 1)) {
					//�Ʒ��� ���� �Ʒ��� �־�� �ϴ� �ֵ� 1, 2, 4, 7
					goGame(y + 1, x, arr[y + 1][x], cnt + 1);
				}
				if(i == 2 && x + 1 < m && mapping(arr[y][x + 1])[3] && (visited[y][x + 1] == 0 || visited[y][x + 1] > cnt + 1)) {
					//������ ���� �����ʿ� �־�� �ϴ� �ֵ� 1, 3, 6, 7
					goGame(y, x + 1, arr[y][x + 1], cnt + 1);
				}
				if(i == 3 && x - 1 >= 0 && mapping(arr[y][x - 1])[2] && (visited[y][x - 1] == 0 || visited[y][x - 1] > cnt + 1)) {
					//���� ���� ���ʿ� �־�� �ϴ� ��
					goGame(y, x - 1, arr[y][x - 1], cnt + 1);
				}
			}
		}
	}

	private static void init(String[] info) {
		n = Integer.parseInt(info[0]);
		m = Integer.parseInt(info[1]);
		r = Integer.parseInt(info[2]);
		c = Integer.parseInt(info[3]);
		l = Integer.parseInt(info[4]);
		arr = new int[n][m];
	}
	
	public static boolean[] mapping(int val) {
		//�� 0, �Ʒ� 1, ������ 2 ���� 3
		boolean[] res = new boolean[4];
		if(val == 0)
			return res;
		for (int i = 0; i < 4; i++) {
			if(val == 1)
				res[i] = true;
			if(val == 2 && (i == 0 || i == 1)) {
				res[i] = true;
			}
			if(val == 3 && (i == 2 || i == 3)) {
				res[i] = true;
			}
			if(val == 4 && (i == 0 || i == 2)) {
				res[i] = true;
			}
			if(val == 5 && (i == 1 || i == 2)) {
				res[i] = true;
			}
			if(val == 6 && (i == 1 || i == 3)) {
				res[i] = true;
			}
			if(val == 7 && (i == 0 || i == 3)) {
				res[i] = true;
			}
				
				
		}
		return res;
	}
	
	private static void print(int cnt ) {
		System.out.println(cnt);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visited[i][j] != 0)
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.println();
		}
		System.out.println();
	}
}