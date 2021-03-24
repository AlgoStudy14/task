import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 주어진 연산자 카드로 만들수 있는 최대 값과 최소 값의 차이.
 * 요구 개념/문제 유형 : DFS
 * 
 * <풀이법 - 순열 : 시간초과>
 * 각 연산자를 나열하고, 순열을 이용하여 연산자를 선택하는 순서를 모두 고려해본다.
 * -> 주어진 조건의 최대 연산자의 수는 11개. 11!은 약 4천만의 경우의 수를 가진다.
 * -> 각 경우의 수에대하여, 12개의 숫자를 더하는 경우를 생각해보면 4천만 x 12. 따라서 5억번 정도의 연산이 필요하므로 시간초과.
 * 
 * <풀이법 - DFS>
 * 1. 부분 집합의 논리를 이용하여, 각 사칙연산을 포함하거나 포함시키지 않으면서 값을 계산한다.
 * -> 가지 치기 : 연산이 불가능한 경우(0으로 나누는 경우)는 재귀를 진행하지 않는다.
 * -> 종료 조건 : 각 사칙연산은 연산자의 개수 만큼만 포함 시킨다.
 * 2. 종료 조건에 도달하면, 최소, 최대 값을 갱신한다.
 * 3. 최종 최소, 최대 값의 차이를 출력한다.
 */
public class Solution_4008 {
	static int T, N;
	static int[] oper, nums;
	static int max, min;
	// 현재 사용되는 연산자의 개수.
	static int plus, minus, multiply, divide;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			N = sc.nextInt();
			oper = new int[4];
			nums = new int[N];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			plus = 0;
			minus = 0;
			multiply = 0;
			divide = 0;
			// 연산자는 +, -, *, / 순서이다.
			for (int i = 0; i < 4; i++) {
				oper[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			// dfs 탐색을 진행한다.
			dfs(1, nums[0]);
			
			// 최종 결과 출력.
			System.out.printf("#%d %d\n", t, Math.abs(max - min));
		}
		sc.close();
	}

	private static void dfs(int idx, int res) {
		// 모든 숫자에 대한 계산이 끝났으면, 최소, 최대값을 갱신한다.
		if (idx == N) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		// 덧셈을 포함 시켜 연산한다.
		if (plus < oper[0]) {
			plus++;
			dfs(idx + 1, res + nums[idx]);
			plus--;
		}
		// 뺄셈을 포함 시켜 연산한다.
		if (minus < oper[1]) {
			minus++;
			dfs(idx + 1, res - nums[idx]);
			minus--;
		}
		// 곱셈을 포함 시켜 연산한다.
		if (multiply < oper[2]) {
			multiply++;
			dfs(idx + 1, res * nums[idx]);
			multiply--;
		}
		// 나눗셈을 포함 시켜 연산한다.
		if (divide < oper[3]) {
			// 단, 0으로 나누는 경우는 재귀를 진행하지 않는다.
			if (nums[idx] == 0) {
				return;
			}
			divide++;
			dfs(idx + 1, res / nums[idx]);
			divide--;
		}

	}
}
