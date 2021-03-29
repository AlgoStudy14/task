package algo210403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 
 * 구해야 하는 것: 가장 짧은 다리 하나를 놓아 두 대륙을 연결할 때, 가장 짧은 다리의 길이
 * 제약 사항: N은 100 이하의 자연수, 0은 바다, 1은 육지 / 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.
 * 문제 유형: BFS+시뮬 
 * 요구 개념: BFS+시뮬
 * 
 * <풀이법 요약> 
 * 1. flood-fill로 섬마다 이름 붙여주기
 * 2. bfs 돌면서 선 길이 가장 짧은 것으로 갱신해주기 
 * 
 * => queue.poll(), 조건 중요!
 */
public class BOJ_Gold3_다리만들기 {

	static int N, seomIdx, answer=Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dist;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
 	public static void main(String[] args) throws NumberFormatException, IOException {
 		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 		N = Integer.parseInt(in.readLine());
 		
 		map = new int[N][N];
 		visited = new boolean[N][N];
 		dist = new int[N][N];
 		
 		StringTokenizer st = null;
 		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
 		
 		namingMap();
 		
 		findBridge();

 		System.out.println(answer);
	}
 	
 	//가장 짧은 다리를 찾기(모든 경우를 탐색한다.)
	private static void findBridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					queue.clear();
					initDist();
					
					int curSeomIdx = map[i][j];
					queue.offer(new int[] {i, j});
					dist[i][j] = 1;
					
					boolean success = false;
					while(!queue.isEmpty()) {
						int cycle = queue.size();
						for (int a = 0; a < cycle; a++) {
							int curR = queue.peek()[0];
							int curC = queue.peek()[1];
							queue.poll(); // 빼먹지 말자! 
							
							for (int dir = 0; dir < 4; dir++) {
								int nr = curR + dr[dir];
								int nc = curC + dc[dir];
								
								//조건 중요! 
								if(!check(nr, nc) || map[nr][nc] == curSeomIdx || dist[nr][nc] != 0) continue;
								if(map[nr][nc] != 0 && map[nr][nc] != curSeomIdx) {
									answer = Math.min(answer, dist[curR][curC]-1);
									success = true;
									break;
								}
								dist[nr][nc] = dist[curR][curC] + 1;
								queue.offer(new int[] {nr,nc});
							}
						}
						if(success) break;
					}
				}
			}
		}
	}

	private static void initDist() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 0);
		}
		
	}

	//섬에 이름 붙이기
	private static void namingMap() {
		seomIdx = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					++seomIdx;
					
					queue.clear();
					queue.offer(new int[] {i,j});
					visited[i][j] = true;
					map[i][j] = seomIdx;
					
					while(!queue.isEmpty()) {
						int curR = queue.peek()[0];
						int curC = queue.peek()[1];
						queue.poll();
						
						for (int dir = 0; dir < 4; dir++) {
							int nr = curR + dr[dir];
							int nc = curC + dc[dir];
							
							if(!check(nr,nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
							map[nr][nc] = seomIdx;
							visited[nr][nc] = true;
							queue.offer(new int[] {nr, nc});
						}
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
