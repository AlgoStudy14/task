import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 크로아티아 알파벳의 개수
 * 문제 핵심 요약 : 크로아티아 알파벳을 미리 배열에 저장해놓고 한자리 문자로 replace 해줌
 * <풀이법 요약> 
 * 1. 크로아티아 알파벳 배열에 미리 저장
 * 2. 반복문을 돌면서 크로아티아 알파벳에 해당하는 문자를 길이가 1인 문자로 바꿔줌
 * 3. 알파벳의 길이를 return
 */

public class Main_Silver5_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String alphabet = sc.nextLine();

		String[] croatia = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" }; // 크로아티아 알파벳 미리 저장

		for (int i = 0; i < 8; i++) {
			alphabet = alphabet.replace(croatia[i], "0"); // 크로아티아 알파벳을 0으로 변경
		}

		System.out.println(alphabet.length()); // 길이 return
	}

}
