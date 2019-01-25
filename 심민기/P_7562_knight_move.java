package baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class P_7562_knight_move {
	static int[][] ground;
	static boolean[][] checked;
	static int l, desX, desY, howlong;
	static int[] dx = {-2,-1,1,2,2,1,-1,-2};
	static int[] dy = {1,2,2,1,-1,-2,-2,-1};
	static Queue<Point_7562> que = new LinkedList<>();
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testcnt = scan.nextInt();
		for(int t=0; t<testcnt; t++){
			l = scan.nextInt();
			howlong = 0; 
			ground = new int[l][l];
			checked = new boolean[l][l];
			int malX = scan.nextInt();
			int malY = scan.nextInt();
			desX = scan.nextInt();
			desY = scan.nextInt();
			que.clear();
			que.offer(new Point_7562(malX,malY));
			checked[malX][malY] = true;
			movemal();
			System.out.println(howlong);
		}
		scan.close();
	}
	static void movemal(){
		while(true){
			int quesize = que.size();
			for(int i=0; i< quesize; i++){
				Point_7562 p = que.poll();
				if(p.x==desX&&p.y==desY)return;
				for(int j=0; j<8; j++){
					int px = p.x+dx[j];
					int py = p.y+dy[j];
					if(px<0||py<0||px>l-1||py>l-1||checked[px][py]==true)continue;
					checked[px][py]=true;
					que.offer(new Point_7562(px,py));
				}
			}
			howlong++;
		}
	}

}



class Point_7562{
    int x;
	int y;
	Point_7562(int x, int y){
		this.x = x;
		this.y = y;
	}
}