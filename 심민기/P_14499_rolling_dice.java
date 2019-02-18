package baekjoon;


import java.util.Scanner;

public class P_14499_rolling_dice {
		static int[][] dice_planar_figure = new int[4][3];
		static int[][] map;
		static int[][] dxdy = {{0,0},{0,1},{0,-1},{-1,0},{1,0}}; // 의미x, 동, 서 , 북, 남
		static int n,m,x,y;
		/*
		 *  1. 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.
	        2. 주사위를 굴렸을 때, 이동한 칸에 써 있는 수가 0이면, 주사위의 바닥면에 써 있는 수가 칸에 복사된다.
			3. 0이 아닌 경우에는 칸에 써 있는 수가 주사위의 바닥면으로 복사되며, 칸에 써 있는 수는 0이 된다.
			4. 주사위가 이동했을 때 마다 상단에 써 있는 값을 구하는 프로그램을 작성하시오.
	        5. 주사위는 지도의 바깥으로 이동시킬 수 없다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안된다.
		 */
		/*
		 *  입력값
		 *  첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 
		 *  그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.
	     *  둘째 줄부터 N개의 줄에 지도에 써 있는 수가 북쪽부터 남쪽으로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다.
	     *  주사위를 놓은 칸에 써 있는 수는 항상 0이다. 지도의 각 칸에 써 있는 수는 10을 넘지 않는 자연수이다.
	     *  마지막 줄에는 이동하는 명령이 순서대로 주어진다. 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
		 */
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			n = scan.nextInt();
			m = scan.nextInt();
			x = scan.nextInt();
			y = scan.nextInt();
			int k = scan.nextInt();
			map = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					map[i][j] = scan.nextInt();
				}
				
			}
			for (int i = 0; i < k; i++) {
				int direction = scan.nextInt();
				rollingdice(direction);
			}
		}
		
		static void rollingdice(int direction) {
			//동 1, 서2, 북3, 남4
			if((direction==1&&y+1==m)||(direction==2&&y==0)||(direction==3&&x==0)||(direction==4&&x+1==n)) return;
			//주사위 좌표 바꾸기
			x+=dxdy[direction][0];
			y+=dxdy[direction][1];
			//전개도 돌리기
			rotate_palnar_figure(direction);
		}
		static void rotate_palnar_figure(int direction){
			//[1,1]은 위쪽면이다.
			int savenum = dice_planar_figure[1][1];
			if(direction==1) {        // 동쪽
				dice_planar_figure[1][1] = dice_planar_figure[1][0];
				dice_planar_figure[1][0] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[1][2];
				dice_planar_figure[1][2] = savenum;
			}else if(direction==2) {  //서쪽
				dice_planar_figure[1][1] = dice_planar_figure[1][2];
				dice_planar_figure[1][2] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[1][0];
				dice_planar_figure[1][0] = savenum;
			}else if(direction==3) {  //북쪽
				dice_planar_figure[1][1] = dice_planar_figure[2][1];
				dice_planar_figure[2][1] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[0][1];
				dice_planar_figure[0][1] = savenum;
				}else if(direction==4) {  //남쪽
				dice_planar_figure[1][1] = dice_planar_figure[0][1];
				dice_planar_figure[0][1] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[2][1];
				dice_planar_figure[2][1] = savenum;
			}
			System.out.println(dice_planar_figure[1][1]);
			//이동한 칸에 써 있는 수가 0이면, 주사위의 바닥면에 써 있는 수가 칸에 복사된다
			if(map[x][y]==0) {
				map[x][y] = dice_planar_figure[3][1];
			}else {  // 0이 아닌 경우에는 칸에 써 있는 수가 주사위의 바닥면으로 복사되며, 칸에 써 있는 수는 0이 된다.
				dice_planar_figure[3][1] = map[x][y];
				map[x][y] = 0;
			}
		}
	}
