#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;
int n, m;
int map[500][500];
bool visited[500][500];
int maxVal = 0;
int dx[] = { -1,0,1,0,-1,0 };
int dy[] = { 0,-1,0,1,0,-1 };
void AnotherCase(int x, int y, int cnt, int val) {
	//방향
	
	for (int i = 0; i < 4; i++) {
		int tmpval = val;
		int tmpcnt = cnt;
		for (int j = i; j <= i+2; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
			tmpcnt++;
			tmpval += map[nx][ny];
		}
		if ((maxVal < tmpval) && tmpcnt==4) {
			maxVal = tmpval;
		}
	}
}
void DFS(int x, int y, int cnt, int val) {
	
	if (cnt == 4) {
		if (maxVal < val) {
			maxVal = val;
		}
		return;
	}
	
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
		if (visited[nx][ny] == false) {
		visited[nx][ny] = true;
		val += map[nx][ny];
		DFS(nx, ny, cnt + 1, val);
		val -= map[nx][ny];
		visited[nx][ny] = false;
		}

	}
}
int main() {
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			visited[i][j] = false;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			visited[i][j] = true;
			DFS(i, j, 1, map[i][j]);
			AnotherCase(i, j, 1, map[i][j]);
			visited[i][j] = false;
		}
	}
	printf("%d\n", maxVal);
}
