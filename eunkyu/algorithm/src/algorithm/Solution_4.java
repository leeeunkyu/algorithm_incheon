package algorithm;

import java.util.Stack;

/*
	 * 문제 설명
	유저가 달성한 점수(score)에 따라 실시간 랭킹을 보여주는 랭킹 페이지가 있습니다. 랭킹 페이지는 한 페이지에 K명씩 닉네임을 보여주며, 랭킹이 산정되는 규칙은 다음과 같습니다.
	
	점수가 높은 유저의 랭킹이 더 높습니다.
	점수가 같다면 해당 점수를 먼저 달성한 유저의 랭킹이 높습니다.
	어떤 유저가 이전 기록보다 더 높은 점수를 달성하면, 이전 기록은 사라지고 새로운 기록이 랭킹에 반영됩니다.
	어떤 유저가 이전 기록보다 더 낮거나 같은 점수를 달성했다면 이 기록은 무시합니다.
	시즌이 바뀌면서 랭킹이 초기화되어 랭킹 페이지가 비어있는 상태가 됐습니다. 이때, 
	각 유저의 닉네임과 점수가 해당 점수를 달성한 순서대로 주어지면, 1페이지는 몇 번 바뀌는지 알아보려 합니다. 
	단, 랭킹 페이지에는 유저 닉네임만 표시되므로 점수 변화가 있더라도 랭킹에 변화가 없다면 랭킹 페이지는 바뀌지 않습니다.
	
	한 페이지에 닉네임이 올라가는 개수 K, 유저의 닉네임과 점수가 달성 순서대로 들어있는 배열 user_scores가 매개변수로 주어질 때, 
	랭킹 1페이지는 몇 번 바뀌는지 return 하도록 solution 함수를 완성해주세요.
	
	제한사항
	K는 1 이상 100 이하인 자연수입니다.
	user_scores의 길이는 1 이상 1,000 이하입니다.
	user_scores의 각 원소는 유저 닉네임과 해당 유저가 달성한 점수로 이루어진 문자열입니다.
	유저 닉네임과 달성 점수는 닉네임 점수 형태의 문자열로 주어집니다.
	닉네임과 점수는 공백(스페이스 바) 한 개로 구분되어 주어집니다.
	닉네임은 알파벳 소문자와 숫자로만 이루어져 있으며, 길이는 1 이상 10 이하입니다.
	점수는 숫자로만 이루어져 있으며, 길이는 1 이상 9 이하이고 0으로 시작하지 않습니다.
	모든 유저는 닉네임 하나만 사용하며, 서로 다른 유저의 닉네임이 같은 경우는 없습니다.
	user_scores에는 각 유저가 해당 점수를 달성한 순서대로 들어있습니다.
	따라서 같은 점수를 동시에 달성 한 유저는 없다고 가정해도 좋습니다.
	k = 3
	["alex111 100", "cheries2 200", "coco 150", "luna 100", "alex111 120", "coco 300", "cheries2 110"]
	res = 4
	 */
public class Solution_4 {
	public static void main(String[] args) {
		int K = 3;
		String [] user_scores = {"alex111 100", "cheries2 200", "coco 150", "luna 100", "alex111 120", "coco 300", "cheries2 110"};
		Solution_4 s = new Solution_4();
		s.solution(K, user_scores);
	}
	
	public int solution(int K, String[] user_scores) {
		int answer = 0;
		Stack<Student> stack = new Stack<Student>();		
		Student[] tempStudent = new Student[K];
		for (int i = 0; i < user_scores.length; i++) {
			String temp[] = user_scores[i].split(" ");
			String name = temp[0];
			int score = Integer.parseInt(temp[1]);
			if(stack.isEmpty()) {
				stack.add(new Student(name, score));
				++answer;
			} else {
				System.out.println("name: "+name+"score: "+score+"  /  "+"name: "+stack.peek().getName()+"score: "+stack.peek().getScore());
				if(score > stack.peek().getScore()) {
					System.out.println("change");
					//새로 들어온게 값이 더 크다면
					int pivot = 0;
					for (int j = 0; j < K && !stack.isEmpty(); j++) {
						tempStudent[j] = stack.peek();
						if(score > tempStudent[j].getScore()) {
							stack.pop();
							
							++answer;
							if(name.equals(tempStudent[j].getName())) {
								--answer;
							}
						} else {
							pivot = j - 1;
							break;
						}
					}						
					stack.add(new Student(name, score));
					for (int l = 0; stack.size() < 3 && l <= pivot; l++) {
						stack.add(tempStudent[l]);
					}
				}
				
			}
			tempStudent = new Student[K];
		}
		System.out.println(answer);
		return answer;
	}
	
}

class Student {
	private String name;
	private int score;
	
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
