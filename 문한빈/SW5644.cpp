#include <iostream>
#include <vector>

using namespace std;

int dx[] = { 0,0,1,0,-1 };
int dy[] = { 0,-1,0,1,0 };


struct BC {
	int x;
	int y;
	int cover;
	int perform;
};
struct user {
	int x;
	int y;
	int charge;
};
int cal_distance(user a, BC bc);
int main() {
	int tc, T;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		int result = 0;
		int charge_a[102] = { 0, };
		int charge_b[102] = { 0, };
		vector<BC>BC;
		int M, A;
		cin >> M >> A;
		int a_move[101] = {0,};
		int b_move[101] = {0,};
		
		for (int i = 0; i < M; i++) {
			cin >> a_move[i];
		}
		for (int i = 0; i < M; i++) {
			cin >> b_move[i];
		}
		for (int i = 0; i < A; i++) {
			int x, y, c, p;
			cin >> x >> y >> c >> p;
			BC.push_back({x, y, c, p});
		}
		user user_a = { 1,1,0 }, user_b = { 10,10,0 };
		
		for (int i = 0; i <= M; i++) {
			vector<int>a_bc_index;
			vector<int>b_bc_index;
			for (int j = 0; j < BC.size(); j++) {
				if ((cal_distance(user_a, BC[j])<=BC[j].cover)) {
					a_bc_index.push_back(j);
				}
				if ((cal_distance(user_b, BC[j]) <= BC[j].cover)) {
					b_bc_index.push_back(j);
				}
			}
			int max = 0;
			int a_i, b_i;
			if (a_bc_index.size() != 0 && b_bc_index.size() != 0) {
				for (int j = 0; j < a_bc_index.size(); j++) {
					for (int k = 0; k < b_bc_index.size(); k++) {
						if (a_bc_index[j] == b_bc_index[k] && max < BC[a_bc_index[j]].perform) {
							max = BC[a_bc_index[j]].perform;
							a_i = b_i = a_bc_index[j];
						}
						if (max < BC[a_bc_index[j]].perform + BC[b_bc_index[k]].perform && a_bc_index[j] != b_bc_index[k]) {
							max = BC[a_bc_index[j]].perform + BC[b_bc_index[k]].perform;
							a_i = a_bc_index[j];
							b_i = b_bc_index[k];
						}
					}
				}
				if (a_i == b_i) {
					user_a.charge += BC[a_i].perform / 2;
					//charge_a[i] = BC[a_i].perform / 2;
					user_b.charge += BC[b_i].perform / 2;
					//charge_b[i] = BC[b_i].perform / 2;
				}
				else {
					user_a.charge += BC[a_i].perform;
					//charge_a[i] = BC[a_i].perform;
					user_b.charge += BC[b_i].perform;
					//charge_b[i] = BC[b_i].perform;
				}
			}
			else if (a_bc_index.size() != 0 && b_bc_index.size() == 0) {
				for (int j = 0; j < a_bc_index.size(); j++) {
					if (max < BC[a_bc_index[j]].perform) {
						max = BC[a_bc_index[j]].perform;
						a_i = a_bc_index[j];
					}
				}
				user_a.charge += BC[a_i].perform;
				charge_a[i] = BC[a_i].perform;
			}
			else if (a_bc_index.size() == 0 && b_bc_index.size() != 0) {
				for (int j = 0; j < b_bc_index.size(); j++) {
					if (max < BC[b_bc_index[j]].perform) {
						max = BC[b_bc_index[j]].perform;
						b_i = b_bc_index[j];
					}
				}
				user_b.charge += BC[b_i].perform;
				charge_b[i] = BC[b_i].perform;
			}
			
			user_a.x += dx[a_move[i]];
			user_a.y += dy[a_move[i]];
			user_b.x += dx[b_move[i]];
			user_b.y += dy[b_move[i]];
		}
		
		cout << "#" << tc << " " << user_a.charge+user_b.charge << endl;
	}

	return 0;
}
int cal_distance(user a, BC bc) {
	return abs((a.x - bc.x)) + abs((a.y - bc.y));
}