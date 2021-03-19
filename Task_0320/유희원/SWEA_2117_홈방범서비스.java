package sw_a_prac;

import java.util.Scanner;

public class SWEA_2117_홈방범서비스 {

	static int T, N, M, max;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			max = 0;
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
					calc(i,j);
				}
			}
			System.out.println("#"+t+" "+max);
		}

	}
	private static void calc(int a, int b) {
		//k가 n*n칸을 모두 채우는 범위까지 진행????
		for (int k = 0; k < N+2; k++) {
			int home = 0;
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
					int nx = Math.abs(i-a);
					int ny = Math.abs(j-b);
					//현재 k일때의 서비스 영역이고 집이면
					if(nx+ny < k && map[i][j] == 1) home++;
				}
			}
			//이익
			int profit = (home*M)-(k*k+(k-1)*(k-1));
			if(profit<0) continue;
			//손해가 아니면 집의 수를 최댓값으로 갱신
			else max = Math.max(home, max);
		}
		
	}

}
