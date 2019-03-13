#include <iostream>

using namespace std;

int N, M;
int dx[] = { 1,0,1 };
int dy[] = { 0,1,1 };
int map[1001][1001];
int dp[1001][1001] = { 0, };

int max(int a, int b) {
	if (a > b)
		return a;
	else
		return b;
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			dp[i][j] = map[i][j] + max(dp[i - 1][j - 1], max(dp[i - 1][j], dp[i][j - 1]));
		}
	}
	cout << dp[N][M];
	return 0;
}