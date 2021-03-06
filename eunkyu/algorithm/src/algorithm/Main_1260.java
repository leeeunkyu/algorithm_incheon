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
		int n = Integer.parseInt(str[0]); //점점 수
		int m = Integer.parseInt(str[1]); //간선 수
		int v = Integer.parseInt(str[2]);//탐색시작 정점번호

		// 양방향 그래프, 가중치 x

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
		System.out.println();
		c = new boolean[n + 1];
		bfs(a, c, v);
	}
	
	public static void dfs(int[][] a, boolean[] c, int v, boolean flag) {
		//스택은 filo
		Stack<Integer> stack = new Stack<Integer>();
		int n = a.length - 1;
		
		stack.push(v);
		c[v] = true;
		System.out.print(v+" ");
		while(!stack.isEmpty()) {
			int vv = stack.peek(); //값 읽기
			
			flag = false;
			
			//읽은 노드와 근접한 애들 다 push
			for (int i = 1; i <= n; i++) {
				if(a[vv][i] == 1 && !c[i]) {
					stack.push(i);
					System.out.print(i+" ");
				
					c[i] = true;
					flag = true;
					break;
				}
			}
			//이웃 한 애중 더이상 읽을 애가 없으면 pop
			if(!flag) {
				stack.pop();
			}
			
		}
	}
	
	public static void bfs(int[][] a, boolean[] c, int v) {
		Queue<Integer> queue = new LinkedList<>();
		int n = a.length - 1;
		
		queue.add(v);
		c[v] = true;
		while (!queue.isEmpty()) {
			v = queue.poll(); //앞에꺼 뽑고 제거
			System.out.print(v+ " ");
			for (int i = 0; i <= n; i++) {
				if(a[v][i] == 1 && !c[i]) {
					queue.add(i);
					c[i] = true;
				}
			}
		}
		
	}
}
