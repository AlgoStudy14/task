package algo210612;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 만족도
 * 제약 사항: 3<=N<=20
 * 문제 유형: 시뮬레이션
 * 요구 개념: 시뮬레이션
 * 
 * <풀이법 요약>
 * 1. 배열로 입력값 다 받음
 * 2. 차례대로 시뮬레이션으로 구현
 */
public class BOJ_Silver1_21680_상어초등학교 {

	static int N, like[][], map[][], sitCnt;
	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};
	
	static class Point implements Comparable<Point> {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Point o) {
			if(this.r == o.r) return this.c - o.c;
			else return this.r - o.r;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		// idx - 해당 학생 넘버 / 학생이 좋아하는 4명의 학생
		like = new int[N*N][5];
		map = new int[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int idx = Integer.parseInt(st.nextToken());
			like[i][0] = idx;
			for (int j = 1; j <= 4; j++) {
				like[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		process();
		
		System.out.println(checkScore());
	}

	private static void process() {
		
		List<int[]> likeSeats = new LinkedList<int[]>();

		for (int order = 0; order < like.length; order++) {
			
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			
			if(sitCnt == 0) {	// 학생이 아무도 없는 경우 -> (1,1)에 학생 앉히기
				map[1][1] = like[order][0];
				++sitCnt;
			} else { // 맨 처음 경우가 아닌 경우
				// 학생이 인접한 칸에 많은 칸 찾기
				likeSeats.clear();
				int maxLikeCnt = -1;
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						
						if(map[r][c]!=0) continue; // 자리에 이미 사람이 있으면
						
						int likeCnt = 0;
						for (int dir = 0; dir < 4; dir++) {
							int nr = r+dr[dir];
							int nc = c+dc[dir];
							
							if(!check(nr,nc) || map[nr][nc] == 0) continue;
							
							for (int i = 1; i <= 4; i++) {
								if(like[order][i] == map[nr][nc]) {
									++likeCnt;
									break;
								}
							}
						}
						
						if(likeCnt > maxLikeCnt) {
							maxLikeCnt = likeCnt;
							likeSeats.clear();
							likeSeats.add(new int[]{r,c});
						} else if(likeCnt == maxLikeCnt){
							likeSeats.add(new int[]{r,c});
						}
					}
				}
				
				if(likeSeats.size() == 1) { // 앉을 수 있는 칸이 1개인 경우
					int[] pos = likeSeats.get(0); 
					map[pos[0]][pos[1]] = like[order][0];
				} else { // 앉을 수 있는 칸이 여러개인 경우
					Queue<Point> pq = new PriorityQueue<Point>();
					
					int maxClearCnt = -1;
					for(int[] cur : likeSeats) {
						int clearCnt = 0;
						int curR = cur[0];
						int curC = cur[1];
						
						for (int dir = 0; dir < 4; dir++) {
							int nr = curR + dr[dir];
							int nc = curC + dc[dir];
							
							if(!check(nr,nc) || map[nr][nc] != 0) continue;
							
							++clearCnt;
						}
						
						if(clearCnt > maxClearCnt) {
							maxClearCnt = clearCnt;
							pq.clear();
							pq.offer(new Point(curR, curC));
						} else if(clearCnt == maxClearCnt) {
							pq.offer(new Point(curR, curC));
						}
					}
					
					Point select = pq.poll();
					
					map[select.r][select.c] = like[order][0];
				}
			}
			
		}
		
	}

	private static int checkScore() {
		int totalScore = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				int order = -1;
				for (int i = 0; i < N*N; i++) {
					if(like[i][0] == map[r][c]) {
						order = i;
						break;
					}
				}
				
				int likeCnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nr = r+dr[dir];
					int nc = c+dc[dir];
					
					if(!check(nr,nc)) continue;
					
					for (int i = 1; i <= 4; i++) {
						if(like[order][i] == map[nr][nc]) {
							++likeCnt;
							break;
						}
					}
				}
				
				if(likeCnt == 1) totalScore += 1;
				else if(likeCnt == 2) totalScore += 10;
				else if(likeCnt == 3) totalScore += 100;
				else if(likeCnt == 4) totalScore += 1000;
			}
		}
		return totalScore;
	}

	private static boolean check(int r, int c) {
		return r<N && r>=0 && c<N && c>=0;
	}

}
