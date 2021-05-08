import java.util.Scanner;

/*
 * <문제 요약>
 * 좌상단 좌표와 우상단 좌표가 주어졌을 때 사각형 합구하기
 * 
 * <풀이법 요약>
 * (1,1)을 좌상단기준, 해당 좌표를 우하단 좌표로 만들어지는 사각형 내 합 구해서 dp[][]에 저장
 * 원하는 좌표값(x,y)을 받아 (x,y) - (x-1,y) - (x,y-1) + (x-1, y-1)를 해서 해당 사각형을 구함
 * 
 * <문제점>
 * 1. 처음에 그냥 이중포문돌렸다가 시간초과
 * 2. 포문 한번은 괜찮겠지하고 자기까지 더한 구간합을 기준으로 포문돌렸다가 시간초과
 * 
 * 다양한 방법으로 구간합을 지정할 수 있다는것을 깨달음...
 */


public class Main_BOJ_11660_구간합구하기 {
	static int N,M;
	static int arr[][];
	static int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	static int dp[][];
	static int sum[];
	
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        sum = new int[M];
        
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }
        
        
        for(int k = 0; k < M; k++) {
    
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            
            sum[k] += dp[x2][y2]-dp[x2][y1-1]-dp[x1-1][y2]+dp[x1-1][y1-1];
 
        }
        
        for(int k = 0; k < M; k++) {
        
            System.out.println(sum[k]);
        }
 
    }
 
}
