import java.util.Scanner;

public class BOJ_Gold4_17281 {
	/*
	 * <⚾ 야구>
	 * 기본적으로 야구의 일반룰
	 * 3 아웃 -> 공격 종료 -> 수비 or 수비 -> 3아웃 -> 공격 전환
	 * 경기 시작전 타순을 정해야 함.
	 * 9번타자까지 공을 쳤는데 3아웃이 아니면 다시 1번타자.
	 * 볼넷은 없고, 1번타자의 타순은 무조건 4번타자. 타순을 정할 때, 주어진 결과을 순열로 최대 몇 득점을 할 수 있는지
	 * 
	 * <풀이>
	 * 이닝 수인 N(2<= && <= 50)이 주어지고, 각 이닝마다 타자가 치는 결과가 주어짐.
	 * 타순을 정해야 하니, 1번 타자가 4번 타순에 있는 순열을 짜고, 이 순열의 cnt == 9가 되면 점수계산.
	 * 
	 * 뭐때문에 계속 틀리지????
	 * 	=> 아. while문 안에서 hit = baseball[ining][now % 9] 가 아니라 baseball[ining][order[now % 9]];
	 */
	static int N, ans;
	static int[][] baseball;
	static int[] order;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		baseball = new int[N][9];
		order = new int[9];
		visited = new boolean[9];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 9; j++)
				baseball[i][j] = sc.nextInt();
		}
		//1번타자 4번 타순 설정?
		order[3] = 0;
		visited[3] = true;
		perm(1);			//1번 타자가 4번타순에 있으니 2번 타자인 1 부터.
		System.out.println(ans);
	}
	static void perm(int cnt) {
		if(cnt == 9) {
			int ining = 0;		//1이닝부터 N이닝
			int out = 0;		//3아웃 체크
			int now = 0;
			int score = 0;
			boolean[] base = new boolean[3];	//잔루 확인
			while(ining < N) {
				while(out < 3) {
					int hit = baseball[ining][order[now % 9]];
					now++;
					switch(hit) {
						case 0:
							out++;
							break;
						case 1:
							if(base[2])
								score++;
							if(base[1])
								base[2] = true;
							else
								base[2] = false;
							if(base[0])
								base[1] = true;
							else
								base[1] = false;
							base[0] = true;
							break;
						case 2:
							if(base[2]) {
								score++;
								base[2] = false;
							}
							if(base[1])
								score++;
							if(base[0]) {
								base[2] = true;
								base[0] = false;
							}
							base[1] = true;
							break;
						case 3:
							if(base[2])
								score++;
							if(base[1]) {
								score++;
								base[1] = false;
							}
							if(base[0]) {
								score++;
								base[0] = false;
							}
							base[2] = true;
							break;
						case 4:
							if(base[2])
								score++;
							if(base[1])
								score++;
							if(base[0])
								score++;
							score++;
							for(int i = 0; i < 3; i++)
								base[i] = false;
							break;
					}
				}
				//3아웃 시.
				out = 0;
				ining++;
				base = new boolean[3];
			}
			ans = Math.max(ans, score);
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			order[i] = cnt;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
