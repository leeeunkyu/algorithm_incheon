#include <iostream>

using namespace std;

int min_result = 987654321;
int map[11][11];
int init_cnt = 0;
//bool visited[11][11] = { 0, };
void dfs(int five, int four, int three, int two, int one, int paper_cnt);

int main() {	
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1) {
				init_cnt++;
			}
		}
	}
	dfs(5,5,5,5,5,0);
	if (min_result == 987654321)
		cout << -1;
	else
		cout << min_result;
	return 0;
}

void dfs(int five,int four,int three, int two, int one,int paper_cnt) {	
	int cnt = 0;
	int index_i, index_j;
	for (index_i = 0; index_i < 10; index_i++) {
		for (index_j = 0; index_j < 10; index_j++) {
			if (map[index_i][index_j] == 1)
				cnt++;
		}
	}
	if (cnt == 0) {
		if (min_result > paper_cnt)
			min_result = paper_cnt;
		return;
	}
	bool exist=false;
	for (index_i = 0; index_i < 10; index_i++) {
		for (index_j = 0; index_j < 10; index_j++) {
			if (map[index_i][index_j] == 1) {
				exist = true;
				break;
			}
		}
		if (exist)
			break;
	}

	//5X5
	if (five > 0) {
		bool able = true;
		for (int i = index_i; i < index_i + 5; i++) {
			for (int j = index_j; j < index_j + 5; j++) {
				if (map[i][j] == 0 ){//|| visited[i][j]) {
					able = false;
					break;
				}
			}
			if (!able)
				break;
		}
		if (able) {
			for (int i = index_i; i < index_i + 5; i++) {
				for (int j = index_j; j < index_j + 5; j++) {
					map[i][j] = 0;
					//visited[i][j] = true;
				}
			}
			dfs(five - 1, four, three, two, one, paper_cnt +1);
			for (int i = index_i; i < index_i + 5; i++) {
				for (int j = index_j; j < index_j + 5; j++) {
					map[i][j] = 1;
					//visited[i][j] = false;
				}
			}
		}
	}
	//4X4
	if (four > 0) {
		bool able = true;
		for (int i = index_i; i < index_i + 4; i++) {
			for (int j = index_j; j < index_j + 4; j++) {
				if (map[i][j] == 0){// || visited[i][j]) {
					able = false;
					break;
				}
			}
			if (!able)
				break;
		}
		if (able) {
			for (int i = index_i; i < index_i + 4; i++) {
				for (int j = index_j; j < index_j + 4; j++) {
					map[i][j] = 0;
					//visited[i][j] = true;
				}
			}
			dfs(five , four-1, three, two, one, paper_cnt + 1);
			for (int i = index_i; i < index_i + 4; i++) {
				for (int j = index_j; j < index_j + 4; j++) {
					map[i][j] = 1;
					//visited[i][j] = false;
				}
			}
		}
	}
	
	//3X3
	if (three > 0) {
		bool able = true;
		for (int i = index_i; i < index_i + 3; i++) {
			for (int j = index_j; j < index_j + 3; j++) {
				if (map[i][j] == 0){// || visited[i][j]) {
					able = false;
					break;
				}
			}
			if (!able)
				break;
		}
		if (able) {
			for (int i = index_i; i < index_i + 3; i++) {
				for (int j = index_j; j < index_j + 3; j++) {
					map[i][j] = 0;
					//visited[i][j] = true;
				}
			}
			dfs(five, four, three-1, two, one, paper_cnt + 1);
			for (int i = index_i; i < index_i + 3; i++) {
				for (int j = index_j; j < index_j + 3; j++) {
					map[i][j] = 1;
					//visited[i][j] = false;
				}
			}
		}
	}
	//2X2
	if (two > 0) {
		bool able = true;
		for (int i = index_i; i < index_i + 2; i++) {
			for (int j = index_j; j < index_j + 2; j++) {
				if (map[i][j] == 0){// || visited[i][j]) {
					able = false;
					break;
				}
			}
			if (!able)
				break;
		}
		if (able) {
			for (int i = index_i; i < index_i + 2; i++) {
				for (int j = index_j; j < index_j + 2; j++) {
					map[i][j] = 0;
					//visited[i][j] = true;
				}
			}
			dfs(five , four, three, two-1, one, paper_cnt + 1);
			for (int i = index_i; i < index_i + 2; i++) {
				for (int j = index_j; j < index_j + 2; j++) {
					map[i][j] = 1;
					//visited[i][j] = false;
				}
			}
		}
	}
	//1X1
	if (one > 0) {
		bool able = true;
		for (int i = index_i; i < index_i + 1; i++) {
			for (int j = index_j; j < index_j + 1; j++) {
				if (map[i][j] == 0){// || visited[i][j]) {
					able = false;
					break;
				}
			}
			if (!able)
				break;
		}
		if (able) {
			for (int i = index_i; i < index_i + 1; i++) {
				for (int j = index_j; j < index_j + 1; j++) {
					map[i][j] = 0;
					//visited[i][j] = true;
				}
			}
			dfs(five, four, three, two, one-1, paper_cnt + 1);
			for (int i = index_i; i < index_i + 1; i++) {
				for (int j = index_j; j < index_j + 1; j++) {
					map[i][j] = 1;
					//visited[i][j] = false;
				}
			}
		}
	}
}