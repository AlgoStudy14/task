package algo0319;
import java.util.LinkedList;
import java.util.Queue;
/*
 * bfs문제
 * 원자의 움직임이 정해져 있는 평면상의 [x,y]가 충돌하여 에너지를 방출할 때 총합을 구하는 문제
 * 중요한 점은 1차이나는 지점에서 충돌하면 0.5에서 충돌한다.
 * 그렇기 때문에 편하게 생각하기 위해서 각범위를 2배씩 늘려준다.
 * 이럴경우 충돌하면 0으로 된다.
 * 즉 (1000-(-1000)+1) *2 = 4002
 * 2000을 중심 좌표로 사용한다는 듯이다.
 * 충돌을 알리기 위해서는?  현재 위치에 map[][]값이 자신의 k보다 크면 자신 외에 다른 원자와 부딪혀서 생긴일로 취급한다.
 * 그래서 arr[][]값에 res를 더하고 continue;
 * 위에 해당 없으면 0으로 초기화하고 이동해야 할 위치를 구한다..
 * map[][]이 0이면 이동 가능하니까 큐에 넣어주고 또 이동
 */
import java.util.Scanner;

public class Solution_5648_원자소멸 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx= { 0, 0, -1, 1 };
	static Queue<int[]> que = new LinkedList<int[]>();
	static int res;
	static int[][] arr= new int[4002][4002];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N=sc.nextInt();
			res=0;
			for(int i=0;i<N;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				int d=sc.nextInt();
				int ene=sc.nextInt();
				if(ene==0) ene=101;
				que.add(new int[] {x*2+2000,y*2+2000,d,ene});
			}
			bfs();
			System.out.println("#"+t+" "+res);
		}

	}
	private static void bfs() {
		int cntzero=0;
		while(!que.isEmpty()) {
			int p[]=que.poll();
			int x=p[0];
			int y=p[1];
			int d=p[2];
			int ene=p[3];
			if(arr[y][x]>ene) {
				System.out.println(1);
				if(ene==101)
					cntzero++;
				res+=arr[y][x];
				arr[y][x]=0;
				continue;
			}
			arr[y][x]=0;
			//System.out.println(2);
			int ry=y+dy[d];
			int rx=x+dx[d];
			if(ry<0||rx<0||ry>4000||rx>4000) continue;
			if(arr[ry][rx]==0) {
				que.add(new int[] {rx,ry,d,ene});
			}
			else {
				if(ene==101)
					cntzero++;
			}
			arr[ry][rx]+=ene;
		}
		res-=cntzero*101;
	}
}
