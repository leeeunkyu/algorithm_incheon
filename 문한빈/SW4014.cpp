#include <iostream>
#include <vector>

using namespace std;

int main() {
	int tc, T;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		int result = 0;
		int N, x;
		cin >> N >> x;
		int map[21][21];
		int visited[21][21] = { 0, };//경사를 겹쳐서 설치하는걸 방지 : 가로
		int visited2[21][21] = { 0, };//경사를 겹쳐서 설치하는걸 방지 : 세로

		int wid[21] = { 0, };
		int hei[21] = { 0, };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
			}
		}
		//가로
		vector<pair<pair<int, int>, int>>slope;//slope에 모든 가로 경사들 추가
		for (int i = 0; i < N; i++) {
			int prev = map[i][0];
			int init = map[i][0];
			for (int j = 1; j < N; j++) {
				if (prev < map[i][j]) {
					slope.push_back(make_pair(make_pair(i, j - 1), 0)); //y좌표,x좌표, 0: 증가하는 경사
					prev = map[i][j];
				}
				else if (prev> map[i][j]) {
					slope.push_back(make_pair(make_pair(i, j), 1));//y좌표,x좌표, 1: 감소하는 경사
					prev = map[i][j];
				}
			}
		}

		vector<int>not_able_row;
		for (int i = 0; i < slope.size(); i++) {//각 행마다 경사로 설치가능한지 확인
			bool row_able = true;
			int a = slope[i].first.first;//y좌표
			int b = slope[i].first.second;//x좌표

			if (slope[i].second == 0) {//증가하는 경사일 때
				if (map[a][b + 1] > map[a][b] + 1)//높이차이가 1인지 확인
					row_able = false;
				if (b - x + 1 < 0) {//경사 설치 범위가 map을 벗어나는지 확인
					row_able = false;
				}
				int cnt = 0;
				for (int j = b; j > b - x; j--) {//경사길이가 x만큼인지 확인
					if (map[a][b] == map[a][j] && !visited[a][j]) {//경사를 겹쳐서 설치하는걸 방지
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//불가능한 경사들은 not_able_row에 저장
					not_able_row.push_back(a);
				}
				else {
					for (int j = b; j > b - x; j--) {
						visited[a][j] = 1;
					}
				}
			}
			else if (slope[i].second == 1) {//감소하는 경사일 때
				if (map[a][b - 1] > map[a][b] + 1)//높이차이가 1인지 확인
					row_able = false;
				if (b + x - 1 > N - 1) {//경사 설치 범위가 map을 벗어나는지 확인
					row_able = false;
				}
				int cnt = 0;
				for (int j = b; j < b + x; j++) {//경사길이가 x만큼인지 확인
					if (map[a][b] == map[a][j] && !visited[a][j]) {
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//불가능한 경사들은 not_able_row에 저장
					not_able_row.push_back(a);
				}
				else {
					for (int j = b; j < b + x; j++) {
						visited[a][j] = 2;
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {//모두 1로 초기화
			wid[i] = 1;
		}
		for (int i = 0; i < not_able_row.size(); i++) {//불가능한 행들을 모두 0으로 처리
			wid[not_able_row[i]] = 0;
		}
		for (int i = 0; i < N; i++) {//가능한 행들을 찾아 result++
			if (wid[i] == 1)
				result++;
		}

		//세로
		vector<pair<pair<int, int>, int>>slope2;//slope2에 모든 세로 경사들 추가
		for (int j = 0; j < N; j++) {
			int prev = map[0][j];
			int init = map[0][j];
			for (int i = 1; i < N; i++) {
				if (prev < map[i][j]) {
					slope2.push_back(make_pair(make_pair(i - 1, j), 0));//y좌표,x좌표, 0: 증가하는 경사
					prev = map[i][j];
				}
				else if (prev > map[i][j]) {
					slope2.push_back(make_pair(make_pair(i, j), 1));//y좌표,x좌표, 1: 감소하는 경사
					prev = map[i][j];
				}
			}
		}

		vector<int>not_able_row2;
		for (int i = 0; i < slope2.size(); i++) {//각 열마다 경사로 설치가능한지 확인
			bool row_able = true;
			int a = slope2[i].first.first;//y좌표
			int b = slope2[i].first.second;//x좌표

			if (slope2[i].second == 0) {//증가하는 경사일 때
				if (map[a][b] + 1 < map[a + 1][b])//높이차이가 1인지 확인
					row_able = false;
				if (a - x + 1 < 0) {//경사 설치 범위가 map을 벗어나는지 확인
					row_able = false;
				}
				int cnt = 0;
				for (int j = a; j > a - x; j--) {//경사길이가 x만큼인지 확인
					if (map[a][b] == map[j][b] && !visited2[j][b]) {//경사를 겹쳐서 설치하는걸 방지
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//불가능한 경사들은 not_able_row2에 저장
					not_able_row2.push_back(b);
				}
				else {
					for (int j = a; j > a - x; j--) {//경사길이가 x만큼인지 확인
						visited2[j][b] = 1;//경사를 겹쳐서 설치하는걸 방지
					}
				}
			}
			else if (slope2[i].second == 1) {
				if (map[a - 1][b]> map[a][b] + 1)//높이차이가 1인지 확인
					row_able = false;
				if (a + x - 1 > N - 1) {//경사 설치 범위가 map을 벗어나는지 확인
					row_able = false;
				}
				int cnt = 0;
				for (int j = a; j < a + x; j++) {//경사길이가 x만큼인지 확인
					if (map[a][b] == map[j][b] && !visited2[j][b]) {//경사를 겹쳐서 설치하는걸 방지
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//불가능한 경사들은 not_able_row2에 저장
					not_able_row2.push_back(b);
				}
				else {
					for (int j = a; j < a + x; j++) {
						visited2[j][b] = 2;//경사를 겹쳐서 설치하는걸 방지
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			hei[i] = 1;
		}
		for (int i = 0; i < not_able_row2.size(); i++) {//불가능한 열들을 모두 0처리
			hei[not_able_row2[i]] = 0;
		}
		for (int i = 0; i < N; i++) {//가능한 열들을 찾아 result++
			if (hei[i] == 1)
				result++;
		}
		cout << "#" << tc << " " << result << endl;
	}
	return 0;
}