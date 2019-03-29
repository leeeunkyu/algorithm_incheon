#include <iostream>
#include <vector>

using namespace std;

int main() {
	long long N;
	cin >> N;
	vector<long long>ex_stud;
	for (int i = 0; i < N; i++) {
		long long num;
		cin >> num;
		ex_stud.push_back(num);
	}
	long long B, C;
	cin >> B >> C;

	long long cnt = 0;

	for (int i = 0; i < N; i++) {
		if (ex_stud[i] < B)
			ex_stud[i] = 0;
		else
			ex_stud[i]-=B;
		cnt++;

		if (ex_stud[i] % C > 0) {
			cnt += ex_stud[i] / C;
			cnt++;
		}
		else if (ex_stud[i] % C == 0) {
			cnt += ex_stud[i] / C;
		}
	}
	cout << cnt;
	return 0;
}