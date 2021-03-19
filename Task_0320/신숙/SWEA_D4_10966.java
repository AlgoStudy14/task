package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_D4_10966 {
	/*
	 * 2차원 배열에서 L에서 W까지 거리들의 합을 구하는 문제.
	 * 
	 * 앵간하면 W가 더 적은 거 같으니 W에서부터 거리들을 구하면 될 것같은데.
	 * 		=> w 위치는 queue로 받아서. 
	 * 1) DFS로 2개의 배열을 두고, 한개는 문자, 한개는 w에서부터의 거리를 더 작다면 계속 갱신하면서?
	 * 		=> 댓글에 시간초과가 있다는데 bfs로?
	 * 2) bfs?
	 * 		=> W위치들을 받아두면 완전탐색 안 하고 bfs로 해도될듯.
	 * 
	 * <오류>
	 * 1. 1번 테케 답 4나옴.
	 * 2. 2번 테케 Arrayout~		=> check에서 M이아니라 N으로 비교해서.	=> 2번 테케는 답 제대로 나옴.
	 * 		ㄴ 이거 때문에 1번도 답이 틀렸던거네.
	 */
	static class Water{
		int r, c, distance;
		Water(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.distance = d;
		}
	}
	
	static int T, N, M, answer;
	static char[][] map;
	static int[][] dist;
	static int[] dr = {1, 0, -1, 0 };
	static int[] dc = {0, 1, 0, -1 };
	static Queue<Water> q;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T= sc.nextInt();
		for(int t = 1; t <= T; t++) {
			//전역변수들 초기화
			q = new LinkedList<Water>();
			N = sc.nextInt();
			M = sc.nextInt();
			answer = 0;
			map = new char[N][M];
			dist = new int[N][M];
			String s = "";
			//배열에 입력.
			for(int i = 0; i < N; i++) {
				s = sc.next();
				for(int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					//W위치를 미리 받고 해버리니까 map이 필요가없네? 
					if(map[i][j] == 'W') {
						dist[i][j] = -1;
						q.offer(new Water(i, j, 0));
					}
				}
			}
			/*
			System.out.println("ㅡㅡㅡㅡㅡdist 배열ㅡㅡㅡㅡ");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					System.out.print(dist[i][j] + " ");
				}System.out.println();
			}
			System.out.println();
			System.out.println("ㅡㅡㅡㅡㅡmap 배열ㅡㅡㅡㅡ");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}System.out.println();
			}*/
			//배열엔 맞게 들어갔으니까 아래 bfs에서 틀린건데.
			bfs();
			System.out.println("#" + t + " " + answer);
		}
	}
	static void bfs() {
		Water w;
		while(!q.isEmpty()) {
			//w위치들을 받아서 4방탐색.
			w = q.poll();
			int nr, nc;
			for(int i = 0; i < 4; i++) {
				nr = w.r + dr[i];
				nc = w.c + dc[i];
				//거리 벗어나면 패스~		+ 1 추가 : dist배열에서 물인건 넘겨야함.
				if(isCheck(nr, nc) || dist[nr][nc] == -1)
					continue;
				if(dist[nr][nc] != 0) {
					//갱신?	=> bfs는 너비순으로 하므로, 갱신할 필요가 없음.	=> w 에서 거리가 1인것들~ 그 후에 2인것들~
					continue;
				}else {
					//물부터 1만큼떨어져있는 것부터. 그다음 물 객체엔 내부에 1이 들어가있을거임.
					dist[nr][nc] = w.distance + 1;
					answer += dist[nr][nc];
				}
				q.offer(new Water(nr, nc, dist[nr][nc]));
			}
		}
	}
	
	static boolean isCheck(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M) ? true : false;
	}
}
