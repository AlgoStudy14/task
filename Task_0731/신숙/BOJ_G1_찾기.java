import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_G1_찾기 {
	/*
	 * <문제>
	 * KMP 문자열 알고리즘.
	 * 
	 * <문제풀이>
	 * 찾으려는 문자열의 접두사와 접미사 길이를 담은 PI
	 * 이를 이용해 전체 문자열에서 중복 제거하며 일치하는 문자열 있는지 체크
	 * 문자열 시작 위치 인덱스를 리스트에.
	 */
	static ArrayList<Integer> list;
	static int[] pi;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String pt = br.readLine();
		list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		pi = getPi(pt);
		KMP(s, pt);
		
		for(int i : list)
			sb.append(i).append(" ");
		
		System.out.println(list.size());
		System.out.println(sb.toString());
	}
	
	static int[] getPi(String pt) {
		int leng = pt.length();
		int[] pi = new int[leng];
		int n = 0;
		for(int i = 1; i < leng; i++) {
			while(n > 0 && pt.charAt(i)!= pt.charAt(n))
				n = pi[n - 1];
			if(pt.charAt(i) == pt.charAt(n))
				pi[i] = ++n;
		}
		return pi;
	}
	
	static void KMP(String s, String pt) {
		int j = 0;
		int len = s.length();
		int ptLen = pt.length();
		
		for(int i = 0; i < len; i++) {
			//다르다면 같았던 다음 접미사로 바로 건너뜀 
			while(j > 0 && s.charAt(i) != pt.charAt(j))
				j = pi[j - 1];
			//해당 인덱스의 문자 두개가 같다면
			if(s.charAt(i) == pt.charAt(j)) {
				//pattern 문자열 전체가 같다면
				if(j + 1 == ptLen) {
					//전체 문자열 중 패턴 문자열과 같은 문자열의 시작 인덱스
					list.add(i - ptLen + 2);
					j = pi[j];
				}else // 다음문자를 가지고 비교
					j++;
			}
		}
	}
}
