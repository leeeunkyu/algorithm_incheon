#include <iostream>

using namespace std;

int D=13, W=20, K=0;
int map[14][21];
int result = 999999999;

void check(int cnt) {
	for (int i = 0; i < W; i++) {
		int cnt = 1;
		for (int j = 0; j < D - 1; j++) {
			if (cnt >= K) {
				break;
			}
			if (map[j][i] != map[j + 1][i]) {
				cnt = 1;
			}
			else {
				cnt++;
			}
		}
		if (cnt < K) {
			return;
		}
	}
	if (result > cnt) {
		result = cnt;
	}
}
void dfs(int d, int cnt) {
	if (cnt > result) {
		return;
	}
	if (d >= D) {
		check(cnt);
		return;
	}
	
	//no choice
	dfs(d + 1, cnt);

	//A
	int temp[20];
	for (int i = 0; i < W; i++) {
		temp[i] = map[d][i];
	}
	for (int i = 0; i < W; i++) {
		map[d][i] = 0;
	}
	dfs(d + 1, cnt + 1);
	for (int i = 0; i < W; i++) {
		map[d][i]=temp[i];
	}

	//B
	for (int i = 0; i < W; i++) {
		temp[i] = map[d][i];
	}
	for (int i = 0; i < W; i++) {
		map[d][i] = 1;
	}
	dfs(d + 1, cnt + 1);
	for (int i = 0; i < W; i++) {
		map[d][i] = temp[i];
	}
}

int main() {
	int tc, T;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		result = 999999999;

		cin >> D >> W >> K;
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = 0;
			}
		}

		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				cin >> map[i][j];
			}
		}
		dfs(0, 0);

		cout << "#" << tc << " " << result << endl;
	}
	return 0;
}
