package BFSDFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 0은 바다, 1은 육지를 나타냄
 * 가장 짧은 다리를 놓아 두 대륙을 연결한다고 할때 그 길이 출력
 * 다리 직선 아니어도됨
 */
public class BOJ_2146_G3_다리만들기 {

	static int N;
	static int[][] map,dist,island;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static class Node{
		int x,y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //지도의 크기
		map = new int[N][N]; 
		dist = new int[N][N]; //섬과 섬 사이의 거리
		island = new int[N][N]; //섬 그룹별로 나눠서 영역 표시
		
		//입력받고 dist -1로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				dist[i][j] = -1;
			}
		}
		int cnt = 0; //영역 구분지어 담아줄 변수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//섬이고 그룹 결정 안했으면 4방 돌면서 인접한 곳 같은 숫자 집어넣어줌
				if(map[i][j]==1&&island[i][j]==0) {
					Queue<Node> que = new LinkedList<>();
					island[i][j] = ++cnt;
					que.add(new Node(i,j));
					while(!que.isEmpty()) {
						Node current = que.poll();
						int r = current.x;
						int c = current.y;
						for (int d = 0; d <4; d++) {
							int nr = r+dr[d];
							int nc = c+dc[d];
							if(check(nr,nc)&&map[nr][nc]==1&&island[nr][nc]==0) {
								que.add(new Node(nr,nc));
								island[nr][nc] = cnt;
							}
						}
					}
				}
			}
		}
		Queue<Node> que = new LinkedList<>(); //큐 재생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {
					que.add(new Node(i,j));
					dist[i][j] = 0; //섬을 0으로 표시
				}
			}
		}
		while(!que.isEmpty()) {
			Node current = que.poll();
			int r = current.x;
			int c = current.y;
			for (int d = 0; d <4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				//범위 안에 있고 섬이 아닌곳이면
				if(check(nr,nc)&&dist[nr][nc]==-1) {
					//거리증가
					dist[nr][nc] = dist[r][c]+1;
					//섬의 영역도 섬을 표시한 같은 숫자로 확장?
					island[nr][nc] = island[r][c];
					que.add(new Node(nr,nc));
				}
			}
		}
		int ans = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d <4; d++) {
					int x = i+dr[d];
					int y = j+dc[d];
					//범위 안에 있고 인접한 섬의 영역이 다를때 
					if(check(x,y)&&island[i][j]!=island[x][y]) {
						//인접한 거리+현재거리 최소값 찾기
						if(ans==-1||ans>dist[i][j]+dist[x][y]) ans = dist[i][j]+dist[x][y];
					}
				}
			}
		}
		System.out.println(ans);
	}
	private static boolean check(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr>=0&&nc>=0&&nr<N&&nc<N;
	}

}
