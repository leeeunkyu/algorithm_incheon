#include <iostream>

using namespace std;
void dfs(int map[51][51], int visited[51][51], int R, int C, int L, int cnt);

int result = 0;

int main() {
	int tc, T;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		result = 0;
		int N, M;
		cin >> N >> M;
		int R, C, L;
		cin >> R >> C >> L;
		int map[51][51];
		int visited[51][51] = { 0, };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cin >> map[i][j];
			}
		}
		
		dfs(map, visited, R, C, L, 1);

		
		/*
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					cout << visited[i][j] << " ";
				else
					cout << "  ";
			}
			cout << endl;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					cout << map[i][j] << " ";
				else
					cout << "  ";
			}
			cout << endl;
		}
		*/
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					result++;
			}
		}
		cout << "#" << tc << " " << result << endl;
	}

	return 0;
}

void dfs(int map[51][51], int visited[51][51], int R, int C, int L,int cnt) {
	if (visited[R][C] && cnt>=visited[R][C])
		return;

	if (L < cnt)
		return;
	
	//result++;
	
	visited[R][C] = cnt;

	switch (map[R][C]) {
	case 1:
		if ((map[R][C - 1] == 3 || map[R][C - 1] == 4 || map[R][C - 1] == 5 || map[R][C - 1] == 1)) {
	//		
			dfs(map, visited, R, C - 1, L, cnt + 1);
		}
		if ((map[R][C + 1] == 3 || map[R][C + 1] == 6 || map[R][C + 1] == 7 || map[R][C + 1] == 1)) {
		//	
			dfs(map, visited, R, C + 1, L, cnt + 1);
		}
		if ( (map[R-1][C] == 2 || map[R-1][C] == 5 || map[R-1][C ] == 6 || map[R-1][C] == 1)) {
			//
			dfs(map, visited, R-1, C , L, cnt + 1);
		}
		if ((map[R + 1][C] == 2 || map[R + 1][C] == 4 || map[R + 1][C] == 7 || map[R+1][C] == 1)) {
		//	
			dfs(map, visited, R + 1, C, L, cnt + 1);
		}
		break;
	case 2:
		if ((map[R - 1][C] == 2 || map[R - 1][C] == 5 || map[R - 1][C] == 6 || map[R-1][C] == 1)) {
		//	
			dfs(map, visited, R - 1, C, L, cnt + 1);
		}
		if ((map[R + 1][C] == 2 || map[R + 1][C] == 4 || map[R + 1][C] == 7 || map[R+1][C] == 1)) {
		//	
			dfs(map, visited, R + 1, C, L, cnt + 1);
		}
		break;
	case 3:
		if ((map[R][C - 1] == 3 || map[R][C - 1] == 4 || map[R][C - 1] == 5 || map[R][C - 1] == 1)) {
		//	
			dfs(map, visited, R, C - 1, L, cnt + 1);
		}
		if ((map[R][C + 1] == 3 || map[R][C + 1] == 6 || map[R][C + 1] == 7|| map[R][C + 1] == 1)) {
		//	
			dfs(map, visited, R, C + 1, L, cnt + 1);
		}
		break;
	case 4:
		if ((map[R - 1][C] == 2 || map[R - 1][C] == 5 || map[R - 1][C] == 6 || map[R - 1][C] == 1)) {
		//	
			dfs(map, visited, R - 1, C, L, cnt + 1);
		}
		if ((map[R][C + 1] == 3 || map[R][C + 1] == 6 || map[R][C + 1] == 7 || map[R][C+1] == 1)) {
		//	
			dfs(map, visited, R, C + 1, L, cnt + 1);
		}
		break;
	case 5:
		if ((map[R + 1][C] == 2 || map[R + 1][C] == 4 || map[R + 1][C] == 7 || map[R + 1][C] == 1)) {
		//	
			dfs(map, visited, R + 1, C, L, cnt + 1);
		}
		if ((map[R][C + 1] == 3 || map[R][C + 1] == 6 || map[R][C + 1] == 7 || map[R][C+1] == 1)) {
		//	
			dfs(map, visited, R, C + 1, L, cnt + 1);
		}
		break;
	case 6:
		if ((map[R + 1][C] == 2 || map[R + 1][C] == 4 || map[R + 1][C] == 7 || map[R + 1][C] == 1)) {
		//	
			dfs(map, visited, R + 1, C, L, cnt + 1);
		}
		if ((map[R][C - 1] == 3 || map[R][C - 1] == 4 || map[R][C - 1] == 5 || map[R][C-1] == 1)) {
		//	
			dfs(map, visited, R, C - 1, L, cnt + 1);
		}
		break;
	case 7:
		if ((map[R - 1][C] == 2 || map[R - 1][C] == 5 || map[R - 1][C] == 6 || map[R - 1][C] == 1)) {
		//	
			dfs(map, visited, R - 1, C, L, cnt + 1);
		}
		if ((map[R][C - 1] == 3 || map[R][C - 1] == 4 || map[R][C - 1] == 5 || map[R][C-1] == 1)) {
		//	
			dfs(map, visited, R, C - 1, L, cnt + 1);
		}
		break;
	}
}