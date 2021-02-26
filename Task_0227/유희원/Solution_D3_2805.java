package algol0203;

import java.util.Arrays;
import java.util.Scanner;

//'9'-'0' char 9 를 int 9로 바꾸는거
//integer.parseint('9'+"")

public class SWEA_2805 {

	static int T,N;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				char[] scc = s.toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = scc[j] - '0';
				}
			}
			int r = sum();
			System.out.println("#"+t+" "+r);
			
		}
	}
	private static int sum() {
		int tot = 0;
		//N*N 사각형에서 다이아몬드 내부의 숫자들의 합
		for (int i = 0; i < N; i++) {
			
			//여기 N/2 해야되는데 그냥 2 해서 틀림ㅡㅡ
			for (int j = Math.abs(i-N/2); j < N-(Math.abs(i-N/2)); j++) {
				//System.out.print(map[i][j]+" ");
				tot+=map[i][j];
			}
		}
		
		
		return tot;
	}

}
