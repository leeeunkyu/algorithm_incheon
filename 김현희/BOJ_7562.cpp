#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <cstring>
using namespace std;
const int INF = 987654321;
int map[300][300];
bool visited[300][300];
int dx[8] = { 2,1,-2,-1,2,1,-2,-1 };
int dy[8] = { 1,2,1,2,-1,-2,-1,-2 };
int I;
int now_x, now_y;
int des_x, des_y;
int res = 0;

void reset(int (*map1)[300]) {
	for (int i = 0; i < 300; i++) {
		for (int j = 0; j < 300; j++) {
			map1[i][j] = INF;
		}
	}
}
void FindWay(int a, int b) {
	queue<pair<int, int>> q;
	reset(map);
	q.push(make_pair(a, b));
	map[a][b] = 0;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		if (x == des_x && y == des_y) {
			res = map[x][y];
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
			if (!visited[nx][ny]) {
				q.push(make_pair(nx, ny));
				visited[nx][ny] = true;
				map[nx][ny] = min(map[nx][ny],map[x][y] + 1);	
			}
		}
	}

}
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		memset(visited, false, sizeof(visited));

		scanf("%d", &I);//체스판 크기

		scanf("%d %d", &now_x, &now_y);
		scanf("%d %d", &des_x, &des_y);
		FindWay(now_x, now_y);
		printf("%d\n", res);
	}
}
