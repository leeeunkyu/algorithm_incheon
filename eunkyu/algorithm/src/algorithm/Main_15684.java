package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//4시30분까지
public class Main_15684 {
	
	static int n;
	static int m;
	static int h;
	static int arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		h = Integer.parseInt(str[2]);
		arr = new int[h + 2][n + 1];
		
		for (int i = 0; i < m; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]);
			int b = Integer.parseInt(info[1]);
			arr[a][b] = 1;
			arr[a][b + 1] = 1;
		}
		
		for (int i = 0; i < h + 2; i++) {
			for (int j = 0; j < n + 1; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		if(goGame()) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < 3; i++) {
			boolean res = setBridge(i);
			if(res)
				break;
		}
	}

	private static boolean setBridge(int i) {
		for (int j = 1; j < h + 1; j++) {
			for (int j2 = 1; j2 < n + 1; j2++) {
				boolean isSet = false;
				if(n == 2) {
					
				} else if(n == 3) {
					
				}
				else if(n >= 4) {
					if(j2 - 1 <= 1) {
						if(arr[j][j2] != 1 && arr[j][j2 + 1] != 1 && (j2 + 2 < n + 1) &&arr[j][j2 + 2] != 1) {
							arr[j][j2] = 1;
							arr[j][j2 + 1] = 1;
							isSet = true;
						}
					} else if(j2 + 1 > n + 1) {
						if((j2 - 2 <= 1) && arr[j][j2 - 2] != 1 && arr[j][j2 - 1] != 1 && arr[j][j2] != 1) {
							arr[j][j2 - 1] = 1;
							arr[j][j2] = 1;
							isSet = true;
						}
					} else {
						if((n >= 4) && arr[j][j2 - 1] != 1 && arr[j][j2] != 1 && arr[j][j2 + 1] != 1 && arr[j][j2 + 2] != 1) {
							arr[j][j2] = 1;
							arr[j][j2 + 1] = 1;
							isSet = true;
						}
					}	
				}
				if(isSet) {
					for (int k = 0; k < n + 3; k++) {
						for (int k2 = 0; k2 < h; k2++) {
							System.out.print(arr[k][k2]);
						}
						System.out.println();
					}
					boolean res = goGame();
					if(!res) {
						arr[j][j2] = 0;
					} else {
						System.out.println(i);
						return true;
					}	
				}
			}
		}
		return false;
	}

	private static boolean goGame() {
		for (int i = 1; i < h; i++) {
			int idx = down(i, 0);
			if(i != idx) {
				return false;
			}
		}
		return true;
	}

	private static int down(int idx1, int idx2) {
		for (int i = idx2; i < n + 3; i++) {
			if(arr[i][idx1] == 1) {
				if(idx1 - 1 >= 1 && arr[i][idx1 - 1] == 1) {
					idx1 = down(idx1-1, i + 1);
					break;
				} else if(idx1 + 1 < h && arr[i][idx1] == 1) {
					idx1 = down(idx1+1, i + 1);
					break;
				}
			}
		}
		return idx1;
	}
}
