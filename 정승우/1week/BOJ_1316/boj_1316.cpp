#include <iostream>
#include <string>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	int n;
	string str;
	int ret = 0;
	cin >> n;
	while (n--) {
		bool c[26] = { 0 };
		bool flag = 0;
		cin >> str;
		c[str[0] - 97] = 1;
		for (int i = 1; i < str.size(); i++) {
			int cur = str[i] - 97;		// 현재 글자
			int pre = str[i - 1] - 97;	// 이전 글자
			if (c[cur] && cur != pre)	flag = 1;	// 앞에 동일한 문자가 나오고 현재글자와 이전글자가 다른경우 flag를 true
			c[cur] = 1;					// 동일한 문자가 나왔으므로 check를 한다.
		}
		if (!flag)						// 해당 문자열이 그룹문자일 경우
			ret++;
	}

	cout << ret;
	return 0;
}
