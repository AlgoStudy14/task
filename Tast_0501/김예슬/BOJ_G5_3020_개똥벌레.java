package algo0425;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간의 개수를 출력
 * 제약 사항 : N <= 200,000, H <= 500,000
 * 문제 유형 : 누적합 사용
 * <풀이법 요약>
 * 1. 석순과 종유석을 나눠서 계산
 * 2. 석순과 종유석 각각 높이에 따른 개수를 저장
 * 3. 높이에 따른 파괴해야할 장애물의 개수 누적합 계산
 * 4. 개똥벌레가 파괴해야하는 장애물의 최솟값을 구한 뒤 구간의 개수를 구함
 * 
 * c.f) 그냥 계산 시 시간초과.. 모르겠어서 구선생의 힘을 빌려서 풂😇
 */

public class BOJ_G5_3020_개똥벌레 {

	static int N, H;
	static int[] bottom, top;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		H = sc.nextInt();
		int[] bCnt = new int[H];
		int[] tCnt = new int[H];
		
		for (int i = 0; i < N / 2; i++) {
			++bCnt[sc.nextInt() - 1];
			++tCnt[sc.nextInt() - 1];
		}
		
		// 높이에 따른 파괴해야할 석순과 종유석의 개수
		int[] bottom = new int[H];
		int[] top = new int[H];
		
		bottom[H - 1] = bCnt[H - 1];
		for (int i = H - 2; i >= 0; i--) 
			bottom[i] = bottom[i + 1] + bCnt[i];
		
		top[0] = tCnt[H - 1];
		for (int i = 1; i < H; i++) 
			top[i] = top[i - 1] + tCnt[H - i - 1];
		
		// 개똥벌레가 파괴해야하는 장애물의 최솟값
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			if (min > bottom[i] + top[i])
				min = bottom[i] + top[i];
		}
		
		// 개똥벌레가 파괴해야하는 장애물의 최솟값 구간의 개수
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			if (min == bottom[i] + top[i]) ++cnt;
		}
		
		System.out.println(min + " " + cnt);
	}

}
