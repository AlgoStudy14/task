import java.util.Scanner;

/*
 * <문제 요약>
 * 연속으로 나오는 숫자는 지워가며 최종적으로 남는 숫자 탐색
 * 
 * <풀이법 요약>
 * 그냥 "00" ~ "99"를 주어진 숫자 문자열에서 없애는 과정을 더이상 바뀌지 않을때까지 반복수행
 * 더 이쁘게 풀 방법이 있을것같긴함.
 */

public class Solution_d3_1234_비밀번호 {

	static int T = 10;
	static int N;
	static String str;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= T; t++) {
			String[] strArr = sc.nextLine().split(" ");
			N = Integer.parseInt(strArr[0]);
			str = strArr[1];

			while (true) {
				str = str.replaceAll("00", "");
				str = str.replaceAll("11", "");
				str = str.replaceAll("22", "");
				str = str.replaceAll("33", "");
				str = str.replaceAll("44", "");
				str = str.replaceAll("55", "");
				str = str.replaceAll("66", "");
				str = str.replaceAll("77", "");
				str = str.replaceAll("88", "");
				str = str.replaceAll("99", "");
				if (str.length() == N)
					break;
				else {
					N = str.length();
				}
			}

			System.out.println("#" + t + " " + str);
		}
	}

}
