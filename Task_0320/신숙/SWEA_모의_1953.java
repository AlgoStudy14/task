package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_모의_1953 {
	/*
	 * <구조물 타입>
	 * 1 : + 	2 : ㅣ 	3 : ㅡ	4 : ㄴ	5 : ┌	6 : ㄱ	7 : ┘ 
	 * 	 
	 * <문제>
	 * 범인 탈출했음 
	 * 맨홀의 위치가 주어지고, 이 위치로부터 해당하는 시간이 지났을때 도둑이 있을 수 있는 위치의 개수
	 * 파이프가 연결되었을때만 이동가능.
	 * 
	 * <풀이법>
	 *  위쪽을 체크할 때	 	:	 현재 위치의 파이프가 위쪽으로 열려있고 다음 위치의 파이프가 아래쪽으로 열려있으면 이동 가능
	 *	아래쪽을 체크할 때 	:	 현재 위치의 파이프가 아래쪽으로 열려있고 다음 위치의 파이프가 위쪽으로 열려있으면 이동 가능
	 *	왼쪽을 체크할 때 		:	 현재 위치의 파이프가 왼쪽으로 열려있고 다음 위치의 파이프가 오른쪽으로 열려있으면 이동 가능
	 *	오른쪽을 체크할 때 	:	 현재 위치의 파이프가 오른쪽으로 열려있고 다음 위치의 파이프가 왼쪽으로 열려있으면 이동 가능
	 * 
	 * 
	 * 	 
	 * 시뮬레이션 너무 어려움...
	 *  #1 7
		#2 15
		#3 29
		#4 75
		#5 77
		
		틀림	=>	몇 개만 더 많이나오네
	 */
	static int T, N, M, R, C, L, ans;
	static int[][] map;
	static Queue<Cood> q;
	static int[] dr = {-1, 1, 0, 0 };		//위 아래 좌 우
	static int[] dc = {0, 0, -1, 1 };
	static boolean[][] visit;
	static class Cood{
		int x, y, time;
		Cood(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.time = d;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			visit = new boolean[N][M];
			ans = 1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++)
					map[i][j] = sc.nextInt();
			}
			bfs();
			System.out.println("#" + t + " " + ans);
		}
	}
	static void bfs() {
		q = new LinkedList<Cood>();
		q.offer(new Cood(R, C, 0));
		visit[R][C] = true;
		Cood c;
		int cur;
		while(!q.isEmpty()) {
			c = q.poll();
			cur = map[c.x][c.y];
			if(c.time >= L)
				break;
			int nr, nc;
			for(int i = 0; i < 4; i++) {
				nr = c.x + dr[i];
				nc = c.y + dc[i];
				
				if((nr < 0 || nr >= N || nc < 0 || nc >= M) || visit[nr][nc] || map[nr][nc] == 0)
					continue;
				
				int nt = map[nr][nc];
				switch(i) {
				case 0:			//위
					if(cur == 1 || cur == 2 || cur == 4 || cur == 7) {
						if(nt == 1 || nt == 2 || nt == 5 || nt == 6) {
							visit[nr][nc] = true;
							q.offer(new Cood(nr, nc, c.time+1));
							ans++;
						}
					}
					break;
				case 1:			//아래
					if(cur == 1 || cur == 2 || cur == 5 || cur == 6) {
						if(nt == 1 || nt == 2 || nt == 4 || nt == 7) {
							visit[nr][nc] = true;
							q.offer(new Cood(nr, nc, c.time+1));
							ans++;
						}
					}
					break;
				case 2:			//좌
					if(cur == 1 || cur == 3 || cur == 6 || cur == 7) {
						if(nt == 1 || nt == 3 || nt == 4 || nt == 5) {
							visit[nr][nc] = true;
							q.offer(new Cood(nr, nc, c.time+1));
							ans++;
						}
					}
					break;
				case 3:			//우
					if(cur == 1 || cur == 3 || cur == 4 || cur == 5) {
						if(nt == 1 || nt == 3 || nt == 6 || nt == 7) {
							visit[nr][nc] = true;
							q.offer(new Cood(nr, nc, c.time+1));
							ans++;
						}
					}
					break;
				}
			}
		}
	}
}
