package algo0405;
/*
 * 쉬운문제.. 실버는 이제 좀 푸나
 * 완탐!
 * 사실 annotation을 쓰면 훨씬 빨라질거같다.
 * 일단통과
 * dfs로 주변 다 돌고 섬 개수 세기
 */
import java.util.Scanner;

public class Main_bae_2468 {
	static int arr[][];
	static int check[][];
	static int N;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		arr=new int[N][N];
		int arrmax=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j]=sc.nextInt();
				arrmax=Math.max(arr[i][j], arrmax);
			}
		}
		for(int i=1;i<arrmax;i++) {
			check=new int[N][N];
			int temp=change(i);
			max=Math.max(temp, max);
		}
		if(arrmax==1)System.out.println(1);//이부분 중요
		/*
5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1 
		 */
		else System.out.println(max);
	}
	private static void count(int y,int x,int cnt) {
		int dy[]= {0,0,-1,1};
		int dx[] = {1,-1,0,0};
		check[y][x]=cnt;
		for(int d=0;d<4;d++) {
			int my=y+dy[d];
			int mx=x+dx[d];
			if(mx<0 || my<0 || mx>=N||my>=N)continue;
			if(check[my][mx]==-1)
				count(my,mx,cnt);
		}
		
	}
	private static int change(int limit) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]>limit)
					check[i][j]=-1;
			}
		}
		int cnt=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(check[i][j]==-1) {
					count(i,j,cnt);
					cnt++;
				}
			}
		}
		return cnt-1;
	}

}
