#include <iostream>
#include <string>
#include <stack>
using namespace std;
string line;
stack<char> s;
int main() {
	cin >> line;
	int tmp = 1;
	long res = 0;
	bool isImposs = false;
	for (int i = 0; i < line.size(); i++) {
		if (line[i] == '(') {
			s.push(line[i]);
			tmp *= 2;
		}
		else if (line[i] == '[') {
			s.push(line[i]);
			tmp *= 3;
		}
		else if (line[i] == ')' && (s.empty() || s.top() != '(')) {
			isImposs = true;
			break;
		}
		else if (line[i] == ']' && (s.empty() || s.top() != '[')) {
			isImposs = true;
			break;
		}
		else if (line[i] == ')') {
			if (line[i - 1] == '(') {
				res += tmp;
				
			}
			s.pop();
			tmp /= 2;
			
		}
		else if (line[i] == ']') {
			
			if (line[i - 1] == '[') {
				res += tmp;
				
			}
			s.pop();
			tmp /= 3;
			
		}
		
	}
	if (!s.empty() || isImposs) {
		cout << 0 << endl;
	}else 
		cout << res << endl;

}
