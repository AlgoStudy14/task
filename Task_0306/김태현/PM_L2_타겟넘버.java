package BFSDFS;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 타겟 넘버를 만드는 방법의 개수 
 * 문제 유형 : 완전 탐색, DFS 
 * <풀이법 요약> 
 * DFS 풀이를 이용한다. 
 * 1. 배열의 숫자를 더하거나 빼면서 재귀를 진행한다. 
 * 2. 길이가 배열의 크기가 되면, 재귀를 종료한다.
 */

public class PM_L2_타겟넘버 {

	public static void main(String[] args) {
		PM_L2_타겟넘버 doit = new PM_L2_타겟넘버();
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		doit.solution(numbers, target);
	}

	static int val = 0;
	static int cnt = 0;

	public int solution(int[] numbers, int target) {
		dfs(numbers, target, 0, 0);
		return cnt;
	}

	private void dfs(int[] numbers, int target, int val, int depth) {
		// n개의 숫자를 모두 연산했으면, 재귀를 종료.
		if (depth == numbers.length) {
			// 만일, 종료된 값이 타겟 값과 같다면 cnt++.
			if (val == target) {
				cnt++;
			}
			return;
		}
		// 부분 집합의 논리, 값을 추가하거나 더하여 dfs를 진행한다.
		dfs(numbers, target, val + numbers[depth], depth + 1);
		dfs(numbers, target, val - numbers[depth], depth + 1);
	}
}
