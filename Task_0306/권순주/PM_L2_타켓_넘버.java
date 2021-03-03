/***
 * <문제 요약> 
 * 구해야 하는 것 : 타겟 넘버를 만드는 방법의 수
 * 문제 핵심 요약 : 더하고 빼고의 합을 기억해서 target과 비교해서 answer 증가 
 * <풀이법 요약>
 * 기저조건
 * 1-1. numbers의 끝까지 방문했다면 -> 1-2
 * 1-2. 만약 sum이 target과 같다면 answer 증가
 * 1-3. return
 * 
 * 재귀
 * 1. number를 더해서
 * 2. number를 빼서
 */

public class PM_L2_타켓_넘버 {

	int answer = 0;

	public int solution(int[] numbers, int target) {
		dfs(numbers, target, 0, 0); // sum :0 cnt : 0으로 시작
		return answer;
	}

	private void dfs(int[] numbers, int target, int sum, int cnt) {
		if (cnt == numbers.length) { // 기저조건 -> numbers의 끝까지 방문했으면 return
			if (target == sum) { // 만약 합이 target과 같다면 
				answer++; // answer 증가
			}
			return;
		}
		dfs(numbers, target, sum + numbers[cnt], cnt + 1); // 더하기
		dfs(numbers, target, sum - numbers[cnt], cnt + 1); // 빼기
	}

	public static void main(String[] args) {
		PM_L2_타켓_넘버 pm = new PM_L2_타켓_넘버();
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(pm.solution(numbers, target));
	}

}
