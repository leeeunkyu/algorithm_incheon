package algorithm;

import java.util.Stack;

/*
	 * ���� ����
	������ �޼��� ����(score)�� ���� �ǽð� ��ŷ�� �����ִ� ��ŷ �������� �ֽ��ϴ�. ��ŷ �������� �� �������� K���� �г����� �����ָ�, ��ŷ�� �����Ǵ� ��Ģ�� ������ �����ϴ�.
	
	������ ���� ������ ��ŷ�� �� �����ϴ�.
	������ ���ٸ� �ش� ������ ���� �޼��� ������ ��ŷ�� �����ϴ�.
	� ������ ���� ��Ϻ��� �� ���� ������ �޼��ϸ�, ���� ����� ������� ���ο� ����� ��ŷ�� �ݿ��˴ϴ�.
	� ������ ���� ��Ϻ��� �� ���ų� ���� ������ �޼��ߴٸ� �� ����� �����մϴ�.
	������ �ٲ�鼭 ��ŷ�� �ʱ�ȭ�Ǿ� ��ŷ �������� ����ִ� ���°� �ƽ��ϴ�. �̶�, 
	�� ������ �г��Ӱ� ������ �ش� ������ �޼��� ������� �־�����, 1�������� �� �� �ٲ���� �˾ƺ��� �մϴ�. 
	��, ��ŷ ���������� ���� �г��Ӹ� ǥ�õǹǷ� ���� ��ȭ�� �ִ��� ��ŷ�� ��ȭ�� ���ٸ� ��ŷ �������� �ٲ��� �ʽ��ϴ�.
	
	�� �������� �г����� �ö󰡴� ���� K, ������ �г��Ӱ� ������ �޼� ������� ����ִ� �迭 user_scores�� �Ű������� �־��� ��, 
	��ŷ 1�������� �� �� �ٲ���� return �ϵ��� solution �Լ��� �ϼ����ּ���.
	
	���ѻ���
	K�� 1 �̻� 100 ������ �ڿ����Դϴ�.
	user_scores�� ���̴� 1 �̻� 1,000 �����Դϴ�.
	user_scores�� �� ���Ҵ� ���� �г��Ӱ� �ش� ������ �޼��� ������ �̷���� ���ڿ��Դϴ�.
	���� �г��Ӱ� �޼� ������ �г��� ���� ������ ���ڿ��� �־����ϴ�.
	�г��Ӱ� ������ ����(�����̽� ��) �� ���� ���еǾ� �־����ϴ�.
	�г����� ���ĺ� �ҹ��ڿ� ���ڷθ� �̷���� ������, ���̴� 1 �̻� 10 �����Դϴ�.
	������ ���ڷθ� �̷���� ������, ���̴� 1 �̻� 9 �����̰� 0���� �������� �ʽ��ϴ�.
	��� ������ �г��� �ϳ��� ����ϸ�, ���� �ٸ� ������ �г����� ���� ���� �����ϴ�.
	user_scores���� �� ������ �ش� ������ �޼��� ������� ����ֽ��ϴ�.
	���� ���� ������ ���ÿ� �޼� �� ������ ���ٰ� �����ص� �����ϴ�.
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
					//���� ���°� ���� �� ũ�ٸ�
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