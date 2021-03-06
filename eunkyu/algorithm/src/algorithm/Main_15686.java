package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.TabableView;

public class Main_15686 {
	
	static int n;
	static int m;
	static int[][] arr;
	static int size;
	static Chicken_14501[] chickens;
	static Queue<Chicken_14501[]> chickesq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		n = Integer.parseInt(info[0]);
		m = Integer.parseInt(info[1]);
		
		arr = new int[n][n];
		Queue<Chicken_14501> chickenq =  new LinkedList<Chicken_14501>();
		
		for (int i = 0; i < n; i++) {
			String[] arrInfo = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(arrInfo[j]);
				arr[i][j] = val;
				if(val == 2) {
					chickenq.add(new Chicken_14501(i, j, 0));
				}
			}
		}
		size = chickenq.size();
		chickens = new Chicken_14501[size];
		
		for (int i = 0; i < chickens.length; i++) {
			chickens[i] = chickenq.poll();
		}
		
		chickesq = new LinkedList<>();
		
		//n���� m���� ������
		
/*		for (int i = 0; i < (1 << size); i++) {
			System.out.println(Integer.bitCount(i)+"  i: "+i);
			if(Integer.bitCount(i) == m) {
				Chicken_14501[] tempChickens = new Chicken_14501[m];
				int temp = 0;
				for (int j = 0; j < size; j++) {
					if(((1 << j) & i) > 0) {
						tempChickens[temp] = chickens[j];
						temp++;
					}
				}
				chickesq.add(tempChickens);
			}
		}*/
		
		
		int r = m;
		int[] combArr = new int[m];
		doCombination(combArr, r, 0, 0);
		
		int rMin = Integer.MAX_VALUE;
		while(!chickesq.isEmpty()) {
			Chicken_14501[] c = chickesq.poll();
			int min = dfs(c);
			if(rMin > min)
				rMin = min;
		}
		System.out.println(rMin);	
	}

	private static void doCombination(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			Chicken_14501[] tempChickens = new Chicken_14501[m];

			for (int i = 0; i < m; i++) {
				tempChickens[i] = chickens[combArr[i]];
			}
			chickesq.add(tempChickens);
		} else if(target == size){
			return;
		} else {
			combArr[index] = target;
			doCombination(combArr, r - 1, index + 1, target + 1);
			doCombination(combArr, r, index, target + 1);
		}
	}

	private static int dfs(Chicken_14501[] c) {
		int dir = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 1) {
					int min = Integer.MAX_VALUE;
					for (int j2 = 0; j2 < c.length; j2++) {
						int y = c[j2].getY();
						int x = c[j2].getX();
						
						int sub = Math.abs(x - j) + Math.abs(y - i);
						if(sub < min)
							min =  sub;
					}
					dir += min;
				}
			}
		}
		return dir;
	}
}

class Chicken_14501 implements Comparable<Chicken_14501>{
	int y;
	int x;
	int cnt;
	
	public Chicken_14501(int y, int x, int cnt) {
		super();
		this.y = y;
		this.x = x;
		this.cnt = cnt;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chicken_14501 [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", cnt=");
		builder.append(cnt);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Chicken_14501 chicken_14501) {
		return this.getCnt() - chicken_14501.getCnt();
	}
}
