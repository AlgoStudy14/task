import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 크로스워드 퍼즐 만들기
 * 문제 핵심 요약 : 가장 먼저 등장하는 같은 단어를 찾아주는 것이 핵심
 * <풀이법 요약> 
 * 1. A의 단어 길이만큼 반복하면서 B에서 같은 단어가 존재하는지 찾는다
 * 2. 만약 찾았다면 해당 문자의 좌표를 체크한다.
 * 3. 찾은 좌표에 대응되는 위치에 각 단어를 저장한다.
 */

public class Main_Bronze2_2804 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String A = st.nextToken();
		String B = st.nextToken();
		char[][] board = new char[B.length()][A.length()];
		int x = 0, y = 0;
		int lenA = A.length(), lenB = B.length();

		for (int i = 0; i < lenB; i++) { // board 초기화
			Arrays.fill(board[i], '.');
		}

		for (int i = 0; i < lenA; i++) { // A의 단어 길이 만큼 반복
			x = B.indexOf(A.charAt(i)); // B에서 A단어와 같은 단어의 index 찾기
			if (x != -1) { // 만약 index가 존재 한다면
				y = i; // y 저장
				break; // 반복문 나가기
			}
		}

		for (int i = 0; i < lenA; i++) {
			board[x][i] = A.charAt(i); // board에 A 저장
		}

		for (int i = 0; i < lenB; i++) {
			board[i][y] = B.charAt(i); // board에 B 저장
		}

		for (int i = 0; i < lenB; i++) { // print
			for (int j = 0; j < lenA; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

	}

}
