package algo210424;
/**
 * <문제 요약>
 * 구해야 하는 것: 두 사람이 모두 귀가하는데 소요되는 예상 최저 택시요금
 * 제약 사항: 3<=n<=200
 * 문제 유형: 그래프
 * 요구 개념: 플루이드 와샬 + 구현
 * 
 * <풀이법 요약>
 * 1. 먼저 플루이드 와샬로 최소 비용 다 설정
 * 2. 시작 정점에서 같이 가는 정점까지 다 따져봐서 최소 비용으로 갱신하기
 *
 * 주의사항
 * - 플루이드에서 최솟값일 때 갱신해주는 로직
 * - INF 값을 그냥 최대값으로 둬버리면 안된다!
 */
import java.util.Arrays;

public class PM_2021kakaoblind_합승택시요금 {

	public static void main(String[] args) {
		PM_2021kakaoblind_합승택시요금 pm = new PM_2021kakaoblind_합승택시요금();
		
		System.out.println(pm.solution(6, 4, 6, 2, new int[][]{{4,1,10},{3,5,24},{5,6,2},{3,1,41},
			{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}}));

	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int INF = 20000000; // 중요!! 최댓값을 그냥 최대로 둬버리면 안됨.
		int[][] dist = new int[n+1][n+1];
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int w = fares[i][2];
			
			dist[from][to] = w;
			dist[to][from] = w;
		}
		
		//플루이드 와샬
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if(k==i) continue;
				for (int j = 1; j <= n; j++) {
					if(j==k || j==i) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // 비교 필수!!
				}
			}
		}
		
		//시작 정점부터 최소구간 찾기
		for (int by = 1; by <= n; by++) {
			if(dist[s][by] != INF && dist[by][a] != INF && dist[by][b] != INF) {
				int sum = 0;
				sum += dist[s][by] + dist[by][a] + dist[by][b];
				
				min = Math.min(min, sum);
			}
		}

		return min;
	}
}
