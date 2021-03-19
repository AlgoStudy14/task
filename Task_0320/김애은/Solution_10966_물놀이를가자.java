//bfs문제
//땅에서 물로 가는데 최소한의 거리의 합을 구하라
//처음 모든 배열을 -1로 초기화한다. 그리고 W를 만나면 0으로 바꾸고 그 좌표를 queue에 넣어준다.
//이 작업을 통해서 처음에 물의 위치들을 queue에 넣는다.
//bfs코드를 사용해 물에서 바로 상하좌우에 있을 경우 1을 넣어준다
//0을 다 하고나면 1인 부분들로 가고 해당 칸에 +1을 하여 넣어준다
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int T,N,M;
	static int V[][];
	static Queue<int[]> que;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N=sc.nextInt();
			M=sc.nextInt();
			V=new int[N][M];
			int sum=0;
			que = new LinkedList<int[]>();
			for(int a[]:V)
				Arrays.fill(a, -1);
			for(int n=0;n<N;n++) {
				String s = sc.next();
				for(int m=0;m<M;m++) {
					if(s.charAt(m)=='W') {
						V[n][m]=0;
						que.offer(new int[] {n,m});
						}
					}
				}

			bfs();
			for(int n=0;n<N;n++) {
				for(int m=0;m<M;m++) {
					sum+=V[n][m];
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}
	private static void bfs() {
		int dx[]= {-1,1,0,0};
		int dy[] = {0,0,-1,1};
		while(!que.isEmpty()) {
			int p[] = que.poll();
			int n=p[0];
			int m=p[1];
			int d=0;
			while(d<4) {
				int mx=n+dx[d];
				int my=m+dy[d];
				d+=1;
				if(mx>-1&&my>-1&&mx<N&&my<M &&V[mx][my]==-1) {
					V[mx][my]=V[n][m]+1;
					que.offer(new int[] {mx,my});
				}
			}
		}
		
	}

}
