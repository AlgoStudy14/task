/*
 * 프로그래머스 코딩테스트 연습 고득점 kit - 탐욕법(Greedy) - 섬 연결하기
 * n개의 섬 건설하는데 최소 비용 구하는 문제, 다리 여러개 지나도 갈수만 있으면 된거임ㅇㅇ
 * costs[][] : {{섬1,섬2,건설비용},{} ...}
 */

import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2]-o2[2];
			}
		});
		//다리 지은덴지 체크
		boolean[] visited = new boolean[costs.length];
		//섬 방문 가능한지 체크
		boolean[] isIsland = new boolean[n];
		//costs정렬 후, costs[0]은 건설비용 최솟값이므로 일단 쓰고 시작
		isIsland[costs[0][0]] = true;
		isIsland[costs[0][1]] = true;
		int answer = costs[0][2];
		//그럼 두개 연결 됐음
		int connected = 2;
		
		while(connected<n) {
			for (int i = 1; i < costs.length; i++) {
				//해당 다리가 아직 건설되지 않았고 && 섬의 한쪽만 방문 가능할때 (isIsland[costs[i][0]]또는 isIsland[costs[i][1]] 다리 지음
				//건설 비용 기준 오름차순으로 정렬해 놓았기 때문에 건설 후엔 break로 for문을 빠져나가서 처음부터 다시 탐색해야됨
				
				if(!visited[i]&&(isIsland[costs[i][0]]^isIsland[costs[i][1]])) {
					visited[i] = true;
					isIsland[costs[i][0]] = true;
					isIsland[costs[i][1]] = true;
					connected++;
					answer += costs[i][2];
					break;
				}
			}
		}
		
        return answer;
    }
}
