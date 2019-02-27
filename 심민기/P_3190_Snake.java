package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_3190_Snake {
	static Queue<Point_3190> snake = new LinkedList<>(); //뱀 몸뚱아리 정보 ㅋㅋ
	static int n,k,l,dict,result; // n:맵 크기, k:사과갯수, l:회전 횟수, dict : 머리방향(0 위, 1 오, 2 아, 3 왼)
	//dict 변환방법 dict = (dict+4+변환방향)%4
	static int[][] map; //-1은 뱀이존재, 1은 사과, 0은 빈공간
	static int[][] rotate_info; //두번째 인덱스의 크기는 2이며, 두번째 인덱스의 0은 시간, 1은 뱡향을 의미(방향 -1 :왼쪽, 1:오른쪽) 
	static int[] snake_head;
	static int[][] dxdy = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		dict = 1;
		n = Integer.parseInt(scan.nextLine());
		k = Integer.parseInt(scan.nextLine());
		map = new int[n][n];
		for (int i = 0; i < k; i++) {
			String[] d = scan.nextLine().split(" ");
			map[Integer.parseInt(d[0])-1][Integer.parseInt(d[1])-1] = 1;
		}
		l=Integer.parseInt(scan.nextLine());
		rotate_info = new int[l][2];
		for (int i = 0; i < l; i++) {
			String[] d = scan.nextLine().split(" ");
			rotate_info[i][0] = Integer.parseInt(d[0]);
			rotate_info[i][1] = d[1].equals("L")?-1:1;
		}
		snake.add(new Point_3190(0,0));
		
		snake_head = new int[2];
		snake_head[0] = 0;
		snake_head[1] = 0;
		
		map[0][0] = -1;
		result = 0;
		int turn_idx = 0;
		int turn_time = rotate_info[turn_idx][0];
		while(true) {
			//draw_map();
			result++;
			snake_head[0]+=dxdy[dict][0];
			snake_head[1]+=dxdy[dict][1];
			//벽에 부딪치는지 확인
			if(snake_head[0]<0||snake_head[1]<0||snake_head[0]==n||snake_head[1]==n)break;
			//사과가 있는지 확인
			if(map[snake_head[0]][snake_head[1]]==1) {
				snake.add(new Point_3190(snake_head[0], snake_head[1]));
				map[snake_head[0]][snake_head[1]]=-1;
			}else {
				//머리 넣을 곳이 꼬리인지 0인지 확인
				if(map[snake_head[0]][snake_head[1]]==-1) {
					break;
				}else {
					snake.add(new Point_3190(snake_head[0], snake_head[1]));
					map[snake_head[0]][snake_head[1]]=-1;
				}
				Point_3190 tail = snake.poll();
				map[tail.pi][tail.pj]=0;
			}
			if(result==turn_time) {
				dict = (dict+4+rotate_info[turn_idx++][1])%4;
				if(turn_idx==l) {
					turn_time = -1;
				}else {
					turn_time = rotate_info[turn_idx][0];
				}
			}
		}
		System.out.println(result);
	}
	
	static void draw_map() {
		System.out.println("--------"+result+"-----------");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
class Point_3190{
	int pi;
	int pj;
	public Point_3190(int pi, int pj) {
		this.pi = pi;
		this.pj = pj;
	}
	
}