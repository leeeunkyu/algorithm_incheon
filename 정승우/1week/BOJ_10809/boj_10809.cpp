#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	string str;
	cin >> str;

	// 알파벳 갯수만큼 -1로 초기화
	vector<int> v(26, -1);
	for (int i = 0; i < str.size(); i++) {

		// 소문자 a의 ASCII값 = 97
		if (v[str.at(i) - 97] == -1)
			v[str.at(i) - 97] = i;
	}

	for (vector<int>::iterator it = v.begin(); it != v.end(); it++)
		cout << *it << " ";

	return 0;
}