package sw_a_prac;

import java.util.Scanner;
/*
 * 식재료 i와 j를 같이 요리했을때 발생하는 시너지 Sij를 담은 배열 주어짐
 * Sij != Sji
 * N개의 식재료를 절반씩 나누어 요리 두개 만들 때, 그 맛의 차이가 최소가 되도록 배분
 * 맛의 차이 최솟값 출력
 */
public class SWEA_4012_요리사 {

	static int T, N;
	static int[][] bake;
	static boolean[] v;
	static int min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			N = sc.nextInt();
			bake = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bake[i][j] = sc.nextInt();
				}
			}
			v = new boolean[N];
			min = Integer.MAX_VALUE;
			nCr(0,0);
			System.out.println("#"+t+" "+min);
		}

	}
	private static void nCr(int cnt, int start) {
		//절반 고르면 고른애는 choice로, 안고른애는 unchoi
		if(cnt == N/2) {
			int[] choice = new int[N/2];
			int[] unchoi = new int[N/2];
			//choice, unchoi 인덱스로 사용할 변수
			int a = 0;
			int b = 0;
			for (int i = 0; i < N; i++) {
				//
				if(v[i]) choice[a++] = i; 
				else unchoi[b++] = i;
			}
			//시너지 
			int A = 0;
			int B = 0;
			
			for (int i = 0; i < choice.length; i++) {
				for (int j = 0; j < choice.length; j++) {
					if(i!=j) {
						A+=bake[choice[i]][choice[j]];
						B+=bake[unchoi[i]][unchoi[j]];
					}
				}
			}
			//맛 차이 최소 저장
			min = Math.min(min, Math.abs(A-B));
			return;
		}
		//조합 선택 하고 안하고 재귀
		for (int i = start; i < N; i++) {
			if(v[i]) continue;
			v[i] = true;
			nCr(cnt+1,i+1);
			v[i] = false;
		}
		
	}

}
