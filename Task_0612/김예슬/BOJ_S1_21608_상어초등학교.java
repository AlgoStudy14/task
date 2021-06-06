package algo0501;

import java.util.PriorityQueue;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 학생의 만족도의 총 합을 출력
 * 제약 사항 : 좋아하는 학생 多 > 비어있는 칸 多 > 행의 번호가 작은 칸 > 열의 번호가 작은 칸 
 * 문제 유형 : 우선순위큐 사용
 * <풀이법 요약>
 * 0. 0(0), 1(1), 2(10), 3(100), 4(1000)
 * 0. seat: 학생들이 앉을 위치, like: 학생들이 좋아하는 학생의 번호
 * 1. 자리를 정할 때, 각 학생마다 우선순위큐를 사용해 하위 순서에 맞게 정렬이 되도록 저장
 *  1-1. 인접한 자리에 좋아하는 학생이 많은 자리
 *  1-2. 인접한 자리가 많이 비어있는 자리
 *  1-3. 행의 번호가 작은 자리
 *  1-4. 열의 번호가 작은 자리
 * 2. 각 학생의 만족도를 계산
 */

public class BOJ_S1_21608_상어초등학교 {

	static int N;
	static int[][] seat, like;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static class Pos implements Comparable<Pos> {
		int x, y, likeCnt, emptyCnt;
		
		Pos (int x, int y, int likeCnt, int emptyCnt) {
			this.x = x;
			this.y = y;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}
		
		@Override
		public int compareTo(Pos p) {
			// 좋아하는 학생이 있는 자리
			int tmp = this.likeCnt - p.likeCnt;
			if (tmp != 0) return -tmp;
			else {
				// 비어있는 자리
				tmp = this.emptyCnt - p.emptyCnt;
				if (tmp != 0) return -tmp;
				else {
					// 행
					tmp = this.x - p.x;			
					if (tmp != 0) return tmp;
					else return this.y - p.y;	// 열
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		seat = new int[N * N + 1][N * N + 1];
		like = new int[N * N + 1][4];
		
		for (int i = 1; i <= N * N; i++) {
			int now = sc.nextInt();
			for (int s = 0; s < 4; s++) like[now][s] = sc.nextInt();
			
			// 학생이 앉을 자리를 구함
			Pos where = able(now);
			seat[where.x][where.y] = now;
		}
		
		System.out.println(count());
	}
	
	// 현재 학생이 앉을 자리를 구함
	static Pos able(int student) {
		PriorityQueue<Pos> able = new PriorityQueue<>();
		
		// 모든 칸에 대해 탐색
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (seat[i][j] != 0) continue;	// 이미 자리가 있다면 패스
				
				int likeCnt = 0;
				int emptyCnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					
					if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
					
					// 인접한 자리에 빈자리가 있는지 체크
					if (seat[nx][ny] == 0) ++emptyCnt;
					// 인접한 자리에 좋아하는 학생이 있는지 체크
					for (int s = 0; s < 4; s++)
						if (seat[nx][ny] == like[student][s]) ++likeCnt;
				}
				
				able.add(new Pos(i, j, likeCnt, emptyCnt));
			}
		}
		
		return able.poll();
	}
	
	// 만족도 조사
	static int count() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int now = seat[i][j];
				int likeCnt = 0;
				
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
				
					if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
					
					// 인접한 자리에 좋아하는 학생이 있다면 카운팅(인접한 자리 == 좋아하는 학생[s])
					for (int s = 0; s < 4; s++)
						if (seat[nx][ny] == like[now][s]) ++likeCnt;
				}
				sum += likeCnt == 0 ? 0 : Math.pow(10, likeCnt - 1);
			}
		}
		return sum;
	}
}
