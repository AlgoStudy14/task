import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/*
 * <문제 요약>
 * 자동차의 시작지점 끝지점이 주어졌을 때 모든 자동차가 적어도 한번은 단속카메라를 만나게 되는 최소 카메라 수
 * 
 * <풀이법 요약>
 * 끝지점을 기준으로 배열을 sorting하여 ArrayList에 집어넣음
 * ArrayList의 제일 처음것을 빼서 끝지점과 나머지 차의 시작지점과 비교
 * 만약 시작지점이 끝지점 보다 앞에있으면 ArrayList에서 제거
 * (뽑은 차의 끝지점에 카메라를 세우면 그보다 전에 시작했던 애들은 다 찍히게 됨)
 */

public class Greedy_단속카메라 {

	public static void main(String[] args) {
		Greedy_단속카메라 pm = new Greedy_단속카메라();
		int[][] routes = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };
		System.out.println(pm.solution(routes));
	}

	public int solution(int[][] routes) {
		int answer = 0;
		ArrayList<int[]> arrList = new ArrayList<>();

		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] t1, int[] t2) {
				return t1[1] - t2[1];
			}
		});

		for (int i = 0; i < routes.length; i++) {
			arrList.add(routes[i]);
		}

		while (!arrList.isEmpty()) {
			answer++;
			int[] arr1 = arrList.get(0);

			for (int i = 0; i < arrList.size(); i++) {
				int[] arr2 = arrList.get(i);
				if (arr2[0] <= arr1[1]) {
					arrList.remove(i);
					i--;
				}
			}
		}
		return answer;
	}

}
