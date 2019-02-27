package algorithm;

import java.util.Arrays;

public class Combination {
	public static void main(String[] ar){
        Combination ex = new Combination();
        int[] arr = { 1, 2, 3 };
        boolean[] isMember = new boolean[3];
        int n = arr.length;
        int r = 2;
        int[] combArr = new int[n];
 
        ex.doCombination(combArr, n, r, 0, 0, isMember);
    }
 
    public void doCombination(int[] combArr, int n, int r, int index, int target, boolean[] isMember){       
    	 System.out.println("=> "+n+" "+r+" "+index+" "+target);
         
         // r ==0 이란 것은 뽑을 원소를 다 뽑았다는 뜻.
         if(r == 0){
             System.out.println(Arrays.toString(combArr));
             for(int i=0; i<index; i++)System.out.print(isMember[combArr[i]] + " ");
            
             System.out.println();
          
         //끝까지 다 검사한 경우..1개를 뽑은 상태여도 실패한 경우임 무조건 return 으로 종료
         }else if(target == n){ 
              
             return;
          
         }else{
             combArr[index] = target;
             // (i) 뽑는 경우 
             // 뽑으니까, r-1
             // 뽑았으니 다음 index + 1 해줘야 함
             doCombination(combArr, n, r-1, index+1, target+1, isMember);
              
             //(ii) 안 뽑는경우
             // 안뽑으니까 그대로 r
             // 안뽑았으니, index는 그대로!
             // index를 그대로하면, 예를 들어 현재 1번 구슬을 comArr에 넣엇어도 다음 재귀에 다시 엎어쳐서 결국 1구슬을 뽑지 않게 된다.
             doCombination(combArr, n, r, index, target+1, isMember); 
         }
    }

}
