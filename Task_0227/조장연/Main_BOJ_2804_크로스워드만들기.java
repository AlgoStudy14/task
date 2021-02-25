import java.util.Arrays;
import java.util.Scanner;

/*
 * <문제 요약>
 * 두 단어가 +모양으로 교차하도록 배열만들기
 * 조건1 : 문자 A는 가로로, 문자 B는 세로로 놓여야 한다.
 * 조건2 : A기준으로 공유하는 글자중 A에서 제일 먼저 등장하는 글자 선택
 * 조건3 : B기준으로 공유하는 글자중 B에서 가장 먼저 등장하는 글자 선택
 * 
 * <풀이법 요약>
 * 이중포문을 이용하여 조건에 만족하는 공유글자 위치 정하기
 * +모양으로 단어가 들어간 이차원 배열만들기
 */

public class Main_BOJ_2804_크로스워드만들기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] strArr = sc.nextLine().split(" ");
		String a = strArr[0];
		String b = strArr[1];

		int i = 0;
		int j = 0;
		boolean check = false;

		for (i = 0; i < a.length(); i++) {
			for (j = 0; j < b.length(); j++) {
				if (a.charAt(i) == b.charAt(j)) {
					check = true;
					break;
				}
			}
			if (check == true)
				break;
		}

		char[][] arr = new char[b.length()][a.length()];
		for (int k = 0; k < arr.length; k++) {
			Arrays.fill(arr[k], '.');
		}

		for (int k = 0; k < a.length(); k++) {
			arr[j][k] = a.charAt(k);
		}

		for (int k = 0; k < b.length(); k++) {
			arr[k][i] = b.charAt(k);
		}

		for (int k = 0; k < arr.length; k++) {
			for (int k2 = 0; k2 < arr[0].length; k2++) {
				System.out.print(arr[k][k2]);
			}
			System.out.println();
		}
	}

}
