#include <iostream>
#include <algorithm>
#define SIZE 51
#define INT 0x7fffffff

using namespace std;
int day[SIZE], cost[SIZE], dp[SIZE];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	int n, result = 0;
	cin >> n;
	for (int i = 1; i <= n; i++)	cin >> day[i] >> cost[i];

	for (int i = 1; i <= n; i++) {
		int nx1 = i + day[i];
		int nx2 = i + 1;

		if (nx1 <= n + 1)
			dp[nx1] = max(dp[nx1], dp[i] + cost[i]);
		if (nx2 <= n + 1)
			dp[nx2] = max(dp[nx2], dp[i]);

		result = max(max(dp[nx1], result), dp[nx2]);
	}
	cout << result;

	return 0;
}