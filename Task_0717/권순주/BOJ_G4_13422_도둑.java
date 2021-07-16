import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_13422_도둑 {

	static int T, N, M, K;
	static int[] house;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			house = new int[N];
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				house[i] = Integer.parseInt(st.nextToken());
				if (i < M) {
					sum += house[i]; // 처음 M개의 합 구하기
				}
			}

			int left = 0;
			int right = M;

			if (N == M) { // 집의 개수 = 훔쳐야 할 집의 개수
				if (sum < K) { // 합이 K보다 작다면 => 1개
					System.out.println(1);
				} else { // 합이 K보다 크거나 같다면 => 0개
					System.out.println(0);
				}
			} else {
				int answer = 0;
				// 슬라이딩 윈도우
				while (left < N) { 
					if (sum < K) { // sum이 K보다 작을 때만 증가
						answer++;
					}
					sum -= house[left]; // 가장 왼쪽꺼 빼주기
					sum += house[right % N]; // 가장 오른쪽꺼 더해주기 + 오른쪽 끝나면 0부터 시작! => mod 사용
					left++; // 왼쪽 증가
					right++; // 오른쪽 증가
				}
				System.out.println(answer);
			}
		}
	}
}
