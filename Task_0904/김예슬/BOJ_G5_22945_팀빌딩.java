package algo0829;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : N명이 팀 빌딩을 할 때 팀의 능력치 최댓값을 출력
 * 문제 유형 : 투포인터
 * <풀이 요약>
 * 1. 개발자 배열의 시작점과 끝점을 가르킴
 * 2. 개발자끼리 능력치를 비교
 *  2-1. 만약 왼쪽(start)가 더 작다면 오른쪽으로 한 칸 이동
 *  2-2. 만약 오른쪽(end)가 더 작다면 왼쪽으로 한 칸 이동
 * 3. 포인터가 만나게 되면 종료 후 최댓값 출력
 * <피드백>
 * 완전 삽질하다가 투포인터로 푸니까 금방 풂,,, 한 번에 안떠올라서 어려웠다
 */

public class BOJ_G5_22945_팀빌딩 {

	static int N, max;
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		
		solve();
		System.out.println(max);
	}

	static void solve() {
		int start = 0;
		int end = N - 1;
		
		while (start < end) {
			int min = Math.min(num[start], num[end]);
			max = Math.max(max, (end - start - 1) * min);
			
			if (num[start] < num[end]) ++start;
			else --end;
		}
	}
}
