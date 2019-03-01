#include <iostream>
#include <vector>

using namespace std;

struct bc {
	int x;
	int y;
	int c;
	int p;
};
struct user {
	int x;
	int y;
	int charge;
};

//이동방향
int dx[] = { 0,0,1,0,-1 };
int dy[] = { 0,-1,0,1,0 };

int cal_dist(bc b, user u) {
	return abs(b.x - u.x) + abs(b.y - u.y);
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		vector<bc> Charger;
		user charge_a = { 1,1,0 }, charge_b = {10,10,0};
		int dir_A[101] = { 0, };
		int dir_B[101] = { 0, };
		int M, BC;

		cin >> M >> BC;
		
		for (int i = 0; i < M; i++) {//M초
			cin >> dir_A[i];//A이동정보
		}

		for (int i = 0; i < M; i++) {
			cin >> dir_B[i];//B이동정보
		}

		for (int i = 0; i < BC; i++) {//BC정보
			int x, y, c, p;
			cin >> x >> y >> c >> p;
			bc temp = { x,y,c,p };
			Charger.push_back(temp);
		}
		//vector<int>testa;
		//vector<int>testb;
		//int s=0;
		//vector<int>ss;
		for (int i = 0; i <= M; i++) {
			int max_a[9][2] = {0,};// j번째, Power
			int max_b[9][2] = {0,};
			int cnt_a = 0, cnt_b = 0;

			for (int j = 0; j < BC; j++) {//BC갯수만큼
				if (cal_dist(Charger[j], charge_a) <= Charger[j].c) {//충전가능한 BC인지 확인
					max_a[cnt_a][0] = j;//해당 BC의 인덱스 저장
					max_a[cnt_a++][1] = Charger[j].p;//해당 BC의 power 저장
					
					//max_a.push_back(make_pair(j, Charger[j].p));
				}
				if (cal_dist(Charger[j], charge_b) <= Charger[j].c) {
					max_b[cnt_b][0] = j;
					max_b[cnt_b++][1] = Charger[j].p;
					//max_b.push_back(make_pair(j, Charger[j].p));
				}
			}
			
			int sum_ab = 0,idx_a=0,idx_b=0;
			bool same = false;
			
			if (cnt_b == 0 && cnt_a!=0) {
				for (int k = 0; k < cnt_a; k++) {
					if (sum_ab < (max_a[k][1])) {
						sum_ab = max_a[k][1];
						idx_a = k;
						same = false;
					}
				}
			}
			else if (cnt_a == 0 && cnt_b!=0) {
				for (int k = 0; k < cnt_b; k++) {
					if (sum_ab < (max_b[k][1])) {
						sum_ab = max_b[k][1];
						idx_b = k;
						same = false;
					}
				}
			}
			else {
				for (int k = 0; k < cnt_a; k++) {
					for (int m = 0; m < cnt_b; m++) {
						if (max_a[k][0] == max_b[m][0]) {
							if (sum_ab < (max_a[k][1] + max_b[m][1]) / 2) {
								sum_ab = max_a[k][1];
								idx_a = k;
								idx_b = m;
								same = true;
							}
						}
						else {
							if (sum_ab < (max_a[k][1] + max_b[m][1])) {
								sum_ab = max_a[k][1] + max_b[m][1];
								idx_a = k;
								idx_b = m;
								same = false;
							}
						}
					}
				}
			}
			if (same) {
				charge_a.charge += max_a[idx_a][1]/2;
				//testa.push_back(max_a[idx_a][1]/2);
				
				charge_b.charge += max_b[idx_b][1]/2;
				//testb.push_back(max_b[idx_b][1]/2);
				//s+=max_a[idx_a][1];
			}
			else {
				charge_a.charge += max_a[idx_a][1];
				//testa.push_back(max_a[idx_a][1]);
				charge_b.charge += max_b[idx_b][1];
				//testb.push_back(max_b[idx_b][1]);
				//s+=(max_a[idx_a][1]+ max_b[idx_b][1]);
			}
			//ss.push_back(s);
			charge_a.x += dx[dir_A[i]];
			charge_a.y += dy[dir_A[i]];
			charge_b.x += dx[dir_B[i]];
			charge_b.y += dy[dir_B[i]];
		}
		int sum = charge_a.charge + charge_b.charge;
		cout << "#" << tc << " " << sum << endl;
	}
	return 0;
}