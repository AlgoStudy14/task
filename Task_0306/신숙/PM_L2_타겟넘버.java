package DFS_BFS;

public class PM_L2_타겟넘버 {
	/*
	 * 양수의 수 배열이 주어지면 이 수들을 이용해 더하거나 빼서 타겟 넘버를 만들려고 함.
	 * 수는 2~20개
	 * 숫자는 각 1~50
	 * 타겟 넘버는 1~1000
	 * 
	 * 문제만 보면 DFS?
	 * 근데, 개수가 20이니까 가지치기가 필요해보임.	=> 어떻게?
	 * 
	 * DFS로는 그냥 sum에 더할때랑 뺄 때 두번 재귀 돌리면 될 듯.
	 * 	=>	테스트 1 〉	통과 (12.93ms, 54MB)
			테스트 2 〉	통과 (6.44ms, 52.5MB)
		통과는 했지만 시간은 확실히 오래 걸리는 듯..?
	 */
	static int answer;
	public static int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
	
	static void dfs(int cnt, int sum, int[] numbers, int target) {
		if(cnt == numbers.length) {
			if(sum == target)
				answer++;
			return;
		}
		dfs(cnt+1, sum+numbers[cnt], numbers, target);
		dfs(cnt+1, sum-numbers[cnt], numbers, target);
	}
	
	public static void main(String[] args) {
		int answer = solution(new int[] {1,1,1,1,1}, 4);
		System.out.println(answer);
	}
}
