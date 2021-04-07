import java.util.Scanner;

/*
 * <문제 요약>
 * 숫자가 주어졌을 때 숫자 사이에 알맞은 사칙연산 기호를 넣어서 최대, 최소 수 구하기
 * 사용할 수 있는 각각의 사칙연산 갯수는 정해져 있음
 * 우선순위 연산은 생각하지 않음
 * 
 * <풀이법 요약>
 * 우선순위 연산을 생각하지 않기 때문에 단순히 4가지 사칙연산 기호를 넣어가며 dfs돌림
 */

public class Main_BOJ_14888_연산자끼워넣기 {
	static int N;
	static int[] nums;
	static int[] cals; // + _ X %
	static int max;
	static int min;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		cals = new int[4];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			cals[i] = sc.nextInt();
		}

		dfs(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int num, int result) {
		if (num == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		if (cals[0] > 0) {
			cals[0]--;
			dfs(num + 1, result + nums[num]);
			cals[0]++;
		}

		if (cals[1] > 0) {
			cals[1]--;
			dfs(num + 1, result - nums[num]);
			cals[1]++;
		}

		if (cals[2] > 0) {
			cals[2]--;
			dfs(num + 1, result * nums[num]);
			cals[2]++;
		}

		if (cals[3] > 0) {
			cals[3]--;
			dfs(num + 1, result / nums[num]);
			cals[3]++;
		}
	}

}
