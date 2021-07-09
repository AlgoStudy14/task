package study0710;
/*
 * 최악의 경우 N,M이 1000이 될수 있기 때문에 완탐은 불가능.
 * 다이나믹 프로그래밍 사용해야 된다.
 * 어려운문제
 * 위에서 내려올때, 양옆에서 올때를 생각해서 해줘야된다
 * 그렇기에 tt[2][M]로 한줄이 가능한 경우를 양옆에서 오는경우를 나눠서 해줘야된다.
 * 맨 첫줄은 무조건 왼쪽에서 오른쪽으로 가는일밖에 없기 때문에 먼저 해준다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2169_로봇 {
	static int [][] arr;
	static int [][] dp;
	static int dx[] = {1,-1,0};
	static int dy[] = {0,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		arr= new int[N][M];
		dp=new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(i==0) dp[i][j]=arr[i][j];
			}
		}
		//가장 윗 행
		for (int c = 1; c < M; c++) {
			dp[0][c]+=dp[0][c-1];
		}
		for (int r = 1; r < N; r++) {
			int [][] tt = new int[2][M];
			
			tt[0][0]=arr[r][0]+dp[r-1][0];
			for (int c = 1; c < M; c++) {
				tt[0][c]=Math.max(tt[0][c-1], dp[r-1][c])+arr[r][c];
			}
			
			tt[1][M-1] = arr[r][M-1]+dp[r-1][M-1];
			for (int c = M-2; c >=0; c--) {
				tt[1][c]=Math.max(tt[1][c+1], dp[r-1][c])+arr[r][c];
				
			}
			for(int c=0;c<M;c++) {
				dp[r][c]=Math.max(tt[0][c], tt[1][c]);
			}
		}
		System.out.println(dp[N-1][M-1]);
	}

}
