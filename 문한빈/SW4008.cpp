#include <iostream>

using namespace std;

int N;
long long mi = 100000000;
long long ma = -100000000;

void dfs(int plus, int minus, int mul, int div, int idx, long long result);
int num[13] = {0,};
int main() {
	int T, tc;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		long long result = 0;
		mi = 100000000;
		ma = -100000000;

		cin >> N;
		for (int i = 0; i < N; i++) {
			num[i] = 0;
		}
		int plus, minus, mul, div;
		
		
		cin >> plus >> minus >> mul >> div;


		for (int i = 0	; i < N; i++) {
			cin >> num[i];
		}
		
		dfs(plus, minus, mul, div, 1,num[0]);

		cout << "#" << tc << " " << ma-mi << endl;
	}
	return 0;
}

void dfs(int plus, int minus, int mul, int div, int idx, long long result) {
	if (idx >= N) {
		if (ma < result)
			ma = result;
		if (mi > result)
			mi = result;
		return;
	}
	if(plus>0)
		dfs(plus - 1, minus, mul, div, idx + 1, result + num[idx]);
	if (minus>0)
		dfs(plus, minus - 1, mul, div, idx + 1, result - num[idx]);
	if (mul>0)
		dfs(plus, minus, mul - 1, div, idx + 1, result * num[idx]);
	
	if (div>0&&num[idx] != 0)
		dfs(plus, minus, mul, div - 1, idx + 1, result / num[idx]);
}