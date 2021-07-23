import java.util.Scanner;

public class BOJ_G4_부분문자열 {
	/*
	 * <문제>
	 * 문자열의 부분문자열이란 문자열의 연속된 일부의 의미.
	 * "aek", "joo", "ekj"는 baekjoon의 부분문자열 bak 이나 p oone 은 아니다.(1글자는 아님)
	 * S와 P가 주어졌을 때, P가 S의 부분문자열인지 판단하라
	 * 문자열이면 1 아니면 0 출력
	 * 
	 * <문제풀이>
	 * 100만 * 100만이라 다 비교하게 되면 시간초과날듯
	 * GG
	 * 
	 * <KMP 알고리즘>
	 * Origin의 문자열은 i를 통해 문자를 가리키고, Pattern 문자열은 j를 통해 문자를 가르킴.
	 * Origin[i] == Pattern[j]면 i, j 모두 1씩 더함.
	 * 일치하지 않고 j != 0 라면, j=pi[j -1]을 통해 그 이전의 문자로 돌아감.
	 * 일치하지 않고 j가 0이라면 i만 1을 더함.
	 * 
	 */
	static String S, P;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.next();
		P = sc.next();
		
		kmp(S, P);
		System.out.println(ans);
	}
	
	static void kmp(String org, String ptn) {
		int[] list = getPi(ptn);
		int j = 0;
		for(int i = 0; i < org.length(); i++) {
			while(j > 0 && org.charAt(i) != ptn.charAt(j))
				j = list[j - 1];
			if(org.charAt(i) == ptn.charAt(j)) {
				if(j == ptn.length() - 1) {
					ans = 1;
					break;
				}else
					j++;
			}
		}
	}
	
	//접미사 접두사의 일치 길이를 나타내는 배열을 반환하는 함수.
	static int[] getPi(String ptn) {
		int leng = ptn.length();
		int[] pi = new int[leng];
		int n = 0;
		for(int i = 1; i < leng; i++) {
			while(n > 0 && ptn.charAt(i)!= ptn.charAt(n))
				n = pi[n - 1];
			if(ptn.charAt(i) == ptn.charAt(n))
				pi[i] = ++n;
		}
		return pi;
	}
}
