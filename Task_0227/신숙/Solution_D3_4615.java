package SW_Expert;

import java.util.Scanner;

public class Solution_D3_4615 {
	/*
	 * 오셀로
	 * 
	 * 1. 2차원 배열에 빈공간은 0, 흑돌은 1, 백돌은 2로 설정
	 * 2. 입력을 받으면 해당위치에 돌을 두고, 8방 탐색을 하여 바뀌는 것이 있다면 오셀로 규칙에 따라 다음 돌 색이 있기까지 변경.
	 */
	static int T, N, M, W, B;
	static int[][] othello;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}; 		//왼쪽 대각선위부터 시계방향
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			W = 0; B = 0;
			int color = 0;
			int r = 0, c = 0;
			othello = new int[N][N];
			//기본 상태 설정.
			othello[N/2][N/2] = 2;
			othello[N/2 - 1][N/2] = 1;
			othello[N/2][N/2 - 1] = 1;
			othello[N/2 -1][N/2 - 1] = 2;
			
			for(int i = 0; i < M; i++) {
				r = sc.nextInt() - 1;
				c = sc.nextInt() - 1;
				color = sc.nextInt();
				othello[r][c] = color;
				//8방 탐색
				for(int j = 0; j < 8; j++)
					ot(r + dr[j], c + dc[j], j, color, false, 0);
			}
			//출력을 위한 개수세기.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(othello[i][j] == 2)
						W++;
					else if(othello[i][j] == 1)
						B++;
				}
			}
			System.out.println("#" + t + " " + B + " " + W);
		}
	}
	
	static void ot(int r, int c, int d, int color, boolean flag, int count) {
		//r, c 가 범위를 벗어나거나, 색이 같거나, 0인 빈칸이면 종료.		=> 흠 같은색일땐 따로 판정해야겠다.
		if(r < 0 || c < 0 || r >= N || c >= N || othello[r][c] == 0)
			return;
		//사이에 다른 돌이 없었는데, 처음 만난 돌이 같은색이면 종료.
		if(!flag && othello[r][c] == color)
			return;
		//사이에 다른 색의 돌이 있었고(2번 이상의 호출, 있다면 flag == true), 같은 색을 만났다면 그 사이를 같은색으로 바꿔야함. => 현재는 같은색인 것을 가르킴.
		if(flag && othello[r][c] == color) {
			int rr = r - dr[d];
			int cc = c - dc[d];
			for(int i = 0; i < count; i++) {
				othello[rr][cc] = color;
				rr = rr - dr[d];
				cc = cc - dc[d];
			}
			return;
		}
		//범위도 배열 안이고 현재 있는 위치가 0이 아닌 색이 있고, 처음 함수를 시작한 돌과 같은 색이 아닐때.
		// 추가로 같은색을 만난 적이 있었으면 다음 것도 한번은 봐야하니까 조건 추가해줌.
		//현재 한번도 호출이 안됨. 왜?	=> 이상한거랑 비교하고있엇음.
		// 흠...재귀로 하니까 너무 오래걸림... => 근데 통과됏네?? 심지어 실행시간도 짧네. 가지치기가 잘되었나보다.
		if(flag || color == 1 && othello[r][c] == 2) {
			ot(r + dr[d], c + dc[d], d, color, true, count + 1);
		}else if(flag || color == 2 && othello[r][c] == 1) {
			ot(r + dr[d], c + dc[d], d, color, true, count + 1);
		}
	}
}
