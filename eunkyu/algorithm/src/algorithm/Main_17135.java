package algorithm;

import java.awt.color.CMMException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//08:20 ~ 8:59
public class Main_17135 {
	
	static int n;	//��
	static int m;	//��
	static int d;
	static int[][] arr;
	static Queue<int[]> arrows;
	static Enemy_17135[] enemys;
	static int size;
	static int score;
	static int enemyNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		Queue<Enemy_17135> tempq = new LinkedList<>();
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		d = Integer.parseInt(str[2]);
		arrows = new LinkedList<int[]>();
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				int val = Integer.parseInt(info[j]);
				arr[i][j] = val;
				if(val == 1) {
					tempq.add(new Enemy_17135(i, j, 1));
					++enemyNum;
				}
			}
		}
		size = tempq.size();
		Enemy_17135[] copyEnemy = new Enemy_17135[size];

		init(tempq, copyEnemy);
		
		
		int res = 0;
		while(!arrows.isEmpty()) {
			score = 0;
			int cpNum = enemyNum;
			goGame(arrows.poll());
			if(res < score)
				res = score;
			copy(copyEnemy, false);
			enemyNum = cpNum;
		}
		System.out.println(res);
	}

	private static void init(Queue<Enemy_17135> tempq, Enemy_17135[] copyEnemy) {
		int r = 3;
		int[] combArr = new int[r];
		doCombination(combArr, r, 0, 0);
		
		enemys = new Enemy_17135[size];
		int temp = 0;
		while(!tempq.isEmpty()) {
			enemys[temp] = tempq.poll();
			copyEnemy[temp] = new Enemy_17135(enemys[temp].getY(), enemys[temp].getX(), 1);
			temp += 1;
		}
	}

	private static void copy(Enemy_17135[] copyEnemy, boolean type) {
		for (int i = 0; i < size; i++) {
			enemys[i].setX(copyEnemy[i].getX());
			enemys[i].setY(copyEnemy[i].getY());
			enemys[i].setState(1);
		}
		
	}

	private static void goGame(int[] arrow) {
		HashSet<Integer> hs = new HashSet<>();
		while(enemyNum != 0) {
			for (int i = 0; i < 3; i++) {
				int val = arrow[i];
				int minIndex = -1;
				int minDist = Integer.MAX_VALUE;
				for (int j = 0; j < size; j++) {
					if(enemys[j].getState() == -1)
						continue;
					int dist = Math.abs(val - enemys[j].getX()) + Math.abs(n - enemys[j].getY());
								
					if(dist <= d) {
						if(dist < minDist) {
							minDist = dist;
							minIndex = j;
						} else if(dist == minDist) {
							if(enemys[j].getX() < enemys[minIndex].getX()) {
								minIndex = j;
							}
						}
					}
				}
				if(minIndex != -1)
					hs.add(minIndex);
			}
			
			for(int idx: hs) {
				enemys[idx].setState(-1);
				++score;
				--enemyNum;
			}
			hs.clear();
			
			move();	
		}
	}

	private static void move() {
		for (int i = 0; i < size; i++) {
			if(enemys[i].getState() == -1)
				continue;
			enemys[i].setY(enemys[i].getY() + 1);
			if(enemys[i].getY() >= n) {
				enemys[i].setState(-1);
				--enemyNum;
			}
		}
	}

	private static void doCombination(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			int[] idxs = new int[3];
			for (int i = 0; i < 3; i++) {
				idxs[i] = combArr[i];
			}
			arrows.add(idxs);
		}else if(m == target) {
			return;
		}else {
			combArr[index] = target;
			doCombination(combArr, r - 1, index + 1, target +1);
			doCombination(combArr, r, index, target + 1);
		}
	}
}

class Enemy_17135 {
	int y;
	int x;
	int state;
	
	public Enemy_17135(int y, int x, int state) {
		super();
		this.y = y;
		this.x = x;
		this.state = state;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
