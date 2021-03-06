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
			//val이 여는거---------------------------------------------------------------------------
			if (val.equals(aType[0]) || val.equals(bType[0]))
				stack.push(c[i]);	
			//val이 닫는거---------------------------------------------------------------------------
			else {
				if (stack.isEmpty()) {
					//닫아야 하는데 여는게 stack에 없다면? -> 잘못된 괄호!
					System.out.println(0);
					return;
				}
				String top = stack.pop(); //top은 정수거나, 여는괄호거나, 닫는괄호거나
				if (!top.equals(aType[0]) && !top.equals(bType[0])) {
					//top이 정수일 경우!
					int tempTop = Integer.parseInt(top);
					while (!stack.isEmpty() && !stack.peek().equals(aType[0]) && !stack.peek().equals(aType[1])
							&& !stack.peek().equals(bType[0]) && !stack.peek().equals(bType[1])) {
						tempTop += Integer.parseInt(stack.pop());
						//정수가 없을때까지 pop 하면서 정수값을 더해준다!
					}
					
					if (stack.isEmpty()) {
						System.out.println(0);
						//다 빼봤는데 stack에 값이 없으면 잘못된괄호 -> 최소한 여는괄호 하나는 있어야 함.
						return;
					}
					
					top = stack.pop();						
					if (val.equals(aType[1])) { //val이 ) 인데 
						if(!top.equals(aType[0])) { //top이 (이게 아니면
							System.out.println(0); //짝이 안맞는다.
							return;
						}
						//짝이맞으면 ( )안에 있다는 소리니깐  *2
						tempTop *= 2;
						
					} else {//val이 ]인데
						if(!top.equals(bType[0])) { //top이 [ 이게 아니면 
							System.out.println(0); //짝이 안맞는다
							return;
						}
						//짝이 맞으면 [ ] 안에 있다는 소리니깐 *3
						tempTop *= 3;
					}
					//구한 값을 push
					stack.push(tempTop+"");
					
				} else {
					//top 이 정수가 아닐경우
					if (top.equals(aType[0])) {
						if(val.equals(aType[1])) { //짝이 맞다면 push
							stack.push("2");
						} else {
							System.out.println(0);
							return;
						}
					} else {
						if(val.equals(bType[1])) { //짝이 맞다면 push
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
