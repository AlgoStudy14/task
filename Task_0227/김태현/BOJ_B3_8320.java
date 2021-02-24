package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 정사각형이 n개 주어졌을 때, 이어 붙여 만들 수 있는 정사각형의 개수(단, 회전등 변형 불가능).
 * 문제 유형 : 그리디.. 이게 B3이라고..?
 * 요구 개념 : 직사각형의 넓이
 * <풀이법 요약>
 * 1. 각 k마다 만들 수 있는 최대 직사각형의 개수를 구한다.
 * -> k를 1부터 sqrt(n)까지 나눈다. 나누어 떨어지는 개수가 n개로 만들 수 있는 사각형 갯수.
 * -> n이 될때까지 진행한다.
 */

public class BOJ_B3_8320 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int tot = 0;
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 1; j <= (int)Math.sqrt(i); j++) {
				if(i % j == 0) {
					cnt++;
				}
			}
			tot += cnt;
		}
		System.out.println(tot);
		sc.close();
	}
}
