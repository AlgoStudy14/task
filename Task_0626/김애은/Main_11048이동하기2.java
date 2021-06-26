package study0626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * dp문제
 * 이동하기 문제
 * (1,1)칸부터 위, 왼, 위왼 중 가장 큰값을 자신의 값에 더한다.
 * 문제에서는 자신이 갈 수 있는 곳이 오 아 오아 였기 때문에 반대로 생각하면 된다.
 * (N,M)까지는 arr[N][M]의 값이 답이다.
 */
public class Main_11048이동하기2 {
	static int N,M;
	static int arr[][];
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		for(int i=1;i<N+1;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<M+1;j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<M+1;j++) {
				arr[i][j]+=Math.max(arr[i][j-1],Math.max(arr[i-1][j], arr[i-1][j-1]));
			}
		}
		System.out.println(arr[N][M]);
	}

}
