package algo210403;

import java.util.Scanner;
/**
 * <문제 요약> 
 * 구해야 하는 것: 인접한 부분 수열이 동일한 수열이 아닌 수열 중 가장 작은 것 구하기
 * 제약 사항: 1,2,3으로만 이루어진 수열  
 * 문제 유형: 백트래킹 
 * 요구 개념: 백트래킹 
 * 
 * <풀이법 요약> 
 * 1. 사실 모르겠어서 검색함... => 문자열 인덱스로 파싱해서 비교하는거... 제대로 이해하고 넘어가야겠다!
 * 이부분을 몰라서 못풀겠었음 ㅠㅠ
 * 
 */
public class BOJ_Gold4_2661_좋은수열 {

	static int N;
	static boolean end;
	static String answer;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N = scann.nextInt();
		
		dfs(1, "");
		
		System.out.println(answer);
	}
	
	private static void dfs(int size, String str) {
		if(end) return;
		
		if(size == N+1) {
			answer = str;
			end = true;
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			String next = str + i;
			
			int len = size/2;
			boolean success = true;
			for (int j = 1; j <= len; j++) {
				String first = next.substring(size-j-j, size-j);
				String second = next.substring(size-j, size);
				if(first.equals(second)) {
					success = false;
					break;
				}
			}
			
			if(success) dfs(size+1,next);
		}	
	}
}