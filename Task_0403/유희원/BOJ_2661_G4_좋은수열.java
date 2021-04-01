package BFSDFS;

import java.util.Scanner;

/*
 * 임의의 길이의 인접한 두개의 부분수열이 동일한 것이 있으면 나쁜수열
 * 길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 출력
 */
public class BOJ_2661_G4_좋은수열 {

	static int N;
	static int[] arr;
	static boolean isEnd = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		dfs("");

	}
	private static void dfs(String string) {
		if(isEnd) return;
		//길이 N인 정수?
		if(string.length()==N) {
			//처음으로 여기서 걸리는 좋은 수열 출력해버리고 끝.
			System.out.println(string);
			isEnd = true;
			return;
		}
		for (int i = 1; i <4; i++) {
			if(isGood(string+i)) dfs(string+i);
		}
		
	}
	private static boolean isGood(String string) {
		int len = string.length();
		//한글자씩 비교하면 안됨. 
		//ex. 1212 -> 한글자씩 비교할 경우 true, 하지만 실제론 아님
		for (int i = 1; i <=len/2; i++) { //1글자부터 절반길이까지 해봄
			String str1 = string.substring(string.length()-2*i,string.length()-i);
			String str2 = string.substring(string.length()-i,string.length());
			if(str1.equals(str2)) return false;
		}
		return true;
	}

}
