package study0501;
/*
 * 누적합 사용해서 빼주기
 * 65%에서 시간초과남
 * 
 * 다른 방법
 * 입력받은 x1, y1으로 새로운 행렬을 만들어 값을 다 더해줬다.
 * 그리고 빼주는 작업을 하며 값을 프린트 하였다.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_구간합구하기 {
	
	static int N,M;
	static int arr[][];
	static int sum[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N+1][N+1];
		sum=new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=N;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				sum[i][j]=sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+arr[i][j];
			}
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine()," ");
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			System.out.println(sum[x2][y2]-sum[x2][y1-1]-sum[x1-1][y2]+sum[x1-1][y1-1]);
		}
	}
}
