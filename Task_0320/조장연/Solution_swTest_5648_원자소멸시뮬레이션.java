import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/*
 * <문제 요약>
 * 원자의 위치와 이동방향이 주어졌을 때 원자끼리 충돌하여 발생하는 에너지 총합 구하기
 * 
 * <풀이법 요약>
 * 방법 1 : 초단위로 시뮬레이션 코드를 짯음 => 시간초과!(다른사람들보면 이걸로 풀었다는데 나는 시간초과 에러....)
 * 
 * 방법 2 : 모든 원자끼리의 상관관계를 따져봄(충돌 할 수 있는지?)
 *		    만약 충돌이 가능하면 원자 번호와 충돌예상시간을 저장한 int[]을 ArrayList에 저장
 *		  ArrayList를 충돌예상시간을 기준으로 정렬
 *		    시뮬레이션 시작(제일 앞에 있는 상황이 제일 일찍 충돌하는 것, 
 *					만약 제일 앞에 있는 상황과 충돌시간이 다르다면 추후에 일어나는 충돌이기 때문에 제일 앞 배열에 속해있는 원자가 있는 상황들은 충돌이 일어나지 않음으로 지워줌
 *					동일한 충돌시간이라면 동시에 충돌하는 것이기 때문에 answer에 더해줌)
 *
 *
 *4000번을 시뮬을 돌리기 때문에 시간이 빡빡한 문제.
 *풀고나니까 다른사람들보다 시간이 훨씬 빨리 나와서 기분은 좋았음.
 *근데 조건주다가 눈알 빠질뻔했음.
 */

public class Solution_swTest_5648_원자소멸시뮬레이션 {
	static int T, N, r, c, dir, K;
	static int[][] atomArr;
	static int answer;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			atomArr = new int[N][4];
			answer = 0;
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				atomArr[i][0] = sc.nextInt() * 2;
				atomArr[i][1] = sc.nextInt() * 2;
				atomArr[i][2] = sc.nextInt();
				atomArr[i][3] = sc.nextInt();
			}

			ArrayList<int[]> boom = new ArrayList<>();

			for (int j = 0; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if ((atomArr[j][1] == atomArr[k][1] && atomArr[j][0] < atomArr[k][0] && atomArr[j][2] == 3
							&& atomArr[k][2] == 2) || // j-> <-k
							(atomArr[j][1] == atomArr[k][1] && atomArr[j][0] > atomArr[k][0] && atomArr[j][2] == 2
									&& atomArr[k][2] == 3)) // k-> <-j
					{
						int[] arr = { j, k, Math.abs(atomArr[k][0] - atomArr[j][0]) / 2 };
						boom.add(arr);
					} else if ((atomArr[j][0] == atomArr[k][0] && atomArr[j][1] < atomArr[k][1] && atomArr[j][2] == 0
							&& atomArr[k][2] == 1) || // 서로 상하관계
							(atomArr[j][0] == atomArr[k][0] && atomArr[j][1] > atomArr[k][1] && atomArr[j][2] == 1
									&& atomArr[k][2] == 0)) {
						int[] arr = { j, k, Math.abs(atomArr[k][1] - atomArr[j][1]) / 2 };
						boom.add(arr);
					} else if ((atomArr[j][0] < atomArr[k][0] && atomArr[j][1] < atomArr[k][1]
							&& Math.abs(atomArr[j][0] - atomArr[k][0]) == Math.abs(atomArr[j][1] - atomArr[k][1])
							&& ((atomArr[j][2] == 3 && atomArr[k][2] == 1)
									|| (atomArr[j][2] == 0 && atomArr[k][2] == 2)))
							|| (atomArr[j][0] > atomArr[k][0] && atomArr[j][1] > atomArr[k][1]
									&& Math.abs(atomArr[j][0] - atomArr[k][0]) == Math
											.abs(atomArr[j][1] - atomArr[k][1])
									&& ((atomArr[j][2] == 1 && atomArr[k][2] == 3)
											|| (atomArr[j][2] == 2 && atomArr[k][2] == 0)))) {
						int[] arr = { j, k, Math.abs(atomArr[k][1] - atomArr[j][1]) };
						boom.add(arr);
					} else if ((atomArr[j][0] < atomArr[k][0] && atomArr[j][1] > atomArr[k][1]
							&& Math.abs(atomArr[j][0] - atomArr[k][0]) == Math.abs(atomArr[j][1] - atomArr[k][1])
							&& ((atomArr[j][2] == 3 && atomArr[k][2] == 0)
									|| (atomArr[j][2] == 1 && atomArr[k][2] == 2))
							|| (atomArr[j][0] > atomArr[k][0] && atomArr[j][1] < atomArr[k][1]
									&& Math.abs(atomArr[j][0] - atomArr[k][0]) == Math
											.abs(atomArr[j][1] - atomArr[k][1])
									&& ((atomArr[j][2] == 0 && atomArr[k][2] == 3)
											|| (atomArr[j][2] == 2 && atomArr[k][2] == 1))))) {
						int[] arr = { j, k, Math.abs(atomArr[k][1] - atomArr[j][1]) };
						boom.add(arr);
					}
				}
			}

			Collections.sort(boom, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2] - o2[2];
				}
			});

			while (boom.size() != 0) {
				int[] arr = boom.get(0);
				while (true) {
					if (boom.get(0)[2] == arr[2]) {
						if (visited[boom.get(0)[0]] == false) {
							answer += atomArr[boom.get(0)[0]][3];
							visited[boom.get(0)[0]] = true;
						}
						if (visited[boom.get(0)[1]] == false) {
							answer += atomArr[boom.get(0)[1]][3];
							visited[boom.get(0)[1]] = true;
						}
						boom.remove(0);
					} else {
						for (int i = 0; i < boom.size(); i++) {
							if (visited[boom.get(i)[0]] == true || visited[boom.get(i)[1]] == true) {
								boom.remove(i);
								i--;
							}
						}
						break;
					}

					if (boom.size() == 0)
						break;
				}
			}

			System.out.println("#" + t + " " + answer);
		}
	}
}
