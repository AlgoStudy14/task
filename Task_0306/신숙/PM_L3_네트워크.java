package DFS_BFS;

public class PM_L3_네트워크 {
	/*
	 * <네트워크>
	 * 컴퓨터 개수 n으로 주어짐.(n = 3, 0 ~ 2)
	 * 배열로 연결 정보 주어짐(int[n][n], 항상 int[i][i] 는 1. => 자기자신?) 
	 * 연결되었다면 네트워크를 1로 칠때, 주어진 연결정보에 대한 네트워크 개수는?
	 * 
	 * <풀이방법>
	 * 1. 재귀 진행하면서 배열을 확인하고 연결되지않은 FALSE가 있다면 COUNT++ 해주며 연결 안 된 곳에서 또 재귀로 진행하면 될 것같은데?
	 */
	static boolean[] isLink;
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        isLink = new boolean[n];
        
        for(int i = 0; i < n; i++) {
        	if(!isLink[i]) {
        		answer++;
        		dfs(i, computers);
        	}
        }
        return answer;
    }
	
	static void dfs(int num, int[][] computers) {
		isLink[num] = true;
		int leng = computers.length;
		for(int i = 0; i < leng; i++) {
			//숫자가 같을땐 자기자신이라 패스, 내부 값이 1이여야 이어지며, 연결이 되어있지 않은 경우.
			if(num != i && computers[num][i] == 1 && !isLink[i])
				dfs(i, computers);
		}
	}
	
	public static void main(String[] args) {
		int ans = solution(3, new int[][] { {1,1,0}, {1,1,1}, {0, 1, 1} } );
		System.out.println(ans);
	}
}
