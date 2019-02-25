#include<iostream> 

using namespace std; 
int N; 
long long dp[31][31];

long long solution(int w,int h){ 
	long long &result = dp[w][h];
	
	if (w == 0 && h == 0)
		return 1;
	
	if (result != 0)
		return result;
	
	if (w != 0) 
		result += solution(w - 1, h + 1);
	
	if (h != 0) 
		result += solution(w, h - 1);
	
	return result;
} 

int main(){ 
	
	while(true){ 
		cin >> N; 
		if(N==0) 
			break; 
		
		cout<< solution(N,0)<<endl;
	} 
	return 0; 
}
