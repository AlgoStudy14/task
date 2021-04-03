package algo210403;
/**
 * <문제 요약>
 * 구해야 하는 것: 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현할 수 있는 방법 중
 * N의 사용횟수의 최솟값
 * 제약 사항: N은 1이상 9이하 / number은 1 이상 32000 이하
 * 수식은 괄호와 사칙연산만 가능, 나누기 연산에서 나머지는 무시
 * 최솟값이 8보다 크면 -1을 return
 * 문제 유형: DP
 * 요구 개념: DP
 * 
 * <풀이법 요약>
 * 1. dfs
 * 2. cnt는 8보다 크지 못함을 이용
 * 3. cnt 범위 => 기저조건 주의!
 */
public class PM_Level3_N으로표현_dp {

	public static void main(String[] args) {
		PM_Level3_N으로표현_dp pm = new PM_Level3_N으로표현_dp();
		
		System.out.println(pm.solution(5, 12));
		System.out.println(pm.solution(2, 11));
		System.out.println(pm.solution(5, 31168));
	}

	static int nn;
	static int min = Integer.MAX_VALUE;
	public int solution(int N, int number) {
		
		min = Integer.MAX_VALUE;
		dfs(N, number, 0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

	
}
