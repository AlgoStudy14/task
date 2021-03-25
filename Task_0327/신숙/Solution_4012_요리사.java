import java.util.Scanner;

public class Solution_4012_요리사 {
	static int T, N, R, answer, sumF, sumS;
	static int[][] ingredient;
	static int[] orderF;
	static int[] orderS;
	static int[] index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			R = N/2;
			orderF = new int[R];
			orderS = new int[R];
			answer = Integer.MAX_VALUE;
			ingredient = new int[N+1][N+1];
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++)
					ingredient[i][j] = sc.nextInt();
			}
			orderF[0] = index[0];
			nCr(1, 1);
			System.out.println("#" + t + " " + answer);
		}
	}
	static void nCr(int cnt, int start) {
		if(cnt == R){
			int ind = 0, sumF = 0, sumS = 0;
			//두번째 배열에 남은 값 넣기.
			for(int i = 0; i < N; i++) {
				boolean flag = false;
				for(int j = 0; j < R; j++) {
					if(orderF[j] == index[i]) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					orderS[ind] = index[i];
					ind++;
				}
			}
			//더하기
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < R; j++) {
					sumF += ingredient[orderF[i]][orderF[j]];
					sumS += ingredient[orderS[i]][orderS[j]];
				}
			}
			answer = Math.min(answer, Math.abs(sumF - sumS));
			
			return;
		}
		for(int i = start; i < N; i++) {
			orderF[cnt] = index[i];
			nCr(cnt+1, i+1);
		}
	}
}