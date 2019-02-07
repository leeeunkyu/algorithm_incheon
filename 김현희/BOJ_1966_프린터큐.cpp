#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;
int map[100] = { 0, };
bool desc(int a, int b) {
	return a > b;
}

int main() {
	int t;
	scanf("%d", &t);
	for (int i = 1; i <= t; i++) {
		queue<pair<int,int>> paper;//index, priority
		map[100] = { 0, };
		int N, M;
		scanf("%d %d", &N, &M);
		for (int j = 0; j < N; j++) {
			int p = 0;
			scanf("%d", &p);
			map[j] = p;
			paper.push(make_pair(j,p));
		}
		sort(map, map + N, desc);
		int now = 0;
		int cnt = 0;
		while (true) {
			if (map[now] == paper.front().second) {
				cnt++;
				if (paper.front().first == M) {
					printf("%d\n", cnt);
					break;
				}
				int tmp1 = paper.front().first;
				int tmp2 = paper.front().second;
				map[now] = -1;
				paper.pop();
				paper.push(make_pair(tmp1, tmp2));
				now++;
			}
			else {
				int tmp1 = paper.front().first;
				int tmp2 = paper.front().second;
				paper.pop();
				paper.push(make_pair(tmp1, tmp2));
			}
			
		}

		
	}
}
