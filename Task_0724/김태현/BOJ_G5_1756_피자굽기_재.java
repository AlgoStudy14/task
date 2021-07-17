import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 오븐에 들어가 있는 가장 가까운 피자의 깊이.
 * 문제 유형 : (구현), (이분 탐색 + 구현).
 * 주의 사항 : 오븐의 크기는 제각각 다르다.
 * <풀이 요약>
 * 1. 오븐의 틀을 재가공하여 저장한다(위 오븐의 크기가 더 작으면 아래 오븐의 크기를 위 오븐의 크기에 맞춘다).
 * 2. 이분 탐색을 이용하여 오븐이 들어갈 수 있는 최대 깊이를 찾고 해당 위치에 피자를 넣는다.
 * 3. 최종적으로 들어간 피자의 위치를 출력한다.
 */

public class BOJ_G5_1756_피자굽기_재 {
	static int D, N;
	static int[] oven;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new int[D];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
		}

		// 오븐 재가공.
		for (int i = 1; i < D; i++) {
			if (oven[i] > oven[i - 1])
				oven[i] = oven[i - 1];
		}

		// 각 반죽의 최대 깊이를 이분 탐색으로 찾기
		st = new StringTokenizer(br.readLine());
		int depth = D - 1;
		for (int i = 0; i < N; i++) {
			// 현재 반죽
			int cur = Integer.parseInt(st.nextToken());
			// 이분 탐색을 활용하여 현재 반죽의 최대 깊이 찾기
			int res = BinarySearch(cur, 0, depth);

			// 만일, 결과가 -1이라면 오븐에 반죽을 넣을 수 없는 상태
			if (res == -1) {
				System.out.println(0);
				return;
			}
			// 결과가 나왔다면, 해당 반죽 위치의 바로 윗쪽 부분까지의 범위에서 이분 탐색을 계속한다.
			depth = res - 1;
		}
		
		// 최종 결과
		System.out.println(depth + 2);
	}

	private static int BinarySearch(int cur, int start, int end) {
		int res = -1;

		while (start <= end) {
			int mid = (start + end) / 2;

			// 현재 반죽의 크기가 오븐 틀의 크기보다 작거나 같다면
			if (cur <= oven[mid]) {
				// 현재 깊이를 우선 기억하고
				res = mid;
				// 더 깊은 오븐틀을 탐색
				start = mid + 1;
			}
			// 그렇지 않다면
			else {
				// 더 얕은 오븐틀을 탐색
				end = mid - 1;
			}
		}

		return res;
	}
}
