package study0626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11048이동하기 {
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
		go(1,1,arr[1][1]);
		System.out.println(max);
	}
	private static void go(int y, int x, int k) {
		if(y==N && x==M) {
			if(k>max)max=k;
			return;
		}
		if(y+1<=N && x+1<=M) {
			go(y+1,x+1,k+arr[y+1][x+1]);
		}
		if(y+1<=N) {
			go(y+1,x,k+arr[y+1][x]);
		}
		if(x+1<=M) {
			go(y,x+1,k+arr[y][x+1]);
		}
		return;
	}
}
