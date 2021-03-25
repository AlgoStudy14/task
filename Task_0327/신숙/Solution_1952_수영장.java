import java.util.Scanner;

public class Solution_1952_수영장 {
	/*
	 * 1년 동안 각 달마다 수영장을 이용할 계획표가 있음.
	 * 
	 * 수영장에서
	 * 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권        4가지 종류가 있음.
	 * 
	 * 이걸 조합해서 가장 적게 내는 비용을 구하라.
	 * 
	 * <문제풀이>
	 * 답이 나올 변수를 1년 이용권으로 초기화. dfs로 1월부터 체크하면서 3가지 티켓팅하는 방법을 비교 
	 * 
	 * 			=> 왜 중간중간 답이 0인게 껴있지??? 답이 0이 될 수가 있나....	=> sum이 0이 될 경우밖에없는데 그럴수가있음?
	 * 			=> 보니까 답이 나오는 경우는 첫번째 항 값이 0이 아닐때만.
	 * 			=> 0인경우를 제외하고 계산을 해보아도 뭔가 이상하게나옴.
	 * 			=> 아... 괜히 앞에 0이 아닐때만 답이 나오는게 아니네.... cal 계산에 넣을 때 인자를 i로 넣었음. 다시 깔끔하게 ㄱㄱ
	 */
	static int T, D, M, Th, Y, ans;
	static int[] cal;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			D = sc.nextInt();
			M = sc.nextInt();
			Th = sc.nextInt();
			Y = sc.nextInt();
			cal = new int[12];
			for(int i = 0; i < 12; i++)
				cal[i] = sc.nextInt();
			ans = Y;
			dfs(0, 0);
			System.out.println("#" + t + " " + ans);
		}
	}
	static void dfs(int sum, int month) {
		if(month > 11) {
			ans = Math.min(ans, sum);
			return;
		}
		//0인 경우는 이용권 고려없이 바로 다음달로 넘어가면 됨.		=> 왜 처음이 0이면 다음으로 안넘어갈까
		if(cal[month] == 0)
			dfs(sum, month+1);
		else {
			for(int i = 0; i < 3; i++) {
				//1일 이용권
				if(i == 0)
					dfs(cal[month] * D + sum, month+1);
				//1달 이용권
				else if(i == 1) 
					dfs(sum + M, month+1);
				//3달 이용권
				else
					dfs(sum + Th, month+3);
			}
		}
	}
}
