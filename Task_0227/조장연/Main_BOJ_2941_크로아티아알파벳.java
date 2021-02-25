import java.util.Scanner;

/*
 * <문제 요약>
 * 애매한 크로아티아 알파벳을 운영체제에 맞게 변환하여 표기하였다.
 * 변환된 String을 보고 원래의 크로아티아 알파벳은 몇개인지 구하는 문제
 * 
 * <풀이법 요약>
 * 변환된 크로아티아 알파벳은 알파벳 소문자와 '-', '='로만 이루어져 있다.
 * 따라서 변환된 크로아티아 알파벳을 1로 바꿔주고 전체 String의 length()를 구해줌
 */

public class Main_BOJ_2941_크로아티아알파벳 {
	static String str;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int answer = 0;

		String[] arr = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };

		for (int i = 0; i < arr.length; i++) {
			str = str.replaceAll(arr[i], "1");
		}
		answer = str.length();
		System.out.print(answer);
	}

}
