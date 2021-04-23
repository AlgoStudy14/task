package 플로이드워샬;
/*
 * 플로이드 워샬 문제
 * 하나의 정점에서 출발했을 때 모든 정점으로부터 최단 경로를 구하는것이 다익스트라라면
 * 플로이드 와샬은 모든 정점에서 모든 정점으로 최단 경로를 구하고 싶을 때 사용한다.
 * 3중 FOR문을 사용해서 변수 k는 환승하는곳을 뜻하고 i부터 j까지 환승할때와 안할때를 비굫 dp 배열에 집어넣는다.
 * 그냥 가는 경우와 중간을 들려 가는 경우로 나눠서 보면 된다.
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_합승택시요금_카카오 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n=6,s=4,a=6,b=2;
		int fares[][]= {{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}};
		System.out.println(solution(n,s,a,b,fares));
	}
	
    public static int solution(int n, int s, int a, int b, int[][] fares) {
    	int dp[][]=new int[n+1][n+1];
    	for(int i=0;i<n+1;i++) {
    		Arrays.fill(dp[i], 20000001);
    		dp[i][i]=0;
    	}
    	for(int i=0;i<fares.length;i++) {
    		int fare[] = fares[i];
    		int start = fare[0];
    		int end = fare[1];
    		int weight =fare[2];
    		dp[start][end]=weight;
    		dp[end][start]=weight;
    	}
    	for (int k = 1; k < n+1; k++) { //환승하는 경우와 안하는 경우 비교해서 더 작은 값 넣기
			for(int i=1;i<n+1;i++){
				for(int j=1;j<n+1;j++) {
					dp[i][j]=Math.min(dp[i][k]+dp[k][j], dp[i][j]);
				}
			}
    	}
    	int answer=dp[s][a]+dp[s][b]; // 일단 스타트에서 그냥 쭉가는거
    	for(int i=1;i<n+1;i++) { // 환승하는걸 다 비교해서 작은값 찾기
    		answer=Math.min(answer, dp[s][i]+dp[i][a]+dp[i][b]);
    	}
    	return answer;
    }
}
