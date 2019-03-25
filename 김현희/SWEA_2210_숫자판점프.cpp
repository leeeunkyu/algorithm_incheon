#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;
int map[5][5];
char mmap[5][5];
bool visited[5][5];
int dx[] = { 1,-1,0,0 };
int dy[] = { 0,0,1,-1 };
set<string> res;
void DFS(int cnt, int x, int y, string s) {
	/*if (cnt == 6) {
		if (!res.empty()) {
			if (res.end() == find(res.begin(), res.end(), s)) {
				res.push_back(s);
			}
		}
		else
			res.push_back(s);
		return;
	}*/
	if (cnt == 6) {
		res.insert(s);
		return;
	}
	s += mmap[x][y];
	//printf("%s", s);
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
		DFS(cnt + 1, nx, ny, s);
	}
}
int main() {
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			mmap[i][j] = map[i][j]+'0';
		}
	}
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			/*string line;
			line += map[i][j];*/
			DFS(0, i, j, "");
		}
	}
	printf("%d", res.size());
}
