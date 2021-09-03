package 하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_20058_마법사상어와파이어스톰 {
	static int arr[][];
	static int n,c;
	static int mm = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		arr = new int[2^n][2^n];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		int num=0;
		int max=0;
		for (int i = 0; i < c; i++) {
			num=Integer.parseInt(st.nextToken());
			dfs(num);
		}
		for (int i = 0; i < (2^n); i++) {
			for (int j = 0; j < (2^n); j++) {
				max+=arr[i][j];
			}
		}
		System.out.println(max);
		System.out.println(mm);
		
	}
	private static void dfs(int move) {
		int loop = n/(move^2);
		int next [][] = new int[n][n];
		int y=0;
		int x=0;
		for (int i = 0; i < loop; i++) {
			x=0;
			if(i!=0)
				y+=i^2;
			for (int j = 0; j < loop; j++) {
				if(j!=0)
					x+=j^2;
				int m=0;
				for (int a = 0; a < (2^move); a++) {
					for (int b = 0; b < (2^move); b++) {
						next[y+a][x-b+(2^move)-1]=arr[y+a][x+b];
						m+=arr[y+a][x+b];
					}
				}
				mm=Math.max(m, mm);
			}
		}
		int dy[]= {-1,1,0,0};
		int dx[] = {0,0,-1,1};
		Queue<int[]> que = new LinkedList<>();
		for (int i = 0; i < (2^n); i++) {
			for (int j = 0; j < (2^n); j++) {
				int cnt=0;
				for (int k = 0; k < 4; k++) {
					int ny=i+dy[k];
					int nx=j+dx[k];
					if(ny<0 || nx<0 || ny>=(2^n)||nx>=(2^n)) continue;
					if(next[ny][nx]>=1)cnt++;
					if(cnt<3)
						que.add(new int[] {i,j});
				}
			}
		}
		while(!que.isEmpty()) {
			int cur[]=que.poll();
			next[cur[0]][cur[1]]--;
		}
		arr=next;
	}

}
