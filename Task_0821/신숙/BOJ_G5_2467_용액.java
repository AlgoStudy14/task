import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G5_2467_용액 {
	/*
	 * <문제>
	 * 산성은 1 ~ 1,000,000,000
	 * 알칼리는 -1 ~ -1,000,000,000
	 * 같은 양의 혼합한 용액의 특성값 = 사용된 각 용액의 특성값의 합. 같은양 두 용액을 혼합하여 특성값 0에 가까운 용액을 만들고자함.  

	 * <문제 풀이>
	 * 용액의 수 N이 2 이상, 100,000 이하.
	 * 투 포인터
	 * 이전에 풀어본 것 같은 그런 느낌적 느낌.
	 * 
	 */
	static int N;
	static int[] liquid, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		liquid = new int[N];
		ans = new int[2];
		for(int i = 0; i < N; i++)
			liquid[i] = sc.nextInt();
		
		Arrays.sort(liquid);
		binarySearch();
		System.out.println(ans[0] + " " + ans[1]);
	}
	
	static void binarySearch() {
		int start = 0;
		int end = N - 1;
		int INF = Integer.MAX_VALUE;
		
		while(start < end) {
			int sum = liquid[start] + liquid[end];
			
			if(Math.abs(sum) < INF) {
				ans[0] = liquid[start];
				ans[1] = liquid[end];
				INF = Math.abs(sum);
			}
			
			if(sum > 0)
				end--;
			else
				start++;
		}
	}
}