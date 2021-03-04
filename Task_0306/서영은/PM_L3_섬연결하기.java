package algo210306;
/**
 * <문제 요약>
 * 구해야 하는 것: n개의 섬 사이에 다리를 건설하는 비용이 주어질 때, 최소의 비용으로 모든 섬 서로 통행 가능하도록 만드는 최소비용 
 * 제약 사항: 섬의 경로를 구하는 것을 잊지 말자!!! 
 * 문제 유형: 그리디 
 * 요구 개념: 그리 
 * 
 * <풀이법 요약>
 * 틀림) 처음에 섬이 이어져있어야 하는 것을 고려하지 않고, 단순하게 섬의 인덱스 체크로만 구했다. 모든 섬의 인덱스가 체크 가능해도, 경로가 아니면 답이 아니다!
 * 1. 맨 처음에 방문하는 모든 섬을 visited 배열로 만들고, costsVisit도 만든다.
 * 2. 섬을 비용으로 정렬 
 * 3. 맨 처음 섬은 무조건 통행 표시 
 * 4. 그 이후의 섬은 현재 섬이 있으면 추가하고, 모든 섬을 방문했으면 return 한다.
 */
import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_섬연결하기 {

	public static void main(String[] args) {
//		int[][] seom = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
		
	}
	
    public static int solution(int n, int[][] costs) {
    	
    	//방문표시 
    	boolean[] visited = new boolean[n];
    	//costs를 사용했는지 
    	boolean[] costsVisit = new boolean[costs.length];
    	
    	Arrays.sort(costs, new Comparator<int[]>() {
    		// 비용 기준으로 오름차순 정렬 
			@Override
			public int compare(int[] o1, int[] o2) {
				int temp1 = o1[2];
				int temp2 = o2[2];
				
				return Integer.compare(temp1, temp2);
			}
    	});
    	
    	// 맨 처음 섬 표시
    	int seom1 = costs[0][0];
    	int seom2 = costs[0][1];
     	int answer= costs[0][2];
     	costsVisit[0]=true;
     	visited[seom1]=true;
     	visited[seom2]=true;
    	
     	while(!checkSeom(visited)) {
     		for (int i = 1; i < costs.length; i++) {
        		seom1=costs[i][0];
        		seom2=costs[i][1];
    			// 둘 다 방문한 섬이면 비용 추가하지 않는다.
        		
        		if(!costsVisit[i] && ((visited[seom1] && !visited[seom2]) || (!visited[seom1] && visited[seom2]))) {
    	    		costsVisit[i] = true;
    				visited[seom1] = true;
    				visited[seom2] = true;
    				answer += costs[i][2];
    				break;
        		}
    		}
     	}
     	
     	// 맨 처음 틀린 코드 
//    	for (int i = 1; i < costs.length; i++) {
//			if(checkSeom(visited)) break;
//    		seom1=costs[i][0];
//    		seom2=costs[i][1];
//			// 둘 다 방문한 섬이면 비용 추가하지 않는다.
//    		
//    		if(!costsVisit[i] && ((visited[seom1] && !visited[seom2]) || (!visited[seom1] && visited[seom2]))) {
//	    		costsVisit[i] = true;
//				visited[seom1] = true;
//				visited[seom2] = true;
//				answer += costs[i][2];
//    		}
//		}
    	
        return answer;
    }
//
	private static boolean checkSeom(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i]) return false;
		}
		return true;
	}
}
