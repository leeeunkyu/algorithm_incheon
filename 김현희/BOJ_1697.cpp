#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <cstring>
using namespace std;
const int INF = 987654321;
int N, K, res = 0;
int map[100001];
bool visited[100001];
void reset(int(*map1)) {
	for (int i = 0; i < 100001; i++) {
		map1[i] = INF;
	}
}
void findSister(int now) {
	reset(map);
	map[now] = 0;
	queue<int> q;
	q.push(now);
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		if (x == K) {
			res = map[x];
			return;
		}
		
		int move1 = x + 1;
		int move2 = x - 1;
		int move3 = x * 2;
		if (move1 < 100001) {
			if (!visited[move1]) {
				visited[move1] = true;
				map[move1] = min(map[move1], map[x] + 1);
				q.push(move1);
			}
		}
		if (move2 >= 0) {
			if (!visited[move2]) {
				visited[move2] = true;
				map[move2] = min(map[move2], map[x] + 1);
				q.push(move2);
			}
		}
		if (move3 < 100001) {
			if (!visited[move3]) {
				visited[move3] = true;
				map[move3] = min(map[move3], map[x] + 1);
				q.push(move3);
			}
		}
	}
	

}
int main() {

	scanf("%d %d", &N, &K);
	memset(visited, false, 100001);
	findSister(N);
	printf("%d", res);
}
