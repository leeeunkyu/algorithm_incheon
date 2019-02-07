#define _CRT_SECURE_NO_WARNINGS
#define MAX 500001
#include <cstdio>
using namespace std;
int map[MAX];
int res[MAX];
int main() {
	int n = 0;

	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		scanf("%d", &map[i]);
	}
	int now = n;
	while (now > 0) {
		bool isOK = 0;
		for (int i = now - 1; i > 0; i--) {
			if (isOK == true) break;
			if (map[now] <= map[i]) {
				isOK = true;
				res[now] = i;
				//printf("%d %d\n", res[now], i);
			}
		}
		if (!isOK) {
			res[now] = 0;
		}
		now--;
	}

	for (int i = 1; i <= n; i++) {
		printf("%d ", res[i]);
	}
	printf("\n");
}
