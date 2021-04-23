import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 각 선수의 이닝당 타격결과가 주어지고 1번선수를 4번타순에 배치할 때 적절하게 나머지 선수 순서를 조합하여 최대점수 낼 방법 찾기
 * 
 * <풀이법 요약>
 * 1번선수를 제외한 나머지 선수를 줄세우는 방법을 다 구함(np사용)
 * 주어진 이닝을 시뮬레이션 하면서 점수를 구한 뒤 answer와 비교.
 */

public class Main_BOJ_17281_야구 {
	static int[][] memory;
	static int[] order;
	static int answer = Integer.MIN_VALUE;
	static int[] perNum = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static int inning;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		inning = sc.nextInt();
		memory = new int[inning][9];
		for (int i = 0; i < inning; i++) {
			for (int j = 0; j < 9; j++) {
				memory[i][j] = sc.nextInt();
			}
		}

		do {
			order = new int[9];
			int pointer = 0;
			int rank = 0;
			for (int i = 0; i < 3; i++) {
				order[i] = perNum[i];
			}
			order[3] = 0;
			for (int i = 4; i < 9; i++) {
				order[i] = perNum[i - 1];
			}

			for (int i = 0; i < inning; i++) {
				int out = 0;
				Queue<Integer> queue = new LinkedList<>();
				while (out < 3) {
					if (memory[i][order[pointer]] == 0) {
						out++;
					} else if (memory[i][order[pointer]] == 1) {
						int members = queue.size();
						for (int j = 0; j < members; j++) {
							int num = queue.poll();
							num += 1;
							if (num >= 4) {
								rank++;
							} else {
								queue.add(num);
							}
						}
						queue.add(1);
					} else if (memory[i][order[pointer]] == 2) {
						int members = queue.size();
						for (int j = 0; j < members; j++) {
							int num = queue.poll();
							num += 2;
							if (num >= 4) {
								rank++;
							} else {
								queue.add(num);
							}

						}
						queue.add(2);
					} else if (memory[i][order[pointer]] == 3) {
						int members = queue.size();
						for (int j = 0; j < members; j++) {
							int num = queue.poll();
							num += 3;
							if (num >= 4) {
								rank++;
							} else {
								queue.add(num);
							}
						}
						queue.add(3);
					} else if (memory[i][order[pointer]] == 4) {
						int members = queue.size();
						for (int j = 0; j < members; j++) {
							queue.poll();
							rank++;
						}
						rank++;
					}
					pointer = (pointer + 1) % 9;
				}
			}
			answer = Math.max(answer, rank);
		} while (np(7));

		System.out.println(answer);
	}

	private static boolean np(int cnt) {
		int i = cnt;
		while (i > 0 && perNum[i] < perNum[i - 1])
			i--;
		if (i == 0)
			return false;

		int j = cnt;
		while (perNum[i - 1] > perNum[j])
			j--;

		int temp = perNum[i - 1];
		perNum[i - 1] = perNum[j];
		perNum[j] = temp;

		int k = cnt;
		while (i < k) {
			temp = perNum[i];
			perNum[i] = perNum[k];
			perNum[k] = temp;
			k--;
			i++;
		}

		return true;
	}

}
