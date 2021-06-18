package algo0619;

import java.util.Scanner;
/*
 * <문제 요약>
 * 구해야 하는 것:감소하는 수 구하기
 *
 * 문제 유형: dp
 * 요구 개념: dp
 * 시간초과남
 * 
 * <풀이법>
 */
public class Main_감소하는수1038 {
	static int N;
	static long answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		if(N>1022)
			System.out.println(-1);
		else if(N<10)
			System.out.println(N);
		else {
			go(10,10);
			System.out.println(answer);
		}
	}
	private static void go(long an, long cnt) {
		if(cnt==N) {
			answer=an;
			return;
		}
		long temp=an;
		boolean ch = true;
		while(true) {
			if(temp<10) break;
			long a = temp %10;
			temp/=10;
			long b=temp %10;
			if(a>=b) { 
			ch=false;
			break;
			}
		}
		if(ch)go(an+1,cnt+1);
		else go(an+1,cnt);
	}

}
