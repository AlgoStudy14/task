package algo0731;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만드는 두 용액을 출력
 * 문제 유형 : 이분탐색
 * <풀이 요약>
 * 전에 풀었던 문제 같은데ㅋㅋㅋㅋ
 * 1. 양 끝 지점에서부터 두 용액의 합을 구해나감
 * 2. 용액의 합의 절대값이 이전에 구한 용액의 합보다 작을 경우 갱신
 * 3. 용액의 합이 0보다 클 경우 end 포인터의 위치를 옮기고
 *    용액의 합이 0보다 작을 경우 start 포인터의 위치를 옮김
 */

public class BOJ_G5_2467_용액 {

	static int N;
	static int[] solution, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		solution = new int[N];
		ans = new int[2];
		
		for (int i = 0; i < N; i++) {
			solution[i] = sc.nextInt();
		}
		
		binarySearch();
		
		System.out.println(ans[0] + " " + ans[1]);
	}

	static void binarySearch() {
		int start = 0;
		int end = N - 1;
		int sum = Integer.MAX_VALUE;
		
		while (start < end) {
			int tmp = solution[start] + solution[end];
			
			if (Math.abs(tmp) < sum) {
				ans[0] = solution[start];
				ans[1] = solution[end];
				sum = Math.abs(tmp);
			}
			
			if (tmp > 0) --end;
			else ++start;
		}
	}
}
