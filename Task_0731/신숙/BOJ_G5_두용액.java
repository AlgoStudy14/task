import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G5_두용액 {
	/*
	 * <문제>
	 * 산성 : 1 ~ 1,000,000,000 / 알칼리 : -1 ~ -1,000,000,000
	 * 같은 양의 두 용액을 혼합하여 특성값이 0에 가까운 용액을 만들려고 한다.
	 * 0에 가장 가까운 특성값을 얻어내는 용액값 출력.
	 * 
	 * <문제 풀이>
	 * 용액의 수 N이 2 이상, 100,000 이하.
	 * 투 포인터
	 * 
	 * <문제 오답>
	 * 1. 왜 계속 리스트에 없는 0이 나오는거지??	=> liquid 범위가 N + 1 이였음...
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
				//System.out.println("ans[0] : " + ans[0]);
				ans[1] = liquid[end];
				//System.out.println("ans[1] : " + ans[1]);
				INF = Math.abs(sum);
			}
			
			if(sum > 0)
				end--;
			else
				start++;
		}
	}
}
