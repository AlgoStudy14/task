import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 2명의 사용자가 궤적을 이용하는 동안 얻을 수 있는 에너지의 최대량. 
 * 요구 개념/문제 유형 : 시뮬레이션
 * <풀이법>
 * 1. 초 단위로 사용자의 위치를 움직인다.
 * 2. 각 초마다 다음 작업을 진행한다.
 * -> 사용자 A와 B에 대하여 각각 근처에 통신 가능한 BC를 선택하여 각각 우선순위 큐에 넣는다(큐는 비용 순위로 정렬).
 * -> 최종 합이 제일 크도록 A, B에서 값을 최대 2개 선택하고 최종 값에 더한다.
 * 3. 최종 더한 값을 출력한다.
 * <의문>
 * 조금더 섹시하게 중복되는 부분에서 최선의 선택을 하는 방법은 없을까..
 */

public class Solution_5644 {
	static int T, time, N;
	static int[] A, B;
	static int[][] BC;
	// 이동 안함, 상, 우, 하, 좌
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };
	// 총 합
	static int tot;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			time = sc.nextInt();
			N = sc.nextInt();
			A = new int[time + 1];
			B = new int[time + 1];
			tot = 0;
			// 초기 위치도 고려하기 위해 0초는 움직이지 않는 0을 넣는다.
			A[0] = 0;
			B[0] = 0;
			for (int i = 1; i < time + 1; i++) {
				A[i] = sc.nextInt();
			}
			for (int i = 1; i < time + 1; i++) {
				B[i] = sc.nextInt();
			}
			BC = new int[N][];
			for (int i = 0; i < N; i++) {
				BC[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
			}

			// 초 단위로 사용자를 움직인다
			int[] posA = { 1, 1 };
			int[] posB = { 10, 10 };
			for (int i = 0; i < time + 1; i++) {
				// 사용자 A와 B에 대하여 각각 근처에 통신 가능한 BC를 선택하여 우선순위 큐에 넣는다(큐는 비용 순위로 정렬).
				PriorityQueue<int[]> pqA = new PriorityQueue<int[]>((o1, o2) -> (Integer.compare(o2[3], o1[3])));
				PriorityQueue<int[]> pqB = new PriorityQueue<int[]>((o1, o2) -> (Integer.compare(o2[3], o1[3])));
				posA[0] += dr[A[i]];
				posA[1] += dc[A[i]];
				for (int a = 0; a < BC.length; a++) {
					int dist = Math.abs(posA[0] - BC[a][1]) + Math.abs(posA[1] - BC[a][0]);
					if (dist <= BC[a][2]) {
						pqA.offer(BC[a]);
					}
				}
				posB[0] += dr[B[i]];
				posB[1] += dc[B[i]];
				for (int a = 0; a < BC.length; a++) {
					int dist = Math.abs(posB[0] - BC[a][1]) + Math.abs(posB[1] - BC[a][0]);
					if (dist <= BC[a][2]) {
						pqB.offer(BC[a]);
					}
				}

				// 우선 순위큐에서 최대 2번 값을 뽑아 더하고, 우선 순위 큐를 비운다.
				// A, B둘 다 없는 경우.
				if (pqA.isEmpty() && pqB.isEmpty()) {
					continue;
				}
				// A만 존재하는 경우
				if (!pqA.isEmpty() && pqB.isEmpty()) {
					tot += pqA.peek()[3];
					continue;
				}
				// B만 존재하는 경우
				if (pqA.isEmpty() && !pqB.isEmpty()) {
					tot += pqB.peek()[3];
					continue;
				}
				// A, B가 동시에 존재하는 경우
				if (!pqA.isEmpty() && !pqB.isEmpty()) {
					// 만일 꺼낸 BC가 같은 경우
					if (pqA.peek().equals(pqB.peek())) {
						// 우선 해당 BC 선택
						int one = pqA.poll()[3];
						pqB.poll();
						tot += one;
						// A, B둘 다 없는 경우.
						if (pqA.isEmpty() && pqB.isEmpty()) {
							continue;
						}
						// A만 존재하는 경우
						if (!pqA.isEmpty() && pqB.isEmpty()) {
							tot += pqA.peek()[3];
							continue;
						}
						// B만 존재하는 경우
						if (pqA.isEmpty() && !pqB.isEmpty()) {
							tot += pqB.peek()[3];
							continue;
						}
						// A, B 둘 다 존재하는 경우.
						if (!pqA.isEmpty() && !pqB.isEmpty()) {
							tot += Math.max(pqA.peek()[3], pqB.peek()[3]);
							continue;
						}
					}
					// 다른 경우
					else {
						tot += pqA.peek()[3] + pqB.peek()[3];
					}
				}
			}
			System.out.printf("#%d %d\n", t, tot);
		}
		sc.close();
	}
}
