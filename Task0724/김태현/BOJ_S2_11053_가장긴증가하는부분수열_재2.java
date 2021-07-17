import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 가장 긴 증가하는 부분 수열의 길이를 구하여라.
 * 문제 유형 : DP.
 * <풀이 요약>
 * 1. 해당 배열에 저장된 수들의 길이가 곧 LIS인 1차원 dp테이블을 선언한다.
 * 2. 다음과 같은 방식으로 dp테이블을 갱신한다.
 * -> 현재 dp테이블에 저장된 수들에 대해서, 이분 탐색을 진행하면서 현재 선택한 수가 삽입될 수 있는 위치를 찾는다.
 * -> 삽입될 수 있는 위치란, 현재 수보다 큰 수들 중 가장 작은 값의 위치와 교체하는 것을 말한다.
 * -> 만일, 그런 위치가 없다면 dp테이블에 숫자를 새로 넣는다.
 * 3. 최종 dp테이블에 저장된 수들의 길이를 출력한다.
 */

public class BOJ_S2_11053_가장긴증가하는부분수열_재2 {
	static int N;
	static int[] arr, dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		dp = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int LIS = 0;
		// 배열의 가장 앞의 수부터 차례로 dp 점화식에 따라 dp 테이블을 갱신한다.
		for(int i = 0; i < N; i++) {
			// 이분 탐색을 이용하여 해당 수가 dp테이블에 삽입될 위치를 찾는다.
			int idx = BinarySearch(arr[i], 0, LIS);
			
			// 만일, 결과가 -1이라면 삽입할 공간이 없으므로 수를 추가해주고 LIS를 + 1한다.
			if(idx == -1)
				dp[LIS++] = arr[i];
			// 그렇지 않다면, 해당 위치에 수를 삽입한다.
			else
				dp[idx] = arr[i];
		}
		System.out.println(LIS);
		sc.close();
	}

	private static int BinarySearch(int num, int start, int end) {
		int res = -1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			// 만일, 현재 입력 받은 수가 현재 비교하는 수보다 작거나 같다면
			if(num <= dp[mid]) {
				// 해당 위치를 우선 기억해둔다(삽입할 위치).
				res = mid;
				// 더 작은 범위를 탐색한다.
				end = mid - 1;
			}
			// 그렇지 않다면
			else {
				// 더 큰 범위를 탐색한다.
				start = mid + 1;
			}
		}
		
		return res;
	}
}
