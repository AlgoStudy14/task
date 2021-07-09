package algo0709;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 오븐의 맨 위에 위치한 피자 반죽의 위치를 출력
 * 문제 유형 : 이분탐색
 * 주의 사항 : 
 * <풀이 요약>
 * 브루트 포스로 문제를 풀 경우, O(DN)로 시간초과
 * 1. 위에서부터 넣어야 하기 때문에 아래로 갈 수록 좁아지는 모양으로 오븐 재정의 가능
 * 2. 오븐을 재정의하면 자동으로 내림차순으로 정렬 되므로 이분탐색 가능
 * 3. 이분탐색을 하며 반죽이 들어갈 수 있는 위치(idx)가 변경될 경우 now에 갱신
 */

public class BOJ_G5_1756_피자굽기 {

	static int D, N, now;
	static int[] oven;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		D = sc.nextInt();
		N = sc.nextInt();
		oven = new int[D];
		
		for (int i = 0; i < D; i++) {
			if (i == 0)
				oven[i] = sc.nextInt();
			else
				oven[i] = Math.min(oven[i - 1], sc.nextInt());
		}
		
		now = D;
		for (int i = 0; i < N; i++) {
			// 반죽을 오븐에(상단 ~ 현재 피자가 있는 곳의 위) 넣을 수 있는지 체크
			binarySearch(sc.nextInt(), 0, now - 1);
			if (now == -1) break;
		}
		
		// 오븐의 인덱스로 위치를 구분했기 때문에 1 더해줌
		System.out.println(now + 1);
	}

	static void binarySearch(int target, int top, int bottom) {
		// 피자를 넣을 수 있는지 없는지 체크하기 위해 idx 변수 사용
		int idx = -1;
		
		while (top <= bottom) {
			int mid = (top + bottom) / 2;
			if (oven[mid] >= target) {
				// 반죽이 오븐보다 작거나 같을 경우
				idx = mid;
				top = mid + 1;
			} else bottom = mid - 1;
		}

		if (idx == -1) now = -1;	// 반죽을 넣을 수 없는 경우
		else now = idx;				// 반죽을 넣을 수 있는 경우
	}
}
