package s0619;

import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의 : N번째로 감소하는 수를 구하여라.
 * 문제 유형 : (DFS), (완전 탐색), (완전 탐색 : 가지 치기)
 * 주의 사항1 : 총 9,876,543,210까지 모두 조사해야 하므로 long형을 사용하여야 한다.
 * 주의 사항2 : N == 0인 경우도 따져주어야 한다.
 * 
 * <풀이 요약 : DFS : 실패>
 * 일반적인 재귀호출을 한다면, 호출이 매우 깊어져 StackOverflow가 발생한다! 무작정 DFS를 사용해서는 안된다.
 * 
 * <풀이 요약 : 완전 탐색 : 실패>
 * 문제를 잘못 이해하였다. 감소하는 수는 완전 탐색을 이용하면 최대 9,876,543,210까지 모든 수를 탐색해야 한다.
 * 따라서, 일반적인 방식으로 완전 탐색을 진행한다면 시간 초과가 발생할 것이다.
 * 문제를 먼저 정확하게 이해하자!!!!!
 * 
 * <풀이 요약 : 완전 탐색, 가지 치기>
 * 1. 0부터 9,876,543,210까지 반복문을 진행하며 해당 수가 감소하는 수인지 파악한다.
 * -> 감소하는 수라면 cnt를 증가시킨다.
 * -> 감소하는 수가 아니라면, 값을 (10^해당 자릿수) * (10 - 해당 자릿수의 값)만큼 값을 증가시킨다.
 * -> cnt == N이 된다면, 해당 수를 반환한다.
 * 2. 반복문이 종료되었는데도 cnt == N이 아니라면, -1을 출력한다.
 * <피드백>
 * 감소하는 수가 아니라면, 해당 자릿수의 이후 숫자는 모두 무시해도 된다. 따라서, 수학적으로 계산해주었다.
 * 1. 이 문제에서는 로직의 순서가 중요했다. 특히, 개수를 세는 문제에서는 이를 특히 주의하자.
 * 2. 문제의 조건에서 N == 0인 경우도 존재한다. 문제의 모든 조건을 고려해야 한다.
 */

public class BOJ_G5_1038_감소하는수 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// 예외 케이스 주의.
		if (N == 0) {
			System.out.println(0);
			sc.close();
			return;
		}
		System.out.println(search());
		sc.close();
	}

	private static long search() {
		// 0부터 1,000,000까지 반복문을 진행한다.
		int cnt = 0;
		long val = 1;
		while (val <= 9_876_543_210l) {
			// i를 val을 String으로 바꾼다.
			String temp = Long.toString(val);
			// 길이 함수를 매번 호출하면 시간이 많이 들기 때문에, len을 미리 구한다.
			int len = temp.length();
			// 해당 String의 앞부터 탐색하며 감소하는 수인지 파악한다.
			boolean flag = true;
			int idx = 0;
			for (int j = 0; j < len - 1; j++) {
				// 감소하는 수가 아닌 경우
				if (temp.charAt(j) <= temp.charAt(j + 1)) {
					flag = false;
					idx = j + 1;
					break;
				}
			}
			// 감소하는 수라면, cnt를 증가시키고, 값을 1 증가시킨다.
			if (flag) {
				cnt++;
				// cnt == N이라면, 해당 값을 출력한다.
				if (cnt == N) {
					return val;
				}
				val++;
			}
			// 감소하는 수가 아니라면, 10^해당 자릿수 * (10 - 해당 자릿수의 값)만큼 값을 증가시킨다.
			else {
				val += Math.pow(10, (len - idx - 1)) * (10 - (temp.charAt(idx) - '0'));
			}
		}
		// 반복문 내에서 감소하는 수를 찾지 못했다면, -1을 출력한다.
		return -1;
	}
}
