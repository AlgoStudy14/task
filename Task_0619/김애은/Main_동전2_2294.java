package algo0619;
import java.util.Arrays;
/*
 * 1에서 응용한 문제...
 * 바보
 * 
 * 
 * <문제 요약>
 * 구해야 하는 것:가치의 합이 k원되도록 만들기
 * 제약 사항: (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 
 * 문제 유형: dp
 * 요구 개념: dp
 * 
 * <풀이법>
 *  동전의 가치가 최대 100000이기 때문에 최악의 경우 100000개 그래서 +1해서 초기화
 *  이 경우 최소를 알고 싶은것이기 때문에 지금까지 될 수 있는것에서 하나만 더하면 되는걸로 계산해야 된다.
 * 1 2 5가 있을때
 * 5는
 * 5일수도 있지만 2 2 1,2 1 1 1, 1 1 1 1 1 이런식으로 될 수 있다.
 * 그렇기 때문에 3까지 구한거에서 2 더한 값, 이런식으로 되야 된다.
 * 즉 dp[j]+=dp[j-arr[i]];이렇게 성립한다는거


 */
import java.util.Scanner;

public class Main_동전2_2294 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int []arr=new int[n+1];
		int []dp = new int[k+1];
		for(int i=1;i<=n;i++) {
			arr[i]=sc.nextInt();
		}
		Arrays.fill(dp, 100001);
		dp[0]=0;
		for(int i=1;i<=n;i++) {
			for(int j=arr[i];j<=k;j++) {
				dp[j]=Math.min(dp[j], dp[j-arr[i]]+1);
			}
		}
		if(dp[k]==100001)System.out.println(-1);
		else System.out.println(dp[k]);
	}
	

}
