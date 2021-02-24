import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 정사각형으로 만들 수 있는 직사각형의 개수
 * 문제 핵심 요약 : 넓이를 생각하며 접근하면 된다
 * <풀이법 요약> 
 * 1. i : 1~n까지 반복
 * 2. j : i~n까지 반복
 * 3. 만약 i*j(넓이)가 n보다 작다면 카운팅
 */

public class Main_Bronze3_8320 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int answer = 0;
		for (int i = 1; i <= n; i++) { // 1~n
			for (int j = i; i * j <= n; j++) { 
				// ex)
				// 1*1, 1*2, ... , 1*n 
				// 2*2, 2*3, ... , 2*n
				// 3*3, 3*4, ... , 3*n
				// ... 중 n보다 작거나 같은 수
				answer++; // 카운팅
			}
		}
		System.out.println(answer);
	}

}
