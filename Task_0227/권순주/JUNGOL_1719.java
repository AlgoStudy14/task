import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 별찍기 문제.. 
// 설명은 필요 없을 듯합니다!

public class JUNGOL_1719 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		if (n < 1 || n > 100 || n % 2 == 0) {
			System.out.println("INPUT ERROR!");
		} else {
			switch (m) {
			case 1:
				first(n);
				break;
			case 2:
				second(n);
				break;
			case 3:
				third(n);
				break;
			case 4:
				fourth(n);
				break;
			default:
				System.out.println("INPUT ERROR!");
				break;
			}
		}
	}

	private static void first(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < -Math.abs(i - n / 2) + n / 2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private static void second(int n) {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < Math.abs(i - n / 2); k++) {
				System.out.print(" ");
			}
			for (int j = 0; j < -Math.abs(i - n / 2) + n / 2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private static void third(int n) {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < -Math.abs(i - n / 2) + n / 2; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * Math.abs(i - n / 2) + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private static void fourth(int n) {
		for (int i = 0; i < n; i++) {
			if (i <= n / 2) {
				for (int k = 0; k < i; k++) {
					System.out.print(" ");
				}
			} else {
				for (int k = 0; k < n / 2; k++) {
					System.out.print(" ");
				}
			}
			for (int j = 0; j < Math.abs(i - n / 2) + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
