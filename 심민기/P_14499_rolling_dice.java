package baekjoon;


import java.util.Scanner;

public class P_14499_rolling_dice {
		static int[][] dice_planar_figure = new int[4][3];
		static int[][] map;
		static int[][] dxdy = {{0,0},{0,1},{0,-1},{-1,0},{1,0}}; // �ǹ�x, ��, �� , ��, ��
		static int n,m,x,y;
		/*
		 *  1. ���� ó���� �ֻ������� ��� �鿡 0�� ������ �ִ�.
	        2. �ֻ����� ������ ��, �̵��� ĭ�� �� �ִ� ���� 0�̸�, �ֻ����� �ٴڸ鿡 �� �ִ� ���� ĭ�� ����ȴ�.
			3. 0�� �ƴ� ��쿡�� ĭ�� �� �ִ� ���� �ֻ����� �ٴڸ����� ����Ǹ�, ĭ�� �� �ִ� ���� 0�� �ȴ�.
			4. �ֻ����� �̵����� �� ���� ��ܿ� �� �ִ� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
	        5. �ֻ����� ������ �ٱ����� �̵���ų �� ����. ���� �ٱ����� �̵���Ű���� �ϴ� ��쿡�� �ش� ����� �����ؾ� �ϸ�, ��µ� �ϸ� �ȵȴ�.
		 */
		/*
		 *  �Է°�
		 *  ù° �ٿ� ������ ���� ũ�� N, ���� ũ�� M (1 �� N, M �� 20), �ֻ����� ���� ���� ��ǥ x y(0 �� x �� N-1, 0 �� y �� M-1), 
		 *  �׸��� ����� ���� K (1 �� K �� 1,000)�� �־�����.
	     *  ��° �ٺ��� N���� �ٿ� ������ �� �ִ� ���� ���ʺ��� ��������, �� ���� ���ʺ��� ���� ������� �־�����.
	     *  �ֻ����� ���� ĭ�� �� �ִ� ���� �׻� 0�̴�. ������ �� ĭ�� �� �ִ� ���� 10�� ���� �ʴ� �ڿ����̴�.
	     *  ������ �ٿ��� �̵��ϴ� ����� ������� �־�����. ������ 1, ������ 2, ������ 3, ������ 4�� �־�����.
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
			//�� 1, ��2, ��3, ��4
			if((direction==1&&y+1==m)||(direction==2&&y==0)||(direction==3&&x==0)||(direction==4&&x+1==n)) return;
			//�ֻ��� ��ǥ �ٲٱ�
			x+=dxdy[direction][0];
			y+=dxdy[direction][1];
			//������ ������
			rotate_palnar_figure(direction);
		}
		static void rotate_palnar_figure(int direction){
			//[1,1]�� ���ʸ��̴�.
			int savenum = dice_planar_figure[1][1];
			if(direction==1) {        // ����
				dice_planar_figure[1][1] = dice_planar_figure[1][0];
				dice_planar_figure[1][0] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[1][2];
				dice_planar_figure[1][2] = savenum;
			}else if(direction==2) {  //����
				dice_planar_figure[1][1] = dice_planar_figure[1][2];
				dice_planar_figure[1][2] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[1][0];
				dice_planar_figure[1][0] = savenum;
			}else if(direction==3) {  //����
				dice_planar_figure[1][1] = dice_planar_figure[2][1];
				dice_planar_figure[2][1] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[0][1];
				dice_planar_figure[0][1] = savenum;
				}else if(direction==4) {  //����
				dice_planar_figure[1][1] = dice_planar_figure[0][1];
				dice_planar_figure[0][1] = dice_planar_figure[3][1];
				dice_planar_figure[3][1] = dice_planar_figure[2][1];
				dice_planar_figure[2][1] = savenum;
			}
			System.out.println(dice_planar_figure[1][1]);
			//�̵��� ĭ�� �� �ִ� ���� 0�̸�, �ֻ����� �ٴڸ鿡 �� �ִ� ���� ĭ�� ����ȴ�
			if(map[x][y]==0) {
				map[x][y] = dice_planar_figure[3][1];
			}else {  // 0�� �ƴ� ��쿡�� ĭ�� �� �ִ� ���� �ֻ����� �ٴڸ����� ����Ǹ�, ĭ�� �� �ִ� ���� 0�� �ȴ�.
				dice_planar_figure[3][1] = map[x][y];
				map[x][y] = 0;
			}
		}
	}
