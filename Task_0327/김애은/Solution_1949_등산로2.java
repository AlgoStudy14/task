package algo0326;

//가장 큰것부터 시작하는 DFS
//k만큼 자를 수 있다. 여기서 조심할거는 k만큼이 아니라 1~k만큼이라는거

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1949_등산로2 {
	static int T,N;
	static int [][] arr;
	static int max=Integer.MIN_VALUE;
	static Queue<int[]> que = new LinkedList<int[]>();
	static boolean v[][];
	static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<=T;t++) {
			answer=0;
			N = sc.nextInt();
			int K = sc.nextInt();
			arr = new int[N][N];
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
					if (max < arr[i][j])
						max = arr[i][j];
				}
			}
			for (int i = 0; i < N; i++) {// 최대 크기 가진애들
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == max) {
						que.add(new int[] { i, j });
					}
				}
			}
			while (!que.isEmpty()) {//여기를 바꿔주면 된다.
				int p[] = que.poll();
				dfs(p[0], p[1], K, 1);
			}
			System.out.println("#"+t+" "+answer);
		}
	}
	private static void dfs(int my,int mx,int K,int cnt) {
		
		int dx[]= {-1,1,0,0};
		int dy[]= {0,0,1,-1};
		for(int i=0;i<4;i++) {
			int x=mx+dx[i];
			int y=my+dy[i];
			if(x<0 || y<0 ||x>=N||y>=N)continue;
			if(v[y][x])continue;
			if(arr[my][mx]>arr[y][x]) {
				v[y][x]=true;
				dfs(y,x,K,cnt+1);
				v[y][x]=false;
			}else {
				if(K>0 && (arr[y][x] - K < arr[my][mx])) {
					for(int dep=1;dep<=K;dep++) {
						int temp = arr[y][x];
						if (arr[y][x] - dep < arr[my][mx]) {
							arr[y][x] = arr[y][x] - dep;
							v[y][x] = true;
							dfs(y, x, 0, cnt + 1);
							v[y][x] = false;
							arr[y][x] = temp;
						}
					}
				}
			}
		}
		answer=Math.max(answer, cnt);
	}

}
