package algo0918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의: 모든 승객을 성공적으로 데려다 줄 수 있는지 출력, 가능하다면 남은 연료의 양도 출력(불가능 -1)
 * 문제 유형: 시뮬레이션, BFS
 * <풀이 요약>
 * 현재 위치에서 가장 가까운 승객 > 행 번호가 작은 승객 > 열 번호가 작은 승객
 * 이동 중, 연료가 바닥나면 이동 실패
 * 목적지 도착 시, (직전 승객의 이동 비용 * 2)의 연료가 충전됨
 * <피드백>
 * (승객의 출발지 = 승객의 도착지)일 경우 거리를 0인데 1로 하는 로직을 작성함
 * 승객이 도착지에 도착할 수 없는 경우를 놓침
 * 생각해야될 조건이 많아서 오래 걸렸다..🥺
*/

public class BOJ_G4_19238_스타트택시 {

	static int N, M, F;
	static Pos taxi;
	static int[][] map;
	static ArrayList<Pos> P;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static class Pos implements Comparable<Pos> {
		int x, y, tx, ty;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Pos(int x, int y, int tx, int ty) {
			this.x = x;
			this.y = y;
			this.tx = tx;
			this.ty = ty;
		}
		
		@Override
		public int compareTo(Pos p) {
			if (this.x == p.x) return this.y - p.y;
			return this.x - p.x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		P = new ArrayList<>();
		
		// 지도 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}

		// 택시의 출발 위치 입력
		st = new StringTokenizer(br.readLine());
		taxi = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		// 승객 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			P.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// 최단거리가 짧은 승객을 위한 정렬(행 > 열)
		Collections.sort(P);
		
		System.out.println(solve() ? F : -1);
	}

	static boolean solve() {
		for (int i = 0; i < M; i++) {
			if (!closer(P.size())) return false;
		}
		return true;
	}
	
	// 1. 가장 가까운 승객 구하기
	static boolean closer(int S) {
		int min = N * N + 1;			// 가장 가까운 승객의 거리
		int minIdx = -1;				// 가장 가까운 승객의 인덱스
		
		for (int i = 0; i < S; i++) {
			Pos cur = P.get(i);
			int distance = bfs(taxi, new Pos(cur.x, cur.y));
			
			// 택시가 승객까지 갈 수 없거나 | 가장 가까운 승객이 아닐 경우
			if (distance == -1 || min <= distance) continue;
			
			min = distance;
			minIdx = i;
		}
		
		// 택시가 데려다 줄 수 있는 승객이 없거나 | 연료가 모자란 경우
		if (minIdx == -1 || min >= F) return false;
		
		// 승객을 태우고 도착지까지 도착할 수 없거나 | 연료가 모자란 경우
		int destination = getDestination(minIdx);
		if (destination == -1 || min + destination > F) return false;
		else {
			move(minIdx);
			remove(minIdx);
			F += 2 * destination - min - destination;
			return true;
		}
	}
	
	// (start ~ end)까지의 거리 구하기
	static int bfs(Pos start, Pos end) {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(start);
		
		int[][] visited = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) Arrays.fill(visited[i], -1);
		visited[start.x][start.y] = 0;
		
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			if (cur.x == end.x && cur.y == end.y) return visited[cur.x][cur.y];
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
				if (visited[nx][ny] >= 0 || map[nx][ny] == 1) continue;
				
				Q.add(new Pos(nx, ny));
				visited[nx][ny] = visited[cur.x][cur.y] + 1;
			}
		}
		
		return -1;
	}
	
	// 2. (가장 가까운 승객까지의 거리 + 승객의 목적지까지의 거리)에 필요한 연료가 충분한지
	static int getDestination(int minIdx) {
		Pos p = P.get(minIdx);
		int distance = bfs(new Pos(p.x, p.y), new Pos(p.tx, p.ty));
		return distance > F ? -1 : distance;
	}
	
	// 3. 택시를 목적지로 이동
	static void move(int minP) {
		taxi.x = P.get(minP).tx;
		taxi.y = P.get(minP).ty;
	}
	
	// 4. 목적지까지 데려다준 승객 삭제
	static void remove(int minP) {
		P.remove(minP);
	}
}
