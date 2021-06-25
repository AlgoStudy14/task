import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* (미완)
 * <문제 요약>
 * 문제 정의 : 최대 K만큼의 무게를 넣을 수 있는 배낭에 넣을 수 있는 물건들의 가치의 최댓값
 * 문제 유형 : DP
 * 
 * <풀이법 요약>
 * 
 * N : 가치
 * W : 무게
 * f(N,W)
 * max - O(담을 때)    : f(N-1, W-Wn) + Vn (Wn <= W) - 물건을 넣고 무게 감소 가치 추가
 *     - X(담지 않을 때) : f(N-1, W) - 물건 변동 없음
 *     
 * <피드백>
 * 처음에 보고 풀지 못했던 문제..!
 * 수업을 다시 듣고 풀어보았다
 * dp에서 2차원 배열을 이용하는 부분을 공부해야 겠다. 아직 여기까지 생각이 가지 못했다
 * 나중에 다시 풀어봐야할 문제
 * 
 */
public class BOJ_G5_12865_평범한_배낭 {

	static int N, K;
	static int[][] dp;
	static int[] weights, values;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		weights = new int[N + 1]; // 물건의 무게정보
		values = new int[N + 1]; // 물건의 가치정보
		dp = new int[N + 1][K + 1]; // 해당물건까지 고려하여 해당무게를 만들 때의 최대 가치

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) { // 첫 물건부터 고려
			for (int w = 1; w <= K; w++) { // 무게 1부터 고려
				if (weights[i] <= w) { // 가방에 넣을 수 있는 상황
					// 넣을까
					dp[i][w] = Math.max(dp[i - 1][w - weights[i]] + values[i], dp[i - 1][w]);
				} else { // 가방에 넣지 못하는 상황
					dp[i][w] = dp[i - 1][w];
				}
			}
		}

		System.out.println(dp[N][K]);
	}

}
