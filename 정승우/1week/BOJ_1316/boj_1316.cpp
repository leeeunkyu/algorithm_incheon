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
			int cur = str[i] - 97;		// ���� ����
			int pre = str[i - 1] - 97;	// ���� ����
			if (c[cur] && cur != pre)	flag = 1;	// �տ� ������ ���ڰ� ������ ������ڿ� �������ڰ� �ٸ���� flag�� true
			c[cur] = 1;					// ������ ���ڰ� �������Ƿ� check�� �Ѵ�.
		}
		if (!flag)						// �ش� ���ڿ��� �׷칮���� ���
			ret++;
	}

	cout << ret;
	return 0;
}
