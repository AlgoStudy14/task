import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : i번 세로선의 결과가 i번이 나오기 위해 추가해야 하는 가로선 개수의 최솟값
 * 유형 : 완탐, 백트래킹
 * 
 * <풀이법 요약>
 * 1. 입력받은 사다리를 map에 그려준다.
 * 2. 입력받지 않은 가로선을 그려주면서 dfs너낌
 * 2-1. 만약 사용한 가로선이 answer와 같다면 끝낸다 (최솟값을 구하는 것이기 때문에!)
 * 2-2. 만약 사용한 가로선이 N개라면 끝낸다 (4개부터 -1을 return하기 때문에!)
 * 2-3. 만약 i번 세로선의 결과가 i가 나온다면 answer를 계산하고 끝낸다
 * 2-4. 해당 위치 가로선 체크
 * 2-5. 시뮬레이션 돌려줌
 * 2-6. 해당 위치 가로선 삭제
 * 3. 사다리 이동 부분은 사다리를 직접 그려서 했기 때문에 for문 돌려서 확인
 * 
 * 일단 나는 이 문제를 처음 보고는 사다리를 직접 그려서 풀어야 겠다고 생각했다
 * 그래서 다 풀고 구글링 해보니까 다들 1(왼쪽) 2(오른쪽)이렇게 풀었더라ㅜㅜㅜㅜ 다들 천재인듯 ㅜㅜㅜㅜㅜ
 * "단, 두 가로선이 연속하거나 서로 접하면 안 된다"
 * 이 조건이 있기 때문에 해당 위치에서는 0,1,2 중 하나만 가질 수 있는 것이다...
 * 하나 배워가는 문제!
 * 이 문제 풀이가 내가 안 걸렸으면 좋겠다.. 간절히...
 */

public class Main_G4_15684_사다리_조작 {

	static int[][] map;
	static int N, M, H;
	static int answer = 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][2 * N - 1];
		
		// 사다리 세로선 1로 만들기
		for (int i = 0; i < N * 2 - 1; i += 2) {
			for (int j = 0; j < H; j++) {
				map[j][i] = 1;
			}
		}
		// 입력받은 가로선 그려주기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a - 1][2 * b - 1] = 1;
		}

		solve(0, 0);
		System.out.println(answer == 4 ? -1 : answer);
	}

	// cnt : 사용한 가로선의 개수
	// x : 탐색을 시작할 높이의 index
	private static void solve(int cnt, int x) {
		if (answer == cnt) { // 만약 사용한 가로선이 answer와 같다면 더 볼필요가 없다!
			return;
		}
		if (cnt == 4) { // 사용한 가로선이 3보다 커지면 더 볼 필요가 없다!
			return;
		}
		if (check()) { // i번 세로선의 결과가 i번이라면
			answer = Math.min(answer, cnt); // 최솟값 저장
			return;
		}
		// i = 0으로 풀어서 시간초과가 났다 => 순열이 되어버린다
		// index를 저장해줘서 조합 느낌으로 만들어줘야 한다.
		// => i = x부터 시작한다!
		for (int i = x; i < H; i++) {
			for (int j = 1; j < 2 * N - 1; j += 2) {
				if (map[i][j] == 0) { // 가로선이 없는 부분이라면
					map[i][j] = 1; // 가로선 만들어주기
					solve(cnt + 1, i); // 가로선의 수 하나 늘리고 index(i) 넘겨주기
					map[i][j] = 0; // 가로선 다시 지워주기
				}
			}
		}
	}

	// i -> i 이동 가능한지 확인
	private static boolean check() {
		for (int i = 0; i < 2 * N - 1; i += 2) {
			if (!down(i)) { // 한번이라도 false가 나온다면 false를 return
				return false;
			}
		}
		return true;
	}

	// 사다리 아래로 내려가기
	private static boolean down(int start) {
		int index = start;
		for (int i = 0; i < H; i++) {
			if (0 < (index - 1) && map[i][index - 1] == 1) { // 왼쪽으로 이동
				index -= 2;
			} else if ((index + 1) < (2 * N - 1) && map[i][index + 1] == 1) { // 오른쪽으로 이동
				index += 2;
			}
		}
		// i번 세로선의 결과가 i번이라면 true를 아니아면 false를 return
		return index == start ? true : false;
	}
}
