package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//15:10 ~ 16: 05
public class Solution_2112 {
	
	static int d;
	static int w;
	static int k;
	static int[][] arr;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			String[] info = br.readLine().split(" ");
			d = Integer.parseInt(info[0]);
			w = Integer.parseInt(info[1]);
			k = Integer.parseInt(info[2]);
			arr = new int[d][w];
			for (int i = 0; i < d; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < w; j++) {
					int val = Integer.parseInt(str[j]);
					arr[i][j] = val;
				}
			}
			int cnt = 0;
			res = 0;
			boolean isOk = checkFilm();
			while(!isOk) {
				inject(cnt + 1);	
				cnt++;
				if(cnt == d) {
					res = d;
				}
				if(res != 0)
					break;
			}
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static boolean inject(int cnt) {
		/*for (int i = 0; i < (1 << d); i++) {
			if(Integer.bitCount(i) == cnt) {
				int[] idxs = new int[cnt];
				int temp = 0;
				for (int j = 0; j < d; j++) {
					if(((1 << j) & i) > 0) {
						idxs[temp] = j;
						++temp;
					}
				}
				goInject(idxs, 0);
				if(res != 0) {
					return true;
				}
			}
		}*/
		
		int r = cnt;
		int[] combArr = new int[r];
		doCombination(combArr, r, 0, 0, cnt);
		return false;

		
	}

	private static void doCombination(int[] combArr, int r, int index, int target, int cnt) {
		if(res != 0) {
			return ;
		}
		if(r == 0) {
			
			int[] idxs = new int[cnt];
			for (int i = 0; i < cnt; i++) {
				idxs[i] = combArr[i];
			}
			goInject(idxs, 0);
			
		} else if(target == d) {
			return;
		} else {
			combArr[index] = target;
			doCombination(combArr, r - 1, index + 1, target + 1, cnt);
			doCombination(combArr, r, index, target + 1, cnt);
		}
	}

	private static boolean goInject(int[] idxs, int idx) {
		if(idx == idxs.length) {
			if(checkFilm()) {
				res = idxs.length;
				return true;
			}
			return false;
			
		}
		for (int i = 0; i < 2; i++) {
			int[] temps = new int[w];
			for (int j = 0; j < w; j++) {
				temps[j] = arr[idxs[idx]][j];
				arr[idxs[idx]][j] = i;
			}
			boolean b = goInject(idxs, idx + 1);
			for (int j = 0; j < w; j++) {
				arr[idxs[idx]][j] = temps[j];
			}
			if(b)
				return b;
		}
		return false;
		
	}

	private static boolean checkFilm() {
		boolean[] check = new boolean[w];
		for (int x = 0; x < w; x++) {
			int cnt = 0;
			int val = -1;
			for (int y = 0; y < d; y++) {
				if(val != arr[y][x]) {
					cnt = 1;
					val = arr[y][x];
				} else {
					++cnt;
				}
				if(cnt == k) {
					check[x] = true;
				}
			}
			
		}
		
		for (int i = 0; i < w; i++) {
			if(!check[i])
				return false;
		}
		return true;
	}
}
