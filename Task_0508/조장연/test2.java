import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test2 {
	static String sentence;
	static String keyword;
	static int[] skips;
	static String result;

	public static void main(String[] args) throws Exception {
		// 문제조건 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sentence = br.readLine();
		keyword = br.readLine();
		int N = Integer.parseInt(br.readLine());
		skips = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			skips[i] = Integer.parseInt(st.nextToken());
		}

		
		int sPointer = 0;
		int kPointer = 0;
		int sLength = sentence.length();
		int kLength = keyword.length();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < skips.length; i++) {
			for (int j = 0; j < skips[i]; j++) {
				if (sPointer == sLength) { //sentence 다 적은경우
					result = sb.toString();
					System.out.println(result);
					return;
				}

				if (sentence.charAt(sPointer) == keyword.charAt(kPointer)) // 같은 알파벳인 경우
				{
					sb.append(sentence.charAt(sPointer));
					sPointer++;
					break;
				}

				sb.append(sentence.charAt(sPointer));
				sPointer++;
			}

			sb.append(keyword.charAt(kPointer));
			kPointer = (kPointer + 1) % kLength;
		}

		for (int i = sPointer; i < sLength; i++) { // 남은 sentence 써주기
			sb.append(sentence.charAt(sPointer));
			sPointer++;
		}

		result = sb.toString();
		System.out.println(result);
	}
}
