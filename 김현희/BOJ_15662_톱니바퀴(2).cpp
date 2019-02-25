#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
using namespace std;
int gear[1001][9];
bool isMove[1001];
bool visited[1001];
int T, K;
void reset() {
	for (int i = 1; i <= T; i++) {
		visited[i] = false;
	}
	for (int i = 1; i <= T-1; i++) {
		isMove[i] = false;
	}
}
void check_move() {
	//톱니 갯수 T,
	for (int i = 1; i <= T-1; i++) {
		if (gear[i][3] != gear[i + 1][7]) {
			isMove[i] = true;
		}
	}
	
}
void move_left(int num) {
	int tmp = gear[num][1];
	for (int i = 1; i < 8; i++) {
		gear[num][i] = gear[num][i + 1];
	}
	gear[num][8] = tmp;
}
void move_right(int num) {
	int tmp = gear[num][8];
	for (int i = 8; i >= 2; i--) {
		gear[num][i] = gear[num][i - 1];
	}
	gear[num][1] = tmp;
}
void move_gear(int num, int dir) {
	int next = 1;
	visited[num] = true;
	if (dir == 1) {
		move_right(num);
		next = -1;
	}
	else
		move_left(num);
	
	if (isMove[num-1] && !visited[num-1] && num-1 >= 1) { //왼쪽
		move_gear(num - 1, next);
	}

	if (isMove[num] && !visited[num+1] && num+1 <= T) { //오른쪽
		move_gear(num + 1, next);
	}
}
int main() {
	scanf("%d", &T);
	for (int i = 1; i <= T; i++) {
		for (int j = 1; j <= 8; j++) {
			int tmp;
			scanf("%1d", &tmp);
			gear[i][j] = tmp;
		}
	}

	scanf("%d", &K);
	for (int i = 0; i < K; i++) {
		int num, dir;
		scanf("%d %d", &num, &dir);
		//m_gear.push_back(make_pair(num, dir)); //p번 q방향
		reset();
		check_move();
		move_gear(num, dir);
	}

	int res = 0;
	for (int i = 1; i <= T; i++) {
		res+=gear[i][1];
	}
	printf("%d", res);


}
