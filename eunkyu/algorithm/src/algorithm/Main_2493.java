package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main_2493 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		
		String a[] = br.readLine().split(" ");
		
		for (int i = 0; i < a.length; i++) {
			stack.add(Integer.parseInt(a[i]));
		}
		
		while (!stack.isEmpty()) {
			int top = stack.pop();
			if(stack.isEmpty()) {
				map.put(top, stack.size());
				while (!temp.isEmpty()) {
					if(top >= temp.peek())
						map.put(temp.pop(), stack.size() + 1);
					else
						map.put(temp.pop(), stack.size());
				}
				break;
			}
			if(top <= stack.peek()) {
				for(int i = temp.size(); i > 0; i--) {
					int tempNum = temp.peek();
					if(tempNum <= stack.peek()) {
						map.put(temp.pop(), stack.size());	
					}else {
						break;
					}
				}
				map.put(top, stack.size());
			}else {
				temp.add(top);
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(map.get(Integer.parseInt(a[i]))+" ");
		}
	}
}
