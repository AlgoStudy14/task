package s0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 많은 득점을 하는 타순.
 * 문제 유형 : 시뮬레이션.
 * 요구 개념 : 순열, 시간복잡도를 고려한 세부구현.
 * <풀이법>
 * 1. 1번 선수를 제외한 2~8번 선수의 순서를 정한다(순열, 스택).
 * 2. 타순이 모두 정해지면 규칙에 맞게 게임을 시뮬레이션한다.
 * -> 선수는 인덱스 0번부터 시작한다.
 * (1) 인덱스가 9번이 되면, mod를 이용하여 시작 선수로 바꿔준다.
 * -> 득점시(그라운드는 int 배열로 선언하고, mod를 이용하여 그라운드에 존재하는 선수를 순환시킨다).
 * (1) 타자를 그라운드에 넣고(그라운드에 없는 선수는 해당 배열 값 = -1), 그라운드에 모든 타자를 진루(값이 -1이 아닌 요소만) 시킨다.
 * (2) 진루 상태가 4인 타자는 그라운드 상태를 -1로 바꾸고, 점수를 올린다.
 * -> 아웃시
 * (1) 아웃 카운트를 +1한다.
 * (2) 만일, 3아웃이 됐다면 다음 이닝으로 진행한다.
 * -> 다음 이닝으로 넘어가는 경우
 * (1) 아웃 카운트를 0으로 초기화한다.
 * (2) 그라운드의 모든 타자의 진루 상태를 -1로 바꾼다.
 * -> 시뮬레이션 종료후 득점 정보를 갱신한다.
 * <주의 사항>
 * 1. 쓸데 없는 연산은 최대한 배제 하여야 한다.
 * -> 예를 들면 현재 라인업을 따로 다른 배열에 복사하는 연산을 필요 없었다(시간 초과 발생).
 * 2. 정적 배열을 활용할 수 있으면 정적 배열을 최대한 활용하여야 한다.
 * -> 예를 들면 타순을 정하는 배열을 스택으로 두는 것 보다는 int배열로 두는 것이 좋다.
 * -> 예를 들면 그라운드에 있는 선수를 ArrayList에 저장하기 보다는 int배열에서 조작하는것이 낫다.
 */

public class BOJ_G4_17281_야구 {
	static int N;
	static int[][] res;
	static int[] temp = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static int[] player;
	static boolean[] visited;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		res = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				res[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 4번 타자는 항상 1번선수이다.
		player = new int[9];
		player[3] = 0;
		visited = new boolean[9];
		visited[3] = true;
		max = Integer.MIN_VALUE;

		// 타순 정하기
		Permutation(1);

		System.out.println(max);
	}

	private static void Permutation(int cnt) {
		if (cnt == 9) {
			simulation();
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 만일, 4번 타자의 순서가 되면 항상 1번 플레이어를 넣어주어야 한다.
				player[i] = cnt;
				Permutation(cnt + 1);
				// 만일, 4번 타자를 빼야 한다면, 빼준다.
				visited[i] = false;
			}
		}
	}

	private static void simulation() {
		int start = 0;
		int inning = 0;
		int score = 0;
		int on = 0;
		
		// 이닝이 종료될 때 까지 진행
		while (inning < N) {
			// 그라운드
			int[] ground = { -1, -1, -1, -1 };
			// 아웃 카운트
			int out = 0;

			// 각 이닝은 아웃이 3번될 때 까지 진행
			while (true) {
				// 선수 번호가 9가 되면 mod를 이용하여 시작 선수로 초기화.
				start %= 9;
				// 현재 선수
				int cur = player[start++];
				
				// 득점인 경우
				if (res[inning][cur] > 0) {
					// 타자 그라운드에 넣기
					ground[on++] = 0;
					// 그라운드의 위치는 mod를 이용하여 4가되면 초기화. 
					on %= 4;
					// 모든 선수 진루 시키고, 홈에 도착한 선수는 큐에서 제거하기
					for (int i = 0; i < 4; i++) {
						if (ground[i] > -1) {
							ground[i] += res[inning][cur];
						}
						if (ground[i] >= 4) {
							ground[i] = -1;
							// 점수 + 1.
							score++;
						}
					}
				}
				// 아웃인 경우
				else {
					// 아웃 카운트 + 1.
					out++;
					// 아웃 카운트가 3이라면 현재 이닝 종료
					if (out == 3) {

						break;
					}
				}
			}
			// 이닝 + 1.
			inning++;
		}

		// 최대 점수 갱신
		max = Math.max(max, score);
	}
}
