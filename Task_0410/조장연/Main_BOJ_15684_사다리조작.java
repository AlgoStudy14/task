import java.util.Scanner;

/*
 * <문제 요약>
 * 사다리의 정보가 주어졌을 때 최대 3개의 사다리 라인을 추가하여 각자 자신인 결과가 나오도록 만들기
 * 만약 3개 이하로 사용하여 조건을 만족한 경우 최소 사용 사다리갯수 출력
 * 만약 3개 이상이 필요할 때  -1출력
 * 바로 옆라인과 연속되서 사다리 라인을 만들 수 없음
 * 
 * <풀이법 요약>
 * 사다리의 정보를 2차원 배열로 만듬
 * 사다리 추가가 가능한 경우 하나씩 만들며 dfs
 * 
 * 
 * 보통 배열을 사용할 때는 각 값이 배열에 알맞게 들어가는 경우였는데 이번 문제는 양쪽을 잇는 라인을 배열로 표시하는것이 문제였음.
 * 1번줄과 2번줄을 잇는 라인을 1번 column에 저장하니까 생각하기 복잡했음
 * 구글링 해보니까 1, 2로 이어진거를 표시하니까 훨씬 직관적이고 좋았던 것 같음
 */

public class Main_BOJ_15684_사다리조작 {
	static int ladder[][];
	static int n, m, h;
	static boolean check[][];
	static int min = Integer.MAX_VALUE;
	static int end_y;
	static boolean final_result;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		h = sc.nextInt();
		ladder = new int[h + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			ladder[x][y] = 1;
			ladder[x][y + 1] = 2;
		}
		
		solve(1, 0);
		if (min > 3)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	public static void solve(int index, int depth) {

		if (check_move()) {
			min = Math.min(depth, min);
			return;
		}

		if (depth > 2) {
			return;
		}

		for (int i = index; i <= h; i++) { // 차례로 사다리 만들어보기
			for (int k = 1; k < n; k++) {
				if (ladder[i][k] == 1 || ladder[i][k] == 2 || ladder[i][k + 1] == 1 || ladder[i][k + 1] == 2)
					continue;
				ladder[i][k] = 1;
				ladder[i][k + 1] = 2;
				solve(i, depth + 1);
				ladder[i][k] = 0;
				ladder[i][k + 1] = 0;
			}
		}

	}

	public static boolean check_move() { // 각자 자기 찍는지 확인
		for (int k = 1; k <= n; k++) {
			int current_y = k;
			for (int i = 1; i <= h; i++) {
				int current_index = ladder[i][current_y];
				if (current_index == 1) {
					current_y++;
				} else if (current_index == 2) {
					current_y--;
				}
			}

			if (current_y != k) { // 자기 안찍으면 false
				return false;
			}

		}
		return true;
	}
}
