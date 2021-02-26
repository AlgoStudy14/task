package imprepare.problems;

import java.util.Scanner;

//B로만 이루어진, 특히 s2만 B인 경우를 똑바로 안걸러냄
public class SWEA_7272_안경이없어 {

	static String ans;
	//static char[] zero = {};
	//static char[] two = {'B'};
	//처음에 구멍 한개짜리 중에 'P' 빼먹어서 한참 헤맸음....
	static char[] one = {'A','D','O','P','Q','R'};
	static boolean isone,isone2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		isone = false;
		isone2 = false;
		for (int t = 1; t <=T; t++) {
			ans = "DIFF";
			String s1 = sc.next();
			String s2 = sc.next();
			//길이가 다르면
			if(s1.length()==s2.length()) {
				
				for (int i = 0; i <s1.length(); i++) {
					//이거 안해줘서 ABA ABB 같은 경우 못걸러냄
					ans = "DIFF";
					isone = false;
					isone2 = false;
					// s1 B고
					if(s1.charAt(i)=='B') {
						//s2 도 B면 다음 자리 가면서 비교
						if(s2.charAt(i)=='B') {
							if(i==s1.length()-1) ans = "SAME";
							continue;
						}
						//ans = "DIFF";
						//다르면 다른거니까 걍 다음 테케로 ㄱㄱ
						break;
					}
					//s1 이 B가 아닌데 s2가 B면
					else if(s2.charAt(i)=='B') break;
					//여기로 들어온다는건 그 자리가 일단 B는 아님 -> 하나냐 개냐 비교
					for (int j = 0; j <6; j++) {
//					isone = (one[j]==s1.charAt(i))?true:false; 
//					if(!isone) break;
						//구멍 한개짜리 알파벳이면
						if(one[j]==s1.charAt(i)) {
							isone = true;
							break;
						}
					}
					
					for (int j = 0; j <6; j++) {
//					isone2 = (one[j]==s2.charAt(i))?true:false; 
//					if(!isone2) break;
						if(one[j]==s2.charAt(i)) {
							isone2 = true;
							break;
						}
					}
					//둘이 속한 집합이 다르면 - xor: 다르면 1, 같으면 0
					if(isone^isone2) {
						ans = "DIFF";
						break;
					}
					ans = "SAME";
				}
			}
			
			System.out.println("#"+t+" "+ans);
			//System.out.println(isone+" "+isone2);
		}

	}

}


