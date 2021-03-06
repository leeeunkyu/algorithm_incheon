package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;
//미생물
class Node {
    int x, y, d, p;
 
    public Node(int x, int y, int d, int p) {
        super();
        this.x = x;
        this.y = y;
        this.d = d;
        this.p = p;
    }
}
 
class Dist {
    int num1, num2;
    float dist;
 
    public Dist(int num1, int num2, float dist) {
        super();
        this.num1 = num1;
        this.num2 = num2;
        this.dist = dist;
    }
 
}
 
class Solution_5648_copy {
 
    static int N, Answer;
    static Vector<Node> list;
    static PriorityQueue<Dist> path;
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
 
    public static int clossCheck(int dir) {
        if (dir == 0)
            return 1;
        else if (dir == 1)
            return 0;
        else if (dir == 2)
            return 3;
        else
            return 2;
    }
 
    public static void Mapping() {
        for (int i = 0; i < list.size() - 1; i++) {
        	int x1 = list.get(i).x;
            int y1 = list.get(i).y;
            int d1 = list.get(i).d;
            for (int j = i + 1; j < list.size(); j++) {
            	//원자들의 이동 방향은 상(0), 하(1), 좌(2), 우(3)로 주어진다.
                int x2 = list.get(j).x;
                int y2 = list.get(j).y;
                int d2 = list.get(j).d;
 
                float XL = Math.abs(x2 - x1);
                float YL = Math.abs(y2 - y1);
 
                if (d1 == d2)
                    continue;
 
                //만날 가능성이없는애들
                if ((d1 == 0 && y2 <= y1) || (d1 == 1 && y2 >= y1) || (d1 == 2 && x1 <= x2) || (d1 == 3 && x1 >= x2)) {
                    continue;
                }
 
                if ((d1 == 0 && d2 == 1 && x1 != x2) || (d1 == 1 && d2 == 0 && x1 != x2)
                        || (d1 == 2 && d2 == 3 && y1 != y2) || (d1 == 3 && d2 == 2 && y1 != y2)) {
                    continue;
                }

                //만날 가능 성이있는애들
                if (d1 == 0 || d1 == 1) {
                    if ((d2 == 2 && x1 > x2) || (d2 == 3 && x1 < x2))
                        continue;
                    if (d2 == 2 || d2 == 3) {
                        if (XL == YL)
                            path.offer(new Dist(i, j, XL));
                    } else {
                        path.offer(new Dist(i, j, (float) YL / 2));
                    }
                } else {
                    if ((d2 == 0 && y1 < y2) || (d2 == 1 && y1 > y2))
                        continue;
                    if (d2 == 0 || d2 == 1) {
                        if (XL == YL)
                            path.offer(new Dist(i, j, XL));
                    } else
                        path.offer(new Dist(i, j, (float) XL / 2));
                }
            }
        }
    }
 
    public static void Process() {
        float Visited[] = new float[N];
 
        while (!path.isEmpty()) {
            int n1 = path.peek().num1;
            int n2 = path.peek().num2;
            float d = path.peek().dist;
            path.poll();
 
            if (Visited[n1] == 0 && Visited[n2] == 0) {
                Answer += list.get(n1).p + list.get(n2).p;
                Visited[n1] = d;
                Visited[n2] = d;
            } else if (Visited[n1] == 0 && Visited[n2] != 0) {
                if (Visited[n2] == d) {
                    Answer += list.get(n1).p;
                    Visited[n1] = d;
                }
            } else if (Visited[n1] != 0 && Visited[n2] == 0) {
                if (Visited[n1] == d) {
                    Answer += list.get(n2).p;
                    Visited[n2] = d;
                }
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int testcase = 0; testcase < T; testcase++) {
            N = Integer.parseInt(br.readLine());
            Answer = 0;
            list = new Vector<Node>();
            path = new PriorityQueue<Dist>(new Comparator<Dist>() {
                @Override
                public int compare(Dist d1, Dist d2) {
                    // TODO Auto-generated method stub
                    return d2.dist > d1.dist ? -1 : 1;
                }
            });
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                while (st.hasMoreTokens()) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    int d = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    list.add(new Node(x, y, d, p));
                }
            }
 
            Mapping();
            Process();
            System.out.println("#" + (testcase + 1) + " " + Answer);
        }
    }
}