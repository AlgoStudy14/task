import java.util.Scanner;

public class PM_L3_N으로_표현 {
	/*
	 * N과 수가 주어지면, 해당하는 수를 N을 이용해서 사칙연산과 수로 표현
	 * 
	 * DFS는 생각나는데 dp는 어떻게 풀지?? DP문젠디	=> 너무느린데...? + 답이 1 낮음.
	 * 			=> count + i 가 아니라 count + 1을 했네....  => 빨라짐
	 *	1 <= N <= 9   최솟값이 8보다 크면 -1 리턴
	 *
	 *=> 5,31168 오답. ??? 왜 -1이 아닌가.
	 */
	static int ans;
	public static void main(String[] args) {
		ans = solution(5, 31168);
		System.out.println(ans);
	}
	
	public static int solution(int n, int number) {
		ans = Integer.MAX_VALUE;
		dfs(n, number, 0, 0);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
	
	static void dfs(int n, int number, int sum, int count) {
		int temp = n;
		//최솟값 8보다 크면 -1 리턴
		if(count > 8) {
			ans = -1;
			return;
		}
		if(number == sum) {
			ans = Math.min(ans, count);
			return;
		}
		for(int i = 1; i < 9 - count; i++) {
			dfs(n, number, sum + temp, count+i);
			dfs(n, number, sum - temp, count+i);
			dfs(n, number, sum * temp, count+i);
			dfs(n, number, sum / temp, count+i);
			temp = temp * 10 + n;
		}
	}
}