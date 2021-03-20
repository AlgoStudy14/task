import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 방출되는 에너지의 총합
 * 문제 핵심 요약 : 0.5초씩 위치를 이동시키면서 충돌하는지 체크, 시뮬레이션
 * 
 * <풀이법 요약>
 * 핵심은 map에 원자의 에너지를 저장하면서, 현재 map에 저장된 에너지가 나의 에너지와 같지 않으면 충돌했다고 판단하여 계산한다.
 * 
 * 1. q에 해당 원자의 정보를 저장 + map에는 해당 원자의 에너지를 저장한다.(나중에 계산을 용이하게 하기 위함)
 * 2. bfs를 이용해서 시뮬레이션을 진행한다.
 * 3. 만약 해당 원자의 위치에 해당하는 map의 값이 다르다면 => map에는 원자의 에너지가 저장되어 있기때문에 충돌이 되었다고 판단할 수 있다
 * 4. 충돌이라고 판단한 뒤 answer에 map의 합(방출 에너지의 합)을 더해준다.
 * 5. 원자 위치 이동
 * 5-1. 이동한 위치에 해당하는 map의 값 : 0 => 아직 아무도 방문하지 않았기 때문에 자신의 에너지를 넣어준 뒤 q에 추가
 * 5-2. 이동한 위치에 해당하는 map의 값 : 0이 아님 => 이미 다른 원자가 위치해있기 때문에 자신의 에너지를 추가해준다.
 * 
 * 구느님의 힘들 빌렸음!
 */

public class Solution_5648_원자_소멸_시뮬레이션 {

	static class Node {
		int x;
		int y;
		int dir;
		int value;

		public Node(int x, int y, int dir, int value) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.value = value;
		}
	}

	static int N;
	static int[][] map = new int[4002][4002];
	static int answer;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2; // 값이 양수가 될 수 있도록 변경
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2; // 값이 양수가 될 수 있도록 변경
				int dir = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				q.offer(new Node(x, y, dir, value));
				map[x][y] = value; // map에 해당 원자의 에너지를 저장
			}

			bfs();
			System.out.printf("#%d %d\n", t, answer);
		}
	}

	public static void bfs() {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {
			Node n = q.poll();
			if (map[n.x][n.y] != n.value) { // 충돌했다면
				answer += map[n.x][n.y]; // answer에 값 저장
				map[n.x][n.y] = 0; // 원자 소멸
				continue;
			}
			int nx = n.x + dx[n.dir];
			int ny = n.y + dy[n.dir];
			if (nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000) {
				if (map[nx][ny] == 0) { // 만약 이돌할 위치가 비워져 있다면
					map[nx][ny] = n.value; // 이동한 위치 표시
					q.offer(new Node(nx, ny, n.dir, n.value));
				} else {
					map[nx][ny] += n.value; // 충돌 에너지의 총합
				}
			}
			map[n.x][n.y] = 0; // 이동했으니까 원래 위치는 0
		}
	}

}
