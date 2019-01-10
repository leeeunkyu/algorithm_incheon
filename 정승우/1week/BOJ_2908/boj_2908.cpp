#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int reverse(int num) {
	int a = num / 100;
	int b = (num % 100) / 10;
	int c = num % 10;
	int ret = c * 100 + b * 10 + a;
	return ret;
}
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int a, b;
	cin >> a >> b;
	a = reverse(a);
	b = reverse(b);
	if (a > b)
		cout << a;
	else
		cout << b;
	return 0;
}