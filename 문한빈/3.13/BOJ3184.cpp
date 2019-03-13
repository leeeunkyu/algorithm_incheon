#include <iostream>
#include <vector>

using namespace std;

char map[251][251];
int visited[251][251] = { 0, };
int R, C;
int dx[] = { -1,0,1,0 };
int dy[] = { 0,1,0,-1 };
int sheep[63001] = { 0, };
int wolf[63001] = { 0, };
void bfs(int x, int y, int num);

int main() {
	cin >> R >> C;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> map[i][j];
		}
	}
	int num = 1;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if(!visited[i][j])
				bfs(j, i,num++);
		}
	}
	int s = 0, w = 0l;
	for (int i = 0; i < num; i++) {
		if (sheep[i] > wolf[i])
			s += sheep[i];
		else
			w += wolf[i];
	}

	cout << s << " " << w << endl;

	return 0;
}

void bfs(int x, int y,int num) {
	if (map[y][x] != '#' && !visited[y][x]) {
		visited[y][x] = true;

		if (map[y][x] == 'o')
			sheep[num]++;
		else if (map[y][x] == 'v')
			wolf[num]++;
		/*else
			map[y][x] = num;*/
	}
	else if (map[y][x] == '#')
		return;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		
		if (nx >= 0 && nx < C&& ny>=0 && ny < R&& !visited[ny][nx]) {
			bfs(nx, ny,num);
		}
	}
	return;
}