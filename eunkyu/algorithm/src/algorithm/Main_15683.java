package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_15683 {
	// 1�� �ѹ���
	// 2�� �����
	// 3�� 90��
	// 4�� ������
	// 5�� �׹���
	static int N; 	//����ũ��  N < 8
	static int M;	//����ũ�� M < 8
	static int arr[][];
	static ArrayList<Cctv> cctyList = new ArrayList<>(); //size < 8
	static int rotSize[] = {4, 2, 4, 4, 1}; //cctv type���� ȸ���ؾ� �ϴ� ��
	static int temp = 1000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tempStr[] = br.readLine().split(" ");
			for (int j = 0; j < tempStr.length; j++) {
				arr[i][j] = Integer.parseInt(tempStr[j]);
				if(arr[i][j] != 0 && arr[i][j] != 6) {
					//cctv y, x ��ǥ type (type �� 1�� ���� �־�� rotsize�� ��Ī�� �ȴ�
					cctyList.add(new Cctv(j, i, (arr[i][j] - 1)));
				}
					
			}
		}
		
		dfs(0);		
		System.out.println(temp);
	}
	private static void dfs(int i) {
		int visited[][] = new int [8][8];
		if(i >= cctyList.size()) {
			int min = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(arr[j][j2] == 0) {
						++min;
					}
				}
			}
			
			if(temp > min) {
				temp = min;
			}
			return ;
		}
		Cctv cctv = cctyList.get(i);
		int type = cctv.getType();
		backUp1(visited);
		for (int j = 0; j < rotSize[type]; j++) {
			if(type == 0) {
				update(j, cctv);
			}else if(type == 1) {
				update(j, cctv);
				update(j + 2, cctv);

			}else if(type == 2) {
				update(j, cctv);
				update(j + 1, cctv);

			}else if(type == 3) {
				update(j, cctv);
				update(j + 1, cctv);
				update(j + 2, cctv);

			}else if(type == 4) {
				update(j, cctv);
				update(j + 1, cctv);
				update(j + 2, cctv);
				update(j + 3, cctv);

			}
			dfs(++i);
			backUp2(visited);

		}
		
		for (int j = 0; j < arr.length; j++) {
			for (int j2 = 0; j2 < arr[j].length; j2++) {
				System.out.print(arr[j][j2]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("=======================");
		System.out.println();
	}
	
	private static void backUp1(int[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = arr[i][j];
			}
		}
	}
	
	private static void backUp2(int[][] visited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = visited[i][j];
			}
		}
	}
	private static void update(int dir, Cctv cctv) {
		dir = (dir % 4); //������ ��, ��, ��, ��  4�����θ� ��Ÿ���� ����
		int x = cctv.getX();
		int y = cctv.getY();
		if(dir == 0) {
		// ����������
			for (int i = x + 1; i < M; i++) {
				if(arr[y][i] == 6) break;
				arr[y][i] = 9;
			}
		}else if(dir == 1) {
			// ��������
			for (int i = y - 1; i >= 0; i--) {
				if(arr[i][x] == 6) break;
				arr[i][x] = 9;
			}
		}else if(dir == 2) {
			// ��������
			for (int i = x - 1; i >= 0; i--) {
				if(arr[y][i] == 6) break;
				arr[y][i] = 9;
			}
		}else if(dir == 3) {
			// �Ʒ���
			for (int i = y + 1; i < N; i++) {
				if(arr[i][x] == 6) break;
				arr[i][x] = 9;
			}
		}
	}
}

class Cctv {
	
	private int x;
	private int y;
	private int type;
	
	public Cctv() {
		super();
	}
	
	public Cctv(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}