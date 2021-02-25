package algo0225;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/***
 * <문제 요약> 구해야 하는 것 : 가장 긴 스트레이트의 길이. 문제 유형 : 완전 탐색 요구 개념 : 인덱스 조작. <풀이법 요약> 1.
 * 조커는 빼고, 나머지 숫자를 넣은뒤 정렬한다. 2. 각 숫자에 대하여 조커를 넣으며 최대 얼마나 길어지는지 탐색한다(얼마나 연결되는지).
 * -> 만일 다음 수가 같은 숫자라면 그냥 스킵한다. -> 만일 다음 수가 1차이가 난다면, 연결 길이를 + 1 한다. -> 만일 다음 수가
 * 2이상 차이가 나는데 조커가 존재한다면, 조커 카드를 한 장 줄이고 연결 길이를 + 1 한다. 값도 + 1 한다. 3. 최종 최대 길이
 * 값이 정답. -> 만일, 모든 카드가 조커로만 이루어져 있다면 조커의 개수가 정답.
 */

public class JUNGOL_1205 {
	static int N;
	static ArrayList<Integer> nums;
	static int joker;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 카드를 입력 받고 정렬한다(0은 따로 세어두기만 한다).
		N = sc.nextInt();
		nums = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (temp == 0) {
				joker++;
				continue;
			}
			nums.add(temp);
		}
		Collections.sort(nums);

		// 만일, 조커로만 이루어져 있다면 조커카드의 개수가 정답.
		if (nums.isEmpty()) {
			max = joker;
		}
		// 그렇지 않다면 각 숫자에 대해서 조커를 대입하며 최대로 연결해본다.
		else {
			for (int i = 0; i < nums.size(); i++) {
				int cnt = 1;
				int idx = i;
				int j_cnt = joker;
				int temp = nums.get(idx);
				// 만일, 탐색 범위를 넘어간다면 아웃.
				while (idx < nums.size() - 1) {
					// 만일, 다음 숫자가 같다면 인덱스만 증가.
					if (temp == nums.get(idx + 1)) {
						idx++;
						continue;
					}
					// 만일, 다음 숫자가 1차이가 난다면 다음 숫자와 인덱스를 모두 증가.
					if (temp + 1 == nums.get(idx + 1)) {
						temp = nums.get(idx + 1);
						cnt++;
						idx++;
						continue;
					}
					// 만일, 조커 카드가 남아 있다면 현재 값에 + 1을 하면서 반복 진행.
					if (j_cnt != 0) {
						j_cnt--;
						temp++;
						cnt++;
						continue;
					}
					// 여기 까지 왔다면, 그 숫자에서는 더이상 연결이 불가능하다.
					break;
				}
				// 조커 카드가 남아있다면 그만큼 더 연결할 수 있다는 말이므로 길이 + 남은 조커 카드의 개수.
				if (j_cnt != 0) {
					cnt += j_cnt;
				}
				// 최댓값을 갱신한다.
				max = Math.max(max, cnt);
			}
		}
		System.out.println(max);

		sc.close();
	}
}
