#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#define INF 1000;
using namespace std;
int N, W, H;
int minCount = INF;
int map[15][12] = { 0, };
int dx[] = { 0,1,0,-1 }; //동남북서
int dy[] = { 1,0,-1,0 };

void copy(int(*a)[12], int(*b)[12]) {
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			b[i][j] = a[i][j];
		}
	}
}

//범위 체크
bool check_range(int x, int y) {
	if (x >= 0 && y >= 0 && x < H && y < W) {
		return true;
	}
	else
		return false;
}

//벽돌 던지기
void boom(int row, int col) {
	if (!map[row][col]) return;
	int k = map[row][col];
	map[row][col] = 0;
	for (int i = 0; i < k; i++) {
		for (int z = 0; z < 4; z++) {
			int nr = row + dx[z] * i;
			int nc = col + dy[z] * i;
			if (check_range(nr, nc) && map[nr][nc] != 0) {
				boom(nr, nc);
			}
		}
	}
}
void reset() {
	int ans = 0;
	for (int i = 0; i < W; i++) {
		for (int j = H - 1; j >= 1; j--) {
			for (int k = j - 1; k >= 0; k--) { 
				if (map[j][i] != 0) break;	// j가 0이면 continue
				if (map[k][i] != 0) {		// k
					map[j][i] = map[k][i];
					map[k][i] = 0;
					break;
				}
			}
		}
	}
}
void solve(int n) {
	int copy_map[15][12] = { 0, };
	copy(map, copy_map);

	//벽돌 N번 던졌을 때
	if (n == N) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}
		if (minCount > cnt) minCount = cnt;
		return;
	}

	for (int i = 0; i < W; i++) {
		int x = 0, y = i;
		//0이면 점프
		while (check_range(x, y) && map[x][y] == 0) {
			x++;
		}
		//벽돌 깨뜨리기
		boom(x, y);
		//벽돌 내린다
		reset();
		solve(n + 1);
		copy(copy_map, map);
	}
}
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		minCount = INF;
		scanf("%d %d %d", &N, &W, &H);
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				scanf("%d", &map[i][j]);
			}
		}
		solve(0);
		printf("#%d %d", t, minCount);
	}
}
