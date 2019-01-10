#include <iostream>
#include <string.h>
#include <string>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	string str;
	int ret = 0;
	cin >> str;

	for (int i = 0; i < str.size(); i++) {
		ret++;
		if (str[i] == 'c' && (str[i + 1] == '=' || str[i + 1] == '-'))
			i++;
		else if (str[i] == 'd') {
			if (str[i + 1] == '-')
				i++;
			else if (str[i + 1] == 'z' && str[i + 2] == '=')
				i += 2;
		}
		else if ((str[i] == 'l' || str[i] == 'n') && str[i + 1] == 'j')
			i++;
		else if ((str[i] == 's' || str[i] == 'z') && str[i + 1] == '=')
			i++;
	}

	cout << ret;
	return 0;
}