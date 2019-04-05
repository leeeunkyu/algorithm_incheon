#include <iostream>

using namespace std;

int map[21][21];
int N, M;
int result = 0;

int cal_distance(int x, int y, int x2, int y2) {
	return abs(x - x2) + abs(y - y2);
}
void cnt_house(int y, int x, int k);

int main() {
	int T, tc;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		result = 0;
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				map[i][j]=0;
			}
		}
		
		cin >> N >> M;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k <= N+1; k++) {
					cnt_house(i, j, k);
				}
			}
		}
	
		cout << "#" << tc << " " << result << endl;
	}
	return 0;
}

void cnt_house(int y, int x, int k) {
	int start_x = x - k + 1;
	int start_y = y - k + 1;
	int end_x = x + k - 1;
	int end_y = y + k - 1;

	if (start_x < 0)
		start_x = 0;
	if (start_y < 0)
		start_y = 0;
	if (end_x > N-1)
		end_x = N-1;
	if (end_y > N - 1)
		end_y = N - 1;
	
	int cnt = 0;
	for (int i = start_y; i <= end_y; i++) {
		for (int j = start_x; j <= end_x; j++) {
			if ((cal_distance(x, y, j, i) <= k - 1) && map[i][j]==1) {
				cnt++;
			}
		}
	}
	int operate = k * k + (k - 1)*(k - 1);
	if (M*cnt >= operate && result<cnt) {
		result = cnt;
	}
}