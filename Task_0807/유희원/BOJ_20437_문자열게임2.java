package algo0806;

import java.util.Scanner;

public class BOJ_20437_문자열게임2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int[] alpha = new int[26];
			String str = sc.next();
			int k = sc.nextInt();
			int len = str.length();
			//문자열 알파벳 갯수 세고
			for (int i = 0; i < len; i++) {
				alpha[str.charAt(i)-'a']++;
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < len; i++) {
				int cur = str.charAt(i);
				//k개 미만의 문자일 경우 탐색할 필요가 없음
				if(alpha[cur-'a']<k) continue;
				int count = 0;
				for (int j = i; j < len; j++) {
					if(cur == str.charAt(j)) count++;
					//어떤 문자 정확히 k개 포함한 순간!
					if(count==k) {
						//min 구할땐 더 안나가도 되니까 멈추고
						min = Math.min(min, j-i+1);
						//max 구할땐 시작과 끝이 같으니 여기서 같이 멈춤
						max = Math.max(max, j-i+1);
						break;
					}
				}
			}
			if(min!=Integer.MAX_VALUE) System.out.println(min+" "+max);
			else System.out.println(-1);
		}

	}

}
