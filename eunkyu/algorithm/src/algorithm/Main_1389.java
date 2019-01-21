package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1389 {
	static Node node[];
	static int visitedNode[];
	static Node edge[];
	static int n;
	static int c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		node = new Node[m];
		visitedNode = new int[n];
		edge = new Node[m];
		for (int i = 0; i < m; i++) {
			String src[] = br.readLine().split(" ");
			node[i] = new Node(Integer.parseInt(src[0]), Integer.parseInt(src[1]));
			edge[i] = node[i];
		}
		
		for (int i = 1; i <= n; i++) {
			System.out.println(i+" 노드로 시작");
			c = 0;
			bfs(i);
			System.out.println("탐색 횟수: "+c);
			for (int j = 0; j < node.length; j++) {
				node[j] = edge[j];
			}
			for (int j = 0; j < visitedNode.length; j++) {
				visitedNode[j] = 0;
			}
			System.out.println();
		}
		//6 8 5 5 
		
	}

	private static int bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		int cnt = 0;
		int stack = 0;
		visitedNode[cnt] = i;
		while(!q.isEmpty()) {
	//		Node nd = selectNode(i); //selectNode 는 노드 연결하는 엣지를 선택하는것
			int num = q.poll();
			while(cnt+1 < n && checkList(num)) {
				System.out.println("-------------------------------------------------");
				Node temp = selectNode(num);
				q.add(temp.getNextNum());
				visitedNode[cnt+1] = temp.getNextNum();
				for (int j = 0; j < visitedNode.length; j++) {
					System.out.print(visitedNode[j]+" ");
				}
				System.out.println();
				cnt++;
				c = c + 1 + stack;
				System.out.println("num: "+num+" c: "+c+" stack: "+stack);
				System.out.println("------------------------------------------------");
			}
			if(cnt+1 < n) {
				++stack;
			}
			/*visitedNode[cnt] = 0;
			cnt--;*/
		}
		
		return cnt;
	}


	private static Node selectNode(int num) {
		Node n = null;		
		boolean check = false;
		
		for (int j = 0; j < node.length; j++) {

			if(node[j].getNum() == num) {
				
				for (int i = 0; i < visitedNode.length; i++) {
					if(visitedNode[i] == node[j].getNextNum()) {
						check = true;
						break;
					}else {
						check = false;
					}
				}
				if(check)
					continue;
				
				n = node[j];
				node[j] = new Node(0, 0);
				break;
			
			}
			else if(node[j].getNextNum() == num) {				
				int temp = 0;
				temp = node[j].getNextNum();
				node[j].setNextNum(node[j].getNum());
				node[j].setNum(temp);
				
				for (int i = 0; i < visitedNode.length; i++) {
					if(visitedNode[i] == node[j].getNextNum()) {
						check = true;
						break;
					} else {
						check = false;
					}
				}
				if(check)
					continue;
				
				n = node[j];
				node[j] = new Node(0, 0);
				break;
			}
		
		}
		return n;
	}
	
	private static boolean checkList(int num) {
		loop:
		for (int i = 0; i < node.length; i++) {
			if(node[i].getNum() == num || node[i].getNextNum() == num) {
				int tempNum = node[i].getNum();
				int tempNextNum = node[i].getNextNum();
				boolean a = false;
				boolean b = false;
				for (int j = 0; j < visitedNode.length; j++) {
					if(visitedNode[j] == tempNum) {
						a = true;
					}
					if(visitedNode[j] == tempNextNum) {
						b = true;
					}
					if(a && b)
						continue loop;
				}
				return true;
			}
		}
		return false;
	}
}

class Node {
	int num;
	int nextNum;
	
	public Node(int num, int nextNum) {
		super();
		this.num = num;
		this.nextNum = nextNum;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNextNum() {
		return nextNum;
	}
	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [num=");
		builder.append(num);
		builder.append(", nextNum=");
		builder.append(nextNum);
		builder.append("]");
		return builder.toString();
	}
	
}
