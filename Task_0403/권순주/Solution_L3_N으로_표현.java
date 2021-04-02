public class Solution_L3_N으로_표현 {

	private int answer = 9; // 나올 수 있는 최대값으로 지정

	public int solution(int N, int number) {
		dfs(N, number, 0, 0);
		return answer == 9 ? -1 : answer;
	}

	private void dfs(int N, int number, int cnt, int current) {

		if (cnt > 8) { // 최솟값이 8보다 크면 -1 return
			answer = -1;
			return;
		}

		if (current == number) { // number를 만들었을 때
			answer = Math.min(answer, cnt); // 최솟값 저장
			return;
		}

		int tempN = N;
		for (int i = 0; i < 8 - cnt; i++) { // 8개 중에 남은 개수만큼 삭칙연산을 사용한다
			dfs(N, number, cnt + i + 1, current + tempN);
			dfs(N, number, cnt + i + 1, current - tempN);
			dfs(N, number, cnt + i + 1, current / tempN);
			dfs(N, number, cnt + i + 1, current * tempN);

			tempN = tempN * 10 + N; // 5 -> 55 -> 555 -> ...
		}
	}

	public static void main(String[] args) {
		Solution_L3_N으로_표현 pm = new Solution_L3_N으로_표현();
		int N = 5;
		int number = 12;
		System.out.println(pm.solution(N, number));
	}
}