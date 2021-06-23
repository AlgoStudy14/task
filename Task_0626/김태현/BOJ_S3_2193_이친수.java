package s0626;

import java.util.Arrays;
import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : N자리 이친수의 개수를 구하여라.
 * 문제 유형 : DP.
 * 주의 사항 : 피보나치 수열의 값이 증가하는 크기는 매우 크기 때문에, 해당 문제에서는 long형을 선언해주어야 한다.
 * <풀이 요약 : DP>
 * 1. 각 자릿수당 가질 수 있는 원소의 개수를 저장할 1차원 dp테이블을 만든다.
 * 2. 피보나치 수열의 점화식과 같은 점화식을 갖는다.
 * -> 1자리 일 때는 1, 2자리 일 때는 10인 각 한 가지의 경우 밖에 없다.
 * -> n자리 일때는(n >= 3) dp[n] = dp[n - 1] + dp[n - 2]이다.
 * -> dp[n - 1]은 n - 1자리에 0을 추가한 모든 경우이다.
 * -> dp[n - 2]는 n - 2자리에 01을 추가한 모든 경우이다.
 * 3. 최종 dp[n]을 출력한다.
 * <피드백>
 * 우선, dp를 완료했으면 '경계값 체크'를 반드시 해보자. 이 문제에서는 long형으로 dp테이블을 선언했어야 한다.
 * 1. dp의 핵심은(dp테이블 작성 + 초기값 설정 및 점화식 도출)이 두가지에 달려있다.
 * 2. dp 테이블 작성
 * -> '어떤 것'에서 규칙성을 찾을지 생각해보는 단계이다. 이 문제에서는 'N자리수가 가질 수 있는 이친수의 개수'가 '대상'이었다.
 * -> 즉, 이친수의 개수는 어떤 규칙성을 가지는지 고민을 해보며 규칙성을 찾는 것이 기본 마인드이다.
 * 3. 점화식 도출
 * -> 초항부터 차례로 나열해보면서 규칙성을 찾아보는 것이 가장 좋다.
 * -> 이 문제의 경우, 어차피 이전 자리수가 N자리수의 이친수를 구했으므로, 해당 이친수들을 어떻게 활용하여 다음 수를 구할 수 있을지를 고민하는것이 키 포인트였다.
 */

public class BOJ_S3_2193_이친수 {
	static int N;
	static long[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// 초기 값이 N으로 주어지는 경우.
		if(N == 1 || N == 2) {
			System.out.println(1);
		} else {
			dp = new long[N];
			dp[0] = 1;
			dp[1] = 1;
			for(int i = 2; i < N; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}
			System.out.println(Arrays.toString(dp));
			System.out.println(dp[N - 1]);
		}
		sc.close();
	}
}
