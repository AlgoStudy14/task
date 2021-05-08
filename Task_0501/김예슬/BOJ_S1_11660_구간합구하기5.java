package algo0424;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : (x1, y1)부터 (x2, y2)의 합을 구해 출력
 * 문제 유형 : prefix sum 사용
 * <풀이법 요약>
 * 0. ps 배열에 누적합 저장
 * 1. 누적합을 이용해 원하는 칸의 합을 구함
 */

public class BOJ_S1_11660_구간합구하기5 {

	static int N, M;
	static int[][] arr, ps;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N + 1][N + 1];
		ps = new int[N + 1][N + 1];
		
		// 입력
		for (int i = 0; i < N * N; i++)
			arr[i / N + 1][i % N + 1] = sc.nextInt();
		
		// DP
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + arr[i][j];
			}
		}
		
		// (fx, fy) ~ (tx, ty)의 합을 구해 출력
		for (int i = 0; i < M; i++) {
			int fx = sc.nextInt();
			int fy = sc.nextInt();
			int tx = sc.nextInt();
			int ty = sc.nextInt();
			
			System.out.println(ps[tx][ty] - ps[fx -1][ty] - ps[tx][fy - 1] + ps[fx - 1][fy - 1]);
		}
	}

}
