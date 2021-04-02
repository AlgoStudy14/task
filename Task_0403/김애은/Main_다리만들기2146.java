package algo0402;

import java.util.Scanner;

public class Main_다리만들기2146 {
	static int dx[]= {0,0,-1,1};
	static int dy[]= {1,-1,0,0};
	static int N, min=Integer.MAX_VALUE;
	static int arr[][];
	static boolean V[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		arr=new int [N][N];
		V=new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]==1)
					arr[i][j]=-1;
			}
		}
		int cnt=1;
		for(int i=0;i<N;i++) {//섬 분리 코드
			for(int j=0;j<N;j++) {
				if(arr[i][j]==-1) {
					dfs(i,j,cnt);
					cnt++;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]!=0) {
					V[i][j]=true;
					dfs2(i,j,arr[i][j],0);
					V[i][j]=false;
				}
			}
		}
		System.out.println(min);
	}
	private static void dfs2(int y, int x, int k,int cnt) {
		
		if(min<cnt)return;
		
		for(int d=0;d<4;d++) {
			int my=y+dy[d];
			int mx=x+dx[d];
			if(mx<0||my<0||mx>=N||my>=N)continue;
			if(V[my][mx])continue;
			if(arr[my][mx]==0) {
				V[my][mx]=true;
				dfs2(my,mx,k,cnt+1);
				V[my][mx]=false;
			}
			else if(arr[my][mx]==k)
				continue;
			else {
				min=Math.min(cnt, min);
			}
		}
		
	}
	private static void dfs(int i, int j, int cnt) {
		arr[i][j]=cnt;
		
		for(int d=0;d<4;d++) {
			int my=i+dy[d];
			int mx=j+dx[d];
			if(mx<0||my<0||mx>=N||my>=N)continue;
			if(arr[my][mx]==-1)
				dfs(my,mx,cnt);
		}
		
	}

}
