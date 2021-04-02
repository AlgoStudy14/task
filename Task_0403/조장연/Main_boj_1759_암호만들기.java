import java.util.Arrays;
import java.util.Scanner;

public class Main_boj_1759_암호만들기 {
	static int L, C;
	static char[] alpArr;
	static char[] answer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] lc = sc.nextLine().split(" ");
		L = Integer.parseInt(lc[0]);
		C = Integer.parseInt(lc[1]);
		answer = new char[L];
		alpArr = sc.nextLine().replaceAll(" ", "").toCharArray();
		Arrays.sort(alpArr);

		combi(0, 0);
	}

	private static void combi(int cnt, int start) {
		if (cnt == L) {
			int countA = 0;
			int countB = 0;
			for (int i = 0; i < answer.length; i++) {
				if (answer[i] == 'a' || answer[i] == 'e' || answer[i] == 'i' || answer[i] == 'o' || answer[i] == 'u')
					countA++;
				else
					countB++;

				if (countA >= 1 && countB >= 2) {
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < answer.length; j++) {
						sb.append(answer[j]);
					}
					System.out.println(sb.toString());
					break;
				}
			}
			return;
		}

		for (int i = start; i < alpArr.length; i++) {
			answer[cnt] = alpArr[i];
			combi(cnt + 1, i + 1);
		}
	}

}
