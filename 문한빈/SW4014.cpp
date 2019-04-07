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
		int visited[21][21] = { 0, };//��縦 ���ļ� ��ġ�ϴ°� ���� : ����
		int visited2[21][21] = { 0, };//��縦 ���ļ� ��ġ�ϴ°� ���� : ����

		int wid[21] = { 0, };
		int hei[21] = { 0, };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
			}
		}
		//����
		vector<pair<pair<int, int>, int>>slope;//slope�� ��� ���� ���� �߰�
		for (int i = 0; i < N; i++) {
			int prev = map[i][0];
			int init = map[i][0];
			for (int j = 1; j < N; j++) {
				if (prev < map[i][j]) {
					slope.push_back(make_pair(make_pair(i, j - 1), 0)); //y��ǥ,x��ǥ, 0: �����ϴ� ���
					prev = map[i][j];
				}
				else if (prev> map[i][j]) {
					slope.push_back(make_pair(make_pair(i, j), 1));//y��ǥ,x��ǥ, 1: �����ϴ� ���
					prev = map[i][j];
				}
			}
		}

		vector<int>not_able_row;
		for (int i = 0; i < slope.size(); i++) {//�� �ึ�� ���� ��ġ�������� Ȯ��
			bool row_able = true;
			int a = slope[i].first.first;//y��ǥ
			int b = slope[i].first.second;//x��ǥ

			if (slope[i].second == 0) {//�����ϴ� ����� ��
				if (map[a][b + 1] > map[a][b] + 1)//�������̰� 1���� Ȯ��
					row_able = false;
				if (b - x + 1 < 0) {//��� ��ġ ������ map�� ������� Ȯ��
					row_able = false;
				}
				int cnt = 0;
				for (int j = b; j > b - x; j--) {//�����̰� x��ŭ���� Ȯ��
					if (map[a][b] == map[a][j] && !visited[a][j]) {//��縦 ���ļ� ��ġ�ϴ°� ����
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//�Ұ����� ������ not_able_row�� ����
					not_able_row.push_back(a);
				}
				else {
					for (int j = b; j > b - x; j--) {
						visited[a][j] = 1;
					}
				}
			}
			else if (slope[i].second == 1) {//�����ϴ� ����� ��
				if (map[a][b - 1] > map[a][b] + 1)//�������̰� 1���� Ȯ��
					row_able = false;
				if (b + x - 1 > N - 1) {//��� ��ġ ������ map�� ������� Ȯ��
					row_able = false;
				}
				int cnt = 0;
				for (int j = b; j < b + x; j++) {//�����̰� x��ŭ���� Ȯ��
					if (map[a][b] == map[a][j] && !visited[a][j]) {
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//�Ұ����� ������ not_able_row�� ����
					not_able_row.push_back(a);
				}
				else {
					for (int j = b; j < b + x; j++) {
						visited[a][j] = 2;
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {//��� 1�� �ʱ�ȭ
			wid[i] = 1;
		}
		for (int i = 0; i < not_able_row.size(); i++) {//�Ұ����� ����� ��� 0���� ó��
			wid[not_able_row[i]] = 0;
		}
		for (int i = 0; i < N; i++) {//������ ����� ã�� result++
			if (wid[i] == 1)
				result++;
		}

		//����
		vector<pair<pair<int, int>, int>>slope2;//slope2�� ��� ���� ���� �߰�
		for (int j = 0; j < N; j++) {
			int prev = map[0][j];
			int init = map[0][j];
			for (int i = 1; i < N; i++) {
				if (prev < map[i][j]) {
					slope2.push_back(make_pair(make_pair(i - 1, j), 0));//y��ǥ,x��ǥ, 0: �����ϴ� ���
					prev = map[i][j];
				}
				else if (prev > map[i][j]) {
					slope2.push_back(make_pair(make_pair(i, j), 1));//y��ǥ,x��ǥ, 1: �����ϴ� ���
					prev = map[i][j];
				}
			}
		}

		vector<int>not_able_row2;
		for (int i = 0; i < slope2.size(); i++) {//�� ������ ���� ��ġ�������� Ȯ��
			bool row_able = true;
			int a = slope2[i].first.first;//y��ǥ
			int b = slope2[i].first.second;//x��ǥ

			if (slope2[i].second == 0) {//�����ϴ� ����� ��
				if (map[a][b] + 1 < map[a + 1][b])//�������̰� 1���� Ȯ��
					row_able = false;
				if (a - x + 1 < 0) {//��� ��ġ ������ map�� ������� Ȯ��
					row_able = false;
				}
				int cnt = 0;
				for (int j = a; j > a - x; j--) {//�����̰� x��ŭ���� Ȯ��
					if (map[a][b] == map[j][b] && !visited2[j][b]) {//��縦 ���ļ� ��ġ�ϴ°� ����
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//�Ұ����� ������ not_able_row2�� ����
					not_able_row2.push_back(b);
				}
				else {
					for (int j = a; j > a - x; j--) {//�����̰� x��ŭ���� Ȯ��
						visited2[j][b] = 1;//��縦 ���ļ� ��ġ�ϴ°� ����
					}
				}
			}
			else if (slope2[i].second == 1) {
				if (map[a - 1][b]> map[a][b] + 1)//�������̰� 1���� Ȯ��
					row_able = false;
				if (a + x - 1 > N - 1) {//��� ��ġ ������ map�� ������� Ȯ��
					row_able = false;
				}
				int cnt = 0;
				for (int j = a; j < a + x; j++) {//�����̰� x��ŭ���� Ȯ��
					if (map[a][b] == map[j][b] && !visited2[j][b]) {//��縦 ���ļ� ��ġ�ϴ°� ����
						cnt++;
					}
					else
						break;
				}
				if (cnt<x)
					row_able = false;
				if (!row_able) {//�Ұ����� ������ not_able_row2�� ����
					not_able_row2.push_back(b);
				}
				else {
					for (int j = a; j < a + x; j++) {
						visited2[j][b] = 2;//��縦 ���ļ� ��ġ�ϴ°� ����
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			hei[i] = 1;
		}
		for (int i = 0; i < not_able_row2.size(); i++) {//�Ұ����� ������ ��� 0ó��
			hei[not_able_row2[i]] = 0;
		}
		for (int i = 0; i < N; i++) {//������ ������ ã�� result++
			if (hei[i] == 1)
				result++;
		}
		cout << "#" << tc << " " << result << endl;
	}
	return 0;
}