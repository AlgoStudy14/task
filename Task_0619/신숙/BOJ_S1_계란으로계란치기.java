import java.util.Scanner;

public class BOJ_S1_계란으로계란치기 {
	/*
	 * <문제 요약>
	 * 계란에는 내구도와 무게가 있음. 계란으로 계란을 치게 되면 계란의 내구도는 상대 계란의 무게만큼 깎임. 내구도 0 이하면 깨짐.
	 * 	1. 가장 왼쪽의 계란을 든다.
	 * 	2. 들고 있는 계란으로 깨지지 않은 것 중 1개를 침. 손에 든 것이 깨졌거나, 깨지지 않은 다른 계란이 없으면 치지 않고 넘어감. 다시 내려둠. 
	 * 	3. 들었던 오른쪽의 계란을 들고 2번 진행. 마지막으로 가장 우측의 계란을 들었다면 이를 종료.
	 * 계란의 내구도와 무게가 주어질 때, 최 대 몇 개의 계란을 깰 수 있는가
	 * 
	 * <조건>
	 * 1 <= 계란개수(N) <= 8 
	 * 
	 * <풀이 : 백트래킹>
	 * 1. N의 범위가 적으므로 백트래킹 가능.
	 * 2. 달걀이 내구도와 무게로 주어지므로 이를 행이 2개인 배열(혹은 클래스로 생성)로 관리.
	 * 3. 백트래킹을 하며 깨진 것의 개수를 올리며, 배열의 마지막을 들었을 때 종료 가능.
	 * 
	 * <오답>
	 * 1. 왜 첫번째 테스트케이스가 3이 될 수가 있지?	=> 바로 오른쪽만 때리는게 아니네..
	 * 
	 * <피드백>
	 * 오랜만에 문제를 풀어서 그런지 문제 이해를 잘 못하고 있음.
	 */
	static int N, ans;
	static int[][] egg;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		egg = new int[N][2];
		for(int i = 0; i < N; i++) {
			egg[i][0] = sc.nextInt();
			egg[i][1] = sc.nextInt();
		}
		back(0);		//왼쪽부터
		System.out.println(ans);
	}
	
	static void back(int n) {
		//마지막 껄 들었을 때
		if(n == N) {
			int s = 0;
			for(int i = 0; i < N; i++) {
				if(egg[i][0] <= 0)
					s++;
			}
			ans = Math.max(ans, s);
			return;
		}
		//지금 들고 있는 계란이 깨졌을 때
		if(egg[n][0] <= 0) {
			back(n + 1);
		}else {	//아닐때 오른쪽꺼 때리기
			boolean brk = true;
			for(int i = 0; i < N; i++) {		//이게 왜 n+1이 아니라 0부터 해야 답이나오는거지?? 남은건 오른쪽 중에서 일 것 같은데...
				if(i == n || egg[i][0] <= 0)
					continue;
				brk = false;
				egg[i][0] -= egg[n][1];
				egg[n][0] -= egg[i][1];
				back(n+1);
				egg[i][0] += egg[n][1];
				egg[n][0] += egg[i][1];
			}
			if(brk)
				back(n+1);
			/*
				if(egg[i][0] < 0)
					continue;
				egg[i][0] -= egg[i+1][1];
				egg[i+1][0] -= egg[i][1];
				back(n);
			}*/
		}
	}
}
