package imprepare.problems;

import java.util.Scanner;

public class SWEA_4615_재미있는오셀로게임 {

	//상우하좌 상좌 상우 하우 하좌
	static int[] dr = {-1,0,1,0,-1,-1,1,1};
	static int[] dc = {0,1,0,-1,-1,1,1,-1};
	static int[][] map, arr;
	static int T,N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			//보드
			map = new int[N][N];
			//탐색할 좌표 저장할 배열
			arr = new int[N][2];
			int b = 0;
			int w = 0;
			//가운데에 돌 두개씩 놓고 시작. 
		    //2: B, 1:W
			map[N/2][N/2] = 2;
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			//M번동안
			for (int i = 0; i <M; i++) {
				int r = sc.nextInt()-1; //index 0부터 시작하니까 하나씩 빼줌
				int c = sc.nextInt()-1;
				int k = sc.nextInt(); //r,c에 k돌 놓기. (1또는 2)
				map[r][c] = k;
				//탐색할 방향이 8가지임;;
				for (int j = 0; j <8; j++) {
					int nr = r+dr[j];
					int nc = c+dc[j];
					//범위 안에 있고 돌이 놓여져 있으면 dfs호출
					if(nr>=0&&nc>=0&&nr<N&&nc<N&&map[nr][nc]!=0) dfs(nr,nc,dr[j],dc[j],k,0);
				}
				
			}
			//완성하면 개수 카운팅
			for (int i = 0; i <N; i++) {
				for (int j = 0; j <N; j++) {
					if(map[i][j]==1) b++;
					if(map[i][j]==2) w++;
				}
			}
			System.out.println("#"+t+" "+b+" "+w);
		}
	}
	//좌표를 나타낼 r,c값, 방향을 나타낼 ddr,ddc값, 돌의 종류인 k값, 그리고 idx
	private static void dfs(int r, int c, int ddr, int ddc, int k, int idx) {
		//기저 조건. 만약에 같은 돌 만나면 교체할게 있는 경우임 쭉쭉 바꿔줌~~
		if(map[r][c]==k) {
			for (int i = 0; i <idx; i++) map[arr[i][0]][arr[i][1]] = k;
			return;
		}
		//r,c에서 시작. 여기에 탐색하는 좌표값을 담아가면서 기저조건 만난 후 return할때 for문으로 목표 돌(k)로 바꿔줄예정
		arr[idx][0] = r;
		arr[idx][1] = c;
		//그 방향으로 더 가서
		int nr = r+ddr;
		int nc = c+ddc;
		//범위 안에 있으면
		if(nr>=0&&nc>=0&&nr<N&&nc<N) {
			//재귀~
			if(map[nr][nc]!=0) dfs(nr,nc,ddr,ddc,k,idx+1);
		}
		//조건을 만족 안했다는건 바꿀게 없는거 (기준 돌에서 다음거 봤더니 벗어났거나 돌이없거나
		arr[idx][0] = 0;
		arr[idx][1] = 0;
		
	}

}
