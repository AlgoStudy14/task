import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 합이 0에 가까운 두 용액을 찾기
 * 문제 유형 : 투 포인터
 * 유의 사항 : Max : 2000000000, 비교를 위해 절대값을 사용해야한다
 * 
 * <풀이법 요약>
 * 1. 입력받은 배열을 정렬한다.
 * 2. 양쪽 끝의 투 포인터에 해당하는 값의 합이 0에 가까워지도록 투 포인터를 조정한다.
 * 2-1. 합이 0보다 작을 때 : 합이 0보다 작기 때문에 두 수의 합이 더 커져야 0에 가까워진다 -> left 증가(작은값을 증가)
 * 2-2. 합이 0보다 클 때 : 합이 0보다 크기 때문에 두 수의 합이 더 작아져야 0에 가까워진다 -> right 감소(큰값 감소)
 * 3. 이전에 저장한 합과 비교하여 용액을 저장한다 + 절대값을 사용하여 구한다.
 * 3-1. 합이 0이라면 아무것이나 출력해도 괜찮기 때문에 break해준다.
 * 
 */

public class BOJ_G5_2470_두_용액 {

	final static int INF = 2000000005;
	static int N;
	static int[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		// 배열 정렬
		Arrays.sort(list);

		solve();
	}

	static void solve() {
		// 양 끝에서 시작
		int left = 0;
		int right = N - 1;
		int[] answer = new int[2];
		int sumMin = INF;

		while (left < right) {
			int sum = list[left] + list[right];

			// 합을 비교한다 + 비교를 위해 절대값을 구한다
			if (sumMin > Math.abs(sum)) {
				sumMin = Math.abs(sum);
				// answer에 각 용액의 크기를 저장한다.
				answer[0] = list[left];
				answer[1] = list[right];
				
				// 만약 sum이 0이 된다면 끝
				if (sum == 0) {
					break;
				}
			}

			if (sum < 0) { // 합이 0보다 작기 때문에 두 수의 합이 더 커져야 0에 가까워진다 -> left 증가(작은값을 증가)
				left++;
			} else { // 합이 0보다 크기 때문에 두 수의 합이 더 작아져야 0에 가까워진다 -> right 감소(큰값 감소)
				right--;
			}
		}
		
		// 출력
		for (int i = 0; i < 2; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}
