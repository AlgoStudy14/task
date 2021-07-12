import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : N개의 연속된 집을 골라 돈을 훔칠 때, 방범장치가 작동하지 않게 돈을 훔치는 경우의 수.
 * 문제 유형 : 슬라이딩 윈도우.
 * 주의 사항 : 집은 원형으로 이어져 있다, 테스트 케이스형 문제이다.
 * <풀이 요약>
 * 1. 집의 개수 + (연속된 집의 개수 - 1)만큼의 배열을 선언한다.
 * 2. 원형의 형태에 맞게 집을 초기화한다.
 * 3. 슬라이딩 윈도우를 이용하여, 합을 구하며 훔친 돈이 K미만이 되는 모든 경우의 수를 구한다.
 * <피드백>
 * 1. N = M이 되는 순간은 엣지 케이스였다. 주의하자.
 * 2. 킹라이딩 윈도우.. 치밀한 인덱스 조작에 가슴이 웅장해진다..
 */

public class BOJ_G4_13422_도둑 {
	static int T, N, M, K;
	static int[] house, prefix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 집의 개수에 맞게 배열을 초기화한다.
			house = new int[N + M - 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				house[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = N; i < N + M - 1; i++) {
				house[i] = house[i - N];
			}
			// 슬라이딩 윈도우를 이용하여 모든 경우의 수를 구한다.
			int cnt = 0;
			int start = 0, end = M - 1;
			int sum = 0;
			for (int i = 0; i < M; i++) {
				sum += house[i];
			}
			if (N == M) {
				if (sum < K) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			} else {
				while (true) {
					if (sum < K) {
						cnt++;
					}
					end++;
					if(end == N + M - 1)
						break;
					sum += house[end];
					sum -= house[start++];
				}
				System.out.println(cnt);
			}
		}
	}
}
