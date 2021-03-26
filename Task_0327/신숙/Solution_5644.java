import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644 {
	/* 
	 * 아니 이제 문제 이해하는것부터 어렵네.
	 * 
	 * 이동정보 값
	 * 0 : 정지 	1 : 상	 2: 우 	3: 하	 4: 좌
	 * 지도는 10,10 고정.
	 * 
	 * <조건>
	 * 1. A, B가 같은 BC에 접근할 때, 반으로 나누어주어야함. 근데 그 위치에 BC가 2개 중첩되어있으면 하나씩 따로???
	 * 2. 지도 밖으로 가는 경우는 없음.
	 * 
	 * 주어진 시간(M)만큼 진행함.
	 * 문제는 전체시간 합이 최대가 되어야함.
	 * 		=> 각 움직인 시간마다 최대의 값을 찾으면 최대의 값이 됨.
	 * 
	 *  1. 위치마다 충전이 가능한 BC를 모두 List에 넣음.
	 *  2. 두 List의 사이즈가 0이면 그 위치는 Pass.
	 *  3. 하나의 List만 0이라면 다른 List의 최대값만 확인하면됨.
	 *  4. 둘다 0이 아닐때, 둘의 경우의 수를 보아서 최대 충전량 확인.
	 */
	static int[][] bc;
	static int[][] user;
	static int[][] move;
	static int M, A, T, ans;
	static int[] dr = {0, 0, 1, 0, -1};
	static int[] dc = {0, -1, 0, 1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			ans = 0;			
			bc = new int[A][4];
			user = new int[2][2];
			move = new int[2][M + 1];			
			user[0][0] = 1;		//A.x
			user[0][1] = 1;		//A.y
			user[1][0] = 10;	//B.x
			user[1][1] = 10;	//B.y
			//움직임을 저장하는 배열 Move
			for(int i = 0 ; i < 2 ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M ; ++j)
					move[i][j] = Integer.parseInt(st.nextToken());
			}
			//BC들 정보를 저장한 배열
			for(int i = 0 ; i < A ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 4 ; ++j)
					bc[i][j] = Integer.parseInt(st.nextToken());
			}
			//움직임에 따라서 충전을 계산해야하는데, 조건에 따라서 초기 위치부터 확인을 해야하므로, 함수먼저.
			for(int i = 0 ; i <= M ; ++i) {
				charge();
				user[0][0] += dr[move[0][i]];
				user[0][1] += dc[move[0][i]];
				user[1][0] += dr[move[1][i]];
				user[1][1] += dc[move[1][i]];
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void charge() {
		ArrayList<Integer> bcForA = new ArrayList<>();
		ArrayList<Integer> bcForB = new ArrayList<>();
		//해밍턴 거리가 해당BC의 C값 내외라면 A에 차징된 리스트에 BC의 p값 저장. 
		for(int i = 0 ; i < A ; ++i) {
			if(Math.abs(user[0][0] - bc[i][0]) + Math.abs(user[0][1] - bc[i][1]) <= bc[i][2])
				bcForA.add(i);
			if(Math.abs(user[1][0] - bc[i][0]) + Math.abs(user[1][1] - bc[i][1]) <= bc[i][2])
				bcForB.add(i);
		}
		int ap = 0, bp = 0, max = 0;
		//둘 다 0이면 확인할 경우가 없으므로 종료
		if(bcForA.isEmpty() && bcForB.isEmpty())
			return;
		//A 사이즈가 0이면, 남은것은 B값들이 있으므로 이것을 크기 비교하여 저장.
		else if(bcForA.isEmpty()) {
			for(int b : bcForB)
				//저장되어있는 BC의 p 값이 가지고 있는 값보다 작으면 p값 저장 아니면 기존 값 그대로.
				bp = Math.max(bc[b][3], bp);
		}
		else if(bcForB.isEmpty()) {
			for(int a : bcForA)
				ap = Math.max(bc[a][3], ap);
		} 
		//둘 다 값이 있을 때에는 각각의 위치에서 가장 큰 값을 계산해야함.
		else {
			for(int a : bcForA) {
				for(int b : bcForB) {
					int sum = 0;
					//같은 값이 들어갈 땐 제약조건에 따라 크기를 반으로 나눔. 그 후 해당 움직임에서 가장 값이 큰 것을 저장.
					if(a == b) {
						sum = bc[a][3];
						
						if(sum > max) {
							ap = sum / 2;
							bp = sum / 2;
							max = sum;
						}
					} else {
						sum = bc[a][3] + bc[b][3];

						if(sum > max) {
							ap = bc[a][3];
							bp = bc[b][3];
							max = sum;
						}
					}
				}
			}
		}
		ans += ap + bp;
	}
}
