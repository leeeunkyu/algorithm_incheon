#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
using namespace std;
int gear[5][8];
bool info[4];
bool visited[5];
int number[5] = { 0,1,2,4,8 };
void reset(bool (*map), int size) {
	for (int i = 0; i < size; i++) {
		map[i] = false;
	}
}
void rotate(int num, int dir) {
	visited[num] = true;
	int odir = -1;
	if (dir == 1) {//시계방향
		int tmp = gear[num][7];
		for (int i = 6; i >= 0; i--) {
			gear[num][i + 1] = gear[num][i];
		}
		gear[num][0] = tmp;
	}
	else {//반시계
		odir = 1;
		int tmp = gear[num][0];
		for (int i = 1; i <= 7; i++) {
			gear[num][i - 1] = gear[num][i];
		}
		gear[num][7] = tmp;
	}
	//1. 양쪽 체크해서 움직일 기어 체크 (재귀)

	if (num < 4) {
		//info가 true일때 && visited==false
		//
		if (info[num] && !visited[num + 1]) {
			rotate(num + 1, odir);
		}
		//오른쪽꺼 회전, 방향 반대
	}
	if (num > 1) {
		//info-1 true일때
		//왼쪽꺼 회전, 방향 반대
		if (info[num - 1] && !visited[num - 1]) {
			rotate(num - 1, odir);
		}
	}
	//2. 회전해야하는 기어 회전하기

	
}
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		int K;
		scanf("%d", &K);
		reset(info, 4);
		reset(visited, 5);
		//memset(gear, 0, 5 * 8);
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				scanf("%d", &gear[i][j]);
			}
		}

		for (int i = 0; i < K; i++) {
			memset(info, false, 4);
			memset(visited, false, 4);
			for (int i = 1; i <= 3; i++) {
				if (gear[i][2] != gear[i + 1][6]) {
					info[i] = true;
				}
			}
			int num, dir;
			scanf("%d %d", &num, &dir);
			rotate(num, dir);
			/*for (int p = 1; p <= 4; p++) {
				for (int q = 0; q < 8; q++) {
					printf("%d ", gear[p][q]);
				}
				printf("\n");
			}*/
		}
		int res = 0;
		for (int i = 1; i <= 4; i++) {
			if (gear[i][0] == 1) {
				res += number[i];
			}
		}
		printf("#%d %d\n", t, res);
	}
}
