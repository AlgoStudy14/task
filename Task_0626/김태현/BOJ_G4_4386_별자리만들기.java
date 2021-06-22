package s0626;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 별자리를 일직선으로 이을 수 있을 때, 별자리를 만드는 최소 비용을 구하라.
 * 문제 유형 : 최소 스패닝 트리, 크루스칼 알고리즘.
 * 주의 사항 : 오차는 10^-2까지 허용한다.
 * <풀이 요약 : 최소 스패닝 트리, 크루스칼 알고리즘>
 * 1. 각 점에서 점까지의 비용을 계산하여, 각 점과 비용 정보를 '비용'을 기준으로 오름차순 정렬한다(우선순위 큐 활용).
 * 2. 크루스칼 알고리즘을 이용하여 MST를 구한다.
 * <피드백>
 * 능숙함을 연습하기 좋은 문제. 아이디어 자체는 쉽지만, 구현을 설계하는 과정은 빠르게 수행하기 힘들 수 있다.
 */

public class BOJ_G4_4386_별자리만들기 {
	static int n;
	static double[][] stars;
	static double ans;
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		stars = new double[n][2];
		ans = 0.0d;
		for (int i = 0; i < n; i++) {
			stars[i][0] = sc.nextDouble();
			stars[i][1] = sc.nextDouble();
		}
		// 각 간선 비용을 오름차순으로 저장할 우선순위큐를 만든다.
		PriorityQueue<double[]> pq = new PriorityQueue<double[]>((o1, o2) -> Double.compare(o1[0], o2[0]));
		// 본인과, 이미 구한 간선 정보를 제외한 모든 간선을 계산하고 큐에 저장한다.
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				pq.offer(new double[] {
						Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2)), i,
						j });
			}
		}

		// 큐가 빌 때 까지 크루스칼 알고리즘을 이용하여 MST를 구한다.
		// makeSet
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		while (!pq.isEmpty()) {
			double[] cur = pq.poll();
			// 비용
			double val = cur[0];
			// 두 정점
			int a = (int) cur[1];
			int b = (int) cur[2];
			if (find(a) != find(b)) {
				union(a, b);
				ans += val;
			}
		}
		System.out.printf("%.2f", ans);
		sc.close();
	}

	private static void union(int a, int b) {
		int o1 = find(a);
		int o2 = find(b);
		if (o1 > o2)
			parents[o1] = o2;
		else
			parents[o2] = o1;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
}
