#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <string.h>
#include <queue>
#include <set>
using namespace std;
int m, n, r, c, l;//세로,가로,맨홀위치x,y,시간
int map[50][50];
bool visited[50][50];
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };
int res = 0;
vector<int> PipeShape(int x, int y) {
	int shape = map[x][y];
	vector<int> dir;
	if (shape == 1) {
		for (int i = 0; i < 4; i++) {
			dir.push_back(i);
		}
	}
	else if (shape == 2) {
		dir.push_back(0);
		dir.push_back(1);
	}
	else if (shape == 3) {
		dir.push_back(2);
		dir.push_back(3);
	}
	else if (shape == 4) {
		dir.push_back(0);
		dir.push_back(3);
	}
	else if (shape == 5) {
		dir.push_back(1);
		dir.push_back(3);
	}
	else if (shape == 6) {
		dir.push_back(1);
		dir.push_back(2);
	}
	else if (shape == 7) {
		dir.push_back(0);
		dir.push_back(2);
	}
	return dir;
}
bool nextPipe(int nx, int ny, int d) {
	set<int> dir;
	int shape = map[nx][ny];
	if (shape == 1) {
		for (int i = 0; i < 4; i++) {
			dir.insert(i);
		}
	}
	else if (shape == 2) {
		dir.insert(0);
		dir.insert(1);
	}
	else if (shape == 3) {
		dir.insert(2);
		dir.insert(3);
	}
	else if (shape == 4) {
		dir.insert(0);
		dir.insert(3);
	}
	else if (shape == 5) {
		dir.insert(1);
		dir.insert(3);
	}
	else if (shape == 6) {
		dir.insert(1);
		dir.insert(2);
	}
	else if (shape == 7) {
		dir.insert(0);
		dir.insert(2);
	}
	else
		return false;

	if (d == 0) {
		if (dir.end() == dir.find(1)) {
			return false;
		}
		else
			return true;
	}
	else if (d == 1) {
		if (dir.end() == dir.find(0)) {
			return false;
		}
		else
			return true;
	}
	else if (d == 2) {
		if (dir.end() == dir.find(3)) {
			return false;
		}
		else
			return true;
	}
	else if (d == 3) {
		if (dir.end() == dir.find(2)) {
			return false;
		}
		else
			return true;
	}
}
void BFS() {
	queue<pair<int, int>> q;
	//맨홀 좌표 넣기
	q.push(make_pair(r, c));
	int t = 1;
	//방문 체크
	visited[r][c] = true;
	while (!q.empty()) {
		//시간 체크를 위해 사이즈 측정
		int size1 = q.size();
		if (l == t) return;
		for (int i = 0; i < size1; i++) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();
			vector<int> dir = PipeShape(x, y);
			//파이프모양만큼 회전
			for (int j = 0; j < dir.size(); j++) {
				int nx = x + dx[dir[j]];
				int ny = y + dy[dir[j]];
				if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				//다음 파이프 모양 확인 
				if (nextPipe(nx, ny, dir[j]) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					//printf("%d %d\n", nx, ny);
					res++;
					q.push(make_pair(nx, ny));
				}
			}
			//printf("time:%d\n", t);
		}
		t++;
	}
}
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		res = 1;
		scanf("%d %d %d %d %d", &m, &n, &r, &c, &l);
		memset(map, 0, sizeof(map));
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				scanf("%d", &map[i][j]);
			}
		}
		BFS();
		printf("#%d %d\n", t, res);
	}
}
