import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* (완)
 * <문제 요약>
 * 문제 정의 : NXN의 체스판에 N개의 퀸을 놓는 방법의 수
 * 문제 유형 : 백트래킹
 * 주의 사항 : 시간을 줄이는데 집중
 * 
 * <풀이 요약>
 * 1. 가지치기를 하며 백트래킹을 진행한다 
 * -> 퀸의 이동 범위를 체크하면서 진행
 * -> 같은 행과 대각선을 비교하며서 체크 (저장한 col을 가지고 비교)
 * 2. N개의 체스를 사용한다면 answer를 증가시킨다
 * 
 * <피트백>
 * 1차원으로 생각해 보자
 * 직관적인 비교를 할 수 있지 않을까 생각해 보기
 * 
 */

public class BOJ_G5_9663_NQueen_2 {

	static int N;
	// map을 1차원으로 나타냄
	// index : 행 / value : 열
	static int[] col; 
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N];
		Arrays.fill(col, -1);

		playChess(0);

		System.out.println(answer);
	}

	// 전체 문제 풀이
	private static void playChess(int chessCnt) {
		// 개수가 N개라면
		if (chessCnt == N) {
			// 결과값 증가
			answer++;
			return;
		}
		// 범위 돌리기
		for (int j = 0; j < N; j++) {
			// 퀸 놓기
			col[chessCnt] = j;
			// 만약 말을 놓을 수 있다면
			if (checkPoint(chessCnt)) {
				// 재귀
				playChess(chessCnt + 1);
			}
		}
	}

	// 말을 놓을 수 있는지 판단한다
	private static boolean checkPoint(int index) {
		for (int j = 0; j < index; j++) {
			// 새로운 퀸과 기존의 퀸이 같은 행에 있거나 대각선에 있을 경우
			if (col[j] == col[index] || Math.abs(col[index] - col[j]) == (index - j)) {
				return false;
			}
		}
		return true;
	}
}
