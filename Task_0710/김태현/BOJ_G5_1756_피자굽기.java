import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 반죽이 완성되는 순서대로 피자 반죽을 오븐에 넣을 때, 최상단부터 가장 가까운 반죽위치 까지의 깊이를 구하라.
 * 문제 유형 : 이분 탐색.
 * 주의 사항 : 단, 반죽이 틀보다 크다면 그 이상 깊이로 들어갈 수 없다.
 * <풀이 요약>
 * 1. 바로 위 반죽 틀이 작으면 아래 반죽틀이 커도 들어갈 수 없으므로, 그 기준에 맞게 반죽틀을 재가공한다.
 * 2. 반죽 틀이 내림 차순으로 정렬되어 있으므로, 이분 탐색을 활용하여 현재 들어오는 반죽의 적절한 위치를 찾는다.
 * -> 해당 위치를 찾으면, 해당 깊이 - 1를 끝 지점으로 하여 이분 탐색을 반복한다.
 * 3. 최종 깊이를 출력한다.
 * <피드백>
 * (1) 어느 정도 구현이 필요한 문제였다. 다만, 이분 탐색을 활용하려면 '정렬된 배열'이 있다는 점에서 힌트를 얻을 수 있다.
 * (2) 찾고자 하는 대상에 따라 분기문을 잘 작성할 수 있다. 여기서는 오븐에 넣을 수 있는 경우 더 깊은 부분을 찾아야 하므로 >=을 이용하여 2개의 분기문만 작성하였다.
 */
public class BOJ_G5_1756_피자굽기 {
	static int D, N;
	static int[] oven;
	static int bot;
	static int ans;

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
			// 이전 반죽틀이 현재 반죽 틀보다 작다면, 현재 반죽 틀을 이전 반죽틀의 크기와 같게 설정한다.
			if (oven[i - 1] < oven[i]) {
				oven[i] = oven[i - 1];
			}
		}

		// 들어오는 반죽의 위치를 이분 탐색하여 찾는다.
		st = new StringTokenizer(br.readLine());
		bot = D - 1;
		// 최대 깊이
		ans = D;
		for (int i = 0; i < N; i++) {
			// 최 상단(idx : 0), 부터 최 하단(bot)에서 반죽이 들어갈 위치를 이분 탐색한다.
			binarySearch(Integer.parseInt(st.nextToken()), 0, bot);
		}
		System.out.println(ans);
	}

	private static void binarySearch(int num, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			// 만일 오븐의 크기가 크거나 같다면, 오븐의 아래 지역을 이분 탐색
			if (oven[mid] >= num) {
				// 해당 위치의 윗 부분을 기억
				bot = mid - 1;
				start = mid + 1;
			}
			// 만일 오븐의 크기가 작다면, 오븐의 위 지역을 이분 탐색
			else {
				end = mid - 1;
			}
		}

		// 최대 깊이 갱신
		// 오븐에 넣을 수 있는 경우.
		if (end != -1) {
			ans = Math.min(ans, bot + 2);
		}
		// 오븐에 넣을 수 없는 경우.
		else {
			ans =  Math.min(ans, end + 1);
		}
	}
}
