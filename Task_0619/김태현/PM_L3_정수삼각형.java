package s0619;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 삼각형의 꼭대기에서 아래로 이동하는 경로의 중 합이 가장 큰 경우를 찾아라.
 * 문제 유형 : DP.
 * <풀이 요약 : DP>
 * 1. 삼각형 모양 2차원 dp 테이블을 만든다.
 * 2. n행의 값을 기반으로 n + 1행의 값을 누적하며 구한다. 이때, 최댓값이 갱신된다면 갱신한다.
 * <피드백>
 * -> dp는 Top-down, Bottom-up 방식으로 구현이 가능한데, 해당 문제는 Bottom-up방식을 사용하였다.
 * -> Bottom-up 방식은 우선, 합리적인 dp테이블을 만들어야한다. dp 테이블이란, 다음 단계의 문제를 해결하기 위해 사용할 이전 단계의 값을 저장해놓는 자료구조이다.
 * -> dp 테이블은 일반적으로 배열의 형태이고, 문제에 따라 보통 1차원, 2차원으로 나뉜다. 해당 문제에서는 삼각형 모양의 배열을 만들었다.
 * -> 해당 문제는 각 행의 이전 행까지의 합을 이용하는 부분문제로 해석할 수 있다. 이를 이용하여 dp를 설계하였다.
 */
public class PM_L3_정수삼각형 {
	public static void main(String[] args) {
		int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
		solution(triangle);
	}

	public static int solution(int[][] triangle) {
		int answer = 0;
		// dp 테이블을 만든다.
		int[][] dp = new int[triangle.length][];
		for (int i = 0; i < triangle.length; i++) {
			dp[i] = new int[triangle[i].length];
		}
		// dp 테이블 1행의 값 초기화.
		dp[0][0] = triangle[0][0];

		// triangle 배열을 탐색하며, dp테이블의 n행의 값을 기반으로 n + 1행의 값을 갱신한다.
		// 행(n - 1행까지만 반복)
		for (int i = 0; i < triangle.length - 1; i++) {
			// 모든 열 탐색
			for (int j = 0; j < triangle[i].length; j++) {
				// dp 테이블의 n행의 n열 + 삼각형 배열의 n + 1행의 n열 > dp 테이블의 n행의 n열이라면 dp테이블 갱신(좌측 경로)
				if (dp[i][j] + triangle[i + 1][j] > dp[i + 1][j]) {
					dp[i + 1][j] = dp[i][j] + triangle[i + 1][j];
					// 최댓값 갱신
					answer = Math.max(answer, dp[i + 1][j]);
				}
				// dp 테이블의 n행의 n열 + 삼각형 배열의 n + 1행의 n + 1열 > dp 테이블의 n행의 n + 1열이라면 dp테이블 갱신(우측 경로)
				if (dp[i][j] + triangle[i + 1][j + 1] > dp[i + 1][j + 1]) {
					dp[i + 1][j + 1] = dp[i][j] + triangle[i + 1][j + 1];
					// 최댓값 갱신
					answer = Math.max(answer, dp[i + 1][j]);
				}
			}
		}
		
		return answer;
	}
}
