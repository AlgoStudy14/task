package Jungol;

import java.util.Scanner;

public class JUNGOL_1707 {
	/*
	 * 이전에 풀었던 달팽이 처럼 정사각형 배열을 돌면서 수를 채우는 문제 
	 */
	static int[][] snail;
	static int N;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		snail = new int[N][N];
		int r = 0;
		int c = 0;
		int d = 0;
		for(int i = 1; i <= N * N; i++) {
			snail[r][c] = i;
			if(r + dr[d] >= 0 && c + dc[d] >= 0 && r + dr[d] < N && c + dc[d] < N && snail[r + dr[d]][c+dc[d]] == 0) {
				r += dr[d];
				c += dc[d];
			}else {
				d = (d + 1) % 4;
				r += dr[d];
				c += dc[d];
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				System.out.print(snail[i][j] + " ");
			System.out.println();
		}
	}
}
