package s0424;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 괄호를 적절히 추가해 만들 수 있는 식의 최대값.
 * 유의 조건 : 괄호를 제외한 연산자의 우선순위는 동일하다, 중첩된 괄호는 허용하지 않는다, 괄호안의 연산자는 하나만 있어야 한다.
 * 문제 유형 : DFS, 시뮬레이션.
 * <풀이법>
 * 1. 연산자를 기준으로 괄호를 쳐보고, 치지 않아보면서 괄호를 놓는 모든 경우를 탐색한다.
 * -> 단, 이전 연산자에 괄호가 씌워져 있다면 현재 연산자에 대한 괄호는 스킵한다.
 * 2. 모든 괄호가 완성되었다면, 괄호 표시된(true) 연산자를 먼저 계산하고 나머지를 계산하는 시뮬레이션 진행.
 * -> 연산자를 탐색하면서 true인 경우, 수를 계산한 후 큐에 넣는다.
 * -> 연산자를 탐색하면서 false인 경우, 연산자를 큐에 넣는다.
 * -> 최종적으로 큐에서 숫자를 빼면서 값을 계산하고, 최댓값을 갱신한다.
 * <주의사항>
 * 1. 괄호안의 연산자는 하나만 있어야 한다는 조건을 놓쳐서 다르게 풀이했었다.
 * '중요한 조건들'은 항상 눈에 보이게 기록해두고 놓치지 말도록 하자. 풀이가 아예 다른 방향으로 흘러갈 가능성이 생긴다.
 * 2. 괄호안의 숫자를 계산하는 로직은 연습해두면 나중에도 쓸만하다고 생각된다.
 * 3. 전역 변수를 사용할때는 항상 주의하자. 이 문제에서 전역 변수를 일관성 없이 건드렸다가 디버깅하는데 오래 걸렸다.
 */

public class BOJ_G3_16637_괄호추가하기 {
	static int N;
	static int[] nums;
	static char[] opers;
	static boolean[] use;
	static int max;

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N / 2 + 1];
		opers = new char[N / 2];
		use = new boolean[N / 2];
		sc.nextLine();
		String line = sc.nextLine();
		int idx1 = 0, idx2 = 0;
		for (int i = 0; i < N; i++) {
			char cur = line.charAt(i);
			// 숫자면 숫자 저장.
			if (cur >= '0' && cur <= '9') {
				nums[idx1++] = cur - '0';
			}
			// 연산자면 연산자 저장.
			else {
				opers[idx2++] = cur;
			}
		}
		max = Integer.MIN_VALUE;

		// dfs를 이용한 연산자 괄호 치기.
		dfs(0);

		System.out.println(max);

		sc.close();
	}

	private static void dfs(int cnt) {
		// 연산자를 모두 괄호를 칠지 말지 결정해줬으면 계산 진행
		if (cnt == use.length) {
			count();
			return;
		}

		// 첫 번째 연산자는 비교 대상이 없으므로 현재 연산자에 대해서 괄호를 치느냐 마느냐 두 방향으로 진행.
		if (cnt == 0) {
			use[cnt] = true;
			dfs(cnt + 1);
			use[cnt] = false;
			dfs(cnt + 1);
		} else {
			// 앞 연산자에 이미 괄호를 쳤다면 현재 연산자는 무시하고 다음 dfs진행.
			if (use[cnt - 1]) {
				dfs(cnt + 1);
			}
			// 앞 연산자에 괄호가 없다면 현재 연산자에 대해서 괄호를 치느냐 마느냐 두 방향으로 진행.
			else {
				use[cnt] = true;
				dfs(cnt + 1);
				use[cnt] = false;
				dfs(cnt + 1);
			}
		}
	}

	private static void count() {
		Queue<Integer> num = new LinkedList<Integer>();
		Queue<Character> oper = new LinkedList<Character>();
		boolean[] visited = new boolean[N / 2 + 1];
		for (int i = 0; i < N / 2; i++) {
			// 현재 인덱스의 연산자가 true라면 앞 뒤의 숫자를 계산한뒤 큐에 넣는다. 숫자는 방문처리한다.
			if (use[i]) {
				num.offer(rule(nums[i], opers[i], nums[i + 1]));
				visited[i] = true;
				visited[i + 1] = true;
			}
			// 현재 인덱스의 연산자가 false라면 현재 숫자가 방문되어 있지 않는 경우 현재 숫자와 연산자를 각각 큐에 넣는다. 숫자는 방문처리한다.
			else {
				if (!visited[i]) {
					num.offer(nums[i]);
					visited[i] = true;
				}
				oper.offer(opers[i]);
			}
		}
		// 마지막 숫자가 방문처리 안되어 있다면 큐에 넣는다.
		if (!visited[N / 2]) {
			num.offer(nums[N / 2]);
		}

		// 최종 계산
		int ans = num.poll();
		while (!num.isEmpty()) {
			ans = rule(ans, oper.poll(), num.poll());
		}

		// 최댓값 갱신
		max = Math.max(max, ans);
	}

	private static Integer rule(int i, char c, int j) {
		switch (c) {
		case '+':
			return i + j;
		case '-':
			return i - j;
		case '*':
			return i * j;
		case '/':
			return i / j;
		}
		return null;
	}
}
