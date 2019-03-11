#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
using namespace std;
int n, m;
int map[301][301];
bool visited[301][301];
vector<pair<int, int>> ice;
int ans = 0;
int dx[] = { 1,-1,0,0 };
int dy[] = { 0,0,1,-1 };
void YearsLater() {
	for (int i = 0; i < ice.size(); i++){
		int x = ice[i].first;
		int y = ice[i].second;
		if (map[x][y] > 0) {
			map[ice[i].first][ice[i].second] -= 1;
		}
		
	}
}
void BFS(int a, int b) {
	queue<pair<int, int>> q;
	q.push(make_pair(a, b));
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			if (!visited[nx][ny] && map[nx][ny] > 0) {
				q.push(make_pair(nx, ny));
			}
		}
	}
}
int main() {
	scanf("%d %d", &n, &m);
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] != 0) {
				ice.push_back(make_pair(i, j));
			}
		}
	}
	int res = 0;
	for (int k = 1; k <= 10; k++) {
		//년도 지남
		YearsLater();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
		int cnt = 0;
		bool c = false;
		//빙산찾기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					cnt++;
					BFS(i, j);
				}
				if (cnt > 1) {
					res = k;
					c = true;
					break;
				}
			}
			if (c) break;
		}
		if (c) break;
	}
	printf("%d\n", res);
}
