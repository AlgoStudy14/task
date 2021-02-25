import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/*
 * <문제 요약>
 * 어떤 사람이 M번 공을 가질때까지 공을 던짐
 * 공을 가진 사람이 홀수번 공을 가졌을 경우 시계 방향으로 L번 떨어져 있는 사람에게 공을 던짐
 * 공을 가진 사람이 짝수번 공을 가졌을 경우 반시계 방향으로 L번 떨어져 있는 사람에게 공을 던짐
 * 
 * <풀이법 요약>
 * 시계방향, 반시계 방향으로 나누어서 공을 던지기 때문에 Deque로 구현
 */

public class Main_BOJ_1592_영식이와친구들 {
	static int N, M, L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();
		int answer = 0;
		int[] count = new int[N];
		Deque<Integer> run = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			run.add(i);
		}
		count[0]++;

		while (true) {
			if (count[run.peekFirst()] % 2 == 1) // 홀수
			{
				for (int i = 0; i < L; i++) {
					int num = run.pollFirst();
					run.offerLast(num);
				}
			} else {
				for (int i = 0; i < L; i++) {
					int num = run.pollLast();
					run.offerFirst(num);
				}
			}

			count[run.peekFirst()]++;
			answer++;
			if (count[run.peekFirst()] == M) {
				System.out.print(answer);
				return;
			}
		}

	}

}
