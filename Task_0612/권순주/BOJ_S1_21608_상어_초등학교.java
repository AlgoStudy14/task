import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 학생의 만족도의 총 합
 * 문제 유형 : 구현 + 우선순위 큐
 * 
 * <풀이법 요약>
 * compareTo와 우선순위 큐를 적절히 사용하는 문제
 * 1. 입력받을 때 마다 해당 학생의 최선의 자리를 선택한다
 * 2-1. compareTo에 해당 조건을 잘 녹여서 코딩한다
 * 2-2. 우선순위 큐를 사용하여 조건에 부합하는 모든 자리를 넣어준다
 * 2-3. 우선순위가 가장 높은 자리를 찾아준다
 * 3. map에 해당 자리를 위치시킨다
 * 4. 모든 map을 체크하면서 만족도를 구한다
 */

public class Main_S1_21608_상어_초등학교 {

	static int N;
	static int[][] map, students;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static class Seat implements Comparable<Seat> {
		int x; // x좌표
		int y; // y좌표
		int likesCount; // 좋아하는 학생 수
		int emptyCount; // 비어있는 칸 수

		public Seat(int x, int y, int likesCount, int emptyCount) {
			this.x = x;
			this.y = y;
			this.likesCount = likesCount;
			this.emptyCount = emptyCount;
		}

		@Override
		public int compareTo(Seat o) {
			if (this.likesCount == o.likesCount) {
				if (this.emptyCount == o.emptyCount) {
					if (this.x == o.x) {
						// 3-2. 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
						return this.y - o.y;
					}
					// 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로,
					return this.x - o.x;
				}
				// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
				return o.emptyCount - this.emptyCount;
			}
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			return o.likesCount - this.likesCount;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		students = new int[N * N + 1][4];

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				students[now][j] = Integer.parseInt(st.nextToken());
			}

			Seat seat = findSeat(now); // 자리 찾아주기
			map[seat.x][seat.y] = now; // map에 자리 위치시키기
		}

		System.out.println(solve());
	}

	// 만족도 조사
	private static int solve() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int nowStudent = map[i][j]; // 현재 위치의 학생
				int likeStudentCount = 0; // 인접한 좋아하는 학생의 수

				for (int k = 0; k < 4; k++) { // 인접한 위치 탐색
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					if (checkAround(nowStudent, map[nx][ny])) { // 인접한 위치에 내가 좋아하는 학생이 존재한다면
						likeStudentCount++; // 좋아하는 학생 수 증가
					}
				}

				// 만족도 계산
				if (likeStudentCount == 1)
					answer += 1;
				else if (likeStudentCount == 2)
					answer += 10;
				else if (likeStudentCount == 3)
					answer += 100;
				else if (likeStudentCount == 4)
					answer += 1000;
			}
		}
		return answer;
	}

	// 자리 찾기
	private static Seat findSeat(int now) {
		PriorityQueue<Seat> pq = new PriorityQueue<>(); // 자리 선택을 위한 우선순위 큐

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) { // 앉을 수 있는 자리라면
					int likeTmp = 0; // 좋아하는 학생이 인접한 칸의 수
					int emptyTmp = 0; // 인접한 칸 중 비어있는 칸의 수
					for (int k = 0; k < 4; k++) { // 인접한 위치 탐색
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}
						if (checkAround(now, map[nx][ny])) { // 인접한 위치에 내가 좋아하는 학생이 존재한다면
							likeTmp++; // 좋아하는 학생 수 증가
						}
						if (map[nx][ny] == 0) { // 빈칸이라면
							emptyTmp++; // 빈칸 수 증가
						}
					}
					pq.add(new Seat(i, j, likeTmp, emptyTmp)); // 우선순위 큐에 가능한 자리의 객체를 만들어서 넣어준다
				}
			}
		}

		return pq.peek(); // 가장 우선순위가 높은 -> 조건에 가장 부합하는 자리를 return한다
	}

	// 인접한 위치에 내가 좋아하는 학생이 존재하는지 체크
	private static boolean checkAround(int nowStudent, int likeStudent) {
		for (int i = 0; i < 4; i++) {
			if (students[nowStudent][i] == likeStudent) { // 좋아하는 학생이 존재하면
				return true; // true return 
			}
		}
		return false; // false return
	}

}
