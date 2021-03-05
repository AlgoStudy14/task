package algo0305;
//네트워크 문제
//되게 간단하다. 방문했는지 안했는지만 검토 해줌
//방문 안했을 경우 dfs로 방문 했다고 하며 같은 경우는 continue, 연결되어 있고 연결된 애가 false일경우 그 애로 dfs 한번 더 한다.
//for문 계속 반복
public class Solution_progra_네트워크 {
	static boolean V[];
	public static void main(String[] args) {
		int n=3;
		int answer=0;
		int[][] computers= {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		V=new boolean[n];
		for(int i=0;i<n;i++) {
			if(!V[i]) {
				answer++;
				dfs(i,computers);
			}
		}
		System.out.println(answer);
	}
	private static void dfs(int index, int[][] computers) {
		V[index]=true;
		for(int i=0;i<computers.length;i++) {
			if(i==index)
				continue;
			if(computers[index][i]==1 && V[i]==false)
				dfs(i,computers);
		}
		
	}

}
