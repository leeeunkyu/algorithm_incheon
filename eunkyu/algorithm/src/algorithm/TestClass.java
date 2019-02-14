package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class TestClass {
    static int n,m,k;
    static int[][] arr,copy;
    static List<ArrayList<ArrayList<Pair>>> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // n*n 배열
        m = sc.nextInt(); // 나무 정보 수
        k = sc.nextInt(); // K년이 지난후.
        
        arr = new int[n][n];
        copy = new int[n][n]; // 추가 되어야할 양분
        // n개의 arrayList 생성
        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
            for(int j=0;j<n;j++)
                list.get(i).add(new ArrayList<>());
        }
        // 초기 양분값
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                copy[i][j] = sc.nextInt();
                arr[i][j] = 5; // 초기 양분은 5
            }
        }
        // m개의 줄에 상도가 심은 나무정보수 (x,y), 나무나이
        for(int i=0;i<m;i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int age = sc.nextInt();
            list.get(x-1).get(y-1).add(new Pair(x-1, y-1, 1, age));
        }
        
        // K년 동안
        for(int i=0;i<k;i++) {
            // 1년은 4계절
            solveSpring();
            solveSummer();
            solveFall();
            solveWinter();
        }
        // k년이 흐른 후 살아있는 나무 조사
        int result = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                result += list.get(i).get(j).size();
            }
        }
        System.out.println(result);
    }
    // 겨울에는 땅에 양분을 추가한다. 각 칸에 추가되는 양분은 초기 입력 양분이다.
    private static void solveWinter() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j] += copy[i][j];
            }
        }
    }
    // 가을에는 나무가 번식한다. 나이가 5의 배수인 나무의
    // 인접 8칸에 나이가 1인 나무가 생긴다.
    static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    private static void solveFall() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    for(int k=0;k<list.get(i).get(j).size();k++) {
                        // age가 5의 배수가 아니라면 패스
                        if(list.get(i).get(j).get(k).age%5!=0) continue;
                        for(int z=0;z<8;z++) {
                            int tx = i+dir[z][0];
                            int ty = j+dir[z][1];
                            if(tx<0 || ty<0 || tx>=n || ty>=n) continue;
                            // 해당 칸이 나이가 1인 나무 생성
                            list.get(tx).get(ty).add(0,new Pair(tx, ty, 1, 1));
                        }
                    }
            }
        }
    }
    // 여름에는 봄에 죽은 나무가 양분으로 변한다.
    // 죽은 나무의 나이/2가 양분으로 해당칸에 추가된다.
    private static void solveSummer() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    // 해당 좌표의 나무들을 보면서 0인것을 없애주자.
                    for(int k=0;k<list.get(i).get(j).size();k++) {
                        // 죽은 나무라면
                        if(list.get(i).get(j).get(k).v==0) {
                            arr[i][j] += list.get(i).get(j).get(k).age/2;
                            // k--를 해주어 없어진 걸로 인한 영향을 없앤다.
                            list.get(i).get(j).remove(k);
                            k--;
                        }
                }
            }
        }
    }
    
    // 봄에는 나무가 나이만 큼 양분을 먹고 나이가 +1 (자신칸만 가능)
    // 하나의 칸에 여러 개의 나무가 있따면 나이 어린 나무가 먼저 먹는다.
    // 양분이 부족해 먹지못하면 먹지 않고 바로 즉사
    private static void solveSpring() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                // 나무 검사
                for(int k=0;k<list.get(i).get(j).size();k++) {
                    if(list.get(i).get(j).get(k).age>arr[i][j]) {
                        // 나무의 나이가 더 크다면 즉사
                        list.get(i).get(j).get(k).v = 0;
                    }else {
                        // 해당지역 나이 만큼 양분 감소
                        arr[i][j] -= list.get(i).get(j).get(k).age++;
                    }
                }
            }
        }
    }
    
    static class Pair{
        int x,y; // 위치
        int v; // 상태 (1살음,0죽음)
        int age; // 나이
        Pair(int x,int y,int v,int age){
            this.x = x;
            this.y = y;
            this.v = v;
            this.age = age;
        }
    }
}