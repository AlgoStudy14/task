import java.io.BufferedReader;
import java.io.InputStreamReader;

// 별찍기 문제.. 
// 설명은 필요 없을 듯합니다!

public class JUNGOL_1239 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		if (n < 1 || n > 100 || n % 2 == 0) {
			System.out.println("INPUT ERROR!");
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < -Math.abs(i - n / 2) + n / 2; j++) {
					System.out.print(" ");
				}
				for (int k = 0; k < -2 * Math.abs(i - n / 2) + n; k++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
	}
}
