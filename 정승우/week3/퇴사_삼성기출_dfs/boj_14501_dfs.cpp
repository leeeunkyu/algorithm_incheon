#include <iostream>
#include <algorithm>
#define SIZE 16

using namespace std;

int n,result;
int cost[SIZE];
int day[SIZE];

void dfs(int s, int sum) {
	if (s == n + 1) {
		result = max(result, sum);
		return;
	}

	if (s + day[s] <= n + 1)
		dfs(s + day[s], sum + cost[s]);
	if (s + 1 <= n + 1)
		dfs(s + 1, sum);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	
	cin >> n;
	for (int i = 1; i <= n; i++)	cin >> day[i] >> cost[i];

	dfs(1, 0);
	cout << result;
	return 0;
}