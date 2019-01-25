package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_1697_hide_and_seek {

	public static void main(String[] args) {
		boolean[] visited = new boolean[100001];
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		Queue<Integer> que = new LinkedList<>();
		visited[n] = true;
		if(n!=k) que.add(n);
		int quesize;
		int result=0;
		loop:while((quesize = que.size())!=0) {
			result++;
			for (int i = 0; i < quesize; i++) {
				int a = que.poll();
				int a1 = a-1;
				int a2 = a+1;
				int a3 = a*2;
				if(a1==k||a2==k||a3==k)break loop;
				if(a1>=0&&!visited[a1]) {
					que.add(a1);
					visited[a1]=true;
				}
				if(a2<=100000&&!visited[a2]) {
					que.add(a2);
					visited[a2]=true;
				}
				if(a3<=100000&&!visited[a3]) {
					que.add(a3);
					visited[a3]=true;
				}
			}
		}
		System.out.println(result);
	}

}
