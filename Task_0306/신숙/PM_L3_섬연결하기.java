package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_섬연결하기 {
	/*	<문제>
	 *  n 개의 섬 사이에 다리를 건설하는 비용 배열로 주어짐.
	 *  이 때 모든 섬을 통행 가능 할 때 필요한 최소비용은?
	 *  
	 *  <제한사항>
	 *  1. 1 <= n <= 100
	 *  2. costs의 길이 =  ( (n-1)*n ) / 2				<= 힌트일까?
	 *  3. 주어진 배열은 내부 인덱스 3개인 배열. cost[0], cost[1]은 연결되는 섬 번호, cost[2]는 비용
	 *  4. 같은 연결은 주어지지 않는다. => 0 <-> 1 연결과 1 <-> 0 연결은 또 주어지지 않음.
	 *  5. 모든 섬이 전부 연결되지는 않음.(0번이 5번까지 섬 전부오 ㅏ연결되고 있지는 않음)
	 *  6. 연결불가능한 섬은 없음.
	 *  
	 *  <풀이>
	 *  크루스칼 알고리즘    https://swexpertacademy.com/main/visualcode/main.do#/home/editor// 
	 *  	
	 *  1. cost의 최소값을 순서대로 가져오기 위해 오름차순정렬.
	 *  2. 정렬된 순서대로 해당 연결이 다른 집합에 속해있다면, 트리에 추가하기.
	 *  
	 *  	=> comparator로 정렬 후, 가장 비용이 낮은 것을 연결해보고, 모두 연결이 되었다면 비용반환.
	 */
	//어디에 연결되어있는지 확인해줄 배열.
	static int[] isLink;		
	public static int solution(int n, int[][] costs) {
		int answer = 0;
		int first = 0;
		int second = 0;
		isLink = new int[n];
		//아무것도 연결이 되지 않은 초기상태로 초기화.
		for(int i = 0; i < n; i++)
			isLink[i] = i;
		//오름차순 정렬
		Arrays.sort(costs, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
        //union-find? 
        for(int i = 0; i < costs.length; i++) {
        	first = findNode(costs[i][0]);
        	second = findNode(costs[i][1]);
        	if(first != second) {
        		isLink[second] = first;
        		answer += costs[i][2];
        	}
        }
        
        return answer;
    }
	
	static int findNode(int island) {
		if(isLink[island] == island)
			return island;
		else
			return isLink[island] = findNode(isLink[island]);
	}
	
	public static void main(String[] args) {
		int ans = solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
		System.out.println(ans);
	}
}
