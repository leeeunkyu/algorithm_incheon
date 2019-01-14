package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_1260 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]); //���� ��
		int m = Integer.parseInt(str[1]); //���� ��
		int v = Integer.parseInt(str[2]);//Ž������ ������ȣ

		// ����� �׷���, ����ġ x

		int[][] a = new int[n + 1][n + 1];

		boolean[] c = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			String str2[] = br.readLine().split(" ");
			int v1 = Integer.parseInt(str2[0]);
			int v2 = Integer.parseInt(str2[1]);

			a[v1][v2] = 1;
			a[v2][v1] = 1;
		}

		// dfs(a,c,v);
		dfs(a, c, v, true);
	
	}
	
	public static void dfs(int[][] a, boolean[] c, int v, boolean flag) {
		//������ filo
		Stack<Integer> stack = new Stack<Integer>();
		int n = a.length - 1;
		
		stack.push(v);
		c[v] = true;
		System.out.println(v+" ");
		
		while(!stack.isEmpty()) {
			int vv = stack.peek(); //�� �б�
			
			flag = false;
			
			//���� ���� ������ �ֵ� �� push
			for (int i = 1; i <= n; i++) {
				if(a[vv][i] == 1 && !c[i]) {
					stack.push(i);
					System.out.println(i+" ");
				
					c[i] = true;
					flag = true;
					break;
				}
			}
			//�̿� �� ���� ���̻� ���� �ְ� ������ pop
			if(!flag) {
				stack.pop();
			}
			
		}
	}
	
	public static void bfs(int[][] a, boolean[] c, int v, boolean flag) {
		Queue<Integer> queue = new LinkedList<>();
	}
}