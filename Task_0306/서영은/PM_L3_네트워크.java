package algo210306;
/**
 * <문제 요약>
 * 구해야 하는 것: 네트워크의 수를 구한다.
 * 제약 사항: 간접적으로 이어진 것도 하나의 네트워크로 본다. 
 * 문제 유형: 백트래킹(DFS) 
 * 요구 개념: 백트래킹(DFS) 
 * 
 * <풀이법 요약>
 * 1. 0번 인덱스부터 시작해주기 위해서 idx에 -1부터 담음
 * 2. 백트래킹을 돌면서 확인
 * -> 틀림. 풀이보고 다시 생각 
 * 다시 풀어보기!
 * -> 행과 열이 같은 인덱스일 때도 넣어줘야 하는 부분을 간과함.
 * 
 * 아직 방문하지 않은 점이고, 1이면 계속 타고 들어간 후에 방문 표시를 해준다.
 *
 */
public class PM_L3_네트워크 {

	public static void main(String[] args) {
//		int[][] com = {{1,1,0},{1,1,0},{0,0,1}};
		int[][] com = {{1,1,0},{1,1,1},{0,1,1}};
		System.out.println(solution(3, com));

	}
	static boolean[] visited;
	
	public static int solution(int n, int[][] computers) {
        visited = new boolean[n];
		
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i, n, computers);
				answer++;
			}
		}

        return answer;
    }

	private static void dfs(int r, int nCnt, int[][] com) {
		visited[r]=true;
		
		for (int i = 0; i < nCnt; i++) {
			if(!visited[i] && com[r][i]==1) {
				dfs(i,nCnt, com);
			}
		}
	}

}
