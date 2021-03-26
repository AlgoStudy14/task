package sw_a_prac;

import java.util.Scanner;

/*
 * 각 이용권 요금과 각 달의 이용 계획 입력으로 주어질 때 
 * 수용장을 이용할 수 있는 가장 적은 비용 출력
 */
public class SWEA_1952_수영장 {

	static int T, ans;
	static int[] costs, plans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			costs = new int[4];
			plans = new int[12];
			for (int i = 0; i <4; i++) {
				costs[i] = sc.nextInt();
			}
			for (int i = 0; i <12; i++) {
				plans[i] = sc.nextInt();
			}
			//여기 integer.maxvalue했다가 틀림 (47/50)
			ans = costs[3];
			dfs(0,0);
			System.out.println("#"+t+" "+ans);
		}
	}
	private static void dfs(int cnt, int total) {
		if(cnt>=12) {
			ans = Math.min(total, ans);
			return;
		}
		//이용안하면 안삼
		if(plans[cnt] == 0) dfs(cnt+1,total);
		//이용 하는 달이면 이용횟수만큼 1일 사용권으로 채우기
		if(plans[cnt]>0) dfs(cnt+1,total+plans[cnt]*costs[0]);
		//1달 사용권
		dfs(cnt+1,total+costs[1]);
		dfs(cnt+3,total+costs[2]);
		//10월까지만 3달 사용권 사용 시도 가능
		//if(cnt<10) dfs(cnt+3,total+costs[2]);
		//if(cnt<10)이거에 따라 위에 cnt==12로 바꿨을때 코드: 0.14443s
		//지금 이 코드가 0.14982s
		//0.14982s vs 0.14443s
	}

}
