import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 :.
 * 문제 유형/요구 개념 :
 * <풀이법 요약>
 * 풀이 1 : 전체 공간 좌표를 직접 만들어서 생각하는 경우(불가능).
 * -> 배열의 크기는 최대 1000 x 1000까지만 가능하다고 생각하면된다.
 * -> 그 이상이 된다면 대부분 메모리 초과 혹은 시간 초과가 발생한다.
 * 풀이 2 : 들어오는 좌표에 '시간'이라는 원소를 추가시켜 좌표를 시간단위로 움직이고, 충돌체크를한다.
 * -> 단점 : 충돌 여부와 관계 없이 시간의 한계까지 모두 체크해야함.
 * 풀이 3 : 각 원소에 대하여 충돌 가능성을 기록, 충돌 가능성에 대한 충돌 시간이 짧은 순서로 오름차순 정렬.
 * -> 대상의 충돌 여부를 확인하여, 충돌 연산 수행.
 * 풀이 4 : 좌표 평면은 만들되, 모든 좌표를 탐색하지 않고 좌표 그 자체의 시점에서 이동 좌표를 표현(이 풀이법 이용).
 * -> 이동된 좌표에 대하여 충돌 여부를 판단. 충돌된 원소를 하나씩 제거해 간다.
 */
public class Solution_A_5648 {
	// 충돌 시간 한계(4000초 까지 모두 탐색)
	static int tCase, N, MAX = 4001;
	static int[][] visited;
	static boolean[][] collapse;
	static Node[] atoms;
	static int[] dirX = new int[] { 1, -1, 0, 0 };
	static int[] dirY = new int[] { 0, 0, -1, 1 };
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		tCase = Integer.parseInt(br.readLine());
		// 지도상에서 충돌된 위치를 기억할 배열.
		collapse = new boolean[MAX][MAX];
		// 해당 좌표에 몇 개의 원소가 있는지를 시뮬레이션할 배열(아이디어의 핵심).
		visited = new int[MAX][MAX];

		for (int t = 1; t <= tCase; t++) {
			N = Integer.parseInt(br.readLine());

			atoms = new Node[N];

			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				// 원소를 저장하되, 0.5초 단위까지 고려해야 하므로 좌표 * 2를하여 저장하고, 배열 인덱스는 양수이므로 + 1000을 먼저 해준다.
				atoms[i] = new Node((row + 1000) * 2, (col + 1000) * 2, dir, power);
				visited[atoms[i].row][atoms[i].col] = 1;
			}
			int ans = solve();
			System.out.println("#" + t + " " + ans);
		}
	}

	public static int solve() {
		int numOfAtom = N;
		int ans = 0;

		// 4000
		for (int j = 0; j < MAX; j++) {
			// 원자들을 이동시킴
			for (int i = 0; i < numOfAtom; i++) {

				// 이동시키기 전
				int row = atoms[i].row;
				int col = atoms[i].col;
				int dir = atoms[i].dir;
				// 원래 있던 자리에서 이동하므로, 해당 자리의 원소 수는 -1 해준다.
				visited[row][col] -= 1;

				// 이동시키기
				if (isBoundary(row + dirX[dir], col + dirY[dir])) {
					row += dirX[dir];
					col += dirY[dir];
					atoms[i].row = row;
					atoms[i].col = col;
					// 이동한 좌표의 원소수 + 1.
					visited[row][col] += 1;

					// 만일, 해당 좌표의 값이 2이상이라면 충돌한 것이므로, 해당 원소는 충돌 처리를한다.
					if (visited[row][col] >= 2)
						collapse[row][col] = true;

				} else {
					// 좌표를 벗어나는 원자들을 제거한다.
					atoms[i] = atoms[numOfAtom - 1];
					numOfAtom--;
					i--;
				}
			}

			// 원자들의 충돌여부 확인
			for (int i = 0; i < numOfAtom; i++) {

				int row = atoms[i].row;
				int col = atoms[i].col;

				// 만일, 뽑은 원소의 좌표가 충돌인 상태라면, 해당 좌표에 있는 원소들을 하나씩 뺄 것인데
				if (collapse[row][col] == true) {
					// 해당 좌표가 만일, 원소 한개 뿐이라면 충돌한 것이 아님(즉, 2개의 충돌이 성립이 안함 : 한개씩 빼고 있으므로).
					if (visited[row][col] == 1)
						collapse[row][col] = false;

					// 충돌로 인한 파워 갱신.
					ans += atoms[i].power;
					// 충돌한 원소는 제거
					visited[row][col] -= 1;
					// 일반 배열에서 해당 원소를 제거하는 로직(자바시간에 배웠는데 이렇게 쓸 수 있음!!).
					atoms[i] = atoms[numOfAtom - 1];
					numOfAtom--;
					// 인덱스 주의
					i--;
				}
			}
			// 원소가 하나도 안 남았으면 시뮬레이션 종료.
			if (numOfAtom == 0)
				break;
		}
		return ans;
	}

	public static boolean isBoundary(int row, int col) {
		return (row >= 0 && row < MAX) && (col >= 0 && col < MAX);
	}
}

class Node {
	int row;
	int col;
	int dir;
	int power;

	public Node(int row, int col, int dir, int power) {
		this.row = row;
		this.col = col;
		this.dir = dir;
		this.power = power;
	}

}
