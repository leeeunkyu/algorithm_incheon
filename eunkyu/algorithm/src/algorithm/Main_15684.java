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
	static int size;
	
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
		int temp = -1;
		for (int i = 1; i <= 3; i++) {
			size = i;
			boolean res = setBridge(1, i);
			if(res) {
				temp = i;
				break;
			}	
		}
		System.out.println(temp);
	}

	private static boolean setBridge(int cnt, int idx) {
		//idx개를 놓을 수 있는 경우.
		int setCnt = 0;
		boolean[] visited = new boolean[n + 1];
		for (int i = 1; i < h + 1; i++) {
			visited = new boolean[n + 1];
			for (int j = 1; j < n; j++) {
				if(arr[i][j] == 0 && setCnt < idx) {
/*					System.out.println("init: "+cnt+"   /  "+idx);
*//*					print();
*/					boolean setting = false;
					if(lcheck(i, j)) {
						if(j - 1 >= 1) {
							if(j + 1 < n + 1) {
								if(arr[i][j + 1] == 0) {
									arr[i][j] = 1;
									arr[i][j - 1] = 1;
									visited[j] = true;
									visited[j - 1] = true;
									if(cnt < idx) {
										setBridge(cnt + 1, idx);
										arr[i][j] = 0;
										arr[i][j - 1] = 0;
									}
									setting = true;
								}
							} else {
								arr[i][j] = 1;
								arr[i][j - 1] = 1;
								if(cnt < idx) {
									setBridge(cnt + 1, idx);
									arr[i][j] = 0;
									arr[i][j - 1] = 0;
								}
								setting = true;
							}
							
						}
					}
					if(setting && cnt == idx) {
						if(goGame()) {
							return true;
						}
						arr[i][j] = 0;
						arr[i][j - 1] = 0;
						
						setting = false;
					}
					
					if(rcheck(i, j)) {
						if(j + 1 < n + 1 && !visited[j + 1]) {
							if(j - 1 >= 1) {
								if(arr[i][j - 1] == 0) {
									arr[i][j] = 1;
									arr[i][j + 1] = 1;
									if(cnt < idx) {
										setBridge(cnt + 1, idx);
										arr[i][j] = 0;
										arr[i][j + 1] = 0;
									}
									setting = true;
								}
							} else {
								arr[i][j] = 1;
								arr[i][j + 1] = 1;
								if(cnt < idx) {
									setBridge(cnt + 1, idx);
									arr[i][j] = 0;
									arr[i][j + 1] = 0;
								}
								setting = true;
							}
						}
					}
					
					if(setting && cnt == idx) {
						if(goGame()) {
							return true;
						}
						arr[i][j] = 0;
						arr[i][j + 1] = 0;
						setting = false;
					}
				}
			}
		}
		return false;
	}

	private static boolean rcheck(int i, int j) {
		for (int k = n; k > j; k--) {
			if(arr[i][k] == 1 && Math.abs(j - k) <= 2) {
				return false;
			}
		}
		return true;
	}

	private static boolean lcheck(int i, int j) {
		for (int k = 0; k < j; k++) {
			if(arr[i][k] == 1 && Math.abs(j - k) <= 2) {
				return false;
			}
		}
		return true;
	}

	private static boolean goGame() {
		System.out.println("start");
		print();
		for (int i = 1; i < h; i++) {
			int idx = down(i);
			if(i != idx) {
				return false;
			}
		}
		return true;
	}

	private static void print() {
		System.out.println(size);
		for (int k = 0; k < h + 2; k++) {
			for (int j = 0; j < n + 1; j++) {
				System.out.print(arr[k][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static int down(int idx1) {
		for (int i = 0; i < n + 3; i++) {
			if(arr[i][idx1] == 1) {
				if(idx1 - 1 >= 1 && arr[i][idx1 - 1] == 1) {
					idx1 = idx1 -1;
				} else if(idx1 + 1 < h && arr[i][idx1] == 1) {
					idx1 = idx1 + 1;
				}
			}
		}
		return idx1;
	}
}
