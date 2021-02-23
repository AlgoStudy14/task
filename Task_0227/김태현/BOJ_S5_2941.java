package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 주어진 문자열에서 크로아티아 알파벳의 개수.
 * 문제 유형 : 구현
 * 요구 개념 : contains.
 * <풀이법 요약>
 * 1. 해당 문자열을 포함한 List 배열을 만든다.
 * 2. 3글자, 2글자, 1글자 순으로 알파벳을 분석한다.
 * -> 인덱스를 벗어나면 더 적은 글자수로 넘어간다.
 * -> 해당 문자열이 List에 포함된다면(contains) (idx += 글자 수)를 적용하고 반복문을 탈출한다, 크로아티아 문자수는 +1.
 * 3. 인덱스가 범위를 벗어나면 결과를 출력한다.
 */

public class BOJ_S5_2941 {
	// 문자열을 구분할 String 배열.
	static String[] kind = { "", "abcdefghijklmnopqrstuvwxyz=-", "c=,c-,d-,lj,nj,s=,z=", "dz=" };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = 0;

		String line = sc.nextLine();
		int idx = 0;
		while (idx < line.length()) {
			// 3글자, 2글자, 1글자 순으로 알파벳 분석.
			for (int i = 3; i >= 1; i--) {
				// 인덱스를 벗어나면 더 적은 글자수로 넘어간다.
				if (idx + i > line.length()) {
					continue;
				}
				// 해당 문자열이 List에 포함된다면(contains) (idx += 글자 수)를 적용하고 반복문을 탈출한다, 크로아티아 문자수는 +1.
				if (kind[i].contains(line.substring(idx, idx + i))) {
					cnt++;
					idx += i;
					break;
				}
			}
		}
		// 결과 출력.
		System.out.println(cnt);
		sc.close();
	}
}
