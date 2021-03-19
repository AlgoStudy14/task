package algo0316;
/*
 * 탈주범이 위치할 수 있는 장소 개수 구하기
 * 직접 다 그려보면 풀 수 있는 문제!
 * 중요한건 현재 위치 파이트와 다음 파이프, 다음 위치 방향!
 * 이걸 다 고려하다 보니 코드가 복잡해짐..
 * 왼쪽 체크할때는 현재 위치 파이프가 왼쪽으로 되어있고 다음 파이프가 오른쪽으로 되어있을때 이동
 * 오른쪽은 현재 위치파이프 오른쪽 다음위치 파이프가 왼쪽일대 가능
 * 위쪽은 위쪽으로 열려있고 다음위치 파이프가 아래쪽일때
 * 아래는 연해 위치 아래 다음위치 위쪽
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1953탈주범2 {
	static int arr[][];
	static int cnt,N,M;
	static boolean V[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N=sc.nextInt();
			M=sc.nextInt();
			int R=sc.nextInt();
			int C=sc.nextInt();
			int L=sc.nextInt();
			cnt=1;
			arr=new int[N][M];
			V=new boolean[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					arr[i][j]=sc.nextInt();
				}
			}
			//System.out.println(R+" "+C);
			go(R,C,L);
			
			System.out.println("#"+t+" "+cnt);
		}
	}
	private static void go(int r, int c, int l) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[]{r,c});
		V[r][c]=true;
		while(!que.isEmpty()) {
			int size = que.size(); //bfs 사이즈 만큼씩 돌게 하기
			//System.out.println(size+"size");
			if(--l<=0) return;
			//System.out.println(123);
			for(int i=0;i<size;i++) {
				int q[]=que.poll();
				int rr=q[0];
				int cc=q[1];
				int type=arr[rr][cc]; //현재 위치 파이프 타입
				//System.out.println(type);
				int dy[]= {-1,1,0,0};//아래 위 왼 오
				int dx[]= {0,0,-1,1};
				for(int d=0;d<4;d++) {
					int nr = rr+dy[d];
					int nc = cc+dx[d];
					if(nr<0||nr>=N||nc<0||nc>=M) continue;
					if(V[nr][nc] || arr[nr][nc]==0) continue;
					//System.out.println(i+"sd"+nr+" qwe "+nc);
					int next= arr[nr][nc]; //다음 위치 파이프 타입
					if(d==0) {//다음위치 방향!
						if (type == 1 || type == 2 || type == 4 || type == 7) {
							if (next == 1 || next == 2 || next == 5 || next == 6) {
								V[nr][nc] = true;
								que.offer(new int[] { nr, nc });
								cnt++;
							}
						}
					}
					else if(d==1) {
						if (type == 1 || type == 2 || type == 5 || type == 6) {
							if (next == 1 || next == 2 || next == 4 || next == 7) {
								V[nr][nc] = true;
								que.offer(new int[] { nr, nc });
								cnt++;
							}
						}
					}
					else if(d==2) {
						if (type == 1 || type == 3 || type == 6 || type == 7) {
							if (next == 1 || next == 3 || next == 4 || next == 5) {
								V[nr][nc] = true;
								que.offer(new int[] { nr, nc });
								cnt++;
							}
						}
					}
					else if(d==3) {
						if (type == 1 || type == 3 || type == 4 || type == 5) {
							if (next == 1 || next == 3 || next == 6 || next == 7) {
								V[nr][nc] = true;
								que.offer(new int[] { nr, nc });
								cnt++;
							}
						}
					}
				}
			}
		}
		
	}
	
}
