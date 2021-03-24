import java.util.Scanner;
import java.util.Stack;

/*
 * <문제 요약>
 * 구해야 하는 것 : 맛의 차이가 최소가 되게 식재료를 나누는 방법(그때 맛의 차이).
 * 요구 개념/문제 유형 : 완전 탐색, 조합.
 * <풀이법>
 * 1. N개의 식재료중 N/2개를 선택하는 모든 조합의 수를 구한다.
 * 2. 각 조합의 수에 대하여 식재료의 차를 계산하고, 최솟값을 갱신한다.
 * <주의점>
 * 배열에서 중복되지 않는 두 그룹으로 나누려면 visited 배열을 활용할 수 있다.
 * -> true인 대상은 A 그룹에, false인 대상은 B 그룹에 넣는 방법.
 */

public class Solution_4012 {
	static int T, N;
	static int[][] table;
	// 선택된 번호를 담을 스택
	static Stack<Integer> selected;
	// 현재 최소 비용
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			N = sc.nextInt();
			table = new int[N][N];
			selected = new Stack<Integer>();
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					table[i][j] = sc.nextInt();
				}
			}
			// N개의 식재료중 N/2개를 선택하는 모든 조합의 수를 구한다.
			Combination(0, 0);
			System.out.printf("#%d %d\n", t, min);
		}
		sc.close();
	}

	private static void Combination(int cnt, int idx) {
		if (cnt == N / 2) {
			Stack<Integer> non_selected = new Stack<Integer>();
			// 선택되지 않은 나머지 수를 구한다.
			for (int i = 0; i < N; i++) {
				if (!selected.contains(i)) {
					non_selected.push(i);
				}
			}
			// 차이를 계산하고 갱신한다.
			int sum = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					sum += table[selected.get(i)][selected.get(j)];
					sum -= table[non_selected.get(i)][non_selected.get(j)];
				}
			}
			min = Math.min(min, Math.abs(sum));
			return;
		}
		for (int i = idx; i < N; i++) {
			selected.push(i);
			Combination(cnt + 1, i + 1);
			selected.pop();
		}
	}
}
