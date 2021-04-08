import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* <문제 요약>
 * 구해야 하는 것 : N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것  
 * 유형 : DFS 백트래킹
 * <풀이법 요약>
 * 1. 해당 연산자를 사용할 수 있으면 dfs를 진행한다.
 * -> N개의 숫자를 모두 사용했다면 끝!
 * 
 * '음수를 양수로 나눌 때는 C++14의 기준을 따른다 '
 * 고 되어있는데 그냥 /로 sum을 계속 저장해도 맞는 결과가 나왔다!
 * 
 */

public class Main_S1_14888_연산자_끼워넣기 {

	// nums : 입력받은 숫자들
	// oper : 연산자 개수
	static int[] nums, oper; // + - x /
	static int N;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		oper = new int[4];

		// N개의 수 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 개수 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		dfs(1, nums[0]);
		System.out.println(max + "\n" + min);
	}

	// cnt : 사용한 숫자의 개수 (index라고 생각해도 무방)
	// sum : 결과값
	private static void dfs(int cnt, int sum) {
		if (cnt == N) { // N개의 숫자를 모두 사용했을 때
			max = Math.max(max, sum); // 최댓값을 구하고
			min = Math.min(min, sum); // 최솟값을 구한다.
			return;
		}
		for (int i = 0; i < 4; i++) { // + - * /
			if (oper[i] > 0) { // 해당 연산자를 사용할 수 있을 때 
				oper[i]--; // 해당 연산자 1개를 사용
				switch (i) {
				case 0: // +
					dfs(cnt + 1, sum + nums[cnt]);
					break;
				case 1: // -
					dfs(cnt + 1, sum - nums[cnt]);
					break;
				case 2: // *
					dfs(cnt + 1, sum * nums[cnt]);
					break;
				case 3: // /
					dfs(cnt + 1, sum / nums[cnt]);
					break;
				}
				oper[i]++; // 재귀 호출이 끝나면 해당 연산자 개수 복구
			}
		}
	}

}
