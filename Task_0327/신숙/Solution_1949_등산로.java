import java.util.ArrayList;
import java.util.Scanner;

public class Solution_1949_등산로 {
	/*
	 * 등산로 조성
	 * 
	 * 3 <=N <= 8.   1 <= K <= 5
	 * 수가 별로 안큰거 보니, dfs로~ 공사는 1번만 가능.
	 * 
	 * 답				내 답
	 *  #1 6			6
	 	#2 3			3
		#3 7			7
		#4 4			4
		#5 2			2
		#6 12			12
		#7 6			6
		#8 7			7
		#9 10			10
		#10 19			19
	 * 왜 5번만..? 뭐가틀린겨??
	 * 아 다른데 만 너무 봐서 왜 틀린지도 몰랐네....
	 */
	static class XY{
		int x, y;
		XY(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	static int T, N, K, maxM, ans;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<XY> list;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			ans = 0;
			maxM = 0;
			list = new ArrayList<XY>();
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] > maxM) {
						maxM = map[i][j];
						list.clear();
						list.add(new XY(i, j));
					}else if(map[i][j] == maxM)
						list.add(new XY(i, j));
				}
			}
			for(XY a : list) {
				visited[a.x][a.y] = true;
				dfs(a.x, a.y, 1, map[a.x][a.y], false);
				visited[a.x][a.y] = false;
			}
			System.out.println("#" + t + " " + ans);
		}
	}
	static void dfs(int r, int c, int cnt, int value, boolean cons) {
		int nr, nc;
		ans = Math.max(ans, cnt);
		for(int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			//범위를 넘어가거나 다음꺼가 이미 방문된거면 패스...
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
				continue;
			
			//만약 다음 값이 더 작다면 공사 없이 재귀.	
			if(map[nr][nc] < value) {
				visited[nr][nc] = true;
				dfs(nr, nc, cnt+1, map[nr][nc], cons);
				visited[nr][nc] = false;
			}
			//만약 다음 값이 더 작거나 같다면 공사를 시도해야함
			else {
//				//공사를 했다면(cons가 공사여부) 종료. 
//				if(cons) {
//					ans = Math.max(ans, cnt);
//					return;
//				}
//				int temp = 0;
//				temp = map[nr][nc];
//				//하지 않았다면  K수를 한번씩 빼면서  한 칸에 공사 시도.
//				for(int a = 0; a < K; a++) {
//					temp--;
//					//K내의 값으로 공사를 하여서 value보다 작다면, boolean은 true로 값은 공사를 한 temp로 넘김.
//					if(temp < value) {
//						visited[nr][nc] = true;
//						dfs(nr, nc, cnt+1, temp, true);
//						visited[nr][nc] = false;
//						break;
//					}
//				}
				//	위가 답이 안나오게 된 이유. cons에서 종료를 시켜버리기 때문에 값 하는걸 위에 빼고
				if(!cons) {
					int temp = 0;
					temp = map[nr][nc];
					//하지 않았다면  K수를 한번씩 빼면서  한 칸에 공사 시도.
					for(int a = 0; a < K; a++) {
						temp--;
						//K내의 값으로 공사를 하여서 value보다 작다면, boolean은 true로 값은 공사를 한 temp로 넘김.
						if(temp < value) {
							visited[nr][nc] = true;
							dfs(nr, nc, cnt+1, temp, true);
							visited[nr][nc] = false;
							break;
						}
					}
				}
			}
				
		}
	}
}
