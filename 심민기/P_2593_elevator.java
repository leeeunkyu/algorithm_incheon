package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_2593_elevator {
	static int n,m,st,goal,result;
	static int[][] info;
	static boolean[] visited;
	static Node_2593 last_node;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		info = new int[m][2];
		for (int i = 0; i < m; i++) {
			info[i][0] = scan.nextInt();
			info[i][1] = scan.nextInt();
		}
		goal = scan.nextInt();  // 시작 지점과 목표지점을 바꿈 (출력 할때 역순으로 출력하는것이 편하기 때문)
		st = scan.nextInt();
		if(goal==st) {
			System.out.println(0);
			return;
		}
		visited = new boolean[n+1];
		Queue<Node_2593> que = new LinkedList<>();
		que.add(new Node_2593(st,null,-1));
		visited[st] = true;
		int quesize = 1;
		loop:while(true) {
			for (int i = 0; i < quesize; i++) {
				Node_2593 node = que.poll();
				int val = node.val;
//				System.out.println("현재는 " + val+"층입니다");
				for (int j = 0; j < m; j++) {
					int sf = info[j][0];
					int add_f = info[j][1];
					if(sf>val)continue;
					if((val-sf)%add_f==0) {
						int add_cnt = 0;
						int n_v;
						while((n_v = sf+add_f*add_cnt++)<=n) {
							if(visited[n_v])continue;
							visited[n_v] = true;
							last_node = new Node_2593(n_v, node, j+1);
//							System.out.println("현재층 : "+ node.val + ", 넣은 층 : "+n_v);
							que.add(last_node);
							if(n_v==goal) break loop;
						}
					}
				}
			}
			if((quesize = que.size())==0) {
				result = -1;
				break;
			}else {
				result++;
			}
		}
		System.out.println(result==-1?-1:result+1);
		if(result!=-1) {
			while(true) {
				if(last_node.par==null)break;
				System.out.println(last_node.ele_num);
				last_node = last_node.par;
			}
		}
	}
}
class Node_2593{
	int val;
	int ele_num;
	Node_2593 par;
	public Node_2593(int val, Node_2593 par, int ele_num) {
		this.val = val;
		this.par = par;
		this.ele_num = ele_num;
	}
	
}