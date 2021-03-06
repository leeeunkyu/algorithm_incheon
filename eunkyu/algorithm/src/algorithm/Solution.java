package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
   public static void main(String args[]) {
      int N = 3;
      int spot[] = new int[N];
      
      int[] ct = {4,2,2,5,3};
      
      Solution solution = new Solution();
      solution.solution(N, ct);
      
      
   }

   public int[] solution(int n, int[] coffee_times) {
	   int[] answer = new int [coffee_times.length];
	   Queue<Integer> ans = makeCoffee(n, coffee_times);
	   int i = 0;
	   while (!ans.isEmpty()) {
		   answer[i++] = ans.poll();
		   System.out.println(answer[i - 1]);
	   }
	   return answer;
   }
   
   Queue<Integer> makeCoffee(int n , int[] coffee_times) {
	  Queue<Integer> ans = new LinkedList<Integer>();
	  Queue<Coffee> q = new LinkedList<Coffee>();
	  int i;
      for (i = 1 ; i <= n ; i++) {
         q.add(new Coffee(i, coffee_times[i-1]));
      }
      
      Coffee coffee[] = new Coffee[n];
      while (!q.isEmpty()) {
         for (int j = 0 ; j < n; j++ ) {
        	 if(q.peek() == null)
        		 continue;
        	 coffee[j] = q.poll();
        	 
            int sec = coffee[j].getMakeTime();
            
            if (sec -1 == 0) {
               if (i <= coffee_times.length) {
                  q.add(new Coffee(i, coffee_times[i-1]));
                  i++;
               }
               ans.add(coffee[j].num);
            } else {
               q.add(new Coffee(coffee[j].num, coffee[j].getMakeTime()-1));
            }
            
         }    
      }
      return ans;
      
   }
   

	class Coffee{
		
	int num;
	int makeTime;

	public Coffee(int num, int makeTime) {
		super();
		this.num = num;
		this.makeTime = makeTime;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(int makeTime) {
		this.makeTime = makeTime;
	}
      
      
   }
}