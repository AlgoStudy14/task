import java.util.Arrays;

/*
 * <문제 요약>
 * 갈색격자의 수와 노란색 격자의 수가 주어졌을 때 카펫의 가로,세로 구하기
 * 
 * <풀이법 요약>
 * "갈색격자 - 4 = 노란색범위의 가로 + 세로" 인 것을 활용 
 * 노란색 가로길이 1부터 for문을 돌며 가로 * 세로를 통해 노란색 범위의 가로세로를 구함
 * 가로와 세로에 각각 +2를 해줘서 전체 카펫의 가로 세로를 구함
 */

public class BruteForce_카펫 {

	public static void main(String[] args) {
		BruteForce_카펫 pm = new BruteForce_카펫();
		int brown = 10;
		int yellow = 2;
		System.out.println(Arrays.toString(pm.solution(brown, yellow)));
	}

	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int num = (brown - 4) / 2;

		for (int i = 1; i <= num / 2; i++) {
			int h = i;
			int w = num - i;

			if (h * w == yellow) {
				answer[0] = w + 2;
				answer[1] = h + 2;
				break;
			}
		}
		return answer;
	}

}
