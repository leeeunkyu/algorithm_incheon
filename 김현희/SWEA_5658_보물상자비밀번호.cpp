#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <string>
#include <algorithm>
#include <vector>
#include <math.h>
using namespace std;
vector<vector<char>> res;
vector<int> numarr;
int rot;
bool desc(int a, int b) {
	return a > b;
}
int getNum(char tmp) {
	if (tmp == 'A') {
		return 10;
	}
	else if (tmp == 'B') {
		return 11;
	}
	else if (tmp == 'C') {
		return 12;
	}
	else if (tmp == 'D') {
		return 13;
	}
	else if (tmp == 'E') {
		return 14;
	}
	else if (tmp == 'F') {
		return 15;
	}
	else
		return 0;
}
void changeNum() {
	for (int i = 0; i < res.size(); i++) {
		int num = 0;
		for (int j = 0; j < rot; j++) {
			if ((int)res[i][j] > (int)'9') {
				num += getNum(res[i][j])*pow(16, rot-j-1);
			}
			else {
				num += (int)(res[i][j] - '0')*pow(16, rot-j-1);
			}
		}
		numarr.push_back(num);
	}
}
bool check(vector<char> word) {
	for (int i = 0; i < res.size(); i++) {
		bool isNot = false;
		for (int j = 0; j < rot; j++) {
			if (res[i][j] != word[j]) {//중복아님
				isNot = true;
				break;
			}
		}
		if (isNot == false) {//중복
			/*printf("중복: ");
			for (int j = 0; j < rot; j++) {
				printf("%c", word[j]);
			}
			printf("\n");*/
			return true;
		}
	}
	return false;
}
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		int N, K;
		res.clear();
		numarr.clear();
		scanf("%d %d", &N, &K);
		char map[28];
		scanf("%s", map);
		rot = N / 4;
		for (int i = 0; i < rot; i++) {
			map[N + i] = map[i];
		}
		for (int i = 0; i < rot; i++) {
			int cnt = 0;
			vector<char> word;
			for (int j = 0; j < N; j++) {
				if (cnt < rot) {
					word.push_back(map[i + j]);
					//printf("%d회전:%c ",i, map[j + i]);
					cnt++;
				}
				if (cnt == rot) {
					if (!check(word)) {
						res.push_back(word);
					}
					word.clear();
					cnt = 0;
				}	
			}
		}
		changeNum();
		sort(numarr.begin(), numarr.end(),desc);
		printf("#%d %d\n", t, numarr[K - 1]);
	}
}
