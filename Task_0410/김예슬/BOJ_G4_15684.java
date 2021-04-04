package algo0403;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 사다리타기 i번 세로선의 결과가 i번이 나오도록  조작할 때 추가해야하는 가로선 개수의 최솟값을 출력
 * 제약 사항 : 가로선은 연속하거나 접하면 안됨
 * 문제 유형 : DFS, 조합, 백트랙킹을 사용해 가로선을 선택(조합 시 300C3 ≒ 450만)
 * <풀이법 요약>
 * 1. 추가할 가로선을 선택
 * 	1-1. 가로선은 연속하거나 접하면 안되므로 추가할 칸의 왼쪽, 오른쪽 확인
 *  1-2. 마지막 세로칸은 true 불가능하므로 N번째 칸은 체크할 필요X
 * 2. 선택할 때마다 i번 세로선의 결과가 i가 나오는지 체크
 * 	2-1. 현재칸이 true일 경우 오르쪽으로 이동
 *  2-2. 현재 칸의 왼쪽이 true일 경우 왼쪽으로 이동
 *  	 (현재 칸이 제일 첫 번째 칸(1번)일 경우 왼쪽(0번)은 항상 false)
 * 3. i번 세로선의 결과가 i라면 min 값 갱신
 *  3-1. min이 4라면 조작 불가능
 */

 public class BOJ_G4_15684 {

	static int N, M, H, min;
	static boolean[][] ladder;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		ladder = new boolean[H + 1][N + 1];
		
		for (int i = 0; i < M; i++)
			ladder[sc.nextInt()][sc.nextInt()] = true;
		
		min = 4;
		selectRow(0, 1);
		
		System.out.println(min == 4 ? -1 : min);
	}
	
	// 추가할 가로선을 선택(조합)
	static void selectRow(int depth, int start) {
		if (depth == 4) return;
		
		if (down()) {
			min = Math.min(min, depth);
			return;
		}
		
		for (int i = start; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				// 왼쪽O, 오르쪽O -> 가로선 추가 불가능
				if (ladder[i][j]) continue;
				if (ladder[i][j - 1]) continue;
				if (ladder[i][j + 1]) continue;
				
				ladder[i][j] = true;
				selectRow(depth + 1, i);
				ladder[i][j] = false;
			}
		}
	}
	
	// i번 세로선의 결과가 i번이 나오는지 체크
	static boolean down() {
		for (int i = 1; i <= N; i++) {
			int col = i;
			
			for (int j = 1; j <= H; j++) {
				// 현재칸O 오르쪽으로 이동 / 왼쪽칸O 왼쪽으로 이동 
				if (ladder[j][col]) ++col;
				else if (ladder[j][col - 1]) --col;
			}
			
			if (i != col) return false;
		}
		return true;
	}
}
