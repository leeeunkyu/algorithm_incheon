#include <iostream>
#include <string>
#include <deque>
#include <vector>
#include <algorithm>
using namespace std;
bool desc(string a, string b) {
	return a > b;
}
int calc_16(string num) {
	int digit = 1;
	int sum =0;
	int size = num.size();

	for (int i = size-1; i >=0 ; i--) {
		if (num[i] >= '0' &&num[i] <= '9') {
			sum += digit * (num[i]-'0');
		}
		else if (num[i] >= 'A' &&num[i] <= 'F') {
			sum += digit * (num[i] - 'A'+10);
		}
		digit *= 16;
	}
	return sum;
}
int main() {
	int T, tc;
	cin >> T;
	for (tc = 1; tc <= T; tc++) {
		int result = 0;
		
		int N, K;
		cin >> N >> K;
		
		deque<char> text_case;
		vector<string> candidate;
		getchar();
		string temp;
		getline(cin, temp);
		
		int char_cnt = N / 4;//3
		
		for (int i = 0; i < N; i++) {
			text_case.push_back(char(temp[i]));
		}
		
		for (int i = 0; i < char_cnt; i++) {
			for (int j = 0; j < N; j+=char_cnt) {
				string temp="";
				for (int k = j; k < char_cnt+j; k++) {
					temp+=text_case[k];
				}
				candidate.push_back(temp);
			}
			char temp = text_case.back();
			text_case.pop_back();
			text_case.push_front(temp);
		}
		int cnt = 0;
		sort(candidate.begin(), candidate.end(),desc);
		
		for (int i = 0; i < K; i++) {
			for (int j = i + 1;j<K; j++) {
				if (candidate[i] == candidate[j]) {
					cnt++;
				}
			}
		}
		K += cnt;
		
		cout << "#" << tc << " " << calc_16(candidate[K - 1]) << endl;
	}

	return 0;
}