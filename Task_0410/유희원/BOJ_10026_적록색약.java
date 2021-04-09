package algo0410;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/* G5
 * R,G,B으로만 이루어진 N*N map 주어짐
 * 같은 색상으로 붙어있는 부분을 한 구역으로 카운팅
 * 적록색약이 없는 사람과 있는 사람이 인식하는 구역의 수 출력하는 문제
 */
public class BOJ_10026_적록색약 {

	static int N,cnt1,cnt2;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				 map[i][j] = s.charAt(j);
			}
		}
		//정상 먼저 세고
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt1++;
					dfs(i,j);
				}
			}
		}
		//적,록 하나로 합치고
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='G') map[i][j] = 'R';
			}
		}
		visited = new boolean[N][N];
		//적록색약 cnt
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt2++;
					dfs(i,j);
				}
			}
		}
		System.out.println(cnt1+" "+cnt2);
	}
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		//4방탐색돌면서 방문
		for (int d = 0; d <4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0&&nc>=0&&nr<N&&nc<N && !visited[nr][nc]) {
				//같은 색이면
				if(map[nr][nc] == map[r][c]) dfs(nr,nc);
			}
		}
	}

}
