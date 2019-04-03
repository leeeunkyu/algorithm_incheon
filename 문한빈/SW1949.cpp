#include <iostream>
#include <vector>
using namespace std;
int dx[] = { -1,0,0,1 };
int dy[] = { 0,1,-1,0 };

int result = 0;
void dfs(int x, int y, int map[9][9], bool visited[9][9], bool dig, int cnt);
int N, K;
int main() {
	int tc, T;
	cin >> T;

	for (tc = 1; tc <= T; tc++) {
		
		cin >> N >> K;
		result = 0;
		int map[9][9];
		
		vector<pair<int, int>>top;
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
				if (map[i][j] > max)
					max = map[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (max == map[i][j]) {
					top.push_back(make_pair(j, i));
				}
			}
		}
		for (int i = 0; i < top.size(); i++) {
			int x = top[i].first;
			int y = top[i].second;
			bool visited[9][9] = { 0, };
			int temp[9][9];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[j][k] = map[j][k];
				}
			}
			
			dfs(x, y, temp, visited,false,0);
		}

		cout << "#" << tc << " " << result+1 << endl;
	}
	return 0;
}

void dfs(int x, int y, int map[9][9], bool visited[9][9],bool dig,int cnt) {
	visited[y][x] = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		
		if (nx>=0&& nx<N&&ny>=0&&ny<N) {
			if (!visited[ny][nx] && map[y][x] > map[ny][nx]) {
				dfs(nx, ny, map, visited, dig, cnt + 1);
				visited[ny][nx] = false;
			}
			else if (!visited[ny][nx] && map[y][x] <= map[ny][nx] && !dig) {
				for (int k = 1; k <= K; k++) {
					dig = true;
					map[ny][nx] -= k;
					if(map[ny][nx]<map[y][x]){	
						dfs(nx, ny, map, visited, dig, cnt + 1);
						visited[ny][nx] = false;
					}
					map[ny][nx] += k;
					dig = false;
				}
			}
		}
	}
	if (cnt > result)
		result = cnt;
}