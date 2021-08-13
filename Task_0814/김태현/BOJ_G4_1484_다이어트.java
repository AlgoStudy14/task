package ss;

import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 제곱의 차가 주어진 수가 나오는 모든 경우의 수를 구하여라.
 * 문제 유형 : 투 포인터.
 * <풀이 요약>
 * 1. 1 ~ 100,000까지의 모든 숫자를 저장한다.
 * 2. 배열의 가장 첫 번째 인덱스를 start = 1, 두 번째 인덱스를 end = 2로 두고 포인터를 이동시키며 다음을 진행한다.
 * -> (end의 값^2 - start의 값^2) = diff 를 구한다.
 * -> G > diff인 경우 end++
 * -> G < diff인 경우 start++
 * -> start == end 혹은 end가 100,000을 넘어가는 경우 종료.
 * <피드백>
 * 투 포인터 문제! 아이디어 외워두자.
 */

public class BOJ_G4_1484_다이어트 {
	static int G;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		G = sc.nextInt();

		int start = 1, end = 2, cnt = 0;
		while (start < end || end <= 100000) {
			long diff = (long) (Math.pow(end, 2) - Math.pow(start, 2));
			if(G > diff) {
				end++;
			}
			else if(G < diff) {
				start++;
			}
			else {
				System.out.println(end);
				end++;
				cnt++;
			}
		}
		if(cnt == 0) {
			System.out.println(-1);
		}
		sc.close();
	}
}
