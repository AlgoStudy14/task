import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_6603_로또 {

	static int N;
	static int[] numList, lotto;
	static StringBuilder sb = new StringBuilder(); // StringBuilder를 사용해야 5배 정도 단축된다..!!

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			numList = new int[N];
			lotto = new int[6];
			for (int i = 0; i < N; i++) {
				numList[i] = Integer.parseInt(st.nextToken());
			}
			makeLotto(0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	// indexN : numlist index
	// indexL : lotto index
	private static void makeLotto(int indexN, int indexL) {
		// lotto 번호 6개가 입력된다면 출력
		if (indexL == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(lotto[i] + " ");
			}
			sb.append("\n");
			return;
		}

		// 6개의 원소 순착적으로 선택
		for (int i = indexN; i < N; i++) {
			lotto[indexL] = numList[i];
			makeLotto(i + 1, indexL + 1);
		}
	}

}
