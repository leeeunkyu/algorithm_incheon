#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <queue>
#include <algorithm>
#include <functional>
#include <string.h>
using namespace std;
int map[100000];
int sortmap[100000];
int maxi = 0;

queue <pair <int, int>> q;
int bfs(int f, int t,int ti)
{
	
	int temp = t;
	int tempi = ti;
	int qf=0;
	int cnt=0;
	int qi = 0;
	while (!q.empty())
	{
		int qsize = q.size();
		qi = q.front().first;
		qf = q.front().second;
		
		//printf("%d %d %d\n", qf, qsize, sortmap[maxi]);
		q.pop();
		if (qf <= sortmap[maxi])
		{
			//f++;
			printf("q의값 : %d %d\n", qi, qf);
			q.push(make_pair(qi,qf));
		}

		if (qf == sortmap[maxi])
		{
			
			printf("결과 :%d %d %d\n", qf,cnt,maxi);
			maxi++;
			cnt++;
			if (qf == temp && qi == tempi)
			{
				//printf("결과3 : %d %d \n", temp, tempi);
				printf("%d\n", cnt);
				//q.pop();
				return 0;
			}
		}

		//printf("\n");
	}
	return 0;
}

int main()
{
	int n;
	int a, b;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d %d",&a,&b);
		memset(sortmap, 0,sizeof(sortmap));
		memset(map, 0, sizeof(smap));
		
		for (int j = 0; j < a; j++)
		{
			scanf("%d",&map[j]);
			sortmap[j] = map[j];
			q.push(make_pair(j,map[j]));
			sort(sortmap, sortmap + a, greater<int>());
		}
		maxi = 0;
		bfs(0,map[b],b);
		while (!q.empty())
		{
			q.pop();
		}
	}
	return 0;
}