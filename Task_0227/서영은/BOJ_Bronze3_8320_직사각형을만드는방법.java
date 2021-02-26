import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 몇가지의 직사각형을 만들 수 있는지
 * 제약 사항: 이동, 회전시켜서 만들 수 있으면 같은 직사각형이다. 직사각형은 꽉 차있어야 한다. 
 * 문제 유형: 구현
 * 요구 개념: 인수분해(수학)
 * 
 * <풀이법 요약>
 * 1. N을 입력 받아서 1부터 N까지 반복문 돌린다.
 * 2. 1~N까지 수를 차례대로 1~sqrt(N)까지 나눠서 나눠지는 수가 있다면 카운팅한다.
 * 3. 카운팅한 수를 출력한다.
 * 
 */
public class BOJ_Bronze3_8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		int cnt=0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= Math.sqrt(i); j++) {
				if(i%j == 0) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
