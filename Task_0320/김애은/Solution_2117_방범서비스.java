package algo0319;
import java.util.LinkedList;
import java.util.Queue;
/*
 * bfs
 * 각점마다 모든 bfs를 다 돈다.
 * bfs에서 N+1만큼 for문을 돌아야함. 짝수일때는 N으로 하면 마지막을 안돈다.
 * for문을 하나 더 써서 que size만큼 level만큼 돈다.
 * 이렇게 해야 비용 손해 집개수 체크 가능하다
 */
import java.util.Scanner;

public class Solution_2117_방범서비스 {
	static int arr[][];
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx= { 0, 0, -1, 1 };
	static int house,tot,N,M,cur;
	static boolean V[][];
	static Queue<int[]> que;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			house=0;
			tot=0;
			N=sc.nextInt();
			M=sc.nextInt();
			tot=0;
			arr=new int [N][N];
			que=new LinkedList<int[]>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j]=sc.nextInt();
					if(arr[i][j]==1) tot++;
				}
			}
			go();
			System.out.println("#"+t+" "+house);
		}

	}
	private static void go() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]==1) cur=1;
				else cur=0;
				que.clear();
				V=new boolean[N][N];
				V[i][j]=true;
				que.add(new int[] {i,j});
				bfs();
			}
		}
		
	}
	private static void bfs() {
		int cost=1; //현재 금액
		for(int i=1;i<=N+1;i++) {
			cost+=(i-1)*4;
			if(cur>0) {
				if((cur*M)-cost>=0) {
					if(house<cur) house=cur;
					if(house==tot) return;
				}
			}
			int size=que.size();
			for(int j=0;j<size;j++) {
				int p[] =que.poll();
				int y=p[0];
				int x=p[1];
				for(int d=0;d<4;d++) {
					int ny=y+dy[d];
					int nx=x+dx[d];
					if(nx<0 || nx>=N||ny<0||ny>=N) continue;
					if(arr[ny][nx]==1)cur++;
					V[ny][nx]=true;
					que.add(new int[] {ny,nx});
				}
			}
		}
		
	}

}
