#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
int l, c;
char ch[16];
/*
* ���� ������ �� �о�� �մϴ�.
* 1. ���ĵ� ���ڿ��� ��ȣ(�����ʿ�)
* 2. �ּ� �Ѱ��� ������ �ּ� �ΰ��� �������� ����(base case ����)
* �� �� ������ Ž���� ���� ���� 1���̻� ���� 2���̻����� ������ ��ȣ�� ����ϸ� �Ǹ�
* Ž���� �ϱ� ���� ������ �ϰ� ��ȣ�� ����� ���� �Ǵ� ���Դϴ�.
* ������ ��츦 ��� �������� �ִ� �ͺ��� ���� ���� ���� ���� �����϶��� �ƴѰ��� ����������
* ���� ���ǿ� �����ϸ� �������ڸ� 1 �������� ��������� ȣ���ϸ� �Ǵ� �����Դϴ�.
*/
void solve(int cur, string password, int consonant, int vowel) {
	if (password.size() == l&& vowel >= 1 && consonant >= 2) {
		cout << password << endl;
		return;
	}
	for (int i = cur; i <= c; i++) {
		if (ch[i] == 'a' || ch[i] == 'e' || ch[i] == 'i' || ch[i] == 'o' || ch[i] == 'u')
			solve(i + 1, password + ch[i], consonant, vowel + 1);
		else
			solve(i + 1, password + ch[i], consonant + 1, vowel);
	}
}

int main() {
	ios_base::sync_with_stdio(false); cin.tie(0); cout.tie(0);
	cin >> l >> c;

	char input;
	for (int i = 0; i < c; i++) cin >> ch[i];
	sort(ch, ch+c+1);
	solve(1, "", 0, 0);
	return 0;
}