package algo210306;
/**
 * <문제 요약>
 * 구해야 하는 것: n개의 음이 아닌 정수를 적절히 더하거나 빼서 타겟 넘버를 만드는 수 카운팅 
 * 제약 사항: 모든 숫자를 다 이용해야 한다. 
 * 문제 유형: 백트래킹(DFS) 
 * 요구 개념: 백트래킹(DFS) 
 * 
 * <풀이법 요약>
 * 1. 0번 인덱스부터 시작해주기 위해서 idx에 -1부터 담음
 * 2. 백트래킹을 돌면서 확인
 *
 *
 */
public class PM_L2_타겟넘버 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,1,1,1,1}, 3));
	}
	
	static int cnt = 0;
    public static int solution(int[] numbers, int target) {
    	// 0번 인덱스부터 시작해주기 위해서 idx에 -1부터 담음 
    	DFS(-1, 0, numbers, target);
        return cnt;
    }
	private static void DFS(int idx, int sum, int[] num, int target) {
		// 전체 배열의 길이와 맞고, 타겟 넘버와 맞으면 출력
		if(idx == num.length-1) {
			if(sum == target) cnt++;
			return;
		}
		// 더할때 
		DFS(idx+1, sum+num[idx+1], num, target);
		// 뺄 때 
		DFS(idx+1, sum-num[idx+1], num, target);
	}
}
