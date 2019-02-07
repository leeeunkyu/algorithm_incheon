package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * () = 2
 * [] = 3
 * ([]) = 2 * 3
 * ()[] = 2 + 3
 */
public class Main_2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(System.in));
		String c[] = br.readLine().split("");
//		for (int i = 0; i < c.length; i++) {
//			System.out.println(c[i]);
//		}
		check(c);
	}
	//char를 int로 바꿀려면 - '0' 을
	//int를 char로 바꿀려면 (char) + '0'을
	
	private static void check(String[] c) {
		String aType[] = {"(", ")"};
		String bType[] = {"[", "]"};
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < c.length; i++) {
			String val = c[i]+"";
			//여는거
			if (val.equals(aType[0]) || val.equals(bType[0]))
				stack.push(c[i]);	
			//닫는거
			else {
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				}
				String top = stack.pop();
				if (!top.equals(aType[0]) && !top.equals(bType[0])) {
					//정수라는 뜻
					int tempTop = Integer.parseInt(top);
					
					while (!stack.isEmpty() && !stack.peek().equals(aType[0]) && !stack.peek().equals(aType[1])
							&& !stack.peek().equals(bType[0]) && !stack.peek().equals(bType[1])) {
						
						tempTop += Integer.parseInt(stack.pop());
					}
					if (stack.isEmpty()) {
						System.out.println(0);
						return;
					}
					top = stack.pop();						
					if (val.equals(aType[1])) {
						if(!top.equals(aType[0])) {
							System.out.println(0);
							return;
						}
						tempTop *= 2;
						
					} else {
						if(!top.equals(bType[0])) {
							System.out.println(0);
							return;
						}
						tempTop *= 3;
					}
					stack.push(tempTop+"");

				} else {
					if (top.equals(aType[0])) {
						if(val.equals(aType[1])) {
							stack.push("2");
						} else {
							System.out.println(0);
							return;
						}
					} else {
						if(val.equals(bType[1])) {
							stack.push("3");	
						} else {
							System.out.println(0);
							return;
						}
					}
				}
			}
		}
		int sum = 0;
		while (!stack.isEmpty()) {
			String val = stack.pop();
			if(val.equals(aType[0]) || val.equals(aType[1]) || 
					val.equals(bType[0]) || val.equals(bType[1])) {
				System.out.println(0);
				return;
			}
			sum += Integer.parseInt(val);
			
		}
		
		System.out.println(sum);
		
	}
	
}
