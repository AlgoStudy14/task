package algo0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 문제 정의 : M개의 카드에 대해 상근이가 갖고 있는지 출력
 * 문제 유형 : 이분탐색
 * 주의 사항 : 이분탐색 시 log(50만) = 18
 * <풀이 요약>
 * 1. 이분탐색을 위해 카드 정렬
 * 2. mid 값을 기준으로 찾아야 할 숫자와 비교
 * 	2-1. mid보다 클 경우 start = mid + 1로 갱신
 *  2-2. mid보다 작을 경우 end = mid - 1로 갱신
 *  2-3. mid와 같으면 찾을 숫자가 존재하므로 탐색 종료
 * <피드백>
 * 시초 why...????🤔
 */

public class BOJ_S4_10815_숫자카드_이분탐색 {

	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
				
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			if (binarySearch(target))
				System.out.print(1 + " ");
			else
				System.out.print(0 + " ");
		}
	}

	static boolean binarySearch(int target) {
		int start = 0, end = N - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] < target) start = mid + 1;
			else if (arr[mid] > target) end = mid - 1;
			else return true;
		}
		
		return false;
	}
}
