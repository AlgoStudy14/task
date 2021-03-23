package algo210327;

import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 1년동안 수영장을 이용하는 경우 가장 적게 지출하는 비용 
 * 제약 사항: 각 달의 이용 계획은 각 달의 마지막 일자보다 크지 않다. 
 * 문제 유형: dfs -> 완전탐색 
 * 요구 개념: dfs 
 * 
 * <풀이법 요약>
 * 1. dfs를 이용한 풀이법
 * 2. 먼저 해당 달에 1일 이용권, 1달 이용권을 사용하는 것 중 최소를 정하기 
 * -> 최소인 경우에만 재귀를 타고 들어가서 가지 치기 효과 
 * 3. 3월 이상이면, 해당 달 3달 전부터의 비용을 2번에서 구한 값과 비교하여 넣어주기 
 * 4. 마지막에 12월에 있는 비용과 해당 해에 사용한 비용을 비교하기
 */
public class Solution_1952_수영장_완탐 {

	static int TC, dayP, monthP, threeMonthP, yearP, answer;
	static int[] use;
	public static void main(String[] args) {

		Scanner scann = new Scanner(System.in);
		
		TC = scann.nextInt();
		for (int t = 1; t <= TC; t++) {
			dayP = scann.nextInt();
			monthP = scann.nextInt();
			threeMonthP = scann.nextInt();
			yearP = scann.nextInt();
			
			use = new int[13];
			
			for (int i = 1; i <= 12; i++) {
				use[i] = scann.nextInt();
			}
			
			answer=Integer.MAX_VALUE;
			
			dfs(0, 0);
			
			System.out.print("#"+t+" ");
			if(answer>yearP) System.out.println(yearP);
			else System.out.println(answer);
		}
	}
	private static void dfs(int month, int sum) {
		
		if(month>=13) {
			answer = Math.min(answer, sum);
			return;
		}
		
		if(use[month]*dayP > monthP) dfs(month+1, sum+monthP);
		else dfs(month+1, sum+use[month]*dayP);
		
		dfs(month+3, sum+threeMonthP);
	}
}
