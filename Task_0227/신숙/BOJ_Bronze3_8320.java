package BaekJoon;

import java.util.Scanner;

public class BOJ_Bronze3_8320 {
	/*
	 * 직사각형을 만드는 방법.
	 * 
	 * Math.sqrt(N)보다 작은수까지 for(int i= 1; i< Math.sqrt(N);i++) 하면서 나누어떨어지는 수일때 count++ 시키는데, 같은수 이상일때만 세어야함.
	 * ex) N = 6, i = 1 일 때, 6 % 1 == 0 이므로 시작함. (1, 1)~ (1,6)
	 * 			  i = 2 일 때, 6 % 2 == 0 이므로 시작. (2, 2), (2, 3). sqrt(6) 은 2.xxx 따라서 2까지만 계산.
	 * 
	 * 1. 틀렸음. 뭐가 틀렸지?
	 * 		=> 일단 break가 아니라 continue. 그래도 틀렸음
	 * 2. 흠... N = 10, 개수가 15가 나와야할 것 같은데 14가 나오네.
	 * 		=> 아 나누어떨어질때만 하는게 아닌가? 그러네;
	 */
	static int N, count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i = 1; i <= Math.sqrt(N); i++) {
			//if(N % i != 0)
			//	continue;
			for(int j = i; j <= N; j++) {
				if(i * j > N)
					break;
				if(j * i == N) {
					count++;
					break;
				}
				count++;
			}
		}
		System.out.println(count);
	}
}
