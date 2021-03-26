package sw_a_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 위에서 구슬 N개 떨어트려서 벽돌 깨기
 * 벽돌에는 숫자가 있는데 그 숫자-1만큼 사방으로 영향 끼쳐서 같이 터지는 형식
 * 연쇄적으로 터질 수 있음
 * 벽돌 깨고 난 후 벽돌들은 아래로 떨어진다
 * 최대한 많은 벽돌을 깨서 남은 벽돌의 개수 출력
 */
public class SWEA_5656_벽돌깨기 {

	static int N,W,H,ans,burstCnt;
	static int[][] map;
	static class Node{
		int r, c, cnt;
		public Node(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//구슬개수
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			//W가 가로 H가 세로임;;;;;
			map = new int[H][W];
			ans = Integer.MAX_VALUE;
			int total = 0; //전체 벽돌 개수. 입력받을때 미리 개수 세어둠
			for (int i = 0; i <H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) total++;
				}
			}
			DFS(0,total,map);
			
			System.out.println("#"+t+" "+ans);
		}

	}
	//사용한 구슬 수, 남은 벽돌 수, map
	private static boolean DFS(int cnt, int remain, int[][] map) {
		if(remain == 0) {
			ans = 0;
			return true; //남은 벽돌이 없으면 더이상 확인할 필요X
		}
		if(cnt == N) {
			ans = Math.min(remain, ans);
			return false; //이건 다른 경우 확인해봐야함
		}
		int[][] newMap = new int[H][W];
		//좌우로만 움직이면서 떨구기 때문에 모든 열에 돌려봄
		for (int i = 0; i < W; i++) {
			int r = 0;
			//젤 위에있는 벽돌 찾기. H보다 작으면서(범위 내에 있으면서) 
			while(r<H&&map[r][i] == 0) r++;
			//여기까지왔으면 해당 열에 벽돌없는거.
			if(r==H) continue;
			//여기가 실행된다는건 벽돌이 있는거. 일단 map 복사
			for (int j = 0; j < H; j++) {
				for (int k = 0; k <W; k++) {
					newMap[j][k] = map[j][k];
				}
			}
			burstCnt = 0;
			//r,i좌표로 구슬 떨어트려서 벽돌 터트려보기
			bomb(newMap, r,i,newMap[r][i]);
			
			//다 터졌으면 벽돌 밑으로 내리기
			for (int cc = 0; cc <W; cc++) {
				int rr = H-1;
				while(rr>0) {
					//밑에서부터 빈공간찾기
					if(newMap[rr][cc]==0) {
						//직전행부터 탐색해서
						int nr = rr-1;
						//처음만나는 벽돌
						while(nr>0&&newMap[nr][cc]==0) nr--;
						//벽돌 찾아서 그거 rr,cc위치에 넣고 찾은 벽돌은 0으로
						newMap[rr][cc] = newMap[nr][cc];
						newMap[nr][cc] = 0;
					}
					rr--;
				}
			}
			//다음구슬 dfs. 만약에 dfs가 true, 즉 더 확인할 필요가 없으면 return true;
			if(DFS(cnt+1,remain-burstCnt,newMap)) return true;
		}
		return false;
		
	}
	private static void bomb(int[][] map, int r, int c, int cnt) {
		//일단 벽돌 깨고 시작
		map[r][c] = 0;
		//깨진 벽돌수 체크체크~
		burstCnt++;
		//벽돌의 숫자가 1이면 걍 걔만 깨고 끝이기 때문에 return
		if(cnt==1) return;
		for (int d = 0; d <4; d++) {
			int nr = r;
			int nc = c;
			//숫자-1만큼의 범위 4방탐색으로 깸
			for (int k = 0; k <cnt-1; k++) {
				nr+=dr[d];
				nc+=dc[d];
				if(nr>=0&&nc>=0&&nr<H&&nc<W&&map[nr][nc]!=0) {
					bomb(map,nr,nc,map[nr][nc]);
				}
			}
		}
		
	}

}
