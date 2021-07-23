package algo0724;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16916_부분문자열 {

	static int ans=0;
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j=0;
		for(int i=1;i<pattern.length();i++) {
			//맞는 위치가 나올때까지 j-1칸의 공통부분 위치로 이동
			while(j>0 && pattern.charAt(i)!=pattern.charAt(j)) {
				j = pi[j-1];
			}
			//맞는 경우
			if(pattern.charAt(i)==pattern.charAt(j)) 
				//i길이 문자열의 공통 길이는 j의 위치+1
				pi[i] = ++j;
		}
		return pi;
	}
	
	static void KMP(String origin, String ptn) {
		int[] pi = getPi(ptn);
		int j=0;
		//맞는 위치가 나올때까지 j-1칸의 공통 부분 위치로 이동
		for(int i=0;i<origin.length();i++) {
			while(j>0 && origin.charAt(i)!=ptn.charAt(j)) {
				j=pi[j-1];
			}
			//맞는 경우
			if(origin.charAt(i)==ptn.charAt(j)) {
				if(j==ptn.length()-1) {
					ans=1;
					break;
				}
				//맞았지만 패턴이 끝나지 않았다면 j 하나 증가
				else
					j++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		KMP(origin,pattern);
		System.out.println(ans);
	}
}
