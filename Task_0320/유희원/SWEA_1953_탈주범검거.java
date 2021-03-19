package sw_a_prac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 파이프 모양 (1~7)
 * 한시간마다 위치 이동하는데 그자리 그대로 있을수도있음
 * L시간 이후 탈주범이 있을 수 있는 위치 개수
 */
public class SWEA_1953_탈주범검거 {
	public static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	//상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map;
	static boolean[][] visited;
	static int T, N, M, R, C, L, ans;
	static Queue<Node> que;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			//터널 지도 세로크기
			N = sc.nextInt();
			//가로크기
			M = sc.nextInt();
			//맨홀 세로위치
			R = sc.nextInt();
			//맨홀 가로위치
			C = sc.nextInt();
			//탈출 후 소요된 시간
			L = sc.nextInt();
			que = new LinkedList<>();
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 1;
			//지도 입력받기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			bfs();
			System.out.println("#"+t+" "+ans);
		}
	}
	private static void bfs() {
		int time = 0;
		que.offer(new Node(R,C));
		visited[R][C] = true;
		
		while(!que.isEmpty()) {
			//이거 변수선언 안하고 그냥 for문에다가 i<que.size()했더니 테케 안맞음;;;;
			int size = que.size();
			if(++time>=L) return;
			
			for (int i = 0; i <size; i++) {
				Node current = que.poll();
				int type = map[current.r][current.c];
				for (int d = 0; d <4; d++) {
					int nr = current.r+dr[d];
					int nc = current.c+dc[d];
					
					//범위안에있고
					if(nr>=0&&nc>=0&&nr<N&&nc<M) {
						//방문안했고 0이 아니면(터널이면)
						if(!visited[nr][nc]&&map[nr][nc]!=0) {
							int next = map[nr][nc];
							switch(d) {
							case 0:
								//현재 위치의 파이프가 위쪽으로 열려있고 다음 위치의 파이프가 아래쪽으로 열려있으면 이동 가능
								if(type == 1 || type == 2 || type == 4 || type == 7) {
									if(next == 1 || next == 2 || next == 5 || next == 6) {
										visited[nr][nc] = true;
										que.offer(new Node(nr,nc));
										ans++;
									}
								}
								break;
							case 1:
								//현재 위치의 파이프가 아래쪽으로 열려있고 다음 위치의 파이프가 위쪽으로 열려있으면 이동 가능
								if(type == 1 || type == 2 || type == 5 || type == 6) {
									if(next == 1 || next == 2 || next == 4 || next == 7) {
										visited[nr][nc] = true;
										que.offer(new Node(nr,nc));
										ans++;
									}
								}
								break;
							case 2:
								//현재 위치의 파이프가 왼쪽으로 열려있고 다음 위치의 파이프가 오른쪽으로 열려있으면 이동 가능
								if(type == 1 || type == 3 || type == 6 || type == 7) {
									if(next == 1 || next == 3 || next == 4 || next == 5) {
										visited[nr][nc] = true;
										que.offer(new Node(nr,nc));
										ans++;
									}
								}
								break;
							case 3:
								//현재 위치의 파이프가 오른쪽으로 열려있고 다음 위치의 파이프가 왼쪽으로 열려있으면 이동 가능
								if(type == 1 || type == 3 || type == 4 || type == 5) {
									if(next == 1 || next == 3 || next == 6 || next == 7) {
										visited[nr][nc] = true;
										que.offer(new Node(nr,nc));
										ans++;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		
	}
}
