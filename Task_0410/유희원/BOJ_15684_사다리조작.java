package algo0410;

import java.util.Scanner;
/* G4
 * 주어진 사다리에 가로선을 추가해서 
 * 사다리의 출발=도착 이도록 조작
 */
public class BOJ_15684_사다리조작 {

	static int N,M,H,ans;
	static int[][] map;
	static boolean done = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		//인덱스 그대로 번호로 쓰기위해 1씩 더해줌
		map = new int[H+1][N+1];
		
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			//y번 세로선과 y+1선을 x행에서 연결할건데 1,2로 저장해서 방향 결정
			map[x][y] = 1;
			map[x][y+1] = 2;
		}
		//추가해야되는 선이 3개 초과일 경우 걍 -1
		for (int i = 0; i <= 3; i++) {
			ans = i;
			dfs(1,0);
			if(done)break;
		}
		System.out.println(done?ans:-1);
	}
	private static void dfs(int x, int cnt) {
		//선 추가하고 성공했으면 종료
		if(done) return;
		//깊이 끝까지 탐색했으면 return
		if(ans == cnt) {
			if(check()) done = true;
			return;
		}
		for (int i = x; i < H+1; i++) {
			//j+1이 들어가기때문에 1부터 N까지
			for (int j = 1; j <N; j++) {
				//양옆이 0일때: 연결 안된 부분
				if(map[i][j]==0&&map[i][j+1]==0) {
					//선 그어봄
					map[i][j] = 1;
					map[i][j+1] = 2;
					dfs(i,cnt+1);
					//재귀 돌리고 초기화
					map[i][j] = map[i][j+1] = 0;
				}
			}
		}
		
	}
	//선 연결하고 시작-끝 같은 값 됐는지 확인
	private static boolean check() {
		for (int i = 1; i <=N; i++) {
			int x = 1, y = i;
			for (int j = 0; j < H; j++) {
				if(map[x][y]==1) y++; //오른쪽
				else if(map[x][y]==2)y--; //왼쪽
				x++; //아래로
			}
			//다 내려왔을때 다른 번호면 실패
			if(y!=i) return false;
		}
		return true;
	}

}
