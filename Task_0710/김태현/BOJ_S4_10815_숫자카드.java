import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 상근이가 가지고 있는 숫자 카드에 주어진 카드가 포함되어 있는지 파악하라.
 * 문제 유형 : 이분 탐색.
 * <풀이 요약>
 * 1. 상근이의 카드를 오름차순으로 정렬한다.
 * 2. 각 수를 상근이의 카드 배열을 이분 탐색하여 포함되어 있는지 판단한다.
 * <피드백>
 * 1. StringBuilder를 사용하면 무려 3배나 빨라진다.
 * 2. 이분 탐색 범위 지정
 * (1) 양 끝 인덱스를 초기 값으로 지정한다.
 * (2) 중앙 값을 제외하고 탐색을 진행한다(mid + 1, mid - 1).
 * 3. 재귀와 반복문은 취향이다.
 */

public class BOJ_S4_10815_숫자카드 {
	static int N, M;
	static int[] sang;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sang = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sang[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sang);

		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			if (binarySearch(Integer.parseInt(st.nextToken()))) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}
		System.out.println(sb);
	}

	private static boolean binarySearch(int num) {
		// 시작, 끝 구간 설정(인덱스 범위 그대로).
		int s = 0, e = N - 1;
		int mid;
		
		while(s <= e) {
			// 중앙 값.
			mid = (s + e) / 2;
			
			// 중앙 값과 같은 경우 true 리턴. 
			if(sang[mid] == num) {
				return true;
			} 
			// 중앙 값 보다 큰 경우 큰 범위 탐색
			else if(sang[mid] < num) {
				// 중앙 값을 제외하고 탐색.
				s = mid + 1;
			}
			// 중앙 값 보다 작은 경우 작은 범위 탐색.
			else {
				// 중앙 값을 제외하고 탐색.
				e = mid - 1;
			}
		}
		// 반복문 안에서 숫자를 찾지 못했으면 false 리턴.
		return false;
	}
}
