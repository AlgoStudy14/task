
public class KAKAO21_합승택시요금 {
	/*
	 * <합승 택시 요금>
	 * 방향성 없는 연결 그래프에 양의 가중치가 주어짐.
	 * solution에 n, s, a, b의 정수들과 fares라는 배열이 주어지며
	 * 		=>	n은 지점의 개수, s는 a b의 출발지점, a는 a의 집 노드번호, b는 b의 집 노드번호.
	 * 구하는 것 : S에서 A,B에 각각 가는데 중간 까지 공동 비용을 내며 갔을 때, 최소값
	 * 배열 fares의 3개 항 {a, b, c} => a와 b지점 사이의 가중치가 c
	 * 
	 * <풀이 방법>
	 * 결국 각각의 지점의 최소값들을 확인하는 거니, 모든 지점을 확인해야 하므로 플로이드 와샬.
	 * 플로이드 와샬로 A,B로 가는 모든 경우에서 최소값을 구하면 될려나?
	 * 		=> 첫번째 케이스가 Integer.min_value가 나온거 같은데	 => 초기화값을 바꾸어야 할것 같은데?
	 */
	public static void main(String[] args) {
		//int ans = solution(6, 4, 6, 2, new int[][] { {4,1,10}, {3,5,24}, {5,6,2}, {3,1,41}, {5,1,24}, {4,6,50}, {2,4,66}, {2,3,22}, {1,6,25} });
		//int ans = solution(7, 3, 4, 1, new int[][] { {5,7,9}, {4,6,4}, {3,6,1}, {3,2,3}, {2,1,6} });
		int ans = solution(6, 4, 5, 6, new int[][] { {2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}});
		System.out.println(ans);
	}
	
	static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] D = new int[n + 1][n + 1];
		D = makeset(n + 1, D);
		int ans = Integer.MAX_VALUE;
		//fares 연결
		for(int i = 0; i < fares.length; i++) {
			D[fares[i][0]][fares[i][1]] = fares[i][2];
			D[fares[i][1]][fares[i][0]] = fares[i][2];
		}
		//플로이드 와샬
		for(int k = 1; k < n + 1; k++) {			//경유지
			for(int i = 1; i < n + 1; i++) {		//출발지
				for(int j = 1; j < n + 1; j++) {	//도착지
					if(D[i][j] > D[i][k] + D[k][j])	//경유지를 거쳐서 가는게 더 짧으면
						D[i][j] = D[i][k] + D[k][j];
				}
			}
		}
		//택시 합승을 해서 더 짧은 것이 있으면 최소값 갱신. 흠....
		for(int i = 1; i < n + 1; i++)		// i 번째에 있는 노드 까지 합승한 경우.
			ans = Math.min(ans, D[s][i] + D[i][a] + D[i][b]);
		return ans;
	}
	
	static int[][] makeset(int n, int[][] arr){
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j)
					arr[i][j] = 0;
				else			//이 부분을 MAX_VALUE가 아니라 위에서 3개를 더 할 것이니 3으로 나눠줌.
					arr[i][j] = Integer.MAX_VALUE/3;
			}
		}
		return arr;
	}
}
