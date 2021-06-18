package algo0619;
/*
 * 감소하는 수.
 * n번째 감소하는 수를 찾는 문제이다.
 * DP로 가능해 보이지만 시간초과가 계속 나서 결국 브루트 포스로 풀게되었다.
 int 가 아니라 long으로 해야된다.
 부르트포스란?
 
  * <문제 요약>
 * 구해야 하는 것:n번째 감소하는 수 찾기
 * 제약 사항: 어차피 n이 1000000보다 작은 수라 할지라도 최고로 감소하는수는 9876543210이기 때문에 9876543210이 몇번째인지만 알면 된다.
 * 문제 유형: 브루트포스
 * 요구 개념: 부르트포스
 * 
 * <풀이법>
 * 이건 10
 * 20 21 210 이렇게 다 구해서 k번째 빼내는걸로 풀었다.
 * long으로 안풀면 틀린다.
 * 결국 다 돌아본다.
 * 
 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main_감소하는수1038_2 {
	static ArrayList<Long> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		list = new ArrayList<>();
		if(N<=10)
			System.out.println(N);
		else if(N>1022){
			System.out.println(-1);
		}
		else {
			for(int i=0;i<10;i++) {
				go(i,1);
			}
			Collections.sort(list);
			System.out.println(list.get(N));
		}
	}
	private static void go(long n, int cnt) { //cnt는 자리수이다.
		//9876543210이 10자리
		if(cnt>10)return;
		list.add(n);
		
		for (int i = 0; i < 10; i++) {
			if(n%10 > i)
				go((n * 10)+i,cnt+1);
		}
		return;
	}

}
