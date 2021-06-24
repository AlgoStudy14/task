package study0626;
/*
 * 기본 bfs...
 * 인데 또 실수함
 * ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
 * 1,1부터 m,n까지 돌아서 몇번만에 가는지 보는 문제이다.
 * 문제는 !! 입력 잘 확인할것
 *  N, M(2 ≤ N, M ≤ 100)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_미로탐색_2178 {
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int [][]arr= new int[N][M];
		int check[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			String s = st.nextToken();
			char aa[]=s.toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j]=aa[j]-'0';
			}		
		}
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {0,0});
		check[0][0]=1;
		int dx[]= {0,0,-1,1};
		int dy[]= {1,-1,0,0};
		while(!que.isEmpty()) {
			int []p = que.poll();
			int y=p[0];
			int x=p[1];
			for(int i=0;i<4;i++) {
				int my = y + dy[i];
				int mx = x + dx[i];
				if(my<0 || mx<0 || my>=N || mx>=M)continue;
				if(check[my][mx]>0)continue;
				if(arr[my][mx]==0)continue;
				que.add(new int[] {my,mx});
				check[my][mx]=check[y][x]+1;
			}
		}
		System.out.println(check[N-1][M-1]);
	}

}
