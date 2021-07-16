package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 치즈 개수를 저장한다.
 * bfs를 반복 cnt를 저장하고 time 감소시킨다.
 * bfs는 0,0에서 시작
 */
public class Main_치즈_2636 {
	static int N,M;
	static int arr[][];
	static boolean check[][];
	static int ch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==1)
					ch+=1;
			}
		}
		int cnt=0;
		int temp=0;
		while(ch>0) {
			check=new boolean[N][M];
			cnt++;
			temp=ch;
			go();
		}
		System.out.println(cnt);
		System.out.println(temp);
		
	}
	private static void go() {
		int dx[]= {0,0,-1,1};
		int dy[] = {1,-1,0,0};
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {0,0});
		check[0][0]=true;
		while(!que.isEmpty()) {
			int p[] = que.poll();
			int x=p[1];
			int y=p[0];
			for(int i=0;i<4;i++) {
				int my=y+dy[i];
				int mx=x+dx[i];
				if(my<0 || mx< 0 || my>=N||mx>=M)continue;
				if(arr[my][mx]==0 && !check[my][mx]) {
					que.add(new int[] {my,mx});
					check[my][mx]=true;
				}
				else if(arr[my][mx]==1) {
					ch-=1;
					arr[my][mx]=0;
					check[my][mx]=true;
				}
		
			}
		}
	}

}
