package study0626;
/*
 * dp문제
 * dp[i][j] 는 처음부터 i번째까지 물건을 보고, 배낭의 용량이 j였을 때 배낭에 들어간 물건의 가치합의 최대
 *  N(1 ≤ N ≤ 100)개수  K(1 ≤ K ≤ 100,000)무게
 *  용량이 J인 배낭에 i번째 물건을 넣기전에 최대값은 dp[i-1][j]이다.
 *  넣었을 때 상황을 보면 dp[i-1][j-w[i]]+v[i]가 된다.
 */
import java.util.Scanner;

public class Main_12865_평범한배낭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		int w[] = new int[101]; //무개 100개
		int v[] = new int[101]; //가치 100개
		int dp[][] = new int[101][100001];
		for(int i=1;i<=N;i++) {
			w[i]=sc.nextInt();
			v[i]=sc.nextInt();
		}
		int max= 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				if(j-w[i]>=0) { // 여유공간 있는지 확인
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
