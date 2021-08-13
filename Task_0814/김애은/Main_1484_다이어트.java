package 백준;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/* 
 * y^2-x^2=G
 * x<y라서 x를 y-1로 볼 수 있다.
 * G는 100000보다 작거나 같은 자연수
 	최대값: G=100000
 * y^2 -(y-1)^2 <=100000
	y가 최대 50000
 * y^2 = G+x^2
 *  > G 이면 start를 늘리고, <= G 이면 end를 늘려서 찾으면 end의 값을 리스트에 저장
 */
public class Main_1484_다이어트 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        int start = 1;
        int end = 2;
        ArrayList<Integer>arr = new ArrayList<>();
        while(true) {
            long ans = (long)(Math.pow(end, 2)) - (long)(Math.pow(start, 2));
            if(end - start == 1 && ans > g) break; // 끝까지 가본거
            if(ans >= g) start++;
            else end++;
            if(ans == g) {
                arr.add(end);
            }
        }
        if(arr.size()==0) System.out.println(-1);
        else {
        	for(int a:arr)
        		System.out.println(a);
        }
    }
}
