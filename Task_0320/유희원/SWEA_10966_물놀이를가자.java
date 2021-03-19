package sw_a_prac;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * L에서 W로 가는 최소 거리 다 더하기
 * W부터 BFS 해서 다 더함
 */
//BFS
public class SWEA_10966_물놀이를가자 {

	static int T, N, M, res;
	static int[][] ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Water{
		int r;
		int c;
		int cnt;
		public Water(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			//map = new char[N][M];
			ans = new int[N][M];
			for (int i = 0; i <N; i++) {
				Arrays.fill(ans[i], N+M);
			}
			Queue<Water> que = new LinkedList<>();
			res = 0;
			for (int i = 0; i <N; i++) {
				String s = sc.next();
				for (int j = 0; j <M; j++) {
					//물 위치 받아둠
					if(s.charAt(j) == 'W') {
						ans[i][j] = 0;
						//물인 부분은 0으로 
						que.add(new Water(i,j,0));
					}
				}
			}
			
			while(!que.isEmpty()) {
				Water w = que.poll();
				for (int d = 0; d <4; d++) {
					int nr = w.r + dr[d];
					int nc = w.c + dc[d];
					//범위 내에 있고 ans에 있던 값이 w의 cnt+1보다 크면
					if(nr>=0&&nc>=0&&nr<N&&nc<M && ans[nr][nc]>(w.cnt+1)) {
						//갱신
						ans[nr][nc] = w.cnt+1;
						que.add(new Water(nr,nc,w.cnt+1));
					}
				}
			}
			//다 더해줌
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <M; j++) {
					res+=ans[i][j];
				}
			}
			System.out.println("#"+t+" "+res);
		}

	}
	

}
