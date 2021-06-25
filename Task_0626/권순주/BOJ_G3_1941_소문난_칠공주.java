import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * <문제 요약>
 * 문제 정의 : 조건에 맞는 '소문난 칠공주'를 결성할 수 있는 모든 경우의 수
 * 문제 유형 : bfs + 조합
 * 주의 사항 : 25C7 * (7 + 6) = 대략 630만 정도 
 * 
 * <풀이 요약>
 * 1. 조합으로 25개 중 7개의 숫자를 구한다.
 * 2. 구한 숫자 조합을 map에 나타내준다 => checkMap을 사용 => true로 나타내줌
 * 2-1. map에 나타내주면서 임도연파가 3명 초과라면 함수 out
 * 3. 큐에 첫번째 위치를 넣어줌
 * 4. 7공주가 모두 연결되어 있는지 bfs로 확인 
 * 5. 모두 연결되어 있을 때 answer 증가
 * 
 * <피드백>
 * 처음에 dfs + 백트래킹으로 풀 수 있지 않을까? 고민했던 문제
 * 1. T자형을 나타낼 수 없었고
 * 2. 겹치는 부분을 어떻게 처리할지 생각이 안 났음
 * 그래서 조합 + bfs로 다 체크해주었다 -> 내가 구한 시간복잡도로는 가능했다! 
 * 설계의 중요성을 배웠던 문제 -> 안 되는 방법을 미리 알 수 있음
 */

public class BOJ_G3_1941_소문난_칠공주 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char[][] map;
	// checkMap : 조합으로 얻은 위치 체크
	// visited : bfs에서 사용하는 배열
	static boolean[][] checkMap, visited;
	static Queue<Point> list;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] point;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		point = new int[7];

		combi(0, 0);

		System.out.println(answer);
	}

	// 가능한 그룹 개수 찾기
	private static void countGroup() {
		int cntY = 0;
		checkMap = new boolean[5][5];
		visited = new boolean[5][5];
		list = new LinkedList<>();
		
		// 조합으로 얻은 배열 사용
		for (int num : point) {
			int x = num / 5; // x좌표
			int y = num % 5; // y좌표
			checkMap[x][y] = true; // 지도에 나타내기
			if (map[x][y] == 'Y') {
				cntY++;
			}
			// 임도연파가 3명 초과라면 끝내주기
			if (cntY > 3) {
				return;
			}
		}

		int x = point[0] / 5;
		int y = point[0] % 5;
		// 큐에 시작점 넣어주기 -> 가장 앞의 위치 넣어줌
		list.add(new Point(x, y));
		visited[x][y] = true;
		// 모두 연결되어 있다면 결과값 증가
		if (checkLine()) {
			answer++;
		}
	}

	// 7공주가 모두 연결되어 있는지 확인
	private static boolean checkLine() {
		int count = 1;

		while (!list.isEmpty()) {
			Point current = list.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || !checkMap[nx][ny] || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				list.add(new Point(nx, ny));
				count++;
			}
		}

		return count == 7 ? true : false;
	}

	// 조합 만들기
	private static void combi(int cnt, int start) {
		if (cnt == 7) {
			countGroup(); // 함수 실행
			return;
		}
		for (int i = start; i < 25; i++) {
			point[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

}
