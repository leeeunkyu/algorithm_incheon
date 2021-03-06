package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//16:00 16:30
public class Main_14890 {

	static int n;
	static int x;
	static int[][] arr;
	static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		x = Integer.parseInt(str[1]);
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
			}
		}
		res = n;
		go();
		System.out.println(res);
		
	}

	private static void go() {
		for (int i = 0; i < n; i++) {
			int top = arr[i][0];
			int cnt = 1;
			boolean[] visited = new boolean[n];
			loop:
			for (int j = 1; j < n; j++) {
				int val = arr[i][j];
				if(top == val) {
					System.out.println("dd i: "+i+" j: "+j+" cnt: "+cnt);
					++cnt;
				}else {
					if(Math.abs(top - val) > 1) {
						System.out.println("1. "+i);
						--res;
						break;
					}
					if(top > val) {
						System.out.println("2. i: "+i+" j: "+j+" cnt: "+cnt);
						for (int k = 0; k < x; k++) {
							if(j + k >= n || arr[i][j + k] != top - 1) {
								--res;
								System.out.println("2. "+i);
								break loop;
							}
						}
						for (int l = 0; l < x; l++) {
							if(visited[j + l]) {
								--res;
								break loop;	
							}
							visited[j + l] = true;
						}
						j += x - 1;
						top = arr[i][j];
						cnt = x;
						System.out.println("2. ~i: "+i+" j: "+j+" cnt: "+cnt);
					} else if(top < val) {
						System.out.println("i: "+i+" j: "+j+" cnt: "+cnt);
						if(!(cnt >= x)) {
							System.out.print("3. idx: "+i+"  ");
							--res;
							break;
						}
						if(visited[j - 1]) {
							--res;
							break;	
						}
						for (int k = 0; k < cnt; k++) {
							if(visited[j - k]) {
								System.out.print("3. idx: "+i+"  ");
								--res;
								break loop;	
							}
							visited[j - k] = true;
						}
						top = val;
						cnt = 1;
					}
				}
			}
		}
	}
}
