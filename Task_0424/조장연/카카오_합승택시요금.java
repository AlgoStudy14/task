import java.util.*;

/*
 * <문제 요약>
 * 지점의 수, 시작지점, a,b가 사는곳이 주어졌을 때 적절한 합승을 하여 둘의 택시요금 합이 최소가 되는 방법 구하기
 * 
 * <풀이법 요약>
 * 플로이드 방법을 사용
 * 시작점이 주어졌으므로 시작점-특정노드 + 특정노드에서 A가는 최소비용 + 특정노드에서 B가는 최소비용의 최소값을 구함.
 */

public class 카카오_합승택시요금 {
	class Solution {
	    public int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = Integer.MAX_VALUE;
	        int[][] map = new int[n+1][n+1];
	        
	        for(int i = 1; i < n+1; i++)
	        {
	            for(int j = 1; j < n+1; j++)
	            {
	                if(i != j)
	                    map[i][j] = 100000000;
	            }
	        }
	        
	        for(int i = 0; i < fares.length; i++)
	        {
	            map[fares[i][0]][fares[i][1]] = fares[i][2];
	            map[fares[i][1]][fares[i][0]] = fares[i][2];
	        }
	        
	        for(int k = 1; k < n+1; k++)
	        {
	            for(int i = 1; i < n+1; i++)
	            {
	                for(int j = 1; j < n+1; j++)
	                {
	                    if(map[i][j] > map[i][k] + map[k][j])
	                        map[i][j] = map[i][k] + map[k][j];
	                }
	            }
	        }
	        
	        for(int i = 1; i < n+1; i++)
	        {
	            int checkAnswer = 0;
	            checkAnswer = map[s][i] + map[i][a] + map[i][b];
	            answer = Math.min(answer, checkAnswer);
	        }
	        
	        return answer;
	    }
	}
}
