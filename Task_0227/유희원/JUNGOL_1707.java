package imprepare.problems;

import java.util.Scanner;

public class JO_1707_달팽이사각형 {

	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[][] arr;
	static int r,c,dir;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		
		for (int i = 0; i <N; i++) {
			for (int j = 0; j <N; j++) {
				arr[r][c] = i*N+j+1;
				if(!(check(r+dr[dir],c+dc[dir])&&arr[r+dr[dir]][c+dc[dir]]==0)) dir = (dir+1)%4;
				r+=dr[dir];
				c+=dc[dir];
			}
		}
		for (int i = 0; i <N; i++) {
			for (int j = 0; j <N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	}
	private static boolean check(int i,int j) {
		// TODO Auto-generated method stub
		return i>=0&&j>=0&&i<N&&j<N;
	}

}
