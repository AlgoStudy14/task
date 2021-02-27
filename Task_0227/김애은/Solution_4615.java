package algo_myselr;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_4615 {
	static int T;
	static int arr[][];
	static int whi,bla,N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			int M = sc.nextInt();
			//v=new boolean[N][N];
			arr=new int[N][N];
			int n=N/2;
			arr[n-1][n-1]='W';
			arr[n][n]='W';
			arr[n-1][n]='B';
			arr[n][n-1]='B';
			whi=2;
			bla=2;
			for(int i=0;i<M;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				int c=sc.nextInt();
				arr[x-1][y-1]=c;
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++)
				check(i,j,arr[i][j]);
			}
			System.out.println("#"+t+" "+bla+" "+whi);
		}
	}
	
	private static void check(int x,int y, int chec) {
		int d=0;
		int dx[] = {0,1,0,-1,1,1,-1,-1};
		int dy[] = {1,0,-1,0,-1,1,-1,1};
		arr[x][y]=chec;
		if(chec==1)bla++;
		else whi++;
		while(d<8) {
			int r=x+dx[d];
			int c=y+dy[d];
			if(r>=0 && r<N && c>=0 && c<N) {
				if (arr[r][c] != chec && arr[r][c] != 0) {
					int mx = r + dx[d];
					int my = c + dy[d];
					if (mx >= 0 && mx < N && my >= 0 && my < N) {
						if (arr[mx][my] == chec)
							go(r, c, chec);
					}
				}
			}
			d++;
		}
	}
	

	private static void go(int r, int c, int chec) {
		System.out.println("go");
		if(chec==1) {
			arr[r][c]=1;
			bla+=1;
			whi-=1;
		}
		else {
			arr[r][c]=2;
			bla-=1;
			whi+=1;
		}
	}

}
