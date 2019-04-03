#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int main() {
	int tc, T;
	cin >> T;
	
	for (tc = 1; tc <= T; tc++) {
		deque<int>saw_wheel[5];
		int score = 0;
		int K;
		cin >> K;

		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				int n;
				cin >> n;
				saw_wheel[i].push_back(n);
			}
		}
		for (int i = 0; i < K; i++) {
			int index, rotate;
			cin >> index >> rotate;
			
			vector<pair<int, int>>rotate_wheel;
			rotate_wheel.push_back(make_pair(index, rotate));
			
			if (index == 1) {
				int prev = 1;

				for (int j = prev + 1; j <= 4; j++) {
					if (saw_wheel[prev][2] != saw_wheel[j][6]) {
						rotate *= -1;
						rotate_wheel.push_back(make_pair(j, rotate));
						prev = j;
					}
					else
						break;
				}
			}
			else if (index == 2) {
				int prev = 2;
				if (saw_wheel[prev][6] != saw_wheel[prev-1][2]) {
					rotate_wheel.push_back(make_pair(prev-1, rotate*-1));
				}
				for (int j = prev + 1; j <= 4; j++) {
					if (saw_wheel[prev][2] != saw_wheel[j][6]) {
						rotate *= -1;
						rotate_wheel.push_back(make_pair(j, rotate));
						prev = j;
					}
					else
						break;
				}
			}
			else if (index == 3) {
				int prev = 3;
				if (saw_wheel[prev][2] != saw_wheel[prev+1][6]) {
					rotate_wheel.push_back(make_pair(prev + 1, rotate*-1));
				}
				for (int j = prev - 1; j >= 1; j--) {
					if (saw_wheel[prev][6] != saw_wheel[j][2]) {
						rotate *= -1;
						rotate_wheel.push_back(make_pair(j, rotate));
						prev = j;
					}
					else
						break;
				}
			}
			else if (index == 4) {
				int prev = 4;
				for (int j = prev -1 ; j >= 1; j--) {
					if (saw_wheel[prev][6] != saw_wheel[j][2]) {
						rotate *= -1;
						rotate_wheel.push_back(make_pair(j, rotate));
						prev = j;
					}
					else
						break;
				}
			}
			if (rotate_wheel.size() != 0) {
				for (int j = 0; j < rotate_wheel.size(); j++) {
					int idx = rotate_wheel[j].first;
					int rot = rotate_wheel[j].second;
					if (rot == -1) {
						int num = saw_wheel[idx].front();
						saw_wheel[idx].pop_front();
						saw_wheel[idx].push_back(num);
						
					}
					else if (rot == 1) {
						int num = saw_wheel[idx].back();
						saw_wheel[idx].pop_back();
						saw_wheel[idx].push_front(num);
					}
				}
			}
		}

		int num = 1;
		for (int i = 1; i <= 4; i++) {
			if (saw_wheel[i][0] == 1) {
				score += num;
			}
			num *= 2;
		}
		cout << "#" << tc << " " << score << endl;
	}
	return 0;
}