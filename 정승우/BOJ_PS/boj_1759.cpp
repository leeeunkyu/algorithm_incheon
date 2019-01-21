#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
int l, c;
char ch[16];
/*
* 역시 문제를 잘 읽어야 합니다.
* 1. 정렬된 문자열을 선호(정렬필요)
* 2. 최소 한개의 모음과 최소 두개의 자음으로 구성(base case 조건)
* 즉 이 문제는 탐색한 이후 모음 1개이상 자음 2개이상으로 구성된 암호만 출력하면 되며
* 탐색을 하기 전에 정렬을 하고 암호를 만들어 가면 되는 것입니다.
* 자음의 경우를 모두 조건으로 주는 것보다 비교적 수가 적은 모음 조건일때와 아닌경우로 나누었으며
* 모음 조건에 부합하면 모음숫자를 1 증가시켜 재귀적으로 호출하면 되는 문제입니다.
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