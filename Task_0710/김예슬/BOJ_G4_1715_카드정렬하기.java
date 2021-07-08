package algo0707;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 카드 묶음의 최소 비교 횟수를 출력
 * 문제 유형 : 그리디..?
 * 주의 사항 :
 * <풀이 요약>
 * 카드 묶음이 작은 것을 먼저 합치며 계산하면 비교를 최소화 할 수 있음
 */

public class BOJ_G4_1715_카드정렬하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		PriorityQueue<Integer> card = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++)
			card.add(sc.nextInt());
		
		while (card.size() > 1) {
			int sum = card.poll() + card.poll();
			ans += sum;
			card.add(sum);
		}
		
		System.out.println(ans);
	}
}
