package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2503 {
	
	static int n;
	static int a;
	static int b;
	static int c;
	static BallMap_2503[] ballmap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ballmap = new BallMap_2503[n];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			ballmap[i] = 
					new BallMap_2503(Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[0]));
		}
		
		goGame();
	
	}

	private static void goGame() {
		int ans = 0;
		for (int i = 102; i <= 987; i++) {
			int io = i / 100;
			int it = (i % 100) / 10;
			int ith = ((i % 100) % 10);
			if(io == it || io == ith || it == ith) continue;
			if(io == 0 || it == 0 || ith == 0) continue;
			boolean isAble = true;
			for (int j = 0; j < ballmap.length; j++) {
				BallMap_2503 ball = ballmap[j];
				int num = ball.getNum();
				int s = ball.getS();
				int b = ball.getB();
				
				int o = num / 100;
				int t = (num % 100) / 10;
				int th = ((num % 100) % 10);
				
				int cnt = 0;
				int cnt2 = 0;
				if(o == io)	cnt++;
				if(t == it)	cnt++;
				if(th == ith) cnt++;
				if(cnt != s) {
					isAble = false; 
					break;
				}
				if(io == t || io == th) cnt2++;
				if(it == o || it == th) cnt2++;			
				if(ith == t || ith == o) cnt2++;
				if(cnt2 != b) {
					isAble = false;
					break;
				}
				
			}
			
			if(isAble) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}

class BallMap_2503 {
	int s;
	int b;
	int num;
	
	public BallMap_2503(int s, int b, int num) {
		super();
		this.s = s;
		this.b = b;
		this.num = num;
	}
	
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
