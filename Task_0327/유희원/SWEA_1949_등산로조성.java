package sw_a_prac;
import java.util.Scanner;
/*
 * 제일 높은 곳에서 시작해서 높은 곳->낮은곳으로 가는 가로/세로로 구성된 등산로 조성
 * 딱 한번 최대 K 깊이만큼 깎아서 진행 가능
 * 가장 긴 등산로 길이 출력
 */
public class SWEA_1949_등산로조성 {

	static int T, N, K, len, height;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			len = height = 0;
			map = new int[N][N];
			visit = new boolean[N][N];
			
			//지도 정보 입력받기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					//입력받으면서 최대높이 저장
					if(height<map[i][j]) height = map[i][j];
				}
			}
			startPoint();
			System.out.println("#"+t+" "+len);
		}

	}
	private static void startPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//최대높이인 부분에서 dfs
				if(map[i][j] == height) {
					visit[i][j] = true;
					dfs(i,j,height,1,0);
					visit[i][j] = false;
				}
			}
		}
		
	}
	private static void dfs(int x, int y, int height, int length, int shaveCount) {
		for (int d = 0; d <4; d++) {
			if(len<length) len = length;
			int nr = x + dr[d];
			int nc = y + dc[d];
			if(nr>=0&&nc>=0&&nr<N&&nc<N&&!visit[nr][nc]) {
				if(height<=map[nr][nc]) { //원래는 이동 불가능할때
					if(shaveCount == 0) { //깎았을 때 지금보다 작아지면
						if(height > map[nr][nc]-K) {
							visit[nr][nc] = true;
							//깎아서 지나갈 수 있어도 최대한 적게 깎는게 유리하기때문에 height는 -1만
							dfs(nr,nc,height-1,length+1,shaveCount+1);
							visit[nr][nc] = false;
						}
					}
				}else {
					visit[nr][nc] = true;
					dfs(nr,nc,map[nr][nc],length+1,shaveCount);
					visit[nr][nc] = false;
				}
			}
		}
		
	}

}
